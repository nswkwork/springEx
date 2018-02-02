package com.kosmo.springEx;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import common.MemberDTO;
import common.MemberValidator;

@Controller
public class ValidateController {

	//회원가입페이지 매핑하기
	@RequestMapping("/validate/memberRegist")
	public String memberRegist(){
				
		return "03Validate/memberRegist";
	}
	
	
	//회원가입페이지의 폼값을 전송받아 검증하는 메소드 
	@RequestMapping("/validate/registProc")
	public String registProc(
			@ModelAttribute("mInfo") MemberDTO memberDTO,
			BindingResult result,
			Model model){
		
		//폼값 검증 완료시 이동할 페이지의 요청명(경로명)
		String viewPage = "03Validate/memberDone";
		
		/*
		 * 유효성검증을 위해 정의한 클래스의 객체를 생성한후 
		 * 전송된 폼값을 통째로 저장한 memberDTO객체를 통해 폼값의 유효성체크를
		 * 실시한다.
		 * 매개변수로 유효성체크를 할 커멘드객체(DTO객체)와 바인딩결과를 저장할
		 * 객체를 전달한다.
		 */
		MemberValidator validator = new MemberValidator();		
		validator.validate(memberDTO, result);
		
		//폼값 유효성 검증에서 문제가 발생했다면...
		if(result.hasErrors()){
			//바인딩결과 객체를 통해 전달된 에러내용을 문자열로 출력
			System.out.println("유효성 체크 실패:"+result.toString());
			//뷰로 전달할 데이터 저장
			model.addAttribute("formError", "폼값 유효성체크에 실패하였습니다.");
			//실패시에는 완료페이지로 이동하지 않고 가입페이지로 재이동함
			viewPage = "03Validate/memberRegist";
		}
		
		return viewPage;
	}
	
}

















