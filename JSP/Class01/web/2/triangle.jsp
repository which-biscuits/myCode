<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    public String getArea(double a,double b,double c) {
        if(a+b>c && a+c>b && b+c>a) {
            double p = (a+b+c) / 2.0;
            double area = Math.sqrt(p*(p-a)*(p-b)*(p-c));
            String result = String.format("%.2f",area);
            return result;
        } else {
            return ("" + a + "," + b + "," + c + "不能构成一个三角形,无法计算面积");
        }
    }
%>
<%
    String sideA = request.getParameter("sideA");
    String sideB = request.getParameter("sideB");
    String sideC = request.getParameter("sideC");
    double a = Double.parseDouble(sideA);
    double b = Double.parseDouble(sideB);
    double c = Double.parseDouble(sideC);
%>
<p style="font-family: 黑体,serif;font-size: 36px;color: blue">
    <br><b>我是被加载的文件,负责计算三角形的面积<br>
    给我传递的三边是:<%= sideA%>,<%= sideB%>,<%= sideC%></b>
    <br><b><i>三角形的面积(保留两位小数):<%= getArea(a,b,c) %></i></b>
</p>
