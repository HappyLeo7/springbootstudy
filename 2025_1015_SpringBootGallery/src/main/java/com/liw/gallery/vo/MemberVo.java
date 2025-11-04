package com.liw.gallery.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("MemberVo")
@Data
public class MemberVo {
	int mem_idx;
	String mem_name;
	String mem_id;
	String mem_pwd;
	String mem_email;
	String mem_zippcode;
	String mem_addr;
	String mem_ip;
	String mem_regdate;
	String mem_grade;
	
	public MemberVo() {
		// TODO Auto-generated constructor stub
	}
	
	
	//데이터 추가하기위한 vo생성자
	public MemberVo(String mem_name, String mem_id, String mem_pwd, String mem_email, String mem_zippcode,
			String mem_addr, String mem_ip, String mem_grade) {
		super();
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_email = mem_email;
		this.mem_zippcode = mem_zippcode;
		this.mem_addr = mem_addr;
		this.mem_ip = mem_ip;
		this.mem_grade = mem_grade;
	}
	
	
	/**데이터 수정을 위한 vo생성자*/
	public MemberVo(int mem_idx, String mem_name, String mem_id, String mem_pwd, String mem_email, String mem_zippcode,
			String mem_addr, String mem_grade) {
		super();
		this.mem_idx = mem_idx;
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_email = mem_email;
		this.mem_zippcode = mem_zippcode;
		this.mem_addr = mem_addr;
		this.mem_grade = mem_grade;
	}
	
	
	/** 데이터 수정을 위한 vo생성자 IP 추가*/
	public MemberVo(int mem_idx, String mem_name, String mem_id, String mem_pwd, String mem_email, String mem_zippcode,
			String mem_addr, String mem_ip, String mem_grade) {
		super();
		this.mem_idx = mem_idx;
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_email = mem_email;
		this.mem_zippcode = mem_zippcode;
		this.mem_addr = mem_addr;
		this.mem_ip = mem_ip;
		this.mem_grade = mem_grade;
	}


	
}
