<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://code.jquery.com/jquery-1.11.3.js"></script>

<title>Insert title here</title>

<link href="<c:url value="/css/default.css"/>" rel="stylesheet">

<style>
</style>
</head>
<body>

<jsp:include page="/WEB-INF/views/commons/header.jsp"/>

<input type="hidden" id="url"  value="<c:url value="/"/>">

<form method="post" id="regForm" enctype="multipart/form-data">
<table>
	<tr>
		<td>ID :</td>
		<td><input type= "text" name="id" id="id"></td>
		<td><input type="button" value="CHECK ID" id="idChk"></td>
	</tr>
	<tr>
		<td>PASSWORD :</td>
		<td><input type= "text" name="pw" id="pw"></td>
		<td></td>
	</tr>
	
	<tr>
		<td>NAME :</td>
		<td><input type= "text" name="name"></td>
		<td></td>
	</tr>
	
	<tr>
		<td>PHOTO :</td>
		<td><input type="file" name="photoFile"></td>
	</tr>
	
	<tr>
		<td colspan="3" align="left"><input type="button" id="regBtn" value="SUBMIT"></td>
	</tr>

</table>
</form>
<p id="msg"></p>


<script>


var idChk = false;

//아이디 중복 체크
$('#idChk').click(function(){
	
	var contextPath = $('#url').val();
	
	$.ajax({
		   url: contextPath + 'idCheck',
		   type: 'get',
		   data: {'id':$('#id').val()}, //사용자가 입력한 아이디
		   //dataType:'text' ,
		   success: function(data){
			   
			// 결과 텍스트를 경고창으로 보여준다.
		    console.log($.trim(data));
			   if($.trim(data) == "duplicate"){
				   $('#msg').text("중복입니다");
				   idChk = false;
				   console.log(idChk)
			   }else{
				   $('#msg').text("사용 할 수 있는 아이디 입니다");
				   idChk = true;
				   console.log(idChk)
			   }
		   }//success
	});
	
});


//아이디 입력 값 변경시
$('#id').keypress(function() {
	idChk = false;
	console.log(idChk)
});

//제출 버튼
$('#regBtn').click(function(){
	if(idChk == false){
		$('#msg').text("아이디 중복 확인해주세요");
		return false;
	}else if($('#pw').val() == ""){
		$('#msg').text("패스워드를 입력해주세요");
		return false;
	}else{
		$('#regForm').submit();
	}
	
});


</script>

</body>
</html>