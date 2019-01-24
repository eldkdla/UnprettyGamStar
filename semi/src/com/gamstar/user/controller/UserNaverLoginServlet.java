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
		u.setId(nId);			//TB_USER_NAVER

		u.setEmail(nEmail);
		u.setName(nName);
		//u.setPhone(null);//TB_USER
		u.setLinkType(1);//기본 유저용 네이버는 1 추가해둘것

		User data = new UserService().loginCheckNaver(u); //로그인 확인
	
//끼효올
		//System.out.println("어느놈이냐??? = "+u.getId()+" : "+data.getId());
		if(data!=null && nId.equals(data.getId()))
		//if(u.getId()==data.getId())
		{

			System.out.println("네이버 연동 기존 가입자 로그인 성공");

			HttpSession session=request.getSession();//세션생성~!
			session.setAttribute("userNo", data.getNo());
			//response.sendRedirect(request.getContextPath()+"index.jsp");
			response.sendRedirect(request.getContextPath()+"/");

			System.out.println("네이버것서블렛 ? : "+(int)request.getSession().getAttribute("userNo"));

		}
		else//신규 가입인경우임
		{
			
			//기존 가입자인지 확인 추가
			
			
			System.out.println("우완전 신상일떈? 신규일땐?");
			int result = new UserService().insertUserNaver(u); //Naver user insert



			if(result>0)
			{
				HttpSession session=request.getSession();//세션생성~!
				session.setAttribute("userNo", u.getNo());
				response.sendRedirect(request.getContextPath()+"/");
				System.out.println("+++네이버것서블렛 ? : "+(int)request.getSession().getAttribute("userNo"));
			}
			else
			{	
				String view="view/common/msg.jsp";//응답페이지의 주소
				String msg="회원가입을<br>실패하였습니다!";//찾을수 없을때 메세지!
				String loc="/view/login.jsp";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);

				RequestDispatcher rd=request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
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
