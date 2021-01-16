<%
	// 브라우저가 캐시에 화면(응답된 도큐먼트) 저장하지 않도록 설정
	response.setHeader("Pragma", "no-cache");				// http 1.0
	response.setHeader("Cache-control", "no-store");		// http 1.1
	response.setHeader("Expires", "0");						// proxy server
	// 회원명단에서 command=login으로 갈 때 세션이 캐시 영역에 자동으로 저장해두기 때문에 에러가 나지않고 이동할 수 있다.
	// 하지만 우리는 그렇게 이동하지 않게 하기 위해 캐시를 지워주고 바로 adminmain.jsp로 이동시켰다.
%>

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
	a:hover{
		color: red;
	}
	a:visited{
		text-decoration: none;
		color: black;
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

	<h1><%=dto.getMyname() %>님, 환영합니다.<a href="logincontroller.jsp?command=logout">logout</a></h1>
	<h3>등급 : <%=dto.getMyrole() %></h3>
	
	<div>
		<div>
			<a href="logincontroller.jsp?command=selectlist">회원정보 조회(ALL)</a>
		</div>
		<div>
			<a href="logincontroller.jsp?command=selectenabled">회원정보 조회(Enabled)</a>
		</div>
	</div>

</body>
</html>