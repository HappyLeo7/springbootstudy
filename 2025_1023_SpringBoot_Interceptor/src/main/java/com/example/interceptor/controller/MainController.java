package com.example.interceptor.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	HttpSession httpSession;
	
	@RequestMapping("/")
	public String main() {
		return "main_list";
	}
	
	@RequestMapping("/login.do")
	public String login(@RequestParam Map<Object, Object> map) {
		System.out.println("		[map]==>"+map);
		//로그인정보를 세션에 넣는다.
		httpSession.setAttribute("user", map);
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	public String logout() {
		httpSession.removeAttribute("user");
		return "redirect:/";
	}
}
