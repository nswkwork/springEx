package AOP;

public class ObjStudent {
	
	//학생을 표현하는 클래스
	//이름,나이,전공과목,학년
	private String name; 
	private int age;
	private String major;
	private int gradeNum;
	
	//view메소드
	public void getObjStudentView() {
		System.out.println("이름:"+getName());
		System.out.println("나이:"+getAge());
		System.out.println("전공과목:"+getMajor());
		System.out.println("학년:"+getGradeNum());
	}
	
	//게러세러 메쒀드
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getGradeNum() {
		return gradeNum;
	}
	public void setGradeNum(int gradeNum) {
		this.gradeNum = gradeNum;
	}
	
	
}
