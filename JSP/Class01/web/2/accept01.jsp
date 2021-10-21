<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String userId = request.getParameter("userId");
    String userPassword = request.getParameter("userPassword");
    session.setAttribute("userId",userId);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>你好<% out.print(userId);%></h1>
</body>
</html>
