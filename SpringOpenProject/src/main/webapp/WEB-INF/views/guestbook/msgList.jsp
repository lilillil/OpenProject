<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/css/default.css"/>" rel="stylesheet">

<style>

table{
	width : 500px;
	table-layout:fixed;
}
td#img{
	width : 100px;
	height : 100px;
}
td#msg {
    vertical-align: top;
    text-align: left;
    word-break:break-all;
}
td#msg_id{
	width : 30px;
}
td#guestname{
	width : 70px;
}
td#delete{
	width : 50px;
}
td.center {
	text-align: center;
}

</style>

</head>
<body>
<jsp:include page="/WEB-INF/views/commons/header.jsp"/>

<c:choose>
	<c:when test="${ guestbook eq null }"> no messages </c:when>
	<c:otherwise>
		<c:forEach items="${guestbook}" var="guestbook" varStatus="status">
			<table border="1">
				<tr>
					<td id="msg_id" class="center">${guestbook.message_id }</td>
					<td id="guestname">${ guestbook.guestname }</td>
					<td>
						<fmt:formatDate value="${guestbook.regdate}" pattern="yyyy.MM.dd HH:mm:ss" />
					</td>
					<td id="delete" class="center">Delete</td>
				</tr>
				<tr>
					<td colspan="2" id="img"> <img src ="" width="100px"> </td>
					<td colspan="2" id="msg">${ guestbook.message }</td>
				</tr>			
			</table>
		</c:forEach>
	</c:otherwise>
</c:choose>


	<c:if test="${paging.startPage!=1 && paging.startPage%5==1}">
		<a href="<c:url value="/guestbook/msgList"/>?page=${paging.startPage-1}">◀</a>
	</c:if>
	<c:forEach var="page" begin="${paging.startPage}" end="${paging.endPage}">
		<a href="<c:url value="/guestbook/msgList"/>?page=${page}">[${page}]</a>
	</c:forEach>
	<c:if test="${paging.endPage!=paging.totalPageCnt && paging.endPage%5 ==0 }">
		<a href="<c:url value="/guestbook/msgList"/>?page=${paging.endPage+1}">▶</a>
	</c:if>




<br>
<a href="<c:url value="/guestbook/msgWrite"/>">[write]</a>

</body>
</html>