package com.example.shop.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("CartVo")
public class CartViewVo {

	int idx;
	int c_idx;
	String p_model_num;
	String p_name;
	int p_price;
	int p_saleprice;
	int c_cnt;
	int amount;
	int mem_idx;
}
