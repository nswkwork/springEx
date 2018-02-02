<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>07Mybatis/list.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../common/bootstrap3.3.7/css/bootstrap.min.css" />
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
function deleteRow(idx){
	if(confirm("정말로 삭제하시겠습니까?")){
		location.href="delete.do?idx="+idx;
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
			<h3>방명록(한줄게시판)
				<small>Mybatis로 제작한 방명록입니다.</small></h3>
			
			<div class="row" style="text-align:right; margin-bottom:20px;">
				<button class="btn btn-default" onclick="location.href='write.do';">글쓰기</button>
			</div>
			
			<div class="row" style="border:1px solid #dddddd; padding:15px;">
				
			<c:forEach items="${lists }" var="row">
				<!-- 반복 시작 -->
				<div class="media">
				   <div class="media-left">
				      <img src="../common/images/img_avatar1.png" class="media-object" style="width:60px">
				   </div>
				   <div class="media-body">
				      <h4 class="media-heading">작성자 : ${row.name }(${row.id })</h4>
				      <p>${row.contents }</p>
				   </div>
				   <div class="media-right" style="width:100px;">
				   
			         <c:choose>
			         	<c:when test="${sessionScope.siteUserInfo.id==row.id }">
			         	<button class="btn btn-primary" onclick="location.href='modify.do?idx=${row.idx}';">수정하기</button>
			         	<button class="btn btn-success" onclick="javascript:deleteRow(${row.idx});">삭제하기</button>
			         	</c:when>
			         	
			         	<c:otherwise>
			         	
			         	</c:otherwise>
			         </c:choose>			   	  
				      
				      <!-- 자바스크립트 온클릭에 넣을때 ''나 "" 해야하는데 숫자라 안넣어도 됨 -->
				   </div>
				</div>
				<hr/>
				<!-- 반복 끝 -->	
			</c:forEach>	
			</div>
			
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