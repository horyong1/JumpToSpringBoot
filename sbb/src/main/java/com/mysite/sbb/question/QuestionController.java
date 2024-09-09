package com.mysite.sbb.question;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")	// URL 프리픽스 하는법, question을 생략하고 GetMappring 가능함
@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionService questionServiec;
	
	/**
	 * 질문 목록 전체 데이터 조회 
	 * */
	@GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionServiec.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }
	
	/**
	 * id값으로 질문상세 데이터 조회 
	 * */
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id,
							AnswerForm answerForm) {
		Question question = this.questionServiec.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	/**
	 * 질문 등록 페이지 넘어가기
	 * */
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	/**
	 * 질문 저장
	 * subject, content 대신 QuestionForm 객체로 변경
	 * subject, content 항목의 form값이 전송되면 QuestionForm 의 속성이 자동으로 바인딩 됨.
	 * BindingResult @Valid 매개변수 바로 뒤에 위치 해야함. 위치가 다르면 @Valid만 적용되어 입력값 검증 실패 시 400오류 발생
	 * bindResult.hasErrors() 호출하여 오류가 있는경우 화면으로 돌아가고 오류없을 경우만 등록되도록 설정
	 * */
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm,
			BindingResult bindingResult) {
	
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		this.questionServiec.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";	// 질문 저장 후 질문 목록으로 이동 
	}
	
}