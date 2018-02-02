package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;

public class ModifyActionCommand implements BbsCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		String idx = req.getParameter("idx");				
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String pass = req.getParameter("pass");
		
		//SpringBbsDAO dao = new SpringBbsDAO();
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		dao.modify(idx, name, title, contents, pass);		
	}
}

