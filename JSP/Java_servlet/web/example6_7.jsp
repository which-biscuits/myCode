<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/28
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .textStyle{
            font-size: 30px;
        }
    </style>
</head>
<body bgcolor="#ffccff">
    <p class="textStyle">
        <form action="handleForward" class="textStyle" method="post">
            输入数字(用逗号空格或其他非数字字符分隔):<br/>
            <textarea name="digitData" class="textStyle" rows="5" cols="10">

            </textarea>
        <br><input type="submit" class="textStyle" name="submit" value="提交(排序数字 sort)" />
        <br><input type="submit" class="textStyle" name="submit" value="提交(求和 sum)" />
        </form>
    </p>
</body>

</html>
