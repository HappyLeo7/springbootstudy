<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap3.x ver -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		setTimeout(showMessage,200);
		
	})
	function showMessage(){
		if("${param.error_code eq 'no_authentication'}" == "true"){
			alert("로그인후에 이용하세요!!");
		}
	}
	
	
</script>
</head>
<body>
	<h1>여기는 메인 페이지 입니다.</h1>
	
	<!-- 로그인 안된경우 -->
	<c:if test='${empty user }'>
		<input value="로그인" type="button" onclick="location.href='/login_form.do'">
	</c:if>
	<!-- 로그인 된경우 --> 
	<c:if test='${not empty user }'>
		<b>${user.mem_name }</b>환영합니다.
		<form action="logout" method="post"></form>
		<input value="로그아웃" type="submit">
	</c:if>
	
	<br>
	
	<a href="/admin/info.do">관리자페이지 이동</a>
	<a href="/user/info.do">유저페이지 이동</a>
</body>
</html>