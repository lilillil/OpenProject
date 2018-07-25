<%@page import="op.model.Message"%>
<%@page import="op.model.MessageListView"%>
<%@page import="op.service.GetMessageListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String pageNumStr = request.getParameter("page");
	int pageNumber = 1;
	if (pageNumStr != null) {
		pageNumber = Integer.parseInt(pageNumStr);
	}

	GetMessageListService messageListService = GetMessageListService.getInstance();

	MessageListView viewData = messageListService.getMessageList(pageNumber);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GuestBook</title>
<style>
</style>
</head>
<body>
<jsp:include page="../commons/header.jsp"/>
	
	<%
		if (viewData.isEmpty()) {
	%>
			등록된 메시지가 없습니다.
	<%
		} else { /* 메시지 있는 경우 처리 시작 */
	%>
	
	<table border="1">
		<%
			for (Message message : viewData.getMessageList()) {
		%>
		<tr>
			<td>
				메시지 번호 : <%=message.getId()%> <br> 
				작성자 : <%=message.getGuestName()%><br>
				메시지: <%=message.getMessage()%> <br> 
				
				<%
					String sessionId = (String)session.getAttribute("id"); 
					String writer = message.getGuestName();
							
					if(sessionId !=null && sessionId.equals(writer)){
						
				%>
				
						<a href="confirmDeletion.jsp?messageId=<%=message.getId()%>">[삭제하기]</a>
				<%
				
					} else if (sessionId ==null ) {
				%>
					<a href="confirmDeletion.jsp?messageId=<%=message.getId()%>">[삭제하기]</a>
					
				<%
					}
				%>	
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		for (int i = 1; i <= viewData.getPageTotalCount(); i++) {
	%>
			<a href="msglist.jsp?page=<%=i%>">[<%=i%>]</a>
	<%
		}
	%>
	<%
	} /* 메시지 있는 경우 처리 끝 */
	%>
	
	<p><a href="./msgWriteForm.jsp">방명록 작성</a></p>
	

</body>
</html>