<%@ page import="junit.test" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/2
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>单选测试</title>
    <link rel="stylesheet" href="<c:url value="/"></c:url>/css/bootstrap.min.css ">
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"></c:url> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
    <script src="<c:url value="/js/myjs.js"></c:url> "></script>
    <link rel="stylesheet" href="<c:url value="/"></c:url>/css/mycss.css ">
</head>
<body>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                您还尚未登陆本站，请先登陆
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        onclick="window.open('<c:url value="/main_jsp/login.jsp"/>')">登陆
                </button>
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
<div class="container bg-info"style="background-image: url(<c:url value='/img/background/background_1.jpg'/>);background-position-y: 1000px">
    <ul class="nav nav-my bg-warning">
        <li class="modal-title anim"><h3>&nbsp;DbStudy&nbsp;</h3></li>
        <li><a href="<c:url value="/main_jsp/index.jsp"/>"> 首页</a></li>
        <li><a href="<c:url value="/QueryDatumMainServlet?method=dispathcerToMain"/>">资料查询</a></li>
    </ul>
    <c:choose>
        <c:when test="${score lt 50}">
            <div class="text-center" style="padding-top: 50px;padding-bottom: 20px"><h1>总分为：${score} (;´༎ຶД༎ຶ`)</h1>
            </div>
        </c:when>
        <c:when test="${score gt 50 && score lt 80}">
            <div class="text-center" style="padding-top: 50px;padding-bottom: 20px"><h1>总分为：${score} ❥(ゝω・✿ฺ)</h1></div>
        </c:when>
        <c:when test="${score gt 80 && score lt 100}">
            <div class="text-center" style="padding-top: 50px;padding-bottom: 20px"><h1>总分为：${score} ヾ(✿ﾟ▽ﾟ)ノ</h1></div>
        </c:when>
        <c:when test="${score >= 100}">
            <div class="text-center" style="padding-top: 50px;padding-bottom: 20px"><h1>总分为：${score} ヾ(◍°∇°◍)ﾉﾞ 太棒了</h1>
            </div>
        </c:when>
        <c:otherwise>
            <div class="text-center" style="padding-top: 50px;padding-bottom: 20px"><h1>开始测试</h1></div>
        </c:otherwise>
    </c:choose>
</div>
<br>
<br>
<c:choose>
    <c:when test="${list.size() eq 10}">
        <div class="container ">
            <form action="<c:url value="/TestServlet?method=getResult"></c:url>" method="post">
                <% int co = 0;%>
                <c:forEach var="test" items="${list}">
                    <div class="panel panel-group panel-default pan">
                        <div class="panel-title bg-gray">
                            <h3 style="margin-top:0; padding-top:30px;margin-left: 10px; padding-right: 0">
                                <label>${test.question}</label></h3>
                            <c:if test="${!empty errorAnswer || !empty rightAnswer}">
                                <button style="margin-bottom: 10px;" type="button" class="btn btn-info col-sm-offset-10"
                                        data-toggle="modal"
                                        data-target="#myModal<%=co%>">查看正确答案
                                </button>
                            </c:if>
                        </div>
                        <div class="panel-body ">
                            <div class="radio choice">
                                <h4 id="answer<%=co%>A"><label>&nbsp;<input type="radio" id="answer<%=co%>AR"
                                                                            name="answer<%=co%>"
                                                                            value="A">&nbsp;A.${test.answer1}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</label>
                                </h4>
                            </div>
                            <div class="radio choice">
                                <h4 id="answer<%=co%>B"><label>&nbsp;<input type="radio" id="answer<%=co%>BR"
                                                                            name="answer<%=co%>"
                                                                            value="B">&nbsp;B.${test.answer2}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</label>
                                </h4>
                            </div>
                            <div class="radio choice">
                                <h4 id="answer<%=co%>C"><label>&nbsp;<input type="radio" id="answer<%=co%>CR"
                                                                            name="answer<%=co%>"
                                                                            value="C">&nbsp;C.${test.answer3}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</label>
                                </h4>
                            </div>
                            <div class="radio choice">
                                <h4 id="answer<%=co%>D"><label>&nbsp;<input type="radio" id="answer<%=co%>DR"
                                                                            name="answer<%=co%>"
                                                                            value="D">&nbsp;D.${test.answer4}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                </label>
                                </h4>
                            </div>
                        </div>
                    </div>
                    <div class="modal  fade" id="myModal<%=co%>" style="margin-top: 250px" abindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">${test.question}</h4>
                                </div>
                                <div class="modal-body">
                                        ${test.toString()}
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:addWrongBook('${test.sid}')">加入错题本</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%co++;%>
                </c:forEach>
                <div class="row col-sm-offset-8  ">
                    <input type="submit" class="btn btn-primary btn-lg col-xs-offset-1" value="提交答案">
                    <input type="reset" class="btn btn-warning  btn-lg col-xs-offset-3" value="重置选项">
                </div>
            </form>
            <script>
                <c:forEach var="rigthAnswer" items="${rightAnswer}" >
                $("#${rigthAnswer}").addClass("bg-success");
                </c:forEach>
                <c:forEach var="errorAnswer" items="${errorAnswer}" >
                $("#${errorAnswer}").addClass("bg-danger");
                </c:forEach>
            </script>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container">
            <div class="col-sm-12 text-center">
                <br><br>
                <h1>没有题了,Σσ(・Д・；)我我我什么都没做!!!</h1>
            </div>
        </div>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </c:otherwise>
</c:choose>
<br>
<br>
<c:if test="${!empty user}">
    <%@include file="/sub_jsp/personMenu.jsp"%>
</c:if>
<jsp:include page="/sub_jsp/footer.jsp"></jsp:include>
</body>
</html>
