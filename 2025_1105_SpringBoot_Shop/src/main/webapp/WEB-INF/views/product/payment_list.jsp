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


</head>
<jsp:include page="header.jsp" />
<body>

	<form action="">

<c:forEach var="payment_result" items="${payment_result_list }">

		<table align="center" width="600" border="1"
			style="border-collapse: collapse; font-size: 8pt" bordercolor="navy"
			cellpadding="4" cellspacing="0">
			<tr>
				<td align="center" colspan="5"><b>주문 번호 : ${payment_result.pay_order_num }</b>
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
			<!-- for(PaymentVo payment : payment_result.payment_list) -->
			<c:forEach var="payment" items="${payment_result.payment_list}">
				<tr align="center">
					<td align="center">
					${payment.pay_model_num }</td>
					<td>${payment.pay_name }</td>
					<td>단가:<fmt:formatNumber value="${payment.pay_price }"></fmt:formatNumber><br>
					</td>
					<td>
						${payment.pay_cnt }
					</td>
					<td align="center"><fmt:formatNumber value="${payment.pay_cnt * payment.pay_price }" /></td>
				</tr>
			</c:forEach>

			<c:if test="${empty payment_result.payment_list}">
				<tr>
					<td colspan="6" align="center"><b>주문정보가 비었습니다.</b></td>
				</tr>
			</c:if> 

			<tr>
				<td colspan="3" align="right">총 결재액 :</td>
		<td align="center"><fmt:formatNumber value="${payment_result.pay_amount_sum }"></fmt:formatNumber></td>
			</tr>
		</table>
</c:forEach>
	</form>
</body>
</html>






