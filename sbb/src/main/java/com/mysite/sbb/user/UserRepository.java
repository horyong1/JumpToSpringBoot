package com.mysite.sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;

// SiteUser 기본키가 id이고 id 타입이 Long 이기 때문에 Long으로 설정
public interface UserRepository extends JpaRepository<SiteUser, Long>{

}
