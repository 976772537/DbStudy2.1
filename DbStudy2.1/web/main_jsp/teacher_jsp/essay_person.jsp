<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/30
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的文章</title>
    <link rel="stylesheet" href="<c:url value="/"/>/css/bootstrap.min.css ">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
    <script src="<c:url value="/js/myjs.js"/> "></script>
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>">
</head>
<body class="bg-gray">
<div class="container-fluid">
    <ul class="nav nav-my bg-warning">
        <li class="bar-title"><h3>&nbsp;DbStudy&nbsp;</h3></li>
        <li><a href="<c:url value="/main_jsp/index.jsp"/>"> 首页</a></li>
        <li><a href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=mysql"/>"> 每日一练</a></li>
        <li><a href="<c:url value="/QueryDatumMainServlet?method=dispathcerToMain"/>">资料查询</a></li>
        <li><a href="<c:url value="/WrongBookServlet?method=getWrongBook"/>">错题本</a></li>
    </ul>
    <div class="row text-center index-title bg-info"
         style="background-image: url(<c:url value='/img/background/background_1.jpg'/>);background-position-y: 1000px">
        <h1>${user.username}的个人主页</h1>
    </div>
</div>
<div class="container" style="margin-top: 100px; padding: 10px">
    <div class="col-sm-9 panel-my" style="background: white">
        <div class="col-sm-8">
            <h2>文章列表</h2>
            <hr>
        </div>
        <c:forEach varStatus="loop" var="essay" items="${essay_list}">
            <div class="row">
                <div class="col-sm-12">
                    <a class="col-sm-8"
                       href="<c:url value="/QueryDatumMainServlet?method=getEssayStore&essayid=${essay.essayid}"/> ">
                        <h3>
                            <div class="col-sm-8">
                            <c:if test="${essay.getTitle().length()>10}">
                                ${essay.getTitle().substring(0,10)}..
                            </c:if>
                            <c:if test="${essay.getTitle().length()<=10}">
                                ${essay.getTitle()}
                            </c:if>
                            </div>
                            <div class="col-sm-4">
                                <small>${essay.time.substring(0,essay.time.lastIndexOf(' '))}</small>
                            </div>
                        </h3>
                    </a>
                    <form action="<c:url value="/EssayServlet?method=deleteEssayByEssayid&essayid=${essay.essayid}"/>"
                          method="post">
                        <input class="col-sm-2 btn btn-primary" type="submit" value="删除">
                    </form>
                </div>
            </div>
            <hr>
            <br>
        </c:forEach>
    </div>
</div>
<br>
<br>
<%@include file="/sub_jsp/footer.jsp"%>
<c:if test="${!empty delete_success}">
    <script>
        alert("${delete_success}");
    </script>
</c:if>
<c:if test="${!empty delete_error}">
    <script>
        alert(${delete_error});
    </script>
</c:if>
</body>
</html>
