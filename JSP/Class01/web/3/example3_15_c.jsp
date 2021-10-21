<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/5
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <br>输入姓名:<a href="example3_15_a.jsp">确认姓名页面</a>
    <br>选择图书:<a href="example3_15_b.jsp">选择图书页面</a>
    <br>结账:    <a href="example3_15_c.jsp">结账页面</a>
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
    String personName = (String) session.getAttribute("name");
    StringBuffer bookMess = null;
    if (personName==null || personName.length()==0) {
        out.println("到输入名字页面输入名字");
    } else {
        bookMess = (StringBuffer) session.getAttribute("book");
    }
%>
<%
    String buyBook = new String(bookMess);
    double sum = 0;
    String[] price = buyBook.split("[^1234567890.]");
    if (price!=null) {
        for (String item : price) {
            try {
                sum += Double.parseDouble(item);
            } catch (NumberFormatException exp) {}
        }
    }
%>
<br><%= handleStr(personName)%>购书信息:<br>
    <%= handleStr(buyBook)%><br>
总价格:<%= sum%>
</body>
</html>
