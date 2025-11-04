package com.example.tx.service;

import java.util.Map;

import com.example.tx.vo.ProductVo;

public interface ProductService {
	
	//전체조회
	Map<String, Object> selectList();
	
	//입고처리
	int insert_in(ProductVo vo) throws Exception;
	
	//출고처리
	int insert_out(ProductVo vo) throws Exception;
	
	
	//입고처리
	int delete_in(int idx) throws Exception;
	
	//출고처리
	int delete_out(int idx) throws Exception;

	
	
}
