<%@ page import="java.util.*" %>
<%@ page import="handle.data.SQLBean" %>
<%--
  Created by IntelliJ IDEA.
  User: WZX
  Date: 2021/5/29
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="CSS-JS/workerInfos.css">
    <link rel="stylesheet" type="text/css" href="CSS-JS/out.css"/>
    <title>workerInfos</title>
    <%
      Object get_list = session.getAttribute("list");
      Object get_findStatus = session.getAttribute("findStatus");
      List<String[]> list = null;
      if (get_findStatus == "true" && get_list != null) {
        list = (List<String[]>) get_list;
      }
      else {
        list = SQLBean.findWorkers();
      }
      session.setAttribute("findStatus", "false");
    %>
  </head>
  <body bgcolor="#d5d7e9">
  <div>
    <div style="font-size: 20px"> <b>查询条件</b> </div>
    <hr class="hr" align="left"/>
    <form method="post" action="workerInfos.jsp" >
      <div class="div">
        <div class="input_text"> <b class="mark_b">*</b><b>工地名称 : </b></div>
        <div class="find"> <input class="input" type="text" placeholder="请输入工地名称!" id="find_stName" onkeydown="fun_stName()"></div>
        <div class="find" id="find_stNameError"> </div> <br/>
      </div>
      <div class="div">
        <div class="input_text"> <b class="mark_b">*</b><b>工人姓名 : </b></div>
        <div class="find"> <input class="input" type="text" placeholder="请输入工人姓名!" id="find_wkName" onkeydown="fun_wkName()"> </div>
        <div class="find" id="find_wkNameError">  </div><br/>
      </div>
      <div class="find div">
        <input class="buttons" type="button" value="查询" id="findWorkers_button">
        <input class="buttons" type="button" value="新增" id="addWorkers_button">
      </div>
    </form>
    <hr class="hr" align="left"/>
    <div style="font-size:20px"> <b>查询结果</b> </div> <br />
  </div>
  <div class="find_div" style="height: 400px;width: 1000px;border-radius: 6px;">
    <table class="title"  border="1" cellspacing="0" style="margin-top: 5px">

      <th> 工人姓名 </th> <th> 工人编码 </th> <th> 工地名称 </th> <th> 工人生日 </th> <th> 注册时间 </th> <th> 操作 </th>
      <%
        for (int i = 0; i < list.size(); i++) {
          out.write("<tr id=\"tr" + i + "\">");
          for (String strings : list.get(i)) {
            out.write("<td>" + strings + "</td>");
          }
          out.write("<td><div WorkerCode="+ list.get(i)[1] +" class='text_modify'>修改</div> &emsp;" +
                  "<div WorkerCode="+ list.get(i)[1] + " class='text_delete'>删除</div></td>");
          out.write("</tr>");
        }
      %>
    </table>
  </div>
  <div class="mask_div"></div>
  <div class="add_div">
    <div> <b>请输入</b> </div>
    <form method="post" enctype="multipart/form-data">
      <div style="display: inline-block;width: 500px;vertical-align: top;">
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>工地名称&emsp;:</b> </div>
          <div class="input_in">
            <select class="selection" name="siteName">
              <option value="">--请选择--</option>
              <option value="河海大学">河海大学</option>
              <option value="南京大学">南京大学</option>
              <option value="东南大学">东南大学</option>
              <option value="北京大学">北京大学</option>
              <option value="清华大学">清华大学</option>
            </select></div>
          <div class="error" id="siteNameError"></div></div>
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>工人编码 &emsp;:</b></div>
          <div class="input_in">
            <input class="input" type="text" name="workerCode" value=""></div>
          <div class="error" id="workerCodeError"></div></div>
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>工人姓名 &emsp;:</b></div>
          <div class="input_in">
            <input class="input" type="text" name="workerName">
          </div>
          <div class="error" id="workerNameError"></div></div>
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>工人性别 &emsp;:</b></div>
          <div class="input_in">
            <input type="radio" id="sex_01" name="workerSex" value="男" />
            <label for="sex_01">男</label>
            <input type="radio" id="sex_02" name="workerSex" value="女"/>
            <label for="sex_02">女</label>
            <input type="radio" id="sex_03" name="workerSex" value="" style="display: none"/>
          </div>
          <div class="error" id="workerSexError"></div></div>
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>工人生日 &emsp;:</b></div>
          <div class="input_in">
            <input class="time" type="date" name="workerBirthday"/>
          </div>
          <div class="error" id="workerBirthdayError"></div></div>
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>联系电话 &emsp;:</b></div>
          <div class="input_in">
            <input class="input" type="text" name="workerPhone">
          </div>
          <div class="error" id="workerPhoneError"></div></div>
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>联系地址 &emsp;:</b></div>
          <div class="input_in">
            <input class="input" type="text" name="workerAddress">
          </div>
          <div class="error" id="workerAddressError"></div></div>
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>工人工种 &emsp;:</b></div>
          <div class="input_in">
            <select class="selection" name="workerJob" >
              <option value="">--请选择--</option>
              <option value="木工">木工</option>
              <option value="钢筋工">钢筋工</option>
              <option value="泥水工">泥水工</option>
              <option value="砖瓦工">砖瓦工</option>
              <option value="抹灰工">抹灰工</option>
              <option value="电工">电工</option>
            </select>
          </div>
          <div class="error" id="workerJobError"></div></div>
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>工人职务 &emsp;:</b></div> <div class="input_in">
          <select class="selection" name="workerPosition" >
            <option value="">--请选择--</option>
            <option value="普工">普工</option>
            <option value="初级工">初级工</option>
            <option value="中级工">中级工</option>
            <option value="高级工">高级工</option>
            <option value="技师">技师</option>
            <option value="高级技师">高级技师</option>
          </select>
        </div>
          <div class="error" id="workerPositionError"></div></div>
        <div class="div"> <b class="mark_b">*</b> <div class="input_text"><b>注册日期 &emsp;:</b></div> <div class="input_in">
          <input class="time" type="date" value="2021-05-31" name="registerTime"/>
        </div>
          <div class="error" id="registerTimeError"></div></div>
      </div>
      <div style="display: inline-block;vertical-align: top;">
        <div><input class="file_input" style="width: 180px" type="file" value="上传照片" name="workerPhoto"></div>
        <img src="" id="image" width="180" height="200">
        <div class="error" id="workerPhotoError" style="display: block;height: 30px"></div>
      </div>
    </form>
    <div class="div">
      <input class="buttons" type="button" value="取消" id="cancel_button">
      <input class="buttons" type="button" value="保存" id="confirm_button">
    </div>
  </div>
  </body>
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="CSS-JS/workerInfos.js"></script>
  <script src="CSS-JS/out.js"></script>
</html>
