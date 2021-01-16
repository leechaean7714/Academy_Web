package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.AnswerBiz;
import com.answer.biz.AnswerBizImpl;
import com.answer.dto.AnswerDto;

import sun.rmi.server.Dispatcher;

@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AnswerServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String command = request.getParameter("command");
		System.out.println("<" + command + ">");

		AnswerBiz biz = new AnswerBizImpl();

		if (command.equals("list")) {

			List<AnswerDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("answerlist.jsp", request, response);

		} else if (command.equals("writeform")) {

			response.sendRedirect("answerinsert.jsp");//response. 이동하고.....

		} else if (command.equals("writeres")) {

			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			AnswerDto dto = new AnswerDto();

			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);

			int res = biz.insert(dto);
			if (res > 0) {
				jsResponse("글등록 성공", "answer.do?command=list", response);
			} else {
				jsResponse("글등록 실패", "answer.do?command=writeform", response);

			}
		} else if (command.equals("detail")) {

			int boardno = Integer.parseInt(request.getParameter("boardno"));

			AnswerDto dto = biz.selectOne(boardno);

			request.setAttribute("dto", dto);// 
			dispatch("answerdetail.jsp", request, response);//

		} else if (command.equals("update")) {

			int boardno = Integer.parseInt(request.getParameter("boardno"));

			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);//session
			dispatch("answerupdate.jsp", request, response);//sendRedirect

		} else if (command.equals("updateres")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			AnswerDto dto = new AnswerDto(boardno,0,0,0,title,content,null,null);
			
			int res = biz.update(dto);
			if (res > 0) {
				jsResponse("글수정 성공", "answer.do?command=list", response);

			} else {
				jsResponse("글수정 실패", "answer.do?command=update&boardno=" + boardno, response);

			}

		} else if (command.equals("answer")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch("answerform.jsp", request, response);

		} else if (command.equals("answerres")) {
			
			int parentboardno = Integer.parseInt(request.getParameter("parentboardno"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			AnswerDto dto = new AnswerDto();

			dto.setBoardno(parentboardno);
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.answerProc(dto);

			if (res > 0) {
				jsResponse("답변성공", "answer.do?command=list", response);
			} else {
				jsResponse("답변작성 실패", "answer.do?command=answer&boardno=" + parentboardno, response);

			}

		}else if(command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			int res = biz.delete(boardno);
			
			if(res>0) {
				jsResponse("글삭제 성공", "answer.do?command=list", response);
			}else {
				jsResponse("글삭제 실패", "answer.do?command=detail&boardno="+boardno, response);
			}
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
	doGet(request, response);
		
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);

	}
	
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();

		out.println("<script type = 'text/javascript'>");
		out.println("alert('" + msg + "')");
		out.println("location.href='" + url + "'");
		out.println("</script>");

	}
	
	

}
