package com.example.db4.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.db4.vo.BoardVo;

@Mapper
public interface BoardDao {
	List<BoardVo> selectList();
	List<BoardVo> selectConditionList(Map<String,Object> map);
	int 		  selectRowTotal(Map<String, Object> map);
	BoardVo		  selectOne(int b_idx);
	
	int insert(BoardVo board_vo);
	int update_ref(BoardVo board_vo);
	void update_readhit(int b_idx);
	void update_step(BoardVo board_base_vo);
	int reply(BoardVo vo);
	int delete_update_b_use(int b_idx);
	void update_subject_content(BoardVo board_vo);
	void test_insert(Map<String,Object> map);
}
