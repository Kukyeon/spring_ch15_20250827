package com.kkuk.validate;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "joinOk") // 유저가 입력한 값이 유효한 값인지 체크 -> validator(유효성 체크)
	public String joinOk(StudentDto studentDto, Model model, BindingResult result) {
		
		StudentValidate validator = new StudentValidate();
		validator.validate(studentDto, result);
		// result는 발생한 에러를 받을 결과 값
		
		if(result.hasErrors()) { // result 내에 에러가 1개라도 있으면 true, 에러가 없으면 false
			model.addAttribute("error", "error");
			return "join"; // 가입실패 -> 회원가입 페이지로 돌려보내기
		}
		
		model.addAttribute("sDto", studentDto);
		
		
		return "joinOk"; // 가입 성공 = 에러가 0개
	}
}
