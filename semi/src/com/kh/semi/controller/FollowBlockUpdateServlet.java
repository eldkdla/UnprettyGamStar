package com.kh.semi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.model.service.UserService;
import com.kh.semi.model.vo.User;

/**
 * Servlet implementation class FollowBlockUpdateServlet
 */
@WebServlet("/view/updatefollowblock")
public class FollowBlockUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowBlockUpdateServlet() {
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
		
		HttpSession se = request.getSession();
		
		int myname=(int)se.getAttribute("myname");
		int blockFllowId=Integer.parseInt(request.getParameter("uu"));
			
		User u= new User();
		u.setUserNo(blockFllowId);
		
		int result=0;
		
		if(request.getParameter("follow")!=null){
		System.out.println(request.getParameter("follow"));
			if(request.getParameter("follow").equals("팔로우됨")){
				//상대를 내 팔로우 목록에 추가 ,나를 상대 팔로워 목록에 추가
				result=new UserService().insertFollow(u,myname);
			}
			else{
				//상대를 내 팔로우 목록에 삭제 ,나를 상대 팔로워 목록에 삭제
				result=new UserService().deleteFollow(u,myname);
			}
		}
		else if(request.getParameter("block")!=null){
		System.out.println(request.getParameter("block"));
			if(request.getParameter("block").equals("차단됨")){
				//내 차단목록에 상대를 추가 (db에서 트리거로 서로 팔로우,팔로워 삭제)
				result=new UserService().insertBlockUser(u,myname);
				response.sendRedirect("myprofile");
			}
			else{
				//내 차단목록에서 상대를 삭제		
				result=new UserService().deleteBlockUser(u,myname);
				response.sendRedirect("myprofile");
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
