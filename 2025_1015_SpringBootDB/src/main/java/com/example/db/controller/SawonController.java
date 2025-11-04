package com.example.db.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.db.dao.SawonDao;
import com.example.db.vo.SawonVo;

import controller.TestController;

@Controller
public class SawonController {

    private final TestController testController;

	@Autowired
	SawonDao sawon_dao;
	
	public SawonController(TestController testController) {
		super();
		System.out.println("---SawonController()---");
		System.out.println("[C] SawonController()");
		this.testController = testController;
	}

	@RequestMapping("/sawon/list.do")
	public String list(Model model) {
		
		System.out.println("[m]list");
		List<SawonVo> list = sawon_dao.selectList();
		System.out.println("[.]사원 list 레코드 정보===>  "+list.size());
		
		model.addAttribute("list",list);
		System.out.println("[.] 사원 list 정보 ===>  "+list);
		return "sawon/sawon_list";
	}
	
	
}
