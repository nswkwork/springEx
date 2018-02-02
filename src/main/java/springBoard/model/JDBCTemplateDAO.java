package springBoard.model;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

/*
 * Spring JDBC 사용하기
 * 
 * 웹어플리케이션을 제작할 때 Database 사용을 위해 매번 같은 동작을 반복하는 부분이 있다.
 * 주로 DAO객체를 이용해서 드라이버로드,커넥션생성 및 DB연결, SQL실행, 자원반납 및 레코드를 관리한다.
 * JDBCTemplate을 이용하면 이런 반복적인 작업을 아주 짧은 코드로 처리할 수 있다.
 * Spring빈을 이용하여 코드를 간소화하고 Database관련 클래스들을 생성하고 조립할 수 있다.
 * (DI개념 이용함)
 * 
 * [사용 절차]
 * 
 * 1. pom.xml에 의존설정
 * 
 * <dependencys> 엘리먼트 하위에 설정한다.
 * 
 * 		<dependency>
 *			<groupId>org.springframework</groupId>
 *			<artifactId>spring-jdbc</artifactId>
 *			<version>4.1.4.RELEASE</version>
 *		</dependency>
 *
 * 의존설정 후 관련 라이브러리가 다운로드 되었는지 확인한다. (Maven Dependencies)
 * 
 * 2. 스프링 설정파일에서 DB연결 관련 빈을 생성하기 위한 코드 삽입
 * 
 *  servlet-context.xml에 설정한다.
 * 
 	<!-- Spring JDBC를 사용하기 위해 빈을 생성하는 부분 -->
	<beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<beans:property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/>
		<beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:orcl"/>
		<beans:property name="username" value="kosmo"/>
		<beans:property name="password" value="1234"/>
	</beans:bean>

	<beans:bean name="template" class="org.springframework.jdbc.core.JdbcTemplate">
		<beans:property name="dataSource" ref="dataSource"></beans:property>
	</beans:bean>
	
	dataSource빈을 1차로 생성한 후 이를 참조하여 template빈을 생성함.
	의존설정 이후 이므로 class명은 자동완성 지원됨.
	즉 DB연결을 기존 DAO생성자에서 하는 것이 아니라 스프링 설정파일에서 미리 연결하고 컨트롤러에서는 해당 빈을 주입받아 사용하기만 하면 된다.
	
	
	3. 컨트롤러에서 스프링 설정파일에서 생성한 JdbcTemplate 빈을 주입받기 위한 멤버변수와 setter()메소드를 삽입한다.
	
	
	4. 해당 JdbcTemplate를 웹어플리케이션 전체에서 사용하기 위한 설정을 한다.
	/model/JDBCTemplateConst.java 클래스를 생성후 static변수(싱글톤객체)를 생성한다.
	
	5. 컨트롤러에서 빈을 자동으로 주입받기 위해 setter()에 @AutoWired 어노테이션 설정을 한다. 
	
		※ @Autowired 어노테이션
		-의존관계 자동설정(type 기반)
		-생성자, 필드, 메서드에 적용 가능
		-setXX()의 형식이 아니어도 적용가능
		-타입을 이용해 자동적으로 프로퍼티 값을 설정하므로 해당 타입의 빈 객체가 존재하지 않거나
		  빈 객체가 두개 이상 존재할 경우 예외 발생됨.

JdbcTemplate관련 주요 메소드

-List query(String sql, RowMapper rowMapper)
	: 여러개의 레코드를 반환하는 select계열의 쿼리문인 경우 사용
-List query(String sql, Object[] args, RowMapper rowMapper)
	: 인파라미터를 가진 여러개의 레코드를 반환하는 select게열의 쿼리문인 경우 사용
-int queryForInt(String sql) / queryForInt(String sql, Object[] args)
	: 쿼리문의 실행결과 숫자를 반환하는 Select게열의 쿼리문인 경우
-Object queryForObject(String sql, RowMapper rowMapper) / Object queryForObject(String sql, Object[] args, RowMapper rowMapper)
	: 하나의 레코드를 반환하는 select 계열의 쿼리문
-int update(String sql) 
	: 인파라미터가 없는 update/delete/insert 쿼리문인 경우 사용
-int update(String sql, Object[] args)
	: 인파라미터가 있는 update/delete/insert 쿼리문인 경우 사용

※In파라미터는 Object배열로 배열 초기화를 통해 전달한다.
Object[] args는 In파라미터

※queryForObject 메소드는 실행결과 레코드가 0 또는 2개 이상인 경우 예외발생됨.
try~catch문으로 처리해야함.
 * */
public class JDBCTemplateDAO {
	
	JdbcTemplate template;
	
	//생성자
	public JDBCTemplateDAO() {
		this.template = JDBCTemplateConst.template;
		System.out.println("템플릿 이용한 DB연결 성공");
	}
	
	public void close() {} // 아무기능 없으나 db.close();를 컨버팅 과정에서 에러안나게 하기 위해서 만들어둔다.
	
	//전체목록 카운트 하기
	public int getTotalCount(Map<String, Object> map){
		System.out.println("Spring JDBC 템플릿 사용[전체게시물 카운트]");
		String query = "SELECT COUNT(*) FROM springboard";
		return template.queryForObject(query, Integer.class);
	}
	
	
	//리스트 보기
	public ArrayList<SpringBbsDTO> list(Map<String, Object> map){
		System.out.println("Spring JDBC 탬플릿 이용[리스트보기]");
		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());
		
		String query = ""
				+"SELECT * FROM ("
				+"    SELECT Tb.*, rownum rNum FROM ("
				+"        SELECT * FROM springboard ";
				
				//검색기능구현
				if(map.get("Word")!=null){
					query +=" WHERE "+map.get("Column")+" "
						+ " LIKE '%"+map.get("Word")+"%' ";				
				}

				query += " ORDER BY bgroup DESC, bstep ASC"
				+"    ) Tb"
				+")"
				+"WHERE rNum BETWEEN " +start+ " AND " +end;
				
		return (ArrayList<SpringBbsDTO>)template.query(query, new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class));
	}
	
	//상세보기
	public SpringBbsDTO view(String idx) {
		//조회수 증가
		updateHit(idx);
		//파라미터 임의 조작을 통한 예외발생시 처리 - 개터새터 부분 예외처리? 도 해야하기때문에 일단 넘어감. ?
		SpringBbsDTO dto = null;
		String query = "SELECT * FROM springboard WHERE idx="+idx;
		try {
			dto = template.queryForObject(query, new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//조회수증가
	public void updateHit(final String idx) {
		
		String query = "UPDATE springboard SET hits=hits+1 WHERE idx=?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// TODO Auto-generated method stub
				psmt.setInt(1, Integer.parseInt(idx));
			}
		});
	}
	
	//글쓰기처리
	public void write(final String name, final String title, final String contents, final String pass) {
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				
				String query = "INSERT INTO springboard (idx, name, title, contents, hits, bgroup, bstep, bindent, pass)"
						+ " VALUES (springboard_seq.nextval, ?, ?, ?, 0,"
						+ " springboard_seq.nextval, 0, 0, ?)";
				
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setString(1, name);
				psmt.setString(2, title);
				psmt.setString(3, contents);
				psmt.setString(4, pass);
				return psmt;
			}
		});
	}
	
	
	//패스워드 검증
	public int password(String idx, String pass) {
		
		
		String query = "SELECT * FROM springboard WHERE idx ="+idx+" AND pass="+pass;
		
		/*
		 * 패스워드 검증을 위해 select를 한 후 조건에 맞는 레코드가 있다면 해당 레코드의 idx값을 반환하고
		 * 없으면 0을 반환한다.
		 * */
		int returnVal = 0;
		
		try {
			SpringBbsDTO dto = (SpringBbsDTO)template.queryForObject(query, new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class));
			returnVal = dto.getIdx();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnVal;
/*		
		return ((SpringBbsDTO)template.queryForObject(query, new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class))).getIdx();
		참고. idx가 다를경우 ??..??
*/
	}
	
	//수정하기
	public void modify(final String idx, final String name, final String title, final String contents, final String pass) {
		
		String query = "UPDATE springboard SET name=?, title=?, contents=? WHERE idx=? AND pass=?";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// TODO Auto-generated method stub
				psmt.setString(1, name);
				psmt.setString(2, title);
				psmt.setString(3, contents);
				psmt.setInt(4, Integer.parseInt(idx));
				psmt.setString(5, pass);
			}
		});
	}
	
	//삭제하기
	public void delete(final String idx){
		
		String query = "DELETE FROM springboard WHERE idx=?";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {			
				psmt.setInt(1, Integer.parseInt(idx));					
			}
		});
	}
	
	//답변글쓰기
	public void reply(final String name, final String title, final String contents, final String pass, final String bgroup, final String bstep, final String bindent) {
	
		//답변글쓰기전 업데이트
		replyPrevUpdate(bgroup, bstep);
		
		String query = "INSERT INTO springboard (idx, name, title, contents, pass, bgroup, bstep, bindent) VALUES"
				+ " (springboard_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// TODO Auto-generated method stub
				psmt.setString(1, name);
				psmt.setString(2, title);
				psmt.setString(3, contents);
				psmt.setString(4, pass);
				psmt.setInt(5, Integer.parseInt(bgroup));
				psmt.setInt(6, Integer.parseInt(bstep)+1);
				psmt.setInt(7, Integer.parseInt(bindent)+1);
			}
		});	
	}
	//답변글쓰기 전 레코드 일괄 업데이트
	private void replyPrevUpdate(final String strGroup, final String strStep) {
		
		String query = "UPDATE springboard SET bstep=bstep+1 WHERE bgroup=? AND bstep>?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// TODO Auto-generated method stub
				psmt.setInt(1, Integer.parseInt(strGroup));
				psmt.setInt(2, Integer.parseInt(strStep));
			}
		});
	}
}
