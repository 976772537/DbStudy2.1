<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cn.drp
  Date: 2019/2/27
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My JSP 'regist.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" href="<c:url value="/"></c:url>/css/mycss.css ">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/commons.css'/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"></c:url> "></script>
    <script src="<c:url value="/js/myjs.js"></c:url> "></script>
    <script>
        $(document).ready(function () {
            $("#username").blur(function () {
                var username = $("#username").val();
                if (!username) {
                    return ;
                }
                    $.ajax({
                        url: "<c:url value="/UserServlet"/>",
                        type: "post",
                        dataType: "json",
                        data: {
                            "method": "ajaxValidateUsername",
                            "username": username
                        },
                        async: true,
                        cache: true,
                        success: function (result) {
                            if (!result) {
                                $("#checkUser").text("√ 可以使用ヾ(ﾟ∀ﾟゞ)");
                                $("#checkUser").css({"color": "green"});
                            } else {
                                $("#checkUser").text("× 已经有人了哦");
                                $("#checkUser").css({"color": "red"});
                            }
                        },
                        error: function () {
                            $("#checkUser").text("× 查询失败");
                            $("#checkUser").css({"color": "red"});
                        }
                    });
            });
        })
    </script>
</head>
<body class="bg-gray">
<center>
    <form action="<c:url value='/UserServlet?method=regist'/>" method="post">
        <table class="registContext" style="box-shadow: 1px 2px 3px black" cellpadding="10px" cellspacing="0">
            <c:if test="${!empty regist_error }">
                <script>
                    alert("${regist_error}");
                </script>
            </c:if>
            <tr>
                <td colspan="3">
                    <center><h3>注册界面</h3></center>
                </td>
            </tr>
            <tr>
                <td><label>用户名：</label></td>
                <td colspan="2">&nbsp;<input type="text" id="username" name="username" value="${user.username }"></td>
            </tr>
            <tr>
                <td colspan="3"><h3><label><span id="checkUser"></span></label></h3></td>
            </tr>
            <tr>
                <td><label>密&emsp;码：</label></td>
                <td colspan="2">&nbsp;<input id="password" type="password" name="password" value="${user.password }">
                </td>
            </tr>
            <tr>
                <td><label>重复密码：</label></td>
                <td colspan="2">&nbsp;<input id="passwordRep" type="password" name="password" value="${user.password }">
                </td>
            </tr>
            <tr>
                <td><label>昵称：</label></td>
                <td colspan="2">&nbsp;<input type="text" id="name" name="name" value="${user.name }"></td>
            </tr>
            <tr>
                <td><label>性&emsp;别：</label></td>
                <td>&nbsp;<input type="radio" name="sex" value="man">男</td>
                <td>&nbsp;<input type="radio" name="sex" value="woman">女</td>
            </tr>
            <tr>
                <td><label>电话话码：</label></td>
                <td colspan="2">&nbsp;<input type="text" id="telephone" name="telephone" value="${user.telephone}"></td>
            </tr>
            <tr>
                <td><label>邮箱：</label></td>
                <td colspan="2">&nbsp;<input type="text" id="email" name="email" value="${user.email}"></td>
            </tr>
            <tr>
                <td><label>年&emsp;龄：</label></td>
                <td colspan="2">&nbsp;<input type="text" id="age" name="age" value="${user.age}"></td>
            </tr>
            <tr>
                <td><label>描&emsp;述：</label></td>
                <td colspan="2">&nbsp;<input type="text" id="describle"  name="describle" value="${user.describle}"></td>
            </tr>
            <c:if test="${!empty verif_error}">
                <tr>
                    <td colspan="3" class="error">${verif_error}</td>
                </tr>
                </tr>
            </c:if>
            <tr>
                <td>验证码:</td>
                <td><input type="text" name="verifyCode" size="3"></td>
                <td><img id="image" src='<c:url value="/" ></c:url>VerifyCodeServlet?send=regist' alt="VerifyCode"></td>
            </tr>
            <tr>
                <td colspan="3" style="text-align: right;"><a href="javascript:changeImage()">看不清，换一张</a></td>
                <script type="text/javascript">
                    function changeImage() {
                        var image = document.getElementById("image");
                        image.src = "<c:url value="/" ></c:url>VerifyCodeServlet?a=" + new Date().getMilliseconds() + "&send=regist";
                    }
                </script>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" class="btn" value="注册">
                    <input type="reset" class="btn" value="重置">
                </td>
            </tr>
        </table>
    </form>
</center>

</body>
</html>
