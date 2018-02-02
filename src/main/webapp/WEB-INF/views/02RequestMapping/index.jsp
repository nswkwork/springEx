<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>02RequestMapping/index.jsp</title>
<link href="../common/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

});

function searchCheck(frm){
	if(!frm.searchWord.value){
		alert("검색어를 입력하세요");
		frm.searchWord.focus();
		return false;
	}
}

function loginCheck(frm){
	if(!frm.user_id.value){
		alert("아이디를 입력하세요");
		frm.user_id.focus();
		return false;
	}
	if(!frm.user_pw.value){
		alert("패스워드를 입력하세요");
		frm.user_pw.focus();
		return false;
	}
}
</script>
</head>
<body>
<div class="container">
	
	<h2>@RequestMapping 어노테이션</h2>
	
	<h3>GET방식으로 전송 - 검색폼</h3>
	<form action="../requestMapping/getSearch" method="get"
		name="searchFrm" onsubmit="return searchCheck(this);">
	
		<select name="searchColumn">
			<option value="title">제목</option>
			<option value="name">작성자</option>
			<option value="content">내용</option>
		</select>
		
		<input type="text" name="searchWord" />
		
		<input type="submit" value="검색하기" />		
	</form>
	
	
	<h3>POST방식으로 전송 - 로그인폼</h3>
	<%
	/*
	JSP에서 컨텍스트루트(패스) 경로를 얻어옴. 컨텍스트루프 경로를 
	사용하면 요청명의 depth(깊이)가 추가되더라도 경로에 대한 추가적인
	수정이 필요없기때문에 편리하다. 실무에서 많이 쓰이는 방식이다.
	*/
	String ctxPath = request.getContextPath();
	%>
	<form action="<%=ctxPath%>/requestMapping/postLogin" method="post"
		name="loginFrm" onsubmit="return loginCheck(this);">
	<table class="table table-bordered" style="width:400px;">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="user_id" /></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="user_pw" /></td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
				<button type="submit">로그인</button>
			</td>
		</tr>
	</table>	
	</form>
	
	
	<h3>@ModelAttribute 어노테이션 사용하여 커맨드객체 이름 변경하기</h3>
	<form action="<%=ctxPath%>/requestMapping/modelAttribute"
		method="post" name="studentFrm" onsubmit="return stuCheck();">
	<table class="table table-bordered" style="width:400px;">
		<tr>
			<td>이름</td>
			<td>
				<input type="text" name="name" />
			</td>
		</tr>
		<tr>
			<td>나이</td>
			<td>
				<input type="text" name="age" />
			</td>
		</tr>
		<tr>
			<td>학년</td>
			<td>
				<select name="gradeNum">
					<option value="1학년">1학년</option>
					<option value="2학년">2학년</option>
					<option value="3학년">3학년</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>학반</td>
			<td>
				<input type="radio" name="classNum" value="1반" />1반
				<input type="radio" name="classNum" value="2반" />2반
				<input type="radio" name="classNum" value="3반" />3반
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
				<button type="submit">전송하기</button>
			</td>
		</tr>
	</table>
	
	</form>
	
	
	
	
	
	
</div>
</body>
</html>







