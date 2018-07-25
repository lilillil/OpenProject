<%@page import="op.service.InvalidMessagePassowrdException"%>
<%@page import="op.service.MessageService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 메시지 삭제 확인</title>
<style>
</style>
</head>
<body>
<!-- p.40 -->
<jsp:include page="../commons/header.jsp"/>
<% if (session.getAttribute("id") == null){  %>


	<form action="deleteMessage.jsp" method="post">
		<%-- value="<% request.getParameter("messageId") -->el식 --%>
		<input type="hidden" name="messageId" value="${param.messageId}" />
		메시지를 삭제하시려면 암호를 입력하세요:<br /> 
		암호: <input type="password" name="password" /> <br />
		<input type="submit" value="메시지 삭제하기" />
	</form>


<%} else { 

		//1. 사용자의 요청 데이터 받기
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		String password = (String)session.getAttribute("pw");
		boolean invalidPassowrd = false;

		// 2. 요청의 처리를 위한 서비스 객체 생성	
		MessageService deleteService = MessageService.getInstance();

		// 3. 삭제 요청
		try {
			deleteService.deleteMessage(messageId, password);
			
		} catch (InvalidMessagePassowrdException ex) {
			invalidPassowrd = true;
		}
		

		if (!invalidPassowrd) {
%>
	메시지를 삭제하였습니다.
	<%
		} else {
	%>
	메시지 삭제에 실패 하였습니다.
	<%
		}

	}

%>


	<br />
	<a href="msgList.jsp">[목록 보기]</a>




</body>
</html>