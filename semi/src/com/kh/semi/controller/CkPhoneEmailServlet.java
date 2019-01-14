package com.kh.semi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.model.service.UserService;
import com.kh.semi.model.vo.User;

/**
 * Servlet implementation class CkPhoneServlet
 */
@WebServlet("/view/ckPhone")
public class CkPhoneEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CkPhoneEmailServlet() {
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
		
		boolean ck=false;
		
		User u=new User();
		u.setNo((int)se.getAttribute("userNo"));
		
		if(request.getParameter("ckEmail")!=null){
			u.setEmail(request.getParameter("ckEmail"));
			ck=new UserService().ckEmail(u);
		}
		else if(request.getParameter("ckPhone")!=null){
			u.setPhone(request.getParameter("ckPhone"));
			ck=new UserService().ckPhone(u);
		}
		
		
		PrintWriter out = response.getWriter();
		
		if(ck==true){
			out.print("true");
		}else if(ck==false){
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
