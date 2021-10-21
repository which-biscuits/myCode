# _*_ coding:utf-8 _*_
"""
@ 功能: 本模块用于储存学生成绩系统有关的函数
@ author : 王子潇
@ create : 2020/7
"""

import csv
import os
import pickle


class Student:
    """定义一个学生信息类，用于存储学生信息"""
    School = GPA_c_grade = GPA_m_grade = GPA_e_grade = None  # 类属性，用于存储学校的姓名及学分设置

    def __init__(self, num, name, sex, c_grade, m_grade, e_grade, total, ave, GPA):
        self.num = num  # 学号
        self.name = name  # 姓名
        self.sex = sex  # 性别
        self.c_grade = c_grade  # C语言成绩
        self.m_grade = m_grade  # 数学成绩
        self.e_grade = e_grade  # 英语成绩
        self.total = total  # 总分
        self.ave = ave  # 平均分
        self.GPA = GPA  # 平均绩点

    @classmethod  # 修饰器  方法==>属性
    def setting(cls, School):
        """通过元组 ’School‘ 对 ’Student‘ 类的类属性的初始化"""
        cls.School = School.get('name')
        cls.GPA_c_grade = float(School.get('C_grade'))
        cls.GPA_m_grade = float(School.get('M_grade'))
        cls.GPA_e_grade = float(School.get('E_grade'))

    @classmethod
    def append(cls, AppendSet):
        """通过一个包含六个数据的列表 ’AppendSet‘ 新增一个Students实例对象的方法,返回值为一个Students实例对象"""
        num = AppendSet[0]
        name = AppendSet[1]
        sex = AppendSet[2]
        c_grade = round(float(AppendSet[3]), 2)  # 使 c_grade 中的值保留两位小数
        m_grade = round(float(AppendSet[4]), 2)
        e_grade = round(float(AppendSet[5]), 2)
        total = c_grade + m_grade + e_grade
        ave = total / 3
        ave = round(ave, 2)
        a = G_grade(c_grade) * Student.GPA_c_grade + G_grade(m_grade) * Student.GPA_m_grade + G_grade(
            e_grade) * Student.GPA_e_grade
        b = Student.GPA_c_grade + Student.GPA_m_grade + Student.GPA_e_grade
        GPA = round(a / b, 2)  # 使GPA中的值保留两位小数
        a = Student(num, name, sex, c_grade, m_grade, e_grade, total, ave, GPA)
        return list([a.num, a.name, a.sex, a.c_grade, a.m_grade, a.e_grade, a.total, a.ave, a.GPA])


def G_grade(grade):
    """用于将百分制成绩转换为5分制GPA"""
    if grade >= 90.0:
        return 5
    elif grade >= 85:
        return 4.5
    elif grade >= 80:
        return 4.0
    elif grade >= 75:
        return 3.5
    elif grade >= 70:
        return 3.0
    elif grade >= 65:
        return 2.5
    elif grade >= 60:
        return 2.0
    else:
        return 0


def FileProcess(Kind, Students=None):
    """用于读取或保存学生信息的csv文件 kind取值为 Read or Save"""
    address = os.getcwd()  # 获取当前的工作目录
    if not os.path.exists('学生成绩系统数据'):
        os.mkdir('学生成绩系统数据')  # 如果工作目录不存在’学生成绩系统数据‘子目录，则创建目录
    address = os.path.join(address, '学生成绩系统数据')
    if Kind == 'Read':
        try:
            with open('{0}/Student.csv'.format(address), 'r'):
                pass  # 尝试以’r‘类型打开文件，报错则说明文件不存在，则创建文件并进行初始化
        except FileNotFoundError:
            with open('{0}/Student.csv'.format(address), 'w+') as f:
                Stu_csv = csv.writer(f)  # 将文件的标题写入文件中
                Stu_csv.writerow(["学号", "姓名", "性别", "C语言", "数学", "英语", "总分", "平均分", "加权平均绩点"])
        finally:
            with open('{0}/Student.csv'.format(address), 'r') as f:
                Students = list(csv.reader(f))  # 将文件中的信息读取到一个二维列表中，一个元素为一个学生的信息
                StudentNew = []
                for i in range(len(Students)):
                    if Students[i]:
                        StudentNew.append(Students[i])  # 删除所读取文件元素中的空元素，并返回一个新列表
                return StudentNew
    elif Kind == 'Save':
        with open('{0}/Student.csv'.format(address), 'w+') as f:
            Stu_csv = csv.writer(f)  # 在进行保存时，已进行过’Read‘操作，无需判断文件是否存在
            Stu_csv.writerows(Students)
            return True
    else:
        print('函数调用传参错误！')  # 当调用函数时未进行’kind‘的赋值或赋值错误则在控制台打印


def AdScProcess(Kind, Admins=None, Schools=None):
    """用于读取或保存系统账号以及学校信息"""
    address = os.getcwd()  # 获取当前工作目录
    if not os.path.exists('学生成绩系统数据'):  # 判断工作目录下是否存在子目录，无则创建
        os.mkdir('学生成绩系统数据')
    address = os.path.join(address, '学生成绩系统数据')
    if Kind == 'Read':
        try:
            with open('{0}/Admin.bat'.format(address), 'rb+'):  # 若文件不存在则创建新文件并存入基本信息
                pass
        except FileNotFoundError:
            with open('{0}/Admin.bat'.format(address), 'wb+') as f:
                Admins = [['admin', '']]
                Schools = {'name': None, 'C_grade': None, 'M_grade': None, 'E_grade': None}
                pickle.dump(Admins, f)
                pickle.dump(Schools, f)  # 将数据依次存入新文件
        finally:
            with open('{0}/Admin.bat'.format(address), 'rb+') as f:
                Admins = pickle.load(f)  # 从文件中以此取出数据并存入内存(取出顺序必须与存入顺序相同)
                Schools = pickle.load(f)
                if Schools.get('name') is not None:
                    Student.setting(Schools)  # 当元组’Schools‘的值不为空时，对’Student‘类进行类属性的初始化
                return Admins, Schools
    elif Kind == 'Save':
        with open('{0}/Admin.bat'.format(address), 'wb+') as f:
            pickle.dump(Admins, f)
            pickle.dump(Schools, f)  # 将数据依次存入文件
            return True
    else:
        print('函数调用传参错误！')  # 当kind的值不为Read or Save 时，在控制台报错
