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
	int resultCnt = 0;

	MemberService service = MemberService.getInstance();

	resultCnt = service.idDuplicateChk(member);
%>
<%
				if(resultCnt == 1){
%>
duplicate
<%
				}
%>