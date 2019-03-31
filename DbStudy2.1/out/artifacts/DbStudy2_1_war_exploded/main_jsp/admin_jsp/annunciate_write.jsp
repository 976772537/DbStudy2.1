<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/29
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="<c:url value="/css/bootstrap.min.css"/>"  rel="stylesheet">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/mycss.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/admin_mycss.css"/>" rel="stylesheet">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/>" ></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>" ></script>
    <script src="<c:url value="/js/highcharts.js"/>" ></script>
    <title>信息发布</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">管理中心</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li ><a href="<c:url value="/main_jsp/index.jsp"/> ">网站主页</a></li>
                <li ><a href="<c:url value="/main_jsp/admin_jsp/admin_index.jsp"/>">用户数据</a></li>
                <li class="active"><a href="<c:url value="/main_jsp/admin_jsp/annunciate_write.jsp"/>">信息发布</a></li>
                <li><a href="#">资源修改</a></li>
                <li><a href="#">用户封禁</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2  sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="<c:url value="/main_jsp/admin_jsp/annunciate_write.jsp"/>">公告发布<span class="sr-only">(current)</span></a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="#">教学计划</a></li>
            </ul>
        </div>
        <div class="col-sm-8 col-sm-offset-2 main">
            <form action="<c:url value="/AnnunciateServlet?method=addAnnunciate"/> " method="post">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <input class="form-control" type="text" placeholder="公告标题(10~100字)" name="title" >
                    </div>
                    <div class="panel-body">
                        <textarea class="col-sm-12 " name="context" placeholder="公告内容(0~2000字)" style="resize: none;height:200px"></textarea>
                    </div>
                    <div class="panel-footer text-center">
                        <input class="btn btn-primary" type="submit" value="&nbsp;发布公告&nbsp;">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
