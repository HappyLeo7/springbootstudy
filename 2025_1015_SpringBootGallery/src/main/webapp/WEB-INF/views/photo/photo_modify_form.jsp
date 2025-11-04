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
img{
	width : 300px;
	height: 300px;
}
</style>

<script type="text/javascript">

function send(f){
	let p_subject= f.p_subject.value.trim();
	let p_content= f.p_content.value.trim();
	
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
	
	
	
	f.action = "modify.do";
	f.submit();
}//end : send()

//이미지수정클릭
function photo_upload(){
	$('#ajaxFile').click();
}//end : photo_upload


//사진선택이 된경우 => Ajax를 이용한 파일업로드 
function photo_change(){
	  
	  //화일선택 취소했으면
	  if($("#ajaxFile")[0].files.length==0) return; 
	  //if($("#ajaxFile")[0].files[0]=='undefined') return;
	  
	  let form		= $("#ajaxForm")[0];
	  let formData	= new FormData(form); 
	  formData.append("p_idx", "${ vo.p_idx }");
	  formData.append("photo", $("#ajaxFile")[0].files[0]); //선택된 화일정보
	  
	  $.ajax({
		  url	:	"photo_upload.do",
		  type	:	"POST",
		  data	:	formData,
		  processData	: false,
		  contentType	: false,
		  dataType		: "json",
		  success		: function(res_data){
			  
			  // res_data = {"p_filename", "xxxx.jpg"}
			  
			  location.href = ""; //refresh()
		  },
		  error			: function(err){
			  alert(err.responseText);
		  }
		  
	  });
	  
	  
}//end:photo_change()
</script>
</head>
<body>

<!-- 파일 업로드용 폼 -->
<form action="" method="post" enctype="multipart/form-data" id="ajaxForm">
	<input type="file" id="ajaxFile" onchange="photo_change();" style="display: block;">
	
</form>

	<form>
	<input type="hidden" name="p_idx" value="${vo.p_idx }">
		<div id="box">
			<div class="panel panel-primary">
				<div class="panel-headding">
					<h5>수정하기</h5>	
				</div>		
				
				<div class="panel-body">
					<table class=table>
					
						<!-- 이미지 수정 코드 -->
						<tr>
							<td colspan="2" align="center">
								<img id="p_img" alt="" src="${pageContext.request.contextPath}/images/${vo.p_filename}">
								<br>
								<br>
								<input type="button" value="이미지수정" class="btn btn-success" onclick="photo_upload();">
								
							</td>
						</tr>
						
						
						<tr>
							<th>제목</th>
							<td><input class="form-control" name="p_subject" value="${vo.p_subject }"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="form-control" rows="5" name="p_content" >${vo.p_content }</textarea></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<input class="btn btn-info" type="button" value="메인화면"
								onclick="location.href='list.do'">
							<input class="btn btn-info" type="button" value="수정하기"
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