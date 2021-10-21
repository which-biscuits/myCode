import scrapy


class BdSpider(scrapy.Spider):
    name = 'bd'
    allowed_domains = ['www.baidu.com']
    start_urls = ['http://www.baidu.com/']

    def parse(self, response):
        self.logger.warning("可能会有错误!")
        print(response)
