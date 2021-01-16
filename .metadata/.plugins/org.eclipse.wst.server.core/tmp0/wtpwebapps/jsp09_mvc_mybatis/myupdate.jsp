<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09mvc</title>
</head>

<body>


<%-- 간단한 방법:${     }--%>
<jsp:useBean id="update" class="com.mvc.dto.MyDto" scope="request"></jsp:useBean>
<h1>수정</h1>

<form action="con.do" method="post">
	<input type="hidden" name="command" value="updateres">
	<input type="hidden" name="seq" value="<jsp:getProperty property="seq" name="update"/>">
		<table border="1">
		
		<tr>
			<th>작성자</th>
			<td><jsp:getProperty property="writer" name="update" /></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" value="<jsp:getProperty property="title" name="update"/>" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60"name="content" >
			<jsp:getProperty property="content" name="update"/></textarea></td>
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