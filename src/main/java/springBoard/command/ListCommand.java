package springBoard.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.PagingUtil;
import springBoard.model.SpringBbsDTO;

public class ListCommand implements BbsCommand {

	@Override
	public void execute(Model model) {
		
		//DAO연결 - 커넥션풀을 통한 연결 이후 DB사용
		//SpringBbsDAO dao = new SpringBbsDAO();
		
		//JdbcTemplate을 통한 DB연결 이후 DB사용
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		//컨트롤러에서 넘겨준 파라미터 한번에 받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
				
		//검색기능구현
		String addQueryString = "";
		String keyField = req.getParameter("keyField");
		String keyString = req.getParameter("keyString");
		if(keyString!=null)
		{
			//페이지번호에 링크를 걸기위한 추가 파라미터
			addQueryString = String.format("keyField=%s"
				+"&keyString=%s&", keyField, keyString);
			
			paramMap.put("Column", keyField);
			paramMap.put("Word", keyString);
		}
				
		
		//전체 레코드 수 카운트하기
		int totalRecordCount = dao.getTotalCount(paramMap);
		
		//페이지 처리를 위한 설정값 가져오기(우선은 변수로 처리)
		
		/*int pageSize = 4;
		int blockPage = 2;*/
		
		//외부파일에서 페이지 설정값 가져오기(Environment객체사용)
		int pageSize = 0;
		int blockPage = 0;
		
		/*
		 * 설정값으로 외부파일을 사용하기 위한 방법
		 * 
		 * 1. 스프링 컨텍스트 파일을 생성한다. 
		 * */
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		
		/*
		 * 2. Environment객체를 생성한다. 
		 * */
		ConfigurableEnvironment env = ctx.getEnvironment();
		
		/*
		 * 3. PropertySources를 가져온다.
		 * */
		MutablePropertySources propertySources = env.getPropertySources();
		
		try {
			
			/*
			 * 4. 외부파일인 properties파일을 가져와서 addLast로 추가한다.
			 * */
			propertySources.addLast(new ResourcePropertySource("classpath:SpringBoardInit.properties"));
			
			/*
			 * 5. 해당 데이터를 getProperty로 읽어서 변수에 저장한다.
			 * 단, String타입이므로 int타입으로 캐스팅 후 사용해야 한다.
			 * */
			pageSize = Integer.parseInt(env.getProperty("springBoard.pageSize"));
			blockPage = Integer.parseInt(env.getProperty("springBoard.blockPage"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//전체페이지수계산하기
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		
		//시작 및 끝 rownum 구하기
		int nowPage = req.getParameter("nowPage")==null ? 1 : 
			Integer.parseInt(req.getParameter("nowPage"));
		int start = (nowPage-1) * pageSize + 1;
		int end = nowPage * pageSize;
		
		//리스트 가져오기 위한 파라미터 저장
		paramMap.put("start", start);
		paramMap.put("end", end);		
				
		//출력할 리스트 가져오기
		ArrayList<SpringBbsDTO> listRows = dao.list(paramMap);
		
		/*
		 * JdbcTemplate사용시 답변글 들여쓰기 처리
		 *  : 기존은 DAO에서 처리했지만, 템플릿을 사용할때는 command()에서 처리한다.
		 * */
		
		int virtualNum = 0; // 리스트 가상번호 부여를
		int countNum = 0; // 위한 변수 추가...
		
		for(SpringBbsDTO row : listRows) {
			String reSpace = "";
			if(row.getBindent()>0) {
				//답변글 들여쓰기
				for(int i=0 ; i<=row.getBindent(); i++) {
					reSpace += "&nbsp;&nbsp;";
				}
				//답변글 아이콘 붙여주기
				row.setTitle(reSpace + "<img src='../common/images/re1.gif'>" + row.getTitle());
			}
			reSpace = "";
			
			/*
			 * 리스트 가상번호 부여하기 : 게시물의 일련번호(idx)는 DB처리를 위해 필요한 값이므로
			 * 게시판 리스트의 번호는 전체 레코드 수를 기준으로 하여 역순으로 출력해 주는 것이 좋다.
			 * */
			virtualNum = totalRecordCount - (((nowPage-1)*pageSize)+countNum++);
			row.setVirtualNum(virtualNum);
			//System.out.println(row.getVirtualNum());
			/*
			 * 
			 *  JDBC템플릿DAO에  
			 *  
				+"SELECT * FROM ("
				+"    SELECT Tb.*, rownum rNum FROM ("
				+"        SELECT * FROM springboard ";
				
				query += " ORDER BY bgroup DESC, bstep ASC"
				+"    ) Tb"
				+")"
				+"WHERE rNum BETWEEN " +start+ " AND " +end;

				와 같이 rownum에 대해서 설명되어 있고 정렬도 되어있음.
				그 기준으로 뽑아왔기 때문에 listRows에서 실제로 한 페이지를 조회했을때 보여지는 레코드 수는 페이지사이즈인 5개임.
				
			 * */
		}
		
		
		//페이지 처리를 위한 처리부분
		String pagingImg = PagingUtil.pagingImgServlet(totalRecordCount,
				pageSize, blockPage, nowPage, 
				req.getContextPath()+"/board/list.do?"+addQueryString);
		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("totalPage", totalPage);//전체페이지수	
		model.addAttribute("nowPage", nowPage);//현재페이지번호		
		model.addAttribute("listRows", listRows);
		
		//JdbcTemplate 사용시에는 사용하지 않는다. 아래를 주석처리 하거나
		//템플릿DAO에 비어있는 close 메소드 만들어줌. public void close() {} 이렇게.
		dao.close();
		
	}
}
