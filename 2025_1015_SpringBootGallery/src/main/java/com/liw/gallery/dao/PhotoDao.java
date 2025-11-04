package com.liw.gallery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.liw.gallery.vo.PhotoVo;
@Mapper
public interface PhotoDao {
	
	List<PhotoVo> selectList();
	
	/**페이지 처리해서 전체조회해오기 map 데이터 start , end 키값 받아오기*/
	List<PhotoVo> selectListPage(Map<String, Object> map);
	
	PhotoVo selecOne(int p_idx);
	
	/**전체 레코드수 구하는 매서드*/
	int selectRowTotal(); 

	
	int insert(PhotoVo vo);
	int update(PhotoVo vo);
	int update_filename(PhotoVo vo);
	int delete(int p_idx);

}
