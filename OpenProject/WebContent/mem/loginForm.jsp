<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
</style>
</head>
<body>
<jsp:include page="../commons/header.jsp"/>

<form action="login.jsp" method="post" id="loginForm">
<table>
	<tr>
		<td>아이디 :</td>
		<td><input type= "text" name="id" id="id"></td>
	</tr>
	<tr>
		<td>비밀번호 :</td>
		<td><input type= "text" name="pw"></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" id="loginBtn" value="제출"></td>
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