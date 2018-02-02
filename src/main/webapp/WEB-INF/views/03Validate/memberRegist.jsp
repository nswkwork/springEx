<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>03Validate/memberRegist.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
function registCheck(){
	//스프링에서 제공하는 클래스를 통해 검증하기 때문에 구색만 갖춤...
}
</script>
</head>
<body>
<div class="container">
	<h2>Validator 인터페이스를 구현한 폼값 검증</h2>
	
	<span style="color:red; font-size:1.5em;">${formError }</span>
	
	<form action="./registProc" method="post" name="registFrm"
		onsubmit="return registCheck();">
	<table class="table table-bordered" style="width:500px;">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" value="${mInfo.id }" /></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="pw" value="${mInfo.pw }" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="${mInfo.name }" /></td>
		</tr>
		<tr>
			<td class="text-center" colspan="2">
				<button type="submit">회원가입하기</button>
			</td>
		</tr>
	</table>
	</form>	
</div>
</body>
</html>












