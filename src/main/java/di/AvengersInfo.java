package di;

public class AvengersInfo {
	
	//멤버변수
	private Avengers avengers;
	
	//인자생성자
	public AvengersInfo(Avengers avengers) {
		super();
		this.avengers = avengers;
	}
	
	//getter/setter
	public Avengers getAvengers() {
		return avengers;
	}
	public void setAvengers(Avengers avengers) {
		this.avengers = avengers;
	}
	
	//정보출력위한 메소드 정의
	public String AvengersView(){
		
		String returnStr = "";
		
		if(avengers != null){
			returnStr = String.format("본명:%s<br/>"
					, avengers.getName());
			returnStr += String.format("히어로명:%s<br/>"
					, avengers.getHeroName());
			returnStr += String.format("능력:%s<br/>"
					, avengers.getAbility());
			returnStr += String.format("나이:%s<br/>"
					, avengers.getAge());
		}
		
		return returnStr;
	}
}









