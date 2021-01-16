<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<!-- uri="http://java.sun.com/jsp/jstl/core" -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>글목록</h1>
<table border="1">
	<col width="100">
	<col width="300">
	<col width="100">
	<col width="100">
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
	<c:choose>
		<c:when test="${empty list}">
			<tr>
				<td colspan="4">작성된 글이 없습니다,</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list }" var ="dto"><!-- 이 리스트를 dto라는 이름으로 쓰겠습니다. -->
				<tr>
					<td>${dto.boardno }</td>
					<td>
						<c:choose>
							<c:when test="${dto.delflag eq 'Y'}">
								<c:out value="-------삭제된 글입니다.-------"></c:out>
							</c:when>
							<c:otherwise>
								<c:forEach begin="1" end="${dto.titletab }">
									<c:out value="&nbsp;&nbsp;"></c:out>
								</c:forEach>
								<a href="">${dto.title }</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td>${dto.writer }</td>
					<td>${dto.regdate }</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
	
		<td colspan="4" align="right">
			<input type="button" value="글작성" onclick="location.href='answer.do?command=writeform'">
		</td>
		</tr>
</table>

</body>
</html>