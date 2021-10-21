<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/1
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.time.LocalTime" %>
<%!
    public int continueSum(int start,int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#eee8aa">
    <script>
        var userTime = new Date();
        var hour = userTime.getHours();
        var minute = userTime.getMinutes();
        var second = userTime.getSeconds();
        var millisecond = userTime.getMilliseconds();
        document.write("<h2>浏览器时间:" + hour + ":" + minute + ":" + second + ":" + millisecond + "<br></h2>")
    </script>
    <hr>
    <p style="font-family: 黑体;font-size: 36px;color: red">
        <%
            LocalTime timeSever = LocalTime.now();
            int hour = timeSever.getHour();
            int minute = timeSever.getMinute();
            int second = timeSever.getMinute();
            int nano = timeSever.getNano();
            int millisecond = nano / 1000000;
            out.write("服务器时间:<br>" +  hour + ":" + minute + ":" + second + ":" + millisecond);
            int start = 1;
            int end = 100;
            int sum = continueSum(start,end);
        %>
    </p>
    <p style="font-family: 宋体,serif;font-size: 33px;color: blue">
        从
        <%= start%>
        至
        <%= end%>
        的<br>连续和是:
        <%= sum%>
    </p>
    <script>
        document.write("<h2>服务器时间:" + <%= hour%> + ":" + <%= minute%> + ":"
            + <%= second%> + ":" + <%= millisecond%> + "</h2>")
    </script>
</body>
</html>
