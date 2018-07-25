<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%@ include file="../commons/loginChk.jspf" %> --%>

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

<form action="msgWrite.jsp" method="post">
<%

if( session.getAttribute("id") != null ){
	
%>
	작성자 : ${id}
	<input type="hidden" name="guestName" value="${id}"><br>
	<input type="hidden" name="password" value="${pw}"><br>
<% 
} else{
%>
	작성자 : <input type="text" name="guestName"><br>
	비밀번호 : <input type="text" name="password"><br>
<%	
}
%>
		
		메시지: <textarea name="message" cols="30" row="3"></textarea><br> 
		<input type="submit" value="메시지 남기기" />
</form>




</body>
</html>