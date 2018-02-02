package di;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
 * 빈 초기화 과정에서 자동으로 호출되는 메소드를 정의할때
 * @PostConstruct 어노테이션을 메소드 선언시 붙여줌
 * 
 * 빈 소멸 과정에서 자동으로 호출되는 메소드를 정의할때
 * @PreDestroy 어노테이션을 메소드 선언시 붙여줌
 */
public class Lifecycle01 {
	
	private String name;
	private int age;
		
	//생성자
	public Lifecycle01(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	
	@PostConstruct
	public void myInitMethod(){
		
		System.out.println("myInitMethod()메소드호출(사용자정의)");
	}	
	@PreDestroy
	public void myDestoryMethod(){
		
		System.out.println("myDestoryMethod()메소드호출(사용자정의)");
	}

	
	//getter()/setter() 정의
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}	
}
