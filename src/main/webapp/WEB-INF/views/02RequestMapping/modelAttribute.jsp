<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>02RequestMapping/modelAttribute.jsp</title>
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
	
	<h3>@ModelAttribute 어노테이션을 사용하여 파라미터 일괄전송</h3>
	
	<ul>
		<li>이름 : ${sInfo.name }</li>
		<li>나이 : ${sInfo.age }</li>
		<li>학년 : ${sInfo.gradeNum }</li>
		<li>학반 : ${sInfo.classNum }</li>
	</ul>
	
</div>
</body>
</html>







