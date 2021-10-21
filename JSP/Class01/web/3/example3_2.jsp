<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#00ffff">
    <form action="" method="post" name="form">
        <input type="text" name="girl">
        <input type="submit" value="确定" name="submit">
    </form>
    <%
        String textContent = request.getParameter("girl");
        if (textContent==null) {
            textContent="0";
        }
        String[] a = textContent.split("#");
        double sum = 0;
        try {
            for (String s: a) {
                out.print(s + " ");
                sum += Double.parseDouble(s);
                out.print("<br>数字的和是" + sum);
            }
        } catch (NumberFormatException e) {
            out.print("<br>" + "请输入数字字符");
        }
    %>
</body>
</html>
