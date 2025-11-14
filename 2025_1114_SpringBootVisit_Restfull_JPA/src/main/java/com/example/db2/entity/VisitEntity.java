package com.example.db2.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "visit")
public class VisitEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Mysql 자동증가
	//@GeneratedValue(strategy=GenerationType.SEQUENCE) // Oracle 자동증가
	//@GeneratedValue(strategy=GenerationType.AUTO) // 알아서 자동증가
	Integer idx;
	String name;
	String content;
	String pwd;
	String ip;
	
	@CreationTimestamp
	LocalDateTime regdate; //시간이 자동으로 들어감
	
}
