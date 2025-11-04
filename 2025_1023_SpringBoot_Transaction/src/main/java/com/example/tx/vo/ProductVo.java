package com.example.tx.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ProductVo")
public class ProductVo {
	int idx;
	String name;
	int cnt;
	String regdate;
}
