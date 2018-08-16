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

<c:choose>
	<c:when test="${ guestbook eq null }"> no messages </c:when>
	<c:otherwise>
		<c:forEach items="${guestbook}" var="guestbook" varStatus="status">
			<table border="1">
				<tr>
					<td>${guestbook.message_id }</td>
					<td>${ guestbook.guestname }</td>
					<td>${ guestbook.regdate }</td>
					<td>[Delete]</td>
				</tr>
				<tr>
					<td><img src ="" width="100px" alt="no_img"></td>
					<td>${ guestbook.message }</td>
				</tr>			
			</table>
		</c:forEach>
	</c:otherwise>
</c:choose>
<br>
<a href="<c:url value="/guestbook/msgWrite"/>">[write]</a>

</body>
</html>