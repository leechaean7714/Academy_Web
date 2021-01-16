<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usebean</title>
</head>
<body>

<jsp:useBean id="lee" class="com.test.dto.Score" scope="session"/>
<!-- Bean의 의미 : Score lee = new Score(); -->

<jsp:setProperty property="name" name="lee" value="이순신"/>
<!-- lee.setName("이순신"); -->

<jsp:getProperty property="name" name="lee"/>
<!-- lee.getName(); -->
<br>
<h1>
${lee.name }
</h1>
<a href="res.jsp">result</a>
</body>
</html>