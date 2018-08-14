<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PROJECT</title>
<link href="<c:url value='/css/default.css'/>" rel="stylesheet">
<style>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/commons/header.jsp" />
	<div id="contents">
		<h3>회원가입</h3>
		<hr>		
		<c:if test="${insertCnt > 0}">
		<h4>Success !</h4>
		${memberRegRequest}<br>
		${memberRegRequest.memberId} / ${memberRegRequest.memberPassword} / ${memberRegRequest.memberName}
		<a href="<c:url value="/login"/>">Go to Login</a>
		</c:if>
		
		<c:if test="${!(insertCnt > 0)}">
		<h4> Fail !</h4>
		<a href="<c:url value='/member/memberReg'/>">Retry</a>
		</c:if>
		
		
		
<%-- 		<c:choose>
			<c:when test="${insertCnt > 0}">
				<h4>회원가입 성공 !</h4>
				<a href="login">로그인 하러가기</a>
			</c:when>
			<c:otherwise>
				<h4>회원가입 실패 !</h4>
				<a href="memberReg">회원가입 다시 하러가기</a>
			</c:otherwise>
		</c:choose> --%>
		

	</div>
</body>
</html>