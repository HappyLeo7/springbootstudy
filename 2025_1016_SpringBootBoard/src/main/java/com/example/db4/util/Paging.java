package com.example.db4.util;

/*
        nowPage:현재페이지
        rowTotal:전체데이터갯수
        blockList:한페이지당 게시물수
        blockPage:한화면에 나타낼 페이지 목록수
 */
public class Paging {
	public static String getPaging(String pageURL // 경로
			, int nowPage // 현재페이지
			, int rowTotal // 전체페이지
			, int blockList // 한 화면에 보이는 리스트 갯수
			, int blockPage, String search, String search_text, String search_filter) { // 페이지 갯수

		int totalPage/* 전체페이지수 */, startPage/* 시작페이지번호 */, endPage;/* 마지막페이지번호 */

		boolean isPrevPage, isNextPage;
		StringBuffer sb; // 모든 상황을 판단하여 HTML코드를 저장할 곳

		isPrevPage = isNextPage = false;
		// 입력된 전체 자원을 통해 전체 페이지 수를 구한다..
		totalPage = (int) (rowTotal / blockList);
		// totalPage를 나누고 나머지값이 0이 아니면 totalPage를 1개더 증가
		if (rowTotal % blockList != 0)
			totalPage++;

		// 만약 잘못된 연산과 움직임으로 인하여 현재 페이지 수가 전체 페이지 수를
		// 넘을 경우 강제로 현재페이지 값을 전체 페이지 값으로 변경
		if (nowPage > totalPage)
			nowPage = totalPage;

		// 시작 페이지와 마지막 페이지를 구함.
		startPage = (int) (((nowPage - 1) / blockPage) * blockPage + 1);
		endPage = startPage + blockPage - 1; //

		// 마지막 페이지 수가 전체페이지수보다 크면 마지막페이지 값을 변경
		if (endPage > totalPage)
			endPage = totalPage;

		// 마지막페이지가 전체페이지보다 작을 경우 다음 페이징이 적용할 수 있도록
		// boolean형 변수의 값을 설정
		if (endPage < totalPage)
			isNextPage = true;
		// 시작페이지의 값이 1보다 작으면 이전페이징 적용할 수 있도록 값설정
		if (startPage > 1)
			isPrevPage = true;

		// HTML코드를 저장할 StringBuffer생성=>코드생성
		sb = new StringBuffer("<ul class=\"pagination\">");
//-----그룹페이지처리 이전 --------------------------------------------------------------------------------------------		
		if (isPrevPage) {

			sb.append(String.format("<li ><a href='%s?search=%s&search_text=%s&page=%d' >◀</a></li>", pageURL, search,
					search_text, startPage - 1));

			// sb.append("<a href ='"+pageURL+"?page=");
			// sb.append(nowPage - blockPage);
			// sb.append("'>◀</a>");
		} else

			sb.append("<li ><a href='#' >◀</a></li>");
//			sb.append("◀");

//------페이지 목록 출력 -------------------------------------------------------------------------------------------------
		// sb.append("|");
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage)
				break;
			if (i == nowPage) { // 현재 있는 페이지
				sb.append(String.format("<li class='active'><a href='%s?search=%s&search_text=%s&page=%d' >%d</a></li>",
						pageURL, search, search_text, i, i));

				// sb.append("&nbsp;<b><font color='#91b72f'>");
				// sb.append(i);
				// sb.append("</font></b>");
			} else {// 현재 페이지가 아니면
				sb.append(String.format("<li><a href='%s?search=%s&search_text=%s&page=%d' >%d</a></li>", pageURL,
						search, search_text, i, i));

				// sb.append("&nbsp;<a href='"+pageURL+"?page=");
				// sb.append(i);
				// sb.append("'>");
				// sb.append(i);
				// sb.append("</a>");
			}
		} // end for

		// sb.append("&nbsp;|");

//-----그룹페이지처리 다음 ----------------------------------------------------------------------------------------------
		if (isNextPage) {

			sb.append(String.format("<li ><a href='%s?search=%s&search_text=%s&page=%d' >▶</a></li>", pageURL, search,
					search_text, endPage + 1));

			// sb.append("<a href='"+pageURL+"?page=");
			// if(nowPage+blockPage > totalPage)nowPage = totalPage;
			// else
			// nowPage = nowPage+blockPage;
			// sb.append(nowPage);
			// sb.append("'>▶</a>");
		} else
			sb.append("<li ><a href='#'>▶</a></li>");
		// sb.append("▶");
//---------------------------------------------------------------------------------------------------------------------	    

		sb.append("</ul>");
		return sb.toString();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public static String getPaging2(String pageURL // 경로
			, int nowPage // 현재페이지
			, int rowTotal // 전체페이지
			, int blockList // 한 화면에 보이는 리스트 갯수
			, int blockPage, String search, String search_text, String search_filter) { // 페이지 갯수

		int totalPage/* 전체페이지수 */, startPage/* 시작페이지번호 */, endPage;/* 마지막페이지번호 */

		boolean isPrevPage, isNextPage;
		StringBuffer sb; // 모든 상황을 판단하여 HTML코드를 저장할 곳

		isPrevPage = isNextPage = false;
//입력된 전체 자원을 통해 전체 페이지 수를 구한다..
		totalPage = (int) (rowTotal / blockList);
//totalPage를 나누고 나머지값이 0이 아니면 totalPage를 1개더 증가
		if (rowTotal % blockList != 0)
			totalPage++;

//만약 잘못된 연산과 움직임으로 인하여 현재 페이지 수가 전체 페이지 수를
//넘을 경우 강제로 현재페이지 값을 전체 페이지 값으로 변경
		if (nowPage > totalPage)
			nowPage = totalPage;

//시작 페이지와 마지막 페이지를 구함.
		startPage = (int) (((nowPage - 1) / blockPage) * blockPage + 1);
		endPage = startPage + blockPage - 1; //

//마지막 페이지 수가 전체페이지수보다 크면 마지막페이지 값을 변경
		if (endPage > totalPage)
			endPage = totalPage;

//마지막페이지가 전체페이지보다 작을 경우 다음 페이징이 적용할 수 있도록
//boolean형 변수의 값을 설정
		if (endPage < totalPage)
			isNextPage = true;
//시작페이지의 값이 1보다 작으면 이전페이징 적용할 수 있도록 값설정
		if (startPage > 1)
			isPrevPage = true;

//HTML코드를 저장할 StringBuffer생성=>코드생성
		sb = new StringBuffer("<ul class=\"pagination\">");
//-----그룹페이지처리 이전 --------------------------------------------------------------------------------------------		
		if (isPrevPage) {

			sb.append(String.format("<li ><a href='%s?page=%d&%s' >◀</a></li>", pageURL, startPage - 1,search_filter));

//sb.append("<a href ='"+pageURL+"?page=");
//sb.append(nowPage - blockPage);
//sb.append("'>◀</a>");
		} else

			sb.append("<li ><a href='#' >◀</a></li>");
//sb.append("◀");

//------페이지 목록 출력 -------------------------------------------------------------------------------------------------
//sb.append("|");
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage)
				break;
			if (i == nowPage) { // 현재 있는 페이지
				sb.append(String.format("<li class='active'><a href='%s?page=%d&%s' >%d</a></li>",
						pageURL,i,search_filter, i));

//sb.append("&nbsp;<b><font color='#91b72f'>");
//sb.append(i);
//sb.append("</font></b>");
			} else {// 현재 페이지가 아니면
				sb.append(String.format("<li><a href='%s?page=%d&%s' >%d</a></li>", pageURL, i, search_filter,i));

//sb.append("&nbsp;<a href='"+pageURL+"?page=");
//sb.append(i);
//sb.append("'>");
//sb.append(i);
//sb.append("</a>");
			}
		} // end for

//sb.append("&nbsp;|");

//-----그룹페이지처리 다음 ----------------------------------------------------------------------------------------------
		if (isNextPage) {

			sb.append(String.format("<li ><a href='%s?page=%d&%s' >▶</a></li>", pageURL,endPage + 1,search_filter));

//sb.append("<a href='"+pageURL+"?page=");
//if(nowPage+blockPage > totalPage)nowPage = totalPage;
//else
//	nowPage = nowPage+blockPage;
//sb.append(nowPage);
//sb.append("'>▶</a>");
		} else
			sb.append("<li ><a href='#'>▶</a></li>");
//sb.append("▶");
//---------------------------------------------------------------------------------------------------------------------	    

		sb.append("</ul>");

		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String getPagingComment(
			int nowPage // 현재페이지
			, int rowTotal // 전체페이지
			, int blockList // 한 화면에 보이는 리스트 갯수
			, int blockPage
			) { 
		
		int totalPage/* 전체페이지수 */, startPage/* 시작페이지번호 */, endPage;/* 마지막페이지번호 */
		
		boolean isPrevPage, isNextPage;
		StringBuffer sb; // 모든 상황을 판단하여 HTML코드를 저장할 곳
		
		isPrevPage = isNextPage = false;
		//입력된 전체 자원을 통해 전체 페이지 수를 구한다..
		totalPage = (int) (rowTotal / blockList);
		//totalPage를 나누고 나머지값이 0이 아니면 totalPage를 1개더 증가
		if (rowTotal % blockList != 0)
			totalPage++;
		
		//만약 잘못된 연산과 움직임으로 인하여 현재 페이지 수가 전체 페이지 수를
		//넘을 경우 강제로 현재페이지 값을 전체 페이지 값으로 변경
		if (nowPage > totalPage)
			nowPage = totalPage;
		
		//시작 페이지와 마지막 페이지를 구함.
		startPage = (int) (((nowPage - 1) / blockPage) * blockPage + 1);
		endPage = startPage + blockPage - 1; //
		
		//마지막 페이지 수가 전체페이지수보다 크면 마지막페이지 값을 변경
		if (endPage > totalPage)
			endPage = totalPage;
		
		//마지막페이지가 전체페이지보다 작을 경우 다음 페이징이 적용할 수 있도록
		//boolean형 변수의 값을 설정
		if (endPage < totalPage)
			isNextPage = true;
		//시작페이지의 값이 1보다 작으면 이전페이징 적용할 수 있도록 값설정
		if (startPage > 1)
			isPrevPage = true;
		
		//HTML코드를 저장할 StringBuffer생성=>코드생성
		sb = new StringBuffer("<ul class=\"pagination\">");
//-----그룹페이지처리 이전 --------------------------------------------------------------------------------------------		
		if (isPrevPage) {
			
			sb.append(String.format("<li ><a href='#cmt_menu' onclick='comment_list(%d);' >◀</a></li>", startPage - 1));
			
			//sb.append("<a href ='"+pageURL+"?page=");
			//sb.append(nowPage - blockPage);
			//sb.append("'>◀</a>");
		} else
			
			sb.append("<li ><a href='#cmt_menu' >◀</a></li>");
		//sb.append("◀");
		
//------페이지 목록 출력 -------------------------------------------------------------------------------------------------
		//sb.append("|");
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage)
				break;
			if (i == nowPage) { // 현재 있는 페이지
				sb.append(String.format("<li class='active'><a href='#cmt_menu' >%d</a></li>",
						i));
				
			//sb.append("&nbsp;<b><font color='#91b72f'>");
			//sb.append(i);
			//sb.append("</font></b>");
			} else {// 현재 페이지가 아니면
				sb.append(String.format("<li><a href='#cmt_menu' onclick='comment_list(%d)' >%d</a></li>", i,i));
				
			//sb.append("&nbsp;<a href='"+pageURL+"?page=");
			//sb.append(i);
			//sb.append("'>");
			//sb.append(i);
			//sb.append("</a>");
			}
		} // end for
		
			//sb.append("&nbsp;|");
		
//-----그룹페이지처리 다음 ----------------------------------------------------------------------------------------------
		if (isNextPage) {
			
			sb.append(String.format("<li ><a href='#cmt_menu' onclick='comment_list(%d)' >▶</a></li>", endPage + 1));
			
//sb.append("<a href='"+pageURL+"?page=");
//if(nowPage+blockPage > totalPage)nowPage = totalPage;
//else
//	nowPage = nowPage+blockPage;
//sb.append(nowPage);
//sb.append("'>▶</a>");
		} else
			sb.append("<li ><a href='#cmt_menu'>▶</a></li>");
//sb.append("▶");
//---------------------------------------------------------------------------------------------------------------------	    
		
		sb.append("</ul>");
		
		return sb.toString();
	}


}//end : Paging class