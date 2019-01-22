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
 * Servlet implementation class UnregisterServlet
 */
@WebServlet(name="UnregisterServlet",urlPatterns="/view/unregister")
public class UnregisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnregisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		User user=new User();
		user.setNo((int)request.getSession().getAttribute("userNo"));
		
		int result=new UserService().unregister(user);
		
		if(result!=0){
			request.setAttribute("msg", "회원탈퇴 성공");
			request.setAttribute("loc", "");
		}
		else{
			request.setAttribute("msg", "회원탈퇴 실패");
			request.setAttribute("loc", "");
		}

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
