package com.example.tx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tx.dao.ProductInDao;
import com.example.tx.dao.ProductOutDao;
import com.example.tx.dao.ProductRemainDao;
import com.example.tx.vo.ProductVo;

@Service
public class ProductServiceImpl implements ProductService {




	@Autowired
	ProductInDao product_in_dao;
	@Autowired
	ProductOutDao product_out_dao;
	@Autowired
	ProductRemainDao product_remain_dao;





	
	
	@Override
	public Map<String, Object> selectList() {
		// TODO Auto-generated method stub
		
		// TODO 입고
		List<ProductVo> in_list = product_in_dao.selectList();
		// TODO 출고
		List<ProductVo> out_list = product_out_dao.selectList();
		// TODO 재고
		List<ProductVo> remain_list = product_remain_dao.selectList();
		
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("in_list", in_list);
		map.put("out_list", out_list);
		map.put("remain_list", remain_list);
		
		
		return map;
	}

	@Override
	public int insert_in(ProductVo vo) throws Exception {
		int res = 0; //처리된 행수 (성공 : 1 , 실패 : 0)
		// 1. 입고등록
		res= product_in_dao.insert(vo);
		
		// 2. 재고처리
		ProductVo remainVo = product_remain_dao.selectOneFromName(vo.getName());
		System.out.println("	[remainVO] : "+remainVo);
		System.out.println("	[입력받은vo] : "+vo);
		if(remainVo==null) {
			//재고 목록에 입고상품이 없으면 새로 등록 
			res=res*product_remain_dao.insert(vo);
		}else {
			// 재고수량 수정
			// 재고수량 = 기존재고수량 +입고수량
			int cnt = remainVo.getCnt() + vo.getCnt();
			remainVo.setCnt(cnt);
			res=res*product_remain_dao.update(remainVo);
		}
		
		return res;
	}

    //해당메소드내에서 Exception발생시 rollback된다
    @Transactional(rollbackFor = Exception.class)
    @Override
	public int insert_out(ProductVo vo) throws Exception {
		// TODO Auto-generated method stub
		int res=0;
		/* 1.출고(등록)처리 */
		res=product_out_dao.insert(vo);
		
		/* 2. 재고처리 */
		//	출고할 상품정보 얻어온다.
		ProductVo remainVo = product_remain_dao.selectOneFromName(vo.getName());
		
		if(remainVo==null) {
			//	출고상품이 재고 목록에 없는경우
			throw new Exception("remain_not");
		}else {
			//재고수량 = 현재재고수량 - 출고수량
			int cnt = remainVo.getCnt() - vo.getCnt();
					
			if(cnt<0) { //	출고 수량 > 재고 수량
				throw new Exception("remain_lack");
			}
			
			// 정상처리
			remainVo.setCnt(cnt);
			res = res*product_remain_dao.update(remainVo);
		}
		
		return res;
	}

    
    @Transactional(rollbackFor = Exception.class)
	@Override
	public int delete_in(int idx) throws Exception {
    	System.out.println("	[ServiceImpl] delete_in()	...");
    	
		// TODO delete_in 추가해야합니다
		// 2가지 명령 실행
		int res=0;
		ProductVo inVo = product_in_dao.selectOne(idx);
		String name=inVo.getName();

		ProductVo remainVo=product_remain_dao.selectOneFromName(name);
		int cnt=remainVo.getCnt()-inVo.getCnt();
		
		res = product_in_dao.delete(idx);
		if(cnt<0) {
			throw new Exception("delete_in_lack");
			
		}
		
		remainVo.setCnt(cnt);
		
		
		res=res*product_remain_dao.update(remainVo);
		
		
		return res;
	}

	
	@Override
	public int delete_out(int idx) throws Exception {

		int res=0;
		ProductVo outVo=product_out_dao.selectOne(idx); //출고 삭제할 목록 1개 가져오기
		String name=outVo.getName(); // 출고 삭제할거 이름 추출
		ProductVo remainVo=product_remain_dao.selectOneFromName(name); //추출한 이름으로 재고에서 이름으로 1개 목록 가져오기
		System.out.printf("		[remainVO]==>%s\n",remainVo);
		int cnt=remainVo.getCnt()+outVo.getCnt(); // 가져온 재고 목록1개 + 출고 목록1개
		remainVo.setCnt(cnt);
		System.out.printf("		[remainVO]==>%s\n",remainVo);
		res=product_remain_dao.update(remainVo);
		res=product_out_dao.delete(idx);
		
		return res;
	}


}
