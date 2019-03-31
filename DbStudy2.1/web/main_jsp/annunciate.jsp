<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/29
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Annunciate</title>
    <link rel="stylesheet" href="<c:url value="/"/>/css/bootstrap.min.css ">
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
</head>
<body>
<%@include file="/sub_jsp/nav.jsp" %>
<div class="container" style="margin-top: 100px; padding: 10px">
    <div class="col-sm-9 panel-my" style="background: white">
        <div class="col-sm-8">
            <h2>告示栏</h2>
            <hr>
        </div>
        <c:forEach varStatus="loop" var="annunciate" items="${annunciatelist}">
            <div class="row">
                <a href="<c:url value="/AnnunciateServlet?method=getAnnunciateByAnnID&id=${annunciate.ann_id}"/> ">
                    <h3>
                        <div class="col-sm-8">
                            <c:if test="${annunciate.getTitle().length()>10}">
                                ${annunciate.getTitle().substring(0,10)}..
                            </c:if>
                            <c:if test="${annunciate.getTitle().length()<=10}">
                                ${annunciate.getTitle()}
                            </c:if>
                        </div>
                        <div class="col-sm-4">
                            <small>${annunciate.time.substring(0,annunciate.time.lastIndexOf(' '))}</small>
                        </div>
                    </h3>
                </a>
            </div>
            <br>
            <div class="row">
                <div class="col-sm-8">
                    <c:if test="${annunciate.context.length()>30}">
                        ${annunciate.context.substring(0,30)}..
                    </c:if>
                    <c:if test="${annunciate.context.length()<=30}">
                        ${annunciate.context}
                    </c:if>
                    <hr>
                </div>
            </div>
            <br>
        </c:forEach>
    </div>
    <div class="col-sm-3" style="background: whitesmoke">

    </div>
    <div class="row">
        <c:set var="pc" value="${page.currentPage}"></c:set>
        <nav class="pager col-sm-8">
            <ul>
                <li class="previous"><a
                        href="<c:url value="/AnnunciateServlet?method=getAnnunciatePage&cp=${pc-1}"/>"><span
                        aria-hidden="true">&larr;</span> 上一页</a></li>
                <li class="next"><a
                        href="<c:url value="/AnnunciateServlet?method=getAnnunciatePage&cp=${pc+1}"/>">下一页
                    <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
        </nav>
        <div class="col-sm-1 " style="padding-top: 20px">
            <form action="<c:url value="/AnnunciateServlet?method=getAnnunciatePage&title=${title}"/> " method="post">
                <input type="text" class="col-xs-12 " name="cp"
                       value="${page.currentPage}/${page.totalPage}">
            </form>
        </div>
    </div>
</div>
</body>
</html>
