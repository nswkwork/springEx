<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>10Transaction/buyTicketTpl.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
function buyTicketValidate(f){
	if(!f.customerId.value){
		alert("고객아이디를 입력하세요");
		f.customerId.focus();
		return false;
	}
}
</script>
</head>
<body>
<div class="container">
	<h2>트랜젝션(Transaction)</h2>
	<h3>트랜젝션 템플릿 이용하여 티켓 구매하기</h3>
	<h4>제약조건 : check(countNum<5) -> 티켓구매는 4장까지만 가능함</h4>
	
	<form action="buyTicketTplAction.do" method="post" name="ticketFrm" onsubmit="return buyTicketValidate(this);">
	<table class="table table-bordered" style="width:500px;">
		<tr>
			<td>고객아이디</td>
			<td>
				<input type="text" name="customerId"/>
			</td>
		</tr>
		<tr>
			<td>티켓구매수</td>
			<td>
				<select name="amount">
				<%
				for(int i=1 ; i<=10 ; i++){
				%>
					<option value="<%=i %>"><%=i %>장</option>
				<%
				}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-warning">구매하기</button>
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>