package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//urlpattern:
@WebServlet("/HelloServlet")
//servlet-name:com.hello.controller.HelloServlet
//HttpServlet:통신규약문서공유규약.내가 http약속에 맞게 요청하면 http에 맞게 문서를  html에 구조화시켜서 응답해줄께
/*
 * restfullapi공부하기(백엔드 필수 공부:카카오톡 2차문제 restfull로 간단한 홈쇼핑만들기) doGet :나 이거 읽고 싶어요
 * doPost:나 쓰고 싶어요 doPut :수정하고 싶어요 doDelete: uri(url+urn)이 개념을 잘 지키고 만들자.
 * 
 * 요청하면 응답해서 처리해주는 것은 servlet은 누가 가지고 있나? (tomcat(server)이 가지고 있고, tomcat이
 * servletcontainer ) servletapi 톰캣에서 찾음.
 * 
 */

public class HelloServlet extends HttpServlet {// http://tomcat.apache.org/tomcat-9.0-doc/servletapi/index.html
	private static final long serialVersionUID = 1L;

	/*
	 * public HelloServlet() { System.out.println("Servlet 생성자!!!");
	 * 
	 * }
	 * 
	 */
	private String initParam;
	private String contextParam;

	@Override
	public void init(ServletConfig config) throws ServletException {

		System.out.println("Servlet init!!!!");

		initParam = config.getInitParameter("driver");
		contextParam = config.getServletContext().getInitParameter("jdbcurl");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서블릿 쓸때 무조건 써주자.
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("get방식으로 넘어옴");

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		System.out.println("post방식으로 넘어옴!!!");
		System.out.println("initParam:" + initParam);// initParm이 왜 null일까?.!!!
		System.out.println("contextParm:" + contextParam);

		String command = request.getParameter("command");
		System.out.println("command:" + command);
		System.out.println("=============================");

		// 서블릿에서 응답되서 나가지는 response객체에다가 값을 넣어줄꺼다 .그값은 문서(html)
		// 자바 코드안에 html코드가 들어가는형태->힘듦 jsp html안에 자바가 들어간다 <%자바%>
		PrintWriter out = response.getWriter();
		out.println("<h1 style ='background-color:skyblue'>HelloServlet</h1>");
		out.println("<h3>init-service-doGet/doPost-destroy</h3>");
		out.println("<a href ='home.html'>돌아가기</a>");

	}

	/* ctrl+space /공부할것:최상위 객체 즉 모든 class의 조상인 object 의 메소드를 override가능 */
	@Override
	public void destroy() {
		System.out.println("servlet destroy!!!");
	}

}
