package com.example.shop.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("ProductVo")
public class ProductVo {

	int idx;
	String p_model_num;
	String p_name;
	String p_company;
	int p_price;
	int p_saleprice;
	String p_image_s;
	String p_image_l;
	String p_content;
	String p_date;
	int cate_idx;
	
	public int getDiscountRate() {
		//할일률=(정가-할인가)/정가*100
		if(p_price==0) {
			return 0;
		}
		return (int)((double)(p_price-p_saleprice)/p_price*100.0);
	}
}
