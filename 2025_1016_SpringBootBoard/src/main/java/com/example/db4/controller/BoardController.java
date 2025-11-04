package com.example.db4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.db4.common.MyConstant;
import com.example.db4.dao.BoardDao;
import com.example.db4.util.Paging;
import com.example.db4.vo.BoardVo;
import com.example.db4.vo.MemberVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

    private final MemberController memberController;

	@Autowired
	BoardDao board_dao;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession httpsession;
	
	
	

    BoardController(MemberController memberController) {
        this.memberController = memberController;
    }
	
    
    // /board/list.do?search=name&search_text=길동&page=1
	
    @RequestMapping("test.do")
    public String test_do() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	MemberVo mem=(MemberVo)httpsession.getAttribute("user");
    	map.put("mem_idx", mem.getMem_idx());
    	map.put("mem_id", mem.getMem_id());
    	for(int i=0;i<30;i++) {
    		map.put("i",i);
    		board_dao.test_insert(map);
    	}
    	return "";
    }
    
	@RequestMapping("/board/list.do")
	public String board_list(Model model
							,@RequestParam(name = "page" ,defaultValue = "1") int nowPage
							,@RequestParam(name="search" , defaultValue = "all") String search
							,String search_text
							) {
		
		httpsession.removeAttribute("b_readhit");
		
		/* Page and Search Condition을 넣을 Map */
		Map<String, Object> map = new HashMap<String, Object>();

		/* Page 계산(start , end) */
		int start = (nowPage-1)*MyConstant.Board.BLOCK_LIST+1;
		int end = start + MyConstant.Board.BLOCK_LIST-1;
		
		map.put("start", start);
		map.put("end", end);
		
		
		/* 검색조건 map에 추가 */
		if(search.equals("subject_name_content")) {
			map.put("subject", search_text);
			map.put("name", search_text);
			map.put("content", search_text);
		}else if(search.equals("subject")) {
			map.put("subject", search_text);
		}else if(search.equals("name")) {
			map.put("name", search_text);
		}else if(search.equals("content")) {
			map.put("content", search_text);
		}			
		
		/* 목록가져오기 */
		List<BoardVo> board_list = board_dao.selectConditionList(map);

		/* 페이지 메뉴 만들기 */
		int rowTotal = board_dao.selectRowTotal(map); //전체 게시물수
		
		String search_filter=String.format("search=%s&search_text=%s",search,search_text);
		
		String pageMenu = Paging.getPaging2("list.do"
										  , nowPage
										  , rowTotal
										  , MyConstant.Board.BLOCK_LIST
										  , MyConstant.Board.BLOCK_PAGE
										  , search
										  , search_text
										  ,search_filter
											);
		
		
		System.out.println(pageMenu);
		
		//request binding
		model.addAttribute("board_list",board_list);
		if(rowTotal>0) {
		model.addAttribute("pageMenu",pageMenu);
		}
		
		return "board/board_list";
	}
	
	// 게시판 등록 폼
	@RequestMapping("/board/insert_form.do")
	public String board_insert_form() {
		
		
		return "board/board_insert_form";
	}
	
	// /board/insert.do?mem_idx=1&mem_name=경원이&b_subject=제목&b_content=내용
	@RequestMapping("/board/insert.do")
	public String board_insert(BoardVo board_vo){
			System.out.println("[~]	board insert.do");
			String b_ip = request.getRemoteAddr();
			board_vo.setB_ip(b_ip);
			
			// \n -> <br>
			String b_content = board_vo.getB_content().replaceAll("\n", "<br>");
			board_vo.setB_content(b_content);
			
			synchronized (this) {
				int res = board_dao.insert(board_vo);
				System.out.println(board_vo.getB_idx());
				res = board_dao.update_ref(board_vo);
				
			}
			
		return"redirect:list.do";
	}//
	
	
	//	/board/view.do?b_idx=10
	@RequestMapping("/board/view.do")
	public String board_view(int b_idx, Model model) {
		System.out.println("[~] board_view.do 실행");
		
		BoardVo board_vo = board_dao.selectOne(b_idx); //게시물 1개 조회
		
		//조회수 증가 
		if(httpsession.getAttribute("b_readhit")==null) {
		board_dao.update_readhit(b_idx);
		httpsession.setAttribute("b_readhit", "1");
		}
		
		// request binding
		model.addAttribute("board_vo",board_vo);
		
		return "board/board_view";
	}
	
	
	
	@RequestMapping("/board/reply_insert_form.do")
	public String board_reply_insert_form() {
		
		
		return "board/board_reply_insert_form";
	}
	
	@RequestMapping("/board/reply_insert.do")
	public String reply(BoardVo vo, int page,RedirectAttributes ra) {
		
		//답글을 달 기준글 정보 얻어오기
		BoardVo board_base_vo = board_dao.selectOne(vo.getB_idx());
		
		// 현재 기준글보다 큰 게시물의 b_step을 1 씩 증가 시킨다.
		board_dao.update_step(board_base_vo);
		
		//새로추가될 답글의 b_ref b_step b_depth 계산
		vo.setB_ref(board_base_vo.getB_ref());
		vo.setB_step(board_base_vo.getB_step()+1);
		vo.setB_depth(board_base_vo.getB_depth()+1);
		
		String b_ip=request.getRemoteAddr();
		vo.setB_ip(b_ip);
		
		String b_content=vo.getB_content().replaceAll("\n", "<br>");
		vo.setB_content(b_content);
		
		int res=board_dao.reply(vo);
		
		//기준글이 마지막글이면 페이지를 1증가
		if(board_base_vo.getNo()%MyConstant.Board.BLOCK_LIST==0) {page++;}
		
		ra.addAttribute("page",page);
		
		return"redirect:list.do";
	}//
	
	//	/board/delete.do?b_idx=20&page=3
	@RequestMapping("/board/delete.do")
	public String delete(int b_idx, int page, RedirectAttributes ra, String search,String search_text) {
		
		// b_idx의 b_use='n' 로 변경하는 코드
		int res = board_dao.delete_update_b_use(b_idx);
		
		//Redirect시 parameter 전달함 : list.do?page=3
		ra.addAttribute("page",page);
		ra.addAttribute("search", search);
		ra.addAttribute("search_text", search_text);
		
		return "redirect:list.do";
	}
	
	@RequestMapping("/board/modify_form.do")
	public String modify_form(int b_idx, Model model) {
		
		BoardVo board_vo=board_dao.selectOne(b_idx);
		// "<br>" = > "\n"
		//board_vo.setB_content(board_vo.getB_content());
		board_vo.setB_content(board_vo.getB_content().replaceAll("<br>", "\n"));
		model.addAttribute("board_vo",board_vo);
		
		return"/board/board_modify_form";
	}//
	
	@RequestMapping("/board/board_modify.do")
	public String board_modify(BoardVo board_vo,int page, RedirectAttributes ra,String search,String search_text) {
		String b_ip = request.getLocalAddr();
		board_vo.setB_ip(b_ip);
		board_dao.update_subject_content(board_vo);
		ra.addAttribute("page", page);
		ra.addAttribute("b_idx", board_vo.getB_idx());
		ra.addAttribute("search", search);
		ra.addAttribute("search_text", search_text);
		return"redirect:view.do";
	}
	
	
}
