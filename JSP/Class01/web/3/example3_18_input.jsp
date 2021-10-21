<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/5
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="example3_18_pane.jsp" method="post" name="form">
        输入姓名:<input type="text" name="peopleName">
        <br>留言标题:<input type="text" name="title">
        <br>留言:<br><textarea name="messages" rows="10" cols="36" wrap="physical"></textarea>
        <input type="submit" value="提交" name="submit">
    </form>
    <a href="example3_18_show.jsp">查看留言板</a>
</body>
</html>
