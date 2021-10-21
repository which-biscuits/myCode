<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/7
  Time: 8:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.*"%>
<%!
    public double getPriceSum(String input) {
        Pattern pattern;    // 模式对象
        Matcher matcher;    // 匹配对象
        String regex = "-?[0-9][0-9]*[.]?[0-9]*";   // 匹配模式的正则表达式
        pattern = Pattern.compile(regex);   // 初始化模式对象
        matcher = pattern.matcher(input);   // 初始化匹配对象,用于检索input
        double sum = 0;
        while (matcher.find()) {
            String str = matcher.group();
            sum += Double.parseDouble(str);
        }
        return sum;
    }
%>
<html>
<head>
    <title>Title</title>
    <style>
        .input{
            width: 300px;
        }
    </style>
</head>
<body bgcolor="#eee8aa">
    <p style="font-family: 黑体,serif;font-size: 20px"></p>
    <form action="" method="post" name="form">
        <br>请输入人数:
        <input type="text" name="peopleNum" placeholder="请输入人数">
        <input type="submit">
    </form>
    <form action="" method="post" name="form">
    <%
        String peopleNum = request.getParameter("peopleNum");
        if(peopleNum!=null){
            for (int i = 0; i < Integer.parseInt(peopleNum); i++) {
                char name = (char) (i + 65);
                out.println("<br>用户" + name + "的菜单:<br>");
                out.println("<input class=\"input\" type=\"text\" name=\"user" + name + "\" placeholder=\"请输入菜单\">");
                out.println("<br>消费总和:<br>");
            }
            out.println("<input type=\"submit\">");
            out.println("<input type=\"text\" name=\"Num\" value=\""+ peopleNum +"\"style=\"display: none\" >");
        }
    %>
    </form>
    <%
        String Num = request.getParameter("Num");
        if (Num!=null) {
            for (int i = 0; i < Integer.parseInt(Num); i++) {
                char name = (char) (i + 65);
                String input = request.getParameter("user" + name);
                out.println("<br>用户" + name + "的菜单:<br>");
                out.println("<input class=\"input\" type=\"text\" name=\"user" + name + "\" placeholder=\"请输入菜单\">");
                out.println("<br>消费总和:"+getPriceSum(input)+"<br>");
            }
        }
    %>
</body>
</html>
