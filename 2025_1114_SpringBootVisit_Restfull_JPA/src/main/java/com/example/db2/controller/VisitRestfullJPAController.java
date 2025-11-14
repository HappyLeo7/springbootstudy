package com.example.db2.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.db2.dao.VisitDao;
import com.example.db2.entity.VisitEntity;
import com.example.db2.repository.VisitRepository;
import com.example.db2.vo.VisitVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController  // => @Controller + @ResponseBody 형태
public class VisitRestfullJPAController {
	
	@Autowired
	VisitDao visit_dao;

	@Autowired
	HttpServletRequest request;
	
	
	@Autowired
	VisitRepository visitRepository; //JPA 객체
	
	//전체조회
	@GetMapping("/rest2/visits")
	public List<VisitEntity> list(){
		
//		List<VisitVo> list =visit_dao.selectList(null);
//		List<VisitEntity> list =visitRepository.findAll();
		List<VisitEntity> list =visitRepository.findAllByOrderByIdxDesc();
//		List<VisitEntity> list =visitRepository.findAllByIdxBetween(1,5);
//		List<VisitEntity> list =visitRepository.findAllByIdxGreaterThan(10);
				
		return list;
	}
	
	//1개조회
	@GetMapping("/rest2/visit/{idx}")
	public VisitEntity selectOne(@PathVariable(name="idx")int idx) {
//		VisitVo vo = visit_dao.selectOne(idx);
		Optional<VisitEntity> vo=visitRepository.findById(idx);
		if(vo.isPresent()) {
			return vo.get();
		}
		return null;
	}
	
	//insert
	@PostMapping("/rest2/visit")
	public Map<String,Object> insert(@RequestBody VisitEntity vo){
		//작성자 IP구하기
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		//내용 : \n -> <br> 로 변경하는 코드 작성
		String content = vo.getContent().replaceAll("\n", "<br>");
		vo.setContent(content);
		
		//DB insert
		//int res = visit_dao.insert(vo);
		VisitEntity resVo=visitRepository.save(vo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", resVo!=null);
		return map;
	}
	
	//update
	@PutMapping("/rest2/visit")
	public Map<String,Object> update(@RequestBody VisitEntity vo){
		
		String ip=request.getRemoteAddr();
		vo.setIp(ip);
		
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
		
		// DB update
		//int res=visit_dao.update(vo);
		
		//날짜수정? java객체를 이용해서 현재시간을 가져온뒤 넣어준다.
		vo.setRegdate(LocalDateTime.now());
		
		// 수정 : idx가 DB에 존재하면 수정함
		//	   : idx가 없으면 insert 된다.
		VisitEntity resVo=visitRepository.save(vo);
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", resVo!=null);
		
		return map;
	}
	
	//delete
	@DeleteMapping("/rest2/visit/{idx}")
	public Map<String,Object> delete(@PathVariable int idx){
	
		//int res = visit_dao.delete(idx);
		boolean bResult=true;
		
		try {
			visitRepository.deleteById(idx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bResult = false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", bResult);
		
		return map;
	}
	
	//pwd check
	@GetMapping("/rest2/check-pwd/{idx}/{c_pwd}")
	public Map<String,Object> checkPwd(@PathVariable int idx
										,@PathVariable String c_pwd){
		// 1. idx에 해당되는 게시물 1건 정보 얻어오기
		//VisitVo vo = visit_dao.selectOne(idx);
		boolean bResult=false;
		Optional<VisitEntity> vo=visitRepository.findById(idx);
		if(vo.isPresent()) { // 데이터가 있는지 여부 붙는거
			
			// 3. 비밀번호 비교
			bResult = vo.get().getPwd().equals(c_pwd);
		}
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", bResult);
		
		return map;
	}
	
	//pwd check
	@PostMapping("/rest2/check-pwd")
	public Map<String,Object> checkPwdPost(@RequestBody Map<String,String> m){
		int idx= Integer.parseInt(m.get("idx"));
		VisitVo vo = visit_dao.selectOne(idx);
		
		// 3. 비밀번호 비교
		String c_pwd= m.get("c_pwd");
		boolean bResult = vo.getPwd().equals(c_pwd);
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", bResult);
		
		return map;
	}
	
}
