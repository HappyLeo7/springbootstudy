package com.example.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.shop.vo.MemberVo;

@Mapper
public interface MemberDao {
	
	
	//전체조회 TODO 제네릭 수정하기
	public List<MemberVo> selectList();
	
	//member에서 회원번호로 1개 조회
	public MemberVo selectOneIdx(int mem_idx);
	
	
	//로그인시 사용
	public MemberVo selectOneId(String mem_id);

	
	//추가
	public int insert(MemberVo vo);
	
	//삭제
	public int delete(int mem_idx);
	
	//수정처리
	public int update(MemberVo vo);
	
}//end : MemberDao class
