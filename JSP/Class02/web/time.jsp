<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/7
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.LocalDate" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
    %>
    <h2>
        用户在<%= date.getYear()%>/<%= date.getMonthValue()%>/<%= date.getDayOfMonth()%><br>
        <%= time.getHour()%>:<%= time.getMinute()%>:<%= time.getSecond()%>访问了网页
    </h2>

</body>
</html>
