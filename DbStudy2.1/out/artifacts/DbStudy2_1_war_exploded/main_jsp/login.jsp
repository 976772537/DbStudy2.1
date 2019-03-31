<%--
  Created by IntelliJ IDEA.
  User: cn.drp
  Date: 2019/2/27
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico" tppabs="http://v3.bootcss.com/favicon.ico">
    <title>My JSP 'login.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <link rel="stylesheet" href="<c:url value="/"/>/css/bootstrap.min.css ">
    <link rel="stylesheet" href="<c:url value="/"/>/css/mycss.css ">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
</head>

<body class="bg-gray">
<%
    String username = "";
    String password = "";
    if (request.getCookies () != null) {
        Cookie[] cookies = request.getCookies ();
        for (Cookie ck : cookies) {
            if (ck.getName ().equalsIgnoreCase ("username")) {
                username = ck.getValue ();
            }
            if (ck.getName ().equalsIgnoreCase ("password")) {
                password = ck.getValue ();
            }

        }
    }
%>
<div class="container">
    <div class="col-sm-offset-4 col-sm-4 bg-info" style="margin-top: 200px;border-radius: 10px; box-shadow: 1px 2px 3px">
       <br>
        <form action="<c:url value="/" />UserServlet?method=login" method="post" class="form-signin">
            <h2 class="form-signin-heading text-center">请登录</h2>
            <c:if test="${!empty login_error}">
                <h4 class="text-danger text-center">${login_error}</h4>
                <br>
            </c:if>
            <label for="inputUsername" class="sr-only">用户名</label>
            <input type="text" id="inputUsername" class="form-control" placeholder="username" required autofocus
                   name="username"
            <c:choose>
                   <c:when test="${!empty user.username}">value="${user.username}"</c:when>
                   <c:otherwise>value="<%=username%>"
            </c:otherwise>
            </c:choose> >
            <br>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required
                   name="password"
            <c:choose>
                   <c:when test="${!empty user.password}">value="${user.password}"</c:when>
                   <c:otherwise>value="<%=password%>"
            </c:otherwise>
            </c:choose> >
            <c:if test="${!empty verif_error}">
                <h4 colspan="3" class="text-danger text-center">${verif_error}</h4>
            </c:if>
            <br>
            <input type="text" class="input-sm" name="verifycode" size="4" placeholder="验证码">&nbsp;&nbsp;
            <img id="image" src="<c:url value="/" ></c:url>VerifyCodeServlet?send=login" alt="VerifyCode">&emsp;
           <a href="javascript:changeImage()">看不清,换一张</a>
            <script type="text/javascript">
                function changeImage() {
                    var image = document.getElementById("image");
                    image.src = "<c:url value="/" />VerifyCodeServlet?a="
                        + new Date().getMilliseconds()
                        + "&send=login";
                }
            </script>
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="remmber" > 记住密码
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
            <button class="btn btn-lg btn-primary btn-block"
                    onclick="window.open('<c:url value="/main_jsp/regist.jsp"/>')"
                    class="btn">注册
            </button>
        </form>
        <br>
    </div>
</div>

</body>
</html>
