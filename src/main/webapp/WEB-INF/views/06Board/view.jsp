<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>06Board/view.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});
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
			<h3>게시판 상세보기 - 
				<small>SPRING Framework로 제작한 게시판입니다.</small></h3>
 
<form enctype="multipart/form-data">
<table class="table table-bordered">
<colgroup>
	<col width="20%"/>
	<col width="30%"/>
	<col width="20%"/>
	<col width="*"/>
</colgroup>
<tbody>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">번호</th>
		<td>
			${viewRow.idx }
		</td>
		<th class="text-center" 
			style="vertical-align:middle;">작성자</th>
		<td>
			${viewRow.name }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">작성일</th>
		<td>
			${viewRow.postdate }
		</td>
		<th class="text-center" 
			style="vertical-align:middle;">조회수</th>
		<td>
			${viewRow.hits }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">제목</th>
		<td colspan="3">
			${viewRow.title }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">내용</th>
		<td colspan="3">
			${viewRow.contents }
		</td>
	</tr>	 
</tbody>
</table>

<div class="row text-center" style="">
	<!-- 각종 버튼 부분 -->
	<button type="button" class="btn btn-info" 
	onclick="location.href='reply.do?idx=${viewRow.idx}&nowPage=${param.nowPage}';">답글쓰기</button>
	
	<button type="button" class="btn btn-primary" 
	onclick="location.href='password.do?idx=${viewRow.idx}&mode=modify&nowPage=${param.nowPage}';">수정하기</button>
		
	<button type="button" class="btn btn-success" 
	onclick="location.href='password.do?idx=${viewRow.idx}&mode=delete&nowPage=${param.nowPage}';">삭제하기</button>	
	
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