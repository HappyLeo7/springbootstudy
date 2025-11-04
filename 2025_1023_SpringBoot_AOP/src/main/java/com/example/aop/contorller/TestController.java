package com.example.aop.contorller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.aop.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	TestService test_service;
	
	public TestController() {
		// TODO Auto-generated constructor stub
		System.out.println("	[생성자]	TestController()");
	}
	
	@RequestMapping("/hello.do")
	@ResponseBody
	public String hello() {
		System.out.println("	[Controller]	hello()	");
		
		return test_service.hello();
	}
	
	@RequestMapping("/hi.do")
	@ResponseBody
	public String hi() {
		System.out.println("	[Controller]	hi()	");
		
		return test_service.hi();
	}
	
	@RequestMapping("/total.do")
	@ResponseBody
	public Map<String, String> total() {
		System.out.println("	[Controller]	total()	");
		
		Map<String, String> map = test_service.total();
		
		return map; //	json으로 반환
	}
	
	
}
