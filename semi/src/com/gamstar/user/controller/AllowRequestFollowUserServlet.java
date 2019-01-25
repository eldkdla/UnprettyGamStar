package com.gamstar.user.controller;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class InsertRequestFollowUserServlet
 */
@WebServlet("/view/allowrequestfollowuser")
public class AllowRequestFollowUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllowRequestFollowUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(request.getSession().getAttribute("userNo")!=null&&request.getParameter("uu")==null){
			
		Connection conn=getConnection();
		
		int myUserNo=(int)request.getSession().getAttribute("userNo");
		User user=new User();
		user.setNo(Integer.parseInt(request.getParameter("requestFollowUserNo")));
		
		//insert팔로우 서비스,다오 다시 안쓰려고 순서 바꿔줌
		int temp=user.getNo();
		user.setNo(myUserNo);
		myUserNo=temp;
		
		int result=new UserService().insertFollow(conn,user,myUserNo);
		
		//insert팔로우 서비스,다오 다시 안쓰려고 순서 바꿔줌
		temp=user.getNo();
		user.setNo(myUserNo);
		myUserNo=temp;
		result=new UserService().deleteRequestFollowuser(conn, user,myUserNo);
		
		close(conn);
		
		}
		else{
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
