import pymysql

con = pymysql.connect(host='106.14.245.132', user='root', password='wangzixiao621.', database='students', port=3306,
                      charset='utf8')

with con.cursor() as cur:

    sql = 'insert into data values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)'
    sql1 = 'select * from data where '
    cur.execute(sql)
con.commit()

# with con.cursor() as cur:
#     sql = 'select * from data'
#     a = cur.execute(sql)
#     for i in range(a):
#         print(cur.fetchone())
# con.commit()