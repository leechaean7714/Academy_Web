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
	<h1 align="center">열람하기</h1>
	<table border="1" align="center">
		<tr>
			<td>작성자</td>
			<td><%=dto.getWriter()%></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><%=dto.getTitle()%></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="20" cols="50" readonly="readonly"><%=dto.getContent()%></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="location.href='update.jsp?no=<%=dto.getNo()%>'"/>
				<input type="button" value="삭제" onclick="location.href='delete.jsp?no=<%=dto.getNo()%>'"/>
				<input type="button" value="목록" onclick="location.href='boardlist.jsp'"/>
			</td>
		</tr>
	</table>



<%@ include file="./form/footer.jsp" %>
</body>
</html>