<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/1
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        try {
            // 1-获取请求参数
            int left = Integer.parseInt(request.getParameter("left"));
            int right = Integer.parseInt(request.getParameter("right"));
            // 2-运算 + 伪耗时
            int result = left * right;
            Thread.sleep(3000);
            // 3-转换成字符串返回
            out.write("" + result);
        } catch (Exception e) { e.printStackTrace();}
    %>
</body>
</html>
