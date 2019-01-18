package com.gamstar.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;

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
		
		if(request.getSession().getAttribute("userNo")!=null){

			int userNo=(int)request.getSession().getAttribute("userNo");
			int blockFllowNo=Integer.parseInt(request.getParameter("uu"));
				
			User user= new User();
			user.setNo(blockFllowNo);
			
			int result=0;
			
			if(request.getParameter("follow")!=null){
			System.out.println(request.getParameter("follow"));
				if(request.getParameter("follow").equals("팔로우됨")){
					//상대를 내 팔로우 목록에 추가 ,나를 상대 팔로워 목록에 추가
					result=new UserService().insertFollow(user,userNo);
				}
				else{
					//상대를 내 팔로우 목록에 삭제 ,나를 상대 팔로워 목록에 삭제
					result=new UserService().deleteFollow(user,userNo);
				}
			}
			else if(request.getParameter("block")!=null){
			System.out.println(request.getParameter("block"));
				if(request.getParameter("block").equals("차단됨")){
					//내 차단목록에 상대를 추가 (db에서 트리거로 서로 팔로우,팔로워 삭제)
					result=new UserService().insertBlockUser(user,userNo);
					response.sendRedirect("profile");
				}
				else{
					//내 차단목록에서 상대를 삭제		
					result=new UserService().deleteBlockUser(user,userNo);
					response.sendRedirect("profile");
				}
			}
		
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
