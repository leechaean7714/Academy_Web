<%@page import="com.mul.dto.MyDto"%>
<%@page import="java.util.List"%>
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
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js">
	$(function() {
		$("#muldelform").submit(function() {
			if ($("#muldelform input:checked").length == 0) {
				alert("하나이상을 선택해주세요");
				return false;//0개가 아닌 체크된 것을 리턴
			}
		})
	})
</script>

<script type="text/javascript">
	function writeForm() {
		location.href = "boardwriteform.jsp";

	}

	function allChk(bool) {
		var chks = document.getElementsByName("chk");

		for (var i = 0; i < chks.length; i++) {
			chks[i].checked = bool;//모두 선택 모두 해제
		}
	}
</script>
</head>

<%
	MyDao dao = new MyDao();
	List<MyDto> list = dao.selectList();
%>
<body>
	<%@ include file="./form/header.jsp"%>

	<h1>list</h1>
	<form action="muldel.jsp" method="post" id="muldelform">
		<table border="1">
			<col width="30">
			<col width="50">
			<col width="200">
			<col width="300">
			<col width="100">
			<tr>
				<th><input type="checkbox" name="all"
					onclick="allChk(this.checked);"></th>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			<%
				if (list.size() == 0) {
			%>
			<tr>
			<tr>
				<td colspan="5">----------------작성된 글이 없습니다.----------------</td>
			</tr>
			<%
				} else {
					for (MyDto dto : list) {
			%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>"></td>
				<td><%=dto.getSeq()%></td>
				<td><%=dto.getWriter()%></td>
				<td><a href="detail.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
				<td><%=dto.getRegdate()%></td>
			</tr>
			<%
				}
				}
			%>
			<tr>
				<td colspan="5"><input type="submit" value="선택 삭제"> <input
					type="button" value="글쓰기" onclick="writeForm();"></td>
			</tr>
		</table>
	</form>
	<%@ include file="form/footer.jsp"%>

</body>
</html>