# _*_ coding:utf-8 _*_
import scrapy
import json
from ..items import DbItem  # 安全的字典


class Db250Spider(scrapy.Spider):  # 继承基础类
    name = 'db250'  # 爬虫文件名字，必须存在且唯一
    allowed_domains = ['movie.douban.com']  # 允许的域名，可以不存在   不存在即允许访问任何域名
    start_urls = ['https://movie.douban.com/top250']  # 初始URL 至少存在一个
    page_number = 1
    #初始化文件，将文件变为空白
    with open("film.txt", "w", encoding="utf-8") as f:
        pass

    def parse(self, response):  # 请求函数，处理响应的response数据
        node_list = response.xpath('//div[@class="info"]') # xpath方式匹配response中的数据
        with open("film.txt", "a", encoding="utf-8") as f:
            for node in node_list:
                # 电影名字 text() 取出标签对应的值
                film_name = node.xpath("./div/a/span/text()")[0].extract()
                # 导演信息 xpath匹配到的是 selector 对象，通过 extract() 可获取到对应的值
                director_name = node.xpath("./div/p/text()")[0].extract().strip()
                # score 通过 @ 属性=value的方式匹配 对应的标签
                score = node.xpath('./div/div/span[@property="v:average"]/text()')[0].extract()

                # 非管道存储 字典
                item = {"film_name": film_name, "director_name": director_name, "score": score}
                # 转为 Json 对象存储 ensure_ascii 以ascii编码
                content = json.dumps(item, ensure_ascii=False) + "\n"
                f.write(content)

                # 管道存储
                item_pipe = DbItem()  # 创建一个Dbitem对象  以类字典方式使用字典使用
                item_pipe['film_name'] = film_name
                item_pipe['director_name'] = director_name
                item_pipe['score'] = score

                # 电影简介，次级URL
                detail_url = node.xpath('./div/a/@href')[0].extract()
                # 产生迭代对象，不终止函数
                # callback 将response返回至对应函数运行
                # meta 实现异步request数据的整合，
                yield scrapy.Request(detail_url, callback=self.get_detail,meta={"info":item_pipe})

        # 发送新一页的请求
        # 构造URL
        page_url = "https://movie.douban.com/top250?start={}&filter=".format(self.page_number * 25)
        # 限制读取页数
        if self.page_number == 10:
            return
        self.page_number += 1
        yield scrapy.Request(page_url)


    def get_detail(self, response):
        # 解析详情页的response
        # meta会跟随response一起返回，通过response.meta回收
        item = DbItem()
        info = response.meta["info"]
        # update 将继承的 info 值传递给item
        item.update(info)
        # 电影简介
        film_detail = response.xpath('//span[@property="v:summary"]/text()').extract()
        film_detail = "".join(film_detail).strip()
        film_detail = "".join(film_detail.split())
        item["film_detail"] = film_detail
        # 将完整的爬取信息返回给管道
        yield item
