package springBoard.model;

import java.sql.Date;

public class SpringBbsDTO {

	private int idx;
	private String name;
	private String title;
	private String contents;
	private java.sql.Date postdate;
	private int hits;
	private int bgroup;
	private int bstep;
	private int bindent;
	private String pass;
	
	//리스트 가상번호 출력을 위한 컬럼 추가
	private int virtualNum;
	
	public int getVirtualNum() {
		return virtualNum;
	}
	public void setVirtualNum(int virtualNum) {
		this.virtualNum = virtualNum;
	}
	
	/*
	 * 클래스를 정의할때 생성자를 별도로 정의하지 않으면 컴파일러가
	 * 자동으로 기본생성자를 만들어주지만, 
	 * 생성자를 정의하는 경우에는 반드시 정의한 생성자를 통해 객체를
	 * 생성해야한다. 
	 * 이경우 기본생성자를 통해 객체를 생성하고 싶다면 기본생성자를 
	 * 정의해야한다.
	 * */
	
	//기본생성자
	public SpringBbsDTO(){}
	
	//인자생성자
	public SpringBbsDTO(int idx, String name, String title, String contents, Date postdate, int hits, int bgroup,
			int bstep, int bindent, String pass) {

		this.idx = idx;
		this.name = name;
		this.title = title;
		this.contents = contents;
		this.postdate = postdate;
		this.hits = hits;
		this.bgroup = bgroup;
		this.bstep = bstep;
		this.bindent = bindent;
		this.pass = pass;
	}	
	//getter() / setter()
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getBgroup() {
		return bgroup;
	}
	public void setBgroup(int bgroup) {
		this.bgroup = bgroup;
	}
	public int getBstep() {
		return bstep;
	}
	public void setBstep(int bstep) {
		this.bstep = bstep;
	}
	public int getBindent() {
		return bindent;
	}
	public void setBindent(int bindent) {
		this.bindent = bindent;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}


}
