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


<form method="post">

	<table border="1">
		<c:choose>
			<c:when test="${loginInfo.id ne null}">
				<tr>
					<td> Writer : </td>
					<td> 
						<input type="text" name="guestName" value="${loginInfo.id}" disabled="disabled">
						<input type="hidden" name="password" value="${loginInfo.pw}">
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td> Writer : </td>
					<td>
						<input type="text" name="guestName">
					</td>
				</tr>
				<tr>
					<td> Password : </td>
					<td>
						<input type="text" name="password">
					</td>
				</tr>
			</c:otherwise>
		</c:choose> 
		
		<tr>
			<td colspan="2">
				Message : 
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea name="message" cols="30" rows="10"></textarea> 
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="POST" />
			</td>
		</tr>
	
	</table>



	
	
		
		
		 
</form>






<a href="<c:url value="/guestbook/msgList"/>">[List]</a>

</body>
</html>