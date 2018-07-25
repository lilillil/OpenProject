<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%@page import="op.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 사용자 입력 데이터 받기
	//2. 요구사항을 처리할 서비스 객체 생성(받아오는것)
	//3. 처리요청 (service.write 실행)
	//4. 결과 받아서 사용자에게 보여줄 응답 view 작성
	
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="member" class="op.model.Member"/>
<jsp:setProperty property="*" name="member"/>

<%
	String imgName = "";

boolean isMultipart = ServletFileUpload.isMultipartContent(request);
if (isMultipart) {
	
	// 2. 메모리나 파일로 업로드 파일 보관하는 FileItem의 Factory 설정
	DiskFileItemFactory factory = new DiskFileItemFactory();
	// 3. 업로드 요청을 처리하는 ServletFileUpload 생성
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	
	//4. 업로드 요청 파싱해서 FileItem 목록 구함
	List<FileItem> items = upload.parseRequest(request);
	Iterator<FileItem> iter = items.iterator();
	
	
	while(iter.hasNext()){
		FileItem item = iter.next();
		
		//파일 인지 여부 확인 : isFormField()->type=file 이외의 폼 데이터인지 확인 
		if(item.isFormField()){
			String name = item.getFieldName();
			String value = item.getString("utf-8");
			System.out.println("일반 폼 필드 :" + name + " - " + value);
			
			if(name.equals("id")){
				member.setId(value);
			}
			if(name.equals("pw")){
				member.setPw(value);
			}
			if(name.equals("name")){
				member.setName(value);
			}
			
		} else { //파일 업로드인 경우
			String name = item.getFieldName();
			String fileName = item.getName();
			String contentType = item.getContentType();
			boolean isInMemory = item.isInMemory();
			long sizeInBytes = item.getSize();
			
			System.out.println("파일 폼 필드 :" + name + " - " + fileName);
			
			//저장하고자 하는 파일의 이름
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			Date now = new Date();
			String nowStr = sdFormat.format(now);
			imgName = nowStr+"_" + fileName;
			
			// 웹서비스에서 사용되는 저장 경로
			String uploadUri = "/file/photo";
			
			//물리적인 경로
			String dir = request.getSession().getServletContext().getRealPath(uploadUri);
			
			System.out.println( uploadUri + " 물리적 경로 " + dir);
			
			//웹서비스에서 사용되는 저장경로 저장
			member.setPhoto(uploadUri + "/" + imgName);
			
			//데이터 저장
			item.write(new File(dir, imgName));
			
		}
	}
	
}








	int resultCnt = 0;
	MemberService service = MemberService.getInstance();
	resultCnt = service.register(member);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
</style>
</head>
<body>

<jsp:include page="../commons/header.jsp"/>

<% if(resultCnt >0 ){ %>
		회원 가입이 완료 되었습니다.<br>
<%}else{ %>
		회원 가입이 정상적으로 완료되지 않았습니다.<br></a>
<%} %>
</body>
</html>