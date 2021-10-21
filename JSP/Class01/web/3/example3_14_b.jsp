<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/3
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#eee8aa">
    <%
        String id = session.getId();
        out.println("<br>您的session对象的ID是:<br>" + id);
    %>
<br> 连接到example3_14_a.jsp的页面<br>
<a href="example3_14_a.jsp">example3_14_a.jsp</a>
</body>
</html>
