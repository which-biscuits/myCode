const cancel_button = $("#cancel_button");      // 取消按钮
const confirm_button = $("#confirm_button");    // 确认按钮

const judgeCode = [];

// 确认按钮
confirm_button.click(function (event) {
    const siteName = $("select[name='siteName'] option:selected");         // 工地名称的select输入框
    const workerCode = $("input[name='workerCode']");     // 工人编码的input输入框
    const workerName = $("input[name='workerName']");     // 工人姓名
    const workerSex = $("input[name='workerSex']:checked");       // 工人性别
    const workerBirthday = $("input[name='workerBirthday']");     //  工人生日
    const workerPhone = $("input[name='workerPhone']");           // 工人联系方式
    const workerAddress = $("input[name='workerAddress']");       // 工人家庭住址
    const workerJob = $("select[name='workerJob'] option:selected");             // 工人工种
    const workerPosition = $("select[name='workerPosition'] option:selected");    // 工人职务
    const registerTime = $("input[name='registerTime']");         // 注册时间
    const workerPhoto = $("input[name='workerPhoto']");           // 工人照片

    const list = [siteName, workerCode, workerName, workerSex, workerBirthday, workerPhone,
        workerAddress, workerJob, workerPosition, registerTime, workerPhoto];
    judgeList(list);
    console.log(judgeCode);
    if (judgeCode.indexOf(0) !== -1 ) return;
    const formData = new FormData();
    formData.append("siteName", siteName.val());
    formData.append("workerCode", workerCode.val());
    formData.append("workerName", workerName.val());
    formData.append("workerSex", workerSex.val());
    formData.append("workerBirthday", workerBirthday.val());
    formData.append("workerPhone", workerPhone.val());
    formData.append("workerAddress", workerAddress.val());
    formData.append("workerJob", workerJob.val());
    formData.append("workerPosition", workerPosition.val());
    formData.append("registerTime", registerTime.val());
    if (list[10].val())
        formData.append("workerPhoto", workerPhoto[0].files[0]);
    if (this.getAttribute('status') === "add") {
        $.ajax({
            async:false,
            cache: false,
            processData : false, // 使数据不做处理
            contentType : false, // 不要设置Content-Type请求头
            url:"addServlet",
            type:"post",
            dataType:"json",
            data:formData,
        });
    } else if (this.getAttribute("status") === "modify") {
        $.ajax({
            async:false,
            cache: false,
            processData : false, // 使数据不做处理
            contentType : false, // 不要设置Content-Type请求头
            url:"modifyServlet",
            type:"post",
            dataType:"json",
            data:formData,
        });
    } else {
        alert("操作失败 !");
    }
    location.reload();
});

function judgeList(list) {
    $(".error").empty();
    for (let i = 0; i < list.length; i++) {
        judgeCode[i] = 1;
        switch (i) {
            case 0 : {
                if (!list[0].val()) {
                    $("#siteNameError").append("<b class='error_text'> 请选择工地 ! </b>");judgeCode[i] = 0;
                }
                break;
            }
            case 1 : {
                if (!list[1].val()) {
                    $("#workerCodeError").append("<b class='error_text'> 请填写工人编码 ! </b>");judgeCode[i]=0;
                } else if (list[1].val().search(/^[A-Za-z0-9]{5,}$/) !== 0) {
                    $("#workerCodeError").append("<b class='error_text'> 编码格式错误 ! </b>");judgeCode[i]=0;
                } else {
                    $.ajax({
                        async:false,
                        url:"judgeServlet",
                        type:"post",
                        dataType:"text",
                        data:{
                            "status":"judgeCode",
                            "workerCode":list[1].val(),
                        },
                        error:function (result) {
                            $("#workerCodeError").append("<b class='error_text'> 请重新填写工人编码 ! </b>");judgeCode[i]=0;
                        },
                        success:function (result) {
                            result = JSON.parse(result);
                            if (result.CodeStatus === "false" && $("#confirm_button").attr("status") !== "modify") {
                                $("#workerCodeError").append("<b class='error_text'> 工人编码已存在 ! </b>");judgeCode[i]=0;
                            } else if (result.CodeStatus === "true" && $("#confirm_button").attr("status") === "modify"){
                                $("#workerCodeError").append("<b class='error_text'> 工人编码不存在 ! </b>");judgeCode[i]=0;
                            }
                        }
                    });
                }
                break;
            }
            case 2 : {
                if (!list[2].val()) {
                    $("#workerNameError").append("<b class='error_text'> 请填写工人姓名 ! </b>");judgeCode[i] = 0;
                } else if (list[2].val().search(/^[\u4e00-\u9fa5]+$/) !==0) {
                    $("#workerNameError").append("<b class='error_text'> 姓名格式错误 ! </b>");judgeCode[i] = 0;
                }
                break;
            }
            case 3 : {
                if (!list[3].val()) {
                    $("#workerSexError").append("<b class='error_text'> 请选择工人性别 ! </b>");judgeCode[i] = 0;
                }
                break;
            }
            case 4 : {
                if (!list[4].val()) {
                    $("#workerBirthdayError").append("<b class='error_text'> 请选择工人生日 ! </b>");judgeCode[i] = 0;
                }
                break;
            }
            case 5 : {
                if (!list[5].val()) {
                    $("#workerPhoneError").append("<b class='error_text'> 请输入工人联系方式 ! </b>");judgeCode[i] = 0;
                } else if (list[5].val().search(/^[1][0-9]{10}$/)){
                    $("#workerPhoneError").append("<b class='error_text'> 联系方式格式错误 ! </b>");judgeCode[i] = 0;
                }
                break;
            }
            case 6 : {
                if (!list[6].val()) {
                    $("#workerAddressError").append("<b class='error_text'> 请输入工人联系地址 ! </b>");judgeCode[i] = 0;
                }
                break;
            }
            case 7 : {
                if (!list[7].val()) {
                    $("#workerJobError").append("<b class='error_text'> 请选择工人工种 ! </b>");judgeCode[i] = 0;
                }
                break;
            }
            case 8 : {
                if (!list[8].val()) {
                    $("#workerPositionError").append("<b class='error_text'> 请选择工人职务 ! </b>");judgeCode[i] = 0;
                }
                break;
            }
            case 10 : {
                if (!list[10].val() && $("#confirm_button").attr("status") !== "modify") {
                    $("#workerPhotoError").append("<b class='error_text'> 请上传工人照片 ! </b>");judgeCode[i] = 0;
                } else if (list[10].val()) {
                    const acceptType = "png/jpg/jpeg";
                    const selectedFileName = list[10][0].files[0].name;
                    const fileType = selectedFileName.substring(selectedFileName.indexOf('.') + 1, selectedFileName.length);
                    if (acceptType.includes(fileType.toLowerCase()) < 0) {
                        $("#workerPhotoError").append("<b class='error_text'> 不支持该图片类型 ! </b>");judgeCode[i] = 0;
                    } else if (list[10][0].files[0].size > 16777216) {
                        $("#workerPhotoError").append("<b class='error_text'> 图片大于16MB ! </b>");judgeCode[i] = 0;
                    }
                }
                break;
            }
        }
    }
}

cancel_button.click(function () {
    location.reload();
});

$("input[name='workerPhoto']").change(function(){
    $("#image").attr("src",URL.createObjectURL($(this)[0].files[0]));
});

