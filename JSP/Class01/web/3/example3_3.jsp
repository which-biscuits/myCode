<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#eee8aa">
    <form action="" method="post" name="form">
        <input type="text" name="information_fees" size="50">
        <input type="submit" value="确定" name="submit">
    </form>
    <%
        request.setCharacterEncoding("gb2312");
        String information_fees = request.getParameter("information_fees");
        double number = 0;
        if (information_fees==null) {
            information_fees="";
        }
    %>
    <b>账单内容:<br><%= information_fees %></b>
    <%
        String[] a = information_fees.split("[^0123456789.]");
        double sum = 0;
        for (String s:a) {
            try {
                sum += Double.parseDouble(s);
            } catch (NumberFormatException exp) {}
        }
    %>
    <br>账单总消费额:<%= sum%></br>
</body>
</html>
