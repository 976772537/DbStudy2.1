<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/17
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-inverse" style="margin-bottom: 0">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">DbStudy</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/main_jsp/index.jsp"/>">首页<span class="sr-only">(current)</span></a></li>
                <li><a href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=mysql"/>">每日一练</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">功能
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<c:url value="/QueryDatumMainServlet?method=dispathcerToMain"/>">社区主页</a></li>
                        <li><a href="<c:url value="/AnnunciateServlet?method=getAnnunciatePage&cp=1"/>">公告板</a></li>
                        <li class="divider"></li>
                        <li><a href="#">资料中心</a></li>
                        <li class="divider"></li>
                        <li><a href="#">每日一问</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search" action="<c:url value="/SearchEssayServlet?method=SearchEssayByTitle&cp=1"/>" method="post">
            <div class="form-group">
            <input type="text" class="form-control" placeholder="站内查找文章" name="title">
            </div>
            <button type="submit" class="btn btn-default">查找</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty user}">
                    <li><a href="<c:url value="/main_jsp/login.jsp"/>">登陆</a></li>
                    <li><a href="<c:url value="/main_jsp/regist.jsp"/>">注册</a></li>
                </c:if>
                <c:if test="${!empty user}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">个人首页<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="<c:url value="/HeadImgStoreServlet?method=getHeadImg"></c:url>">个人主页</a></li>
                            <c:if test="${user.type eq 'teacher'}">
                                <li><a href="<c:url value="/main_jsp/teacher_jsp/write_essay.jsp"/> ">发表文章</a> </li>
                            </c:if>
                            <li class="divider"></li>
                            <li><a href="<c:url value="/WrongBookServlet?method=getWrongBook"/>">错题本</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
