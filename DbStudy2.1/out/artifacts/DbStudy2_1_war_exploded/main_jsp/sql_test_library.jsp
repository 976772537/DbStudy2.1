<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/3
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sql测试</title>
    <link rel="stylesheet" href="<c:url value="/"/>/css/bootstrap.min.css ">
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
    <script src="<c:url value="/js/myjs.js"></c:url> "></script>
    <link rel="stylesheet" href="<c:url value="/"/>/css/mycss.css ">
</head>
<body>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel">提示框</h4>
            </div>
            <div class="modal-body">
                您还尚未登陆本站，请先登陆
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        onclick="window.open('<c:url value="/main_jsp/login.jsp"/>')">登陆
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<c:if test="${empty user}">
    <script>
        $("#myModal").modal('show')
    </script>
</c:if>
<%-----------------正文----------------------%>
<div class="container-fluid bg-info"
     style="background-image: url(<c:url value='/img/background/background_1.jpg'/>);background-position-y: 1000px">
    <ul class="nav nav-my bg-warning">
        <li class="modal-title anim"><h3>&nbsp;DbStudy&nbsp;</h3></li>
        <li><a href="<c:url value="/main_jsp/index.jsp"/>"> 首页</a></li>
        <li><a href="<c:url value="/QueryDatumMainServlet?method=dispathcerToMain"/>">资料查询</a></li>
        <c:if test="${user.type eq 'teacher'}">
            <li><a href="<c:url value="/main_jsp/teacher_jsp/add_test.jsp"/>">新增题目</a></li>
            <li><a href="<c:url value="/TestServlet?method=testManger"/>">题目管理</a></li>
        </c:if>
    </ul>
    <div class="text-center" style="padding-top: 50px;padding-bottom: 20px"><h1>Sql题库</h1></div>
</div>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <ul class="nav nav-tabs">
                <li role="presentation"><a
                        href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=mysql"/>">MySql</a>
                </li>
                <li role="presentation"><a
                        href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=oracle"/>">Oracle</a>
                </li>
                <li role="presentation"><a
                        href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=mongodb"/>">MongoDB</a>
                </li>
            </ul>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-8">
            <div class="row">
                <div class="list-group">
                    <a href="#" class="list-group-item active">
                        <h3 class="list-group-item-heading">各种题...</h3>
                        <p class="list-group-item-text">...</p>
                    </a>
                    <c:forEach var="testLibrary" items="${list}">
                        <a id="testLibrary" href="<c:url value="/TestServlet?method=getTest&tid=${testLibrary.tid}"/> "
                           class="list-group-item">
                            <h4> ${testLibrary.t_name}</h4>
                        </a>
                    </c:forEach>
                </div>
            </div>
            <div class="row">
                <c:set var="pc" value="${page.currentPage}"></c:set>
                <nav class="pager col-xs-9">
                    <ul>
                        <li class="previous"><a
                                href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=${pc-1}&type=${type}"/>"><span
                                aria-hidden="true">&larr;</span> 上一页</a></li>
                        <li class="next"><a
                                href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=${pc+1}&type=${type}"/>">下一页
                            <span aria-hidden="true">&rarr;</span></a></li>
                    </ul>
                </nav>
                <form action="<c:url value="/TestLibraryServlet?method=getTestLibrary"/> " method="post">
                    <div class="col-xs-3 " style="padding-top: 20px">
                        <input type="text" class="col-xs-12 " name="cp_stl"
                               value="${page.currentPage}/${page.totalPage}">
                    </div>
                </form>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    精华
                </div>
            </div>
            <% int index = 0;%>
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <c:forEach var="hotSpot" items="${hotSpotList}">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading<%=index%>">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse<%=index%>"
                                   aria-expanded="true" aria-controls="collapse<%=index%>">
                                        ${hotSpot.question}
                                </a>
                            </h4>
                        </div>
                        <div id="collapse<%=index%>" class="panel-collapse collapse <%if(index==0){%>in<%}%>"
                             role="tabpanel"
                             aria-labelledby="heading<%=index++%>">
                            <div class="panel-body">
                                问题:${hotSpot.getQuestion()}<br>
                                选项一:${hotSpot.getAnswer1()}&nbsp;选项二:${hotSpot.getAnswer2()}&nbsp;选项三:${hotSpot.getAnswer3()}&nbsp;选项四:${hotSpot.getAnswer4()}<br>
                                答案:${hotSpot.getResult()}&emsp;分数:${hotSpot.getScore()}<br>
                                <span><img src="<c:url value="/img/fire.png"/>"
                                           style="width: 30px;height: 30px;"></span>${hotSpot.getHotspot()}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<br>
<c:if test="${!empty user}">
    <%@include file="/sub_jsp/personMenu.jsp" %>
</c:if>
<jsp:include page="/sub_jsp/footer.jsp"></jsp:include>
</body>
</html>
