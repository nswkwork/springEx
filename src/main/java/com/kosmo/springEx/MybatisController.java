package com.kosmo.springEx;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mybatis.MemberDTO;
import mybatis.MyBoardDAO;
import mybatis.MyBoardDAOImpl;
import mybatis.MyBoardDTO;

/*
 * Mybatis 사용하기
 * 
 * ORM(Object Relational Mapping) 프레임워크의 하나임
 * 데이터베이스와 개체의 관계를 매핑시켜 퍼시스턴트 로직처리를 도와주는 프레임워크
 * XML파일에 매핑 정보를 기술하여 데이터베이스의 테이블과 자바객체(빈)을 매핑하여 데이터베이스에 생성, 조회, 수정, 삭제(CRUD) 작업을 도와주는 역할
 * JDBC를 사용할 때 보다 5~60% 정도의 코드만으로 프로그램 작성이 가능
 * 자바코드와 SQL문의 분리로 프로그램 배포시에도 프로그램 변경없이 XML파일의 변경만으로 수정이 가능함
 * 기존에는 iBatis라는 이름을 사용했으나 Google로 프로젝트가 이전되면서 MyBatis로 이름이 변경됨
 * 
 
  	@@ 사용방법  @@
 
1. pom.xml 파일에 의존설정
 
	<!-- Mybatis 사용하기 위한 의존설정 -->
	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis</artifactId>
		<version>3.2.8</version>
	</dependency>
	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis-spring</artifactId>
		<version>1.2.2</version>
	</dependency>
		
	: 관련 라이브러리가 자동으로 다운로드 된다. Maven 항목에서 확인 가능함.

2. 스프링 설정파일 servlet-context.xml에서 빈을 생성함.
 
	<!-- Mybatis 방명록 제작 2차버전(Mybatis사용)에서 주입받을 빈 생성 -->
	<beans:bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<beans:property name="dataSource" ref="dataSource"/>
		<beans:property name="mapperLocations" value="classpath:mybatis/mapper/*.xml"/>
	</beans:bean>
	<beans:bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
		<beans:constructor-arg index="0" ref="sqlSessionFactory"/>
	</beans:bean>
	
	: DB Connection을 위한 dataSource빈을 기반으로 Mybatis관련 빈을 2개 생성한다.
	
3. 컨트롤러에서 생성된 빈을 자동으로 주입받음.

	@Autowired
	private SqlSession sqlSession;

4. 2에서 sqlSessionFactory 빈을 생성할 때  mapperLocations의 값으로 지정한 경로에 Mapper파일을 생성한다.
 
	4-1 : xml 파일 생성
	4-2 : http://blog.mybatis.org > products > Mybatis3 > docs > Configuration XML > DOCTYPE 검색후 아래 부분 복사
	
		<!DOCTYPE mapper
	    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

	4-3 : <mapper> 엘리멘트 추가 
		
		<mapper namespace="연결할 인터페이스의 풀패키지 경로">
			<select id="호출할 함수명 " resultType="반환타입">
				쿼리문
			</select>
			<insert .. delete,update 해당 엘리먼트 사용>
		</mapper>
		
		※ 반환타입을 명시할 때 기본자료형이 아니라면 풀패키지경로를 써야한다.
		기본 자료형의 경우 int, double처럼 쓰면 된다.

※ 파라미터 사용방법

방법1 : param1, param2
	<select>
		SELECT*FROM 테이블명 WHERE 컬렴=#{param1} AND 컬럼=#{param2}
	</select>

방법2 : 0부터 시작하는 인덱스를 사용
	<select>
		SELECT*FROM 테이블명 WHERE 컬럼=#{0} AND 컬럼=#{1}
	</select>
	
방법3 : 파라미터명을 그대로 사용하기 위해 @Param 어노테이션을 사용
	
	호출을 가장 먼저 받는 interface 추상메소드 정의시
	public void 함수명(@Param("파라미터명") String 파라미터명, ... )
	
	Mapper파일에서 SELECT*FROM 테이블명 WHERE 필드명=#{파라미터명}
 * */

@Controller
public class MybatisController {
   
   /*
    * servlet-context.xml에서 생성한 빈을 자동으로 주입받기 위한 설정
    * 여기서는 MyBoardDAO를 기반으로 생성한 빈을 주입받기 때문에
    * new MyBoardDAO()로 객체를 생성할 필요가 없다.
    * */


   //Mybatis로 컨버팅 완료 후 주석처리함
   MyBoardDAO dao;
   //자바빈을 어디서든 사용하기로 한 어노테이션
   @Autowired
   public void setDao(MyBoardDAO dao) {
      this.dao = dao;
   }
   
   
	
	
	/*
	 * Mybatis사용할 때 Servlet-context.xml에서 생성한 sqlSession빈을 자동으로 주입받아 사용하기 위한 선언
	 * */
	@Autowired
	private SqlSession sqlSession;
	
	
	
   //리스트
   @RequestMapping("/mybatis/list.do")
   public String list(Model model) {
	   
	   //JdbcTemplate 사용
	   //ArrayList<MyBoardDTO> lists = dao.list();
	  
	   
	   //Mybatis 사용
	   MyBoardDAOImpl dao = sqlSession.getMapper(MyBoardDAOImpl.class);
	   ArrayList<MyBoardDTO> lists = dao.list();
	   
	   
	  //줄바꿈처리
	  for(MyBoardDTO list : lists) {
		  list.setContents(list.getContents().replace("\r\n", "<br/>"));
	  }
	  
	  model.addAttribute("lists", lists);
	  
      return "07Mybatis/list";
   }
   
   //글쓰기
   @RequestMapping("/mybatis/write.do")
   public String write(Model model, HttpSession session) {
     /* 
	  //회원로그인 후 접근 하도록 설정
	  if(session.getAttribute("siteUserInfo"==null)) {
		  return "redirect:login.do";
	  }
	  		*/
      return "07Mybatis/write";
   }
   
   //글쓰기처리하기
   @RequestMapping("/mybatis/writeAction.do")
   public String writeAction(Model model, HttpServletRequest req, HttpSession session) {
      
	  //로그인이 되어있는지 확인 후 글작성 완료하기
	   if(session.getAttribute("siteUserInfo")==null) {
		   return "redirect:login.do";
	   }
	  //JdbcTemplate 사용
	  //dao.write(req.getParameter("name"), req.getParameter("contents"), ((MemberDTO)session.getAttribute("siteUserInfo")).getId());
	  
	  //Mybatis 사용
	  MyBoardDAOImpl dao = sqlSession.getMapper(MyBoardDAOImpl.class);
	  dao.write(
			  req.getParameter("name"), req.getParameter("contents"), ((MemberDTO)session.getAttribute("siteUserInfo")).getId()
	  );
	  
      //글작성후 리스트 페이지로 리다이렉트
      return "redirect:list.do";
   }
   
   //글삭제하기
   @RequestMapping("/mybatis/delete.do")
   public String delete(HttpServletRequest req, Model model, HttpSession session) {
	   
	   //로그인확인
	   if(session.getAttribute("siteUserInfo")==null) {
		   return "redirect:login.do";
	   }
	   
	   //JdbcTemplate 사용
	   //dao.delete(req.getParameter("idx"), ((MemberDTO)session.getAttribute("siteUserInfo")).getId());
	   
	   
	   //Mybatis사용
	   MyBoardDAOImpl dao = sqlSession.getMapper(MyBoardDAOImpl.class);
	   dao.delete(req.getParameter("idx"), ((MemberDTO)session.getAttribute("siteUserInfo")).getId());
	   
	   return "redirect:list.do";
	   
   }
   
   //수정하기
   @RequestMapping("/mybatis/modify.do")
   public String modify(Model model, HttpServletRequest req, HttpSession session) {

	   //로그인확인
	   if(session.getAttribute("siteUserInfo")==null) {
		   return "redirect:login.do";
	   }
	   
	   //JdbcTemplate 사용
	   //MyBoardDTO dto = dao.view(req.getParameter("idx"), ((MemberDTO)session.getAttribute("siteUserInfo")).getId());
	   
	   //Mybats사용
	   MyBoardDAOImpl dao = sqlSession.getMapper(MyBoardDAOImpl.class);
	   MyBoardDTO dto = dao.view(req.getParameter("idx"), ((MemberDTO)session.getAttribute("siteUserInfo")).getId());
	   
	   model.addAttribute("dto", dto);
	   return "07Mybatis/modify";
   }  
   
   //수정처리
   @RequestMapping("/mybatis/modifyAction.do")
   public String modifyAction(Model model, HttpServletRequest req, HttpSession session) {


	  //JdbcTemplate 사용
	  //dao.modify(req.getParameter("idx"), req.getParameter("name"), req.getParameter("contents"), ((MemberDTO)session.getAttribute("siteUserInfo")).getId());
	  
	  
	  //Mybatis사용
	  MyBoardDAOImpl dao = sqlSession.getMapper(MyBoardDAOImpl.class);
	  dao.modify(req.getParameter("name"), req.getParameter("contents"), req.getParameter("idx"), ((MemberDTO)session.getAttribute("siteUserInfo")).getId());
	  
      //글작성후 리스트 페이지로 리다이렉트
      return "redirect:list.do";
   }
   
   //로그인
   @RequestMapping("/mybatis/login.do")
   public String login(Model model) {
	   return "07Mybatis/login";
   }
   
   //로그인처리
   @RequestMapping("/mybatis/loginAction.do")
   public ModelAndView loginAction(HttpServletRequest req, HttpSession session) {
	   
	   //JdbcTemplate 사용
	   //MemberDTO memberDTO = dao.login(req.getParameter("id"), req.getParameter("pass"));
  
	   //Mybatis사용
	   MyBoardDAOImpl dao = sqlSession.getMapper(MyBoardDAOImpl.class);
	   MemberDTO memberDTO = dao.login(req.getParameter("id"), req.getParameter("pass"));
	   //MemberImpl로 분리했는데 똑같아서 일단 수정 안했음
	   
	   ModelAndView mv = new ModelAndView();
	   
	   if(memberDTO!=null) {
		   //null이 아니면 로그인 성공
		   session.setAttribute("siteUserInfo", memberDTO);
	   }
	   else {
		   //null이면 로그인 실패
		   mv.addObject("LoginNG", "아이디 또는 패스워드가 틀렸습니다.");
	   }
	   
	   mv.setViewName("07Mybatis/login");
	   return mv;
   }
   
   //로그아웃
   @RequestMapping("/mybatis/logout.do")
   public String logout(HttpSession session) {
	   
	   //세션영역에 저장된 데이터를 지워준다.
	   session.setAttribute("siteUserInfo", null);
	   return "redirect:login.do";
   }
   
   //사용자페이지 - 세션을 사용하지 않는 페이지 선언
   @RequestMapping("/mybatis/sessionFalse.do")
   public String sessionFalse() {
	   return "07Mybatis/sessionFalse";
   }
}
   
   
   
   
   
   
   
   
   
   
 































