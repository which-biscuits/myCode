# _*_ coding:utf-8 _*_
"""
@ 功能 : 本模块用于对窗口界面的定义
@ author : 王子潇
@ create : 2020/7
"""
from tkinter import *
from tkinter import messagebox
from tkinter import ttk
import DataProcess as Dp
import time


class FirstFrame(Frame):
    """程序首界面，用于选择执行的系统"""
    global Student   # 存放学生信息的列表

    def __init__(self, master=None):
        super().__init__(master)  # super()代表了父类的定义
        self.master = master
        self.pack(anchor='n')  # 创建frame时将其放置在master父模块上
        '''建造时创建组件'''
        # 初始化组件的属性
        self.label_tit = Label(self, text='学生成绩系统', width=24, height=1, fg='black', font=('黑体', 25))
        self.btn_stu = Button(self, text='学生成绩查询系统', width=18, height=1, bg='gray', font=('黑体', 15))
        self.btn_adm = Button(self, text='学生成绩管理系统', width=18, height=1, bg='gray', font=('黑体', 15))
        self.btnQuit = Button(self, text='退出', width=18, height=1, font=('黑体', 15), command=Exit)
        # 组件的放置位置
        self.label_tit.grid(row=0, column=2, columnspan=3, pady=30)
        self.btn_stu.grid(row=1, column=0, columnspan=3, pady=30, padx=50)
        self.btn_adm.grid(row=1, column=4, columnspan=3, pady=30, padx=50)
        self.btnQuit.grid(row=2, column=2, columnspan=3, pady=30)
        # 组件和事件的绑定
        self.btn_stu.bind('<Button-1>', self.btn_stu_ev)
        self.btn_adm.bind('<Button-1>', self.btn_adm_ev)

    def btn_stu_ev(self, event):
        """学生成绩查询系统 的操作"""
        global root
        RegisterFrame(master=root, TypeChoice='Student')
        self.destroy()

    def btn_adm_ev(self, event):
        """学生成绩管理系统 的操作"""
        global root
        RegisterFrame(master=root, TypeChoice='Admin')
        self.destroy()


class RegisterFrame(Frame):
    """登录界面"""

    def __init__(self, master=None, TypeChoice=None):  # TypeChoice 用于判断学生查询界面或管理员登录界面
        super().__init__(master)  # super()代表了父类的定义
        self.master = master
        self.type = TypeChoice
        self.Save = 0
        self.pack(anchor='center')  # 创建时将Frame放置在master中
        if self.type == 'Student':
            self.type = '学生成绩查询系统登录界面'
        elif self.type == 'Admin':
            self.type = '学生成绩管理系统登录界面'
        else:
            self.type = '登陆界面函数参数错误'  # 通过判断 self.type 的值决定界面的标题抬头
        """创建组件"""
        # 初始化组件的属性
        self.label_tit = Label(self, text=self.type, width=24, height=1, fg='black', font=('黑体', 20))
        self.label_adm = Label(self, text='用户名(姓名):', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_pwd = Label(self, text='密  码(学号):', width=12, height=1, fg='black', font=('黑体', 15))
        self.btn_cfm = Button(self, text='确定', width=8, height=1, bg='gray', font=('黑体', 15))
        self.btn_cnc = Button(self, text='清空', width=8, height=1, bg='gray', font=('黑体', 15))
        self.btn_rtn = Button(self, text='返回', width=8, height=1, bg='gray', font=('黑体', 15))
        self.btnQuit = Button(self, text='退出系统', width=8, height=1, font=('黑体', 15), command=Exit)
        self.adm = StringVar()  # 将Entry框的内容与变量进行绑定
        self.pwd = StringVar()
        self.adm.set('admin')  # 设定Entry框显示的内容
        self.pwd.set('')
        self.entry_adm = Entry(self, textvariable=self.adm)
        self.entry_pwd = Entry(self, textvariable=self.pwd, show='*')
        # 设定组件在父Frame 中的位置
        self.label_tit.grid(row=0, column=0, columnspan=48, pady=30)
        self.label_adm.grid(row=1, column=0, pady=20, columnspan=12, sticky=W)
        self.label_pwd.grid(row=2, column=0, pady=20, columnspan=12, sticky=W)
        self.btn_cfm.grid(row=3, column=0, pady=20, padx=20, columnspan=8)
        self.btn_cnc.grid(row=3, column=10, pady=20, padx=20, columnspan=8)
        self.btn_rtn.grid(row=3, column=20, pady=20, padx=20, columnspan=8)
        self.btnQuit.grid(row=4, column=10, pady=20, columnspan=8)
        self.entry_adm.grid(row=1, column=20, columnspan=12, )
        self.entry_pwd.grid(row=2, column=20, columnspan=12, )
        # 设定组件绑定的事件
        self.btn_cfm.bind('<Button-1>', self.btn_cfm_ev)
        self.btn_cnc.bind('<Button-1>', self.btn_cnc_ev)
        self.btn_rtn.bind('<Button-1>', self.btn_rtn_ev)
        self.entry_adm.bind('<KeyPress-Return>', self.btn_cfm_ev)
        self.entry_pwd.bind('<KeyPress-Return>', self.btn_cfm_ev)

    def btn_cfm_ev(self, event):
        """确认按钮对应的事件"""
        global Admin, School, root, Empty, Student, adMenuFrame, showFrame, FindResult, findStudent
        if self.type == '学生成绩管理系统登录界面' and [self.adm.get(), self.pwd.get()] in Admin:  # 当在管理员登陆界面并且账号密码在管理员列表中
            if School.get('name') is None:  # 如果学校信息元组为空时，对学校信息进行初始化
                messagebox.showinfo(title='提示信息', message='请初始化学校信息！')
                self.destroy()
                StudentSet(master=root)
            else:  # 转入管理员界面
                self.destroy()
                Empty.destroy()
                adMenuFrame = AdMenuFrame(master=root)
                showFrame = ShowFrame(master=root)
        elif self.adm.get() == '':  # 账号Entry框为空
            messagebox.showinfo(title='提示信息', message='账号(姓名)不能为空！')
        elif self.type == '学生成绩查询系统登录界面' and self.pwd.get() == '':
            messagebox.showinfo(title='提示信息', message='密码(学号)不能为空！')
        elif self.type == '学生成绩查询系统登录界面' and self.pwd.get() != '':
            for i in range(1, len(Student)):  # 学生成绩查询系统界面 判断学生姓名和学号是否存在并对应同一个人
                if self.pwd.get() == Student[i][0] and self.adm.get() == Student[i][1]:
                    self.Save = i
            if self.Save != 0:
                FindResult = Student[self.Save]
                self.destroy()
                Empty.destroy()
                findStudent = FindStudent(master=root)
            else:
                messagebox.showinfo(title='提示信息', message='姓名或学号输入错误,不存在该学生信息！')
        else:
            messagebox.showinfo(title='提示信息', message='账号或密码输入错误！')

    def btn_cnc_ev(self, event):
        """清空按钮对应的事件"""
        self.adm.set('')
        self.pwd.set('')

    def btn_rtn_ev(self, event):
        """返回按钮对应的事件"""
        global root
        self.destroy()
        FirstFrame(master=root)


class StudentSet(Frame):
    """学校信息初始化界面"""

    def __init__(self, master=None):
        super().__init__(master)  # super()代表了父类的定义
        self.master = master
        self.pack(anchor='n')
        """创建组件"""
        # 组件内容的初始化
        self.label_tit = Label(self, text='学校信息初始化', width=24, height=1, fg='black', font=('黑体', 25))
        self.label_scn = Label(self, text='学  校名称:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_C_grade = Label(self, text='C 语言学分:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_M_grade = Label(self, text='数  学学分:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_E_grade = Label(self, text='英  语学分:', width=12, height=1, fg='black', font=('黑体', 15))

        self.btn_cfm = Button(self, text='确定', width=8, height=1, bg='gray', font=('黑体', 15))
        self.btn_rtn = Button(self, text='返回上一级', width=10, height=1, bg='gray', font=('黑体', 15))
        self.btn_cnc = Button(self, text='清空', width=8, height=1, bg='gray', font=('黑体', 15))
        self.btnQuit = Button(self, text='退出系统', width=8, height=1, font=('黑体', 15), command=Exit)

        self.v_Name = StringVar()
        self.v_C_grade = StringVar()
        self.v_M_grade = StringVar()
        self.v_E_grade = StringVar()
        self.entry_scn = Entry(self, textvariable=self.v_Name, width=24)
        self.entry_C_grade = Entry(self, textvariable=self.v_C_grade, width=24)
        self.entry_M_grade = Entry(self, textvariable=self.v_M_grade, width=24)
        self.entry_E_grade = Entry(self, textvariable=self.v_E_grade, width=24)
        # 组件的放置位置
        self.label_tit.grid(row=0, column=0, columnspan=24, pady=30)
        self.label_scn.grid(row=1, column=0, columnspan=12)
        self.label_C_grade.grid(row=2, column=0, columnspan=12)
        self.label_M_grade.grid(row=3, column=0, columnspan=12)
        self.label_E_grade.grid(row=4, column=0, columnspan=12)

        self.btn_cfm.grid(row=5, column=0, pady=20, padx=20, columnspan=8)
        self.btn_cnc.grid(row=5, column=10, pady=20, padx=20, columnspan=8)
        self.btn_rtn.grid(row=5, column=20, pady=20, padx=20, columnspan=8)
        self.btnQuit.grid(row=6, column=10, pady=20, columnspan=8)

        self.entry_scn.grid(row=1, column=15, pady=10, columnspan=24)
        self.entry_C_grade.grid(row=2, column=15, pady=10, columnspan=24)
        self.entry_M_grade.grid(row=3, column=15, pady=10, columnspan=24)
        self.entry_E_grade.grid(row=4, column=15, pady=10, columnspan=24)
        # 组件对应的事件
        self.btn_cfm.bind('<Button-1>', self.btn_cfm_ev)
        self.btn_cnc.bind('<Button-1>', self.btn_cnc_ev)
        self.btn_rtn.bind('<Button-1>', self.btn_rtn_ev)

    def btn_cfm_ev(self, event):
        """确定按钮"""
        global School, Empty, adMenuFrame, showFrame
        Name = self.v_Name.get()
        E_grade = self.v_E_grade.get()
        C_grade = self.v_C_grade.get()
        M_grade = self.v_M_grade.get()
        List = [Name, E_grade, C_grade, M_grade]
        try:
            if Name == '' or E_grade == '' or C_grade == '' or M_grade == '':
                messagebox.showinfo(title='提示信息', message='数据不能为空！')
            elif 0 < float(E_grade) < 10 and 0 < float(C_grade) < 10 and 0 < float(M_grade) < 10:
                School['name'] = Name
                School['E_grade'] = float(E_grade)
                School['C_grade'] = float(C_grade)
                School['M_grade'] = float(M_grade)
                Dp.Student.setting(School)
                messagebox.showinfo(title='提示信息', message='初始化学校信息成功！')
                self.destroy()
                Empty.destroy()
                adMenuFrame = AdMenuFrame(master=root)
                showFrame = ShowFrame(master=root)
            else:
                messagebox.showinfo(title='提示信息', message='学分必须在 0-10 之间!')
        except ValueError:
            messagebox.showinfo(title='提示信息', message='学分必须为数字!')

    def btn_cnc_ev(self, event):
        self.v_Name.set('')
        self.v_E_grade.set('')
        self.v_M_grade.set('')
        self.v_C_grade.set('')

    def btn_rtn_ev(self, event):
        global root
        self.destroy()
        FirstFrame(master=root)


class AdMenuFrame(Frame):
    """管理员操作界面，用于对学生信息的操作"""

    def __init__(self, master=None):
        super().__init__(master)  # super()代表了父类的定义
        self.master = master
        self.pack(anchor='n')
        """创建组件"""
        self.label_tit = Label(self, text='学生成绩管理系统', width=24, height=1,
                               fg='black', font=('黑体', 25))
        self.btn_add = Button(self, text='新增学生信息', width=12, height=1,
                              bg='gray', font=('黑体', 15))
        self.btn_del = Button(self, text='删除学生信息', width=12, height=1,
                              bg='gray', font=('黑体', 15))
        self.btn_adm = Button(self, text='登录账户管理', width=12, height=1,
                              bg='gray', font=('黑体', 15))
        self.btn_rtn = Button(self, text='返回主界面', width=12, height=1,
                              bg='gray', font=('黑体', 15))
        self.label_tit.grid(row=0, column=8, columnspan=24)
        self.btn_add.grid(row=1, column=0, pady=10, columnspan=12)
        self.btn_del.grid(row=1, column=12, pady=10, columnspan=12)
        self.btn_adm.grid(row=1, column=24, pady=10, columnspan=12)
        self.btn_rtn.grid(row=2, column=12, pady=10, columnspan=12)

        self.btn_add.bind('<Button-1>', self.btn_add_ev)
        self.btn_del.bind('<Button-1>', self.btn_del_ev)
        self.btn_adm.bind('<Button-1>', self.btn_adm_ev)
        self.btn_rtn.bind('<Button-1>', self.btn_rtn_ev)

    def btn_add_ev(self, event):
        global root, showFrame, Empty
        self.destroy()
        showFrame.destroy()
        Empty = Frame(root, height=100)
        Empty.pack()
        AddStudent(master=root)

    def btn_del_ev(self, event):
        global root, showFrame
        self.destroy()
        showFrame.destroy()
        DelStudent(master=root)

    def btn_adm_ev(self, event):
        global root, adminSetFrame, Empty
        self.destroy()
        showFrame.destroy()
        Empty = Frame(root, height=100)
        Empty.pack()
        adminSetFrame = AdminSetFrame(master=root)

    def btn_rtn_ev(self, event):
        global showFrame, Empty
        self.destroy()
        showFrame.destroy()
        Empty = Frame(root, height=100)
        Empty.pack()
        FirstFrame(master=root)


class ShowFrame(Frame):
    """将学生信息以表格形式显示"""

    def __init__(self, master=None):
        super().__init__(master)  # super()代表了父类的定义
        self.master = master
        self.place(relx=0.1, y=150, relwidth=0.8, height=330)
        """创建组件"""
        global Student, count
        count = len(Student)
        columns = Student[0]
        self.widths = [100, 60, 60, 60, 60, 60, 60, 60, 90]
        self.canvas = Canvas(self, bg='blue')  # 创建canvas
        self.canvas.place(relx=0, y=30, relwidth=1, relheight=1)
        self.treeview = ttk.Treeview(self.canvas, show="headings", columns=columns)  # 表格
        self.treeview.place(relx=0, rely=0, relwidth=0.97, relheight=1)
        self.VScroll1 = Scrollbar(self.canvas, orient='vertical', command=self.treeview.yview)
        self.VScroll1.place(relx=0.97, rely=0, relwidth=0.03, height=300)
        self.treeview.configure(yscrollcommand=self.VScroll1.set)  # 给treeview添加配置
        for i in range(len(self.widths)):
            self.treeview.column(columns[i], width=self.widths[i], anchor='center')  # 表示列,不显示
            self.treeview.heading(columns[i], text=columns[i])  # 显示表头

        for i in range(len(Student) - 1):  # 写入数据
            self.treeview.insert('', i, values=Student[i + 1])

        self.entry = 1
        self.treeview.bind('<Double-1>', self.set_cell_value)  # 双击左键进入编辑
        self.btn_new = ttk.Button(self, text='新建学生信息', width=20, command=self.NewRow)
        self.btn_new.place(x=50, y=0)

        for col in columns:  # 绑定函数，使表头可排序
            self.treeview.heading(col, text=col,
                                  command=lambda _col=col: self.treeview_sort_column(self.treeview, _col, False))

    def treeview_sort_column(self, treeview, col, reverse):  # 'Treeview' 、列名、排列方式
        sort = [(treeview.set(k, col), k) for k in treeview.get_children('')]
        sort.sort(reverse=reverse)  # 排序方式
        # rearrange items in sorted positions
        for index, (val, k) in enumerate(sort):  # 根据排序后索引移动
            treeview.move(k, '', index)
        treeview.heading(col, command=lambda: self.treeview_sort_column(treeview, col, not reverse))  # 重写标题，使之成为再点倒序的标题

    def set_cell_value(self, event):  # 双击进入编辑状态
        if self.entry == 0:
            self.entryedit.destroy()
            self.okb.destroy()
        for self.item in self.treeview.selection():
            # item = I001
            self.item_text = self.treeview.item(self.item, "values")
            self.items = self.item
            # print(self.item_text[0:])  # 输出所选行的值
        self.column = self.treeview.identify_column(event.x)  # 列
        self.row = self.treeview.identify_row(event.y)  # 行
        self.cn = int(str(self.column).replace('#', ''))
        self.rn = int(eval(str(self.row).replace('I', '0X').lower()))
        self.VScroll1Place = [round(list(self.VScroll1.get())[0], 2), round(list(self.VScroll1.get())[1], 2)]
        if self.cn <= 6:
            self.v_entryedit = StringVar()
            self.entryedit = Entry(self.treeview, textvariable=self.v_entryedit, width=12)
            self.okb = ttk.Button(self.treeview, text='OK', width=4)
            if self.rn < len(Student):
                self.v_entryedit.set(str(Student[self.rn][self.cn - 1]))
            else:
                self.v_entryedit.set(str(Student[0][self.cn - 1]))
            self.entry = x = 0
            for i in range(self.cn - 1):
                x += self.widths[i]
            if self.VScroll1Place[0] != 0:
                self.entryedit.place(x=x, y=self.rn * 20 + 5 - count * 20 * self.VScroll1Place[0])
                self.okb.place(x=x + 85, y=self.rn * 20 + 5 - count * 20 * self.VScroll1Place[0])
            else:
                self.entryedit.place(x=x, y=self.rn * 20 + 5)
                self.okb.place(x=x + 85, y=self.rn * 20 + 5)
            self.entryedit.bind('<KeyPress-Return>', self.save_edit)
            self.okb.bind('<Button-1>', self.save_edit)
        else:
            messagebox.showinfo(title='提示信息', message='该数据不能直接修改!')

    def save_edit(self, event):
        global Student, showFrame
        if self.rn < len(Student) and self.judge():
            Student[self.rn][self.cn - 1] = self.entryedit.get()
            if [i for i in range(3, 6) if Student[self.rn][i] != Student[0][i]] == [3, 4, 5]:
                Student[self.rn] = Dp.Student.append(Student[self.rn][0:6])
        elif self.rn >= len(Student) and self.judge():
            Student.append(list(Student[0]))
            Student[-1][self.cn - 1] = self.entryedit.get()
            if [i for i in range(3, 6) if Student[self.rn][i] != Student[0][i]] == [3, 4, 5]:
                Student[self.rn] = Dp.Student.append(Student[self.rn][0:6])
        self.destroy()
        showFrame = ShowFrame(master=root)
        self.entryedit.destroy()
        self.okb.destroy()

    def judge(self):
        try:
            if self.entryedit.get() == '':
                messagebox.showinfo(title='提示信息', message='数据不能为空！')
            elif self.cn == 1 and len(self.entryedit.get()) != 10:
                messagebox.showinfo(title='提示信息', message='学号必须为 10 位！')
            elif self.cn == 1 and self.entryedit.get() in [Student[i][0] for i in range(1, len(Student)) if
                                                           i != self.rn]:
                messagebox.showinfo(title='提示信息', message='该学号已存在！')
            elif self.cn == 2 and self.entryedit.get() in ['男', '女']:
                messagebox.showinfo(title='提示信息', message='性别必须为‘男’or‘女’！')
            elif 3 < self.cn < 7 and 0 < float(self.entryedit.get()) < 100:
                return True
            elif 3 < self.cn < 7:
                messagebox.showinfo(title='提示信息', message='成绩必须在 0-100 之间!')
            else:
                return True
        except ValueError:
            messagebox.showinfo(title='提示信息', message='成绩必须为数字!')

    def NewRow(self):
        global count, Student
        self.treeview.insert('', count, values=Student[0])
        count += 1
        self.treeview.update()
        self.btn_new.update()


class AddStudent(Frame):
    def __init__(self, master=None):
        super().__init__(master)  # super()代表了父类的定义
        self.master = master
        self.pack(anchor='n')
        """创建组件"""
        self.label_tit = Label(self, text='新增学生信息', width=12, height=1, fg='black', font=('黑体', 25))
        self.label_Num = Label(self, text='学      号:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_Name = Label(self, text='姓      名:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_Sex = Label(self, text='性      别:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_Cgrade = Label(self, text='C语言成绩:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_Mgrade = Label(self, text='数  学成绩:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_Egrade = Label(self, text='英  语成绩:', width=12, height=1, fg='black', font=('黑体', 15))

        self.btn_cfm = Button(self, text='确定', width=12, height=1, bg='gray', font=('黑体', 15))
        self.btn_del = Button(self, text='清空', width=12, height=1, bg='gray', font=('黑体', 15))
        self.btn_rtn = Button(self, text='返回上一级', width=12, height=1, bg='gray', font=('黑体', 15))

        self.v_num = StringVar()
        self.v_name = StringVar()
        self.v_Cgrade = StringVar()
        self.v_Mgrade = StringVar()
        self.v_Egrade = StringVar()
        self.entry_Num = Entry(self, textvariable=self.v_num)
        self.entry_Name = Entry(self, textvariable=self.v_name)
        self.sexvalue = StringVar()
        self.sexvalue.set('男')
        self.sexman = Radiobutton(self, text="男", value="男", variable=self.sexvalue)
        self.sexwoman = Radiobutton(self, text="女", value="女", variable=self.sexvalue)
        self.entry_Cgrade = Entry(self, textvariable=self.v_Cgrade)
        self.entry_Mgrade = Entry(self, textvariable=self.v_Mgrade)
        self.entry_Egrade = Entry(self, textvariable=self.v_Egrade)

        self.label_tit.grid(row=0, column=6, columnspan=24, pady=40)
        self.label_Num.grid(row=1, column=0, columnspan=12)
        self.label_Name.grid(row=2, column=0, columnspan=12)
        self.label_Sex.grid(row=3, column=0, columnspan=12)
        self.label_Cgrade.grid(row=4, column=0, columnspan=12)
        self.label_Mgrade.grid(row=5, column=0, columnspan=12)
        self.label_Egrade.grid(row=6, column=0, columnspan=12)
        self.entry_Num.grid(row=1, column=18, columnspan=18)
        self.entry_Name.grid(row=2, column=18, columnspan=18)
        self.sexman.grid(row=3, column=18, columnspan=9)
        self.sexwoman.grid(row=3, column=27, columnspan=9)
        self.entry_Cgrade.grid(row=4, column=18, columnspan=18)
        self.entry_Mgrade.grid(row=5, column=18, columnspan=18)
        self.entry_Egrade.grid(row=6, column=18, columnspan=18)
        self.btn_cfm.grid(row=7, column=0, pady=20, padx=5, columnspan=12)
        self.btn_del.grid(row=7, column=12, pady=20, padx=5, columnspan=12)
        self.btn_rtn.grid(row=7, column=24, pady=20, padx=5, columnspan=12)

        self.btn_cfm.bind('<Button-1>', self.btn_cfm_ev)
        self.btn_del.bind('<Button-1>', self.btn_del_ev)
        self.btn_rtn.bind('<Button-1>', self.btn_rtn_ev)

    def btn_cfm_ev(self, event):
        global Student
        AppendSet = list(
            [self.v_num.get(), self.v_name.get(), self.sexvalue.get(), self.v_Cgrade.get(), self.v_Mgrade.get(),
             self.v_Egrade.get()])
        try:
            if [i for i in range(6) if AppendSet[i] != ''] != [0, 1, 2, 3, 4, 5]:
                messagebox.showinfo(title='提示信息', message='数据不能为空！')
            elif len(AppendSet[0]) != 10:
                messagebox.showinfo(title='提示信息', message='学号必须为 10 位！')
            elif AppendSet[0] in [Student[i][0] for i in range(1, len(Student))]:
                messagebox.showinfo(title='提示信息', message='该学号已存在！')
            elif int(AppendSet[0]) > 0 and 0 < float(AppendSet[3]) < 100 and 0 < float(
                    AppendSet[4]) < 100 and 0 < float(AppendSet[5]) < 100:
                Student.append(Dp.Student.append(AppendSet))
                self.v_num.set('')
                self.v_name.set('')
                self.sexvalue.set('男')
                self.v_Cgrade.set('')
                self.v_Mgrade.set('')
                self.v_Egrade.set('')
            else:
                messagebox.showinfo(title='提示信息', message='成绩必须在 0-100 之间!')
        except BaseException:
            messagebox.showinfo(title='提示信息', message='学号和成绩必须为数字!')

    def btn_del_ev(self, event):
        self.v_num.set('')
        self.v_name.set('')
        self.sexvalue.set('男')
        self.v_Cgrade.set('')
        self.v_Mgrade.set('')
        self.v_Egrade.set('')

    def btn_rtn_ev(self, event):
        global Empty, adMenuFrame, showFrame
        self.destroy()
        Empty.destroy()
        adMenuFrame = AdMenuFrame(master=root)
        showFrame = ShowFrame(master=root)


class DelStudent(Frame):
    """用于删除学生信息"""

    def __init__(self, master=None):
        super().__init__(master)  # super()代表了父类的定义
        self.master = master
        self.place(relx=0.1, relwidth=0.8, relheight=1)
        """创建组件"""
        self.label_tit = Label(self, text='删除学生信息', width=12, height=1, fg='black', font=('黑体', 25))
        self.label_Num = Label(self, text='学      号:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_Name = Label(self, text='姓      名:', width=12, height=1, fg='black', font=('黑体', 15))
        self.label_Rmd = Label(self, text='支持模糊搜索,双击需要删除的行并确定', width=40, height=1, fg='black', font=('黑体', 10))

        self.btn_cfm = Button(self, text='确定', width=12, height=1, bg='gray', font=('黑体', 15))
        self.btn_del = Button(self, text='清空', width=12, height=1, bg='gray', font=('黑体', 15))
        self.btn_rtn = Button(self, text='返回上一级', width=12, height=1, bg='gray', font=('黑体', 15))

        self.v_num = StringVar()
        self.v_name = StringVar()
        self.entry_Num = Entry(self, textvariable=self.v_num)
        self.entry_Name = Entry(self, textvariable=self.v_name)

        self.label_tit.grid(row=0, column=18, columnspan=24, pady=40)
        self.label_Num.grid(row=1, column=12, columnspan=12)
        self.label_Name.grid(row=2, column=12, columnspan=12)
        self.label_Rmd.grid(row=8, column=12, columnspan=40)

        self.entry_Num.grid(row=1, column=30, columnspan=18)
        self.entry_Name.grid(row=2, column=30, columnspan=18)

        self.btn_cfm.grid(row=7, column=12, pady=20, padx=5, columnspan=12)
        self.btn_del.grid(row=7, column=24, pady=20, padx=5, columnspan=12)
        self.btn_rtn.grid(row=7, column=36, pady=20, padx=5, columnspan=12)

        self.btn_cfm.bind('<Button-1>', self.btn_cfm_ev)
        self.btn_del.bind('<Button-1>', self.btn_del_ev)
        self.btn_rtn.bind('<Button-1>', self.btn_rtn_ev)

    def FindShowFrame(self):
        """用于显示查找到的学生信息"""
        global Student
        columns = Student[0]
        widths = [100, 60, 60, 60, 60, 60, 60, 60, 90]
        self.canvas = Canvas(self)  # 创建canvas
        self.canvas.place(relx=0, y=280, relwidth=1, height=250)
        self.treeview = ttk.Treeview(self.canvas, show="headings", columns=columns)  # 表格
        self.treeview.place(relx=0, rely=0, relwidth=0.97, relheight=1)
        self.treeview.bind('<Double-1>', self.del_value)  # 双击左键进入删除确定
        self.VScroll1 = Scrollbar(self.canvas, orient='vertical', command=self.treeview.yview)
        self.VScroll1.place(relx=0.97, rely=0, relwidth=0.03, relheight=1)
        for i in range(len(widths)):
            self.treeview.column(columns[i], width=widths[i], anchor='center')  # 表示列,不显示
            self.treeview.heading(columns[i], text=columns[i])  # 显示表头

        for i in range(len(self.Find)):  # 写入数据
            self.treeview.insert('', i, values=self.Find[i])

    def btn_cfm_ev(self, event):
        global Student
        self.Find = []
        num = name = Save = True
        for i in range(1, len(Student)):
            if self.v_num.get() != '' and self.v_num.get() in Student[i][0]:
                num = Save = False
            if self.v_name.get() != '' and self.v_name.get() in Student[i][1]:
                name = Save = False
            if not Save:
                self.Find.append(Student[i])
                Save = True
        self.FindShowFrame()
        if len(self.Find) > 1:
            messagebox.showinfo(title='提示信息', message='找到多个学生信息！')
        elif self.v_num.get() == self.v_name.get() == '':
            messagebox.showinfo(title='提示信息', message='请输入学生信息！')
        if self.v_num.get() != '' and num:
            messagebox.showinfo(title='提示信息', message='按“学号”不存在该生信息！')
        elif self.v_name.get() != '' and name:
            messagebox.showinfo(title='提示信息', message='按“姓名”不存在该生信息！')

    def btn_del_ev(self, event):
        self.v_num.set('')
        self.v_name.set('')

    def btn_rtn_ev(self, event):
        global Empty, adMenuFrame, showFrame
        self.destroy()
        adMenuFrame = AdMenuFrame(master=root)
        showFrame = ShowFrame(master=root)

    def del_value(self, event):  # 双击进行删除
        global Student
        for self.item in self.treeview.selection():
            # item = I001
            self.item_text = self.treeview.item(self.item, "values")
            self.items = self.item
        a = '是否删除学号为:{0},姓名为{1}的学生？'.format(self.item_text[0], self.item_text[1])
        res = messagebox.askokcancel(title='提示消息', message=a)
        if res == True:
            for i in range(1, len(Student)):
                if list(self.item_text[0:]) == Student[i]:
                    del Student[i]
            for i in range(len(self.Find)):
                if list(self.item_text[0:]) == self.Find[i]:
                    del self.Find[i]
                    self.canvas.destroy()
                    self.FindShowFrame()


class FindStudent(Frame):
    """用于显示学生查询系统的查询结果"""

    def __init__(self, master=None):
        super().__init__(master)  # super()代表了父类的定义
        self.master = master
        self.place(relx=0.1, relwidth=0.8, relheight=1)
        '''创建组件'''
        global Student, FindResult
        columns = Student[0]
        widths = [100, 60, 60, 60, 60, 60, 60, 60, 90]
        self.label_tit = Label(self, text='查询结果', width=12, height=1, fg='black', font=('黑体', 25))

        self.btn_rtn = Button(self, text='返回上一级', width=12, height=1, bg='gray', font=('黑体', 15))
        self.btnQuit = Button(self, text='退出系统', width=12, height=1, font=('黑体', 15), command=Exit)

        self.canvas = Canvas(self)  # 创建canvas
        self.treeview = ttk.Treeview(self.canvas, show="headings", columns=columns)  # 表格
        for i in range(len(widths)):
            self.treeview.column(columns[i], width=widths[i], anchor='center')  # 表示列,不显示
            self.treeview.heading(columns[i], text=columns[i])  # 显示表头
        self.treeview.insert('', 1, values=FindResult)
        self.treeview.place(relx=0, rely=0, relwidth=1, relheight=1)
        self.canvas.place(relx=0, rely=0.2, relwidth=1, height=200)
        self.label_tit.place(relx=0.25, rely=0.1, relwidth=0.5, height=40)
        self.btnQuit.place(relx=0.2, rely=0.6, width=150, height=40)
        self.btn_rtn.place(relx=0.55, rely=0.6, width=150, height=40)

        self.btn_rtn.bind('<Button-1>', self.btn_rtn_ev)

    def btn_rtn_ev(self, event):
        global Empty, registerFrame
        self.destroy()
        Empty = Frame(root, height=100)
        Empty.pack()
        registerFrame = RegisterFrame(master=root, TypeChoice='Student')


class AdminSetFrame(Frame):
    """学校信息初始化界面"""

    def __init__(self, master=None):
        super().__init__(master)  # super()代表了父类的定义
        self.master = master
        self.pack(anchor='n')
        """创建组件"""
        self.label_tit = Label(self, text='管理员账户设置', width=24, height=1,
                               fg='black', font=('黑体', 25))
        self.label_adm = Label(self, text='请输入账号:      ', width=18, height=1,
                               fg='black', font=('黑体', 15))
        self.label_rmd = Label(self, text='(可新增账号或修改已有账号密码)', width=18, height=1,
                               fg='black', font=('黑体', 8))
        self.label_pwd = Label(self, text='请输入密码:      ', width=18, height=1,
                               fg='black', font=('黑体', 15))
        self.label_pwdagn = Label(self, text='再次输入密码密码:', width=18, height=1,
                                  fg='black', font=('黑体', 15))

        self.btn_cfm = Button(self, text='确定', width=8, height=1, bg='gray', font=('黑体', 15))
        self.btn_rtn = Button(self, text='返回上一级', width=10, height=1, bg='gray', font=('黑体', 15))
        self.btn_cnc = Button(self, text='清空', width=8, height=1, bg='gray', font=('黑体', 15))
        self.btnQuit = Button(self, text='退出系统', width=8, height=1, font=('黑体', 15), command=Exit)

        self.v_adm = StringVar()
        self.v_pwd = StringVar()
        self.v_pwdagn = StringVar()
        self.entry_adm = Entry(self, textvariable=self.v_adm, width=24)
        self.entry_pwd = Entry(self, textvariable=self.v_pwd, width=24)
        self.entry_pwdagn = Entry(self, textvariable=self.v_pwdagn, width=24)

        self.label_tit.grid(row=0, column=0, columnspan=24, pady=30)
        self.label_adm.grid(row=1, column=0, columnspan=12)
        self.label_rmd.place(x=110, rely=0.2, width=200, height=20)
        self.label_pwd.grid(row=2, column=0, columnspan=12)
        self.label_pwdagn.grid(row=3, column=0, columnspan=12)

        self.btn_cfm.grid(row=5, column=0, pady=20, padx=20, columnspan=8)
        self.btn_cnc.grid(row=5, column=10, pady=20, padx=20, columnspan=8)
        self.btn_rtn.grid(row=5, column=20, pady=20, padx=20, columnspan=8)
        self.btnQuit.grid(row=6, column=10, pady=20, columnspan=8)

        self.entry_adm.grid(row=1, column=15, pady=10, columnspan=24)
        self.entry_pwd.grid(row=2, column=15, pady=10, columnspan=24)
        self.entry_pwdagn.grid(row=3, column=15, pady=10, columnspan=24)

        self.btn_cfm.bind('<Button-1>', self.btn_cfm_ev)
        self.btn_cnc.bind('<Button-1>', self.btn_cnc_ev)
        self.btn_rtn.bind('<Button-1>', self.btn_rtn_ev)

    def btn_cfm_ev(self, event):
        global Admin
        adm = pwd = Save = False
        if self.v_adm.get() == '':
            messagebox.showinfo(title='提示信息', message='请输入账号！')
        else:
            adm = True
        if self.v_pwd.get() != self.v_pwdagn.get():
            messagebox.showinfo(title='提示信息', message='两次输入密码不一致！')
        else:
            pwd = True
        if adm and pwd:
            for x in range(len(Admin)):
                if self.v_adm.get() == Admin[x][0]:
                    Save = x
            if Save == 0 and messagebox.askokcancel(title='提示消息', message='该账号已存在，是否修改密码？'):
                Admin.append(['admin', self.v_pwd.get()])
            elif not Save and messagebox.askokcancel(title='提示消息', message='是否新建账号？'):
                Admin.append([self.v_adm.get(), self.v_pwd.get()])
            elif messagebox.askokcancel(title='提示消息', message='该账号已存在，是否修改密码？'):
                Admin[Save] = [self.v_adm.get(), self.v_pwd.get()]

    def btn_cnc_ev(self, event):
        self.v_adm.set('')
        self.v_pwd.set('')
        self.v_pwdagn.set('')

    def btn_rtn_ev(self, event):
        global root, Empty, adMenuFrame, showFrame
        self.destroy()
        Empty.destroy()
        adMenuFrame = AdMenuFrame(master=root)
        showFrame = ShowFrame(master=root)


def Windows(width, height, app='FirstFrame(master=root)', empty=True):
    global root, Student, Empty, School
    x = root.winfo_screenwidth()
    y = root.winfo_screenheight()
    align_str = '%dx%d+%d+%d' % (width, height, (x - width) / 2, (y - height) / 2)
    root.geometry(align_str)
    if Dp.Student.School is None:
        root.title('学生成绩系统')
    else:
        root.title('学生成绩系统 BY {0}'.format(Dp.Student.School))
    if empty:
        Empty = Frame(root, height=100)
        Empty.pack()
    eval(app)
    get_time()
    root.protocol('WM_DELETE_WINDOW', Exit)
    root.mainloop()


def Save():
    if Dp.FileProcess('Save', Students=Student) and Dp.AdScProcess('Save', Admins=Admin, Schools=School):
        messagebox.showinfo(title='提示消息', message='数据保存成功,欢迎下次使用！')


def Exit():
    # True or 'False'
    res = messagebox.askokcancel(title='提示消息', message='是否退出系统？')
    if res:
        Save()
        root.destroy()


def get_time():  # 屏幕刷新时间
    global root, TimeNow
    time_str = time.strftime("%H:%M:%S", time.localtime())  # 获得系统现在时间
    TimeNow = Label(root, text=time_str, width=12, height=1, fg='black', font=('黑体', 25))
    TimeNow.place(relx=0.7, rely=0.9)
    time_str = time.strftime("%H:%M:%S", time.localtime())  # 获得系统现在时间
    TimeNow.configure(text=time_str)  # 重新设置文本标签
    root.after(1000, get_time)


if __name__ == "__main__":
    '''当在该界面运行时的操作'''
    root = Tk()
    Student = Dp.FileProcess('Read')
    Admin, School = Dp.AdScProcess('Read')
    Windows(800, 600)
