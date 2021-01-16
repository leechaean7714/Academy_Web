<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

/*
	function idChk(){
		var doc = document.getElementById("myid");
		
		if(doc.value.trim()=="" || doc.value == null){
			alert("아이디를 입력해 주세요");
		} else {
			location.href="logincontroller.jsp?command=idchk&id=" + doc.value;		
		}
	}
*/
	function idchkConfirm(){
		var chk = document.getElementsByName("myid")[0].title;
		if(chk == 'n'){
			alert("id 중복체크를 먼저 해주세요");
			document.getElementsByName("myid")[0].focus();
		}
	}

	function idChk(){
		var doc = document.getElementsByName("myid")[0];
		
		if(doc.value.trim()==""|| doc.value == null){
			alert("아이디를 입력해 주세요jjjjj");
		} else {
			open("logincontroller.jsp?command=chk_id&id="+doc.value,"","width=200, height=200");
		}
	}
	
	function pwChk(){
		var pw = document.getElementsByName("mypw")[0];
		var pwchk = document.getElementsByName("pwchk")[0];
		if(pw.value == pwchk.value){
			document.getElementsByName("pwres")[0].style.background="red";
		} else{			
			pwchk.style.background="red";
			pwchk.style.color='red';
		}
	}

</script>
<body>
<form action="logincontroller.jsp" method="post">
<input type="hidden" name="command" value="registres"/>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="myid" id="myid" title="n" required="required" />
				<input type="button" value="중복체크" onclick="idChk();"/>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="mypw" required="required" onclick="idchkConfirm();"/></td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td><input type="password" name="pwchk" required="required" onkeydown="pwChk();"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="text" name="pwres" value=""/></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="myname" required="required" onclick="idchkConfirm();"/></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="myaddr" required="required" onclick="idchkConfirm();"/></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="myphone" required="required" onclick="idchkConfirm();"/></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="myemail" required="required" onclick="idchkConfirm();"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="가입하기"/>
				<input type="button" value="취소" onclick="location.href='index.jsp'"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>