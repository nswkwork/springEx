package mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface MyBoardDAOImpl {
	
	//리스트
	public ArrayList<MyBoardDTO> list();
	//쓰기
	//public void write(String name, String contents, String id);
	public void write(@Param("_name") String name, @Param("_contents") String contents, @Param("_id") String id );
	
	//수정
	public void modify(String idx, String name, String contents, String id);
	//삭제
	public void delete(String idx, String id);
	//보기
	public MyBoardDTO view(String idx, String id);
	//로그인처리
	public MemberDTO login(String id, String pass);
	
}
