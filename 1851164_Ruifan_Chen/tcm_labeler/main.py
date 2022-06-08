"""
This program labels audio files in 'YOUR_DATA/YOUR_FILES_HERE'.
"""

from feature_extractor.audio_chopper import chop_audio
from feature_extractor.wav_mp3_converter import convert_audio
from feature_extractor.feature_extractor import extract_features
from model_tester.data_loader import get_data
from model_tester.cnn_tester import test_cnn_model

import os
import sys
import time
import pandas as pd  # 1.1.5

import utility

org_dir = 'YOUR_DATA/YOUR_FILES_HERE'
chopped_dir = 'YOUR_DATA/tmp/chopped'
converted_dir = 'YOUR_DATA/tmp/chopped_mp3'
feat_dir = 'YOUR_DATA/tmp/features'
mel_dir = 'YOUR_DATA/tmp/features/mel'
log_dir = 'YOUR_DATA/tmp/logs'
res_dir = 'YOUR_DATA/YOUR_RESULTS_HERE'

all_trg_len = [5, 10, 20]
data_type = ['all_f', 'chroma', 'mel']
data_len = ['5s', '10s', '20s']
type_len_feat_dict = {'all_f/10s': 0, 'all_f/20s': 1, 'mel/10s': 2, 'chroma/5s': 3, 'chroma/10s': 4}
weights = [30, 30, 20, 10, 10]
type_names = {0: '宫', 1: '商', 2: '角', 3: '徵', 4: '羽'}
columns = ['song_title', 'predicted_label']


# 重置临时目录
def reset_dirs():
    utility.print_verbose('Resetting directories...', 3)
    utility.del_dir(chopped_dir)
    utility.check_dir(chopped_dir)
    utility.del_dir(converted_dir)
    utility.check_dir(converted_dir)
    utility.del_dir(feat_dir)
    utility.check_dir(feat_dir)


# '''
# 音频预处理与特征提取：
reset_dirs()
org_fp = utility.get_all_fp(org_dir)
if len(org_fp) <= 0:
    print('Please rerun this program after you place your files in the directory given below:\n' + org_dir)
    sys.exit(0)
for cur_trg_len in all_trg_len:
    chop_audio(org_fp, cur_trg_len, chopped_dir)
chopped_fp = utility.get_all_fp(chopped_dir)
convert_audio(chopped_fp, converted_dir)
converted_fp = utility.get_all_fp(converted_dir)
extract_features(converted_fp, feat_dir)
# '''

# '''
utility.print_verbose('Removing temporary files...', 3)
utility.del_dir(chopped_dir)
utility.del_dir(converted_dir)
# '''


# 获取待测歌曲列表：
org_fp = utility.get_all_fp(org_dir)
song_list = []
for file in org_fp:
    song_list.append(file.rpartition('/')[2].rpartition('.')[0])

# '''
# 逐一预测数据：
utility.print_verbose('Predicting features...', 3)
for cur_data_type in data_type:
    utility.print_verbose('Predicting ' + cur_data_type + ' files...', 2)
    cur_type_dir = os.path.join(feat_dir, cur_data_type)
    for cur_data_len in data_len:
        if cur_data_len == '5s' and cur_data_type != 'chroma':
            continue
        if cur_data_len == '20s' and cur_data_type != 'all_f':
            continue
        utility.print_verbose('Predicting ' + cur_data_len + ' files...', 1)
        cur_len_dir = os.path.join(cur_type_dir, cur_data_len)
        for each_song in song_list:
            print(each_song)
            cur_song_dir = os.path.join(cur_len_dir, each_song)
            # 按当前数据类型与数据长度找到并读取待测数据：
            x_test, y_test = get_data(cur_song_dir, is_svm=False)
            # 测试CNN模型：
            test_cnn_model(x_test, y_test, cur_data_type, cur_data_len, each_song, log_dir)
            del x_test, y_test
# '''

# '''
# 根据日志求结果矩阵：
utility.print_verbose('Collecting logs...', 3)
res_mtrx = [['0' for i in range(5)] for j in range(len(org_fp))]
log_fp = utility.get_all_fp(log_dir)
for each_log in log_fp:
    print(each_log)
    cur_log = open(each_log, mode='r')
    log = cur_log.readlines()
    cur_log.close()
    pred = ''
    predicts = []
    cnt_predicts = []
    for line in range(2, len(log)):
        predicts.append(log[line].strip().partition(' ')[2])
    for res in range(5):
        cnt_predicts.append(predicts.count(str(res)))
    max_cnt_predicts = max(cnt_predicts)
    for i in range(5):
        if cnt_predicts[i] == max_cnt_predicts:
            pred = pred + str(i)
    song_title = each_log.rpartition('/')[0].rpartition('/')[2]
    type_len_feat = each_log.rpartition('/')[0].rpartition('/')[0].partition('/')[2].partition('/')[2].partition('/')[2]
    res_mtrx[song_list.index(song_title)][type_len_feat_dict[type_len_feat]] = pred

# 根据结果矩阵按既定权重求最终结果：
utility.print_verbose('Tagging audio files...', 3)
pred_table = [['' for i in range(2)] for j in range(len(org_fp))]
for i in range(len(org_fp)):
    print(song_list[i])
    pred_table[i][0] = song_list[i]
    cur_pred = [0 for j in range(5)]
    for j in range(5):
        for k in range(len(res_mtrx[i][j])):
            cur_pred[int(res_mtrx[i][j][k])] = cur_pred[int(res_mtrx[i][j][k])] + int(weights[j] / len(res_mtrx[i][j]))
    max_cur_pred = max(cur_pred)
    pred = ''
    for j in range(5):
        if cur_pred[j] == max_cur_pred:
            pred = pred + type_names[j]
    pred_table[i][1] = pred

# 打印结果：
utility.print_verbose('Generating results...', 3)
utility.check_dir(res_dir)
table_df = pd.DataFrame(pred_table, columns=columns)
sheet_name = time.strftime("%Y_%m_%d_%H_%M", time.localtime())
table_df.to_excel(os.path.join(res_dir, 'results.xlsx'), sheet_name=sheet_name)
# '''

# 程序结束：
utility.print_verbose('JOB DONE.', 3)
