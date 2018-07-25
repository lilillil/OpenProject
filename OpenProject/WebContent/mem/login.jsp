<%@page import="op.model.Member"%>
<%@page import="op.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 사용자 입력 데이터 받기
	//2. 요구사항을 처리할 서비스 객체 생성(받아오는것)
	//3. 처리요청 (service.write 실행)
	//4. 결과 받아서 사용자에게 보여줄 응답 view 작성
	
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="member" class="op.model.Member"/>
<jsp:setProperty property="*" name="member"/>

<%
	
	MemberService service = MemberService.getInstance();

	Member mem = service.login(member);
%>

<%
	if(mem != null){
		//세션
		session.setAttribute("id", mem.getId());
		session.setAttribute("pw", mem.getPw());
		session.setAttribute("name", mem.getName());
		session.setAttribute("photo", mem.getPhoto());
		response.sendRedirect("../index.jsp");
	}else{
		request.setAttribute("msg", "id또는 비밀번호가 틀립니다.");
		
		RequestDispatcher reqDis = request.getRequestDispatcher("./loginForm.jsp");
		reqDis.forward(request, response);
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


</body>
</html>