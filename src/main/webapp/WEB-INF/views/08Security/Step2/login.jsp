<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>08Security/Step2/login.jsp</title>
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
	<h2>로그인 화면</h2>
	
	<c:url value="/login" var="loginUrl"/>
	<form:form name="loginFrm" action="${loginUrl }" method="post">
		<c:if test="${param.error != null }">
			<p>아이디와 패스워드가 잘못되었습니다.</p>
		</c:if>
		<c:if test="${param.login != null }">
			<p>로그아웃 하였습니다.</p>
		</c:if>
		
		<p>
			아이디 : <input type="text" name="id" value="kosmo_"/>
		</p>
		<p>
			패스워드 : <input type="password" name="pass"/>
		</p>
		<button type="submit" class="btn btn-info">로그인하기</button>
	</form:form>
</div>
</body>
</html>