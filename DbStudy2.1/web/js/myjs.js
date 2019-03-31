function addWrongBook(sid) {
    $.ajax({
        url: "TestServlet",
        type: "post",
        dataType: "json",
        data: {
            "method": "ajaxAddWrongBook",
            "sid": sid
        },
        async: false,
        cache: false,
        success: function (result) {
            if (result) {
                alert("添加成功")
            } else {
                alert("错题本中已有")
            }
        }
    });
}

function giveThumbs(essayid) {
    $.ajax({
        url: "QueryDatumMainServlet",
        type:"post",
        dataType: "json",
        data: {
            "method":"ajaxGiveThumbs",
            "essayid":essayid
        },
        async: false,
        cache: false,
        success:function (result) {
            if(result){
                alert("成功点赞");
            }else{
                alert("已经点过赞了")
            }
        }
    });
}

function getHeadImg() {
// $.ajax({
//         url: "/DbStudy2_1_war_exploded/HeadImgStoreServlet",
//         type: "post",
//         dataType: "json",
//         data: {
//             "method": "ajaxGetHeadImg"
//         },
//         async: true,
//         cache: false,
//         success: function (result) {
//             if (result) {
//                 $('#ImgModal').modal('show');
//             }
//         },error:function () {
//             alert("hello");
//         }
//     });
    $('#ImgModal').modal('show');
}


$(document).ready(function () {
    $("#username").blur(function () {
        var val = $("#username").val();
        var str = new RegExp(/^([a-zA-Z0-9]|[._]){5,15}$/);
        if (!str.test(val)) {
            $("#username").val(null);
            $("#username").attr(
                "placeholder", "用户名格式不对，5~15位"
            );
        }
    });
    $("#password").blur(function () {
        var val = $("#password").val();
        var str = new RegExp(/^([a-zA-Z]){1}([a-zA-Z0-9]|[._]){5,15}$/);
        if (!str.test(val)) {
            $("#password").val(null);
            $("#password").attr("placeholder", "密码请以字母开头且5~15位");
        }
    })

    $("#passwordRep").blur(function () {
        var val = $("#passwordRep").val();
        var val1 = $("#password").val();
        if (!(val1 == null) && !(val == val1)) {
            $("#passwordRep").val(null);
            $("#passwordRep").attr("placeholder", "两次密码不一致");
        }
    })

    $("#telephone").blur(function () {
        var val = $("#telephone").val();
        var str = new RegExp(/^1[3|4|5|7|8]\d{9}$/);
        if (!str.test(val)) {
            $("#telephone").val(null);
            $("#telephone").attr("placeholder", "请输入正确的手机号码");
        }
    })
    $("#email").blur(function () {
        var val = $("#email").val();
        var str = new RegExp(/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/);
        if (!str.test(val)) {
            $("#email").val(null);
            $("#email").attr("placeholder", "请输入正确的邮箱");
        }
    })
    $("#age").blur(function () {
        if (!(/^\d{1,2}$/.test($("#age").val()))) {
            $("#age").val(null);
            $("#age").attr("placeholder", "请输入正确的年龄");
        }
    })

});
