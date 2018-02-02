<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>04DI/myCalculator.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
<div class="container">
	
	<h2>DI를 활용한 간단한 사칙연산 계산기</h2>	
	
	<h3>200과 100의 사칙연산 결과는?</h3>
	
	<ul>
		<li>덧셈결과 : ${addResult }</li>
		<li>뺄셈결과 : ${subResult }</li>
		<li>곱셈결과 : ${mulResult }</li>
		<li>나눗셈결과 : ${divResult }</li>
	</ul>	

</div>
</body>
</html>






