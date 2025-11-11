package com.example.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.shop.vo.PaymentResultVo;
import com.example.shop.vo.PaymentVo;

@Mapper
public interface PaymentDao {

	List<PaymentResultVo> selectList(int mem_idx);
	//주문번호 생성
	int selectOrderNum();
	
	int insert(PaymentVo vo);
}
