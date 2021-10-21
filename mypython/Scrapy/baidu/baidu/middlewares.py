# Define here the models for your spider middleware
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/spider-middleware.html

from scrapy import signals

# useful for handling different item types with a single interface
from itemadapter import is_item, ItemAdapter


class BaiduSpiderMiddleware:
    # Not all methods need to be defined. If a method is not defined,
    # scrapy acts as if the spider middleware does not modify the
    # passed objects.

    @classmethod
    def from_crawler(cls, crawler):
        # This method is used by Scrapy to create your spiders.
        s = cls()
        crawler.signals.connect(s.spider_opened, signal=signals.spider_opened)
        return s

    def process_spider_input(self, response, spider):
        # Called for each response that goes through the spider
        # middleware and into the spider.

        # Should return None or raise an exception.
        return None

    def process_spider_output(self, response, result, spider):
        # Called with the results returned from the Spider, after
        # it has processed the response.

        # Must return an iterable of Request, or item objects.
        for i in result:
            yield i

    def process_spider_exception(self, response, exception, spider):
        # Called when a spider or process_spider_input() method
        # (from other spider middleware) raises an exception.

        # Should return either None or an iterable of Request or item objects.
        pass

    def process_start_requests(self, start_requests, spider):
        # Called with the start requests of the spider, and works
        # similarly to the process_spider_output() method, except
        # that it doesn’t have a response associated.

        # Must return only requests (not items).
        for r in start_requests:
            yield r

    def spider_opened(self, spider):
        spider.logger.info('Spider opened: %s' % spider.name)


class BaiduDownloaderMiddleware:
    # Not all methods need to be defined. If a method is not defined,
    # scrapy acts as if the downloader middleware does not modify the
    # passed objects.

    @classmethod
    def from_crawler(cls, crawler):
        # This method is used by Scrapy to create your spiders.
        s = cls()
        crawler.signals.connect(s.spider_opened, signal=signals.spider_opened)
        return s

    def process_request(self, request, spider):
        # Called for each request that goes through the downloader
        # middleware.

        # Must either: 以下必选其一
        # - return None: continue processing this request   # 返回 None request 进入下一个中间件进行处理
        # - or return a Response object     # 直接交给下载器
        # - or return a Request object      # 直接交给引擎处理
        # - or raise IgnoreRequest: process_exception() methods of      # 抛出异常 使用 process_exception() 处理
        #   installed downloader middleware will be called
        return None

    def process_response(self, request, response, spider):
        # Called with the response returned from the downloader.
        # 处理响应
        # Must either;
        # - return a Response object    继续交给下一个中间件处理
        # - return a Request object     返回一个request对象 直接交给引擎处理
        # - or raise IgnoreRequest      抛出异常 使用 process_exception() 处理
        return response

    def process_exception(self, request, exception, spider):
        # Called when a download handler or a process_request()
        # (from other downloader middleware) raises an exception.
        # 处理异常

        # Must either:
        # - return None: continue processing this exception     继续调用其他中间件的process_exception()
        # - return a Response object: stops process_exception() chain   停止调用其他中间件的process_exception()
        # - return a Request object: stops process_exception() chain    同上
        pass

    def spider_opened(self, spider):
        spider.logger.info('Spider opened: %s' % spider.name)


from .settings import user_agent_list
import random


class User_AgentDownloaderMiddleware:

    def process_request(self, request, spider):
        request.headers["user_Agent"] = random.choice(user_agent_list)      # 随机选择代理

        return None

from .settings import IPPOOL


class MyproxyDownloaderMiddleware:
    # 目的 设置多个代理
    # 通过meta 设置代理

    def process_request(self, request, spider):
        proxyip = random.choice(IPPOOL)
        request.meta["proxy"] = "http://" + proxyip["ipaddr"]  # http://223.167.190.17:42789

        return None