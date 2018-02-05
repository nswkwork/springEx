<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
<title>Home</title>

<link href="./common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="./common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>

<style type="text/css">
	li{font-size:1.3em;}
</style>

</head>
<body>
<div class="container">
	<h2>스프링 MVC 시작하기</h2>
	<img src="./resources/1.jpg" alt="" />
	<img src="./common/2.jpg" alt="나는2번이다" />

	<h2>1ST 컨트롤러 만들기</h2>
	<li>
		<a href="./home/helloSpring" target="_blank">First컨트롤러</a>
	</li>
	
	<h2>Form값 처리하기</h2>
	<li>
		<a href="./form/servletRequest?id=kosmo&pw=1234" target="_blank">
			/form/servletRequest[HttpServletRequest로 폼값받기]
		</a>
	</li>
	<li>
		<a href="./form/requestParam?name=홍길동&id=hong
			&email=hong@gildong.com&pw=1234" target="_blank">
			/form/requestParam[@RequestParam어노테이션으로 폼값받기]
		</a>
	</li>
	<li>
		<a href="./form/commendObjGet?name=홍길동&id=hong
			&email=hong@gildong.com&pw=1234" target="_blank">
			/form/commendObjGet[커멘드객체로 한번에 폼값받기]	
		</a>
	</li>
	<li>
		<!-- 
		주의]컨트롤러중에 파라미터 3개를 받을수 있도록 매핑된 정보가 
		없기때문에 아래링크는 404 오류 발생됨.
		
		<a href="./form/GilDong100/홍길동/9999" target="_blank">
			/form/pathVariable[pathVariable어노테이션으로 폼값받기]
		</a> 
		
		"요청명/파라미터1/파라미터2" 형태로 요청하고 있음.		
		-->
		<a href="./form/GilDong100/홍길동" target="_blank">
			/form/pathVariable[pathVariable어노테이션으로 폼값받기]
		</a>
	</li>
	
	
	<h2>@RequestMapping 어노테이션</h2>
	<li>
		<a href="./requestMapping/index" target="_blank">
			/requestMapping/index시작페이지바로가기
		</a>
	</li>
	
	
	<h2>폼 데이터 검증하기</h2>
	<li>
		<a href="validate/memberRegist" target="_blank">
			회원가입 바로가기
		</a>
	</li>
	
	
	<h2>DI(Dependency Injection)</h2>
	<li>
		<a href="di/myCalculator" target="_blank">간단한 사칙연산 계산기</a>
	</li>
	<li>
		<a href="di/myBMICal" target="_blank">BMI지수 계산하기</a>
	</li>
	<li>
		<a href="./di/myAvengers" target="_blank">어벤져스 히어로</a>
	</li>
	<li>
		<a href="./di/myCar" target="_blank">XML에서자동차변경하기</a>
	</li>
	<!-- 
		DI를 활용한 개발 순서
		1. 요청명을 결정
			-> "di/myBMICal"
		2. 컨트롤러 생성후 해당 요청명을 매핑한 메소드 정의
			-> 
				@RequestMapping("di/myBMICal")
				public String bmiCal(Model model){
					메소드 내용 ...
				}
		3. 해당 프로그램에 사용할 클래스 생성
			-> /src/main/java 아래 패키지 생성후 클래스파일
			추가.
			*실제경로 : 프로젝트명/src/main/java/
		4. xml설정파일 생성 후 빈 생성
			-> /src/main/resources/ 하위에
			Spring Bean Configuration File 생성
			*빈 생성은 해당 파일 참조
		5. 컨트롤러에서 생성된 빈 주입받기
			-> getBean()메소드를 통해 주입받음
		6. 뷰로 전달한 정보 저장후 뷰 호출
			-> 문자열로 반환하거나 ModelAndView()를 사용함 
	 -->
	<li>
		<a href="./di/myAnnotation" target="_blank">
			어노테이션을 이용한 DI 사용하기
		</a>
	</li>
	<li>
		<a href="./di/myLifecycle" target="_blank">
			스프링 컨테이너 LifeCycle(생명주기)
		</a>
	</li>
	
	
	<h2>Environment객체 이용한 외부파일 사용하기</h2>
	<li>
		<a href="environment/main1" target="_blank">바로가기1</a>
	</li>
	<li>
		<a href="environment/main2" target="_blank">바로가기2</a>
	</li>
	<li>
		<a href="environment/main3" target="_blank">바로가기3</a>
	</li>
	
	
	<h2>Spring Board(커넥션풀 사용)</h2>	
	<li>
		<a href="board/list.do" target="_blank">
			커넥션풀 사용 -> JdbcTemplate사용으로 컨버팅
		</a>
	</li>
	
	<h2>Mybatis GuestBook</h2>
	<li>
		<a href="mybatis/list.do" target="_blank">
			템플릿->Mybatis로 컨버팅
		</a>
	</li>
	
	<h2>시큐리티쩌구쩌구~~</h2>
	<li>
		<a href="./security1/index.do" target="_blank">
			시큐리티쩌구쩌구!
		</a>
	</li>


	<h2>Spring Security - Step2 , Step3</h2>
	<li>
		<a href="./security2/index.do" target="_blank">
			Step2 바로가기
		</a>
	</li>
	<li>
		<a href="./security2/login.do" target="_blank">
			로그인페이지
		</a>
	</li>
	<li>
		<a href="./security2/admin/main.do" target="_blank">
			관리자영역 메인
		</a>
	</li>	
	<li>
		<a href="./security2/accessDenied.do" target="_blank">
			접근거부 안내
		</a>
	</li>		
	
	<h2>AOP(Aspect Oriented Programming)</h2>
	<li>
		<a href="./aop/main1.do" target="_blank">바로가기1</a>
	</li>
	<li>
		<a href="./aop/main2.do" target="_blank">바로가기2</a>
	</li>
	
	<h2>트랜젝션(Transaction)</h2>
	<li>
		<a href="./transaction/buyTicketMain.do" target="_blank">
			트렌젝션 이용한 티켓구매하기
		</a>	
	</li>
	<li>
		<a href="./transaction/buyTicketTpl.do" target="_blank">
			템플릿 이용한 티켓구매하기
		</a>	
	</li>
	
	
	<h2>파일업로드</h2>
	<li>
		<a href="./fileUpload/uploadForm.do" target="_blank">파일업로드폼</a>
	</li>
	<li>
		<a href="./fileUpload/uploadPath.do" target="_blank">물리적 경로 보기</a>
	</li>	
	
	
	
	
	
	
	
	
	<P style="font-size:1.3em; color:blue; margin-top:50px;">
		Tomcat server의 현재시간은 ${serverTime}. 
	</P>
	
	
</div>
</body>
</html>









