<%@page import="com.mul.dao.MyDao"%>
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
		String[] seq = request.getParameterValues("chk");
		if (seq == null || seq.length == 0) {
	%>
	<script type="text/javascript">
		alert("하나 이상 체크박스를 선택하세요.");
		location.href = "boardlist.jsp";
	</script>
	
	<%
		} else {
			MyDao dao = new MyDao();
			int res = dao.multiDelete(seq);
			if (res > 0) {
	%>
	
	<script type="text/javascript">
		alert("삭제성공");
		location.href = "boardlist.jsp"
	</script>

	<%
		} else {
	%>
	<script type="text/javascript">
		alert("삭제 실패");
		location.href = "boardlist.jsp";
	</script>

	<%
		      }
		}
		
	%>
</body>
</html>