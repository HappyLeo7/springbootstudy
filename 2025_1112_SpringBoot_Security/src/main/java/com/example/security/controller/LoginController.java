package com.example.security.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.security.vo.CustomUserDetails;

@Controller
public class LoginController {
	
	@GetMapping("/")
	  public String home(Model model) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	  // 로그인되지 않은 익명 사용자면 패스
	  if (auth == null || auth instanceof AnonymousAuthenticationToken) {
	      return "home"; // 또는 로그인 페이지 등으로 이동
	  }
	
	  CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal(); //.getPrincipal() 안에 
	  model.addAttribute("user", userDetails);
	
	  return "home";
	}
	
	@GetMapping("/login_form.do")
	public String loginForm() {
		return"login_form";
	}
	
	
	@GetMapping("/admin/info.do")
	@ResponseBody
	public String admin() {
		return"admin page!!";
	}

	@GetMapping("/user/info.do")
	@ResponseBody
	public String user() {
		return"user page!!";
	}
}
