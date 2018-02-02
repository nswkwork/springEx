package di;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
 * 빈 초기화 과정에서 수행해야 할 작업이 있을경우
 * InitializingBean 인터페이스를 구현한다.
 * 이때 반드시 afterPropertiesSet() 메소드를 오버라이딩 해야한다.
 * 
 * 빈 소멸 과정에서 수행해야 할 작업이 있을경우
 * DisposableBean 인터페이스를 구현한다.
 * 이때 destroy() 메소드를 반드시 오버라이딩 해야한다.
 * 
 * ※위 2개의 인터페이스를 구현하면 메소드 오버라이딩은 자동완성이
 * 지원됨.  
 */
public class Lifecycle02 
	implements InitializingBean, DisposableBean{

	private String name;
	private int age;	
	
	//생성자
	public Lifecycle02(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		System.out.println("afterPropertiesSet()메소드 호출됨");		
	}
	
	@Override
	public void destroy() throws Exception {
		
		System.out.println("destroy() 메소드 호출됨");
	}
	
	//getter() / setter() 메소드 호출
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
