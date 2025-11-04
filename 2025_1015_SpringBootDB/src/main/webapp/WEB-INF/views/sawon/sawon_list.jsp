<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>사번</th>		
			<th>사원명</th>		
			<th>성별</th>		
			<th>직위</th>		
			<th>부서번호</th>		
			<th>입사일자</th>		
			<th>상사번호</th>		
			<th>연봉</th>		
		</tr>
		<c:forEach var="sawonVo" items="${list}">
		<tr>
 			<th>${sawonVo.sabun }</th>		
			<th>${sawonVo.saname }</th>		
			<th>${sawonVo.sasex }</th>		
			<th>${sawonVo.sajob }</th>		
			<th>${sawonVo.deptno }</th>		
			<th>${sawonVo.sahire }</th>		
			<th>${sawonVo.samgr }</th>		
			<th>${sawonVo.sapay }</th>
		</tr>
		</c:forEach>	
	</table>

</body>
</html>