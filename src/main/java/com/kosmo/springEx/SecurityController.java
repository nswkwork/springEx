package com.kosmo.springEx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* 
	Spring Security Framework(스프링 시큐리티 프레임워크)
	
	-필요한 인증, 권한 및 보안관련 기능들을 간단하게 사용할 수 있도록 지원해주는 프레임워크
	-Servlet Filter, Spring AOP를 기반으로 구현되었으며 유연한 설계로 다양한 확장 및 커스터마이징이 가능함
	-비즈니스 로직과 인증, 권한 로직을 90%이상 분리가 가능함
	
	※사용방법
	
	1. pom.xml의존설정 : Spring Security를 사용하기 위한 의존설정을 한다.

	<!-- Spring Security(시큐리티) 사용위한 의존설정 -->
	<dependency>
		<groupId>org.springframework.security</groupId>
		<artifactId>spring-security-web</artifactId>
		<version>4.2.1.RELEASE</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.security</groupId>
		<artifactId>spring-security-config</artifactId>
		<version>4.2.1.RELEASE</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.security</groupId>
		<artifactId>spring-security-taglibs</artifactId>
		<version>4.2.1.RELEASE</version>
	</dependency>

	2. web.xml에 스프링 설정파일 (경로?)추가
	
	<context-param>
		<param-value>
			/기존경로/root-context.xml
			/기존경로/security-context.xml //이부분 추가하기
		</param-value>
	</context-param>
	
	3. web.xml에 추가한 설정파일 경로에 실제 설정파일 생성하기
	
	이클립스 생성메뉴 -> 스프링 설정파일 생성 -> security-context.xml 생성
	※ 생성시 security항목 namespace 추가해야함. 의존설정 이후이므로 항목이 추가되어 있을거임.
	※ 엘리먼트에 대한 설명은 security-context.xml에서 계속..
	
	4. web.xml에 security 관련 필터를 추가함
	
	<!-- Spring Security 사용을 위한 필터추가 -->
 	<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	
	
 * */

@Controller
public class SecurityController {
	
	@RequestMapping("/security1/index.do")
	public String security1Index() {
		return "08Security/Step1/index";
	}
	
	//인덱스 페이지
	@RequestMapping("/security2/index.do")
	public String security2Index() {
		return "08Security/Step2/index";
	}
	//로그인 페이지
	@RequestMapping("/security2/login.do")
	public String security2login() {
		return "08Security/Step2/login";
	}
	//접근거부 페이지
	@RequestMapping("/security2/accessDenied.do")
	public String security2AccessDenied() {
		return "08Security/Step2/accessDenied";
	}
	//관리자모드  메인 페이지
	@RequestMapping("/security2/admin/main.do")
	public String security2AdminMain() {
		return "08Security/Step2/adminMain";
	}
	
}
