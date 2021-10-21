<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/5
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <br>输入姓名:<a href="example3_15_a.jsp">确认姓名页面</a>
    <br>选择图书:<a href="example3_15_b.jsp">选择图书页面</a>
    <br>结账:    <a href="example3_15_c.jsp">结账页面</a>
</head>
<body>
    <p>选择购买的书籍:</p>
    <form action="" method="post" name="form">
        <input type="checkbox" name="choice" value="Java教程32.5元">Java教程32.5元
        <input type="checkbox" name="choice" value="数据库原理23元">数据库原理23元
        <input type="checkbox" name="choice" value="操作系统35元">操作系统35元
        <input type="checkbox" name="choice" value="C语言教程28.6元">C语言教程28.6元
        <br><input type="submit" value="提交" name="submit">
    </form>
    <%
        String[] book = request.getParameterValues("choice");
        if (book != null) {
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < book.length; i++) {
                str.append(book[i]+"<br>");
            }
            session.setAttribute("book",str);
        }
    %>
</body>
</html>
