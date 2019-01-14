package com.gamstar.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamstar.model.service.UserService;
import com.gamstar.model.vo.User;

/**
 * Servlet implementation class ChkBeforePw
 */
@WebServlet("/view/chkBeforePw")
public class ChkBeforePw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChkBeforePw() {
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
		
		String beforePw=request.getParameter("beforePw");
		
		User u = new User();
		u.setNo((int)se.getAttribute("userNo"));
		
		User user=new UserService().chkBeforePw(u);
		
		boolean compare=beforePw.equals(user.getPw());
		
		PrintWriter out = response.getWriter();
		
		if(compare){
			out.print("true");
		}else{
			out.print("false");
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
