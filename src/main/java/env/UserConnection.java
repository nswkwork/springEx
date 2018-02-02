package env;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class UserConnection 
	implements InitializingBean, DisposableBean{

	private String mainUserId;
	private String mainUserPw;
	private String subUserId;
	private String subUserPw;	
	
	//getter() / setter()
	public String getMainUserId() {
		return mainUserId;
	}
	public void setMainUserId(String mainUserId) {
		this.mainUserId = mainUserId;
	}
	public String getMainUserPw() {
		return mainUserPw;
	}
	public void setMainUserPw(String mainUserPw) {
		this.mainUserPw = mainUserPw;
	}
	public String getSubUserId() {
		return subUserId;
	}
	public void setSubUserId(String subUserId) {
		this.subUserId = subUserId;
	}
	public String getSubUserPw() {
		return subUserPw;
	}
	public void setSubUserPw(String subUserPw) {
		this.subUserPw = subUserPw;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet() 호출");		
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("destroy() 호출");
	}	
}
