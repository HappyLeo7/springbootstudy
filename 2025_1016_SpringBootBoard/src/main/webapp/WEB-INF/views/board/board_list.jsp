<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록...</title>
<!-- Bootstrap3.x ver -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

	<style type="text/css">
		#box{
			width: 1200px;
			margin: auto;
			margin-top:20px;
		}
		#title{
			text-align : center;
			font-size:26px;
			color : green;
			text-shadow : 3px 3px 5px black;
			margin-top: 50px;
			margin-bottom: 50px;
		}
		.badge-color{
		background-color: #337ab7;
		}
		
	</style>

<script type="text/javascript">
	
	function insert_form(){
		
		//로그인 안된 상태
		if("${empty user}"=="true"){
			if(confirm("새 글쓰기는 로그인후에 가능합니다.\n로그인 하시겠씁니까?")==false){
				return;
			}
			location.href="../member/login_form.do";
			return;
		}
		
		//로그인 된 상태
		location.href="insert_form.do"
	}// end : insert_form()
	
	
	function find(){
		let search 		= $("#search").val();					
		let search_text = $("#search_text").val().trim();
		
		if(search !='all' && search_text==""){
			alert("검색내용을 입력하세요!!");
			$("#search_text").val("");
			$("#search_text").focus();
			return;
		}
		
		//검색에 따른 목록 요청
		location.href = "list.do?search="+search+"&search_text="+encodeURIComponent(search_text,"UTF-8")+"&page=";
		
	}// end : find()
	
	/* 윈도우 초기화 이벤트 */
	//window.onload = function(){};
	// JQuery 초기화
	$(document).ready(function(){
		if("${ not empty param.search}"=="true"){
			$("#search").val('${param.search}');
		}
		
		/* 전체보기면 검색내용을 지워라 */
		if("${param.search eq 'all'}"=="true"){
			$("#search_text").val("");
		}
	});

</script>

</head>
<body>
	<div id="box">
		<h1 id="title">게시판</h1>
		
		<div class="row" style="margin-bottom: 20px;">
			<div class="col-sm-4">
				<input class="btn btn-primary" type="button" value="새글쓰기"
				 onclick="insert_form();">
			<c:if test="${ not empty user }">
			</c:if>
			</div>
			
			<!-- ## 로그인정보 ## -->
			<div class="col-sm-8" style="text-align : right">
				<!-- ~ 로그인 안됭경우 ~ -->
				<c:if test="${empty user }">
					<input class="btn btn-primary" type="button" value="로그인"
						onclick="location.href='../member/login_form.do'">
				</c:if>
				
				<!-- ~ 로그인 된경우 ~ -->
				<c:if test="${not empty user }">
					<b>${user.mem_name}</b>님 환영합니다.
					<input class="btn btn-primary" type="button" value="로그아웃"
						onclick="location.href='../member/logout.do'">
				</c:if>
			</div>
			 
		</div>
		
			<div class="panel panel-primary">
				<div class="panel-headding"> <h5></h5> </div>		
					<div class="panel-body"> 
						<table class="table"> 
						     <tr class="info">
						        <th width="10%">번호</th>
						        <th width="50%">제목</th>
						        <th>작성자</th>
						        <th>작성일자</th>
						        <th>조회수</th>
						     </tr>
						    <!-- 데이터가 없는 경우 -->
							<c:if test="${ empty board_list }">
							<tr>
							   <td colspan="5" align="center">
							      <font color="red">등록된 게시물이 없습니다</font>
							   </td>
							</tr>
							</c:if>
							
							<!-- 테이블 데이터 -->
							<c:forEach var="board_vo" items="${board_list }">
								<tr>
									<td>${board_vo.no }(${board_vo.b_idx })</td>
									<td>
									<!-- 답글이면   --> 
									<c:if test="${board_vo.b_depth !=0 }">
										<!-- 들여쓰기 -->
										<c:forEach begin="1" end="${board_vo.b_depth }">
										&nbsp;&nbsp;&nbsp;
										</c:forEach>
									ㄴ
									</c:if>
									
									<!-- 삭제된 글이면 -->
									<c:if test="${board_vo.b_use =='n' }">
										<font color="red">삭제된 글입니다(${board_vo.b_subject })</font>
									</c:if>
									
									<!-- 사용중인 글이면 -->
									<c:if test="${board_vo.b_use =='y' }">
									<a href="view.do?page=${empty param.page ? 1:param.page }&b_idx=${board_vo.b_idx }&search=${empty param.search ? 'all':param.search}&search_text=${param.search_text}">${board_vo.b_subject }</a>
									
									<!-- 뱃지(댓글갯수) -->
										<c:if test="${board_vo.comment_count > 0 }">
											<span class="badge badge-color">${board_vo.comment_count }</span>
										</c:if>
									</c:if>
									</td>
									<td>${board_vo.mem_name }</td>
									<td>${board_vo.b_regdate }</td>
									<td>${board_vo.b_readhit }</td>
								</tr>
														
							</c:forEach>
							
							
							<!-- 검색 및 페이지 메뉴 -->
							<tr>
								<td colspan="5" align="center">
								<br>
								
									<!-- 검색 -->
									<!-- UI 때문에 form태그사용 -->
									<form class="form-inline">
										<select id="search" class="form-control">
										<option value="all">전체보기</option>
										<option value="subject">제목</option>
										<option value="name">이름</option>
										<option value="content">내용</option>
										<option value="subject_name_content">제목+이름+내용</option>
										</select>
										
										<input class="form-control" id="search_text" value="${param.search_text }">
										<input class="btn btn-primary" type="button" value="검색" onclick="find();">
									</form>
									
									<!-- 페이지 메뉴 -->
									${pageMenu }
								<!--
								<br>
								 	<ul class="pagination">
									  <li><a href="list.do?page=1">1</a></li>
									  <li><a href="list.do?page=2">2</a></li>
									  <li><a href="list.do?page=3">3</a></li>
									  <li><a href="list.do?page=4">4</a></li>
									  <li><a href="list.do?page=5">5</a></li>
									</ul> -->
								</td>
							</tr>
							
						</table> 
					</div>
			</div>	
	</div><!-- /id="box" -->
</body>
</html>