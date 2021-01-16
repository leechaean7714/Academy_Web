<%@page import="com.login.dto.MBDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function updateRole(myno){
		location.href="logincontroller.jsp?command=updateroleform&myno="+myno;
	}

</script>
</head>
<%
	List<MBDto> list = (List<MBDto>)request.getAttribute("list");
%>
<body>

	<h1>회원정보 조회(Enabled)</h1>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입여부</th>
			<th>등급</th>
			<th>등급변경</th>
		</tr>
<%
	for(MBDto dto : list){
%>
		<tr>
			<td><%=dto.getMyno()%></td>
			<td><%=dto.getMyid()%></td>
			<td><%=dto.getMypw()%></td>
			<td><%=dto.getMyname()%></td>
			<td><%=dto.getMyaddr()%></td>
			<td><%=dto.getMyphone()%></td>
			<td><%=dto.getMyemail()%></td>
			<td><%=dto.getMyenabled().equals("Y")?"가입":"탈퇴"%></td>
			<td><%=dto.getMyrole()%></td>
			<td><button onclick="updateRole(<%=dto.getMyno() %>);">변경</button></td>
		</tr>
<%
	}
%>
		<tr>
			<td colspan="10" align="right">
				<button onclick="location.href='adminmain.jsp'">메인으로</button>
			</td>
		</tr>
	</table>

</body>
</html>