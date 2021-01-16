<%@page import="com.mvc.dto.MVCDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07mvc</title>
</head>

<%
MVCDto dto = (MVCDto)request.getAttribute("update");

%>
<body>
<h1>수정</h1>
<form action="mvc.do" method="post">
	<input type="hidden" name="command" value="updateres">
	<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" value="<%=dto.getTitle()%>" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="content"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정">
				<input type="button" value="취소" onclick="">
			</td>
		</tr>
	</table>
</form>


</body>
</html>