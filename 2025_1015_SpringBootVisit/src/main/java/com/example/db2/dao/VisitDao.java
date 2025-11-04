package com.example.db2.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.db2.vo.VisitVo;


// DAO (Data Access Object)
// : CRUD 처리하는 객체
@Mapper
public interface VisitDao {
	
	// 조회
	//검색 조회
	public List<VisitVo> selectList(Map<String, Object> map);
	
	//idx에 해당하는 1건 데이터 조회
	public VisitVo selectOne(int idx);
	
	//추가
	public int insert(VisitVo vo);	
	//삭제
	public int delete(int idx);

	


	//update
	public int update(VisitVo vo);
	
	
	
	
}//end class
