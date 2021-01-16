package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ScopeController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("controller 도착!!!");// 여기까지만 나오면 아무것도 없는 화면이 나온다.

		// get(담긴 객체를 반환한다.),setAttribute 객체를 담는다.
		String requestId = (String) request.getAttribute("requestId");

		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionId");

		ServletContext application = getServletContext();
		String applicationId = (String) application.getAttribute("applicationId");

		System.out.println("request : " + requestId);
		System.out.println("session : " + sessionId);
		System.out.println("application : " + applicationId);

		String param = request.getParameter("req");
		System.out.println("request parameter : " + param);
		System.out.println("========================================");

		PrintWriter out = response.getWriter();
		out.println("<h1>응답</h1>");
/*
		out.println("<table border=\"1\">\r\n" 
		            + "<tr>\r\n" 
				    + "<th>scope</th>\r\n"
				+ "   	<th>값</th>\r\n" + "</tr>\r\n" + "<tr>\r\n"
				+ "     <th>page</th>\r\n" + "<td>null</td>\r\n" + "</tr>\r\n"
				+ "   	<tr>\r\n" + "<th>request</th>\r\n"
				+ "   		<td>+requestId+</td>\r\n" + "   		</tr>\r\n" + "   		<tr>\r\n"
				+ "   			<th>session</th>\r\n" + "   			<td>+sessionId+</td>\r\n"
				+ "   		</tr>\r\n" + "   		<tr>\r\n" + "   			<th>application</th>\r\n"
				+ "   			<td>+applicationId+</td>\r\n" + "   		</tr>\r\n" + "   		\r\n"
				+ "   		\r\n" + "   </table>");
	
*/
		// RequestDispatcher:이 클래스는 현제 request 담 정보를 저장하고 있다가 그 다음 페이지 그 다음 페이지에도
		// 해당 정보를 볼수 있게 계속 저장하는 기능이다.
		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");

		request.setAttribute("requestId", "servlet 에서 보내준 request");
		dispatch.forward(request, response);// forward하기 전까지 남의것을 쓸수 있따 송금전까지 쓸수 없다.

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		doGet(request, response);
	}

}
