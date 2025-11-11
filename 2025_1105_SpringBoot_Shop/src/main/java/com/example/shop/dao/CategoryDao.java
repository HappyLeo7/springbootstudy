package com.example.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.shop.vo.CategoryVo;

@Mapper
public interface CategoryDao {
	List<CategoryVo> selectList();
}
