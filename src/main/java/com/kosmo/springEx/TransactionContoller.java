package com.kosmo.springEx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import transaction.TicketDAO;
import transaction.TicketDTO;
import transaction.TicketTplDAO;

@Controller
public class TransactionContoller {
	/*
	
	
	//servlet-context.xml 스프링 설정파일에서 생성한 DAO빈을 자동으로 주입받음
	
	//트랜젝션2에서 사용할 DAO
	private TicketDAO dao;
	@Autowired
	public void setDao(TicketDAO dao) {
		this.dao = dao;
	}
	*/
	

	
	@RequestMapping("/transaction/buyTicketMain.do")
	public String buyTicketMain() {
		
		return "10Transaction/buyTicketMain";
	}
	@RequestMapping("/transaction/buyTicketAction.do")
	public String buyTicketAction(TicketDTO dto, Model model) {
		
		//티켓 구매페이지의 폼값을 커맨드객체(DTO)를 통해 한번에 받는다.
		dao.buyTicket(dto);
		model.addAttribute("ticketInfo", dto);
		
		return "10Transaction/buyTicketAction";
	}
	
	//트랜젝션3에서 사용할 DAO
	private TicketTplDAO dao;
	@Autowired
	public void setDao(TicketTplDAO dao) {
		this.dao = dao;
	}	
	//트랜젝션 템플릿 이용한 티켓 구매
	@RequestMapping("transaction/buyTicketTpl.do")
	public String buyTicketTpl() {
		return "10Transaction/buyTicketTpl";
	}
	
	//
	@RequestMapping("/transaction/buyTicketTplAction.do")
	public String buyTicketTplAction(TicketDTO dto, Model model) {
		
		//티켓 구매페이지의 폼값을 커맨드객체(DTO)를 통해 한번에 받는다.
		boolean isBoolean = dao.buyTicket(dto);
		if(isBoolean==true) {
			model.addAttribute("successOrFail","구매처리정상완료");
		}
		else {
			model.addAttribute("successOrFail", "티켓구매 실패하였습니다.");
		}
		model.addAttribute("ticketInfo", dto);
		
		return "10Transaction/buyTicketTplAction";
	}
}
