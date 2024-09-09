package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * @Configuration -> 이 파일이 스프링의 환경 설정 파일임을 의미하는 애너테이션
 * @EnableWebSecurity -> 모든 요청 URL이 스프링 시큐리티의 제어를 받로고 만드는 애너테이션(스프링 시큐리티 활성화)
 * 	- 내부적으로 SecurityFrilterChain 클래스가 동작하여 모든 요청 URL에 이 클래스가 필터로 적용되어 URL별로
 *    특별한 설정을 할 수 있게 된다. 스프링 시큐리티 세부설정은 @Bean 애너테이션을 통해 SecurityFrilterChain
 *    빈을 생성하여 설정 
 * @Bean -> 스프링에 의해 생성 또는 관리되는 객체를 의미. 컨트롤러, 서비스, 리포지터리 등 모두 빈에 해당
 * 	- 하단 코드처럼 @Bean 애너테이션을 사용하여 빈을 정의하고 등록 할 수 있음 
 * */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 인증되지 않은 모든 페이지의 요청을 허락한다는 의미. 
	// 따라서 로그인하지 않더라도 모든 페이지에 접근할 수 있도록 한다.
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
            .csrf((csrf) -> csrf
                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
            .headers((headers) -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        ;
        return http.build();
    }

    // BCryptPasswordEncoder 직접 new 로 객체를 생성 하기보다 빈으로 등록하여 사용하는게 유지보수 측면에서 좋음
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}