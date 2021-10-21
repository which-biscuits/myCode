# -*- coding:UTF-8 -*-
import requests
import time
import pymysql
import smtplib
import __future__
from email.mime.text import MIMEText
from email.utils import formataddr


def mail(message):
    ret = True
    my_sender = '1141207643@qq.com'  # 发件人邮箱账号
    my_pass = 'ovpxewboeatugeea'  # 发件人邮箱授权码
    my_user = '3052806406@qq.com'  # 收件人邮箱账号
    try:
        msg = MIMEText(message, 'plain', 'utf-8')  # 填写邮件内容
        msg['From'] = formataddr(("王子潇", my_sender))  # 括号里的对应发件人邮箱昵称、发件人邮箱账号
        msg['To'] = formataddr(("王子潇", my_user))  # 括号里的对应收件人邮箱昵称、收件人邮箱账号
        msg['Subject'] = "健康打卡结果通知"  # 邮件的主题，也可以说是标题

        server = smtplib.SMTP_SSL("smtp.qq.com", 465)  # 发件人邮箱中的SMTP服务器，端口是25
        server.login(my_sender, my_pass)  # 括号中对应的是发件人邮箱账号、邮箱密码
        server.sendmail(my_sender, [my_user, ], msg.as_string())  # 括号中对应的是发件人邮箱账号、收件人邮箱账号、发送邮件
        server.quit()  # 关闭连接
    except Exception:  # 如果 try 中的语句没有执行，则会执行下面的 ret=False
        ret = False
    if ret:
        print("邮件发送成功")
    else:
        print("邮件发送失败")


class my_hhu:

    def __init__(self, student, message):
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4209.2 Safari/537.36'
        }
        self.message = message
        self.student = student
        self.session = requests.Session()
        self.account = self.student[0]  # 学号
        self.password = self.student[1]  # 信息门户密码
        self.wid = self.student[2]  # 个人wid
        self.home_url = "http://my.hhu.edu.cn/index.portal"
        self.login_url = "http://ids.hhu.edu.cn/amserver/UI/Login"
        self.daka_url = "http://form.hhu.edu.cn/pdc/form/list"
        self.daka_list_url = "http://form.hhu.edu.cn/pdc/formDesignApi/S/gUTwwojq"
        self.daka_save_url = "http://form.hhu.edu.cn/pdc/formDesignApi/dataFormSave?wid=" + self.wid + "&userId=" + self.account
        self.init_form_api = "http://form.hhu.edu.cn/pdc/formDesignApi/initFormAppInfo"

    def login(self):
        post_data = {
            "Login.Token1": self.account,
            'Login.Token2': self.password,
            'goto': "http://my.hhu.edu.cn/loginSuccess.portal",
            'gotoOnFail': "http://my.hhu.edu.cn/loginFailure.portal",
        }
        loginResponse = self.session.post(self.login_url, data=post_data, headers=self.headers)
        if "handleLoginSuccessed()" in loginResponse.text:
            print("登录成功！"),
            return self.message
        else:
            print("{} 登录失败，请检查账号密码是否填写正确！".format(student[3]))
            return self.message + "\n{0} 登录失败，请检查账号密码是否填写正确！".format(student[3])

    def daka(self):
        curr_date = time.strftime('%Y/%m/%d', time.localtime(time.time()))
        self.session.get(self.daka_url)
        self.session.get(self.daka_list_url)
        post_data = {
            'DATETIME_CYCLE': curr_date,
            'XGH_336526': self.account,
            ### 此部分必须和健康打卡系统完全相同
            'XM_1474': self.student[3],  # 姓名
            'SFZJH_859173': self.student[4],  # 身份证号
            'SELECT_941320': self.student[5],  # 学院
            'SELECT_459666': self.student[6],  # 年级
            'SELECT_814855': self.student[7],  # 专业
            'SELECT_525884': self.student[8],  # 班级
            'SELECT_125597': self.student[9],  # 宿舍楼
            'TEXT_950231': self.student[10],  # 宿舍号
            'TEXT_937296': self.student[11],  # 手机号码
            ### 以下全部安按照健康算，不用修改
            'RADIO_853789': '否',
            'RADIO_43840': '否',
            'RADIO_579935': '健康',
            'RADIO_138407': '否',
            'RADIO_546905': '否',
            'RADIO_314799': '否',
            'RADIO_209256': '否',
            'RADIO_836972': '否',
            'RADIO_302717': '否',
            'RADIO_701131': '否',
            'RADIO_438985': '否',
            'RADIO_467360': '是',
            ### 这个也要完全相同
            'PICKER_956186': '江苏省,常州市,新北区',  # 住址 如：江苏省,南京市,江宁区
            'TEXT_434598': "",
            'TEXT_515297': "",
            'TEXT_752063': "",
        }
        self.session.post(self.init_form_api, data={'selfFormWid': self.wid}, headers=self.headers)
        dakaResponse = self.session.post(self.daka_save_url, data=post_data, headers=self.headers)
        if '{"result":true}' in dakaResponse.text:
            Time = time.strftime("%H:%M:%S")
            print("打卡成功！{0} {1}".format(student[0], Time))
            sql = "update data set YES=(%s) where id=(%s) "
            if student[-1] != '-1':
                with con.cursor() as cur:
                    cur.execute(sql, [time.strftime("%d"), student[0]])
                    con.commit()
            return self.message + "\n打卡成功！{0} {1} ".format(student[0], Time)
        else:
            time.sleep(0.5)
            print(" {0} 打卡失败！".format(self.account))
            return self.message + " \n{0} 打卡失败！".format(self.account)


if __name__ == '__main__':
    message = str()
    con = pymysql.connect(host='106.14.245.132', user='root', password='wangzixiao621.', database='students', port=3306,
                          charset='utf8')
    with con.cursor() as cur:
        b = cur.execute('select * from data')
        for i in range(b):
            student = list(cur.fetchone())
            if student[-3] == '-2':
                message = message + "\n打卡已停止 {0}".format(student[0])
            elif student[-1] != time.strftime("%d"):
                user = my_hhu(student, message)
                user.login()
                message = str(user.daka())
            else:
                print("已打卡 {0} ".format(student[0]))
                message = message + "\n已打卡 {0}".format(student[0])
    mail(str(message))
