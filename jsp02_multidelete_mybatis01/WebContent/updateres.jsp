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
<%@ include file="./form/header.jsp" %>
<%
	int no = Integer.parseInt(request.getParameter("no"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	MyDao dao = new MyDao();
	MyDto dto = new MyDto();
	
	dto.setNo(no);
	dto.setTitle(title);
	dto.setContent(content);
	
	int res = dao.update(dto);
	if(res > 0){
%>
<script type="text/javascript">
	alert("수정 성공");
	location.href="detail.jsp?no=<%=no%>";
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert("수정 실패");
	location.href="update.jsp?no=<%=no%>";
</script>
<%
	}
%>


<%@ include file="./form/footer.jsp" %>
</body>
</html>