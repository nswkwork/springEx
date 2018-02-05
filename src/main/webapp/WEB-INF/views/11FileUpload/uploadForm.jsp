<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>11FileUpload/uploadForm.jsp</title>
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

	<h2>파일업로드 폼</h2>
	<form name="fileFrm" method="post" action="uploadAction.do" enctype="multipart/form-data">
		<table class="table table-bordered" style="width:500px;">
			<colgroup>
				<col width="20%"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" />
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="userfile" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
					<button type="submit" class="btn btn-danger">파일업로드</button>
				</td>
			</tr>						
		</table>
	</form>
	
	<div>
		<li>업로드 파일 보기</li>
		<img src="..\resources\upload\d651a434a3db42fc9727ae4fd361ffe8.jpg" alt=""/>
	</div>
</div>
</body>
</html>