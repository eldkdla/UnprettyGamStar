package com.gamstar.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class MyProfilePageModifyServlet
 */
@WebServlet("/view/profilemodifyStart")
public class MyProfilePageModifyStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfilePageModifyStartServlet() {
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
		
		if(request.getSession().getAttribute("userNo")!=null&&request.getParameter("uu")==null){
		
			User user= new User();
			user.setNo((int)request.getSession().getAttribute("userNo"));
			//유저정보 가져오기
			User userData=new UserService().selectUser(user);
			System.out.println(userData.getNo()+" : "+userData.getName());
			request.setAttribute("userData", userData);
			
			//내정보창으로 정보보내기
			RequestDispatcher rd = request.getRequestDispatcher("/view/profileModify.jsp");
			rd.forward(request, response);
			
		}else{
			request.setAttribute("msg", "잘못된 접근");
			request.setAttribute("loc", "");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
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
