const find_stName = $("#find_stName");    // 查找工人的工地输入
const find_wkName = $("#find_wkName");    // 查找工人的名字输入
const find_stNameError = $("#find_stNameError");  // 查找工地信息输入错误的提示框
const find_wkNameError = $("#find_wkNameError");  // 查找工人信息输入错误的提示框
const findWorkers_button = $("#findWorkers_button");    // 查询按钮
const addWorkers_button = $("#addWorkers_button");  // 新增按钮
const modify_button = $(".text_modify");
const delete_button = $(".text_delete");
const findStatus = [0, 0];  // 输入状态

// 查找工人的工地输入正确性判断
find_stName.click(fun_stName);
function fun_stName() {
    find_stNameError.empty();
    const stName = find_stName.val();
    if (stName === "" || stName===null ) {
        find_stNameError.append("<b class='error_text'>请输入工地名称!</b>");
    } else {
        findStatus[0] = 1;
    }
}
// 查找工人的名字输入正确性判断
find_wkName.click(fun_wkName);
function fun_wkName() {
    find_wkNameError.empty();
    const wkName = find_wkName.val();
    if (wkName === "" || wkName===null) {
        find_wkNameError.append("<b class='error_text'>请输入工人姓名!</b>");
    } else {
        findStatus[1] = 1;
    }
}
// 查询 单击事件
findWorkers_button.click(function (event) {
    if (findStatus[0] === 0) {
        find_stNameError.empty();
        find_stNameError.append("<b class='error_text'>请输入工地名称!</b>");
    }
    if (findStatus[1] === 0) {
        find_wkNameError.empty();
        find_wkNameError.append("<b class='error_text'>请输入工人姓名!</b>");
    }
    if (findStatus[0] === 1 && findStatus[1] === 1) {
        $.ajax({
            url: "processServlet",
            type: "post",
            dataType: "text",
            data: {
                "status": "find",
                "siteName": find_stName.val(),
                "workerName": find_wkName.val(),
            },
            error: function (result) {
                console.log(result);
                alert("查询失败, 请重新查询");
            },
            success: function (result) {
                location.reload();
            }
        });
    }
});
// 新增 单击事件
addWorkers_button.click(function (event) {
    $("#confirm_button").attr("status", "add");    // 确认按钮
    $(".mask_div").show();
    $(".add_div").show();
});
// 修改 单机事件
modify_button.click(function () {
    $("#confirm_button").attr("status", "modify");    // 确认按钮
    const workerCode = this.getAttribute("workerCode");
    $(".mask_div").show();
    $(".add_div").show();
    $.ajax({
        url:"findServlet",
        type:"post",
        dataType:"text",
        data:{
            "status":"modify_find",
            "workerCode":workerCode,
        },
        error:function (result) {
            console.log(result);
            alert("提请修改失败, 请重新提交 !");
            location.reload();
        },
        success:function (result) {
            result = JSON.parse(result);
            if (result.status === "true") {
                $("option[value="+result.siteName+"]").prop("selected",true);
                $("input[name='workerCode']").val(result.workerCode);
                $("input[name='workerName']").val(result.workerName);
                $("input[value="+result.workerSex+"]").prop("checked",true);
                $("input[name='workerBirthday']").val(result.workerBirthday);
                $("input[name='workerPhone']").val(result.workerPhone);
                $("input[name='workerAddress']").val(result.workerAddress);
                $("option[value="+result.workerJob+"]").prop("selected",true);
                $("option[value="+result.workerPosition+"]").prop("selected",true);
                $("input[name='registerTime']").val(result.registerTime);
                $("#image").attr("src",result.workerPhoto);
            } else {
                alert("查无此人 !");
                location.reload();
            }
        }
    });
});
// 删除单击事件
delete_button.click(function () {
    const workerCode = this.getAttribute("workerCode");
    const cfm = confirm("您确定要删除 : " + workerCode + " 的信息吗?");
    if (cfm === true) {
        $.ajax({
            url:"processServlet",
            type:"post",
            dataType:"text",
            data:{
                "status":"delete",
                "workerCode":workerCode,
            },
            error:function (result) {
                console.log(result);
                alert("删除失败, 请重新删除 !");
            },
            success:function (result) {
                location.reload();
            }
        });
    }
});
