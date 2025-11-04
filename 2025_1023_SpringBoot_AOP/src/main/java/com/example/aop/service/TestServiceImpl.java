package com.example.aop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aop.dao.TestDao;

@Service
public class TestServiceImpl implements TestService {


	
	@Autowired
	TestDao test_dao;

	public TestServiceImpl() {

		System.out.println("	[생성자]	TestServiceImpl()	");


	}
	
	@Override
	public String hello() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis(); //현재시간을 milisecond로 구한다.
		String hello = test_dao.hello();
		
		long endTime=System.currentTimeMillis();
		System.out.printf("		[hello()]수행시간 : %d(ms)\n ",endTime-startTime);
		return hello;
	}

	@Override
	public String hi() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis(); //현재시간을 milisecond로 구한다.

		String hi=test_dao.hi();
		
		long endTime=System.currentTimeMillis();
		System.out.printf("		[hi()]수행시간 : %d(ms)\n ",endTime-startTime);
		return hi;
	}

	@Override
	public Map<String, String> total() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis(); //현재시간을 milisecond로 구한다.
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String,String> map =new HashMap<String,String>();
		String hello = test_dao.hello();
		String hi 	 = test_dao.hi();
		
		map.put("hello", hello);
		map.put("hi", hi);
		
		long endTime=System.currentTimeMillis();
		System.out.printf("		[total()]수행시간 : %d(ms)\n ",endTime-startTime);
		return map;
	}

}
