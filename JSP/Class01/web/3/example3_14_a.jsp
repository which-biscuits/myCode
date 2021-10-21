<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/3
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#00ffff">
    我是example3_14_a.jsp页面<br>输姓名连接到example3_14_b.jsp
    <%
        String id = session.getId();
        out.println("<br>您的session对象的ID是:<br>" + id);
    %>
    <form action="example3_14_b.jsp" method="post" name="form">
        <input type="text" name="boy">
        <input type="submit" value="送出" name="submit">
    </form>
</body>
</html>
