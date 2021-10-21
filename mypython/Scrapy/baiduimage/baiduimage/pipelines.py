# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
import os
# 媒体管道类
from scrapy.pipelines.images import ImagesPipeline

from itemadapter import ItemAdapter


class BaiduimagePipeline:

    num = 0
    def process_item(self, item, spider):
        if not os.path.exists("dirspider_pipe"):     # 当文件夹不存在时
            os.mkdir("dirspider_pipe")
        file_name = "dirspider_pipe/%s.jpg"%str(self.num)
        self.num += 1
        with open(file_name,"wb") as f:
            f.write(item[""])
        return item

class BdImagePipeline(ImagesPipeline):
    pass
