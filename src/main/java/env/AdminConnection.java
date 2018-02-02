package env;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class AdminConnection 
	implements EnvironmentAware, InitializingBean, DisposableBean{

	private Environment env;
	private String adminId;
	private String adminPw;
	
	/*
	 * 빈이 생성되기 전 먼저 호출되는 메소드이다. 즉 최초로 호출된다.
	 * Environment 객체는 한 어플리케이션에 하나만 존재하므로 
	 * 이미 생성된 객체를 가져와서 설정하게 된다.
	 */
	@Override
	public void setEnvironment(Environment env) {
		System.out.println("setEnvironment()메소드 호출됨");	
		
		setEnv(env);//setter() 호출
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()메소드 호출됨");
		
		setAdminId(env.getProperty("admin.id"));//setter()
		setAdminPw(env.getProperty("admin.pw"));//호출
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("destroy()메소드 호출됨");
	}	
	
	//getter()/setter()메소드 정의
	public Environment getEnv() {
		return env;
	}
	public void setEnv(Environment env) {
		this.env = env;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
}
