package com.reply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.biz.ReplyBiz;
import com.reply.dto.ReplyDto;


@WebServlet("/reply.do")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int board_seq;
       
    
    public ReplyServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		ReplyBiz biz = new ReplyBiz();
		
		if(command.equals("list")) {
			
			List<ReplyDto> list = biz.selectList(board_seq);
			request.setAttribute("list", list);
			dispatch("Board/detail.jsp", request, response);
					
		}
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}

}
