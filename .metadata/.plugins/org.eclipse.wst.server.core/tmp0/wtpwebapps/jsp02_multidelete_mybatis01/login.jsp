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
<%-- <%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	MyDao dao = new MyDao();
	MyDto dto = dao.login(id);
	
	if(dto.getId().equals(id) && dto.getPw().equals(pw)){
%>

<%
	}
%> --%>

</body>
</html>