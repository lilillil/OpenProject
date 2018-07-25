<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<title>Insert title here</title>
<style>
</style>
</head>
<body>
<jsp:include page="../commons/header.jsp"/>
<% String sessionID = (String) session.getAttribute("id"); %>
<% if(sessionID == null){  %>
<script>
$(document).ready(function () {
	alert("로그인 하세요");
	location.href="loginForm.jsp";
});
</script>
<%
	}else{
%>


<table border="1">
	<tr>
		<td colspan="2"><div style="width: 200px;"><img src = "${photo}" alt="사진" width="100%"></div></td>
	</tr>
	<tr>
		<td>아이디 </td>
		<td>${id}</td>
	</tr>
	<tr>
		<td>비밀번호 </td>
		<td>${pw}</td>
	</tr>
	<tr>
		<td>이름 </td>
		<td>${name}</td>
	</tr>
	
</table>

<%
				
			

	}
%>
</body>
</html>