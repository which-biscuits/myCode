# Scrapy settings for baidu project
#
# For simplicity, this file contains only settings considered important or
# commonly used. You can find more settings consulting the documentation:
#
#     https://docs.scrapy.org/en/latest/topics/settings.html
#     https://docs.scrapy.org/en/latest/topics/downloader-middleware.html
#     https://docs.scrapy.org/en/latest/topics/spider-middleware.html

# scrapy 项目名称
BOT_NAME = 'baidu'

# 爬虫模块
SPIDER_MODULES = ['baidu.spiders']
# 使用 genspider 命令创建的爬虫模块
NEWSPIDER_MODULE = 'baidu.spiders'

# 默认的用户代理 user-agent
# Crawl responsibly by identifying yourself (and your website) on the user-agent
# USER_AGENT = 'baidu (+http://www.yourdomain.com)'

# Obey robots.txt rules     遵从 ROBOT 协议
ROBOTSTXT_OBEY = False

# 设置 最大 并发 请求    通过 scrapy 操作
# Configure maximum concurrent requests performed by Scrapy (default: 16) 默认 16
# CONCURRENT_REQUESTS = 32

# 设置网络请求延时
# Configure a delay for requests for the same website (default: 0)
# See https://docs.scrapy.org/en/latest/topics/settings.html#download-delay
# See also autothrottle settings and docs
# DOWNLOAD_DELAY = 3    # 秒

# The download delay setting will honor only one of:    二选一
# CONCURRENT_REQUESTS_PER_DOMAIN = 16   # 单个域名允许的最大请求
# CONCURRENT_REQUESTS_PER_IP = 16       # 单个 ip 允许的最大请求

# Disable cookies (enabled by default)  # 默认使用 cookies
# COOKIES_ENABLED = False

# Disable Telnet Console (enabled by default)   # 设置使用 监控控制台  监控异常
# TELNETCONSOLE_ENABLED = False

# Override the default request headers:     # 默认请求头  request 中间件 均可添加
DEFAULT_REQUEST_HEADERS = {
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
    'Accept-Language': 'en',
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.72 Safari/537.36 Edg/89.0.774.45"
}

# 爬虫中间件
# Enable or disable spider middlewares
# See https://docs.scrapy.org/en/latest/topics/spider-middleware.html
# SPIDER_MIDDLEWARES = {
#    'baidu.middlewares.BaiduSpiderMiddleware': 543,
# }

# 下载中间件
# Enable or disable downloader middlewares
# See https://docs.scrapy.org/en/latest/topics/downloader-middleware.html
# 使用中间件
DOWNLOADER_MIDDLEWARES = {
    'baidu.middlewares.BaiduDownloaderMiddleware': 543,
    # 启用自定义中间件,并设设置参数(距离引擎距离)
    'baidu.middlewares.MyproxyDownloaderMiddleware': 450,
    # 关闭中间件
    # 'scrapy.downloadermiddlewares.redirect.MetaRefreshMiddleware': None,
}

# 扩展程序
# Enable or disable extensions
# See https://docs.scrapy.org/en/latest/topics/extensions.html
# EXTENSIONS = {
#    'scrapy.extensions.telnet.TelnetConsole': None,
# }

# Configure item pipelines    设置管道
# See https://docs.scrapy.org/en/latest/topics/item-pipeline.html
ITEM_PIPELINES = {
   'baidu.pipelines.BaiduPipeline': 300,      # 数字代表优先级,数字越小,优先级越高
}

# 启用 或 配置 扩展
# Enable and configure the AutoThrottle extension (disabled by default) 默认不允许
# See https://docs.scrapy.org/en/latest/topics/autothrottle.html
# AUTOTHROTTLE_ENABLED = True
# The initial download delay
# AUTOTHROTTLE_START_DELAY = 5      # 初始下载延时
# The maximum download delay to be set in case of high latencies
# AUTOTHROTTLE_MAX_DELAY = 60       # 最大延时
# The average number of requests Scrapy should be sending in parallel to
# each remote server
# AUTOTHROTTLE_TARGET_CONCURRENCY = 1.0     #
# Enable showing throttling stats for every response received:
# AUTOTHROTTLE_DEBUG = False        # 启用 显示收到每个响应的调节信息

# 启用配置 http缓存
# Enable and configure HTTP caching (disabled by default)
# See https://docs.scrapy.org/en/latest/topics/downloader-middleware.html#httpcache-middleware-settings
# HTTPCACHE_ENABLED = True
# HTTPCACHE_EXPIRATION_SECS = 0
# HTTPCACHE_DIR = 'httpcache'
# HTTPCACHE_IGNORE_HTTP_CODES = []
# HTTPCACHE_STORAGE = 'scrapy.extensions.httpcache.FilesystemCacheStorage'

# 日志的设置

# LOG_FORMAT = "%(asctime)s [%(name)s] %(levelname)s: %(message)s"
# LOG_DATEFORMAT = "%Y"
# LOG_FILE = "bd.log"
# LOG_ENABLED = True
# LOG_LEVEL = "WARNING"
# LOG_STDOUT = True

"""
LOG_FILE            日志的输出文件,如果为None,就打印在控制台
LOG_ENABLED         是否启用日志,默认为True
LOG_ENCODING        日期编码,默认为 utf-8
LOG_LEVEL           日志等级,默认为 DEBUG/调试信息 < INFO/一般信息 < WARNING/警告 < ERROR/一般错误 < CRITICAL/严重错误
LOG_FORMAT          日志格式
LOG_DATEFORMAT      日志日期格式
LOG_STDOUT          日志标准输出,默认False,如果为True所有标准输出都将写入日志中
LOG_SHORT_NAMES     短日志名,默认为False,如果为True将不再输出组件名

项目中一般设置:
LOG_FILE = 'log_name'
LOG_NAME = 'INFO'
"""

# user_agent
user_agent_list = [
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0",
    "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.5",
    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0",
    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
    "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)",
    "Mozilla/5.0 (compatible; MSIE 10.0; Windows Phone 8.0; Trident/6.0; IEMobile/10.0; ARM; Touch; NOKIA; Lumia 920)",
    "Opera/9.80 (Windows NT 6.1; WOW64) Presto/2.12.388 Version/12.12",
    "Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/ADR-1301071546) Presto/2.11.355 Version/12.10",
    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0 Safari/537.36 OPR/15.0",
    "Mozilla/5.0 (X11; CrOS armv7l 3428.193.0) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.126 Safari/537.22",
]

# ip代理
IPPOOL = [
    {"ipaddr": "61.129.70.131:8080"},
    {"ipaddr": "61.152.81.193:9100"},
    {"ipaddr": "120.204.85.29:3128"},
    {"ipaddr": "219.228.126.86:8123"},
    {"ipaddr": "61.152.81.193:9100"},
    {"ipaddr": "218.82.33.225:53853"},
    {"ipaddr": "223.167.190.17:42789"},
]
