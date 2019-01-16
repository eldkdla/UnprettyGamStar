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
 * Servlet implementation class ChkPhoneServlet
 */
@WebServlet("/view/chkPhone")
public class ChkPhoneEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChkPhoneEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession se=request.getSession();
		se.setAttribute("userNo", 6);
		
		boolean chk=false;
		
		User user=new User();
		user.setNo((int)se.getAttribute("userNo"));
		
		if(request.getParameter("chkEmail")!=null){
			user.setEmail(request.getParameter("chkEmail"));
			chk=new UserService().chkEmail(user);
		}
		else if(request.getParameter("chkPhone")!=null){
			user.setPhone(request.getParameter("chkPhone"));
			chk=new UserService().chkPhone(user);
		}
		
		
		PrintWriter out = response.getWriter();
		
		if(chk==true){
			out.print("true");
		}else if(chk==false){
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
