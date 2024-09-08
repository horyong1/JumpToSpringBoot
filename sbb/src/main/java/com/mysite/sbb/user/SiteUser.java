package com.mysite.sbb.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * SiteUser로 클래스 이름을 한 이유는 스프링 시큐리티에 이미 User클래스가 있음
 * 패키지가 달라 사용은 가능하나 오용으로 인한 오류 발생 가능성으로 변경하여 생성
 * unique = true 는 값을 중복되게 저장 될수 없게 설정(유일한 값만 저장)
 * */



@Getter
@Setter
@Entity
public class SiteUser {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@Column(unique = true)
	private String email;
}
