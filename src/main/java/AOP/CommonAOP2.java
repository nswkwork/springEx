package AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//공통기능으로 사용할 클래스임을 명시함
@Aspect
public class CommonAOP2 {
   
	//공통기능이 동작할 범위와 아이디를 지정함
   @Pointcut("within(AOP.*)")
   private void pointcutMethod() {}
   
   /*
    * 공통기능의 아이디로 지정한 실제 메소드로 핵심기능 전/후/예외발생시 실행하겠다는 선언
    * */
   @Around("pointcutMethod()")
   public Object loggerAOP(ProceedingJoinPoint jointPoint) throws Throwable {
      
      String signatureStr = jointPoint.getSignature().toShortString();
      Object obj = null;
      
      System.out.println(signatureStr+"실행시작됨");
      long startTime = System.currentTimeMillis();
      
      try {
         obj = jointPoint.proceed();
      }
      catch(Exception e) {
         
      }
      finally {
         long endTime = System.currentTimeMillis();
         System.out.println(signatureStr+"실행종료");
         System.out.println(signatureStr+"경과시간:"+(endTime-startTime));
      }
      return obj;
   }
   
   //핵심기능 실행전 공통기능을 수행하겠다는 선언
   @Before("within(AOP.*)")
   public void beforeAdvice() {
      System.out.println("beforeAdvice() 메소드 실행");
   }
}