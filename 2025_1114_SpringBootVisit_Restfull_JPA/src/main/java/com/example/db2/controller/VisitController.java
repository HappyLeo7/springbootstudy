package com.example.db2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.db2.dao.VisitDao;
import com.example.db2.vo.VisitVo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/visit")
//@RequiredArgsConstructor
public class VisitController {

	
	//private final VisitDao visit_dao;
	@Autowired
	VisitDao visit_dao;
	
	
	@Autowired
	HttpServletRequest request;

	// Constructor Injection
//	public VisitController(VisitDao visit_dao) {
//		super();
//		this.visit_dao = visit_dao;
//	}
//	
	@RequestMapping("/")
	public String index() {
		return"";
	}
	
	@RequestMapping("/list.do")
	public String list(@RequestParam(name="search",defaultValue = "all") String search
			,String search_text, Model model) {
		
		//검색정보(조건)를 담을 Map을 선언
				Map<String,Object> map = new HashMap<String,Object>();
				
				
				if(search.equals("name_content")) {
					map.put("name", search_text);
					map.put("content", search_text);
				}else if(search.equals("name")) {
					map.put("name", search_text);
				}else if(search.equals("content")) {
					map.put("content", search_text);
				}
				
				
		//방명록 목록 가져오기
		List<VisitVo> list= visit_dao.selectList(map);
		
		//결과적으로 request binding
		model.addAttribute("list", list);
		
		return "visit/visit_list";
	}//end : list
	
	
	//등록폼 띄우기
	@RequestMapping("/insert_form.do")
	public String insert_form() {
		return "visit/visit_insert_form";
	}
	
	//등록하기
	@RequestMapping("/insert.do")
	public String insert(VisitVo vo, Model model) {
		//작성자 IP구하기
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		//내용 : \n -> <br> 로 변경하는 코드 작성
		String content = vo.getContent().replaceAll("\n", "<br>");
		vo.setContent(content);
		
		//DB insert
		int res = visit_dao.insert(vo);
		
		// "redirect:list.do" -> DS전달
		// DS가 수행 : response.sendRedirect("list.do");
		return"redirect:list.do";
	}
	
	//비밀번호체크
	@RequestMapping("/check_pwd.do")
	@ResponseBody //현재 반환되는 값을 결과값으로 직접 전송해줘
	public Map check_pwd(int idx,String c_pwd) {
		
		VisitVo vo = visit_dao.selectOne(idx);
		
		// 3. 비밀번호 비교
		boolean bResult = vo.getPwd().equals(c_pwd);
		
		Map<String,Boolean> map=new HashMap<String,Boolean>();
		map.put("result", bResult);
		// map => { "result" , true }
		
		
		return map ;
		
	
	}
	
	//삭제
	@RequestMapping("/delete.do")
	public String delete(int idx) {
		
		int res = visit_dao.delete(idx);
		
		return "redirect:list.do";
	}
	
	//수정폼
	@RequestMapping("/modify_form.do")
	public String modify_form(int idx, Model model) {
		
		//수정할 idx 받아오기
		
		// idx를 이용해서 수정할 1개의 데이터 가져오기
		VisitVo vo=visit_dao.selectOne(idx);
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
		
		//가져온데이터 넣어두기
		model.addAttribute("vo", vo);
		
		return "visit/visit_modify_form";
	}
	
	
	//수정
	@RequestMapping("/modify.do")
	public String modify(VisitVo vo) {
		String ip=request.getRemoteAddr();
		vo.setIp(ip);
		
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
		
		visit_dao.update(vo);
		return "redirect:list.do" ;
	}
	
	
}//end : controller
