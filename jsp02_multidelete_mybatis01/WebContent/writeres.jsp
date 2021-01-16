<%@page import="com.md.dto.MyDto"%>
<%@page import="com.md.dao.MyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	MyDao dao = new MyDao();
	MyDto dto = new MyDto();
	
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	
	int res = dao.insert(dto);
	if(res > 0){
%>
<script type="text/javascript">
	alert("작성 성공");
	location.href="boardlist.jsp";
</script>
<%
	}else{
%>
<script type="text/javascript">
	laert("작성 실패");
	location.href="boardwriteform.jsp";
</script>
<%
	}
%>

</body>
</html>