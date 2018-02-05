<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>11FileUpload/uploadAction.jsp</title>
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
	<h2>파일 업로드 결과보기</h2>
	
	<c:forEach begin="0" end="${returnObj.files.size()-1 }" var="i">
		<ul>
			<li>제목${i } : ${returnObj.files[i].title }
			<li>원본파일명${i } : ${returnObj.files[i].originalName }
			<li>저장된파일명${i } : ${returnObj.files[i].serverFileName }
		</ul>
	</c:forEach>
</div>
</body>
</html>