<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>01Form/requestParam.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
<div class="container">
	
	<h2>Form 데이터 받기</h2>
	
	<h3>@RequestParam 으로 파라미터 받기</h3>
	
	<ul>
		<li>이름 : ${memberInfo.name }</li>
		<li>아이디 : ${memberInfo.id }</li>
		<li>패스워드 : ${memberInfo.pw }</li>
		<li>이메일 : ${memberInfo.email }</li>
	</ul>
</div>
</body>
</html>





