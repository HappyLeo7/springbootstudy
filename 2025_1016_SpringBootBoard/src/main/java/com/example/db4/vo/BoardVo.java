package com.example.db4.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("BoardVo")
public class BoardVo {
	int b_idx;
	String b_subject;
	String b_content;
	String b_ip;
	String b_regdate;
	int b_readhit;
	int mem_idx;
	String mem_name;
	int b_ref;
	int b_step;
	int b_depth;
	String b_use;
	int no;
	int comment_count;
	
	
	
}
