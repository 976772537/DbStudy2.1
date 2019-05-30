<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/20
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新增题目</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/> ">
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
    <script>
        $(document).ready(function () {
            $("#testClass").change(function () {
                $("option").remove("#tl");
                getTestLib();
            });
        });

        function getTestLib() {
            var lib = $("#testClass option:selected").val();
            $.get({
                url: "<c:url value="/TestLibraryServlet?method=ajaxGetTestLib&testClass="/>" + lib,
                type: "post",
                success(data) {
                    var arr = data.split(":");

                    for (var i = 0; i < arr.length; i++) {
                        $("#testLib").append("<option id='tl' value='" + arr[i] + "'>" + arr[i] + "</option>");
                    }
                }
            });
        }
    </script>
</head>
<body class="bg-gray">
<%@ include file="/sub_jsp/nav.jsp" %>
<div class="col-sm-1  list-group" style="margin-top: 50px; margin-left: 50px; margin-right: 100px;">
    <a href="<c:url value="/main_jsp/teacher_jsp/write_essay.jsp"/> " class="list-group-item">文章投稿</a>
    <a href="<c:url value="/main_jsp/teacher_jsp/up_datum.jsp"/> " class="list-group-item">资料上传</a>
    <a href="<c:url value="/main_jsp/teacher_jsp/up_video.jsp"/> " class="list-group-item">视频上传</a>
    <a href="<c:url value="/main_jsp/teacher_jsp/add_test.jsp"/> " class="list-group-item active">习题上传</a>
    <a href="<c:url value="/EssayServlet?method=getEssayForPerson"></c:url> " class="list-group-item">我的投稿</a>
</div>
<c:if test="${!empty success}">
    <script>
        alert("${success}");
    </script>
</c:if>
<c:if test="${!empty error}">
    <script>
        alert("${error}");
    </script>
</c:if>
<form action="<c:url value="/TestServlet?method=addTest"/> " method="post">
    <div class="container">
        <div class="col-sm-8 panel panel-default" style="margin-top: 100px">
            <div class="panel-body">
                <div class="row text-center">
                    <h1>添加新题目</h1>
                </div>
                <div class="row" style="margin-bottom: 20px">
                    <div class="input-group">
                        <span class="input-group-addon">题库类型</span>
                        <select id="testClass" class="form-control" name="testClass">
                            <option selected></option>
                            <option value="mysql">Mysql</option>
                            <option value="oracle">Oracle</option>
                            <option value="mongodb">MongoDB</option>
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 20px">
                    <div class="input-group">
                        <span class="input-group-addon">题库</span>
                        <select id="testLib" class="form-control" name="testLib"></select>
                    </div>
                </div>
                <div class="row">
                    <input type="text" class="form-control" placeholder="问题内容" name="question">
                    <br>
                </div>
                <div class="row">
                    <input type="text" class="form-control" placeholder="选项A" name="answer1">
                    <br>
                </div>
                <div class="row">
                    <input type="text" class="form-control" placeholder="选项B" name="answer2">
                    <br>
                </div>
                <div class="row">
                    <input type="text" class="form-control" placeholder="选项C" name="answer3">
                    <br>
                </div>
                <div class="row">
                    <input type="text" class="form-control" placeholder="选项D" name="answer4">
                    <br>
                </div>
                <div class="row">
                    <input type="test" class="form-control" placeholder="答案" name="result">
                    <br>
                </div>
                <div class="row">
                    <input type="number" class="form-control" placeholder="分数" name="score">
                    <br>
                </div>
                <div class="row text-center">
                    <input type="submit" class=" btn btn-primary" value="&emsp;提交&emsp;">
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
