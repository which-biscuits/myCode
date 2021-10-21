<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/1
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>这是一个简单的JSP页面</h3>
    <%
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
    %>
    <h5> 1 到 100 的连续和是: <%= sum%></h5>
</body>
</html>
