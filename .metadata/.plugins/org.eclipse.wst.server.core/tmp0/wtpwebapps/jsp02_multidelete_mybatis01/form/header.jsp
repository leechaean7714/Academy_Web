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

	header{
		background-color: skyblue;
		height: 50px;
	}
	footer{
		background-color: skyblue;
		height: 30px;
		text-align: center;
		line-height: 30px;
	}
	a{
		text-decoration: none;
	}
	span{
		float: right;
		margin: 5px;
	}
	form > input{
		float: right;
		margin: 5px;
	}
	
	

</style>
</head>
<body>

	<header>
	<form action="login.jsp" method="post">
	<a href="./boardlist.jsp">HOME</a> &nbsp;&nbsp;|&nbsp;&nbsp;
	<a href="./index.jsp">목차</a>
	<input type="button" value="회원가입" onclick="location.href='register.jsp'"/>
	<input type="submit" value="로그인"/>
		<span>비밀번호 : <input type="password" name="id"/></span>
		<span>아이디 : <input type="text" name="pw"/></span>
	</form>
	</header>

</body>
</html>