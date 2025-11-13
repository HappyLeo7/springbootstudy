package com.example.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="sawon") // 클래스 이름이 테이블 이름과 같으면 name 설정을 안해도 되지만 다르면 name을 테이블명과 똑같이 해줘야한다.
public class SawonEntity {
	@Id
	int sabun  ;
	
	@Column(name="saname") // 테이블에 있는 컬럼 이름과 java에서 사용하는 변수 이름을 다르게 할때 쓰는 name
	String saname; 
	
	String sasex ; 
	int deptno  ;
	String sajob ; 
	String sahire ; 
	@Column(nullable = true)
	Integer samgr;
	int sapay ;
	int bonus;

}
