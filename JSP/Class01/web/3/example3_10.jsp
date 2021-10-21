<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    我正在学习response对象的
    <br>setContentType方法
    将当前页面保存为word文档吗
</p>
<form action="" method="get" name="form">
    <input type="submit" value="yes" name="submit">
</form>
<%
    String str = request.getParameter("submit");
    if (str==null) {
        str = "";
    }
    if (str.equals("yes")) {
        response.setContentType("application/msword;charset=gb2312");
    }
%>
</body>
</html>
