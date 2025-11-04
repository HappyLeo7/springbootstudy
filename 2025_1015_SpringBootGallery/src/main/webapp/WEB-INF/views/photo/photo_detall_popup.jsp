<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	#photo_detail>img{
		width: 300px;
		height: 300px;
		margin-left: 135px;
		
	}
	
	#photo_detail > p {
		border: 1px solid black; 
		padding: 10px;
	}
	
	
</style>
</head>
<body>


 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Photo 상세보기</h4>
        </div>
        
        <!-- 본문내용작성 -->
        <div class="modal-body">
        	<div id="photo_detail">
        		<img alt="" src="${pageContext.request.contextPath }/images/_01_london_seoul_1.jpg">
        		<p id="p_subject">여기는 제목들어가는 부분</p>
        		<p id="p_content">여기는 내용들어가는 부분</p>
        		<p id="p_regdate">여기는 내용들어가는 부분</p>
        	</div>
        </div>
        
        <div class="modal-footer">
        
          <input class="btn btn-success" type="button" value='다운로드' style="display: none;" 
          			onclick="photo_file_download();">
          
          <input class="btn btn-info" type="button" value="수정하기" style="display: none;"
          			onclick="photo_modify_form();">
          <input class="btn btn-danger" type="button" value="삭제하기" style="display: none;"
          			onclick="photo_delete();">
          
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </div>
      </div>
      
    </div>
  </div>


</body>
</html>