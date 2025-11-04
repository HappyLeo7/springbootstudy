package com.example.interceptor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/admin/member/list.do")
	public String member_list() {
		return "member_list";
	}
}
