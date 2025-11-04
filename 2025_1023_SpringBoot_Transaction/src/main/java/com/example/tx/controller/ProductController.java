package com.example.tx.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tx.service.ProductService;
import com.example.tx.vo.ProductVo;

@Controller
public class ProductController {
	
	@Autowired
	ProductService product_service;
	
	@RequestMapping("/product/list.do")
	public String list(Model model) {
		
		//전체 목록가져오기
		Map<String, Object> map = product_service.selectList();
		
		//request binding
		model.addAttribute("map", map);
		
		return "product/product_list";
	}
	
	
	/* 입고등록 */
	@RequestMapping("/product/insert_in.do")
	public String insert_in(ProductVo vo, RedirectAttributes ra) {
		
		try {
			int res = product_service.insert_in(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:list.do";
	}//
	
	@RequestMapping("/product/insert_out.do")
	public String insert_out(ProductVo vo, RedirectAttributes ra) {
		
		try {
			int res = product_service.insert_out(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
			ra.addAttribute("error", e.getMessage());
		}
		
		return "redirect:list.do";
	}//
	
	@RequestMapping("/product/delete_in.do")
	public String delete_in(int idx, RedirectAttributes ra) {
		
		try {
			int res = product_service.delete_in(idx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
			ra.addAttribute("error", e.getMessage());
		}
		
		return "redirect:list.do";
	}//
	@RequestMapping("/product/delete_out.do")
	public String delete_out(int idx, RedirectAttributes ra) {
		
		try {
			int res = product_service.delete_out(idx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
			ra.addAttribute("error", e.getMessage());
		}
		
		return "redirect:list.do";
	}//
	

	
	
	
	
}
