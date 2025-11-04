package com.example.db4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.db4.common.MyConstant;
import com.example.db4.dao.CommentDao;
import com.example.db4.util.Paging;
import com.example.db4.vo.CommentVo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    private final BoardController boardController;
	
	@Autowired
	CommentDao comment_dao;
	
	@Autowired
	HttpServletRequest request;


    CommentController(BoardController boardController) {
        this.boardController = boardController;
    }
	
	
	
	
	//댓글 리스트
	@RequestMapping("/comment/list.do")
	public String list(int b_idx,int page,Model model) {
		System.out.println("	[~]	/comment/list.do ");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("b_idx", b_idx);

		// start , end 계산
		int start = (page-1)*MyConstant.Comment.BLOCK_LIST+1;
		int end = start + MyConstant.Comment.BLOCK_LIST-1;
		map.put("start", start);
		map.put("end", end);
		
		List<CommentVo> comment_list = comment_dao.selectList(map);
		
		//댓글 메뉴 만들기
		int rowTotal = comment_dao.selectRowTotal(b_idx);
		String pageMenu=Paging.getPagingComment(page, rowTotal, MyConstant.Comment.BLOCK_LIST, MyConstant.Comment.BLOCK_PAGE);
		
		model.addAttribute("comment_list",comment_list);
		model.addAttribute("rowTotal", rowTotal);
		model.addAttribute("pageMenu", pageMenu);
		
		return "comment/comment_list";
	}
	
	
	
	
	
	
	
	
	
	//댓글 추가
	@RequestMapping("/comment/insert.do")
	@ResponseBody
	public Map<String,Boolean> comment_insert(CommentVo commnet_vo){
		String c_ip = request.getLocalAddr();
		commnet_vo.setC_ip(c_ip);
		
		// DML 명령의 결과 : 처리된 행수를 반환
		int res = comment_dao.comment_insert(commnet_vo);
		
		Map<String,Boolean>map =new HashMap<String, Boolean>();
		map.put("result", res==1);
				
		return map;
	}
	
	
	
	
	
	/* 댓글 삭제 */
	@RequestMapping("/comment/delete.do")
	@ResponseBody
	public Map<String,Object> comment_delete(int c_idx){
		System.out.println("	[~]		comment/delete.do	");
		int res = comment_dao.comment_delete(c_idx);
		Map<String, Object> map =new HashMap<String,Object>();
		map.put("result", res==1);
	return map;	
	}
}
