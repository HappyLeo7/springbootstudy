package com.example.shop.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("PaymentResultVo")
public class PaymentResultVo {

	int pay_order_num;
	int pay_amount_sum;
	
	List<PaymentVo> payment_list;
}
