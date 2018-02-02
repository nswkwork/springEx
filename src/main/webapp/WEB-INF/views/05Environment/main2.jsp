<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>05Environment/main2.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
<div class="container">
	
	<h2>XML파일에 프로퍼티 파일을 명시한후 외부파일 읽어오기</h2>
	
	<h3>메인관리자정보</h3>
	<ul>
		<li>아이디 : ${mainUserId }</li>
		<li>패스워드 : ${mainUserPw }</li>
	</ul>
	
	<h3>서브관리자정보</h3>
	<ul>
		<li>아이디 : ${subUserId }</li>
		<li>패스워드 : ${subUserPw }</li>
	</ul>
	
</div>
</body>
</html>


