package com.gamstar.admin.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.manager.model.service.AdminManagerService;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class CreateNewAdminServlet
 */
@WebServlet(name="CreateNewAdminServlet", urlPatterns="/admin/manager/createAdmin")
public class CreateNewAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewAdminServlet() {
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
			String adminType=request.getParameter("adminType");
			String id=request.getParameter("userId");
			String pw=request.getParameter("userPw");
			String name=request.getParameter("userName");
			String email=request.getParameter("userEmail");
			String phone=request.getParameter("userPhone");
			
			
			User admin=new User();
			admin.setId(id);
			admin.setPw(pw);
			admin.setName(name);
			admin.setEmail(email);
			admin.setPhone(phone);
			
			int result=0;
			
			
			result=new AdminManagerService().createAdmin(admin);
			
			String view="/view/common/msg.jsp";
			String msg="";
			String loc="";
			
			if(result>0)
			{
				msg="관리자 생성 성공";
				loc="/admin/manager/adminList";
			}
			else
			{
				msg="관리자 생성 실패";
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
