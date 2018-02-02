<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">		
		<!-- 좌상단 로고 영역 -->
		<div class="navbar-header">
			<a href="#" class="navbar-brand">JAVA31기</a>
		</div>
		
		<!-- 메뉴부분 -->
		<ul class="nav navbar-nav">
			<li class="active"><a href="../">홈으로</a></li>
			
			<li><a href="../board/list.do">게시판[JdbcTemplate]</a></li>			
			<li><a href="../mybatis/list.do">방명록[Mybatis]</a></li>		
		</ul>
		 
		<!-- 검색폼 -->
		<form class="navbar-form navbar-left">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="검색" />
				<div class="input-group-btn">
					<button type="submit" class="btn btn-default">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</form>
		
			
		<!-- 우측메뉴 -->
		<ul class="nav navbar-nav navbar-right">
<% if(session.getAttribute("siteUserInfo")==null){ %>
			<!-- 로그인전 -->
			<li><a href="javascript:alert('준비중');">
				<span class="glyphicon glyphicon-user"></span>회원가입
				</a>
			</li>
			<li><a href="../mybatis/login.do">
				<span class="glyphicon glyphicon-log-in"></span>로그인
				</a>
			</li>
<% }else{ %>			
			<!-- 로그인후 -->
			<li><a href="javascript:alert('준비중');">
				<span class="glyphicon glyphicon-cog"></span>회원정보수정
				</a>
			</li>
			<li><a href="../mybatis/logout.do">
				<span class="glyphicon glyphicon-log-out"></span>로그아웃
				</a>
			</li>
<% } %>			
		</ul>		
	</div>
</nav>