#!/usr/bin/python
# -*- coding: UTF-8 -*-

import os
import sys
import time
import cv2

convert_cmd = 'ffmpeg -threads 4 -i deal_video\\src_video_name -vcodec libx264 -preset fast -crf 24 -y -vf ' \
              '"scale=1920:-1" -acodec libmp3lame -ab 32k deal_result\\dest_video_name '


def transform():
    root = os.getcwd()
    video_src = "deal_video\\"
    i_num = 0
    root_to = os.path.join(root, video_src)
    for (dirname, subdir, subfile) in os.walk(root_to):
        for f in subfile:
            if f.find(".mp4") > -1:
                # video_names.append(f)
                if i_num < 2:
                    i_num += 1
                else:
                    time.sleep(50)
                    i_num = 0
                start_time = time.time()
                roots = os.path.join(root_to, f)
                new_video_names = f.replace(' ', '#')

                dstFile = os.path.join(root_to, new_video_names)
                os.rename(roots, dstFile)
                video_1 = cv2.VideoCapture(dstFile)
                video_width = int(video_1.get(cv2.CAP_PROP_FRAME_WIDTH))
                video_height = int(video_1.get(cv2.CAP_PROP_FRAME_HEIGHT))
                print(f)
                print(video_width)
                print(video_height)
                if not os.path.exists(roots):
                    continue
                cmd = convert_cmd.replace('1920', str(video_width)).replace('-1', str(video_height)).replace(
                    'src_video_name', new_video_names).replace("dest_video_name", new_video_names)
                print(cmd)
                os.popen(cmd)
                end_time = time.time()
                print("Done in " + str(end_time - start_time) + " seconds")


if __name__ == '__main__':
    transform()
