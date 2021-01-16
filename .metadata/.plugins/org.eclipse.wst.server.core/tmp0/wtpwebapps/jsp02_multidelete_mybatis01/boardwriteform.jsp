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

<%@ include file = "./form/header.jsp" %>

	<h1 align="center">글 쓰기</h1>
	
<form action="writeres.jsp" method='post'>
	<table border="1" align="center">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"/></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="20" cols="50" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type='submit' value="작성"/>
				<input type="button" value="취소" onclick="location.href='boardlist.jsp'"/>
			</td>
		</tr>
	
	
	</table>
</form>



<%@ include file ="./form/footer.jsp" %>
</body>
</html>