import scrapy


class LoginSpider(scrapy.Spider):
    name = 'login'
    allowed_domains = ['ids.hhu.edu.cn']
    start_urls = ['http://ids.hhu.edu.cn/amserver/UI/Login?goto=http://form.hhu.edu.cn/pdc/form/list']

    def parse(self, response):
        form_data = {
            "IDToken0": "",
            "IDToken1": "1961310319",
            "IDToken2": "wangzixiao621.",
            "IDButton": "Submit",
            "goto": "aHR0cDovL2Zvcm0uaGh1LmVkdS5jbi9wZGMvZm9ybS9saXN0",
            "encoded": "true",
            "inputCode": "",
            "gx_charset": "UTF-8",
        }
        yield scrapy.FormRequest(url="http://ids.hhu.edu.cn/amserver/UI/Login",callback=self.call_back,formdata=form_data)

    def call_back(self,response):
        if "本科生健康打卡" in response.text:
            print("登录成功!")
            yield scrapy.Request(url="http://form.hhu.edu.cn/pdc/formDesignApi/S/gUTwwojq",callback=self.final)
        else:
            print("登录失败!")

    def final(self,response):

        print(response.text)