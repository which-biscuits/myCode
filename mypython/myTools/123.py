# coding=UTF-8
import os, sys
import subprocess
import glob


def ffmpeg_VideoToAudio(VideoPath, WavPath):
    # 提取视频路径下所有文件名
    videos = os.listdir(VideoPath)
    count = 0
    i = 1
    for video in videos:
        # 提取视频的全路径名（含路径+文件名）
        video_path = VideoPath + "/" + video
        print(video_path)
        # 合成输出音频的全路径名（不含后缀）
        wav_path = WavPath + "/" + str(i) + ".mp4"
        print(wav_path)
        # 提取视频中的音频信息
        # -i source.mp4 -c:v libx264 -crf 24 destination.flv
        # ffmpeg.exe - i record.mp4 -vf eq = contrast = 1:brightness = 0.2 output.mp4
        strcmd = "ffmpeg -i " + video_path + " -threads 5 -preset ultrafast -vf " \
                                             "eq=contrast=1:brightness=0.2:saturation=1.5 " + wav_path
        subprocess.call(strcmd, shell=True)
        i = i + 1

def fileAppend(filename):
    myfile = open(filename,'a')
    myfile.write("####&&&&***")
    myfile.close

if __name__ == '__main__':

    # VideoPath = r'D://123'
    # WavPath = r'D://out'
    # ffmpeg_VideoToAudio(VideoPath, WavPath)

    print("wzx")

    dirname = r'D:\\123' #需要修改的视频的文件目录
    allFile = glob.glob(dirname + os.sep + '*.mp4')

    for filename in allFile:
        fileAppend(filename)
        print(filename + 'is Changed.')
