package com.kosmo.springEx;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import AOP.ObjSalesMan;
import AOP.ObjStudent;

/*

AOP(Aspect Oriented Programming) : 관점 지향 프로그래밍
	
-공통관점 (cross-cutting concern) 즉 보안, 트랜잭션, 예외처리 등 다른 클래스나 모듈에서 공통으로 필요한 기능을
분리하여 필요한 모듈에 제공해 줌으로써 핵심관점(core concern)에만 집중할 수 있도록 하는 프로그래밍 방식

-Spring의 AOP 프레임워크에서 이런 기능을 제공해준다.

-DI는 개체간의 결합도를 낮게 만드는 것이라면 AOP는 DI의 확장된 개념으로 공통적인 관점을 가지고 있는 개체들과의 결합도를 낮게 만들어 준다.

-즉 공통부분과 핵심부분을 완전히 분리하여 개발자는 핵심부분에만 집중할 수 있도록 해주는 프로그래밍 기법이 바로 AOP이다.

AOP의 용어
-Aspect : 공통기능
-Advice : Aspect의 기능 그 자체를 의미함. 공통기능의 세부적인 내용
-Jointpoint : 핵심기능에 Advice를 적용해야 되는 부분.
(예 : 멤버변수, 메소드 등) 스프링에는 메소드에만 공통기능을 적용할 수 있다.
-Pointcut : Jointpoint의 부분으로 실제로 Advice가 적용된 부분
-Weaving : Advice를 핵심기능에 적용하는 행위

AOP의 구현방법
...
 * */

@Controller
public class AopController {
	
	@RequestMapping("/aop/main1.do")
	public String main1() {
		
		String xmlLocation = "classpath:AOPAppCtx1.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(xmlLocation);
		
		ObjStudent student = ctx.getBean("student", ObjStudent.class);
		student.getObjStudentView();
		
		ObjSalesMan salesMan = ctx.getBean("salesMan", ObjSalesMan.class);
		salesMan.getSalesManView();
		
		ctx.close();
		
		return "09Aop/main1";
	}
	
	@RequestMapping("/aop/main2.do")
	public String main2() {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:AOPAppCtx2.xml");
		
		ObjStudent student = ctx.getBean("student", ObjStudent.class);
		student.getObjStudentView();
		
		ObjSalesMan salesMan = ctx.getBean("salesMan", ObjSalesMan.class);
		salesMan.getSalesManView();
		
		ctx.close();
		
		return "09Aop/main2";
	}
	
}
