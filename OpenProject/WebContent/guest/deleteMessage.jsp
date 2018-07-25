<%@page import="op.service.InvalidMessagePassowrdException"%>
<%@page import="op.service.MessageService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

//1. 사용자의 요청 데이터 받기
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	String password = request.getParameter("password");
	boolean invalidPassowrd = false;

	// 2. 요청의 처리를 위한 서비스 객체 생성	
	MessageService deleteService = MessageService.getInstance();

	// 3. 삭제 요청
	try {
		deleteService.deleteMessage(messageId, password);
		
	} catch (InvalidMessagePassowrdException ex) {
		invalidPassowrd = true;
	}
	
%>
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
	<%
		if (!invalidPassowrd) {
	%>
	메시지를 삭제하였습니다.
	<%
		} else {
	%>
	입력한 암호가 올바르지 않습니다. 암호를 확인해주세요.
	<%
		}
	%>
	<br />
	<a href="msgList.jsp">[목록 보기]</a>



</body>
</html>