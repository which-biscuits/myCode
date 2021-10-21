<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/1
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p style="font-family: 黑体;font-size: 26px;color: blue">
        <%!
            double multi(double x, double y) {
                return x * y;
            }
            double div(double x, double y) {
                return x / y;
            }
            class Circle {
                double r;
                double getArea() {
                    return Math.PI * r * r;
                }
            }
        %>
        <%
            double x = 9.79;
            double y = 20.8;
            out.print("调用multi方法计算" + x + "与" + y + "的积:<br>");
            out.print(multi(x,y));
            out.print("<br>调用div方法计算" + x + "除以" + y + "的商:<br>");
            String s = String.format("小数点保留3位:%10.3f",div(x,y));
            out.print(s);
            Circle circle = new Circle();
            circle.r = 3.6;
            out.print("<br>半径是" + circle.r + "的圆的面积:" + circle.getArea());
        %>
    </p>
</body>
</html>
