<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- 주소찾기 API 라이브러리 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- sweetalert2 -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css"> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script> -->

<style type="text/css">
#box {
	width: 700px;
	margin: auto;
	margin-top: 100px;
}
.common{
margin : 10px;
padding : 10px;
border: 1px solid #cccccc;
}
label{
	width : 80px;
	
}
</style>
<script type="text/javascript">
	
	/* 주소 API 함수 */
	function find_addr(){
		   new daum.Postcode({
			   
		        oncomplete: function(data) {
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		            //console.log(data);
		            $("#mem_zippcode").val(data.zonecode);
		            $("#mem_addr").val(data.address);
		        }
		    }).open();
	}//end : find_addr()
	
	/* 데이터 form에 담아서 서버에 보내는 함수 */
	function send(f){
		//fomr 데이터 가져오기
		let mem_name=f.mem_name.value.trim();
		let mem_pwd=f.mem_pwd.value.trim();
		let mem_email=f.mem_email.value.trim();
		let mem_zippcode=f.mem_zippcode.value.trim();
		let mem_addr =f.mem_addr.value.trim();
		
		
		//입력값 검증작업(입력유무)
		if(mem_name==""){
			alert("이름을 입력하세요");
			f.mem_name.value="";
			f.mam_name.focus();
			return;
		}
		
		if(mem_pwd==""){
			alert("비밀번호를 입력하세요");
			f.mem_pwd.value="";
			f.mam_pwd.focus();
			return;
		}
		if(mem_email==""){
			alert("이메일을 입력하세요");
			f.mem_email.value="";
			f.mam_email.focus();
			return;
		}
		
		let chak_mag=confirm("정말 수정하시겠습니까?");
		if(chak_mag==true){
			
		f.method="GET";
		
		
		//전송대상지정
		f.action = "modify.do"; // MemberInsertAction
		
		//서버전송
		f.submit();
		}
	}//end : send(f)
	
	
</script>

<!-- 초기화 처리 코드 -->
<script type="text/javascript">
	//window.onload //말고 다른걸 써보자
	
	//jquery 초기화 방법
	$(document).ready(function(){
		//회원구분의 값을 초기화
		//<select id="mem_grade">
		$("#mem_grade").val('${vo.mem_grade}');
	});
</script>

</head>
<body>
	<form action="" class="form-inline">
		<div id="box">

			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>회원정보수정창</h4>
				</div>
				<div class="panel-body">
				
					<div class="common">
						<label>MemberNo</label> <input class="form-control" type="text" name="mem_idx" value="${vo.mem_idx }" readonly="readonly">
					</div>					
					<div class="common">
						<label>Name</label> <input class="form-control" name="mem_name" value="${vo.mem_name }">
					</div>
					<div class="common">
						<label>Id</label> 
						<input class="form-control" name="mem_id" id="mem_id" value="${vo.mem_id }" readonly="readonly">
						<span id="msg_id"></span>
					</div>
					<div class="common">
						<label>Pwd</label> <input class="form-control" type="password" name="mem_pwd" value="${vo.mem_pwd }">
					</div>
					<div class="common">
						<label>Email</label> 
						<input class="form-control" type="email" name="mem_email" value="${vo.mem_email }">
					</div>
					<div class="common">
						<label>Zippcode</label>
						 <input class="form-control" name="mem_zippcode" id="mem_zippcode" value="${vo.mem_zippcode }">
						<input class="btn btn-primary" type="button" value="주소찾기" onclick="find_addr();">	
						<br><br><label>addr</label> <input size="65%" class="form-control" name="mem_addr" id="mem_addr" value="${vo.mem_addr }">
					</div>
					
					<!-- 회원등급 -->
				<%-- 	<div class="common">
						<label>Grade</label>
						<select name="mem_grade" class="form-control" id="mem_grade">
						<c:if test="${vo.mem_grade=='일반'}">
							<option value="일반" selected="selected">일반</option>
							<option value="관리자">관리자</option>
						</c:if>
						<c:if test="${vo.mem_grade=='관리자' }">
							<option value="일반">일반</option>
							<option value="관리자" selected="selected">관리자</option>
						</c:if>
						</select>
						<input type="text" name="mem_grade" value="${vo.mem_grade }">
					</div> --%>
					
					<div class="common">
						<label>Grade</label>
						<select name="mem_grade" class="form-control" id="mem_grade">
							<option value="일반">일반</option>
							<option value="관리자">관리자</option>
					
						</select>
					</div>

					<div class=common style="text-align: center;">
						<input type="button" class="btn btn-success" value="목록보기" style="margin-right: 20px; " onclick="location.href='list.do'">
						<input type="button" class="btn btn-info" value="수정하기" id="btn_register" 
						onclick="send(this.form);">
					</div>
					
				</div>
			</div>
		</div>
	</form>
</body>
</html>