<%--
  Created by IntelliJ IDEA.
  User: WZX
  Date: 2021/5/29
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="CSS-JS/out.css"/>
    <title>Title</title>
</head>
<body>
    <div>
        <div> 请输入 </div>
        <form method="post" enctype="multipart/form-data">
            <div>
                <div> <b class="mark_b">*</b> <div class="input_text">工地名称 &emsp;:</div>
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
                <div> <b class="mark_b">*</b> <div class="input_text">工人编码 &emsp;:</div>
                    <div class="input_in">
                        <input type="text" name="workerCode" value=""></div>
                    <div class="error" id="workerCodeError"></div></div>
                <div> <b class="mark_b">*</b> <div class="input_text">工人姓名 &emsp;:</div>
                    <div class="input_in">
                        <input type="text" name="workerName">
                    </div>
                    <div class="error" id="workerNameError"></div></div>
                <div> <b class="mark_b">*</b> <div class="input_text">工人性别 &emsp;:</div>
                    <div class="input_in">
                        <input type="radio" id="sex_01" name="workerSex" value="男" />
                        <label for="sex_01">男</label>
                        <input type="radio" id="sex_02" name="workerSex" value="女"/>
                        <label for="sex_02">女</label>
                        <input type="radio" id="sex_03" name="workerSex" value="" style="display: none"/>
                    </div>
                    <div class="error" id="workerSexError"></div></div>
                <div> <b class="mark_b">*</b> <div class="input_text">工人生日 &emsp;:</div>
                    <div class="input_in">
                        <input type="date" name="workerBirthday"/>
                    </div>
                    <div class="error" id="workerBirthdayError"></div></div>
                <div> <b class="mark_b">*</b> <div class="input_text">联系电话 &emsp;:</div>
                    <div class="input_in">
                        <input type="text" name="workerPhone">
                    </div>
                    <div class="error" id="workerPhoneError"></div></div>
                <div> <b class="mark_b">*</b> <div class="input_text">联系地址 &emsp;:</div>
                    <div class="input_in">
                        <input type="text" name="workerAddress">
                    </div>
                    <div class="error" id="workerAddressError"></div></div>
                <div> <b class="mark_b">*</b> <div class="input_text">工人工种 &emsp;:</div>
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
                <div> <b class="mark_b">*</b> <div class="input_text">工人职务 &emsp;:</div> <div class="input_in">
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
                <div> <b class="mark_b">*</b> <div class="input_text">注册日期 &emsp;:</div> <div class="input_in">
                    <input type="date" value="2021-05-31" name="registerTime"/>
                </div>
                    <div class="error" id="registerTimeError"></div></div>
            </div>
            <div>
                <img src="" id="image">
                <input type="file" value="上传照片" name="workerPhoto" onchange="fileOnChange(this)">
                <div class="error" id="workerPhotoError"></div>
            </div>
            <div>
                <input type="button" value="取消" id="cancel_button">
                <input type="button" value="保存" id="confirm_button">
            </div>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="CSS-JS/out.js"></script>
</body>
</html>
