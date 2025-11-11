package com.example.shop.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("PaymentVo")
public class PaymentVo {
	int pay_idx;
	int pay_order_num;
	String pay_model_num;
	String pay_name;
	int pay_cnt;
int	pay_price ; 
String	pay_regdate ;
	int mem_idx ;
	int p_idx ;
	
	
}
