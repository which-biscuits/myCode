<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#00ffff">
    <%
        String path = request.getServletPath();     // 请求的页面
        String webDir = request.getContextPath();   // 获取当前Web服务目录的名称
        webDir = webDir.substring(1);   // 去掉名称前面的目录符号
        String clientIP = request.getRemoteAddr();  // 用户的IP地址
        int serverPort = request.getServerPort();    // 服务器的端口号
    %>
    用户请求的页面:
    <%= path %>
    <br>Web服务目录的名字: <%= webDir%>
    <br>用户的IP地址: <%= clientIP%>
    <br>服务器的端口号: <%= serverPort%>
</body>
</html>
