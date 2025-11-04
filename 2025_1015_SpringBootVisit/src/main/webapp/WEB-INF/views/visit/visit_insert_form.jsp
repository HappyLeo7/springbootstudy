<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap3.x ver -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- CSS -->
<style type="text/css">
#box {
	width: 600px;
	margin: auto;
	margin-top: 100px;
}
textarea{
resize:none;
}
th{
vertical-align: middle !important;
text-align: center;
}
</style>
<!-- end : CSS -->

<!-- JS -->
<script type="text/javascript">

	function send(f){
		let name = f.name.value.trim();
		let content=f.content.value.trim();
		let pwd = f.pwd.value.trim();
		
		if(name==""){
			alert("이름을 입력하세요!");
			f.name.value="";
			f.name.focus();
			return;
		}
		if(content==""){
			alert("이름을 입력하세요!");
			f.content.value="";
			f.content.focus();
			return;
		}
		if(pwd==""){
			alert("이름을 입력하세요!");
			f.pwd.value="";
			f.pwd.focus();
			return;
		}
		//전송대상
		f.action="./insert.do" //VisitInsertAction
		
		//전송
		f.submit();
		
		
		
	}/* end : sene() */
	
</script>
<!-- end : JS -->


</head>
<body>
<form action="">
	<div id="box">
	
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>글쓰기폼</h4>
			</div>
			<div class="panel-body">
				<table class="table">
					<tr>
						<th>작성자</th>
						<td><input class="form-control" name="name"></td>
					</tr>
					
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="5" cols="" name="content" class="form-control"></textarea>
						</td>
					</tr>
					
					<tr>
						<th>비밀번호</th>
						<td><input name="pwd" type="password" class="form-control"></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input type="button" value="목록보기" class="btn btn-info" onclick="location.href='list.do'">
							<input type="button" value="글올리기" class="btn btn-primary" onclick="send(this.form);">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</form>
</body>
</html>