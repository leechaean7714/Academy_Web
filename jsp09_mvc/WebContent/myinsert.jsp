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
<title>09mvc</title>
</head>
<body>


	<h1>글쓰기</h1>
	<form action="con.do" method="post">
		<!--사용자가 @변경해서는 안 되는 데이터@를 함께 보낸다.
데이터베이스의 레코드를 저장하거나, 고유한 보안 토큰 등을 서버로 보낼 때 주로 사용됩니다.-->
		<input type="hidden" name="command" value="writeres">
		<table border="1">
			<tr>
				<th>작성자</th>
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
				<td colspan="2" align="right"><input type="submit" value="글쓰기">
					<input type="button" value="취소" onclick=""></td>
			</tr>


		</table>


	</form>

</body>
</html>