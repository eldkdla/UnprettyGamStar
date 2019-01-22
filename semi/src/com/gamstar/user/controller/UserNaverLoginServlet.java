package com.gamstar.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class UserNaverLoginServlet
 */
@WebServlet("/nlogin")
public class UserNaverLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserNaverLoginServlet() {
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
		String nId = request.getParameter("naverId");
		String nEmail = request.getParameter("email");
		String nName = request.getParameter("name");
		System.out.println("이름이 왜?"+nName);
		System.out.println(nId+" : "+nEmail+" : "+nName);
		
		User u = new User();
		u.setId(nId);
		u.setEmail(nEmail);
		u.setName(nName);
				
		User data = new UserService().loginCheckNaver(u); //로그인 확인
		String view="";//응답페이지의 주소
		String msg="";//찾을수 없을때 메세지!
		
		if(nId.equals(data.getId()))
				{
			
			System.out.println("네이버 로그인 성공");
			view="/";//메인화면으로 localhost:9090
			
			HttpSession session=request.getSession();//세션생성~!
			session.setAttribute("userNo", data.getNo());
			//response.sendRedirect(request.getContextPath()+"index.jsp");
			response.sendRedirect(request.getContextPath()+"/");
			
			System.out.println("네이버것서블렛 ? : "+(int)request.getSession().getAttribute("userNo"));
			
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
