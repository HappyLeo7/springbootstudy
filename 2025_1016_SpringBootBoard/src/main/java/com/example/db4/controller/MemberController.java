package com.example.db4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.db4.dao.MemberDao;
import com.example.db4.vo.MemberVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {


	@Autowired
	MemberDao member_dao;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;

	
	//조회
	@RequestMapping("/member/list.do")
	public String list(Model model) {
		
		//회원목록
			List<MemberVo> list = member_dao.selectList();
			model.addAttribute("list", list);
		
		return "member/member_list";
	}
	
	@RequestMapping("/member/login_form.do")
	public String login_form() {
		
		return "member/member_login_form";
	}
	
	//로그인 처리
	@RequestMapping("/member/login.do")
	public String login(String mem_id,String mem_pwd,RedirectAttributes ra, String url) {
		
		//mem_id에 해당되는 객체정보 얻어오기
		MemberVo user =member_dao.selectOneId(mem_id);
		
		// 아이디가 틀린경우
		if(user==null) {
			// response.sendRedirect("login_form.do?reason=fail_id"); //아이디가 틀려서 로그인 실패시 다시 form으로 이동시킨다.
			// RedirectAttributes에 값을 넣으면 DS가 그값을 꺼내서 query(parameter)로 사용한다.
			//return"redirect:login_form.do?reason=fail_id";
			ra.addAttribute("reason", "fail_id");
			ra.addAttribute("url",url);
			
			return"redirect:login_form.do";
		}
		
		// 비밀번호가 틀린경우
		if(user.getMem_pwd().equals(mem_pwd)==false) {
			//response.sendRedirect("login_form.do?reason=fail_pwd&mem_id="+mem_id);
			ra.addAttribute("reason", "fail_pwd");
			ra.addAttribute("mem_id", mem_id);
			ra.addAttribute("url",url);
			return"redirect:login_form.do";
		}
		
		//정상로그인 처리시 세션에 로그인정보 넣는다.
		session.setAttribute("user", user);
		
		//돌아갈 주소가 있으면 해당주소로 redirect 해라
		if(!url.isEmpty()) {
			return "redirect:" + url;
		}
		
		return "redirect:../board/list.do";
	}
	
	//로그인아웃 처리
		@RequestMapping("/member/logout.do")
		public String logout() {
			session.removeAttribute("user");
			
			return"redirect:../board/list.do";
		}
		
		
		//회원가입폼
		@RequestMapping("/member/insert_form.do")
		public String insert_form() {
			
			return "member/member_insert_form";
		}
		
		//중복아이디 체크
		@RequestMapping("/member/check_id.do")
		@ResponseBody
		public Map<String,Boolean> check_id(String mem_id) {
			
			MemberVo vo	=member_dao.selectOneId(mem_id);
		
			Map<String,Boolean> map =new HashMap<String,Boolean>();
			
			if(vo==null) {
				map.put("result",true); // {"result" : true}
			}else {
				map.put("result", false); // {"result" : false}
			}
			
			return map;
		}
		
		
		//회원가입
		@RequestMapping("/member/insert.do")
		public String insert(MemberVo vo) {
			
			String mem_ip =request.getRemoteAddr();
			
			vo.setMem_ip(mem_ip);
			int res=member_dao.insert(vo);
			
			return "redirect:../board/list.do";
		}
		
		//회원수정 폼
		@RequestMapping("/member/modify_form.do")
		public String modify_form(int mem_idx,Model model) {
			MemberVo vo = member_dao.selectOneIdx(mem_idx);
			model.addAttribute("vo",vo);
			return "/member/member_modify_form";
		}
		
		//회원수정
		@RequestMapping("/member/modify.do")
		public String modify(MemberVo vo) {
			String mem_ip = request.getRemoteAddr();
			vo.setMem_ip(mem_ip);
			
			int res=member_dao.update(vo);
			
			return "redirect:list.do";
		}
		
		
		
	
	
}
