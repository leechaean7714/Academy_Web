package com.score.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/score.cal")
public class ScoreAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ScoreAjax() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));

		int sum = kor + eng + math;

		double avg = sum / 3.0;
		// json simple jar에 있는 class
		// 클라이언트가 서버에 요청 (데이터를 전달하면서 요청) 서버안에 서블릿이 이 데이터 받아서 json형태로 만들어서 응답
		// 서버는 데이터가 왔다간걸 모른다. ajax(비동기:서버몰래 일을 수행)
		JSONObject obj = new JSONObject();// JSONObject:json 형태의 데이터를 관리해 주는 메서드

		obj.put("name", name);// obj.put:데이터를 집어 넣는다.
		obj.put("sum", sum);// 노란색이 뜨는 이유:json simple jar에 있는 JSONObject 클래스는 hashmap(컬렉션의 map)기반으로
							// 만들어져있어서// 타입을 강제 시킨다.
		obj.put("avg", avg);// 컬렉션에서도 타입을 강제시켜주는 지네릭, 즉 지네릭 좀 써라 그러나 이렇게게 둬도 된다.
		
		String res = obj.toJSONString();
		System.out.println("servlet에서 만들어진 결과:" + res);

		PrintWriter out = response.getWriter();// student.html이 있는 ajax 한테 응답해준다.
		out.println(res);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		doGet(request, response);
	}

}
