<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="wzx.model.CounterBean" %>
<jsp:useBean id="cb" class="wzx.model.CounterBean"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1><jsp:getProperty name="cb" property="count"/></h1>
    <h1><jsp:setProperty name="cb" property="count" value="10"/></h1>
    <h1><%= cb.getCount()%></h1>
</body>
</html>
