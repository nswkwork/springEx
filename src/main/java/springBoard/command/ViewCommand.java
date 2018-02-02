package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDTO;

public class ViewCommand implements BbsCommand{

	@Override
	public void execute(Model model) {
		
		//파라미터 한번에 받기...
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		String idx = req.getParameter("idx");
		
		//커넥션풀을 이용한 JDBC 사용을 위한 설정
		//SpringBbsDAO dao = new SpringBbsDAO();

		//Spring JDBC를 사용하기 위한 설정
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		SpringBbsDTO dto = null;
		try {
			dto = dao.view(idx);
			//상세보기 줄바꿈처리-getter로 줄바꿈 처리후 다시 setter로 저장
			dto.setContents(dto.getContents().replace("\r\n", "<br/>"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("viewRow", dto);
		
		//템플릿에선 사용안함
		dao.close();
	}
}
