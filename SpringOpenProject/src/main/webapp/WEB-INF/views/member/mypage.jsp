<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<title>Insert title here</title>
<link href="<c:url value="/css/default.css"/>" rel="stylesheet">

<style>
td#photo {
	text-align: center;
}

</style>

</head>
<body>

<!-- 절대경로 -->
<jsp:include page="/WEB-INF/views/commons/header.jsp"/>

<table border="1" >
	<tr>
		<%-- <td colspan="2">
			<img src="<c:url value="/uploadFile/memberPhoto/${loginInfo.photo}" />" width="200px">
		</td> --%>
		
		<td colspan="2" id="photo">
		<c:choose>
			<c:when test="${loginInfo.photo eq null}">
				<img src="<c:url value="/uploadFile/memberPhoto/no_avatar.jpg" />" width="200px">
			</c:when>
			<c:otherwise>
				<img src="<c:url value="/uploadFile/memberPhoto/${loginInfo.photo}" />" width="200px">	
			</c:otherwise>
		</c:choose>
		</td>
		
	</tr>
	<tr>
		<td>ID </td>
		<td>${loginInfo.id}</td>
	</tr>
	<tr>
		<td>PW</td>
		<td>${loginInfo.pw}</td>
	</tr>
	<tr>
		<td>NAME</td>
		<td>${loginInfo.name}</td>
	</tr>
	<tr>
		<td>Regist Date</td>
		<td>${loginInfo.regdate}</td>
	</tr>

</table>


</body>
</html>