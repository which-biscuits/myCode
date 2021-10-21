# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
import time
from itemadapter import ItemAdapter
import pymysql
from zongheng.items import NovelItem,ChapterItem,ContentItem
from scrapy.exceptions import DropItem

class ZonghengPipeline:
    # 连接数据库
    def open_spider(self,spider):
        data_config = spider.settings["DATABASE_CONFIG"]
        print("数据库内容",data_config)
        if data_config["type"] == "mysql":
            self.conn = pymysql.connect(**data_config["config"])
            self.cursor = self.conn.cursor()
            spider.conn = self.conn
            spider.cursor = self.cursor

    # 数据存储
    def process_item(self, item, spider):
        # 1.小说信息存储
        if isinstance(item,NovelItem):
            sql = "select id from novel where book_name=%s and author=%s"
            self.cursor.execute(sql, (item["book_name"], item["author"]))
            if not self.cursor.fetchone():
                sql = "insert into novel(catagory,book_name,author,status,book_nums,description,c_time,book_url,catalog_url)" \
                      "values (%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                Tuple = (item["category"],item["book_name"],item["author"],item["status"],item["book_nums"],
                         item["description"],item["c_time"],item["book_url"],item["catalog_url"],)
            else:
                sql = "update novel set catagory=%s,book_name=%s,author=%s,status=%s,book_nums=%s,description=%s," \
                      "c_time=%s,book_url=%s,catalog_url=%s where book_name=%s and author=%s"
                Tuple = (item["category"], item["book_name"], item["author"],item["status"], item["book_nums"],
                         item["description"],item["c_time"], item["book_url"], item["catalog_url"],item["book_name"],
                         item["author"],)
            self.cursor.execute(sql, Tuple)
            self.conn.commit()
            return item
        # 2.章节信息存储
        elif isinstance(item, ChapterItem):
            sql1 = "select id from novel where book_name=%s and title=%s"
            # 写入  目录信息
            sql2 = "insert into chapter(book_name,title,ordernum,c_time,chapter_url,catalog_url) values (%s,%s,%s,%s,%s,%s)"
            data_list = []
            for index,chapter in enumerate(item["chapter_list"]):
                c_time = time.strftime("%Y-%m-%d %H:%m:%S")
                ordernum = index + 1
                book_name,title,chapter_url,catalog_url = chapter
                self.cursor.execute(sql1, (book_name, title))
                if not self.cursor.fetchone():
                    data_list.append((book_name,title,ordernum,c_time,chapter_url,catalog_url))
            self.cursor.executemany(sql2,data_list)
            self.conn.commit()
            return item
        # 3.章节内容存储
        elif isinstance(item, ContentItem):
            sql = "update chapter set content=%s where chapter_url=%s"
            content = item["content"]
            chapter_url = item["chapter_url"]
            self.cursor.execute(sql,(content,chapter_url))
            self.conn.commit()
            return item
        else:
            return DropItem

    # 关闭数据库
    def close_spider(self,spider):
        data_config = spider.settings["DATABASE_CONFIG"]  # steting 内设置数据库
        if data_config["type"] == "mysql":
            self.cursor.close()
            self.conn.close()
