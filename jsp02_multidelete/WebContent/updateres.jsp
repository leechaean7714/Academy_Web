<%@page import="com.mul.dao.MyDao"%>
<%@page import="com.mul.dto.MyDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	int seq = Integer.parseInt(request.getParameter("seq"));
String title = request.getParameter("title");
String content = request.getParameter("content");
MyDao dao = new MyDao();
MyDto dto = new MyDto(seq, null, title, content,null);
int res = dao.update(dto);
if(res>0){
%>
<script type="text/javascript">
alert("수정 성공");
location.href="detail.jsp?seq=<%=dto.getSeq()%>";

</script>


<%
}else{
%>
<script type="text/javascript">
alert("수정 실패");
history.back();
</script>


<%}
%>



</body>
</html>