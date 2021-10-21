<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#eee8aa">
    <%
        String sideA = request.getParameter("sizeA");
        String sideB = request.getParameter("sizeB");
        String sideC= request.getParameter("sizeC");
        try {
            double a = Double.parseDouble(sideA);
            double b = Double.parseDouble(sideB);
            double c = Double.parseDouble(sideC);
            double p = (a+b+c)/2, area = 0;
            area = Math.sqrt(p*(p-a)*(p-b)*(p-c));
            out.println("<br>三角形面积" + area);
        } catch(NumberFormatException ee) {
            out.println("请输入数字字符");
        }
    %>
</body>
</html>
