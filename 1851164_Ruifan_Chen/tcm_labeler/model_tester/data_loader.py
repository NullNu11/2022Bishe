"""
This program loads feature sets.
"""

import sys
import numpy as np  # 1.19.5
from PIL import Image  # 8.4.0
from sklearn.preprocessing import normalize  # 0.19.1

import utility


# 加载numpy文件并归一化：
def load_npy(trg_fp, is_svm):
    x = []
    y = []
    # 加载所有数据：
    for file in trg_fp:
        data_type = file.rpartition('/')[0].rpartition('/')[0].rpartition('/')[0].rpartition('/')[2]
        x_in = np.load(file)
        if data_type != 'mel_d':
            x_in = np.abs(normalize(x_in))
            if data_type != 'all_f':
                x_in = x_in.reshape(x_in.shape[0], x_in.shape[1], 1)
            if is_svm:
                x_in = x_in.flatten()
        x.append(x_in)
        try:
            y_in = int(file.rpartition('/')[0].rpartition('/')[2][0]) - 1
            y.append(y_in)
        except ValueError:
            y_in = 0
            y.append(y_in)
        else:
            y_in = 0
            y.append(y_in)
    return np.asarray(x), np.asarray(y)


# 加载图片文件并归一化：
def load_jpg(trg_fp, is_svm):
    x = []
    y = []
    # 加载所有数据：
    for file in trg_fp:
        img = Image.open(file)
        if img.mode != 'L':
            img.convert('L')
        x_in = np.asarray(img) / 255.
        x_in = x_in.reshape(x_in.shape[0], x_in.shape[1], 1)
        if is_svm:
            x_in = x_in.flatten()
        x.append(x_in)
        try:
            y_in = int(file.rpartition('/')[0].rpartition('/')[2][0]) - 1
            y.append(y_in)
        except ValueError:
            y_in = 0
            y.append(y_in)
        else:
            y_in = 0
            y.append(y_in)
    return np.asarray(x), np.asarray(y)


# 加载目标数据：
def get_data(trg_dir, is_svm):
    trg_fp = utility.get_all_fp(trg_dir)
    # 获取文件格式：
    trg_format = trg_fp[0].rpartition('.')[2]
    if trg_format == 'npy':
        return load_npy(trg_fp, is_svm)
    elif trg_format == 'jpg':
        return load_jpg(trg_fp, is_svm)
    else:
        print('ERROR: Invalid file format!')
        sys.exit(0)
