# Traditional Chinese Music Labeler (tcm_labeler)

Ruifan Chen (1851164), Tongji University

chua-5149@qq.com



## Project Description

This project labels music files with five modes labels 'Gong', 'Shang', 'Jue', 'Zhi' and 'Yu', which can be used to regulate the missing, anxiety, anger, joy and fear. To run this project, install project dependencies properly and follow the user's manual.



## Project Dependencies

Python==3.6

librosa==0.7.2

pydub==0.25.1  # installation of package 'ffmpeg' with 'conda install -c main ffmpeg' required

numpy==1.19.5

pillow==8.4.0

scikit-learn==0.19.1

keras==2.2.0

pandas==1.1.5



## User's Manual

Simply place your files in 'YOUR_DATA/YOUR_FILES_HERE' and run main.py. The label results will show in 'YOUR_DATA/YOUR_RESULTS_HERE/results.xlsx' after the program ends. 

Detailed labeling information will be saved in 'YOUR_DATA/tmp/logs', and the feature sets used will be saved in 'YOUR_DATA/tmp/features'



## !NOTES!

### Workflow Explanation

1. audio_chopper: chops audio into clips of 5s, 10s, 20s and 30s.
2. wav_mp3_converter: converts clips into mp3 files.
3. feature extractor: extracts features from mp3 clips.
4. data_loader: loads a feature set and runs pre-processing program.
5. cnn_tester: labels audio clips.

### Example Explanation

A test run is done with my own test data set, whose results are generated in 'YOUR_DATA/YOUR_RESULTS_HERE'. To run this project with your own data, replace the files in 'YOUR_DATA/YOUR_FILES_HERE' with your own files and run main.py.