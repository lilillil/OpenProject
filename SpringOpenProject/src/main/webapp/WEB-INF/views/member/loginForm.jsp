<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="<c:url value="/css/default.css"/>" rel="stylesheet">

<style>
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/commons/header.jsp"/>

<form action="login" method="post" id="loginForm">
<table>
	<tr>
		<td>ID :</td>
		<td><input type= "text" name="id" id="id"></td>
	</tr>
	<tr>
		<td>PW :</td>
		<td><input type= "text" name="pw"></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" id="loginBtn" value="SUBMIT"></td>
	</tr>

</table>
</form>
<p id="msg"></p>

<% 
String msg = (String)request.getAttribute("msg");
if(msg != null){
%>
<p id="msg"><%=msg %></p>
<%
}


%>



</body>
</html>