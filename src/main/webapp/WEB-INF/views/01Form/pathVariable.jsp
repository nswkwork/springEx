<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>01Form/pathVariable.jsp</title>
<link href="../../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
<div class="container">
	
	<h2>Form데이터 받기</h2>
	
	<h3>@PathVariable 어노테이션으로 폼값 받기</h3>
	
	<ul>
		<li>회원아이디 : ${memberId }</li>
		<li>회원이름 : ${memberName }</li>
	</ul>
	
	<p style="font-weight:bold; color:red; font-size:1.5em;">
		해당 파일의 경우 01form 아래의 다른 파일들과 동일한 경로이나
		요청명이 "./form/gildong/홍길동"의 형태로 1Depth를 더 들어간
		형태이므로 상단의 js, css파일의 링크는 ../를 한번 더 붙여줘야한다.
		웹브라우져는 요청명을 단순 경로로 인식하기 때문이다.
	</p>
		
</div>
</body>
</html>









