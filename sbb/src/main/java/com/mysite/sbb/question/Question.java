package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * 질문 엔티티 생성 클래스
 * @Id로 id 속성을 기본키로 지정 
 * @GeneratedValue 애너테이션을 적용하면 데이터 저장시 자동으로 1씩 증가 보통 아래와 같은 속성으로 사용
 * Column length -> 열의 길이를 200자로 지정
 * Column columnDefinition -> 텍스트를 열 데이터로 넣을 수 있음 
 * */
@Getter
@Setter
@Entity
public class Question {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
}
