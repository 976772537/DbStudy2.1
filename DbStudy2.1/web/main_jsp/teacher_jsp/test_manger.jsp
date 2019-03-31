<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/8
  Time: 7:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WrongBook</title>
    <link rel="stylesheet" href="<c:url value="/"></c:url>/css/bootstrap.min.css ">
    <link rel="stylesheet" href="<c:url value="/"></c:url>/css/mycss.css ">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"></c:url> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
</head>
<body>
<div class="container-fluid">
    <ul class="nav nav-my bg-warning">
        <li class="bar-title"><h3>&nbsp;DbStudy&nbsp;</h3></li>
        <li><a href="<c:url value="/main_jsp/index.jsp"/>"> 首页</a></li>
        <li><a href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=mysql"/>"> 每日一练</a></li>
        <li><a href="<c:url value="/QueryDatumMainServlet?method=dispathcerToMain"/>">资料查询</a></li>
    </ul>
    <div class="row text-center index-title bg-info"style="background-image: url(<c:url value='/img/background/background_1.jpg'/>);background-position-y: 1000px">
        <h1>${user.username}发布的题目</h1>
    </div>
    <hr>
    <br>

    <%------------------------正文-----------------%>
    <div class="col-sm-8 col-sm-offset-2">
        <div class="col-sm-8">
            <form class="form-inline" action="<c:url value="/TestServlet?method=getTestByTime"></c:url> " method="post">
                <div class="input-group">
                    <div class="input-group-addon">
                        Date
                    </div>
                    <input class="form-control" type="date" name="date">
                </div>
                <input class="btn btn-info" type="submit" value="查询">
                <br>
                <br>
            </form>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>题目问题</th>
                <th>分数</th>
                <th>创建时间</th>
                <th>详细信息</th>
            </tr>
            </thead>
            <c:forEach var="test" items="${testList}" varStatus="loop">
                <tr>
                    <td>${test.question}</td>
                    <td>${test.score}</td>
                    <td>${test.time}</td>
                    <td>
                        <button style="margin-bottom: 10px;" type="button" class="btn btn-info"
                                data-toggle="modal"
                                data-target="#myModal${loop.count-1}">查看详细信息
                        </button>
                    </td>
                </tr>
                <div class="modal  fade" id="myModal${loop.count-1}" style="margin-top: 250px" abindex="-1"
                     role="dialog"
                     aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">${test.question}</h4>
                            </div>
                            <div class="modal-body">
                                    ${test.toString()}<span><img src="<c:url value="/img/fire.png"/>"
                                                                 style="width: 30px;height: 30px;"></span>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
