<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String sessionID = (String) session.getAttribute("id"); %>
<style>
ul li{
     display: inline-block;
}

</style>


<h1>Open Project</h1>
<div id = "header">

<ul id="nav">
	<li><a href="/index.jsp">Index</a></li>
	<li><a href="/mem/memRegForm.jsp">회원가입</a></li>
<% if(sessionID == null){  %>
    <li><a href="/mem/loginForm.jsp">로그인</a></li>
<%}else { %>        
    <li><a href="/mem/logout.jsp">로그아웃</a></li>
<% } %>
    <li><a href="/mem/mypage.jsp">마이페이지</a></li>
    <li><a href="/mem/memberList.jsp">회원리스트</a></li>
<!--     <li><a href="empList.jsp">사원리스트</a></li>
    <li><a href="empRegForm.jsp">사원등록</a></li> -->
    <li><a href="/guest/msgList.jsp">방명록</a></li>
</ul>

</div>