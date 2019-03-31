<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cn.drp
  Date: 2019/2/27
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/> ">
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/> "></script>
    <script src="<c:url value="/js/bootstrap.min.js"></c:url> "></script>
</head>
<body>
<div class="container-fluid">
    <ul class="nav nav-my bg-warning ">
        <li class="modal-title"><h3>&nbsp;DbStudy&nbsp;</h3></li>
        <li><a href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=mysql"/>"> 每日一练</a></li>
        <li><a href="<c:url value="/QueryDatumMainServlet?method=dispathcerToMain"/>">资料查询</a></li>
        <c:if test="${empty user}">
            <li><a href="<c:url value="/main_jsp/login.jsp"/>">登陆</a></li>
            <li><a href="<c:url value="/main_jsp/regist.jsp"/>">注册</a></li>
        </c:if>
        <c:if test="${!empty user}">
            <c:if test="${user.type eq 'admin'}">
                <li><a href="<c:url value="/main_jsp/admin_jsp/admin_index.jsp"/>">管理中心</a></li>
            </c:if>
            <li><a href="<c:url value="/HeadImgStoreServlet?method=getHeadImg"></c:url> ">个人主页</a></li>
            <li><a href="<c:url value="/UserServlet?method=loginExit"/>">注销</a> </li>
        </c:if>
    </ul>
    <div class="row text-center index-title bg-info" style="
            background-image: url(<c:url value='/img/background/background_1.jpg'/>);background-position-y: 1000px">
        <h1>欢迎${user.username}来到DbStudy</h1>
    </div>
</div>
<br>
<div>
    <br>
    <div class="container marketing">
        <div class="row">
            <div class="col-lg-4">
                <img class="img-circle" src="<c:url value="/img/SqlServer.png"/> " alt="Generic placeholder image" width="140" height="140">
                <h2>Microsoft SQL Server</h2>
                <p>SQL Server 是Microsoft 公司推出的关系型数据库管理系统。Microsoft SQL Server 是一个全面的数据库平台，使用集成的商业智能 (BI)工具提供了企业级的数据管理。Microsoft SQL Server 数据库引擎为关系型数据和结构化数据提供了更安全可靠的存储功能，使您可以构建和管理用于业务的高可用和高性能的数据应用程序。</p>
                <p><a class="btn btn-default" href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=mysql"/>" role="button">更多详情 &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img class="img-circle" src="<c:url value="/img/oracle.jpg"/>" alt="Generic placeholder image" width="140" height="140">
                <h2>Oracle</h2>
                <p>Oracle Database，又名Oracle RDBMS，或简称Oracle。是甲骨文公司的一款关系数据库管理系统。它是在数据库领域一直处于领先地位的产品。可以说Oracle数据库系统是目前世界上流行的关系数据库管理系统，系统可移植性好、使用方便、功能强，适用于各类大、中、小、微机环境。它是一种高效率、可靠性好的 适应高吞吐量的数据库解决方案。</p>
                <p><a class="btn btn-default" href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=oracle"/>" role="button">更多详情 &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img class="img-circle" src="<c:url value="/img/mongodb.jpg"/>" alt="Generic placeholder image" width="140" height="140">
                <h2>MongoDB</h2>
                <p>MongoDB是一个介于关系数据库和非关系数据库之间的产品，是非关系数据库当中功能最丰富，最像关系数据库的。它支持的数据结构非常松散，是类似json的bson格式，因此可以存储比较复杂的数据类型。Mongo最大的特点是它支持的查询语言非常强大，其语法有点类似于面向对象的查询语言，几乎可以实现类似关系数据库单表查询的绝大部分功能，而且还支持对数据建立索引。</p>
                <p><a class="btn btn-default" href="<c:url value="/TestLibraryServlet?method=getTestLibrary&cp_stl=0&type=mongodb"/>" role="button">更多详情 &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->
        <hr class="featurette-divider">
        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">Mysql <span class="text-muted">&nbsp;打开你的思想</span></h2>
                <p class="lead">MySQL®软件提供了十分快速的多线程、多用户、牢靠的SQL（结构化查询语言）数据库服务器。 MySQL服务器定位于任务关键型、重负荷生产系统，并能嵌入在大量部署的软件中。MySQL是MySQL AB的注册商标。
                    MySQL软件采用双许可方式。
                </p>
            </div>
            <div class="col-md-5">
                <img class="featurette-image img-responsive center-block" src="<c:url value="/img/mysql.jpg"></c:url> " alt="Generic placeholder image">
            </div>
        </div>
    </div>
    <c:if test="${!empty user}">
    <%@include file="/sub_jsp/personMenu.jsp"%>
    </c:if>
    <br>
    <br>
    <br>
   <jsp:include page="/sub_jsp/footer.jsp"></jsp:include>
</div>
</body>
</html>
