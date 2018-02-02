<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<title>07Mybatis/sessionFalse.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
</head>
<body>
<div class="container">
	<h2>사용자 페이지</h2>
	<h3>세션을 사용하지 않는 페이지</h3>
	
	<ul>
		<li>세션아이디 : ${sessionScope.siteUserInfo.id } </li>
		<li>세션이름 : ${sessionScope.siteUserInfo.name } </li>
	</ul>
	
	<h3>페이지 지시어 session="false" 선언함</h3>
	
	<button type="button" class="btn btn-success" onclick="history.go(-1);">뒤로가기</button>
</div>
</body>
</html>