<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/2
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>query</title>
    <link rel="stylesheet" href="<c:url value="/"/>/css/bootstrap.min.css ">
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
    <%--<script src="<c:url value="/js/myjs.js"></c:url> "></script>--%>
    <%--<link rel="stylesheet" href="<c:url value="/"/>/css/mycss.css ">--%>
</head>
<body>
<%@include file="/sub_jsp/nav.jsp" %>
<div class="jumbotron"
     style="background-image: url(<c:url value='/img/background/background_1.jpg'/>);background-position-y: 1000px">
    <div class="container">
        <div class="row text-center">
            <br>
            <h1>DbStudy社区主页</h1>
            <br>
        </div>
    </div>
</div>
<br>
<div class="row">
    <div class="col-sm-12 text-center">
        <h2>最新动态</h2>
    </div>
</div>
<br>
<div class="container-fluid" style="margin-bottom: 50px">
    <div class="col-sm-offset-2 col-sm-8">
        <c:forEach var="essay" items="${hot_essay_list}" varStatus="loop">
        <c:if test="${loop.count%4 eq 0}">
        <div class="row">
            </c:if>
            <div class="col-md-3">
                <div class="thumbnail">
                    <c:if test="${empty essay.essayImgPath}">
                        <img src="<c:url value="/img/background/background_1.jpg"/> " alt="缩略图-1">
                    </c:if>
                    <c:if test="${!empty essay.essayImgPath && essay.filetype eq 'img'}">
                        <img id="img${loop.count}" src="<c:url value="/img/essay/${essay.essayImgPath.get(0)}"/> "
                             alt="缩略图-1">
                        <script>
                            if ($("#img${loop.count}").height() > 180) {
                                $("#img${loop.count}").css({height: "180px"});
                            }
                        </script>
                    </c:if>
                    <c:if test="${!empty essay.essayImgPath && essay.filetype eq 'video'}">
                        <div id="video">
                            <a href="<c:url value="/QueryDatumMainServlet?method=getEssayStore&essayid=${essay.essayid}"/> ">
                                <img id="video-img"
                                     style="width: 65%;margin-left:10%;float:left;position: absolute;z-index: 5;display: none"
                                     src="<c:url value="/img/video.png"/> ">
                                <div class="embed-responsive embed-responsive-16by9">
                                    <video class="embed-responsive-item"
                                           src="<c:url value="/file/video/${essay.essayImgPath.get(0)}"/>"
                                           alt="缩略图-1"></video>
                                </div>
                            </a>
                        </div>
                        <script>
                            $("#video").mouseenter(function () {
                                    $("#video-img").show();
                                }
                            );

                            $("#video").mouseleave(function () {
                                $("#video-img").fadeOut("slow")
                            });
                        </script>
                    </c:if>
                    <c:if test="${!empty essay.essayImgPath && essay.filetype eq 'file'}">
                        <img src="<c:url value="/img/background/background_1.jpg"/> " alt="缩略图-1">
                    </c:if>
                    <div class="caption">
                        <c:if test="${essay.title.length()>10}">
                            <h3>${essay.title.substring(0,10)}...</h3>
                        </c:if>
                        <c:if test="${essay.title.length()<=10}">
                            <h3>${essay.title}</h3>
                        </c:if>
                        <c:if test="${essay.body.length() > 30}">
                            <p>${essay.body.substring(0,30)}...</p>
                        </c:if>
                        <c:if test="${essay.body.length() <= 30}">
                            <p>${essay.body}${essay_error}</p>
                        </c:if>
                        <p>
                            <a href="<c:url value="/QueryDatumMainServlet?method=getEssayStore&essayid=${essay.essayid}"/> "
                               class="btn btn-primary" role="button">了解详情</a></p>
                    </div>
                </div>
            </div>
            <c:if test="${loop.count%4 eq 0}">
            </div>
            </c:if>
            </c:forEach>
        </div>
        <div class="col-sm-1">
            <div class="panel panel-default">
                <div class="panel-body">
                    用户排行榜
                </div>
            </div>
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <c:forEach var="user" items="${user_rank_list}" varStatus="loop">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading${loop.count}">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse${loop.count}"
                                   aria-expanded="false"
                                   aria-controls="collapse${loop.count}">
                                    <div class="row">
                                        <div class="col-sm-9 text-left" style="padding-right: 0;">
                                            <c:if test="${user.name.length()>4}">
                                                ${user.name.substring(0,4)}..
                                            </c:if>
                                            <c:if test="${user.name.length()<=4}">
                                                ${user.name}
                                            </c:if>
                                        </div>
                                        <div class="col-sm-3 text-center" style="padding: 0;">
                                            <img src="<c:url value="/img/user_head/${user_Head.get(loop.count-1)}"/> "
                                                 alt="用户头像"
                                                 class="img-circle" style="width:20px;">
                                        </div>
                                    </div>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse${loop.count}" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="heading${loop.count}">
                            <div class="panel-body">
                                    ${user.describle}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <c:if test="${!empty user}">
        <%@include file="/sub_jsp/personMenu.jsp" %>
    </c:if>
    <%@include file="/sub_jsp/footer.jsp" %>
</body>
</html>
