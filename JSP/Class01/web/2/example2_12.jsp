<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>产生一个 1 ~ 10 之间的随机数</h1>
    <%
        double a = 6.12,b = 7.08,c = 9.22;
    %>
    <%
        double i = (int) (Math.random()*10) + 1;
        if (i <= 5) {
    %>
        <jsp:forward page="triangle.jsp">
            <jsp:param name="sideA" value="<%= a%>"/>
            <jsp:param name="sideB" value="<%= b%>"/>
            <jsp:param name="sideC" value="<%= c%>"/>
        </jsp:forward>
    <%
        } else {
    %>
        <jsp:forward page="triangle.jsp">
            <jsp:param name="sideA" value="<%= a%>"/>
            <jsp:param name="sideB" value="<%= b%>"/>
            <jsp:param name="sideC" value="<%= c%>"/>
        </jsp:forward>
    <%
        }
    %>
</body>
</html>
