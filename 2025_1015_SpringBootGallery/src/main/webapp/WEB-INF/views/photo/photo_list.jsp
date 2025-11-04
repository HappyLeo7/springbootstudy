<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진목록</title>
<!-- Bootstrap3.x ver -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
 #box{
	width:800px;
	margin:auto;
	margin-top: 30px; 
 }
 #title{
 	text-align:center;
 	font-size : 28px;
 	font-weight: bold;
 	color:#337AB7;
 	text-shadow:1px 1px 1px black;
 	margin-bottom: 50px;
 }
 
 input[value='사진등록'],input[value='로그인'],input[value='회원가입']{
 	width:80px;
 }
 
 #photo_box{
 	width:100%;
 	height: 500px;
 	border: 1px solid blue;
 	margin-top: 10px;
 	
 	/* 사이즈가 넘어가면 스크롤 처리한다. */
 	/* overflow-y:scroll; */
 }
 .photo{
 	width: 160px;
 	height:180px;
 	border : 1px solid black;
 	border-radius : 10px 100px / 500px;
 	background-color: rgb(179,57,20);
 	padding: 9px;
 	float: left;
 	margin: 14px;
 	
 	opacity: 0.6;
 }
 .photo > img{
 	width: 130px;
 	height: 130px;
 		
 }
 .photo>p{
 	font-size:12px;
 	font-weight:bold;
 	padding: 5px;
 	
 	overflow: hidden;
	white-space: nowrap; /* 엔터가 발생하면 줄바꿈하지말아라 */
	text-overflow: ellipsis; /* 텍스트가 오바되면 ...으로 써라 */
	word-break: break-all; /* 온전한 단어로 출력해라 */
 }
 
 #empty_photo{
	text-align: center;
	margin-top: 220px;
	color: red; 
	

}

/* 마우스 효과 흐리다가 진하게 */
.photo:hover{
	opacity:1.0;
}

</style>
<script type="text/javascript">

	function insert_photo(){
		
		//로그인 여부 체크
		if("${empty user}"=="true"){
			if(confirm("사진등록은 로그인후 가능합니다.\n로그인하시겠습니까?")==false){
				return;
			}
			
			//로그인폼 이동시킨다.
			//location.href="../member/login_form.do";
			location.href="${pageContext.request.contextPath}/member/login_form.do";
			return;
		}
		
		//사진등록폼으로 이동
		location.href="insert_form.do";
		
	}//end : insert_photo()

	
	//  수정 / 삭제할 p_idx를 저장해놓을 전역변수
	// let은 지역변수때 많이사용 //전역변수 var?
	var global_p_idx;
	var global_p_filename;
	
	function photo_detail(p_idx){
	  global_p_idx=p_idx;

	  $("#myModal").modal({backdrop: true});
	  
		
	  $.ajax({
		 url		: "photo_one.do"  //PhotoCon
		 ,data		: {"p_idx":p_idx} //photo_one.do?p_idx=5
		 ,dataType 	: "json"
		 ,success	: function(res_data){
			 
			  //전역변수에 파일명 받아두기
			  global_p_filename = res_data.p_filename;
			  
			  //res_data = { "p_idx":5, "p_subject":"제목", "p_content":"내용","p_filename":"a.jpg",...}
			  
			  $("#photo_detail > img").attr("src","${ pageContext.request.contextPath }/images/" + res_data.p_filename);
			  
			  $("#p_subject").html("<b>제목 : </b>" + res_data.p_subject);
			  $("#p_content").html(res_data.p_content);
			  $("#p_regdate").html("<b>등록일자 : </b>" + res_data.p_regdate + "&nbsp;&nbsp;&nbsp;" +
					               "<b>수정일자 : </b>" + res_data.p_lastmodifydate.substring(0,16)   	  
			  );
			  
			  
			/*  //res_data={"p_idx":5,"p_subject":"제목","p_content":"내용"...}
			 $("#p_subject").html("<b>제목 : </b>" + res_data.p_subject);
			 $("#p_content").html(res_data.p_content);
			 $("#p_regdate").html("<b>일자 : </b>" + res_data.p_regdate);
			  */
			 
			 $("input[value='다운로드']").hide();
			 $("input[value='수정하기']").hide();
			 $("input[value='삭제하기']").hide();
			 
			 
			 //로그인이 되어있는지 체크
			 if("${not empty user}"=="true"){
				 $("input[value='다운로드']").show();
			 }
			 
			 //로그인유저와 게시물의 주인이 동일하면 수정/삭제 메뉴 보여줘라
			 if("${user.mem_idx}"==res_data.mem_idx){
				 $("input[value='수정하기']").show();
				 $("input[value='삭제하기']").show();
			 }
			 
		 }
		 ,error		: function(err){
			 alert(err)
		 }
	  });
	
	}//end : photo_detail()
	
	//수정폼 띄우기
	function photo_modify_form(){
		
		location.href="modify_form.do?p_idx="+global_p_idx //PhotoContoller
		
	}//end : phot_modify_form()
	
	/* -------------------------------------------------  */
	
	//삭제처리
	function photo_delete(){
		if(confirm("정말 삭제 하시겠습니까?")==false)return;
		
		location.href="delete.do?p_idx="+global_p_idx+"&page=${param.page}";
	}//end : photo_delete()
	
	
	/* -------------------------------------------------  */
	
	//다운로드처리
	function photo_file_download(){
		//현재경로 photo
		location.href="${pageContext.request.contextPath}/FileDownload.do?dir=/images/&filename="
					+encodeURIComponent(global_p_filename,"utf-8");
		
	}
	
	
</script>
</head>
<body>

<%@include file="photo_detall_popup.jsp" %>

<div id="box">
	<h1 id="title">::::PhotoGallery::::</h1>
	<!-- 그리드모델로 가져오기 -->
	<!-- 사진등록 및 로그인정보 -->
	<div class="row">
 	 	<div class="col-sm-4">
 	 		<input class="btn btn-primary" type="button" value="사진등록" 
 	 		onclick="insert_photo();">
 	 	</div>
		<div class="col-sm-8" style="text-align:right;">
			<!-- 로그인 안된경우 
				현재경로 : /photo/list.do
				이동경로 : /member/login_form.do
			-->
			<c:if test="${empty sessionScope.user }">
				<input type="button" value="로그인" class="btn btn-primary"
				onclick="location.href='${pageContext.request.contextPath}/member/login_form.do'">
				<input type="button" value="회원가입" class="btn btn-info"
				onclick="location.href='../member/insert_form.do'">
			</c:if>
			
			<!-- 로그인 된경우 -->
			<c:if test="${not empty user }">
				<b>${user.mem_name }</b>님 환영합니다.
				<input type="button" value="로그아웃" class="btn btn-primary"
				onclick="location.href='${pageContext.request.contextPath}/member/logout.do'">
			</c:if>
			
		</div>
	</div>
	<%-- 프로그램경로:${pageContext.request.contextPath }/images/gabi.jsp --%>
	<div id="photo_box">
		
		<c:if test="${empty list }">
			<div id="empty_photo">
				등록된 사진이 없습니다.
			</div>
		</c:if>
	
	
<%-- 	<c:forEach begin="1" end="20"> --%>
		<c:forEach var="vo" items="${list }">
			<div class="photo" onclick="photo_detail('${vo.p_idx}');">
				<img alt="" src="${pageContext.request.contextPath}/images/${vo.p_filename}">
				<p>${vo.p_filename}</p>
				<p>${vo.p_subject }</p>
				
			</div>
			
		</c:forEach>
	</div>
	
	
	<!-- 페이지 메뉴 -->
	<div style="text-align: center;">
			<ul class="pagination">
			
			<c:forEach var="no" begin="1" end="5">
				<c:if test="${ no eq param.page }">
			  		<li class="active"><a href="list.do?page=${no }" >${no }</a></li>
				</c:if>
				<c:if test="${ no ne param.page }">
			  		<li><a href="list.do?page=${no }">${no }</a></li>
				</c:if>
			</c:forEach>
			<!-- 
			  <li><a href="list.do?page=1">1</a></li>
			  <li><a href="?page=2">2</a></li>
			  <li><a href="list.do?page=3">3</a></li>
			 -->
			</ul>
	</div>
	
	<div style="text-align: center;">
		${ pageMenu }
	</div>


</div>
</body>
</html>