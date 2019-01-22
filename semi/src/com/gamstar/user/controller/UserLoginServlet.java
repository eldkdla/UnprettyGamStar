package com.gamstar.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		User u = new User();
		u.setId(id);
		u.setPw(pw);

		User data = new UserService().loginCheck(u); //로그인 확인

		String view="";//응답페이지의 주소
		String msg="";//찾을수 없을때 메세지!
		if(data==null)
		{
			//아이디가 없다!
			msg="입력하신 아이디가 <br> 존재하지 않습니다.";
			view="/view/common/msg.jsp";
			request.setAttribute("loc", "/view/login.jsp");
			request.setAttribute("msg", msg);

			RequestDispatcher rd=request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		else
		{
			//아이디가 일단있다
			if(pw.equals(data.getPw()))
			{
				//로그인 성공
				System.out.println("로그인 성공");
				view="/";//메인화면으로 localhost:9090

				//로그인이 성공했으므로 session객체에 값을 넣고 유지하자!
				HttpSession session=request.getSession();//세션생성~!
				session.setAttribute("userNo", data.getNo());
				//response.sendRedirect(request.getContextPath()+"index.jsp");
				response.sendRedirect(request.getContextPath()+"/");
				
				System.out.println("서블렛 ? : "+(int)request.getSession().getAttribute("userNo"));

			}
			else {
				//패스워드가 일치하지 않습니다.
				System.out.println("패스워드가 일치하지 않음");
				msg="패스워드가<br>일치하지 않습니다!";
				view="/view/common/msg.jsp";
				request.setAttribute("loc", "/view/login.jsp");
				request.setAttribute("msg", msg);

				RequestDispatcher rd=request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
		}

		//		response.sendRedirect("views/user/userLogin.jsp");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
