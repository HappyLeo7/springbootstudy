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


<style type="text/css">

#box{
	width:500px;
	margin: auto;
	margin-top: 120px;
}

input[type='button']{
	width:80px;
}
th{
	vertical-align: middle !important;
}
</style>

<script type="text/javascript">
	// 로그인 전송 버튼기능
	function send(f){ 
		let mem_id = f.mem_id.value.trim();
		let mem_pwd = f.mem_pwd.value.trim();
		
		if(mem_id==""){
			alert("ID를 입력하세요");
			f.mem_id.value="";
			f.mem_id.focus();
			return;
		}
		if(mem_pwd==""){
			alert("PWD를 입력하세요");
			f.mem_pwd.value="";
			f.mem_pwd.focus();
			return;
		}
		
		//전송위치이동
		
		f.action = "login.do" ;// MemberLoginAction
		
		//전송
		f.submit();
	}
</script>

<script type="text/javascript">
	//document내의 모든 Element가 배치가 완료되면 다시 호출해준다.
	window.onload=function(){
		
		//일회성 매서드 (매서드실행,0.1초후실행)
		setTimeout(show_message,100);
		
		//바로 호출했을때 문제가 있다. 뷰화면이 안나오기때문에
		//show_message();
		
	};
	
	function show_message(){
		//  /login_form.do?reason=fail_id
		if("${param.reason eq 'fail_id'}"=="true"){
			alert("아이디가 틀렸습니다.");
		}
		if("${param.reason eq 'fail_pwd'}"=="true"){
			alert("비밀번호가 틀렸습니다.");
		}
		if("${param.reason eq'session_timeout'}"=="true"){
			alert("로그아웃되었습니다.");
		}
		
	}
	
	
</script>
</head>
<body>
	<form action="">
		<div id="box">
			<div class="panel panel-primary">
			<div class="panel-heading">
				<h5>로그인</h5>
			</div>
				<div class="panel-body">
					<table class="table">
						<tr>
							<th>ID</th>
							<td><input class="form-control" name="mem_id" id="mem_id" value="${param.mem_id }"></td>
						</tr>
						<tr>
							<th>PWD</th>
							<td><input class="form-control" name="mem_pwd"
								type="password"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input class="btn btn-success" type="button" value="메인화면"
										onclick="location.href='${pageContext.request.contextPath}/photo/list.do'"> 
								<input class="btn btn-primary" type="button" value="로그인"
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