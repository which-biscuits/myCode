from scrapy.http import Request, FormRequest

# FormRequest 继承于Request 对POST请求更加好用

"""
class Request(object_ref):
    def __init__(self, url, callback=None, method='GET', headers=None, body=None,
                 cookies=None, meta=None, encoding='utf-8', priority=0,
                 dont_filter=False, errback=None, flags=None, cb_kwargs=None):
url (字符串) - 请求的URL
callback (callable) - 回调函数
method (string) - 此请求的HTTP方法。默认值为”GET“
meta (dict) - Request.meta 属性的初始值
body (str / unicode) - 请求体 如果没有传参,默认为空字符串
headers (dict) - 此请求的请求头
cookies - 请求cookies
encoding (字符串) - 此请求的编码(默认为"utf-8") 此编码将用于对URL进行百分比编码并将body转化为str(如果指定Unicode)
priority (int) - 此请求的优先级(默认为0 big for best)
dont_filter (Boolean) - 表示调度程序不应过滤此请求
errback (callable) - 在处理请求时引发任何异常时将调用的函数
flags (list) - 发送给请求的标志,可用于日志记录或类似目的
"""

req = Request("http://www.baidu.com", headers={"spider": 666}, meta={"name": "爬虫"})
# 功能构造请求
# 参数
# 返回值
req.copy()  # 生成副本

print(req.url)
print(req.method)
print(req.headers)  # {b'Spider': [b'666']} 字节串
print(req.meta)
print(req.cookies)

# request的更新
rer = req.replace(url="https://www.baidu.com")
print(rer.url)

from scrapy.http.response import Response

"""
class Response(object_ref):
    def __init__(self, url, status=200, headers=None, body=b'', flags=None,request=None, certificate=None, ip_address=None):

功能:构造response对象     参数  返回值:response 对象

url (字符串) - 此响应的URL
status (整数) - 响应的HTTP状态,默认为200
headers (dict) - 此响应的响应头,dict值可以为字符串(对于单值标头)或列表(对于多值标头)
body (字节) - 响应主体,要将解码后的文本作为str访问,您可以通过使用response.text来自编码感知的response子类
flag (列表) - 是包含Response.flags属性初始值的列表.如果给定,列表将被浅层复制
request Request.request 属性的初始值.这表示Request生成此响应的内容
"""

res = Response("http://www.baidu.com", request=req)
print("****************************")
print(res.body)  # 响应内容
print(res.url)  # url
print(res.status)  # 状态码
print(res.request)  # 请求
print(res.headers)  # 响应头头
print(res.meta)  # 参数传递
# print(res.text)     # 响应内容
