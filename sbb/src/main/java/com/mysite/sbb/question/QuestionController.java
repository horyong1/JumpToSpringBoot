package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")	// URL 프리픽스 하는법, question을 생략하고 GetMappring 가능함
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionServiec questionServiec;
	
	/**
	 * 질문 목록 전체 데이터 조회 
	 * */
	@GetMapping("/list")
	public String list(Model model) {
		
		List<Question> qList = this.questionServiec.getList();
		model.addAttribute("questionList",qList);
		
		return "question_list";
	}
	
	/**
	 * id값으로 질문상세 데이터 조회 
	 * */
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Question question = this.questionServiec.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	/**
	 * 질문 등록 페이지 넘어가기
	 * */
	@GetMapping("/create")
	public String questionCreate() {
		return "question_form";
	}
	
	/**
	 * 질문 저장
	 * */
	@PostMapping("/create")
	public String questionCreate(@RequestParam(value="subject") String subject,
			@RequestParam(value="content") String content) {
		this.questionServiec.create(subject, content);
		return "redirect:/question/list";	// 질문 저장 후 질문 목록으로 이동 
	}
}