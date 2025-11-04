<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function send1(){
		
		location.href="login.do?name=일길동&grade=일반&age=20";
	}
	function send2(){
		
		location.href="login.do?name=일아동&grade=일반&age=16";
	}
	function send3(){
		
		location.href="login.do?name=삼관리&grade=관리자&age=16";
	}
	function send4(){
		
		location.href="login.do?name=사길동&grade=관리자&age=20";
	}
	
	window.onload=function(){
		setTimeout(showMessage,100);
	};
		
	function showMessage(){
		if("${param.reason eq 'session_timeout'}"=="true"){
			alert("로그아웃되었습니다.\n로그인 후 이용가능합니다.");
		}
		if("${param.reason eq 'not_admin'}"=="true"){
			alert("관리자만 이용 가능합니다.");
		}
		if("${param.reason eq 'not_adult'}"=="true"){
			alert("성인만 이용 가능합니다.");
		}
		
	}
	
	
	
	
</script>
</head>
<body>

	<!-- 로그인이 안된경우 -->
	<c:if test="${empty user }">
		<input  type="button" value="로그인(일반/20)" onclick="send1();">
		<input  type="button" value="로그인(일반/16)"onclick="send2();">
		<input  type="button" value="로그인(관리자/16)"onclick="send3();">
		<input  type="button" value="로그인(관리자/20)"onclick="send4();">
	</c:if>

	<!-- 로그인이 된경우 -->
	<c:if test="${not empty user }">
	<b>${user.name }(${user.grade}/${user.age })</b>님 환영합니다.
	<input type="button" value="로그아웃" onclick="location.href='logout.do'">
	</c:if>
		<hr>
	<ul>
		<li><a href="admin/member/list.do">관리자 페이지</a></li>
		<li><a href="adult/photo/list.do">성인 페이지</a></li>
	</ul>
</body>
</html>