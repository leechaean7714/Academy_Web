<%@page import="com.my.dao.MyDao"%>
<%@page import="com.my.dto.MyDto"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	int myno = Integer.parseInt(request.getParameter("myno"));
	MyDao dao = new MyDao();
	MyDto dto = dao.selectOne(myno);

%>

	<h1 align="center">수정</h1>

	<form action="myupdateres.jsp" method = "post">
		<!--사용자가 @변경해서는 안 되는 데이터@를 함께 보낸다.-->
		<input type = "hidden" name = "myno" value ="<%=myno%>"/>
		<table border="1" align="center">
			<tr>
				<th>이름</th>
				<td><%=dto.getMyname() %></td>
			</tr>

			<tr>
				<th>제목</th>
				<td><input type = "text" value="<%=dto.getMytitle() %>" name = "mytitle"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="6" cols="60" name = "mycontent" ><%=dto.getMycontent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type = "submit" value = "수정"/>
					<input type = "button" value = "취소" onclick="location.href='mydetail.jsp?myno=<%=dto.getMyno()%>'"/>
				</td>
			</tr>
		
		</table>	
		
	</form>



