package com.example.db4.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.db4.vo.CommentVo;

@Mapper
public interface CommentDao {
	List<CommentVo> selectList(Map<String,Object> map);
	
	int selectRowTotal(int b_idx);
	
	int comment_insert(CommentVo commentvo);
	int comment_delete(int c_idx);
}
