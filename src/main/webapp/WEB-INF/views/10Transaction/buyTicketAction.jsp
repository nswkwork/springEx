<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>10Transaction/buyTicketAction.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
</head>
<body>
<div class="container">
	<h2>트랜젝션(Transaction)</h2>
	<h3>트랜젝션 처리해서 티켓 구매결과</h3>
	
	<ul>
		<li>고객아이디 : ${ticketInfo.customerId } </li>
		<li>티켓구매수 : ${ticketInfo.amount }</li>
	</ul>
	
	<p>
		<a href="./buyTicketMain.do">티켓 구매페이지 바로가기</a>
	</p>
</div>
</body>
</html>