
# 项目名称
BOT_NAME = 'db'

SPIDER_MODULES = ['db.spiders']
NEWSPIDER_MODULE = 'db.spiders'


# Obey robots.txt rules
# 是否遵循 robots 协议
ROBOTSTXT_OBEY = False

# Override the default request headers:
# requeest 响应的头文件
DEFAULT_REQUEST_HEADERS = {
  'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
  'Accept-Language': 'en',
  "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/"
                "89.0.4389.72 Safari/537.36 Edg/89.0.774.45"
}

# Configure item pipelines
# 启用管道 ·pipelines
ITEM_PIPELINES = {
   'db.pipelines.DbPipeline': 300,
}

