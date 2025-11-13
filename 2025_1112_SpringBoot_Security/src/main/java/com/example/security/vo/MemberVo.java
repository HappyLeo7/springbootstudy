package com.example.security.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("MemberVo")
public class MemberVo {
	int mem_idx;
	String mem_id;
	String mem_pwd;
	String mem_name;
	String mem_grade;
}
