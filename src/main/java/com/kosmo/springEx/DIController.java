package com.kosmo.springEx;

import static org.junit.Assert.assertFalse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import di.AppConfiguration;
import di.Avengers;
import di.AvengersInfo;
import di.BMIInfoView;
import di.CalculatorDTO;
import di.Car;
import di.Lifecycle01;
import di.Lifecycle02;

@Controller
public class DIController {

	@RequestMapping("/di/myCalculator")
	public String myCal(Model model){
		
		/*
		 * applicationContext파일의 위치를 문자열에 저장함
		 * 실제위치는 /src/main/resources 폴더 하위임
		 */
		String configLocation = "classpath:DIAppCtxCalculator.xml";
		
		/*
		 * 스프링 컨테이너 생성 : xml파일을 파싱하여 파싱된 내용을
		 * 기반으로 ctx 참조변수에 할당한다.
		 */
		AbstractApplicationContext ctx = 
			new GenericXmlApplicationContext(configLocation);
		
		/*
		 * xml설정파일에서 생성한 빈(Bean)을 getBean()메소드를 통해
		 * 주입받아 참조변수에 할당. 
		 * new 연산자를 통해 생성한것과 동일하나 외부에서 생성된것임을
		 * 주의할것.
		 */
		CalculatorDTO myCal = 
			ctx.getBean("myCal", CalculatorDTO.class);
		
		/*
		 * 메소드 실행
		 */
		model.addAttribute("addResult", myCal.add());
		model.addAttribute("subResult", myCal.sub());
		model.addAttribute("mulResult", myCal.mul());
		model.addAttribute("divResult", myCal.div());
		
		/*
		 * 뷰 호출
		 */
		return "04DI/myCalculator";
	}
	
	
	@RequestMapping("di/myBMICal")
	public String bmiCal(Model model){
		
		//스프링 컨테이너 생성
		String configLocation = 
			"classpath:DIAppCtxBMICal.xml";
		AbstractApplicationContext ctx = 
			new GenericXmlApplicationContext(configLocation);
		
		//xml설정파일에서 생성한 빈을 getBean을 통해 주입받음
		BMIInfoView myInfo = ctx.getBean("myInfo", BMIInfoView.class);
		
		//스프링 컨테이너 자원해제
		ctx.close();
		
		//주입받은 빈을 통해 메소드 호출
		String myBMIResult = myInfo.getInfo();
		
		//뷰로 전달한 정보 저장
		model.addAttribute("myBMIResult", myBMIResult);						
		
		//뷰 호출
		return "04DI/myBMICal";
	}
	
	
	@RequestMapping("/di/myAvengers")
	public ModelAndView myAvengers(){
		
		String configLocation = "classpath:DIAppCtxAvengers.xml";
		AbstractApplicationContext ctx = 
			new GenericXmlApplicationContext(configLocation);
			
		//빈 주입받기
		AvengersInfo avengersInfo = 
			ctx.getBean("AvengersInfo1", AvengersInfo.class);
		String captainAmerica = avengersInfo.AvengersView();
				
		Avengers avengers = ctx.getBean("hero2", Avengers.class);
		avengersInfo.setAvengers(avengers);
		String ironMan = avengersInfo.AvengersView();
					
		//ModelAndView 이용하여 뷰로 전달한 정보저장 및 뷰 설정
		ModelAndView mv = new ModelAndView();
		mv.addObject("captainAmerica", captainAmerica);
		mv.addObject("ironMan", ironMan);
		mv.setViewName("04DI/myAvengers");
		
		//자원해제
		ctx.close();
		
		//ModelAndView 반환
		return mv;
	}
	
	
	@RequestMapping("/di/myCar")
	public String myCar(Model model){
		
		//xml설정파일 생성 및 객체생성
		String configLocation = "classpath:DIAppCtxMyCar.xml";
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext(configLocation);
		
		Car car = ctx.getBean("car", Car.class);
		model.addAttribute("myDrive", car.myDrive());
		
		ctx.close();
		
		return "04DI/myCar";
	}
	
	
	@RequestMapping("/di/myAnnotation")
	public ModelAndView myAnnotation(){
		
		//빈을 생성할 JAVA파일을 가져와서 참조변수에 담음
		AnnotationConfigApplicationContext aCtx = new 
			AnnotationConfigApplicationContext(AppConfiguration.class);
		
		//JAVA파일에서 생성한 빈을 getBean메소드를 통해 주입받음
		BMIInfoView mem1 = aCtx.getBean("member1", BMIInfoView.class);
		
		//빈의 정보를 getter() 를 통해 출력함
		String str1 = "이름 : "+mem1.getName()+"<br/>";
		str1 += "취미 : "+mem1.getHobbys()+"<br/>";
		str1 += "신장 : "+mem1.getHeight()+"<br/>";
		str1 += "몸무게 : "+mem1.getWeight()+"<br/>";
		
		BMIInfoView mem2 = aCtx.getBean("member2", BMIInfoView.class);
		
		String str2 = "이름 : "+mem2.getName()+"<br/>";
		str2 += "취미 : "+mem2.getHobbys()+"<br/>";
		str2 += "신장 : "+mem2.getHeight()+"<br/>";
		str2 += "몸무게 : "+mem2.getWeight()+"<br/>";
		
		//ModeAndView를 이용해 뷰로 전달할 데이터저장과 뷰 호출
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberInfo1", str1);
		mv.addObject("memberInfo2", str2);
		mv.setViewName("04DI/myAnnotation");
		return mv;		
	}
	
	
	@RequestMapping("/di/myLifecycle")
	public String myLifecycle(){
		
		GenericXmlApplicationContext ctx = 
			new GenericXmlApplicationContext();
		
		ctx.load("classpath:DIAppCtxLifecycle.xml");
		ctx.refresh();
		
		Lifecycle01 family1 = 
			ctx.getBean("family1", Lifecycle01.class);
		System.out.println("이름:"+family1.getName());
		System.out.println("나이:"+family1.getAge());
		
		/*
		Lifecycle02 family2 = 
			ctx.getBean("family2", Lifecycle02.class);
			-> "서로다른 객체를 사용" 이라고 로그 출력됨
		*/
		Lifecycle01 family2 = 
			ctx.getBean("family1", Lifecycle01.class);
		/*
			-> 싱글톤으로 생성된 객체를 재사용 하므로
			"동일 객체 사용" 이라고 로그 출력됨.
		*/
		family2.setName("홍길자");
		family2.setAge(28);
		System.out.println("이름:"+family2.getName());
		System.out.println("나이:"+family2.getAge());
		
		if(family1.equals(family2)){
			System.out.println("family1과 family2는 동일한객체사용");
		}
		else{
			System.out.println("family1과 family2는 다른객체사용");
		}
		
		ctx.close();		
		
		return "04DI/myLifecycle";
	}
}













