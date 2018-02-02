package mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class MyBoardDAO implements MyBoardDAOImpl {

	//기본생성자
	public MyBoardDAO() {};
	//JdbcTemplate 사용하기
	JdbcTemplate template;
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template =template;
	}
	
	//리스트
	@Override
	public ArrayList<MyBoardDTO> list() {
		
		String query = "SELECT*FROM myboard ORDER BY idx DESC";
		ArrayList<MyBoardDTO> lists = 
				(ArrayList<MyBoardDTO>)
				template.query(
						query,
						new BeanPropertyRowMapper<MyBoardDTO>(MyBoardDTO.class)
				);
		
		return lists;
	}
	//쓰기
	@Override
	public void write(final String name, final String contents, final String id) {
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				String query = "INSERT INTO myboard (idx, name, contents) VALUES"
						+ " (myboard_seq.nextval, ?, ?, ?)";
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setString(1, name);
				psmt.setString(2, contents);
				psmt.setString(3, id);
				return psmt;
			}
		});
	}
	
	//뷰
	@Override
	public MyBoardDTO view(String idx, String id) {
		// TODO Auto-generated method stub
		MyBoardDTO dto = null;
		String query = "SELECT*FROM myboard WHERE idx="+idx+"AND id='"+id+"' ";
		// idx는 숫자라 상관 없지만 id는 스트링 타입이라 ''로 다시 묶어줘야함..
		try {
			dto = template.queryForObject(query, new BeanPropertyRowMapper<MyBoardDTO>(MyBoardDTO.class));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}	
	
	//수정
	@Override
	public void modify(final String idx, final String name, final String contents, final String id) {
		
		String query = "UPDATE myboard SET name=?, contents=? WHERE idx=? AND id=?";
		
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// TODO Auto-generated method stub
				psmt.setString(1, name);
				psmt.setString(2, contents);
				psmt.setInt(3, Integer.parseInt(idx));
				psmt.setString(4, id);
			}
		});
	}	
	
	//삭제
	@Override
	public void delete(final String idx, final String id) {
		
		String query = "DELETE FROM myboard WHERE idx=? AND id=?";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// TODO Auto-generated method stub
				psmt.setInt(1, Integer.parseInt(idx));
				psmt.setString(2, id);
			}
		});
	}
	
	//로그인
	@Override
	public MemberDTO login(String id, String pass) {
		
		String query = "SELECT*FROM member WHERE id='"+id+"' AND pass='"+pass+"' ";
		MemberDTO memberDTO = null;
		try {
			memberDTO = template.queryForObject(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return memberDTO;
	}






}
