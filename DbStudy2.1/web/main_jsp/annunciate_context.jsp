<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/29
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="panel panel-default" style="margin-top: 100px">
        <div class="panel-heading">
            <h3>${annunciate.title}</h3>
        </div>
        <div class="panel-body">
            <p>${annunciate.context}</p>
            <br>
            <p>${annunciate.time}</p>
        </div>
    </div>
</div>
</body>
</html>
