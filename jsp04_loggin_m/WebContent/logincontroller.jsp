<%@page import="java.util.List"%>
<%@page import="com.login.dto.MBDto"%>
<%@page import="com.login.biz.MBBiz"%>
<%@page import="com.login.biz.MBBizImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
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
		System.out.println("[" + command + "]");

		MBBiz biz = new MBBizImpl();

		if (command.equals("login")) {
			String myid = request.getParameter("id");
			String mypw = request.getParameter("pw");

			MBDto dto = biz.login(myid, mypw);

			if (dto.getMyenabled().equals("N")) {
	%>
	<script type="text/javascript">
				alert("탈퇴한 회원정보입니다.");
				location.href="index.jsp";
			</script>
	<%
		} else if (dto.getMyenabled().equals("Y")) {

				if (dto != null) {
					// session : 만료되기 전까지 어플리케이션 전체에서 사용 가능
					session.setAttribute("dto", dto);
					session.setMaxInactiveInterval(10 * 60);
					// setMaxInactiveInterval(second) : 초 만큼 활동이 없으면 session을 invalidate 한다. (default : 30분 / 음수 : 무제한)

					if (dto.getMyrole().equals("ADMIN")) {
						response.sendRedirect("adminmain.jsp");
					} else if (dto.getMyrole().equals("USER") || dto.getMyrole().equals("MANAGER")) {
						response.sendRedirect("usermain.jsp");
					}
				}
			} else {
	%>
	<script type="text/javascript">
			alert("id와 pw를 다시한번 확인해 주세요!");
			location.href="index.jsp";
		</script>
	<%
		}

		} else if (command.equals("logout")) {
			// 만료 : session은 그대로 있고 그 안에 들어있는 값만 clear 시켜준다.
			session.invalidate();
			response.sendRedirect("index.jsp");

		} else if (command.equals("selectlist")) {
			List<MBDto> list = biz.selectList();

			request.setAttribute("list", list);

			pageContext.forward("userselectlist.jsp");

		} else if (command.equals("selectenabled")) {
			List<MBDto> list = biz.selectEnabled();

			request.setAttribute("list", list);

			pageContext.forward("userselectenabled.jsp");

		} else if (command.equals("updateroleform")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MBDto dto = biz.selectUser(myno);
			request.setAttribute("select", dto);
			pageContext.forward("updaterole.jsp");

		} else if (command.equals("updateroleres")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			String role = request.getParameter("role");

			int res = biz.updateRole(myno, role);

			if (res > 0) {
	%>
	<script type="text/javascript">
			alert("등급 변경을 성공하였습니다.");
			location.href="adminmain.jsp";
		</script>
	<%
		} else {
	%>
	<script type="text/javascript">
			alert("등급 변경을 실패하였습니다.");
			location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
		</script>
	<%
		}
		} else if (command.equals("regist")) {
			response.sendRedirect("registform.jsp");

		} else if (command.equals("selectuser")) {
			int myno = Integer.parseInt(request.getParameter("myno"));

			MBDto dto = biz.selectUser(myno);

			request.setAttribute("select", dto);

			pageContext.forward("userinfo.jsp");

		} else if (command.equals("updateuser")) {
			int myno = Integer.parseInt(request.getParameter("myno"));

			MBDto dto = biz.selectUser(myno);

			request.setAttribute("update", dto);

			pageContext.forward("userupdate.jsp");

		} else if (command.equals("updateres")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("mypw");
			String myname = request.getParameter("myname");
			String myaddr = request.getParameter("myaddr");
			String myphone = request.getParameter("myphone");
			String myemail = request.getParameter("myemail");

			MBDto dto = new MBDto();
			dto.setMyno(myno);
			dto.setMyid(myid);
			dto.setMypw(mypw);
			dto.setMyname(myname);
			dto.setMyaddr(myaddr);
			dto.setMyphone(myphone);
			dto.setMyemail(myemail);

			int res = biz.updateUser(dto);

			if (res > 0) {
	%>
	<script type="text/javascript">
	alert("회원 정보를 수정 성공하였습니다.");
	 location.href = 'logincontroller.jsp?command=selectuser&myno=<%=myno%>';
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("회원 정보를 수정 실패하였습니다.");
		history.back();
	</script>
	<%
		}
		} else if (command.equals("deleteuser")) {
			int myno = Integer.parseInt(request.getParameter("myno"));

			int res = biz.deleteUser(myno);

			if (res > 0) {
	%>
	<script type="text/javascript">
		alert("회원 탈퇴 성공");
		location.href = 'index.jsp';
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("회원 탈퇴 실패");
		location.href = "usermain.jsp";
	</script>
	<%
		}
		} else if (command.equals("registres")) {
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("mypw");
			String myname = request.getParameter("myname");
			String myaddr = request.getParameter("myaddr");
			String myphone = request.getParameter("myphone");
			String myemail = request.getParameter("myemail");

			MBDto dto = new MBDto();
			dto.setMyid(myid);
			dto.setMypw(mypw);
			dto.setMyname(myname);
			dto.setMyaddr(myaddr);
			dto.setMyphone(myphone);
			dto.setMyemail(myemail);

			int res = biz.insertUser(dto);
			if (res > 0) {
	%>
	<script type="text/javascript">
		alert("회원가입을 성공하셨습니다.");
		location.href = "index.jsp";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("회원가입을 실패하셨습니다.");
		history.back();
	</script>
	<%
		}
		} else if (command.equals("idchk")) {
			String myid = request.getParameter("id");

			MBDto dto = biz.idChk(myid);
			
if (dto.getMyid() == null || dto.getMyid().equals(null)) {
	%>
	<script type="text/javascript">
		alert("사용할 수 있는 아이디입니다aaaa.");
	</script>
	<%
		} else if (dto.getMyid().equals(myid)) {
	%>
	<script type="text/javascript">
		alert("이미 사용중인 아이디입니다aaaaa.");
		location.href = "registform.jsp";
	</script>
	<%
		}
		} else if (command.equals("chk_id")) {
			String myid = request.getParameter("id");
			MBDto dto = biz.idChk(myid);

			boolean idnotused = true;
			if (dto != null) {
				idnotused = false;
			}

			response.sendRedirect("idchk.jsp?idnotused=" + idnotused);
		}
	%>

	<h1 style="color: red;">잘못왔다!! (command 확인)</h1>
</body>
</html>