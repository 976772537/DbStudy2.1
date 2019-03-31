<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/31
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/> ">
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
</head>
<body class="bg-gray">
<%@include file="/sub_jsp/nav.jsp" %>
<div class="col-sm-1  list-group" style="margin-top: 50px; margin-left: 50px; margin-right: 100px;">
    <a href="<c:url value="/main_jsp/teacher_jsp/write_essay.jsp"/> " class="list-group-item">文章投稿</a>
    <a href="<c:url value="/main_jsp/teacher_jsp/up_datum.jsp"/> " class="list-group-item ">资料上传</a>
    <a href="<c:url value="/main_jsp/teacher_jsp/up_video.jsp"/> " class="list-group-item active">视频上传</a>
    <a href="<c:url value="/main_jsp/teacher_jsp/add_test.jsp"/> " class="list-group-item">习题上传</a>
    <a href="<c:url value="/EssayServlet?method=getEssayForPerson"></c:url> " class="list-group-item">我的投稿</a>
</div>
<c:if test="${!empty success}">
    <script>
        alert("${success}");
    </script>
</c:if>
<div class="col-sm-5">
    <div class="panel panel-default" style="margin-top: 50px">
        <div class="panel-body">
            <form action="<c:url value="/UpVideoServlet?method=writeEssay"/> " method="post" enctype="multipart/form-data">
                <div class="col-sm-12 text-center">
                    <h1>投稿中心&nbsp;<small>视频上传</small></h1>
                    <br>
                </div>
                <script>
                    function imgUpLoad() {
                        $("#upfile").click();
                    }
                </script>
                <div class="row">
                    <input type="file" id="upfile" style="display: none" name="essayimg">
                    <div id="upfile-div" class="col-sm-12 text-center img-upload" onclick="imgUpLoad()" style="padding:100px;">
                        <img src="<c:url value="/img/video.png"/> " style="width: 100px;height: 75px;"
                        ><br>
                        <h1>
                            <small>添加上传视频</small>
                        </h1>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-12">
                        <input class="form-control" type="text" name="title" placeholder="请输入视频名(建议30个字以内)">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <h3>主要内容:</h3>
                        <textarea class="col-sm-12" name="body"
                                  style="resize:none;height:150px;background:#fbfafa"
                                  placeholder="请输入视频描述(限制200~2000字)"></textarea>
                    </div>
                </div>
                <hr>
                <div class="col-sm-12 text-center">
                    <input class="btn btn-primary" type="submit" value="&nbsp;提交&nbsp;">
                </div>
            </form>
        </div>
    </div>
</div>
<c:if test="${!empty write_error}">
    <script>
        alert("${write_error}");
    </script>
</c:if>
<c:if test="${!empty img_error}">
    <script>
        alert("${img_error}");
    </script>
</c:if>
</body>
</html>

