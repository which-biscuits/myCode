import pymysql

DATABASE_CONFIG = {
    "type":"mysql",
    "config":{
        "host":"localhost",
        "port":3306,
        "user":"1141207643",
        "password":"wangzixiao621.",
        "db":"zhnovel",
        "charset":"utf8"
    }
}
con = pymysql.connect(host='localhost', user='1141207643', password='wangzixiao621.', database='zhnovel', port=3306,
                          charset='utf8')
with con.cursor() as cur:
    b = cur.execute('select * from data')