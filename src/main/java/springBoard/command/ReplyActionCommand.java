package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;

public class ReplyActionCommand implements BbsCommand{
	
	@Override
	public void execute(Model model) {
	
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String pass = req.getParameter("pass");
		
		String bgroup = req.getParameter("bgroup");
		String bstep = req.getParameter("bstep");
		String bindent = req.getParameter("bindent");
		
		//SpringBbsDAO dao = new SpringBbsDAO();
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		dao.reply(name, title, contents, pass, bgroup, bstep, bindent);		
	}
}
