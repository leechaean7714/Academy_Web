<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
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
List<MVCDto> list = (List<MVCDto>)request.getAttribute("list");
%>
<body>

<h1 align="center">글 목록</h1>


<form action="mvc.do" method="post">
	<input type="hidden" name="command" value="">
	<table border="1" align="center">
		<col width="50">
		<col width="100">

		<col width="300">
		<col width="100">
		<tr>
		
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		<% 
		    if(list.size()==0){
		%>
		<tr>
			<th colspan="5">----------작성된 글이 없습니다.-----------</th>
		</tr>
		
		<%
		}else{
			for(MVCDto dto :list){
		
		%>
		<tr>
		
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
			<td><a href ="mvc.do?command=detail&seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></td>
			<td><%=dto.getRegdate()%></td>
		</tr>
		<%
			}}
		%>
		
		<tr>
			<td colspan="4" align="right">
				
				<input type="button" value="글쓰기" onclick="location.href='mvc.do?command=writeform'">
			</td>
		</tr>
	</table>
</form>



</body>
</html>