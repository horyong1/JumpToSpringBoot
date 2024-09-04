package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.mysite.sbb.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


/**
 * 답변 클래스 
 * @ManyToOne 애너테이션은 N:1관계를 나타냄 
 * 1은 Question N은 Answer 부모자식 관계 
 * 실제 DB에서는 외래키 관계가 생성
 * */
@Getter
@Setter
@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@CreatedDate
	private LocalDateTime createDate;
	
	@ManyToOne
	private Question question;
	
}
