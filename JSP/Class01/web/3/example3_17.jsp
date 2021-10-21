<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/5
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>z
    <title>Title</title>
</head>
<body>
    <%
        session.setMaxInactiveInterval(5);
        boolean boo = session.isNew();
        out.println("<br>如果你是第一次访问当前web服务目录,您的会话是新的");
        out.println("<br>如果你不是第一次访问当前web服务目录,您的会话不是新的");
        out.println("<br>会话是新的吗?:" + boo);
        out.println("<br>欢迎来到本页面,您的session允许的最长发呆时间为" + session.getMaxInactiveInterval() + "秒");
        out.println("<br>您的session的创建时间是" + new Date(session.getCreationTime()));
        out.println("<br>您的session的Id是" + session.getId());
        Long lastTime = (Long) session.getAttribute("lastTime");
        if (lastTime == null) {
            long n = session.getLastAccessedTime();
            session.setAttribute("lastTime",new Long(n));
        } else {
            long m = session.getLastAccessedTime();
            session.setAttribute("lastTime",new Long(m));
        }
    %>
</body>
</html>
