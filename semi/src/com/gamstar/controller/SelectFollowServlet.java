package com.gamstar.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.model.service.UserService;
import com.gamstar.model.vo.User;
import com.google.gson.Gson;

import static common.JDBCTemplate.*;

/**
 * Servlet implementation class SelectFollowServlet
 */
@WebServlet("/view/selectfollow")
public class SelectFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectFollowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		User user=new User();
		user.setNo(Integer.parseInt(request.getParameter("userNo")));
		Connection conn=getConnection();
		
		if(request.getParameter("isfollow").equals("follower")){
			ArrayList<User> followerDataArray=new UserService().selectFollower(conn,user);
			
			close(conn);
			response.setContentType("application/json;charset=UTF-8");
			new Gson().toJson(followerDataArray,response.getWriter());
		}
		else if(request.getParameter("isfollow").equals("follow")){
			ArrayList<User> followDataArray=new UserService().selectFollow(conn,user);
			
			close(conn);
			response.setContentType("application/json;charset=UTF-8");
			new Gson().toJson(followDataArray,response.getWriter());
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
