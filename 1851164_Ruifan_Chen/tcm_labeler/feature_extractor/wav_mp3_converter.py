"""
This program converts wav files to mp3 files.
"""

import os
from pydub import AudioSegment  # 0.25.1, installed with 'conda install -c main ffmpeg' (PIP INSTALLATIONS MAY RAISE
# ERRORS!)

import utility

org_dir = os.path.join(utility.root_dir, '_data/chopped')  # 原始文件存储根目录
default_prc_dir = org_dir + '_mp3'  # 处理后文件存储根目录

trg_br = '128'  # 目标比特率


# 将path中的所有音频文件转换为subtype编码格式：
def convert_audio(trg_dir, prc_dir=default_prc_dir):
    utility.print_verbose('Converting wav files to mp3 files...', 3)
    utility.del_dir(prc_dir)
    utility.check_dir(prc_dir)
    for file in trg_dir:
        print(file)
        # 生成目标文件目录：
        file_len = file.rpartition('/')[0].rpartition('/')[0].rpartition('/')[2]
        file_title = file.rpartition('/')[0].rpartition('/')[2]
        file_name = file.rpartition('/')[2].rpartition('.')[0]
        cur_prc_dir = os.path.join(prc_dir, file_len, file_title)
        utility.check_dir(cur_prc_dir)
        cur_prc_fp = os.path.join(cur_prc_dir, file_name + '.mp3')
        # 转换文件：
        audio = AudioSegment.from_wav(file)
        audio.export(cur_prc_fp, format='mp3', bitrate=trg_br)
        del audio


# 设置目标文件：
org_fp = utility.get_all_fp(org_dir)

# 转换音频：
convert_audio(org_fp)
