<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	function add_cart(){
		
		//ajax를 이용해서 장바구니 담기
		$.ajax({
			url:"../cart/insert.do"
			,data:{"idx":"${productVo.idx}","mem_idx":"${user.mem_idx}"}
			,dataType:"json"
			,success: function(d){
				//d={"result":"success"} or {"result":"exist"} or {"result":"fail"}
				if(d.result=="exist"){
					alert("이미 장바구니에 담겨 있습니다.")
					return;
				}
				if(d.result=="fail"){
					alert("장바구니 추가 실패!")
					return;
				}
				
				//성공했으면..
				if(d.result=="success"){
					if(confirm("장바구니에 추가 했습니다\n장바구니보기로 이동하시겠습니까?")=="false"){
						return;
					}
					
					location.href="../cart/list.do?mem_idx=${user.mem_idx}";
				}
				
			}
			,error: function(e){
				alert(e.responseText);
			}
			
		});
		
	}
	
</script>
</head>
<body>

<%-- <jsp:include page="header.jsp"/> --%>
<%@include file="header.jsp" %>

<table align="center" width="600" border="1"
 style="border-collapse:collapse;font-size:8pt"
 bordercolor="navy" cellpadding="4" cellspacing="0">
		<tr>
			<td width="40%">제품분류</td>
			<td width="60%">${category_list.get(productVo.cate_idx).cate_name }</td>
		</tr>
		<tr>
			<td width="40%">제품번호</td>
			<td width="60%">${productVo.p_model_num }</td>
		</tr>
		<tr>
			<td width="40%">제품명</td>
			<td width="60%">${productVo.p_name }</td>
		</tr>
		<tr>
			<td width="40%">제조사</td>
			<td width="60%">${productVo.p_company }</td>
		</tr>
		<tr>
			<td width="40%">제품가격</td>
			<td width="60%">
				${productVo.p_price }원
				(할인가:${productVo.p_saleprice }원)
			</td>
		</tr>
		<tr>
			<td colspan="2">제품설명</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
		<img src="../images/${productVo.p_image_l}">
			</td>
		</tr>
		<tr>
			<td colspan="2">${productVo.p_content}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" 
				value="장바구니에 담기" onclick="add_cart();"/>
			</td>
		</tr>
	</table>
</body>
</html>