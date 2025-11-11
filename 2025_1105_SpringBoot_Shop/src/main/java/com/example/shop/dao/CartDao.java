package com.example.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.shop.vo.CartViewVo;

@Mapper
public interface CartDao {
	
	//회원별 목록
	List<CartViewVo> selectList(int mem_idx);
	
	//총수량
	int selectAmountTotal(int mem_idx);
	
	//추가
	int insert(CartViewVo vo);

	//수정
	int update(CartViewVo vo);
	
	//삭제
	int delete(int c_idx);

	//존재하는 객체정보 얻어오기 p_idx,mem_idx 이용
	CartViewVo selectOneExist(CartViewVo vo);

	List<CartViewVo> selectPaymentList(Map<String, Object> map);

	//결제 예정정보
	int selectAmountTotal(Map<String, Object> map);

	//결제완료된 항목 삭제
	int deletePaymentComplete(Map<String, Object> map);
}
