<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/1
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table</title>
</head>
<body>
    <table border="1">
        <tr><td>first column</td><td>second column</td><td>third column</td></tr>
        <%
            int num = Integer.parseInt( request.getParameter("num"));
            for (int i = 0; i < num; i++) {
                out.println("<tr>");
                out.println("<td>" + (i+1) + "</td>");
                out.println("<td>" + (i+1)*2 + "</td>");
                out.println("<td>" + (i+1)*3 + "</td>");
                out.println("</tr>");
            }
        %>
    </table>
</body>
</html>
