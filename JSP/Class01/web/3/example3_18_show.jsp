<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/5
  Time: 15:40
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
        public String handleStr(String s) {
            try{
                byte[] bb = s.getBytes();
                s = new String(bb);
            } catch(Exception exp) {
                return s;
            }
            return s;
        }
    %>
    <%
        Vector v = (Vector) application.getAttribute("Mess");
        for (int i = 0; i < v.size(); i++) {
            String message = (String) v.elementAt(i);
            String[] a = message.split("#");
            out.print("留言人:" + handleStr(a[0]) + ",");
            out.print("标题:" + handleStr(a[1]) + "<br>");
            out.print("留言内容:<br>" + handleStr(a[2]));
            out.print("<br>-----------------------<br>");
        }
    %>
</body>
</html>
