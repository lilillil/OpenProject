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

<div id="contents">
<!-- 		<h3>LOGIN</h3>
		<hr> -->
		<c:if test="${loginInfo ne null}">
		<h4>Login Success !</h4>
		<h5>${loginInfo} +1</h5>
		<h5>${id} +2</h5>
		<h5>${pw} +3</h5>
		<h5>${memberId} +4</h5>
		<h5>${memberPassword} +5</h5>
		<h5>${loginRequest.memberId} +6 </h5>
		<h5>${loginRequest.memberPassword} +7</h5>
		
		<a href="<c:url value='/member/mypage'/>">Go to My page</a>
		</c:if>
		<c:if test="${!loginChk}">
		<h4>Login Fail !</h4>
		<a href="<c:url value='/login'/>">Login Retry</a>
		</c:if>

	</div>



</body>
</html>