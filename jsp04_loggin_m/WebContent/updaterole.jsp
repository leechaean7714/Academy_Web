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

	<h1>회원등급 변경</h1>
	
	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="updateroleres"/>
		<input type="hidden" name="myno" value="<%=dto.getMyno()%>"/>
		
		<table border="1">
			<tr>
				<th>번호</th>
				<td><%=dto.getMyno() %></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=dto.getMyname() %></td>
			</tr>
			<tr>
				<th>등급</th>
				<td>
					<select name="role">
						<option value="USER" <%=dto.getMyrole().equals("USER")?"selected":"" %>>일반회원</option>
						<option value="MANAGER" <%=dto.getMyrole().equals("MANAGER")?"selected":"" %>>우수회원</option>
						<option value="ADMIN" <%=dto.getMyrole().equals("ADMIN")?"selected":"" %>>관리자</option>
						<!-- select -> option 태그에 selected라는 속성을 주면 처음 접속했을 때 해당 속성이 있는 태그가 선택되어 나온다. -->
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="변경"/>
					<input type="button" value="취소" onclick=""/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>