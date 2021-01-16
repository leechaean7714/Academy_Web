package com.cal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;

import net.sf.json.JSONObject;


@WebServlet("/calcountajax.do")
public class CalCountAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CalCountAjax() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		String yyyyMMdd=request.getParameter("yyyyMMdd");
		
		CalDao dao = new CalDao();
		int count = dao.getCalCount(id, yyyyMMdd);
		
		System.out.println("일정 갯수:"+count);
		//data를 map으로 감싼다.
		Map<String, Integer> map = new HashMap<String,Integer>();
		map.put("count",count);
		//맵을 제이슨으로 변환한다.
		JSONObject obj = JSONObject.fromObject(map);//맵이라는 객체에서 json객체 만들었어요.
		//변환된 제이슨을 응답한다.
		PrintWriter out = response.getWriter();
		obj.write(out);
				
		
		
		
		
		
		
		
		
	}

}
