<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Open Project</title>
<style>
</style>
</head>
<body>
<jsp:include page="commons/header.jsp"/>

<%

SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmss");
Date now = new Date();
String nowStr = sdFormat.format(now);

%>
<%=nowStr %>

</body>
</html>