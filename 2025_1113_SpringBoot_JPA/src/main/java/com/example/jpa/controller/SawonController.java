package com.example.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.entity.SawonEntity;
import com.example.jpa.repository.SawonRepository;

@RestController // @Controller + @ResponseBody
public class SawonController{
	
	@Autowired
	SawonRepository sawonRepository;

	//전체 목록
	@GetMapping("/sawons")
	public List<SawonEntity> list(){
		return sawonRepository.findAll();
	}
	@GetMapping("/sawons/b")
	public List<SawonEntity> listBo(){
		return sawonRepository.findAllSabunSaname();
	}
	
	@GetMapping("/sawon/{sabun}")
	public SawonEntity selectOne(@PathVariable int sabun) {
		
		Optional<SawonEntity> sawon_op = sawonRepository.findById(sabun);
		
		if(sawon_op.isPresent()) {
			SawonEntity sawon = sawon_op.get();
			return sawon;
		}
		return null;
		
	}
}
