<%@page import="com.my.dto.MyDto"%>
<%@page import="java.util.List"%>
<%@page import="com.my.dao.MyDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %> <!-- 내가 요청 할 떄 저형식으로 요청 할꺼야 -->
<% response.setContentType("text/html;charset=UTF-8"); %> <!-- 내가 응답받는 문서의 형식은 html  -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%//다오랑 통신할껀데 다오에서 리스트에 디티오를 받아올꺼다.
  
  MyDao dao = new MyDao();
  
  List<MyDto> list = dao.selectList();
  
  %>
  
  <h1>List</h1>
  
  <table border="1">
  	<col width="50">
  	<col width="100">
  	<col width="200">
  	<col width="100">
  	
  	<tr>
  		<th>번호</th>
  		<th>작성자</th>
  		<th>제목</th>
  		<th>작성일</th>
  	</tr>
  	
  	
<% for(int i =0; i<list.size() ;i++)

{%>  	

		<tr>
			<td><%=list.get(i).getMyno() %></td><!-- 값을 html에다가 넣어주세요 -->
			<td><%=list.get(i).getMyname() %></td>
			<td><a href="mydetail.jsp?myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle() %></a></td>
			<td><%=list.get(i).getMydate() %></td>
		</tr>


<% }%>
  	<tr>
  		<td colspan="4" align="right">
  			<input type="button" onclick="location.href='myinsert.jsp'" value="글쓰기">  <!-- ./는 생략가능 -->
  		</td>
  	</tr>
  	
  	
  </table>

</body>
</html>