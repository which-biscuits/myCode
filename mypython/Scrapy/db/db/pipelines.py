import json


class DbPipeline:

    def open_spider(self,spider):
        # 爬虫文件开启，此方法执行
        self.f = open("film_pipe.txt","w",encoding="utf-8")

    def process_item(self, item, spider):
        # 有 item 传递时此方法执行
        json_data = json.dumps(dict(item),ensure_ascii=False) + "\n"
        self.f.write(json_data)
        return item

    def close_spider(self,spider):
        # 爬虫文件关闭，此方法执行
        self.f.close()
