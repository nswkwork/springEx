<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>title</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});

function writeValidate()
{
	var f = document.writeFrm;
	if(f.pass.value==""){
		alert("비밀번호를 입력하세요");
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
			<h3>게시판 패스워드 확인 - 
				<small>SPRING Framework로 제작한 게시판입니다.</small></h3>
 
<form name="writeFrm" method="post" onsubmit="return writeValidate();"
	action="<c:url value="/board/passwordAction.do" />" >

<!-- 게시물 확인을 위해 일련번호가 필요함 -->	
<input type="hidden" name="idx" value="${idx }"/>	
<input type="hidden" name="mode" value="${param.mode }"/>
<input type="hidden" name="nowPage" value="${param.nowPage }" >

<!-- 패스워드 검증에 실패했을때 에러메세지 출력 -->
<span style="color:red; font-size:1.5em;">${isCorrMsg }</span>

<table class="table table-bordered">
<colgroup>
	<col width="20%"/>
	<col width="*"/>
</colgroup>
<tbody>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">패스워드</th>
		<td>
			<input type="password" class="form-control" 
				style="width:200px;" name="pass" />
		</td>
	</tr>
</tbody>
</table>

<div class="row text-center" style="">
	<!-- 각종 버튼 부분 -->
	
	<button type="submit" class="btn btn-danger">전송하기</button>
	<button type="reset" class="btn">Reset</button>
	<button type="button" class="btn btn-warning" 
		onclick="location.href='list.do?nowPage=${param.nowPage}';">리스트보기</button>
</div>
</form> 
		</div>
	</div>
	<!-- Bottom영역 -->
	<div class="row" 
		style="border-top:1px solid #bbbbbb; 
			padding-top:20px; margin-top:20px;">
	<jsp:include page="/resources/bbsSkin/commonBottom.jsp" />
	</div>
	
</div>
</body>
</html>