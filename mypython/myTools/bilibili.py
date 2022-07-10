# <meta data-vue-meta="true" itemprop="url" content="https://www.bilibili.com/video/BV1R4411W7FL/">
import sys
from you_get import common as you_get
from multiprocessing import Pool

# 路径自己定义
directory = r'C:\Users\11412\Desktop\计算机图形学中国农大'
base_url = 'https://www.bilibili.com/video/BV1R4411W7FL?p='
urls = []


# 获取所有需要下载的url
def get_urls(p_num):
    for i in range(1, p_num):
        url = base_url + str(i)
        urls.append(url)
    return urls


def download(urls):
    sys.argv = ['you-get', '-o', directory, '--no-caption', urls]
    you_get.main()


if __name__ == '__main__':
    urls = get_urls(133)
    pool = Pool(10)
    pool.map(download, urls)
    pool.close()
    pool.join()


