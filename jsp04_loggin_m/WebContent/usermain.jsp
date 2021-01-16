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
<style type="text/css">
	
	a{
		text-decoration: none;
		color: black;
	}
	a:visited{
		text-decoration: none;
		color: black;
	}
	a:hover{
		color: red;
	}
	h1 > a{
		float: right;
	}

</style>
</head>
<%
	MBDto dto = (MBDto)session.getAttribute("dto");

	if(dto == null){
	// pageContext.forward("index.jsp");
	response.sendRedirect("index.jsp");
}
%>
<body>

	<h1><%=dto.getMyname() %>님 환영합니다.<a href="logincontroller.jsp?command=logout">logout</a></h1>
	
	<h3>등급 : <%=dto.getMyrole() %></h3>
	
	<div>
		<div>
			<a href="logincontroller.jsp?command=selectuser&myno=<%=dto.getMyno()%>">내 정보 조회</a>
		</div>
		<div>
			<a href="logincontroller.jsp?command=updateuser&myno=<%=dto.getMyno()%>">내 정보 수정</a>
		</div>
		<div>
			<a href="logincontroller.jsp?command=deleteuser&myno=<%=dto.getMyno()%>">회원 탈퇴</a>
		</div>
	</div>
	
	
</body>
</html>