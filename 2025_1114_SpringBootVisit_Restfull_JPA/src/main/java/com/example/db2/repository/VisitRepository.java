package com.example.db2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.db2.entity.VisitEntity;

public interface VisitRepository extends JpaRepository<VisitEntity, Integer>{

	// 이미 정의되어있는 메소드
	//List<VisitEntity> findAll();
	//VisitEntity save(VisitEntity entity);
	//void deleteById(int idx);
	
	// 개발자가 정의한 메소드
	List<VisitEntity> findAllByOrderByIdxDesc();
	List<VisitEntity> findAllByIdxGreaterThan(int idx);
	List<VisitEntity> findAllByIdxLessThanEqual(int idx);
	List<VisitEntity> findAllByIdxBetween(int start,int end);
	
}
