<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>02RequestMapping/postLogin.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
<div class="container">
	
	<h2>@RequestMapping 어노테이션</h2>
	
	<h3>POST 방식으로 전송된 로그인 파라미터</h3>
	
	<ul>
		<li>아이디 : ${user_id }</li>
		<li>패스워드 : ${user_pw }</li>
	</ul>
	
</div>
</body>
</html>







