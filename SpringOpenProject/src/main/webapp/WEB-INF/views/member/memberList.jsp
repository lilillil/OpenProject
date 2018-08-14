<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/css/default.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="/WEB-INF/views/commons/header.jsp"/>

<table border="1">
<tr>
	<td>MIDX</td>
	<td>ID</td>
	<td>PW</td>
	<td>PHOTO</td>
	<td>NAME</td>
	<td>REGDATE</td>
</tr>
<c:forEach items="${memberList}" var="member" varStatus="status">
<tr>
	<td>${member.midx}</td>
	<td>${member.id}</td>
	<td>${member.pw}</td>
	<td>
		<c:choose>
			<c:when test="${member.photo eq null}">
				a<img src="<c:url value="/uploadFile/memberPhoto/no_avatar.jpg" />" width="100px">
			</c:when>
			<c:otherwise>
				b<img src="<c:url value="/uploadFile/memberPhoto/${member.photo}" />" width="100px">	
			</c:otherwise>
		</c:choose>
	</td>
	<td>${member.name}</td>
	<td>${member.regdate}</td>
</tr>
</c:forEach>
</table>

	<c:if test="${startPage!=1 && startPage%5==1}">
		<a href="<c:url value="/member/memberList"/>?page=${startPage-1}">◀</a>
	</c:if>
	<c:forEach var="page" begin="${startPage}" end="${endPage}">
		<a href="<c:url value="/member/memberList"/>?page=${page}">[${page}]</a>
	</c:forEach>
	<c:if test="${endPage!=pageCnt && endPage%5 ==0 }">
		<a href="<c:url value="/member/memberList"/>?page=${endPage+1}">▶</a>
	</c:if>



</body>
</html>