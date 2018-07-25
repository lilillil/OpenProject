<%@page import="op.service.MessageService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//1. 사용자 입력 데이터 받기
	//2. 요구사항을 처리할 서비스 객체 생성(받아오는것)
	//3. 처리요청 (service.write 실행)
	//4. 결과 받아서 사용자에게 보여줄 응답 view 작성
	
	request.setCharacterEncoding("utf-8");

%>

<jsp:useBean id="message" class="op.model.Message"/>
<jsp:setProperty property="*" name="message"/>
<%

	int resultCnt = 0;

	MessageService service = MessageService.getInstance();

	resultCnt = service.write(message);

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GuestBook</title>
<style>
</style>
</head>
<body>
<!-- p.39  -->
<jsp:include page="../commons/header.jsp"/>
<% if(resultCnt >0 ){ %>
		방명록에 메시지를 남겼습니다.<br><a href="msgList.jsp">[목록 보기]</a>
<%}else{ %>
		메시지가 정상적으로 작성되지 않았습니다.<br><a href="msgList.jsp">[목록 보기]</a>
<%} %>


</body>
</html>