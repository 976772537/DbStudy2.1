<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/18
  Time: 10:23
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
<div class="container" style="margin-top: 100px; padding: 10px">
    <div class="col-sm-9 panel-my" style="background: white">
        <div class="col-sm-8">
            <h2>文章列表</h2>
            <hr>
        </div>
        <c:forEach varStatus="loop" var="essay" items="${essay_list}">
            <div class="col-sm-8">
                <a class="col-sm-8"
                   href="<c:url value="/QueryDatumMainServlet?method=getEssayStore&essayid=${essay.essayid}"/> ">
                    <c:if test="${essay.getTitle().length()>10}">
                        <h3>${essay.getTitle().substring(0,10)}..
                            <hr>
                        </h3>
                    </c:if>
                    <c:if test="${essay.getTitle().length()<=10}">
                        <h3>${essay.getTitle()}
                            <hr>
                        </h3>
                    </c:if>
                </a>
            </div>
            <div class="col-sm-4">
                <div class="col-sm-8">
                    <c:if test="${users_list.get(loop.count-1).getName().length()>5}">
                        <h3>${users_list.get(loop.count-1).getName().substring(0,5)}..</h3>
                    </c:if>
                    <c:if test="${users_list.get(loop.count-1).getName().length()<=5}">
                        <h3>${users_list.get(loop.count-1).getName()}</h3>
                    </c:if>
                </div>
                <div class="col-sm-4">
                    <img src="<c:url value="/img/user_head/${users_head.get(loop.count-1)}"/>" class="img-circle"
                         style="width: 30px;margin-top: 20px">
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
                        href="<c:url value="/SearchEssayServlet?method=SearchEssayByTitle&cp=${pc-1}&title=${title}"/>"><span
                        aria-hidden="true">&larr;</span> 上一页</a></li>
                <li class="next"><a
                        href="<c:url value="/SearchEssayServlet?method=SearchEssayByTitle&cp=${pc+1}&title=${title}"/>">下一页
                    <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
        </nav>
        <div class="col-sm-1 " style="padding-top: 20px">
            <form action="<c:url value="/SearchEssayServlet?method=SearchEssayByTitle&title=${title}"/> " method="post">
                <input type="text" class="col-xs-12 " name="cp"
                       value="${page.currentPage}/${page.totalPage}">
            </form>
        </div>
    </div>
</div>
</body>
</html>
