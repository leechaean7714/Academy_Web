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
<body>
<h1>글쓰기</h1>
<form action="mvc.do" method="get">
	<input type="hidden" name="command" value="writeres">
	<table border="1">
		<tr>
			<th>이름</th>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea cols="60" rows="10" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="글쓰기">
				<input type="button" value="취소" onclick="">
			</td>
		</tr>
	</table>
</form>

</body>
</html>