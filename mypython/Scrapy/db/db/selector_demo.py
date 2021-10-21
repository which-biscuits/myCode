html_str = """
<div class="info">
    <div class="hd">
        <a href="https://movie.douban.com/subject/1292052/" class="">
            <span class="title">肖申克的救赎</span>
            <span class="title">&nbsp;/&nbsp;The Shawshank Redemption</span>
            <span class="other">&nbsp;/&nbsp;月黑高飞(港)  /  刺激1995(台)</span>
        </a>
        <span class="playable">[可播放]</span>
    </div>
    <div class="bd">
        <p class="">
            导演: 弗兰克·德拉邦特 Frank Darabont&nbsp;&nbsp;&nbsp;主演: 蒂姆·罗宾斯 Tim Robbins /...<br>
            1994&nbsp;/&nbsp;美国&nbsp;/&nbsp;犯罪 剧情
        </p>
        <div class="star">
            <span class="rating5-t"></span>
            <span class="rating_num" property="v:average">9.7</span>
            <span property="v:best" content="10.0"></span>
            <span>2303039人评价</span>
        </div>
            <p class="quote">
                <span class="inq">希望让人自由。</span>
            </p>
    </div>
</div>
"""

from scrapy.selector import Selector

# 通过text参数构建对象
selc_text = Selector(text=html_str)
print(selc_text.xpath('//div[@class="info"]//div/a/span/text()').extract()[0])


# 通过response 构造selector对象
from scrapy.http import HtmlResponse

response = HtmlResponse(url="http://www.example.com", body=html_str.encode())
Selector(response=response)
print(response.selector.xpath('//div[@class="info"]//div/a/span/text()').extract()[0])

# 嵌套表达式   可任意使用 css xpath re (对selector对象)
print(response.css("a").xpath("./span[@class='title']/text()").extract()[0])
print(response.css("a").xpath("./span[@class='title']/text()").re("肖申克.."))
