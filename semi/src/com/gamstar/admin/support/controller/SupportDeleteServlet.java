package com.gamstar.admin.support.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.support.model.service.SupportService;

/**
 * Servlet implementation class SupportOriDeleteServlet
 */
@WebServlet("/admin/supportDelete")
public class SupportDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//no<0 관리자 아니면 못들어옴
		if(request.getSession().getAttribute("userNo")==null||(Integer)request.getSession().getAttribute("userNo")>0)
		{
			request.setAttribute("msg", "잘못된 접근입니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
			return;
		}
		else
		{
		
			int no=Integer.parseInt(request.getParameter("no"));
			int result=new SupportService().deleteSupportBoard(no);
			
			String view="";
			
			if(result>0)
			{
				if(no>0)
				view="/admin/supportList";
				else
				{
					view="/admin/supportView?no="+(no*-1);
				}
			}
			else
			{
				view="/view/common/msg.jsp";
				request.setAttribute("loc", "/admin/supportList");
				request.setAttribute("msg", "문의 삭제 실패");
			}
			
			int cPage=Integer.parseInt(request.getParameter("cPage"));
			request.setAttribute("cPage",cPage);
			request.getRequestDispatcher(view).forward(request, response);
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
