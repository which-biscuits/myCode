import scrapy


class LoginSpider(scrapy.Spider):
    name = 'login'
    allowed_domains = ['github.com']
    start_urls = ['http://github.com/login']  # 首先访问登录页面

    def parse(self, response):
        authenticity_token = response.xpath('//input[@name="authenticity_token"]/@value').extract()[0]
        timestamp = response.xpath('//input[@name="timestamp"]/@value').extract()[0]
        timestamp_secret = response.xpath('//input[@name="timestamp_secret"]').extract()[0]
        required_field = response.xpath('//input[@type="text"]/@name').extract()[1]
        form_data = {
            "commit": "Sign in",
            "authenticity_token": authenticity_token,
            "login": "1141207643 qq.com",
            "password": "wangzixiao621.",
            "trusted_device": "",
            "webauthn - support": "supported",
            "webauthn - iuvpaa - support": "supported",
            "return_to": "",
            "allow_signup": "",
            "client_id": "",
            "integration": "",
            required_field: "",
            "timestamp": timestamp,
            "timestamp_secret": timestamp_secret,
        }
        yield scrapy.FormRequest(url="https://github.com/session", callback=self.verify_login, formdata=form_data)

    def verify_login(self, response):
        if "wzx" in response.text:
            print("登录成功!")
        else:
            print("登录失败!")


"""
commit: Sign in
authenticity_token: VuhIxpQJktTVOQTCUq+yL4L5ZKcS5qCx+NDEYGYNZh0LZrZ30xKc707CSwvIt0f8iMk1it9ZTwdDQd8+yEHf2g==
login: 1141207643@qq.com
password: wangzixiao621.
trusted_device: 
webauthn-support: supported
webauthn-iuvpaa-support: supported
return_to: 
allow_signup: 
client_id: 
integration: 
required_field_5eb9:  
timestamp: 1616324873641
timestamp_secret: de1efdc1a69837ca2f3a5d335ff610ef5dc839fd0c40a2971052a2668cc367c5
"""

"""需要构造的数据
login: 1141207643@qq.com
password: wangzixiao621.
authenticity_token:
timestamp: 
timestamp_secret: 
required_field_5eb9: 

参数获取的方式:

"""
