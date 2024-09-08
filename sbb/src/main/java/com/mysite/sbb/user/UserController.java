package com.mysite.sbb.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";
	}

	@PostMapping("signup")
	public String singup(@Valid UserCreateForm userCreateFrom, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!userCreateFrom.getPassword1().equals(userCreateFrom.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect",
					"2개의 패스워드가 일치하지 않습니다.");
			return "signup_form";
		}
		
		userService.create(userCreateFrom.getUsername(), 
				userCreateFrom.getEmail(), userCreateFrom.getPassword1());
		return "redirect:/";
	}
}
