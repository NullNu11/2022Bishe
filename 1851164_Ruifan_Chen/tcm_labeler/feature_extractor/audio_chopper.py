"""
This program chops audio into clips of lengths given in 'all_trg_len'.
"""

import os
import librosa  # 0.7.2

import utility

org_dir = os.path.join(utility.root_dir, '_data/original')  # 原始文件根目录
default_prc_dir = os.path.join(utility.root_dir, '_data/chopped')  # 处理后文件存储根目录

all_trg_len = [5, 10, 20, 30]


# 将trg_fp中所有文件按trg_time裁切，不足trg_time的部分舍弃：
def chop_audio(trg_fp, trg_len, prc_dir=default_prc_dir):
    utility.print_verbose('Chopping audio files into ' + str(trg_len) + 's clips...', 3)
    cur_prc_dir = os.path.join(prc_dir, str(trg_len) + 's')
    for file in trg_fp:
        print(file)
        # 获取文件时长：
        audio = librosa.load(file)
        audio_len = librosa.get_duration(audio[0])
        # 生成目标文件目录：
        file_title = file.rpartition('/')[2].rpartition('.')[0]
        cur_file_dir = os.path.join(cur_prc_dir, file_title)
        utility.check_dir(cur_file_dir)
        cur_prc_fp = os.path.join(cur_file_dir, file_title)        # 切割音频并保存：
        offset = 0
        clip_cnt = 1
        while offset <= audio_len - trg_len:
            clip = librosa.load(path=file, sr=44100, mono=False, offset=offset, duration=trg_len)
            cur_prc_file_name = cur_prc_fp + ' ' + str(clip_cnt) + '.wav'
            librosa.output.write_wav(path=cur_prc_file_name, y=clip[0], sr=clip[1], norm=False)
            offset = offset + trg_len
            clip_cnt = clip_cnt + 1
            del clip
        del audio


# 设置目标文件：
org_fp = utility.get_all_fp(org_dir)

# 裁切音频：
for cur_trg_len in all_trg_len:
    chop_audio(org_fp, cur_trg_len)
