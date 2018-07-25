<%@page import="op.model.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="op.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	MemberService service = MemberService.getInstance();

	ArrayList<Member> list = new ArrayList<Member>();
	
	list = service.getMemberList();
	
%>

