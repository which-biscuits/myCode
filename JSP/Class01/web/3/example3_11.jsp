<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>现在的时间是:</p><br>
<%
    out.println("" + new Date());
    response.setHeader("Refresh","5");
%>
</body>
</html>
