<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/5
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%!
        Vector v = new Vector();    // 向量, 大小可变
        int i = 0;
        ServletContext application;
        synchronized void leaveWord(String s) {
            application = getServletContext();
            i++;
            v.add("No." + i + "," +s);
            application.setAttribute("Mess",v);
        }
    %>
    <%
        String name = request.getParameter("peopleName");
        String title = request.getParameter("title");
        String messages = request.getParameter("messages");
        if (name == null)
            name = "guest" + (int)(Math.random()*10000);
        if (title == null)
            title = "无标题";
        if (messages == null)
            messages = "无信息";
        String s = name + "#" +title+ "#" +messages;
        leaveWord(s);
        out.println("您的信息已经提交!");
        out
    %>
    <a href="example3_18_input.jsp">返回留言页面</a>
</body>
</html>
