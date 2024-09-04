package com.mysite.sbb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//특정 엔티티 또는 데이터 값 없을 때 예외처리
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
