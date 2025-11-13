package com.example.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.SawonEntity;

//public class SawonRepository implements JpaRepository<T, ID>
//														Entity명		Entity의 @Id타입
public interface SawonRepository extends JpaRepository<SawonEntity, Integer> {
	// findByAll()  findById(id)  save() 메소드가 기본적으로 정의되어 있다.
	@Query(value = "select *, sapay*10 as bonus from sawon",nativeQuery=true)
	List<SawonEntity> findAllSabunSaname();
	}
