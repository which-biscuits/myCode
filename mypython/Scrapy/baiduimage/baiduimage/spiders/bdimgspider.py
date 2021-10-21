import os
import re

import scrapy
from ..items import BaiduimageItem


class BdimgspiderSpider(scrapy.Spider):
    name = 'bdimgspider'
    # allowed_domains = ['https://image.baidu.com/']
    start_urls = [
        'https://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gb18030&word=%C3%A8%DF%E4&fr'
        '=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=000000']
    page_url = "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E7%8C%AB%E5%92%AA&step_word=&hs=2" \
               "&pn=5&spn=0&di=97390&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1" \
               "&st=undefined&cs=3985841650%2C985795826&os=619392083%2C4218942422&simid=0%2C0&adpicid=0&lpn={}&ln=1477" \
               "&fr=&fmq=1616575123288_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright" \
               "=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=11" \
               "&oriquery=&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fnimg.ws.126.net" \
               "%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0324" \
               "%252F7167bb88j00qqgqjb001pc000jg00jgm.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg" \
               "%26refer%3Dhttp%3A%2F%2Fnimg.ws.126.net%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g" \
               "%3D0n%26fmt%3Djpeg%3Fsec%3D1619168189%26t%3D060f2c0b419a82356c74a7af5a5f795a&fromurl=ippr_z2C" \
               "%24qAzdH3FAzdH3Fooo_z%26e3B8mn_z%26e3Bv54AzdH3F1yAzdH3Fw6ptvsjAzdH3FGcS0TUBVac9dbdMN_z%26e3Bip4s&gsm" \
               "=2&rpstart=0&rpnum=0&islist=&querylist=&force=undefined "
    num = 0
    page_num = 0

    def parse(self, response):
        # 解析 page 页 获取图片 url
        img_urls = re.findall('"thumbURL":"(.*?)"', response.text)
        # 发起请求 获取图片数据
        for index, img_url in enumerate(img_urls):  # 枚举
            yield scrapy.Request(img_url, callback=self.get_img)
        self.page_num += 1
        if self.page_num == 20:
            return
        page_url = self.page_url.format(self.page_num)
        yield scrapy.Request(page_url)

    def get_img(self, response):
        print(response.status)
        img_data = response.body
        # 直接存储
        if not os.path.exists("dirspider"):  # 当文件夹不存在时
            os.mkdir("dirspider")
        file_name = "dirspider/%s.jpg" % str(self.num)
        self.num += 1
        with open(file_name, "wb") as f:
            f.write(img_data)

        # 使用管道存储
        item = BaiduimageItem()
        item["img_data"] = img_data
        yield item
