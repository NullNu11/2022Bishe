"""
This program provides tools for the whole project.
"""

import os
import shutil

# 项目根目录：
root_dir = os.path.abspath(os.path.dirname(os.getcwd()))
root_dir_2 = os.path.abspath(os.path.join(os.getcwd(), "../.."))


# 获取trg_dir下所有文件的路径：
def get_all_fp(trg_dir):
    fp = []  # 文件路径列表
    for root, dirs, files in os.walk(trg_dir):
        for name in files:
            if name.startswith('.'):  # 用于跳过macOS系统下的'.DS_Store'文件
                continue
            fp.append(os.path.join(root, name))
    fp.sort()
    return fp


# 检查路径，路径不存在时创建路径：
def check_dir(trg_dir):
    if not os.path.exists(trg_dir):
        os.makedirs(trg_dir)


# 检查路径，路径存在时删除路径：
def del_dir(trg_dir):
    if os.path.exists(trg_dir):
        shutil.rmtree(trg_dir)


# 打印明细：
def print_verbose(msg, cnt_blank):
    msg_len = len(msg)
    print('*' * msg_len + '\n' + msg + '\n' + '*' * msg_len + '\n' * cnt_blank)
