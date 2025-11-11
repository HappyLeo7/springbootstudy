package com.example.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.shop.vo.ProductVo;

@Mapper
public interface ProductDao {

	List<ProductVo> selectList(int cate_idx);
	ProductVo selectOne(int p_idx);
	int insert(ProductVo vo);
	int update(ProductVo vo);
	int delete(int cate_idx);
}
