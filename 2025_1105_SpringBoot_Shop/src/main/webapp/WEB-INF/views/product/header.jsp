<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <meta http-equiv="Content-Type" 
  		content="text/html; charset=UTF-8">
    <title></title>
    <style>
        a:link{text-decoration:none; color:navy}
        a:visited{text-decoration:none; color:navy}
        a:hover{text-decoration:none; color:red}
    </style>
  </head>
  <body>
  <!-- 로그인 정보 작성 -->
  <div style="width:600px;margin: auto;text-align:right">
  	<!-- 로그인이 안되었을때 -->
  	<c:if test="${empty user }">
  		<input type="button" value="로그인" onclick="location.href='../member/login_form.do'">
  		<input type="button" value="회원가입" onclick="location.href='../member/insert_form.do'">
  	</c:if>
  	
  	<!-- 로그인된경우 -->
  	<c:if test="${not empty user }">
  		<b>${user.mem_name }</b>님 환영합니다.
  		<input type="button" value="로그아웃" onclick="location.href='../member/logout.do'">
  		<input type="button" value="장바구니" onclick="location.href='../cart/list.do'">
  		<input type="button" value="구매내역" onclick="location.href='../payment/list.do?mem_idx=${user.mem_idx}'">
  	</c:if>
  </div>
  <hr width="600" border="1" noshade color="navy">
  <center>
      <font size="4" color="maroon">
          <b>ITLAND SHOPPING CENTER</b>
      </font>
  </center>
  <hr width="600" border="1" noshade color="navy">
  <center>
  <c:forEach var="category" items="${category_list }">
      <a href="${pageContext.request.contextPath }/product/list.do?cate_idx=${ category.cate_idx }">${ category.cate_name }</a> | 
  </c:forEach>
  </center>
  <hr width="600" border="1" noshade color="navy">
  
  <!-- 로그인유저가 관리자면 상품등록 버튼 보여줌 -->
  <c:if test="${user.mem_grade eq '관리자' }">
  <!-- 상품등록 버튼 -->
  <div style="margin: auto; width: 600px; margin-bottom: 10px" onclick="location.href='insert_form.do'">
  	<input type="button" value="상품등록">
  </div>
  </c:if>
  
  </body>
</html>