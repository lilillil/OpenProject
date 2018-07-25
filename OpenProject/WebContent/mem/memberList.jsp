<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../commons/loginChk.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<title>Insert title here</title>
</head>
<body>

<jsp:include page="../commons/header.jsp"/>

<a href="javascript:void(0);" id="name">Xml</a><br>
<a href="javascript:void(0);" id="json">Json</a>

<script>

$(document).ready(function(){
	
	$('#json').click(function(){
	
		$.getJSON('jsondata.jsp', function(data){	
			
			alert(data);
			
			$.each(data, function(key, value){
				var str = '<p>' + value.id + ' : ' + value.name + '</p>'
				$('body').append(str);
			});
		});
		
		
	});
	
	
	
});



</script>
</body>
</html>