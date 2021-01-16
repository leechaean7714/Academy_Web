<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> <!-- 사용자의 값만 받아서 넘긴다 -->
</head>
<body>

	<!-- //글쓰는 폼을 만든다. -->

	<h1>글쓰기</h1>
	<form action="myinsertres.jsp" method="post"><!-- myinsertres.jsp에 값을 넘겨줌 -->
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="myname"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="6" cols="60" name="mycontent"></textarea></td>

			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="작성"> 
				<input type="button" value="취소" onclick="">
				</td>
			</tr>

		</table>

	</form>



</body>
</html>