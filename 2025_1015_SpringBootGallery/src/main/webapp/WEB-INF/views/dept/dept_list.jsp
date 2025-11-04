<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap 3.x -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
  #box{
     width: 500px;
     margin: auto;
     margin-top: 100px;
     
     box-shadow: 1px 1px 3px black;
  }
</style>

</head>
<body>
  <div id="box">
      <table class="table table-hover">
          <tr class="success">
              <th>부서번호</th> 
              <th>부서명</th> 
              <th>위치</th> 
          </tr>
          <!--  for(DeptVo vo : list ) 동일  -->
          <c:forEach var="vo"  items="${ requestScope.list }">
             <tr>
                <td>${ pageScope.vo.deptno }</td>
                <!-- square bracket -->
                <td>${ vo['dname'] }</td>
                <td>${ vo.loc }</td>
             </tr>
          </c:forEach>
          
          
      </table>
  </div>
</body>
</html>





