 <%@page import="com.mul.dto.MyDto"%>
<%@page import="com.mul.dao.MyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	MyDto dto = new MyDao().selectOne(Integer.parseInt(request.getParameter("seq")));
%>

<body>
<%@ include file="./form/header.jsp"%>
	<h1>detail</h1>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent() %></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='update.jsp?seq=<%=dto.getSeq()%>'">
				<input type="button" value="삭제" onclick="location.href='delete.jsp?seq=<%=dto.getSeq()%>'">
				<input type="button" value="목록" onclick="location.href='boardlist.jsp'">
			</td>
		</tr>
	</table>



<%@ include file="form/footer.jsp" %>
</body>
</html>