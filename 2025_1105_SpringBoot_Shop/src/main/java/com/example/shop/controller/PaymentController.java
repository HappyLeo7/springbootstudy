package com.example.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shop.dao.CartDao;
import com.example.shop.dao.CategoryDao;
import com.example.shop.dao.PaymentDao;
import com.example.shop.vo.CartViewVo;
import com.example.shop.vo.CategoryVo;
import com.example.shop.vo.MemberVo;
import com.example.shop.vo.PaymentResultVo;
import com.example.shop.vo.PaymentVo;

import jakarta.servlet.http.HttpSession;


@Controller
public class PaymentController {
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	CartDao cartDao;
	@Autowired
	HttpSession session;
	@Autowired
	PaymentDao paymentDao;
	//결제 정보 미리보기
	@RequestMapping("/payment/preview.do")
	public String preview(@RequestParam(name="c_idx") int [] c_idx_array ,Model model) {
	MemberVo memberVo=(MemberVo) session.getAttribute("user");
		int mem_idx=memberVo.getMem_idx();
		
		Map<String,Object> map =new HashMap<String,Object>();	
		map.put("c_idx_array", c_idx_array);
		
		List<CategoryVo> category_list = categoryDao.selectList();
		//List<CartViewVo> cart_list = cartDao.selectList(mem_idx);
		List<CartViewVo> cart_list = cartDao.selectPaymentList(map);
		
		
		
		int total_amount = cartDao.selectAmountTotal(mem_idx);
		
		//결제총액
		int paymentTotalAmount =cartDao.selectAmountTotal(map);
		
		
		model.addAttribute("cart_list", cart_list);
		model.addAttribute("category_list", category_list);
		model.addAttribute("total_amount", total_amount);
		
		return "product/payment_preview";
	}
	
	//결제
	@RequestMapping("/payment/insert.do")
	public String insert(@RequestParam(name="c_idx") int[] c_idx_array,Model model,RedirectAttributes ra,int mem_idx) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("c_idx_array", c_idx_array);
		List<CartViewVo> cart_list = cartDao.selectPaymentList(map);
		
		//주문번호 생성(오더넘버)
		int pay_order_num =paymentDao.selectOrderNum(); 
		int res=0;
		for(CartViewVo cart : cart_list) {
			PaymentVo payment = new PaymentVo();
			payment.setPay_order_num(pay_order_num);
			payment.setPay_model_num(cart.getP_model_num());
			payment.setPay_name(cart.getP_name());
			payment.setPay_cnt(cart.getC_cnt());
			payment.setPay_price(cart.getP_price());
			payment.setMem_idx(cart.getMem_idx());
			payment.setP_idx(cart.getIdx());
			
			res=paymentDao.insert(payment);
		}
		//장바구니 제거
		res=cartDao.deletePaymentComplete(map);
		
		//어떻게 동작?
		ra.addAttribute("mem_idx",mem_idx);
		return "redirect:../product/list.do";
	}
	
	@RequestMapping("/payment/list.do")
	public String list(int mem_idx,Model model) {
		System.out.println(mem_idx);
		List<CategoryVo> category_list = categoryDao.selectList();
		
		//결제내역 얻어오기
		List<PaymentResultVo> payment_result_list = paymentDao.selectList(mem_idx);
		
		for(PaymentResultVo vo: payment_result_list) {
			System.out.println(vo);
		}
		model.addAttribute("category_list", category_list);
		model.addAttribute("payment_result_list", payment_result_list);
		return"product/payment_list";
	}
	
}//end class
