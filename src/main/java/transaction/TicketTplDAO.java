package transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TicketTplDAO {
	
	//스프링 JDBC 사용
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	//트랜젝션3에서 사용 : 트랜젝션 템플릿 사용위한 추가부분
	TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	//기본생성자
	public TicketTplDAO() {
		System.out.println(template);
	}
	
	//티켓구매 위한 메소드 정의
	public boolean buyTicket(final TicketDTO dto) {
		System.out.println("buyTicket()메소드 호출");
		System.out.println(dto.getCustomerId()+"님이 티켓"+dto.getAmount()+" 장을 구매합니다.");
		
		/*
		 * [트랜젝션 2에서 추가함] -- 템플릿에선 필요없음
		 * 트랜젝션을 사용하기 위해 기본적으로 필요한 객체생성
		 * */
/*		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = tranMgr.getTransaction(def);
		*/
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					//결제금액 처리
					template.update(new PreparedStatementCreator() {
						
						@Override
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

							//신용카드 결제내역 인서트
							String query = "INSERT INTO transaction_pay (customerId, amount) VALUES(?, ?)";
							PreparedStatement psmt = con.prepareStatement(query);
							psmt.setString(1, dto.getCustomerId());
							psmt.setInt(2, dto.getAmount()*10000);
							
							return psmt;
						}
					});
					//티켓구매 처리
					template.update(new PreparedStatementCreator() {
						
						@Override
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							
							//티켓 구매내역 인서트
							String query = "INSERT INTO transaction_ticket (customerId, countNum) VALUES (?, ?)";
							PreparedStatement psmt = con.prepareStatement(query);
							psmt.setString(1, dto.getCustomerId());
							psmt.setInt(2, dto.getAmount());
							
							return psmt;
						}
					});			
				}
			
			});
			System.out.println("결제금액,티켓구매 처리 모두 완료. 구매해주셔서 감사합니다.");
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("제약조건 위배하여 결제금액,티켓구매 처리가 모두 취소되었습니다.");
			return false;
		}
	}
}
