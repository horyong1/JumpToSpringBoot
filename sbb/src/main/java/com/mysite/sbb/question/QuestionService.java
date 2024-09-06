package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;


// 데이터 처리 방식은 컨트롤러 -> 서비스 -> 리포지터리
// 서비스단에서 모듈화하여 다른 컨트롤러에서도 필요한 메서드 사용 가능 
@RequiredArgsConstructor
@Service
public class QuestionService {
	
	
	private final QuestionRepository questionRepository;
	
	/**
	 * 질문 목록 전체 데이터 반환
	 * */
	public Page<Question> getList(int page) {
		// 앞에 컨트롤러에서 리포지터리 바로 접근하던 부분을 Service단에서 접근하여 데이터 처리
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }
	
	/**
	 * 질문목록 id값으로 상세 목록 반환
	 * */
	public Question getQuestion(Integer id) {
			Optional<Question> question = this.questionRepository.findById(id);
			if(question.isPresent()) {
				return question.get();
			} else {
				throw new DataNotFoundException("question not found");
			}
		 
	}
	/**
	 * 질문 등록 하기 
	 * */
	public void create(String subject, String content) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q);
	}
	
	
}