package com.liw.gallery.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("PhotoVo")
@Data
public class PhotoVo {

	int p_idx;
	String p_subject;
	String p_content;
	String p_filename;
	String p_ip;
	String p_regdate;
	String p_lastmodifydate;
	int mem_idx;
	
	
	
}
