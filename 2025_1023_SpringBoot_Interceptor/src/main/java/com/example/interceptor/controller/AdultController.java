package com.example.interceptor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdultController {

	@RequestMapping("adult/photo/list.do")
	public String photo_list() {
		return "photo_list";
	}
}
