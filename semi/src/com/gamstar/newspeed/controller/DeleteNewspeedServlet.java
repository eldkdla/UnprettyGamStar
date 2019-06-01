package com.gamstar.newspeed.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.newspeed.model.service.NewspeedService;
import com.gamstar.newspeed.model.vo.Newspeed;

/**
 * Servlet implementation class DeleteNewspeedServlet
 */
@WebServlet("/view/deletenewspeed")
public class DeleteNewspeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNewspeedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(request.getSession().getAttribute("userNo")!=null&&request.getParameter("uu")==null){

			int storedNewspeedNo=Integer.parseInt(request.getParameter("storedNewspeedNo1"));

			
			Newspeed newspeed=new Newspeed();
			newspeed.setNo(storedNewspeedNo);
			newspeed.setUserNo((int)request.getSession().getAttribute("userNo"));
			int result=new NewspeedService().deleteNewspeed(newspeed);
			
			if(result!=0){
				System.out.println("게시물 삭제성공");	
			}
			else{
				System.out.println("게시물 삭제실패");	
			}
		
		}else{
			request.setAttribute("msg", "잘못된 접근");
			request.setAttribute("loc", "");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
