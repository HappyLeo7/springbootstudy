package com.example.db2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.db2.dao.VisitDao;
import com.example.db2.vo.VisitVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController  // => @Controller + @ResponseBody 형태
public class VisitRestfullController {
	
	@Autowired
	VisitDao visit_dao;

	@Autowired
	HttpServletRequest request;
	
	//전체조회
	@GetMapping("/rest/visits")
	public List<VisitVo> list(){
		
		List<VisitVo> list =visit_dao.selectList(null);
				
		return list;
	}
	
	//1개조회
	@GetMapping("/rest/visit/{idx}")
	public VisitVo selectOne(@PathVariable(name="idx")int idx) {
		VisitVo vo = visit_dao.selectOne(idx);
		return vo;
	}
	
	//insert
	@PostMapping("/rest/visit")
	public Map<String,Object> insert(@RequestBody VisitVo vo){
		//작성자 IP구하기
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		//내용 : \n -> <br> 로 변경하는 코드 작성
		String content = vo.getContent().replaceAll("\n", "<br>");
		vo.setContent(content);
		
		//DB insert
		int res = visit_dao.insert(vo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", res==1);
		return map;
	}
	
	//update
	@PutMapping("/rest/visit")
	public Map<String,Object> update(@RequestBody VisitVo vo){
		
		String ip=request.getRemoteAddr();
		vo.setIp(ip);
		
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
		
		
		int res=visit_dao.update(vo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", res==1);
		
		return map;
	}
	
	//delete
	@DeleteMapping("/rest/visit/{idx}")
	public Map<String,Object> delete(@PathVariable int idx){
	
		int res = visit_dao.delete(idx);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", res==1);
		
		return map;
	}
	
	//pwd check
	@GetMapping("/rest/check-pwd/{idx}/{c_pwd}")
	public Map<String,Object> checkPwd(@PathVariable int idx
										,@PathVariable String c_pwd){
		VisitVo vo = visit_dao.selectOne(idx);
		
		// 3. 비밀번호 비교
		boolean bResult = vo.getPwd().equals(c_pwd);
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", bResult);
		
		return map;
	}
	
	//pwd check
	@PostMapping("/rest/check-pwd")
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
