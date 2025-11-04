<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글리스트</title>

<style type="text/css">

.comment-id{
	font-weight: 900;
	font-size: 14px;
}
.comment-date{
    color: #757575;
}
.comment-content{
	font-size:25px;
	font-weight: 100;
	color : #222222;
}
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro@4cac1a6/css/all.css" />

</style>
</head>
<body>

<br>
	
<div id="cmt_menu"></div>
	<c:forEach var="comment_vo" items="${comment_list }">
	
	<div class="row">
		<div class="col-sm-6">
		 <span class="glyphicon glyphicon-user comment-id">${comment_vo.mem_id }</span>
		</div>
		
		<div class="col-sm-6" style="text-align: right;">
			<c:if test="${comment_vo.mem_idx == user.mem_idx }">
				<input type="button" class="btn btn-danger" style="font-size:10px;" value="X" onclick="comment_delete('${comment_vo.c_idx}');">		
			</c:if>
		</div>
	</div>
		<div class="comment-date">${comment_vo.c_regdate }</div>
		<div class="comment-content">&nbsp;&nbsp;&nbsp;${comment_vo.c_content }</div>
		<hr>
	</c:forEach>
	
	<!-- 댓글메뉴 -->
	<c:if test="${rowTotal > 0 }">
	
		<div style="font-size: 10px;">
		${pageMenu }
		</div>
		
	</c:if>
	
<!--  
	<ul class="pagination">
	  <li><a href="#cmt_menu" onclick="comment_list('1')">1</a></li>
	  <li><a href="#cmt_menu" onclick="comment_list(2)">2</a></li>
	  <li><a href="#cmt_menu" onclick="comment_list(3)">3</a></li>
	  <li><a href="#cmt_menu" onclick="comment_list('4')">4</a></li>
	  <li><a href="#cmt_menu" onclick="comment_list('5')">5</a></li>
	</ul>
-->
</body>
</html>