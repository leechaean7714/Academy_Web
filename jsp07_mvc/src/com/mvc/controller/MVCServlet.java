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
import javax.servlet.jsp.PageContext;

import com.mvc.biz.MVCBIz;
import com.mvc.biz.MVCBizImpl;
import com.mvc.dto.MVCDto;
import com.mvc.dto.PagingDto;



@WebServlet("/MVCServlet")
public class MVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MVCServlet() {
       
    }

	//07.mvc
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		
		String command = request.getParameter("command");
		System.out.printf("<%s>\n",command);
		
		MVCBIz biz = new MVCBizImpl();
		PrintWriter out = response.getWriter();
		
		if (command.equals("list")) {
			//response.sendRedirect("mvclist.jsp");
			//1.
			//2.
			
			List<MVCDto> list = biz.selectList();
			
			request.setAttribute("list", list);
			dispatch("mvclist.jsp", request, response);
			
		}else if(command.equals("writeform")) {
			
			
			response.sendRedirect("mvcinsert.jsp");
		
		} else if(command.equals("writeres")) {
			 
			 String writer = request.getParameter("writer");
			 String title = request.getParameter("title");
			 String content = request.getParameter("content");
			 
			 MVCDto dto = new MVCDto();
			 
			 dto.setWriter(writer);
			 dto.setTitle(title);
			 dto.setContent(content);
			 
			 int res = biz.insert(dto);
			 
		
			 
			 if(res>0) {
			 //out.println("<script type=\"text/javascript\">");
			 //out.println("alert(\"새로운 글을 등록 완료하였습니다.\");");
			// out.println("location.href = 'mvc.do?command=list';");
			// out.println("</script>");
			 //dispatch("mvc.do?command=list",request,response);
			//인서트 했더니 포워드 했따.	 방지할 수 있는 방법 
			 //response.sendReirect("mvc.do?command=list");
				 jsResponse("글등록 성공1", "mvc.do?command=list", response);
			}else {
				
				//out.println("<script type=\"text/javascript\">");
				//out.println("alert(\"글 등록에 실패하였습니다.\");");
				//out.println("location.href = 'mvc.do?command=writeform';");
				//out.println("</script>");
				//dispatch("mvc.do?command=insertform",request,response);
				jsResponse("글등록 실패ㅠㅠ", "mvc.do?command=insertform", response);
				}
			}else if(command.equals("detail")) {
				
				int seq = Integer.parseInt(request.getParameter("seq"));
				
				MVCDto dto = new MVCDto();
				dto=biz.selectOne(seq);
				request.setAttribute("detail", dto);
				
				dispatch("mvcdetail.jsp",request,response);
				
				
				}else if(command.equals("update")) {
					
				int seq = Integer.parseInt(request.getParameter("seq"));	
				MVCDto dto = biz.selectOne(seq);
				request.setAttribute("update", dto);
				
				dispatch("mvcupdate.jsp", request, response);
					
					
				}else if(command.equals("updateres")) {
					
					int seq = Integer.parseInt(request.getParameter("seq"));
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					
					MVCDto dto = new MVCDto(seq,null,title,content,null);
					
					int res = biz.update(dto);
					
					if(res>0) {
						
					jsResponse("글 수정 성공", "mvc.do?command=detail&seq="+seq, response);
					}else {
					jsResponse("글 수정 실패", "mvc.do?command=update&seq="+seq, response);	
						
					}
					
				}else if(command.equals("delete")) {
					int seq = Integer.parseInt(request.getParameter("seq"));
					int res = biz.delete(seq);
					
					if(res>0) {
						jsResponse("삭제성공", "mvc.do?command=list", response);
						
					}else {
						
						jsResponse("삭제실패", "mvc.do?command=detail&seq="+seq, response);
					}
					
				}
		
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		
		String res = "<script type=\"text/javascript\">"
		             +"alert('"+msg+"');"
				     +" location.href='"+url+"';"+"</script>";
		
		PrintWriter out =response.getWriter();
		out.println(res);
	}
	
	private void dispatch(String url,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		
		dispatch.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		
		
		
		doGet(request,response);
	}

}
