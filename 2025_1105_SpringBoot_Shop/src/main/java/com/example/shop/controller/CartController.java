package com.example.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shop.dao.CartDao;
import com.example.shop.dao.CategoryDao;
import com.example.shop.vo.CartViewVo;
import com.example.shop.vo.CategoryVo;
import com.example.shop.vo.MemberVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	CartDao cartDao;
	
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	HttpSession session;
	
	@RequestMapping("/cart/list.do")
	public String list(Model model,RedirectAttributes ra) {
		
		MemberVo memberVo=(MemberVo) session.getAttribute("user");
		if(session.getAttribute("user")==null) {
			ra.addAttribute("sessionUser", "logout");
			return "redirect:../member/login_form.do";
		}
		//System.out.println(memberVo);
		int mem_idx=memberVo.getMem_idx();
		List<CategoryVo> category_list = categoryDao.selectList();
		List<CartViewVo> cart_list = cartDao.selectList(mem_idx);
		
		int total_amount = cartDao.selectAmountTotal(mem_idx);
		
		model.addAttribute("cart_list", cart_list);
		model.addAttribute("category_list", category_list);
		model.addAttribute("total_amount", total_amount);
		return "product/cart_list";
	}
	
	@RequestMapping("/cart/modify.do")
	public String cartModify(CartViewVo vo) {
		int res=cartDao.update(vo);
		return"redirect:list.do";
	}
	
	//장바구니에 추가
	//  /cart/insert.do?idx=2&mem_idx=1
	@RequestMapping("/cart/insert.do")
	@ResponseBody
	public Map<String,String> insert(CartViewVo vo){
		//p_idx와 mem_idx로 등록된 장바구니가 존재하나?
		CartViewVo cartExistVo = cartDao.selectOneExist(vo);
		Map<String, String> map = new HashMap<String, String>();
		
		// 이미 등록되어 있으면 ...
		if(cartExistVo!=null) {
			map.put("result", "exist");
			 return map;
		}
		System.out.println(cartExistVo);
		// 등록이 안되어 있으면 등록작업처리
		int res=cartDao.insert(vo);
		
		if(res==1) {
			map.put("result","success");
		}else {
			map.put("result","faul");
		}
		
		
		return map;
	}
	
}
