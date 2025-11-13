<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	로그인 화면입니다..
	<form action="login.do" method="post">
		ID : <input name="username" required="required"><br>
		PW : <input name="password" required="required" type="password">
		<input type="submit" value="로그인">
	</form>
</body>
</html>