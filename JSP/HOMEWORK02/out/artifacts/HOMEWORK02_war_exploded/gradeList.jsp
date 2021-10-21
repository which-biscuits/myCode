<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/15
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="csv.*" %>
<%!
    static class dataPro {
        static List<String[]> data;
        static String path;
        /*设置读取路径*/
        synchronized public static void setPath(String path) {
            dataPro.path = path;
            CSVHelper csvHelper = new CSVHelper();
            data = csvHelper.readCSV(dataPro.path,true);
        }
        /*将 data 写入CSV */
        synchronized public static void writeData() {
            CSVHelper csvHelper = new CSVHelper();
            csvHelper.writeCSV(dataPro.path,data);
        }
        /*读取 data 对象*/
        public static List<String[]> getData() {
            return dataPro.data;
        }

        /*在data中新增行*/
        synchronized public static void addRow(String[] row) {
            int index = find(row);
            if (index == -1){
                data.add(row);
            } else {
                data.set(index,row);
            }
            dataPro.writeData();
        }
        /*在data中删除行*/
        synchronized public static void removeRow(String[] row) {
            int index = find(row);
            if (index != -1)
                data.remove(index);
            dataPro.writeData();
        }
        /*查找行*/
        public static int find(String[] row){
            int index = -1;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i)[2].equals(row[2]) && data.get(i)[5].equals(row[5])){
                    index = i;
                    break;
                }
            }
            return index;
        }
    }
%>

<%
    dataPro.setPath(application.getRealPath("/WEB-INF/users.csv"));

    List<String[]> data;
    String status = request.getParameter("Status");
    String major = request.getParameter("major");
    String grade = request.getParameter("grade");
    String stu_number = request.getParameter("stu_number");
    String stu_name = request.getParameter("stu_name");
    String sex = request.getParameter("sex");
    String class_name = request.getParameter("class_name");
    String score = request.getParameter("score");

    String[] row = {major,grade,stu_number,stu_name,sex,class_name,score};

    if (status == null){
        session.setAttribute("add",session.getAttribute("add")==null?0:session.getAttribute("add"));
        session.setAttribute("modify",session.getAttribute("modify")==null?0:session.getAttribute("modify"));
        session.setAttribute("remove",session.getAttribute("remove")==null?0:session.getAttribute("remove"));
    }
    else if (status.equals("add")){
        dataPro.addRow(row);
        int add = (int) session.getAttribute("add");
        session.setAttribute("add",add + 1);
    } else if (status.equals("remove")){
        dataPro.removeRow(row);
        int remove = (int) session.getAttribute("remove");
        session.setAttribute("remove", remove + 1);
    } else if (status.equals("modify")){
        dataPro.removeRow(row);
        dataPro.addRow(row);
        int modify = (int) session.getAttribute("modify");
        session.setAttribute("modify", modify + 1);
    }
    data = dataPro.getData();
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>学生成绩管理</title>
    <style>
        .input_divs{    /* 输入选择框的 div样式 */
            display: inline-block;     /* 转行内标签 */
            margin-top: 5px;
            width: 400px;
            text-align: left;
        }
        .input{     /* 文本输入框的大小 */
            width: 385px;
            height: 35px;
        }
        .name{      /* 输入框名称的 div 样式 */
            display: inline-block;
            text-align: right;
            width: 100px;
        }
        table.title{    /* 信息表格的样式 */
            margin-top: 10px;
            text-align: center;
            width: 820px;
            border-color: lightgray;
        }
        .buttons{   /* 按钮样式 */
            color: white;
            background-color: #121299 ;
            border-width: 0;
            width: 120px;
            height: 35px;
        }
        div.divs{   /* 按钮 div 样式 */
            margin-top: 10px;
            text-align: center;
        }
        .titles{    /* 标题样式 */
            font-size: 20px;

        }
        tr.tr{      /* 表格 行样式 */
            height: 35px;
        }
        .error{     /* 显示输入错误信息的div样式 */
            width: 150px;
            display: inline-block;
        }
        div.remove,div.modify{   /* 表格 修改/删除 操作按钮样式 */
            color: deepskyblue;
            text-decoration: underline;
            display: inline-block;
        }
    </style>
</head>
<body>
 <!--  学生信息新增录入部分  -->
    <div class="divs" style="margin-top: 5px;margin-bottom: 5px; font-size: 20px">
        <div style="display: inline-block"><b>新增: <b style="color: red;display:inline-block;"><%= session.getAttribute("add")==null?0:session.getAttribute("add") %></b> 条信息</b></div>
        &emsp;
        <div style="display: inline-block"><b>修改: <b style="color: red;display:inline-block;"><%= session.getAttribute("modify")==null?0:session.getAttribute("modify") %></b> 条信息</b></div>
        &emsp;
        <div style="display: inline-block"><b>删除: <b style="color: red;display:inline-block;"><%= session.getAttribute("remove")==null?0:session.getAttribute("remove") %></b> 条信息</b></div>
    </div>
    <!--  存储学生信息的表单  -->
    <table class="title" id="table" border="1" cellspacing="0" align="center">
        <th colspan="8" class="titles">学生成绩列表</th>
        <!-- 输出表头 -->
        <tr class="titles">
        <%
            for (String value : data.get(0)){
                out.println("<th>" + value + "</th>");
            }
            out.println("<th> 操作 </th>");
        %>
        </tr>
        <!-- 输出学生信息行 -->
        <%
            for (int i = 1; i < data.size(); i++) {
                out.println("<tr class=\"tr\">");
                for (String value : data.get(i)){
                    out.println("<td>" + value + "</td>");
                }
                out.println("<td> <div class=\"modify\">修改</div>&emsp;<div class=\"remove\">删除</div> </td>");
                out.println("</tr>");
            }
        %>
    </table>
    <!--  新增信息按钮与提交按钮  -->
    <div class="divs">
        <div class="divs"  style="display: inline-block">
            <input type="button" class="buttons" id="button_add" value="新增">
        </div>
    </div>
    <!--  空格 div 元素  -->
    <div style="height: 10px;"></div>
    <div  class="divs">
        <b class="titles">学生信息录入</b>
        <hr style="width: 820px;"/>
        <!--  专业信息 / 单选框  -->
        <div class="divs">
            <div class="name">
                <b style="color:red;">*</b>
                <b>专业 &emsp;:</b>
            </div>
            <div class="input_divs" id="专业">
                <input type="radio" id="major_01" name="major" value="计算机"/>
                <label for="major_01">计算机</label>
                <input type="radio" id="major_02" name="major" value="物联网"/>
                <label for="major_02">物联网</label>
                <input type="radio" id="major_03" name="major" value="通信"/>
                <label for="major_03">通信</label>
                <input type="radio" id="major_04" name="major" value="" style="display: none"/>
            </div>
            <div class="error"></div>
        </div>
        <!--  年级信息 / 下拉选择框 -->
        <div class="divs">
            <div class="name">
                <b style="color:red;">*</b>
                <b>年级 &emsp;:</b>
            </div>
            <div class="input_divs">
                <select class="input" name="grade" style="width: 388px;height: 37px;" id="年级">
                    <option id="opt_0" value="">--请选择--</option>
                    <option value="2020级">2020级</option>
                    <option value="2019级">2019级</option>
                    <option value="2018级">2018级</option>
                    <option value="2017级">2017级</option>
                    <option value="2016级">2016级</option>
                </select>
            </div>
            <div class="error"></div>
        </div>
        <!--  学号信息 / 文本输入框  -->
        <div class="divs">
            <div class="name">
                <b style="color:red;">*</b>
                <b>学号 &emsp;:</b>
            </div>
            <div class="input_divs" id="学号">
                <input class="input" type="text" placeholder="请输入学号" name="stu_number">
            </div>
            <div class="error"></div>
        </div>
        <!--  姓名信息 / 文本输入框  -->
        <div class="divs">
            <div class="name">
                <b style="color:red;">*</b>
                <b>姓名 &emsp;:</b>
            </div>
            <div class="input_divs" id="姓名">
                <input class="input" type="text" placeholder="请输入姓名" name="stu_name">
            </div>
            <div class="error"></div>
        </div>
        <!--  性别信息 / 单选框  -->
        <div class="divs">
            <div class="name">
                <b style="color:red;">*</b>
                <b>性别 &emsp;:</b>
            </div>
            <div class="input_divs" id="性别">
                <input type="radio" id="sex_01" name="sex" value="男" />
                <label for="sex_01">男</label>
                <input type="radio" id="sex_02" name="sex" value="女"/>
                <label for="sex_02">女</label>
                <input type="radio" id="sex_03" name="sex" value="" style="display: none"/>
            </div>
            <div class="error"></div>
        </div>
        <!--  科目信息 / 单选框  -->
        <div class="divs">
            <div class="name">
                <b style="color:red;">*</b>
                <b>科目 &emsp;:</b>
            </div>
            <div class="input_divs" id="科目">
                <input type="radio" id="class_01" name="class" value="数据结构"/>
                <label for="class_01">数据结构</label>
                <input type="radio" id="class_02" name="class" value="C语言程序设计"/>
                <label for="class_02">C语言程序设计</label>
                <input type="radio" id="class_03" name="class" value="Java语言程序设计"/>
                <label for="class_03">Java语言程序设计</label>
                <input type="radio" id="class_04" name="class" value="" style="display: none"/>
            </div>
            <div class="error"></div>
        </div>
        <!--  成绩信息 / 文本输入框  -->
        <div class="divs">
            <div class="name">
                <b style="color:red;">*</b>
                <b>成绩 &emsp;:</b>
            </div>
            <div class="input_divs" id="成绩">
                <input class="input" type="text" placeholder="请输入成绩" name="score">
            </div>
            <div class="error"></div>
        </div>
        <!--  信息验证写入按钮  -->
        <div class="divs" style="display: inline-block">
            <input type="button" class="buttons" id="button_write" value="提交" disabled="disabled" style="background-color: darkgray">
        </div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
    let Status = null;
    const button_add = $("#button_add");
    const button_write = $("#button_write");
    const modify_remove = $(".modify,.remove");

    // 新增学生按钮单击事件 Jquery
    button_add.click(add_stu);
    // 添加学生表格行
    function add_stu() {
        button_write.removeAttr("disabled");      // 使 写入按钮可点 防止误操作
        button_write.css("background-color","#121299");    // 恢复 写入按钮颜色
        $(this).css("background-color","lightgray"); // 改变 新增按钮颜色，并禁用
        $(this).attr("disabled","disabled");
        modify_remove.css("color","lightgray"); // 改变 新增按钮颜色，并禁用
        modify_remove.unbind();
        // 表格新增行
        const tr = "<tr class='tr' id='write_in'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td> <div class='modify'></div>&emsp;<div class='remove'></div> </td>";
        $("#table").append(tr);
        Status = "add";
    }

    // 提交按钮单击事件
    button_write.click(write_in);
    // 提交学生信息
    function write_in() {
        // 取出 表单中信息对象 ( 方便操作兑现属性值 )
        const major = $("input[name='major']:checked");
        const grade = $("select[name='grade'] option:selected");
        const stu_number = $("input[name='stu_number']");
        const stu_name = $("input[name='stu_name']");
        const sex = $("input[name='sex']:checked");
        const class_name = $("input[name='class']:checked");
        const score = $("input[name='score']");
        // 将 信息对象 以 列表形式存储
        const list = [major,grade,stu_number,stu_name,sex,class_name,score];
        // 判断所有值是否 符合输入要求 是/1 否/0 返回相同大小 0/1 列表
        const judge = judge_list(list);
        // 当列表中存在非法输入时 终止事件
        if(!(judge.indexOf(0)===-1)) return;
        $.ajax({
            url:"gradeList.jsp",
            dataType:"text",
            data:{
                "Status":Status,
                "major":major.val(),
                "grade":grade.val(),
                "stu_number":stu_number.val(),
                "stu_name":stu_name.val(),
                "sex":sex.val(),
                "class_name":class_name.val(),
                "score":score.val(),
            },
            error:function () {
                alert("信息提交失败,请重新提交!");
            },
        });
        $(location).attr('href', 'gradeList.jsp');
        stu_number.removeAttr("readonly");
    }

    // 删除 单击事件
    $(".remove").click(remove);
    // 删除学生信息
    function remove() {
        Status = "remove";
        // 删除列表对应行
        const stu_number = $(this).parent().prevAll().eq(4).text();
        const class_name = $(this).parent().prevAll().eq(1).text();

        $.ajax({
            url:"gradeList.jsp",
            dataType:"text",
            data:{
                "Status":Status,
                "stu_number":stu_number,
                "class_name":class_name,
            },
            error:function (result) {
                console.log(result);
                alert("删除失败,请重新删除!");
            },
        });
        $(location).attr('href', 'gradeList.jsp');
    }

    // 修改 单击事件
    $(".modify").click(modify);
    // 修改学生信息
    function modify() {
        Status = "modify";
        const major = $(this).parent().prevAll().eq(6).text();
        const grade = $(this).parent().prevAll().eq(5).text();
        const stu_number = $(this).parent().prevAll().eq(4).text();
        const stu_name = $(this).parent().prevAll().eq(3).text();
        const sex = $(this).parent().prevAll().eq(2).text();
        const class_name = $(this).parent().prevAll().eq(1).text();
        const score = $(this).parent().prevAll().eq(0).text();

        button_add.attr("disabled","disabled");
        button_add.css("background-color","lightgray");
        button_write.removeAttr("disabled");      // 使 写入按钮可点 防止误操作
        button_write.css("background-color","#121299");    // 恢复 写入按钮颜色
        modify_remove.css("color","lightgray"); // 改变 新增按钮颜色，并禁用
        modify_remove.unbind();
        // form 表单赋值
        $("input[value="+major+"]").prop("checked",true);
        $("option[value="+grade+"]").prop("selected",true);
        const choice_num = $("input[name='stu_number']");
        choice_num.val(stu_number);
        $("input[name='stu_name']").val(stu_name);
        $("input[value="+sex+"]").prop("checked",true);
        $("input[value="+class_name+"]").prop("checked",true);
        $("input[name='score']").val(score);

        // 删除 table 中对应行
        $(this).parent().parent().remove();
        // 表格新增行
        const tr = "<tr class='tr' id='write_in'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td> <div class='modify'></div>&emsp;<div class='remove'></div> </td>";
        $("#table").append(tr);
        // 不允许修改学号
        choice_num.attr("readonly","true");
    }

    // 判断输入合法性函数
    function judge_list(list) {
        const judge = [];
        $(".error_text").remove();
        for(let i=0;i<list.length;i++){
            judge[i] = 0;
            const name = list[i].parent().attr("id");
            switch (i) {
                case 0:{if(!list[i].val()) $("div[id='专业']").next().append("<b style='color: red;' class='error_text'>请选择专业！</b>");else judge[i] = 1;break;}
                case 1:{if(!list[i].val()) $("select[id='年级']").parent().next().append("<b style='color: red;' class='error_text'>请选择年级！</b>");else judge[i] = 1; break;}
                case 4:{if(!list[i].val()) $("div[id='性别']").next().append("<b style='color: red;' class='error_text'>请选择性别！</b>");else judge[i] = 1;break;}
                case 5:{if(!list[i].val()) $("div[id='科目']").next().append("<b style='color: red;' class='error_text'>请选择科目！</b>");else judge[i] = 1;break;}
                default:{
                    if(!list[i].val() && !list[i].parent().next().children().text())
                        list[i].parent().next().append("<b style='color: red;' class='error_text'>"+name+"不能为空！</b>");
                    else if(i===2 && list[i].val().search(/^\d{8}$/)!==0)
                        list[i].parent().next().append("<b style='color: red;' class='error_text'>学号输入错误！</b>");
                    else if(i===3 && list[i].val().length>4)
                        list[i].parent().next().append("<b style='color: red;' class='error_text'>姓名过长！</b>");
                    else if(i===6 && list[i].val().search(/^(100|[1-9][0-9]|)$|^[0-9]$/)!==0)
                        list[i].parent().next().append("<b style='color: red;' class='error_text'>成绩输入错误！</b>");
                    else {
                        list[i].parent().next().children().remove();
                        judge[i] = 1;
                    }
                    break;
                }
            }

        }
        return judge;   // 返回 0/1 合法性判断列表
    }

</script>
</html>
