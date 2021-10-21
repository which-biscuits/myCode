import pymysql

con = pymysql.connect(host='106.14.245.132', user='root', password='wangzixiao621.', database='students', port=3306,
                      charset='utf8')

with con.cursor() as cur:
    students = [('1962410108', '张婉玉', 'A335B048C8456F75E0538101600A6A04', '张婉玉', '341323200107032042', '物联网院',
                 '2019级', '物联网', 'C物联网19_1', '常州校区10号楼', '515', '15158571775', '-1', '3','2')]
    sql = 'insert into data values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)'
    cur.executemany(sql, students)
con.commit()

# with con.cursor() as cur:
#     sql = 'select * from data'
#     a = cur.execute(sql)
#     for i in range(a):
#         print(cur.fetchone())
# con.commit()
