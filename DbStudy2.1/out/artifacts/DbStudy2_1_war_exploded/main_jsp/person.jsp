<%@ page import="cn.drp.user.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/2/28
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>
    <link rel="stylesheet" href="<c:url value="/"></c:url>/css/bootstrap.min.css ">
    <link rel="stylesheet" href="<c:url value="/"></c:url>/css/mycss.css ">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"></c:url> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
    <script src="<c:url value="/js/myjs.js"></c:url> "></script>
</head>
<body>
<div class="container-fluid">
    <ul class="nav nav-my bg-warning">
        <li class="bar-title"><h3>&nbsp;DbStudy&nbsp;</h3></li>
        <li><a href="<c:url value="/main_jsp/index.jsp"/>"> 首页</a></li>
        <li><a href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=mysql"/>"> 每日一练</a></li>
        <li><a href="<c:url value="/QueryDatumMainServlet?method=dispathcerToMain"/>">资料查询</a></li>
        <li><a href="<c:url value="/WrongBookServlet?method=getWrongBook"/>">错题本</a></li>
        <c:if test="${user.type eq 'teacher'}">
            <li><a href="<c:url value="/EssayServlet?method=getEssayForPerson"/> ">我的文章</a></li>
        </c:if>
    </ul>
    <div class="row text-center index-title bg-info"style="background-image: url(<c:url value='/img/background/background_1.jpg'/>);background-position-y: 1000px">
        <h1>${user.username}的个人主页</h1>
    </div>
</div>
<form action="<c:url value="/UserServlet?method=updateUser"/>" method="post">
    <table class="table table-striped ">

        <tr>
            <td class="text-right"></td>
            <td class="col-xs-6 "><img id="imghead" src="<c:url value="/img/user_head/${userhead}"/> "
                                       onclick="getHeadImg();" alt="用户头像" class="img-circle" style="width: 150px;"></td>
        </tr>
        <tr>
            <td class="text-right">用户名:</td>
            <td class="col-xs-6 "><input type="text" name="username" readonly value="${user.username}"></td>
        </tr>
        <tr>
            <td class="text-right">昵称:</td>
            <td><input type="text" name="name" value="${user.name}"></td>
        </tr>
        <tr>
            <td class="text-right">性别:</td>
            <td><input type="text" name="sex" readonly value="${user.sex}"></td>
        </tr>
        <tr>
            <td class="text-right">电话号码:</td>
            <td><input type="text" id="telephone" name="telephone" value="${user.telephone}"></td>
        </tr>
        <tr>
            <td class="text-right">邮箱:</td>
            <td><input type="email" name="email" value="${user.email}"></td>
        </tr>
        <tr>
            <td class="text-right">年龄:</td>
            <td><input type="text" id="age" name="age" value="${user.age}"></td>
        </tr>
        <tr>
            <td class="text-right">自我介绍:</td>
            <td><textarea name="describle" style="resize: none;width:200px;height: 100px">${user.describle}</textarea>
            </td>
        </tr>
        <tr>
            <td class="text-right "><br><input class="btn btn-info" type="submit" value="保存"></td>
            <td class="text-left"><br><input class="btn btn-info" type="reset" value="重置" style="margin-left: 15%"></td>
        </tr>
    </table>
</form>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                ${update_error}${update_success}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<c:if test="${!empty update_error or !empty update_success}">
    <script>
        $("#myModal").modal('show');
    </script>
</c:if>
<c:if test="${!empty changeimg_error}">
    <script>
        alert("${changeimg_error}");
    </script>
</c:if>
<c:if test="${!empty changeimg_success}">
    <script>
        alert("${changeimg_success}");
    </script>
</c:if>

<div class="modal fade" id="ImgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="margin-top: 300px">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="ImgLabel">头像页</h4>
            </div>
            <div class="modal-body">
                <c:forEach var="img" items="${headlist}" varStatus="loop">
                    &emsp;&emsp;<a href="<c:url
                        value="/HeadImgStoreServlet?method=changeHeadImg&imgPath=${img.imgPath}"/> "><img id="img" src="<c:url
                        value="/img/user_head/${img.imgPath}"/> "
                    alt="用户头像" class="img-circle" style="width:75px;margin-top: 10px;margin-left: 30px"></a>
                </c:forEach>
            </div>
            <div class="modal-footer">
                <form action="<c:url value="/HeadImgStoreServlet?method=userHeadUpLoad"/>" method="post"
                      enctype="multipart/form-data">
                    <div class="row">
                        <div class="btn-group btn-group-justified" role="group">
                            <div class="btn-group col-sm-7" role="group">
                                <input class="btn-default btn" type="file" name="fileName">
                            </div>
                            <div class="btn-group col-sm-5" role="group">
                                <input class="btn-primary btn" type="submit" value="上传">
                            </div>
                            <div class="btn-group col-sm-3" role="group">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>
                        </div><!-- /.col-lg-6 -->
                    </div><!-- /.row -->
                </form>
                <%----%>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<br>
<jsp:include page="/sub_jsp/footer.jsp"></jsp:include>
</div>
</body>
</html>
