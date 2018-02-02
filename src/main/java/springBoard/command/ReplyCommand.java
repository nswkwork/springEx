package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;
import springBoard.model.SpringBbsDTO;

public class ReplyCommand implements BbsCommand{

	@Override
	public void execute(Model model) {
	
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
	
		String idx = req.getParameter("idx");
		
		//SpringBbsDAO dao = new SpringBbsDAO();
		//SpringBbsDTO dto = dao.reply(idx);
		
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		SpringBbsDTO dto = dao.view(idx);
		
		
		//제목처리
		dto.setTitle("[RE]"+ dto.getTitle());
		//내용처리
		dto.setContents("\n\r\n\r---[원본글]---\n\r"+ dto.getContents());
		
		model.addAttribute("replyRow", dto);
		dao.close();
	}
}
