<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.biz.MVCBizImple"%>
<%@page import="com.mvc.biz.MVCBiz"%>
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
		String command = request.getParameter("command");
		System.out.println("<" + command + ">");

		MVCBiz biz = new MVCBizImple();//전부 적용
		//03.mvc
		if (command.equals("list")) {
			//1.
			//2.
			List<MVCDto> list = biz.selectList();
			request.setAttribute("list", list);//내가 요청한 것.
			//3.
			pageContext.forward("boardlist.jsp");//

		} else if (command.equals("writeform")) {
			//1.받을 데이터가 있는지?

			//2.db에서 가져올 데이터가 있는지?

			//3.어디로 갈건지?
			response.sendRedirect("boardwrite.jsp");///////////////////////////////////////////

		} else if (command.equals("writeres")) {
			//1.받을 데이터가 있는지?
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			MVCDto dto = new MVCDto();

			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			//2.db에서 가져올 데이터가 있는지?

			//적용된 로우의 갯수	
			int res = biz.insert(dto);

			//3.어디로 갈건지?
			if (res > 0) {
	%>

	<script type="text/javascript">
		alert("새로운 글을 등록 완료하였습니다");
		location.href = "controller.jsp?command=list";
	</script>
	<%
		} else {
	%>

	<script type="text/javascript">
		alert("글 등록에 실패하였습니다.");
		location.href = "controller.jsp?command=writeform";
	</script>


	<%
		}
		} else if (command.equals("muldel")) {
			//1.
			String[] seqs = request.getParameterValues("chk");

			if (seqs == null || seqs.length == 0) {
				//2
	%>
	<script type="text/javascript">
     alert("하나이상을 선태하세요.");
      location.href = "controller.jsp?command=list";
    </script>
	<%
		} else {

				boolean res = biz.multiDelete(seqs);

				if (res) {
	%>

	<script type="text/javascript">
		alert("삭제 완료.");
		location.href = "controller.jsp?command=list";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("삭제 실패.");
		location.href = "controller.jsp?command=list";
	</script>

	<%
		}

			}
		} else if (command.equals("detail")) {
			//1.받을 데이터가 있을지
			int seq = Integer.parseInt(request.getParameter("seq"));

			//2.db에서 가져올 데이터가 있을지
			/* MVCDto dto = biz.selectOne(seq); */

			MVCDto dto = new MVCDto();
			dto = biz.selectOne(seq);
			request.setAttribute("detail", dto);

			//3.어디로 갈껀지 
			pageContext.forward("boarddetail.jsp");//

		} else if (command.equals("delete")) {
			//1.받을 데이터가 있는지
			int seq = Integer.parseInt(request.getParameter("seq"));
			//2.db에서 가져올 데이터가 있을지
			int res = biz.delete(seq);
			System.out.print(res);

			//3.어디로 갈껀지
			if (res > 0) {
	%>
	<script type="text/javascript">
		alert("삭제 성공");
		location.href = "controller.jsp?command=list";//???????
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("삭제 실패");
		location.href = "controller.jsp?command=detail&seq=<%=seq%>";
	</script>
	<%
		}
		} else if (command.equals("update")) {
			//1.받을 데이터가 있는지
			int seq = Integer.parseInt(request.getParameter("seq"));
			//2.db에 가져올 데이터가 있을 지
			MVCDto dto = biz.selectOne(seq);
			request.setAttribute("update", dto);//"dto"
			//3.어디로 갈껀지
			pageContext.forward("boardupdate.jsp");

		} else if (command.equals("updateres")) {
			//1.받을 데이터가 있는지
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			//2.db에 가져올 데이터가 있을 지
			MVCDto dto = new MVCDto(seq, null, title, content, null);
			/* dto.setTitle(title);
			dto.setContent(content);
			dto.setSeq(seq); */

			int res = biz.update(dto);

			//3.어디로 갈껀지
			if (res > 0) {
	%>
	<script type="text/javascript">
			alert("수정 성공");
			location.href="controller.jsp?command=detail&seq=<%=seq%>";
			</script>

	<%
		} else {
	%>

	<script type="text/javascript">
			alert("수정실패");
			location.href="controller.jsp?command=update&seq=<%=seq%>
		";
		/* controller 는 화면제어 모든 것 들이 controller */
	</script>


	<%
		}
		}
	%>

	<h1>잘못왔다(command 확인!)</h1>
</body>
</html>