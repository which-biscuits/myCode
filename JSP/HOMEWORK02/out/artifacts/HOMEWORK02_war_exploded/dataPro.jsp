<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/25
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="sun.awt.windows.WPrinterJob" %>
<html>
<head>
    <title>Title</title>
</head>
<%!
%>>

<%
     BufferedReader br = new BufferedReader(new FileReader(application.getRealPath("use.txt")));
     String read = br.readLine();
     List<String[]> datas=null;  // 存放所有数据的二维数组
     while(read!=null){ // 将所有数据存放在二位数组中
         String[] line = read.split(" ");   // 以空格分隔
         datas.add(line);   // 添加行
         read = br.readLine();
     }
     String num=null;  // 把你页面里面修改的学号传过来
     String class_name=null; // 把你页里面要修改的课程名称传过来

     for(int i = 0; i < datas.size(); i++) {
         // 记得修改下面的下标,选中对应位置
         if (datas.get(i)[0].equals(num)&&datas.get(i)[1].equals(class_name)){  // 当选中行的对应值与要修改的值相同时
             datas.remove(i);   // 删除对应的行
         }
     }

     for (String[] line;datas){
         System.out.println();("wzx");  // 根据你的原有代码,将一行一行写入文件
     }
%>
