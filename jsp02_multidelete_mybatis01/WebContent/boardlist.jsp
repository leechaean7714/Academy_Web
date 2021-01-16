<%@page import="java.util.List"%>
<%@page import="com.md.dto.MyDto"%>
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

<script type="text/javascript">

	function boardwrite(){
		location.href="boardwriteform.jsp";
	}
	
	function allChk(check){
		var chks = document.getElementsByName("chk");
		for(var i = 0; i < chks.length; i++){
			chks[i].checked = check;
		}
	}

</script>
</head>
<%
	MyDao dao = new MyDao();
	List<MyDto> list = dao.selectList();
%>
<body>
<%@ include file="./form/header.jsp" %>
<!-- include하면 해당 파일에 있는 모든 태그 및 style과 같은 설정들을 여기로 가져와 포함시킨다. -->
<!-- include된 상태에서 경로로 사용할 파일을 찾을 때에는 include된 파일의 위치를 기준으로 경로를 설정해준다. -->

	<h1 align="center">글 목록</h1>
	<form action="./muldel.jsp" method="post">
		<table border="1" align="center">
				<col width="30"/>
				<col width="50"/>
				<col width="100"/>
				<col width="300"/>
				<col width="100" />
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
<%
			if(list.size() == 0){
%>
			<tr>
				<td colspan="5">---------------작성된 글이 존재하지 않습니다---------------</td>
			</tr>
<%
			} else {  
				for(MyDto dto : list){
%>
			<tr align="center">
				<td align="center"><input type="checkbox" name="chk" value="<%=dto.getNo()%>"/></td>
				<td><%=dto.getNo()%></td>
				<td><%=dto.getWriter()%></td>
				<td><a href="detail.jsp?no=<%=dto.getNo()%>"><%=dto.getTitle()%></a></td>
				<td><%=dto.getRegdate()%></td>
			</tr>
	<%
			}
		}
	%>
			<tr>
				<td colspan="5" align="right">
					<input type="submit" value="선택삭제"/>
					<input type="button" value="글쓰기" onclick="boardwrite()"/>
				</td>
			</tr>
		</table>
	</form>
	
	<%@ include file="./form/footer.jsp" %>

</body>
</html>