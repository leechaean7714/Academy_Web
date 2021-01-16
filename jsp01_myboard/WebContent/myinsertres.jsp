
<%@page import="com.my.dto.MyDto"%>
<%@page import="com.my.dao.MyDao"%>

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
String myname = request.getParameter("myname");
String mytitle =request.getParameter("mytitle");
String mycontent=request.getParameter("mycontent");

 MyDto dto = new MyDto();

dto.setMyname(myname);
dto.setMytitle(mytitle);
dto.setMycontent(mycontent);

MyDao dao = new MyDao();// insert에서 넣어준 값을 db랑 연결해서 입력해가지고 입력한 결과를 리턴해준다

int res = dao.insert(dto);//dao가 연결해준다.6

if(res>0){


%>
<script type="text/javascript">

alert("글 작성 성공");
location.href = "mylist.jsp";
</script>
<%}else{ %>

<script type="text/javascript">

alert("글 작성 실패");
location.href="myinsert.jsp";
</script>

<%} %>


</body>
</html>