<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>05Environment/main3.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
<div class="container">
	
	<h2>어노테이션을 이용한 외부파일 참조하기</h2>
	
	<h3>게시판 설정값 읽어오기</h3>
	
	<ul>
		<li>타이틀 : ${Title }</li>
		<li>컨텐츠 : ${Content }</li>
		<li>작성자 : ${Writer }</li>
		<li>비밀번호 : ${Passwd }</li>
	</ul>	
	
</div>	
</body>
</html>

