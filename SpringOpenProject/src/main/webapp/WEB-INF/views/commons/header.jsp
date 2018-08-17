<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Open Project</h1>
<div id = "header">

<ul id="nav">
	<li>
		<a href="<c:url value="/"/>">Index</a>
	</li>
	<li>
		<a href="<c:url value='/member/memberReg'/>">SignUp</a>
	</li>

	<c:if test="${loginInfo eq null}">
    <li>
    	<a href="<c:url value="/login"/>">Login</a>
    </li>
    </c:if>
    <c:if test="${loginInfo ne null}">
    <li>
    	<a href="<c:url value="/member/logout"/>">Logout</a>
    </li>
	</c:if>
    <li>
    	<a href= "<c:url value="/member/mypage"/>">MyPage</a>
    </li>
    <li>
    	<a href="<c:url value="/member/memberList"/>">MemberList</a>
    </li>
    <li>
    	<a href="<c:url value="/guestbook/msgList"/>">GuestBook</a>
    </li>
<!--     <li><a href="empList.jsp">사원리스트</a></li>
    <li><a href="empRegForm.jsp">사원등록</a></li> -->
</ul>

</div>
