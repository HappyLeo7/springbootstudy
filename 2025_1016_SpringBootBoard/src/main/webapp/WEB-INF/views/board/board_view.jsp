<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 페이지</title>
<!-- Bootstrap3.x ver -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
#box {
	width: 800px;
	margin: auto;
	margin-top: 50px;
}

.panel-body>div {
	border: 1px solid #dddddd;
	padding: 5px;
	margin-bottom: 10px;
}

#content {
	min-height: 80px;
}

textarea {
	resize: none;
}
</style>
<script type="text/javascript">
	function board_delete(b_idx) {

		if (confirm("정말 삭제 하시겠습니까?") == false) {
			location.href = ''; //refresh()
			return;
		}
		//삭제처리
		location.href = 'delete.do?b_idx=' + b_idx + "&page=${param.page}"
				+ "&search=${param.search}&search_text="
				+ encodeURIComponent("${param.search_text}", "utf-8");

	}

	/* 수정처리 */
	function board_modify_form(b_idx) {
		// modify_form.do?page=3&b_idx=25
		location.href = 'modify_form.do?page=${param.page}&b_idx=' + b_idx
				+ "&search=${param.search}&search_text="
				+ encodeURIComponent("${param.search_text}", "utf-8");
	}// end

	function check_login() {

		if ("${empty user}" == "true") {
			$("#comment_mag").blur();
			if (confirm("댓글쓰기는 로그인 하신후 가능합니다. \n로그인 하시겠습니까?") == false) {
				return;
			}else{
				
			}
			//로그인창 이동
			//$("#comment_mag").blur();
			location.href = "../member/login_form.do?url="	+ encodeURIComponent(location.href, "utf-8");
		}
	}
	
	function ajax_comment(){
		
	/* 	$.ajax({
			url "": 
		}); */
	}
	
	
	//----------------------------------------------------------------------------------------
	var global_comment_page=1;
	
	function comment_insert(event){
		//console.log(event.key);
		if(event.key=='Enter'){
			let c_content = $("#comment_mag").val().trim();
			
			if(c_content==""){
				$("#comment_mag").val("");
				$("#comment_mag").focus();
				return;		
			}
			
			//console.log(c_content);
			
			//
			$.ajax({
				url:"../comment/insert.do"
			   ,type: "get"
			   ,data: {
				   			"b_idx":"${board_vo.b_idx}"
				   		   ,"c_content":c_content
				   		   ,"mem_idx":"${user.mem_idx}"
				   		   ,"mem_id":"${user.mem_id}"
				   	  }
				,dataType:"json"
				,success:function(res_data){
					if(res_data.result==false){
						alert("댓글추가실패")
						return;
					}
					console.log(res_data);
					//작업완료후 
					$("#comment_mag").val("");
					$("#comment_mag").focus();
					
					comment_list(1);
				}
				,error:function(err){
					alert("댓글등록:"+err.responseText)
					
				}
				
					
			});//ajax
			
			
		}
		
	}// end : 댓글등록
	
	function comment_list(page){
		
		//현재 보고 있는
		global_comment_page = page;
		
		
		$.ajax({
			url:"../comment/list.do"
		   ,data:{
			   	  "b_idx":"${board_vo.b_idx}"
			   	 ,"page":page
		  		 }
		   ,success: function(res_data){
			   		console.log("댓글리스트:"+res_data);
			   		// res_data : 서버측에서 생성해준 화면
			   		$("#comment_display").html(res_data);
					}
		   ,error: function(err){
			   alert("댓글리스트:"+err.responseText);
		   			}
		   
			
		});
	}//end comment_list
	
	
	/* 댓글삭제 */
	function comment_delete(c_idx){
		if(confirm("정말 삭제하시겠습니까?")==false){return;}
		
		//ajax로 삭제할 c_idx 서버로 보내기
		$.ajax({
			url 	: "../comment/delete.do"
		   ,data 	: {"c_idx":c_idx}
		   ,dataType: "json"
		   ,success	: function(res_data){
			   			if(res_data.result==false){
			   				alert("삭제실패!!!")
			   				return;
			   			}
			   			// 성공했다면
			   			comment_list(global_comment_page);
		   			}
		   ,error	: function(err){
			   			alert("시스템 오류 관리자에게 문의")
		   			}
		});
		
	}
	
	
	//댓글 초기화 함수
	$().ready(function(){
		comment_list(1)
		});
</script>
</head>
<body>
	<!-- main box -->
	<div id="box">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h5>[${board_vo.mem_name }]</h5>
				님의 글
			</div>
			<div class="panel-body">


				<div id="subject">
					<b>${board_vo.b_subject }</b>
				</div>
				<div id="content">${board_vo.b_content }</div>
				<div id="regdate">작성일자 : ${ board_vo.b_regdate}(${board_vo.b_ip })</div>

				<div>
					<input class="btn btn-primary" type="button" value="목록보기"
						onclick="location.href='list.do?page=${param.page}&search=${param.search }&search_text=${param.search_text }'">

					<!-- 로그인이 된 상태면 -->
					<c:if
						test="${ (param.search eq 'all') and (not empty user) and (board_vo.b_depth < 2) }">
						<input class="btn btn-success" type="button" value="답글쓰기"
							onclick="location.href='reply_insert_form.do?page=${param.page}&b_idx=${board_vo.b_idx}'">
					</c:if>

					<!-- 현재글이 본인 글일경우에만 -->
					<c:if test="${board_vo.mem_idx == user.mem_idx }">
						<input class="btn btn-info" type="button" value="수정하기"
							onclick="board_modify_form('${board_vo.b_idx}')">
						<input class="btn btn-danger" type="button" value="삭제하기"
							onclick="board_delete('${board_vo.b_idx}')">
					</c:if>
				</div>

			</div>
		</div>

		<!-- 댓글쓰기 -->
		<input class="form-control" id="comment_mag"
			onfocus="check_login();" placeholder="댓글을 작성하시려면 로그인이 필요합니다."
			onkeyup="comment_insert(event);"
			>

		<!-- 댓글 리스트 -->
		<div id="comment_display"></div>

	</div>
</body>
</html>