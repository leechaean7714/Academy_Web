<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글보기</h1>

<table border="1">
<!-- 받기만 하고 뿌려줄꺼라서 update 와 다르게 input태그가 없다. -->
<tr>
	<th>작성자</th>
	<td>${dto.writer}</td>
</tr>
<tr>
	<th>제목</th>
	<td>${dto.title }</td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea rows="10" cols="60" readonly="readonly">${dto.content}</textarea></td>
</tr>
<tr>
	<td colspan="2" align="right">
		<input type="button" value="수정" onclick="location.href='myupdate?seq=${dto.seq}'">
		<input type="button" value="삭제" onclick="location.href='mydelete?seq=${dto.seq}'">
		<input type="button" value="목록" onclick="location.href='mylist'">
	</td>
</tr>

</table>


</body>
</html>