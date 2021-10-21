<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/5
  Time: 13:40
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
    <p>输入姓名</p>
    <form action="" method="post" name="form">
        <input type="text" name="name">
        <input type="submit" value="确认" name="submit">
    </form>
    <%
        String name = request.getParameter("name");
        if(name == null)
            name = "";
        else
            session.setAttribute("name",name);  // 将name存放在用户的session中
    %>
</body>
</html>
