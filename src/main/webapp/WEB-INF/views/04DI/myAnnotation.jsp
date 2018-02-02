<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>04DI/myAnnotation.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
<div class="container">
	
	<h2>어노테이션을 통한 JAVA파일에서 DI 사용하기</h2>
	
	<ul>
		<li>홍길동 회원님 정보 : </li>
		<li>${memberInfo1 }</li>
	</ul>
	
	<ul>
		<li>성낙현(본인이름) 회원님 정보 : </li>
		<li>${memberInfo2 }</li>
	</ul>
</div>
</body>
</html>









