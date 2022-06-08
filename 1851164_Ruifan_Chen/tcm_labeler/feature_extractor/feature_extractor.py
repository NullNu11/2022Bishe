"""
This program extract specific features from given audio files.
Features: Chromagram, Melspectrogram, and all-feature vector.
"""

import os
import librosa  # 0.7.2
import numpy as np  # 1.19.5
from PIL import Image  # 8.4.0

import utility

org_dir = os.path.join(utility.root_dir, '_data/chopped_mp3')  # 原始文件根目录
default_prc_dir = os.path.join(utility.root_dir, '_features')  # 处理后文件存储根目录


# 计算某特征的平均值、标准差与方差：
def cal_mean_std_var(trg_f, trg_v):
    trg_v.append(np.mean(trg_f))
    trg_v.append(np.std(trg_f))
    trg_v.append(np.var(trg_f))


# 提取Chroma图：
def get_chroma_stft(y, sr):
    # 提取Chroma图：
    S = np.abs(librosa.stft(y, n_fft=4096)) ** 2
    chroma = librosa.feature.chroma_stft(S=S, sr=sr)
    # 以灰度图的格式导出原始数据：
    chroma = chroma * 255
    chromagram = Image.fromarray(chroma)
    if chromagram.mode == 'F':
        chromagram = chromagram.convert('L')
    return chromagram


# 提取Mel图：
def get_melspectrogram(y, sr):
    # 提取Mel图：
    mel = librosa.feature.melspectrogram(y=y, sr=sr, n_mels=128, fmax=8000)
    return mel


# 提取RMS：
def get_rms(y):
    # 提取RMS：
    rms = librosa.feature.rms(y=y)
    return rms


# 提取Tonal Centroid图：
def get_tonnetz(y, sr):
    # 提取Tonal Centroid图：
    y = librosa.effects.harmonic(y)
    tonnetz = librosa.feature.tonnetz(y=y, sr=sr)
    # 以灰度图的格式导出原始数据：
    tonnetz = tonnetz * 2 * 127.5 + 127.5
    tonnetzgram = Image.fromarray(tonnetz)
    if tonnetzgram.mode == 'F':
        tonnetzgram = tonnetzgram.convert('L')
    return tonnetzgram


# 提取全特征向量：
def get_all_features(y, sr):
    fv = []  # 全特征向量
    S = np.abs(librosa.stft(y))
    oenv = librosa.onset.onset_strength(y=y, sr=sr)
    cal_mean_std_var(librosa.feature.chroma_stft(y=y, sr=sr), fv)
    cal_mean_std_var(librosa.feature.chroma_cqt(y=y, sr=sr), fv)
    cal_mean_std_var(librosa.feature.chroma_cens(y=y, sr=sr), fv)
    cal_mean_std_var(librosa.feature.melspectrogram(y=y, sr=sr), fv)
    cal_mean_std_var(librosa.feature.mfcc(y=y, sr=sr), fv)
    cal_mean_std_var(librosa.feature.rms(y=y), fv)
    cal_mean_std_var(librosa.feature.spectral_centroid(y=y, sr=sr), fv)
    cal_mean_std_var(librosa.feature.spectral_bandwidth(y=y, sr=sr), fv)
    cal_mean_std_var(librosa.feature.spectral_contrast(S=S, sr=sr), fv)
    cal_mean_std_var(librosa.feature.spectral_flatness(y=y), fv)
    cal_mean_std_var(librosa.feature.spectral_rolloff(y=y, sr=sr), fv)
    cal_mean_std_var(librosa.feature.poly_features(S=S, sr=sr, order=2), fv)
    cal_mean_std_var(librosa.feature.tonnetz(y=y, sr=sr), fv)
    cal_mean_std_var(librosa.feature.zero_crossing_rate(y=y), fv)
    cal_mean_std_var(librosa.beat.tempo(onset_envelope=oenv, sr=sr, aggregate=None), fv)
    return np.array(fv)


# 提取音频特征：
def extract_features(trg_fp, prc_dir=default_prc_dir):
    utility.print_verbose('Extracting features...', 3)
    for file in trg_fp:
        print(file)
        # 生成目标文件目录的关键信息：
        file_len = file.rpartition('/')[0].rpartition('/')[0].rpartition('/')[2]
        file_title = file.rpartition('/')[0].rpartition('/')[2]
        file_name = file.rpartition('/')[2].rpartition('.')[0]
        # 读取音频文件：
        audio = librosa.load(file)
        # '''
        if file_len != '20s':
            # 生成目标文件目录：
            cur_prc_dp = os.path.join(prc_dir, 'chroma', file_len, file_title)
            utility.check_dir(cur_prc_dp)
            cur_prc_fp = os.path.join(cur_prc_dp, file_name + '.jpg')
            # 提取Chroma图：
            chromagram = get_chroma_stft(audio[0], audio[1])
            chromagram.save(cur_prc_fp)
            del chromagram
        # '''
        # '''
        if file_len == '10s':
            # 生成目标文件目录：
            cur_prc_dp = os.path.join(prc_dir, 'mel', file_len, file_title)
            utility.check_dir(cur_prc_dp)
            cur_prc_fp = os.path.join(cur_prc_dp, file_name + '.npy')
            # 提取Mel图：
            melspectrogram = get_melspectrogram(audio[0], audio[1])
            np.save(cur_prc_fp, melspectrogram)
            del melspectrogram
        # '''
        # '''
        if file_len != '5s':
            # 生成目标文件目录：
            cur_prc_dp = os.path.join(prc_dir, 'all_f', file_len, file_title)
            utility.check_dir(cur_prc_dp)
            cur_prc_fp = os.path.join(cur_prc_dp, file_name + '.npy')
            # 提取全特征向量：
            fv_a = get_all_features(audio[0], audio[1])  # 获取音频完整的全特征向量
            fv_h = get_all_features(librosa.effects.harmonic(audio[0]), audio[1])  # 获取音频和声部分的全特征向量
            fv_p = get_all_features(librosa.effects.percussive(audio[0]), audio[1])  # 获取音频打击部分的全特征向量
            fv = np.stack((fv_a, fv_h, fv_p), axis=0)
            np.save(cur_prc_fp, fv)
            del fv_a, fv_h, fv_p, fv
        # '''
        del audio


# 设置目标文件：
org_fp = utility.get_all_fp(org_dir)

# 提取音频特征：
extract_features(org_fp)
