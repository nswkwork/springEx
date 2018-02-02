package AOP;

import org.aspectj.lang.ProceedingJoinPoint;

//공통기능을 수행할 클래스
public class CommonAOP1 {
	
	public Object logViewAOP(ProceedingJoinPoint jointPoint) throws Throwable  {
		
		//현재 호출되는 메소드명을 문자열 형태로 변환후 반환해줌
		String joinSignStr = jointPoint.getSignature().toShortString();
		
		//around로 지정시 공통기능 수행부분[메소드 실행전]
		System.out.println("핵심기능 "+joinSignStr+" 실행전");
		long startTime = System.currentTimeMillis();
		
		Object obj = null;
		
		try {
			//핵심기능을 실행해주는 부분(Proxy라고 표현함)
			obj = jointPoint.proceed();
		}
		catch(Exception e) {
			//around로 지정시 공통기능 수행부분[예외발생시]
			e.printStackTrace();
		}
		finally {
			//around로 지정시 공통기능 수행부분[메소드 실행후]
			long endTime = System.currentTimeMillis();
			System.out.println("핵심기능 "+joinSignStr+" 실행후");
			System.out.println(joinSignStr + "가 실행된 경과시간:"+(endTime-startTime));
			System.out.println();
		}
		
		return obj;
	}
}
