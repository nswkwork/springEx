package di;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 * @Configuration : 해당 클래스를 스프링 설정파일로 사용하겠다는 선언
 * xml설정 파일을 사용하는 대신 해당 java파일을 설정파일로 사용한다.
 */
@Configuration
public class AppConfiguration {
	
	/*
	 * @Bean : 설정 파일에서 빈을 생성할때 선언함. 
	 * 해당 빈은 메인클래스(컨트롤러)로 getBean메소드를 통해 
	 * 주입된다.
	 */
	@Bean
	public BMIInfoView member1(){
		
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("삽질");
		hobbys.add("땅파기");
		
		BMIInfoView mem1 = new BMIInfoView();
		mem1.setName("홍길동");
		mem1.setHobbys(hobbys);
		mem1.setHeight(190);
		mem1.setWeight(80);
		
		return mem1;
	}
	
	
	@Bean
	public BMIInfoView member2(){
		
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("스노우보드");
		hobbys.add("자전거");
		
		BMIInfoView mem2 = new BMIInfoView();
		mem2.setName("성낙현");
		mem2.setHobbys(hobbys);
		mem2.setHeight(182);
		mem2.setWeight(77);
		
		return mem2;
	}
	
	
	
	
	
	
}






