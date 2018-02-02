package com.kosmo.springEx;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import common.StudentDTO;

@Controller
public class RequestMappingController {

	/*
	 * method를 명시하지 않으면 디폴트로 GET방식이 된다.
	 * 아래 메소드는 단순히 시작페이지를 매핑하고 있다.
	 */
	@RequestMapping("/requestMapping/index")
	public String rmIndex(){
		
		return "02RequestMapping/index";	
	}
	
	/*
	 * @ReqeustMapping 어노테이션을 통한 매핑시
	 * method : 요청시 전송방식을 명시함
	 * value :  요청명을 명시함
	 * 즉, 요청명과 전송방식을 만족해야 메소드가 호출되는 방식으로
	 * 동작한다.
	 */
	@RequestMapping(method=RequestMethod.GET,
			value="/requestMapping/getSearch")
	public String getSearch(HttpServletRequest req, Model model){
		
		System.out.println("RequestMethod.GET 방식으로 폼값전송");
		
		String searchColumn = req.getParameter("searchColumn");
		String searchWord = req.getParameter("searchWord");
		
		model.addAttribute("searchColumn", searchColumn);
		model.addAttribute("searchWord", searchWord);
		
		return "02RequestMapping/getSearch";				
	}
	
	/*
	 * @RequestMapping 어노테이션을 통한 매핑시
	 * POST방식으로 폼값이 전송될때 사용방식
	 */
	@RequestMapping(method=RequestMethod.POST, 
			value="/requestMapping/postLogin")
	public ModelAndView postLogin(HttpServletRequest req){
		
		/*
		 * ModelAndView() : 뷰로 전송할 데이터의 저장과 뷰를 호출하는
		 * 2가지 로직을 동시에 처리할수 있는 클래스임.
		 * 사용법 : 
		 * 		참조변수.setViewName("뷰의경로 및 파일명") -> 뷰 설정
		 * 		참조변수.addObject("속성명", "속성값") -> 데이터 저장
		 * 뷰로 전달할때는 ModelAndView의 참조변수를 반환.
		 */
		System.out.println("RequestMethod.POST 방식으로 폼값전송");
		
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("02RequestMapping/postLogin");
		mv.addObject("user_id", user_id);
		mv.addObject("user_pw", user_pw);
		
		return mv;				
	}
	
	/*
	 * @ModelAttribute 어노테이션
	 * : 뷰로 전달되는 커멘트객체의 이름을 임의로 변경할수 있음.
	 * 즉, 아래코드는 studentDTO객체를 sInfo로 이름을 변경한후 
	 * View로 전달하고 있음.
	 */
	@RequestMapping("/requestMapping/modelAttribute")
	public String studentInfo(
		@ModelAttribute("sInfo") StudentDTO studentDTO){
		
		return "02RequestMapping/modelAttribute";
	}
}







