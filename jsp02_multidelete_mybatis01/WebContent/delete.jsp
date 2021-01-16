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
	MyDao dao = new MyDao();
	int res = dao.delete(no);
	if(res > 0){
%>
<script type="text/javascript">
	alert("삭제 성공");
	location.href="boardlist.jsp";
</script>
<%
	}else{
%>
<script type="text/javascript">
	alert("삭제 실패");
	location.href="detail.jsp?no=<%=no%>";
</script>
<%
	}
%>

<%@ include file="./form/footer.jsp" %>
</body>
</html>