<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>05Environment/main1.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
<div class="container">
		
	<h2>Environment 객체를 이용한 외부파일 사용하기</h2>
	
	<h3>EnvAdmin.properties 파일에서 읽어온 Oracle접속정보</h3>
	<ul>
		<li>아이디 : ${adminID }</li>
		<li>패스워드 : ${adminPW }</li>
	</ul>
	
	<h3>EnvironmentAware 인터페이스를 이용하여 외부파일 읽기</h3>
	<ul>
		<li>아이디 : ${adminID2 }</li>
		<li>패스워드 : ${adminPW2 }</li>
	</ul>
</div>
</body>
</html>





