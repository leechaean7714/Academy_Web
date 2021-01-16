<%@page import="com.md.dao.MyDao"%>
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
	// muldel.jsp?chk=1&chk=2&chk=5 와 같은 형태로 데이터가 넘어옴. 이름이 모두 같기 때문에 getParameter로 받을 수 없다.
	// getParameterValues = 같은 이름의 여러 값을 받을 때 사용
	String[] seq = request.getParameterValues("chk");

	if(seq == null || seq.length == 0){
%>
	<script type="text/javascript">
		alert("삭제할 글을 1개 이상 선택해 주세요");
		location.href="./boardlist.jsp";
	</script>
<%
	} else {
		MyDao dao = new MyDao();
		int res = dao.multiDelete(seq);
		if(res > 0){
%>
<script type="text/javascript">
	alert("선택한 글들을 삭제 성공하였습니다.");
	location.href="boardlist.jsp";
</script>
	
<%
	} else {
%>
<script type="text/javascript">
	alert("선택한 글들을 삭제 실패하셨습니다.");
	location.href="boardlist.jsp";
</script>
<%
		}
	}
%>

</body>
</html>