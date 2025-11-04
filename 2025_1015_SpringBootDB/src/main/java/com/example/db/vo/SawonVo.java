package com.example.db.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("SawonVo")
@Data	// @Getter + @Seter + @ToString
public class SawonVo {
	int sabun;
	String saname;
	String sasex;
	int deptno;
	String sajob;
	String sahire;
	int samgr;
	int sapay;
}
