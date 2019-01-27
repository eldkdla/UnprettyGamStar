package com.gamstar.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class ResetUserPasswordServlet
 */
@WebServlet(name="ResetUserPasswordServlet",urlPatterns="/resetPassword")
public class ResetUserPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetUserPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		int userNo = Integer.parseInt(request.getParameter("no"));
		User u = new User();
		u.setNo(userNo);		
		u.setPw(request.getParameter("newPw"));
		String msg="";
		String loc="";
		
		int result=new UserService().updatePassword(u);


		if(result!=0){
			request.getSession().removeAttribute("userNo");
			msg="비밀번호 변경성공";
			loc="";
		}
		else{
			msg="비밀번호 변경실패";
			loc="/view/profile";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
