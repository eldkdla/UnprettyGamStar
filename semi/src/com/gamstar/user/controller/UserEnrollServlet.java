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
 * Servlet implementation class UserEnrollServlet
 */
@WebServlet(name="UserEnrollServlet",urlPatterns="/userenroll")
public class UserEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEnrollServlet() {
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
		System.out.println("민지왔쪄염 뿌우");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		String userName = request.getParameter("userName");
		
		System.out.println("뿌우뿌우");
		System.out.println(userId+" : "+userPw+" : "+userEmail+" : "+userPhone+" : "+userName);
		 
		User u = new User();
		u.setId(userId);
		u.setPw(userPw);		//basic user
		
		u.setName(userName);	//tb_user
		u.setEmail(userEmail);
		u.setPhone(userPhone);
		
		u.setLinkType(0);//기본 유저용 네이버는 1 추가해둘것
		
		//int result1=new UserService().insertUser(u); //tb_user insert
		
		
		int result = new UserService().insertUserBasic(u); //basicuser insert
		
		String view="view/common/msg.jsp";
		String loc="";
		String msg="";
		
		if(result>0)
		{
			msg="감스타<br>가입을 환영합니다. :)<br>회원가입이<br>완료되었습니다!";
			loc="/view/login.jsp";
		}
		else
		{
			msg="회원가입을<br>실패하였습니다!";
			loc="/view/userEnroll.jsp";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		RequestDispatcher rd=request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
