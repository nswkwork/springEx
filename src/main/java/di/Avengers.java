package di;

public class Avengers {
	
	private String name;		//본명
	private String heroName;	//히어로명
	private String ability;		//능력
	private String age;			//나이
	
	//생성자 메소드 정의(인자생성자)
	public Avengers(String name, String heroName, String ability, String age) {
		
		this.name = name;
		this.heroName = heroName;
		this.ability = ability;
		this.age = age;
	}
		
	//getter메소드 정의
	public String getName() {
		return name;
	}

	public String getHeroName() {
		return heroName;
	}

	public String getAbility() {
		return ability;
	}

	public String getAge() {
		return age;
	}
	
}
