<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#00ffff">
    <%
        double a = 6.12,b = 7.08,c = 9.22;
    %>
    <p style="font-family: 宋体,serif;font-size: 36px"></p>
    <br>加载triangle.jsp 计算三遍为<%= a%>,<%= b%>,<%= c%>的三角形面积
    <jsp:include page="triangle.jsp" >
        <jsp:param name="sideA" value="<%= a%>"/>
        <jsp:param name="sideB" value="<%= b%>"/>
        <jsp:param name="sideC" value="<%= c%>"/>
    </jsp:include>
</body>
</html>
