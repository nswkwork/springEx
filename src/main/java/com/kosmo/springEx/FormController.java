package com.kosmo.springEx;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.MemberDTO;

/*
 * 맨 처음 요청을 받는 DispatcherServlet은 기본 패키지인 
 * com.kosmo.springEX 를 스캔하여 컨트롤러 클래스를 찾는다. 
 * 그리고 해당 요청명에 매핑되는 메소드를 찾아 실행한다. 
 * 요청명 매핑은 @RequestMapping 어노테이션이 담당한다.
 */
@Controller
public class FormController {

	/*
	 * 아래 요청명을 매핑함.
	 * 웹에서 "컨택스트루트/form/servletRequest?파라미터=값" 형태로 
	 * 요청하게되면 해당 메소드가 실행되게 됨.
	 */
	@RequestMapping("/form/servletRequest")
	public String loginMember(HttpServletRequest req, Model model){
		/*
		 * SpringMVC에서는 파라미터로 전달된값을 HttpServletRequest클래스가
		 * 받게된다. 해당 클래스의 매개변수로 폼값을 받을수 있다.
		 */
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		/*
		 * View로 전송할 데이터는 Model에 저장한다. 
		 * JSP에서의 영역과 동일하다고 생각하면 된다.
		 */
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		model.addAttribute("message", "로그인 정보가 전달되었습니다.");
		
		/*
		 * View 페이지명을 반환한다. 
		 * 아래처럼 뷰의 경로를 문자열로 반환하면 ViewResolver가
		 * 경로를 조립하여 해당 뷰를 웹브라우저에 로딩하게 된다.
		 * (설정 : servlet-context.xml)
		 */
		return "01Form/servletRequest";
	}
	
	/*
	 * @RequestParam 어노테이션으로 폼값 받기
	 * 사용법 :  @RequestParam("파라미터명") 변수타입 변수명
	 * 위와같이 하면 해당 메소드 내에서 변수명을 그대로 사용할 수 있다.
	 */
	@RequestMapping("/form/requestParam")
	public String joinMember(@RequestParam("name") String name,
						@RequestParam("id") String id,
						@RequestParam("pw") String pw,
						@RequestParam("email") String email,
						Model model){
		
		MemberDTO member = new MemberDTO();
		
		member.setId(id);
		member.setName(name);
		member.setPw(pw);
		member.setEmail(email);
		
		model.addAttribute("memberInfo", member);
		
		return "01Form/requestParam";
	}
	
	/*
	 * '커멘드객체'를 이용해서 폼값 한번에 받기
	 * 조건 : 쿼리스트링으로 전달되는 파라미터의 갯수와 폼값을 저장할
	 * 객체(DTO)의 멤버변수의 갯수가 동일해야함. 
	 * ※코드양이 적으므로 실무에서 많이 사용됨.
	 */
	@RequestMapping("/form/commendObjGet")
	public String commendObjectSimpleGet(MemberDTO memberDTO){
		
		return "01Form/commendObjGet";
	}
	
	/*
	 * @PathVariable 어노테이션을 통해 폼값 받기
	 * 요청명 자체를 파라미터로 사용하는 형태로 "./form/ 뒤에 붙는
	 * 값이 메소드에서 사용가능한 파라미터값이 된다. 
	 * 즉, 아래 매핑정보로는 2개의 파라미터를 받을수 있다. 
	 * 파라미터의 갯수가 틀릴경우 404 오류가 발생된다.
	 */
	@RequestMapping("/form/{memberId}/{memberName}")
	public String pathVariable(@PathVariable String memberId,
						@PathVariable String memberName,
						Model model){
		
		model.addAttribute("memberId", memberId);
		model.addAttribute("memberName", memberName);
		
		return "01Form/pathVariable";		
	}	
}




