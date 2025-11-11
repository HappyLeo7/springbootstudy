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
	
	//결제 하기
	function payment(f){
		if(confirm("결재하시겠습니까?")==false){
			return;
		}
		f.action="../payment/insert.do"
		f.submit();
	}
	
</script>




</head>
<jsp:include page="header.jsp" />
<body>

	<form action="">
					<input type="hidden" name="mem_idx" value="${user.mem_idx }">


		<table align="center" width="600" border="1"
			style="border-collapse: collapse; font-size: 8pt" bordercolor="navy"
			cellpadding="4" cellspacing="0">
			<tr>
				<td align="center" colspan="5">:: 결제 내역 ::
				</td>
			</tr>
			<tr bgcolor="#dedede" >
				<th align="center">제품번호</th>
				<th width="25%" align="center">제품명</th>
				<th align="center">단가</th>
				<th align="center">수량</th>
				<th align="center">금액</th>
			</tr>
			<!-- 상품정보 -->
			<c:forEach var="vo" items="${cart_list }">
				<tr align="center">
					<td align="center">
					<input type="hidden" name="c_idx" value="${vo.c_idx }">
					${vo.p_model_num }</td>
					<td>${vo.p_name }</td>
					<td>단가:<fmt:formatNumber value="${vo.p_price }"></fmt:formatNumber><br>
						<font color="red"> 세일가격:<b><fmt:formatNumber
									value="${vo.p_saleprice }"></fmt:formatNumber></b>
					</font>
					</td>
					<td>
						${vo.c_cnt }
					</td>
					<td align="center"><fmt:formatNumber value="${vo.amount }" /></td>
				</tr>
			</c:forEach>

			<c:if test="${empty cart_list}">
				<tr>
					<td colspan="6" align="center"><b>장바구니가 비었습니다.</b></td>
				</tr>
			</c:if>

			<tr>
				<td colspan="1" align="center"><input type="button" value="결제하기" onclick="payment(this.form);"></td>
				<td colspan="3" align="right">총 결재액 :</td>
				<td align="center"><fmt:formatNumber value="${total_amount }"></fmt:formatNumber></td>
			</tr>
		</table>
	</form>
</body>
</html>






