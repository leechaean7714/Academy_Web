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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		int sum = kor+eng+math;
		
		double avg = sum/3.0;
		//json simple jar
		
		JSONObject obj=new JSONObject();//JSONObject:json 형태의 데이터를 관리해 주는 메서드
										//맵의 특성으로 인해 순서를 보장하지 않는다.
		
		obj.put("name", name);//obj.put:데이터를 집어 넣는다.
		obj.put("sum", sum);
		obj.put("avg", avg);
		
		String res = obj.toJSONString();
		
		System.out.println("servlet에서 만들어진 결과:"+res);
		
		PrintWriter out = response.getWriter();
		out.println(res);
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		doGet(request, response);
	}

}
