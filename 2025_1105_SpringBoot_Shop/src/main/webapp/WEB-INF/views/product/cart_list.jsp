<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Bootstrap3.x ver -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<script type="text/javascript">
	const regular_number = /^[0-9]{1,4}$/

	function cartModify(c_idx) {
		let c_cnt = document.getElementById("c_cnt_" + c_idx).value;
		if (regular_number.test(c_cnt) == false) {
			alert("숫자만 입력하세요!!\n(0~9999)");
			document.getElementById("c_cnt_" + c_idx).value = '';
			document.getElementById("c_cnt_" + c_idx).focus();
			return;
		}

		//수정
		// jsp에서 backtic 사용시 \${자바스크립트변수} 
		location.href = `modify.do?c_idx=\${c_idx}&c_cnt=\${c_cnt}`;

	}
	
	//결제 미리보기
	function payment_preview(f){
		if($("input[name='c_idx']:checked").length==0){
			alert("결재할 상품목록이 없습니다.")
			return;
		}
		f.action="../payment/preview.do"
		f.submit();
	}
	
</script>


<!-- 체크박스처리 -->
<script type="text/javascript">
	$(document).ready(function(){
		//전체선택버튼 이벤트
		$("#checkAll").click(function(){
			//전체선택의 체크상태를 가져옴
			const checked=$(this).prop("checked");
			// 하위체크박스 선택유무 결정
			$("input[name='c_idx']").prop("checked",checked);
		});
		
		// 하위체크박스 클릭시
		$("input[name='c_idx']").click(function(){
			
			//전체 및 체크된 체크박스의 갯수 구하기
			const total_count= $("input[name='c_idx']").length;
			const checked_count= $("input[name='c_idx']:checked").length;
			//alert(total_count+"/"+checked_count);
			
			//전체 체크박스의 체크유무설정
			$("#checkAll").prop("checked",(total_count==checked_count));
			
		});
	});
</script>


</head>
<jsp:include page="header.jsp" />
<body>

	<form action="">


		<table align="center" width="600" border="1"
			style="border-collapse: collapse; font-size: 8pt" bordercolor="navy"
			cellpadding="4" cellspacing="0">
			<tr>
			<td>
				<input type="checkbox" id="checkAll">전체선택
			</td>
				<br>
				<td align="center" colspan="5">:: 장바구니 내용
				</td>
			</tr>
			<tr bgcolor="#dedede">
				<th>제품번호</th>
				<th width="25%">제품명</th>
				<th>단가</th>
				<th>수량</th>
				<th>금액</th>
				<th>삭제</th>
			</tr>
			<!-- 상품정보 -->
			<c:forEach var="vo" items="${cart_list }">
				<tr align="center">
					<td align="left">
					<input type="checkbox" name="c_idx" value="${vo.c_idx }">
					${vo.p_model_num }</td>
					<td>${vo.p_name }</td>
					<td>단가:<fmt:formatNumber value="${vo.p_price }"></fmt:formatNumber><br>
						<font color="red"> 세일가격:<b><fmt:formatNumber
									value="${vo.p_saleprice }"></fmt:formatNumber></b>
					</font>
					</td>
					<td>
						<!-- 수량 조정 폼 --> <input id="c_cnt_${vo.c_idx }" size="4"
						style="text-align: center;" value="${vo.c_cnt }"> <input
						type="button" value="수정" onclick="cartModify('${vo.c_idx}');">
					</td>
					<td><fmt:formatNumber value="${vo.amount }" /></td>
					<td><input type="button" value="삭제"
						style="border: 1 solid black; cursor: hand" onclick=""></td>
				</tr>
			</c:forEach>

			<c:if test="${empty cart_list}">
				<tr>
					<td colspan="6" align="center"><b>장바구니가 비었습니다.</b></td>
				</tr>
			</c:if>

			<tr>
				<td colspan="1" align="center"><input type="button" value="결제하기" onclick="payment_preview(this.form);"></td>
				<td colspan="4" align="right">총 결재액 :</td>
				<td><fmt:formatNumber value="${total_amount }"></fmt:formatNumber></td>
			</tr>
		</table>
	</form>
</body>
</html>






