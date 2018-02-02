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

function writeValidate(){
   var f = document.writeFrm;
   
   if(f.name.value==""){
      alert("이름을 입력해주세요");
      f.name.focus();
      return false;
   }
   if(f.contents.value==""){
      alert("내용을 입력해주세요");
      f.contents.focus();
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
         <h3>방명록(한줄게시판) - <small>Mybatis로 제작한 방명록입니다.</small></h3>
         

         <!-- 로그인 후 글쓰기 할 수 있도록 JSTL 로직 추가 -->
         <c:choose>
         	<c:when test="${empty sessionScope.siteUserInfo }">
         		<!-- 로그인 정보가 없다면 경고메시지를 띄우고 로그인 페이지로 이동한다. -->
         		<script>
         			alert("로그인 후 방명록을 작성해주세요");
         			location.href="login.do";
         		</script>
         	</c:when>
         	<c:otherwise>
         		<!-- 로그인 상태라면 글쓰기가 가능하다. -->
			<form name="writeFrm" method="post" onsubmit="return writeValidate();" action="<c:url value="/mybatis/writeAction.do" />" >
	            <table class="table table-bordered">
	               <colgroup>
	                  <col width="20%"/>
	                  <col width="*"/>
	               </colgroup>
	               <tbody>
	                  <tr>
	                     <th class="text-center" style="vertical-align:middle;">작성자</th>
	                     <td>
	                        <input type="text" class="form-control" style="width:100px;" name="name" value="${sessionScope.siteUserInfo.name }" />
	                     </td>
	                  </tr>
	                  <tr>
	                     <th class="text-center" style="vertical-align:middle;">내용</th>
	                     <td>
	                        <textarea rows="10" class="form-control" name="contents"></textarea>
	                     </td>
	                  </tr>
	               </tbody>
	            </table>
	            
	            <div class="row text-center" style="">
	               <!-- 각종 버튼 부분 -->
	               <button type="submit" class="btn btn-danger">전송하기</button>
	               <button type="reset" class="btn">Reset</button>
	               <button type="button" class="btn btn-warning" onclick="location.href='list.do';">리스트보기</button>
	            </div>
	        </form>         		
         		
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