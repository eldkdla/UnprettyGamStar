package com.gamstar.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class FindUserIdServlet
 */
@WebServlet("/findId")
public class FindUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserIdServlet() {
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
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("inputName_1");
		String email = request.getParameter("inputEmail_1");
		
		System.out.println("가져온 정보 name : "+name);
		System.out.println("가져온 정보 email : "+email);
		
		User u = new User();
		
		u.setName(name);
		u.setEmail(email);
		
		u = new UserService().findUserId(u);
		
		System.out.println(u.getId());
		
		if(u.getId()!=null) {
		out.println(u.getId());
		System.out.println("값이있다고");
		}
		else
		{
			System.out.println("값이 없다고");
			out.println(0.0);
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
