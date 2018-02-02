package springBoard.model;

import org.springframework.jdbc.core.JdbcTemplate;

// JDBCTemplate를 웹어플리케이션 어디서나 사용할 수 있도록 하기 위한 설정
public class JDBCTemplateConst {
	public static JdbcTemplate template;
}
