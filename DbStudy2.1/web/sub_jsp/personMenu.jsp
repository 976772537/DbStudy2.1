<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/12
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="personMenu" class="personMenu" onclick="window.open('<c:url value="/HeadImgStoreServlet?method=getHeadImg"/>')">
    <div class="col-sm-8 text-center">
    <label>${user.username}&emsp;</label>
    </div>
    <div class="col-sm-4 text-right">
        <img src="<c:url value="/img/user_head/${userhead}"/> " alt="用户头像" class="img-circle" style="width:30px;">
    </div>
</div>
<script>
    $("#personMenu").mouseenter(function () {
        $(this).animate({
            right:'0'
        },"slow");
    });
    $("#personMenu").mouseleave(function () {
        $(this).animate({
            right:'-200px'
        },"slow");
    });
</script>