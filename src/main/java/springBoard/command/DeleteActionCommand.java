package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;

public class DeleteActionCommand implements BbsCommand{

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		String idx = req.getParameter("idx");	
		
		//SpringBbsDAO dao = new SpringBbsDAO();
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		dao.delete(idx);		
	}
}

