package com.example.security.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.security.vo.MemberVo;

@Mapper
public interface MemberDao {
	MemberVo selectOneFromId(String mem_id);
}
