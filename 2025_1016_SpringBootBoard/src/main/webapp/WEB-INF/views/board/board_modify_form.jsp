<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>게시판 새글쓰기</title>
<!-- Bootstrap3.x ver -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



<style type="text/css">
	#box{
		width:600px;
		margin:auto;
		margin-top: 100px;
	}
	textarea{
		resize: none;
	}
</style>
<script type="text/javascript">

	function send_modify(f){
		
		let b_subject = f.b_subject.value.trim();
		let b_content = f.b_content.value.trim();
		
		if(b_subject==""){
			alert("제목을 입력하세요");
			f.b_subject.value="";
			f.b_subject.focue();
			return;
		}
		if(b_content==""){
			alert("제목을 입력하세요");
			f.b_content.value="";
			f.b_content.focue();
			return;
		}
		
		f.action = "board_modify.do";
		f.submit(); //전송
	}
</script>
</head>
<body>


	<form action="">
	
		<div id="box">
			<div class="panel panel-primary">
				<div class="panel-heading"> <h5>게시물 새로쓰기</h5> </div>		
				<div class="panel-body"> 
							<input type="hidden" name="mem_idx" value="${user.mem_idx }">
							<input type="hidden" name="b_idx"value="${param.b_idx }">
							<input type="hidden" name="page"value="${param.page }">
							<input type="hidden" name="search"value="${param.search }">
							<input type="hidden" name="search_text"value="${param.search_text }">
					<table class="table">
						<tr>
							<th>작성자</th>
							<td><input class="form-control" name="mem_name" value="${board_vo.mem_name }" readonly="readonly"></td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input class="form-control" name="b_subject" value=${board_vo.b_subject }></td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea class="form-control" rows="15" cols="" name="b_content" >${board_vo.b_content }</textarea>
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								<input class="btn btn-info" type="button" value="메인화면"
									onclick="location.href='/board/list.do?page=${param.page}'">
								<input class="btn btn-primary" type="button" value="수정하기"
									onclick="send_modify(this.form)">
							</td>
						</tr>
					</table> 
				</div>
			</div>
		</div>
	
	
	
	
	</form>


</body>
</html>