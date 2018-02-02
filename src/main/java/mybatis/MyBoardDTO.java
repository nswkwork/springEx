package mybatis;

public class MyBoardDTO {
	
	private int idx;
	private String id;
	private String name;
	private String contents;
	
	
	//기본생성자
	public MyBoardDTO() {}
	//인자생성자	
	public MyBoardDTO(int idx, String id, String name, String contents) {
		
		//슈퍼 지우는데 슈퍼 기능이 머징
		this.idx = idx;
		this.id = id;
		this.name = name;
		this.contents = contents;
	}
	
	//getter/setter메소드
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	

}
