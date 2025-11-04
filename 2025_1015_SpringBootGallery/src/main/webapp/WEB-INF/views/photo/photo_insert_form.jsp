<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Bootstrap3.x ver -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#box{
	width:500px;
	margin: auto;
	margin-top: 100px;
}
textarea {
	resize: none;
}

</style>

<script type="text/javascript">

function send(f){
	let p_subject= f.p_subject.value.trim();
	let p_content= f.p_content.value.trim();
	let photo 	 = f.photo.value;
	
	if(p_subject==""){
		
		alert("제목을 입력하세요!")
		f.p_subject.value="";
		f.p_focus();
		return;
	}
	if(p_content==""){
		
		alert("내용을 입력하세요!")
		f.p_content.value="";
		f.p_focus();
		return;
	}
	
	
	//포토가 입력이 안되어있으면
	if(photo==""){
		alert("사진을 선택하세요")
		return;
	}
	
	f.action = "insert.do";
	f.submit();
}
</script>
</head>
<body>
	<form action="" method="post" enctype="multipart/form-data">
		<div id="box">
			<div class="panel panel-primary">
				<div class="panel-headding">
					<h5>사진등록</h5>	
				</div>		
				
				<div class="panel-body">
					<table class=table>
						<tr>
							<th>제목</th>
							<td><input class="form-control" name="p_subject" ></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="form-control" rows="5" name="p_content" ></textarea></td>
						</tr>
						<tr>
							<th>사진</th>
							<td><input class="form-control" name="photo" type="file"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<input class="btn btn-info" type="button" value="메인화면"
								onclick="location.href='list.do'">
							<input class="btn btn-info" type="button" value="등록하기"
								onclick="send(this.form)">
							</td>
						</tr>
						
					</table>
				</div>
			</div>	
		</div>
	</form>
</body>
</html>