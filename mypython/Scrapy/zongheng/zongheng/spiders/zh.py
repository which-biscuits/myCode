import time
import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from ..items import NovelItem,ChapterItem,ContentItem


class ZhSpider(CrawlSpider):
    name = 'zh'
    allowed_domains = ['book.zongheng.com']  # 允许的域名
    start_urls = ['http://book.zongheng.com/store/c0/c0/b0/u0/p1/v0/s1/t0/u0/i1/ALL.html']  # 起始的URL

    # 定义爬取规则    提取URL   形成请求    响应的处理规则
    rules = (
        # allow 允许的URl 通过正则表达式匹配URL 可自动去重
        # follow = True response同时重新返回rules
        # restrict_xpaths  通过xpath限制提取路径
        # process_links 获取URL的指定处理函数
        # 小说 URL
        Rule(LinkExtractor(allow=r'http://book.zongheng.com/book/\d+.html',restrict_xpaths='//div[@class="bookname"]'),
             callback='parse_book', follow=True, process_links="process_booklink"),
        # 小说目录_具体章节URL
        Rule(LinkExtractor(allow=r'http://book.zongheng.com/showchapter/\d+.html'), callback='parse_catelog',
             follow=True,),
        # 小说章节_具体内容URL
        Rule(LinkExtractor(allow=r'http://book.zongheng.com/chapter/\d+/\d+.html',
             restrict_xpaths='//ul[@class="chapter-list"]'), callback='get_content', follow=False,
             process_links="process_chapter"),
    )

    def process_booklink(self, links):
        # 处理 LinkExtractor 提取到的URL
        for index, link in enumerate(links):
            if index <= 3:
                # print(index, link.url)
                yield link
            else:
                return

    def process_chapter(self,links):
        # 处理 LinkExtractor 提取到的URL
        for index, link in enumerate(links):
            if index <= 20:
                # print(index, link.url)
                yield link
            else:
                return

    def parse_book(self, response):
        category = response.xpath('//div[@class="book-label"]/a/text()').extract()[1]
        book_name = response.xpath('//div[@class="book-name"]/text()').extract()[0].strip()
        author = response.xpath('//div[@class="au-name"]/a/text()').extract()[0]
        status = response.xpath('//div[@class="book-label"]/a/text()').extract()[0]
        book_nums = response.xpath('//div[@class="nums"]/span/i/text()').extract()[0]
        description = ''.join(response.xpath('//div[@class="book-dec Jbook-dec hide"]/p/text()').extract())
        c_time = time.strftime("%Y-%m-%d %H:%m:%S")
        book_url = response.url
        catalog_url = response.css("a").re('http://book.zongheng.com/showchapter/\d+.html')[0]

        item = NovelItem()
        item["category"] = category
        item["book_name"] = book_name
        item["author"] = author
        item["status"] = status
        item["book_nums"] = book_nums
        item["description"] = description
        item["c_time"] = c_time
        item["book_url"] = book_url
        item["catalog_url"] = catalog_url
        yield item

    def parse_catelog(self, response):
        # 提取 a 标签   包含title 和 URL
        a_tags = response.xpath('//ul[@class="chapter-list clearfix"]/li/a')
        chapter_list = []
        catalog_url = response.url
        index = 0
        for a in a_tags:
            # print("解析catalog_url")
            if index <= 20:
                # 标题
                title = a.xpath("./text()").extract()[0]
                # URL
                chapter_url = a.xpath("./@href").extract()[0]
                book_name = response.xpath('//div[@class="book-meta"]/h1/text()').extract()[0]
                chapter_list.append((book_name,title,chapter_url,catalog_url))
                index+=1
            else:
                break
        item = ChapterItem()
        item["chapter_list"] = chapter_list
        yield item

    def get_content(self, response):
        chapter_url = response.url
        content = ''.join(response.xpath('//div[@class="content"]/p/text()').extract())
        # 向管道传递数据
        item = ContentItem()
        item["chapter_url"] = chapter_url
        item["content"] = content
        yield item
