package env;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/*
 * @Configuration 어노테이션으로 XML설정파일의 역활을 하는
 * 클래스로 정의함.
 */
@Configuration
public class EnvApplicationConfig {
	
	/*
	 * 멤버변수 설정값을 @Value 어노테이션으로 정의함.
	 */
	@Value("${board1.title}")
	private String board_title;
	
	@Value("${board1.content}")
	private String board_content;
	
	@Value("${board2.writer}")
	private String board_writer;
	
	@Value("${board2.passwd}")
	private String board_passwd;
	
	
	/*
	 * PropertySourcesPlaceholderConfigurer() 클래스를 이용해서
	 * 프로퍼티소스(외부파일)을 읽어온다.
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer Properties(){
		
		PropertySourcesPlaceholderConfigurer config = new
			PropertySourcesPlaceholderConfigurer();
		
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("EnvBoard1.properties");
		locations[1] = new ClassPathResource("EnvBoard2.properties");
		config.setLocations(locations);
		
		return config;	
	}
	
	/*
	 * 빈을 생성함
	 */
	@Bean
	public BoardConnection boardConfig(){
		
		BoardConnection boardConnection = new BoardConnection();
		
		boardConnection.setTitle(board_title);
		boardConnection.setContent(board_content);
		boardConnection.setWriter(board_writer);
		boardConnection.setPasswd(board_passwd);
		
		return boardConnection;
	}	
}




