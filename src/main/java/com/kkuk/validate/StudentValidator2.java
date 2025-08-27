package com.kkuk.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidator2 implements Validator {

	@Override
	public boolean supports(Class<?> clazz) { // 검증할 객체(studentDto)의 클레스 타입 정보를 가져와야함
		// TODO Auto-generated method stub
		return StudentDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		StudentDto studentDto = (StudentDto)target;
		
		String id = studentDto.getId();
		String pw = studentDto.getPw();
		int age = studentDto.getAge();
		
		if(id.strip().isEmpty() || id == null) { // 두 조건 중 한개라도 참이면 error
			System.out.println("에러가 생긴 아이디 : " + id);
			errors.rejectValue("id", "id가 공란입니다"); // 에러가 발생한 필드이름, 에러코드(메세지)
		}
		
//		if(pw.strip().isEmpty() || pw == null) {
//			System.out.println("에러가 생긴 비밀번호 : " + pw);
//			errors.rejectValue("pw", "비밀번호가 공란입니다");
//		}
		 // 위의 코드 구조와 같은 결과값을 가져오는 코드 ValidationUtils
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "비밀번호가 에러");
		
		if(age > 19 || age < 0) { // 나이가 19세 초과 일경우 학생 회원 자격 없음 ->error발생, 나이값이 음수일경우도 error
			errors.rejectValue("age", "나이가 맞지않습니다.");
		}
	}

}
