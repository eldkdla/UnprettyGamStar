package com.kh.semi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.model.service.UserService;
import com.kh.semi.model.vo.User;

/**
 * Servlet implementation class PasswordModifyServlet
 */
@WebServlet("/view/passwordModify")
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
		HttpSession se = request.getSession();
		se.setAttribute("myname", "lm");
		
		User u= new User();
		u.setUserId((String)se.getAttribute("myname"));		
		u.setUserPw("newPw");
		
		int result=new UserService().updatePassword(u);
		
		if(result!=0){
			System.out.println("비밀번호 변경성공");
		}
		else{
			System.out.println("비밀번호 변경실패");
		}
		

		response.sendRedirect("myprofile");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
