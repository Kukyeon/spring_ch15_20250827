package com.kkuk.validate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	@RequestMapping(value = "/join2")
	public String join2() {
		return "join2";
	}
	
	@RequestMapping(value = "joinOk") // 유저가 입력한 값이 유효한 값인지 체크 -> validator(유효성 체크)
	public String joinOk(StudentDto studentDto, Model model, BindingResult result) {
		
		StudentValidator validator = new StudentValidator();
		validator.validate(studentDto, result);
		// result는 발생한 에러를 받을 결과 값
		
		if(result.hasErrors()) { // result 내에 에러가 1개라도 있으면 true, 에러가 없으면 false
			System.out.println("에러 발생 갯수 : " +result.getFieldErrorCount()); // 에러가 발생한 개수
			
			//FieldError fieldError = result.getFieldError("id"); // 해당 필드의 에러 내용을 가져오기
			//System.out.println(fieldError); // id필드의 에러 코드를 가져오기
			
			List<FieldError> fieldErrors = result.getFieldErrors(); // 모든 에러를 list타입으로 반환
			String errorMsg = null;
			for(FieldError error : fieldErrors) {
				System.out.println("-----------------------------------------");
				System.out.println("에러가 발생한 항목 : " + error.getField());
				System.out.println("에러가 발생한 코드명 : " + error.getCode());
				errorMsg = error.getCode();
			}
			
			
			model.addAttribute("error", "error");
			return "join"; // 가입실패 -> 회원가입 페이지로 돌려보내기
		}
		
		model.addAttribute("sDto", studentDto);
		
		
		return "joinOk"; // 가입 성공 = 에러가 0개
	}
	
	@RequestMapping(value = "joinOk2")
	public String joinOk2(@Valid StudentDto studentDto, Model model, BindingResult result) {
		
		if(result.hasErrors()) { // 참이면 에러발생
			return "join";
		} else {
			return "joinOk";
		}
		
		
	}
		@InitBinder
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(new StudentValidator());
		}
	
}
