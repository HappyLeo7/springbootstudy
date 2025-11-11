package com.example.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shop.dao.CategoryDao;
import com.example.shop.dao.ProductDao;
import com.example.shop.vo.CategoryVo;
import com.example.shop.vo.ProductVo;

import jakarta.servlet.ServletContext;

@Controller
public class ProductController {
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ServletContext application;
	
	@RequestMapping("/product/list.do")
	public String list(Model model,@RequestParam(name="cate_idx",defaultValue="0")int cate_idx) {

		List<CategoryVo> category_list = categoryDao.selectList();
		
		//상품목록가져오기
		List<ProductVo> product_list= productDao.selectList(cate_idx);
		
		model.addAttribute("category_list", category_list);
		model.addAttribute("product_list", product_list);
		
		return "product/product_list";
	}
	
	//http://localhost:8080/product/view.do?p_idx=1
	//상품상세보기
	@RequestMapping("/product/view.do")
	public String view(int p_idx,Model model) {
		System.out.printf("[p_idx=>]%d\n",p_idx);
		ProductVo productVo = productDao.selectOne(p_idx);
		System.out.printf("[Vo=>]%s\n",productVo);
		
		// -- 메뉴에 있는 카테고리 정보 -- //
		List<CategoryVo> category_list = categoryDao.selectList();
		model.addAttribute("category_list", category_list);
		// -- /메뉴에 있는 카테고리 정보 -- //
		model.addAttribute("productVo",productVo);
		return "/product/product_content";
	}
	
	//http://localhost:8080/product/insert_form.do
	//상품등록버튼=>등록페이지
	@RequestMapping("/product/insert_form.do")
	public String insert(Model model) {
		// -- 메뉴에 있는 카테고리 정보 -- //
		List<CategoryVo> category_list = categoryDao.selectList();
		model.addAttribute("category_list", category_list);
		// -- /메뉴에 있는 카테고리 정보 -- //
		return"/product/product_insert_form";
	}
	
	//상품등록=>DB
	@PostMapping("/product/insert.do")
	public String insert(ProductVo productVo
							,@RequestParam(name="p_image") MultipartFile[] p_image_array
							,RedirectAttributes ra) throws IllegalStateException, IOException{
		
		//파일 저장위치
		String saveDir = application.getRealPath("/images/");
		
		String p_image_s="no_file";
		String p_image_l="no_file";
		String filename="";
		for(int i=0; i<p_image_array.length;i++) {
			MultipartFile p_image=p_image_array[i];
			if(p_image.isEmpty()==false) {
				filename=p_image.getOriginalFilename();
				File f = new File(saveDir,filename);
				//동일파일있는지 체크
				if(f.exists()) {
					long tm = System.currentTimeMillis();
					filename=String.format("%d_%s", tm, filename);
					f = new File(saveDir,filename);
				}
				//임시 파일을 f경로로 복사해온다.
				p_image.transferTo(f);
				if(i==0) {
					p_image_s=filename;
				}
				if(i==1) {
					p_image_l=filename;
				}
			}
		}//end:for
		
		//처리결과를 vo에 담는다.
		productVo.setP_image_s(p_image_s);
		productVo.setP_image_l(p_image_l);
		
		//p_content : \n => <br>
		productVo.setP_content(productVo.getP_content().replaceAll("\n", "<br>"));
		
		//DB 저장
		int res = productDao.insert(productVo);
		
		ra.addAttribute("cate_idx", productVo.getCate_idx());
		return "redirect:list.do";
	}
}
