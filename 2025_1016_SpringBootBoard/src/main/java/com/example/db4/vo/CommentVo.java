package com.example.db4.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("CommentVo")
public class CommentVo {
	int c_idx;
	String c_content, c_ip, c_regdate;
	int b_idx, mem_idx;
	String mem_id;

}
