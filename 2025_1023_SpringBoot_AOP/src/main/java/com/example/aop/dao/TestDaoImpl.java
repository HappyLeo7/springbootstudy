package com.example.aop.dao;

import org.springframework.stereotype.Repository;


@Repository
public class TestDaoImpl implements TestDao {

	
	

	public TestDaoImpl() {
		super();
		System.out.println("	[생성자]	TestDaoImpl()	");
	}


	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return "hello~";
	}

	@Override
	public String hi() {
		// TODO Auto-generated method stub
		return "hi!";
	}

}
