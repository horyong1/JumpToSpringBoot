package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}