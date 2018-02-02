package springBoard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SpringBbsDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	//생성자 : 커넥션풀을 이용한 DB연결
	public SpringBbsDAO()
	{
		try{			
			Context ctx2 = new InitialContext();
			DataSource source = 
			(DataSource)ctx2.lookup("java:comp/env/jdbc/myoracle");
			
			con = source.getConnection();
			System.out.println("Spring Board DB연결 성공");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Spring Board DB연결 실패");
		}
	}
	
	//자원반납 : 연결해제가 아니라 커넥션풀에 다시 반납하는것임
	public void close(){
		try{
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(con!=null) con.close();
		}
		catch(Exception e){}
	}
	
	//전체목록 카운트 하기
	public int getTotalCount(Map<String,Object> map){
		
		int totalRecord = 0;
		
		try{
			String sql = "SELECT COUNT(*) FROM springboard";
						
			//검색기능구현
			if(map.get("Word")!=null){
				sql +=" WHERE "+map.get("Column")+" "
					+ " LIKE '%"+map.get("Word")+"%' ";				
			}
						
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return totalRecord;
	}
	
	
	//리스트 가져오기
	public ArrayList<SpringBbsDTO> list(Map<String,Object> map){
		
		ArrayList<SpringBbsDTO> lists = new ArrayList<SpringBbsDTO>();
		
		String sql = ""
			+"SELECT * FROM ("
			+"    SELECT Tb.*, rownum rNum FROM ("
			+"        SELECT * FROM springboard ";
			
			//검색기능구현
			if(map.get("Word")!=null){
				sql +=" WHERE "+map.get("Column")+" "
					+ " LIKE '%"+map.get("Word")+"%' ";				
			}

			sql += " ORDER BY bgroup DESC, bstep ASC"
			+"    ) Tb"
			+")"
			+"WHERE rNum BETWEEN ? AND ?";
		System.out.println("쿼리문:"+sql);
						
		try{
			//3.prepare 객체생성 및 실행
			psmt = con.prepareStatement(sql);
						
			psmt.setInt(1, 
				Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, 
				Integer.parseInt(map.get("end").toString()));			

			rs = psmt.executeQuery();
			while(rs.next())
			{
				//4.결과셋을 DTO객체에 담는다.
				SpringBbsDTO dto = new SpringBbsDTO();
				
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));					
				//dto.setTitle(rs.getString(3));
				dto.setContents(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setHits(rs.getInt(6));
				dto.setBgroup(rs.getInt(7));
				dto.setBstep(rs.getInt(8));
				dto.setBindent(rs.getInt(9));
				dto.setPass(rs.getString(10));
				
				
				String title = rs.getString("title");
				int bindent = rs.getInt("bindent");
				
				//답글의 깊이를 표현하기 위한 변수
				String replySpace = "";
				//답변글이면 제목앞에 아이콘을 붙여준다.
				if(bindent>0){					
					for(int i=0 ; i<=bindent ; i++){
						replySpace += "&nbsp;&nbsp;"; 
					}					
					title = replySpace 
						+"<img src='../common/images/re1.gif'>&nbsp;"
						+ title;
				}
				dto.setTitle(title);
				
				
				lists.add(dto);				 
			}		
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		
		return lists;
	}
	
	//글쓰기처리
	public void write(String name, String title, String contents,
			String pass){
		
		try{
			String sql = "INSERT INTO springboard (idx, name, title, "
				+ " contents, hits, bgroup, bstep, bindent, pass) "
				+ " VALUES ("
				+ " springboard_seq.nextval, ?, ?, ?, 0, "
				+ " springboard_seq.nextval, 0, 0, ?)";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, title);
			psmt.setString(3, contents);
			psmt.setString(4, pass);
			
			int rn = psmt.executeUpdate();			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//게시물 상세보기
	public SpringBbsDTO view(String idx){
		
		SpringBbsDTO dto = null;
		
		try{
			String sql = "select * from springboard "
					+ " where idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(idx));
			rs = psmt.executeQuery();
			if(rs.next()){				
				dto = new SpringBbsDTO();
				
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				/*
				 * 내용은 줄바꿈 처리해야함(수정에서도 해당 메소드를
				 * 같이 사용하기 위해 줄바꿈 처리는 command에서 함)
				 */
				dto.setContents(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setHits(rs.getInt(6));
				dto.setBgroup(rs.getInt(7));
				dto.setBstep(rs.getInt(8));
				dto.setBindent(rs.getInt(9));
				dto.setPass(rs.getString(10));				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	//패스워드 검증
	public int password(String idx, String pass){
		
		int returnNum = 0;
		try{
			String sql = "SELECT * FROM springboard"
					+ " WHERE idx=? AND pass=?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(idx));
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				/*조건에 맞는 게시물이 있다면 idx값은 무조건 0이상이므로
				0인지 여부를 확인하면 된다.*/
				returnNum = rs.getInt(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return returnNum;
	}
	
	//수정처리
	public void modify(String idx, String name, String title, 
			String contents, String pass){
		
		try{
			String sql = "UPDATE springboard "
					+ " SET name=?, title=?, contents=? "
					+ " WHERE idx=? AND pass=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, title);
			psmt.setString(3, contents);
			psmt.setInt(4, Integer.parseInt(idx));
			psmt.setString(5, pass);
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//삭제처리
	public void delete(String idx){
		
		try{
			String sql = "DELETE FROM springboard "
					+ " WHERE idx=?";
			psmt = con.prepareStatement(sql);			
			psmt.setInt(1, Integer.parseInt(idx));		
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//답글쓰기폼
	public SpringBbsDTO reply(String idx){
				
		SpringBbsDTO dto = null;
		try{
			String sql = "SELECT * FROM springboard WHERE idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(idx));
			rs = psmt.executeQuery();
			if(rs.next()){				
				dto = new SpringBbsDTO();
								
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setPostdate(rs.getDate(5));
				dto.setHits(rs.getInt(6));
				dto.setBgroup(rs.getInt(7));
				dto.setBstep(rs.getInt(8));
				dto.setBindent(rs.getInt(9));
				dto.setPass(rs.getString(10));				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

	//답글처리
	public void reply(String name, String title, String contents,
			String pass, String bgroup, String bstep, String bindent){
		
		//답변글쓰기전 레코드 업데이트
		replyPrevUpdate(bgroup, bstep);
		
		//답변글 입력
		try{
			String sql = "INSERT INTO springboard "
					+ " (idx, name, title, contents, pass, "
					+ "	bgroup, bstep, bindent) "
					+ " VALUES "
					+ " (springboard_seq.nextval, ?, ?, ?, ?,"
					+ " ?, ?, ?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, title);
			psmt.setString(3, contents);
			psmt.setString(4, pass);
			
			//답글은 기존글에 bstep+1, bindent+1 해준다.
			//bgroup : 원본글의 idx값을 입력받게 되어 같은 그룹으로 처리됨
			//bstep : 같은 그룹내에서의 정렬순서
			//bindent : 답변글의 깊이(1이라면 첫번째 답변글임)
			psmt.setInt(5, Integer.parseInt(bgroup));
			psmt.setInt(6, Integer.parseInt(bstep) + 1);
			psmt.setInt(7, Integer.parseInt(bindent) + 1);
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	//답변글 입력전 레코드 일괄 업데이트
	public void replyPrevUpdate(String strGroup, String strStep){
		/*
		 * 현재 답변글이 작성되는 위치(bstep)를 확인하여 
		 * 해당 위치보다 큰 레코드를 일괄적으로 +1 처리한다.
		 */
		try{
			String query = "UPDATE springboard "
					+ " SET bstep=bstep+1 "
					+ " WHERE bgroup=? AND bstep>?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(strGroup));
			psmt.setInt(2, Integer.parseInt(strStep));
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
}


