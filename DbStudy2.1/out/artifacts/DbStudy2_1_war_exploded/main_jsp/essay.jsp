<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/16
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/"/>/css/bootstrap.min.css ">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
    <script src="<c:url value="/js/myjs.js"/> "></script>
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>">
</head>
<body class="bg-gray">
<%@include file="/sub_jsp/nav.jsp" %>
<div class="container">
    <div class="col-sm-8 panel-my" style=" margin-top: 100px;">
        <div class="col-sm-12 text-center"><br><br>
            <h2>${essay.title}</h2><br></div>
        <div class="col-sm-8"><h4>作者:${essay_writer.name}&nbsp;
            <img class="img-rounded" src="<c:url value="/img/user_head/${essay_writer_head}"/>" style="width: 50px">
        </h4></div>
        <div class="col-sm-4 text-right"><span><img src="<c:url value="/img/fire.png"/>"
                                                    style="width: 30px;height: 30px;"></span>${essay.hotSpot}
        </div>
        <div class="col-sm-12" style="margin: 10px 0">
        <c:if test="${essay.filetype eq 'video'}">
            <div class="embed-responsive embed-responsive-16by9">
                <video class="embed-responsive-item" src="<c:url value="/file/video/${filepath}"/>" controls="controls"></video>
            </div>
        </c:if>
        <c:if test="${essay.filetype eq 'file'}">
            <a href="<c:url value="/file/datum/${filepath}"/> "><h3>点我下载！！</h3></a>
        </c:if>
        </div>
        <div class="col-sm-12">${essay.body}<br><br><br></div>
        <div class="col-sm-12 text-right">${essay.time}<br><br></div>
        <div class="col-sm-12 text-center">
            <button type="button" class="btn btn-default btn-sm" style="margin-bottom: 40px"
                    onclick="giveThumbs('${essay.essayid}')">
                <span aria-hidden="true"><img src="<c:url value="/img/thumbs.png"/>" style="width: 25px"></span> 点赞
            </button>
        </div>
    </div>
    <div class="col-sm-8 panel-my" style="margin-top: 40px">
        <div class="col-sm-12 text-center">
            <br><h4>评论区</h4>
        </div>
        <form action="<c:url value="/CommentServlet?method=getContent&essayid=${essay.essayid}"/> " method="post">
            <div class="col-sm-12 text-center">
                <textarea class="col-sm-12" name="content"
                          style="resize:none;height:100px;background:#fbfafa"></textarea>
            </div>
            <div class="col-sm-12 text-center">
                <script>
                    function notLogin() {
                        alert("尚未登录,请先登陆");
                    }
                </script>
                <input <c:if test="${!empty user}"> type="submit"</c:if>
                        <c:if test="${empty user}"> type="button" onclick="notLogin();" </c:if>class="btn btn-primary"
                        style="margin-top:10px;margin-bottom: 20px" value="&nbsp;提交&nbsp;"/>
            </div>
        </form>
    </div>
    <c:forEach items="${users_list}" var="user" varStatus="loop">
        <div class="col-sm-8 panel-my" style="margin-top: 30px;padding: 20px">
            <div class="col-sm-12">
                <img class="img-circle"
                     src="<c:url value="/img/user_head/${user.getHead()}"/>"
                     style="width: 30px">&nbsp;&nbsp;${user.getName()}
            </div>
            <hr>
            <div class="col-sm-12">
                ${user.getComment().content}<br>${user.getComment().time}
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
