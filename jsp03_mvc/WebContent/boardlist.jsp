<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
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
<script type="text/javascript">
	function allChk(bool) {
		let chks = document.getElementsByName("chk");
		for (let i = 0; i < chks.length; i++) {
			chks[i].checked = bool;
			
		}
	}
</script>
</head>
<%
	List<MVCDto> list = (List<MVCDto>) request.getAttribute("list");
%>
<body>

	<h1 align="center">글목록</h1>
	<form action="controller.jsp" method="post">
		<input type="hidden" name="command" value="muldel">
		<table border="1" align="center">
			<col width="30">
			<col width="50">
			<col width="100">
			<col width="300">
			<col width="100">
			<tr>
				<th><input type="checkbox" name="all"
					onclick="allChk(this.checked)"></th>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>

			<%
				if (list.size() == 0) {
			%>
			<tr>
				<th colspan="5">----------작성된 글이 없습니다.----------</th>

			</tr>
			<%
				} else {
					for (MVCDto dto : list) {
			%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>"></td>
				<td><%=dto.getSeq()%></td>
				<td><%=dto.getWriter()%></td>
				<td><a
					href="controller.jsp?command=detail&seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
				<td><%=dto.getRegdate()%></td>



			</tr>
			<%
				}
				}
			%>
			<tr>
				<td colspan="5" align="right"><input type="submit" value="선택삭제">
					<input type="button" value="글쓰기"
					onclick="location.href='controller.jsp?command=writeform'">
				</td>
			</tr>

		</table>
	</form>

</body>
</html>