<%@ page import="java.nio.charset.StandardCharsets" %><%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#00ffff">
    <%
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        if (name==null||name.length()==0) {
            response.sendRedirect("example3_12.jsp");
        }
    %>
<b>欢迎<%= name%>来到本网页</b>
</body>
</html>