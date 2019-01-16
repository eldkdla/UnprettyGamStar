package com.gamstar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamstar.model.service.UserService;
import com.gamstar.model.vo.User;

/**
 * Servlet implementation class PasswordModifyServlet
 */
@WebServlet(name="PasswordModifyServlet",urlPatterns="/view/passwordModify")
public class PasswordModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession se = request.getSession();
		se.setAttribute("userNo", 6);
		
		User user= new User();
		user.setNo((int)se.getAttribute("userNo"));		
		user.setPw(request.getParameter("newPw"));
		String msg="";
		String loc="";
		
		int result=new UserService().updatePassword(user);
		
		
		if(result!=0){
			msg="비밀번호 변경성공";
			loc="/view/profile";
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
