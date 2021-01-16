<%@page import="com.login.dto.MBDto"%>
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
<%
	MBDto dto = (MBDto)request.getAttribute("select");
%>

	<input type="hidden" name="myno" value="<%=dto.getMyno() %>"/>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><%=dto.getMyid() %></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><%=dto.getMypw() %></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><%=dto.getMyaddr() %></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><%=dto.getMyphone() %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=dto.getMyemail() %></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정하기" onclick="location.href=logincontroller.jsp?command=updateuser&myno=<%=dto.getMyno()%>"/>
				<input type="button" value="메인으로" onclick="location.href='usermain.jsp'"/>
			</td>
		</tr>
		
	</table>


</body>
</html>