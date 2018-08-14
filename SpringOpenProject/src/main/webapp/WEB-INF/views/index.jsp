<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OpenProject</title>
<link href="<c:url value="/css/default.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="/WEB-INF/views/commons/header.jsp"/>

<%

SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
Date now = new Date();
String nowStr = sdFormat.format(now);

%>
<%=nowStr %>


</body>
</html>