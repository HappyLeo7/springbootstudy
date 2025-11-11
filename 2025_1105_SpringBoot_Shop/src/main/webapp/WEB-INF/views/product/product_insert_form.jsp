<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

const regular_number = /^[0-9]{1,}$/
function send(f)
{
  let cate_idx = f.cate_idx.value;
  let p_model_num = f.p_model_num.value.trim();
  let p_name = f.p_name.value;
  let p_company = f.p_company.value;
  let p_price = f.p_price.value;
  let p_saleprice = f.p_saleprice.value;
  let p_content = f.p_content.value;
  let p_image_s = f.p_image[0].value;
  let p_image_l = f.p_image[1].value;
   
   //입력체크...
	if(cate_idx==""){
		alert("카테고리를 선택해주세요");
		f.cate_idx.value="";
		f.cate_idx.focus();
		return;
	}
	if(p_model_num==""){
		alert("제품번호를 입력해주세요");
		f.p_model_num.value="";
		f.p_model_num.focus();
		return;
	}
	if(p_name==""){
		alert("제품이름를 입력해주세요");
		f.p_name.focus();
		return;
	}
	if(p_company==""){
		alert("제품판매사 입력해주세요");
		f.p_company.focus();
		return;
	}
	if(regular_number.test(p_price)==false){
		alert("숫자만 입력해주세요")
		f.p_price.value="";
		f.p_price.focus();
		return;
	}
   if(p_image_s==""){
	   alert("사진을 선택해주세요");
	   f.p_image_s.focus();
	   return;
   }
   
   f.submit();
   
}
</script>
</head>
<body>

<!-- title -->
<jsp:include page="header.jsp"/>
<form name="f" method="post" action="insert.do" 
      enctype="multipart/form-data"> 
<table align="center" width="600" border="1" 
    style="border-collapse:collapse;font-size:8pt" bordercolor="navy"
    cellpadding="2" cellspacing="0">
    <tr>
        <td>제품Category</td>
        <td><select name="cate_idx">
        		<option value="">카테고리 선택</option>
        		
				<c:forEach var="category" items="${category_list }">
					<c:if test="${category.cate_idx>0 }">
        		<option value="${category.cate_idx }">${category.cate_name }</option>
					</c:if>
				</c:forEach>
        </select></td>
    </tr>
    <tr>
        <td>제품번호</td>
        <td><input name="p_model_num" type="text" ></td>
    </tr>
    <tr>
        <td>제품이름</td>
        <td><input name="p_name" type="text" ></td>
    </tr>
    <tr>
        <td>제품 판매사</td>
        <td><input name="p_company" type="text" ></td>
    </tr>
    <tr>
        <td>제품가격</td>
        <td><input name="p_price" type="text" ></td>
    </tr>
    <tr>
        <td>제품할인가격</td>
        <td><input name="p_saleprice" type="text" ></td>
    </tr>    
    <tr>
        <td>제품설명</td>
        <td><TEXTAREA name="p_content" rows="5" cols="50"></TEXTAREA></td>
    </tr>
    <tr>
        <td>제품사진(작은사진)</td>
        <td><input type="file" name="p_image">
    </tr>
    <tr>
        <td>제품사진(큰사진)</td>
        <td><input type="file" name="p_image">
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="button" value="등록" 
            onclick="javascript:send(this.form);" >
            <input type="reset" value="Clear" >
        </td>
    </tr>    
</table>
</form>
</body>
</html>