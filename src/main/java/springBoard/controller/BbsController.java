package springBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springBoard.command.BbsCommand;
import springBoard.command.DeleteActionCommand;
import springBoard.command.ListCommand;
import springBoard.command.ModifyActionCommand;
import springBoard.command.ModifyCommand;
import springBoard.command.ReplyActionCommand;
import springBoard.command.ReplyCommand;
import springBoard.command.ViewCommand;
import springBoard.command.WriteActionCommand;
import springBoard.model.JDBCTemplateConst;
import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;

@Controller
public class BbsController {
	
	/*
	 * Spring JDBC를 사용하기 위한 설정
	 * 멤버변수 template과 setter()메소드 삽입
	 */
	private JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		
		//JdbcTemplate을 웹어플리케이션 전체에서 사용하기 위한 설정
		JDBCTemplateConst.template = this.template;
	}


	//BbsCommand 참조변수 선언(클래스내에서 전역적으로 사용하기 위함)
	BbsCommand command = null;
	
	//게시판 리스트
	@RequestMapping("/board/list.do")
	public String list(Model model, HttpServletRequest req){
		
		System.out.println("list()메소드 호출됨");
		
		//JdbcTemplate을 이용한 DB연결 확인을 위한 임시코드
		//JDBCTemplateDAO dao = new JDBCTemplateDAO();
		//위에 맞나 확인
		
		//컨트롤러가 받은 파라미터 전체를 ListCommand로 넘기는 역활
		model.addAttribute("req", req);
		command = new ListCommand();
		command.execute(model);
		
		return "06Board/list";
	}
	
	//게시판 상세보기
	@RequestMapping("/board/view.do")
	public String view(Model model, HttpServletRequest req){
		
		System.out.println("view()메소드 호출됨");
		
		//컨트롤러가 받은 파라미터 전체를 ListCommand로 넘기는 역활
		model.addAttribute("req", req);
		command = new ViewCommand();
		command.execute(model);
		
		return "06Board/view";
	}
	
	//게시판 글쓰기
	@RequestMapping("/board/write.do")
	public String write(Model model, HttpServletRequest req){
		
		System.out.println("write()메소드 호출됨");
		
		//컨트롤러가 받은 파라미터 전체를 ListCommand로 넘기는 역활
		model.addAttribute("req", req);
		/*command = new ListCommand();
		command.execute(model);*/
		
		return "06Board/write";
	}
	
	//글쓰기처리
	@RequestMapping("/board/writeAction.do")
	public String writeAction(Model model, HttpServletRequest req){
		
		model.addAttribute("req", req);
		command = new WriteActionCommand();
		command.execute(model);
		
		//뷰 연결이 아닌 페이지이동을 할때에는 redirect를 사용함
		return "redirect:list.do?nowPage=1";
	}
	
	//수정,삭제전 패스워드 확인페이지
	@RequestMapping("/board/password.do")
	public String password(Model model, HttpServletRequest req){
		
		//model.addAttribute("mode", req.getParameter("mode"));
		model.addAttribute("idx", req.getParameter("idx"));		
		
		return "06Board/password";
	}
	
	//패스워드 검증
	@RequestMapping("/board/passwordAction.do")
	public String passwordAction(Model model, HttpServletRequest req){
		
		//파라미터 받기
		String mode = req.getParameter("mode");
		String idx = req.getParameter("idx");
		String pass = req.getParameter("pass");
		//패스워드 검증후 이동할 페이지명
		String modePage = null;
		
		//SpringBbsDAO dao = new SpringBbsDAO();
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		//패스워드 검증
		int rowNum = dao.password(idx, pass);
		dao.close();
		
		if(rowNum<=0){
			//패스워드가 일치하지 않는 경우
			model.addAttribute("isCorrMsg", "패스워드가 일치하지 않습니다");
			model.addAttribute("mode", mode);
			model.addAttribute("idx", idx);
			
			modePage = "06Board/password";
		}
		else{
			//패스워드가 일치하는 경우
			if(mode.equals("modify")){
				model.addAttribute("req", req);
				command = new ModifyCommand();
				command.execute(model);
				
				modePage = "06Board/modify";
			}
			else if(mode.equals("delete")){
				model.addAttribute("req", req);
				command = new DeleteActionCommand();
				command.execute(model);
				
				model.addAttribute("nowPage", req.getParameter("nowPage"));
				modePage = "redirect:list.do";
			}
		}
		
		return modePage;
	}
	
	//수정처리
	@RequestMapping("/board/modifyAction.do")
	public String modifyAction(HttpServletRequest req, Model model){
				
		model.addAttribute("req", req);
		command = new ModifyActionCommand();
		command.execute(model);
		
		//수정처리후 상세보기 페이지로 이동함
		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:view.do";
	}
	
	//답변글쓰기
	@RequestMapping("/board/reply.do")
	public String reply(HttpServletRequest req, Model model){
		
		System.out.println("reply()메소드호출");
		
		model.addAttribute("req", req);
		command = new ReplyCommand();
		command.execute(model);
		
		model.addAttribute("idx", req.getParameter("idx"));		
		return "06Board/reply";
	}
	
	//답변글처리
	@RequestMapping("/board/replyAction.do")
	public String replyAction(HttpServletRequest req, Model model){
		
		System.out.println("replyAction() 메소드 호출");
		
		model.addAttribute("req", req);
		command = new ReplyActionCommand();
		command.execute(model);
		
		model.addAttribute("nowPage", req.getParameter("nowPage"));	
		return "redirect:list.do";
	}	
	
	
	
}









