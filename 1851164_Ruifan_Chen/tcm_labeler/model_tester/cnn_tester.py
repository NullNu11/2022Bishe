"""
This program predicts music with trained CNN models.
"""

import os
import numpy as np  # 1.19.5
from sklearn.metrics import accuracy_score  # 0.19.1
from keras import models  # 2.2.0

import utility

model_dir = '_models/cnn_models'
default_log_dir = os.path.join('logs/cnn_models')


# 根据数据类型选择模型：
def load_model(data_type, data_len):
    cur_model_dir = os.path.join(model_dir, data_type, data_len, 'model.h5')
    model = models.load_model(cur_model_dir)
    return model


# 测试模型：
def test_cnn_model(x_test, y_test, data_type, data_len, song_title, log_dir=default_log_dir):
    # 测试模型：
    y_pred_test = []
    model = load_model(data_type, data_len)
    for each in x_test:
        y_pred_test.append(np.argmax(model.predict(np.asarray([each]))[0]))
    del model
    test_acc = accuracy_score(y_test, y_pred_test)
    # 将测试结果写入日志：
    cur_log_dir = os.path.join(log_dir, data_type, data_len, song_title)
    utility.check_dir(cur_log_dir)
    acc_info = 'test_acc: ' + str(test_acc)
    detail_info = 'details (correct/predict) : \n'
    for i in range(len(y_test)):
        detail_info = detail_info + str(y_test[i]) + ' ' + str(y_pred_test[i]) + '\n'
    log_file = open(os.path.join(cur_log_dir, 'log.txt'), 'w')
    log_file.write(acc_info + '\n' + detail_info)
    log_file.close()
