# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class DbItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    # 定义要存储的信息 此处的名字应与其他文件调用DbItem类时的名字保持一致
    film_name = scrapy.Field()
    director_name = scrapy.Field()
    score = scrapy.Field()
    film_detail = scrapy.Field()
