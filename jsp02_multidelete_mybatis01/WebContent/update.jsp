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
	MyDao dao = new MyDao();
	MyDto dto = dao.selectOne(no);
%>
	<h1 align="center">수정하기</h1>
<form action="updateres.jsp" method="post">
	<input type="text" name="no" value="<%=dto.getNo() %>" hidden="hidden"/>
	<table border="1" align="center">
		<tr>
			<td>작성자</td>
			<td><%=dto.getWriter()%></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="<%=dto.getTitle()%>"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea cols="50" rows="20" name="content"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정"/>
				<input type="button" value="취소" onclick="location.href='detail.jsp?no=<%=no%>'"/>
			</td>
		</tr>
	</table>
</form>



<%@ include file="./form/footer.jsp" %>

</body>
</html>