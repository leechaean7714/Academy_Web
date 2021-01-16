package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MyBiz;
import com.mvc.biz.MyBizImpl;
import com.mvc.dto.MyDto;



@WebServlet("/con.do")//mapping 해부
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MyServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		
		MyBiz biz =new MyBizImpl();
		
		if(command.equals("list")) {
			
			List<MyDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("mylist.jsp", request,response);
			
			
		}else if(command.equals("detail")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MyDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("mydetail.jsp", request,response);
			
			
		}else if(command.equals("writeform")) {
			
			response.sendRedirect("myinsert.jsp");
			
			
		}else if(command.equals("writeres")) {
			
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content=request.getParameter("content");
			
			MyDto dto = new MyDto();
			
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.insert(dto);
			
			if(res>0) {
				
				jsResponse("글등록 성공", "con.do?command=list", response);
			}else{
				
				jsResponse("글등록 실패", "con.do?command=insertform", response);
			}
			
			
		}else if(command.equals("update")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyDto dto = biz.selectOne(seq);
			
			request.setAttribute("update", dto);
			dispatch("myupdate.jsp", request, response);
			
		}else if(command.equals("updateres")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content =request.getParameter("content");
			
			MyDto dto = new MyDto(seq,null,title,content,null);
			int res =biz.update(dto);
			
			if(res>0) {
				jsResponse("글수정 성공", "con.do?command=detail&seq="+seq, response);
			}else {
				jsResponse("글수정실패", "con.do?command=update&seq="+seq, response);
				
			}
			
			
		}else if(command.equals("delete")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			int res = biz.delete(seq);
			if(res>0) {
				
				jsResponse("글삭제 성공", "con.do?command=list", response);
			}else {
				
				jsResponse("글삭제 실패", "con.do?command=detail&seq="+seq, response);
			}
		}
		
		
		response.getWriter().append("<h1><a href='con.do?commdand=list'>잘못왔다.</a></h1>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
		
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
		
	}
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}

}
