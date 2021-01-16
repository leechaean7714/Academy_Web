<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>글 목록</h1>
	<table border="1">
		<col width="100"/>
		<col width="300"/>
		<col width="100"/>
		<col width="100"/>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<td colspan="4">-----작성된 글이 존재하지 않습니다------</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<c:choose>
						<c:when test="">
								<tr>
									<td>${dto.boardno }</td>
									
									<td>${dto.writer }</td>
									<td>${dto.regdate }</td>
								</tr>
						</c:when>
						<c:otherwise>
								<tr>
									<td>${dto.boardno }</td>
									<c:choose>
										<c:when test="${dto.flagdel eq 'Y' }">
											<td>
												<c:forEach begin="1" end="${dto.titletab }">
													&nbsp;&nbsp;
												</c:forEach>
												<c:out value="삭제된 글입니다."></c:out>
											</td>
										</c:when>									
										<c:otherwise>
											<td>
												<c:forEach begin="1" end="${dto.titletab }">
													&nbsp;&nbsp;
												</c:forEach>
												<a href="answer.do?command=detail&boardno=${dto.boardno }">${dto.title }</a>
											</td>
										</c:otherwise>
									</c:choose>
									<td>${dto.writer }</td>
									<td>${dto.regdate }</td>
								    <td>${dto.hitno }</td>
								</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="5">
				<input type="button" value="글작성" onclick="location.href='answer.do?command=writeform'" />
			</td>
		</tr>
	</table>

</body>
</html>










