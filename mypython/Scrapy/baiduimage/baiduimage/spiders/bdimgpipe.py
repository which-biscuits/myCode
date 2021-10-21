import re
from ..items import BDImagePipeItem

import scrapy


class BdimgpipeSpider(scrapy.Spider):
    name = 'bdimgpipe'
    # allowed_domains = ['xxx']
    start_urls = [
        'https://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gb18030&word=%C3%A8%DF%E4&fr'
        '=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=000000']

    def parse(self, response):
        # 解析 page 页 获取图片 url
        img_urls = re.findall('"thumbURL":"(.*?)"', response.text)
        print(img_urls)
        item = BDImagePipeItem()
        item["image_urls"] = img_urls
        yield item
