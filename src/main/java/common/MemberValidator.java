package common;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/*
 * 유효성 검증을 위한 클래스정의를 위해 가장먼저 해야할 일은
 * Validator 인터페이스를 구현하는 것이다.
 * 그리고 supports(), validate() 메소드를 반드시 오버라이딩 해야한다.
 * 즉, 자동완성이 제공된다.
 */
public class MemberValidator implements Validator {

	/*
	 * supports() 메소드
	 *  : 매개변수로 전달된 객체가 유효성 검증을 지원할수 있는
	 *  커맨드객체인지 아닌지를 판단하는 메소드이다.
	 *  만약 이 메소드를 통과하지 못하면 실제 유효성검증을 하는
	 *  validate()메소드는 호출되지 않는다.
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		/*
		 * isAssignableFrom() : 커맨드객체가 FormCommand타입이거나
		 * FormCommand를 상속받은 자식타입이라면 true반환
		 * 아니면 false를 반환하게 된다.
		 */
		return MemberDTO.class.isAssignableFrom(clazz);
	}
	
	/*
	 * support()메소드에서 true를 반환한 경우 즉 유효성검증을 할수있는
	 * 커맨드객체로 판단한 경우에만 호출되는 메소드이다.
	 * 첫번째 매개변수 : 커맨드객체
	 * 두번째 매개변수 : 에러정보를 저장할 Errors타입의 변수
	 * 		(BindingResult타입) 
	 * -> 개발자가 컨트롤러 메소드에서 호출한다.
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		
		System.out.println("validate() 메소드 호출됨");
		
		//객체를 Object형으로 받은후 MemberDTO형으로 형변환후 사용
		MemberDTO memberDTO = (MemberDTO)obj;
		
		//아이디검증 : 단순 조건문을 통해 검증하고 있음.
		String member_id = memberDTO.getId();
		if(member_id==null || member_id.trim().isEmpty()){
			System.out.println("아이디가 null입니다. ㅜㅜ");
			errors.rejectValue("id", "idError");
		}
		
		//패스워드검증 : 내장메소드를 통해 검증하고 있음.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "pwError");
			
		//이름검증 : 사용자정의 메소드를 통해 검증하고 있음.
		boolean nameValidate = myEmptyOrWhitespace(memberDTO.getName());
		if(nameValidate==false){
			System.out.println("이름이 null입니다. ㅠㅠ");
			errors.rejectValue("name", "nameError");
		}
	}
	
	public boolean myEmptyOrWhitespace(String value){
		if(value==null || value.trim().length()==0){
			return false;
		}
		else{
			return true;
		}
	}
	
}






