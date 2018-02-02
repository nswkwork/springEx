<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>title</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet" />
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
      
});

function loginValidate(f){

   if(f.id.value==""){
      alert("아이디를 입력해주세요");
      f.id.focus();
      return false;
   }
   if(f.pass.value==""){
      alert("패스워드를 입력해주세요");
      f.pass.focus();
      return false;
   }
}
</script>
</head>
<body>
<div class="container">
   <!-- Top영역 -->
   <div class="row">
      <jsp:include page="/resources/bbsSkin/commonTop.jsp" />
   </div>
      
   <!-- Body영역 -->
   <div class="row">
      
      <!-- LNB영역 -->
      <div class="col-xs-3 col-md-3">
         <jsp:include page="/resources/bbsSkin/commonLeft.jsp" />
      </div>
      
      <!-- Contents영역 -->

     
      
	  <div class="col-xs-9 col-md-9">
		<c:choose>
			<c:when test="${not empty sessionScope.siteUserInfo }">
				<!-- 로그인 성공하여 세션영역에 데이터가 있을 때 -->
				<h3>로그인 성공 - <small>Mybatis로 제작한 방명록입니다.</small></h3>
				<div class="row" style="border:1px solid #cccccc; padding:10px;">
					<h4>아이디 : ${sessionScope.siteUserInfo.id }</h4>
					<h4>이름 : ${sessionScope.siteUserInfo.name }</h4>
					<br/><br/>
					<button class="btn btn-danger" onclick="location.href='logout.do';">로그아웃</button>
					<button class="btn btn-info" onclick="location.href='sessionFalse.do';">SessionFalse</button>
				</div>
			</c:when>
			<c:otherwise>
				<!-- 로그인 실패하여 세션영역에 데이터가 없을 때 -->
			  	<h3>로그인 - <small>Mybatis로 제작한 방명록입니다.</small></h3>
			  	
			  	<span style="font-size:1.5em; color:red;">${LoginNG }</span>
				<div class="row" style="border:1px solid #dddddd; padding:10px;">
					<form name="loginForm" method="post" action="./loginAction.do" onsubmit="return loginValidate(this);">
						<div class="col-sm-3"></div>
						<div class="col-sm-5">
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
								<input type="text" class="form-control" name="id" placeholder="아이디">
							</div>
							<div class="input-group" style="margin-top:10px;">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
								<input type="password" class="form-control" name="pass" placeholder="패스워드">
							</div>
						</div>
						<div class="col-sm-1">
							<button type="submit" class="btn btn-primary" style="height:77px; width:77px;">로그인</button>
						</div>
						<div class="col-sm-3"></div>     
					</form>
				</div>		
			</c:otherwise>
		</c:choose> 	  	
	  </div>

   </div>
   
   <!-- Bottom영역 -->
   <div class="row" style="border-top:1px solid #bbbbbb; padding-top:20px; margin-top:20px;">
      <jsp:include page="/resources/bbsSkin/commonBottom.jsp" />
   </div>
</div>
</body>
</html>