package com.example.tx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.tx.vo.ProductVo;

@Mapper
public interface ProductOutDao {
	
	List<ProductVo> selectList();					//전체조회
	ProductVo 		selectOne(int idx);				//1개 조회(idx)
	ProductVo 		selectOneFromName(String name);	//1개 조회(name)
	int				insert(ProductVo vo);			//등록
	int				update(ProductVo vo);			//수정
	int				delete(int idx);				//삭제
}
