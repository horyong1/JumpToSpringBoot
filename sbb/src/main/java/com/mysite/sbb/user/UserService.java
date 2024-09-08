package com.mysite.sbb.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	/**
	 * User 리포저터리를 이용한 회원데이터 생성하는 함수
	 * 비밀번호는 보안을 위해 반드시 암호화하여 저장 해야함.
	 * 스프링 시큐리티인 BCryptPasswordEncoder 클래스를 사용하여 암호화하여 비밀번호 저장
	 * BCryptPasswordEncoder
	 * - qlzmflqxm(BCrypt) 해시 함수를 사용하는데, 비크립트는 해시함수의 하나로 주로 비밀번호와 같은
	 * 	 보안 정보를 안전하게 저장하고 검증할 때 사용하는 암호화 기술
	 * - BCryptPasswordEncoder 객체를 직접 new로 생성하는 방식보다는 
	 * 	 PasswordEncoder 객체를 빈으로 등록해서 사용하는 것이 좋음
	 *   왜냐하면 암호화 방식을 변경하면 BCryptPasswordEncoder를 사용한 모든 프로그램을 일일이 찾아다니며 수정해야 하기 때문
	 * */
	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		user.setPassword(passwordEncoder.encode(password));
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
		
	}
}
