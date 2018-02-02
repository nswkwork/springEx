package com.kosmo.springEx;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import env.AdminConnection;
import env.BoardConnection;
import env.EnvApplicationConfig;
import env.UserConnection;

/*
 * 외부파일을 이용한 설정방법 
 *  : 프로젝트를 진행하면서 필요한 설정값이 있는경우에 외부파일에
 *  입력후 불러와서 사용할수 있도록 해주는 방법이다.
 *  예) DB접속정보, 관리자정보 등
 */
@Controller
public class EnvironmentController {

	@RequestMapping("/environment/main1")
	public String main1(Model model){
		
		/*1.스프링 컨택스트를 생성함(Ctrl+t하면 클래스의 상속관계를
		볼수있다)*/
		ConfigurableApplicationContext ctx = 
			new GenericXmlApplicationContext();
		
		/*2.Environment 객체를 생성 */
		ConfigurableEnvironment env = ctx.getEnvironment();
		
		/*3.PropertySources를 가져옴 */
		MutablePropertySources propertySources =
			env.getPropertySources();
		
		String adminIdStr = "";
		String adminPwStr = "";
		try{
			/*4.외부파일인 properites 파일을 가져와서 addLast로 
			 추가한다 */
			propertySources.addLast(new
			ResourcePropertySource("classpath:EnvAdmin.properties"));
			
			/*5.getProperty로 해당 데이터를 읽어서 변수에 저장한다 */
			adminIdStr = env.getProperty("admin.id");
			adminPwStr = env.getProperty("admin.pw");
		}
		catch(Exception e){
			e.printStackTrace();
		}
				
		model.addAttribute("adminID", adminIdStr);
		model.addAttribute("adminPW", adminPwStr);		
		
		
		/////////////////////////////////////////
		
		/*
		 * 위에서 이미 생성했던 ctx컨택스트를 통해
		 * 새로운 스프링 컨택스트를 생성함.
		 */
		GenericXmlApplicationContext gCtx = 
			(GenericXmlApplicationContext)ctx;
		//컨테이너 설정 파일 로드
		gCtx.load("EnvAppCtx01.xml");
		//빈생성
		gCtx.refresh();
		
		//위 xml설정파일에서 생성된 빈을 주입받음
		AdminConnection adminConnection = 
			gCtx.getBean("adminConnection", AdminConnection.class);
		adminIdStr = adminConnection.getAdminId();
		adminPwStr = adminConnection.getAdminPw();
		
		//뷰로 전달한 데이터 Model에 저장
		model.addAttribute("adminID2", adminIdStr);
		model.addAttribute("adminPW2", adminPwStr);
					
		//뷰 호출
		return "05Environment/main1";
	}	
	
	/*
	 * 외부파일참조 2
	 * 
	 * Environment 객체를 사용하지 않고 XML파일에 프로퍼티 파일을
	 * 명시한 후 직접 생성하여 빈을 설정하는 방법
	 */
	@RequestMapping("/environment/main2")
	public String main2(Model model){
		
		//XML설정 파일을 기반으로 스프링 컨테이너 생성
		AbstractApplicationContext ctx = 
			new GenericXmlApplicationContext(
				"classpath:EnvAppCtx02.xml");
		
		//외부에서 생성한 빈을 주입받아서 사용
		UserConnection userConn = 
			ctx.getBean("userConnection", UserConnection.class);
		
		String mainUserId = userConn.getMainUserId();
		String mainUserPw = userConn.getMainUserPw();
		String subUserId = userConn.getSubUserId();
		String subUserPw = userConn.getSubUserPw();
		
		model.addAttribute("mainUserId", mainUserId);
		model.addAttribute("mainUserPw", mainUserPw);
		model.addAttribute("subUserId", subUserId);
		model.addAttribute("subUserPw", subUserPw);		
		
		//뷰호출
		return "05Environment/main2";
	}
	
	
	/*
	 * 외부파일참조3
	 *  : 어노테이션을 이용한 외부파일 참조.
	 *  XML설정파일 대신 EnvApplicationConfig 클래스파일을
	 *  이용하여 외부파일참조 및 빈 생성을 한다. 
	 */
	@RequestMapping("/environment/main3")
	public String main3(Model model){
		
		//어노테이션 기반 스프링 컨테이너 생성
		AnnotationConfigApplicationContext ctx = new
			AnnotationConfigApplicationContext(
				EnvApplicationConfig.class);
		
		//설정파일에서 생성한 빈을 주입받음
		BoardConnection bConn = 
			ctx.getBean("boardConfig", BoardConnection.class);
		
		model.addAttribute("Title", bConn.getTitle());
		model.addAttribute("Content", bConn.getContent());
		model.addAttribute("Writer", bConn.getWriter());
		model.addAttribute("Passwd", bConn.getPasswd());		
		
		return "05Environment/main3";
	}
}












