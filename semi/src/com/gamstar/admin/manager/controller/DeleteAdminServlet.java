package com.gamstar.admin.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.manager.model.service.AdminManagerService;


/**
 * Servlet implementation class DeleteAdminServlet
 */
@WebServlet("/admin/manager/deleteAdmin")
public class DeleteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAdminServlet() {
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
		if(request.getSession().getAttribute("userNo")==null||(Integer)request.getSession().getAttribute("userNo")>=0||(Integer)request.getSession().getAttribute("userNo")<-2)
		{
			request.setAttribute("msg", "잘못된 접근입니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
			return;
		}
		else
		{
			int adminNo=Integer.parseInt(request.getParameter("stateFrmAdminNo"));
			int result=0;
			
			String view="/view/common/msg.jsp";
			String msg="";
			String loc="";
			
			
			/*int noOfAdmin=new AdminManagerService().selectAdminCount(); :이건 누구든 상관 없이 어드민 한명이상 남기기 위해*/
			
			if(adminNo!=-1) {
				result=new AdminManagerService().deleteAdmin(adminNo);
			}
			
			
			if(result>0)
			{
				msg="관리자 삭제 성공";
				loc="/admin/manager/adminList";
			}
			else
			{
				msg="관리자 삭제 실패";
				if(adminNo==-1) {
					/*msg="관리자는 1명 이상이어야 합니다";*/
					msg="삭제할 수 없는 관리자입니다.";
				}
				loc="/admin/manager/adminList";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
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
