# -*- coding:UTF-8 -*-
import requests
import time
import pymysql


class my_hhu:

    def __init__(self, student):
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4209.2 Safari/537.36'
        }
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
            print("登录成功！",end='')
        else:
            print("{} 登录失败，请检查账号密码是否填写正确！".format(student[3]))

    def daka(self):
        while True:
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
                'RADIO_138407': '是',
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
                print("打卡成功！{0} {1}".format(student[3],Time))
                break
            else:
                time.sleep(0.5)
                print(" {0} 打卡失败！".format(self.account))


if __name__ == '__main__':
    student = ['1961310319', 'wangzixiao621.', 'A335B048C8456F75E0538101600A6A04', '王子潇', '410224200106210036',
               '物联网院', '2019级', '计算机', '计算机19_1', '常州校区辅楼', '207', '18317875002', '-1', None,'10']
    user = my_hhu(student)
    user.login()
    user.daka()