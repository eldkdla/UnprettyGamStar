package com.gamstar.controller;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamstar.model.service.NewspeedService;
import com.gamstar.model.service.UserService;
import com.gamstar.model.vo.NewspeedMedia;
import com.gamstar.model.vo.User;

/**
 * Servlet implementation class MypageUser
 */
@WebServlet("/view/profile")
public class MyProfilePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfilePageServlet() {
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
		
		int userNo=0;
		int myNo=(int)request.getSession().getAttribute("userNo");
		
		if(request.getParameter("uu")==null){ //내 페이지 접속
			userNo=myNo;
		}else{ // 다른사람페이지 접속
			userNo=Integer.parseInt(request.getParameter("uu"));
		}
			
		User user= new User();
		user.setNo(userNo);
		Connection conn = getConnection();
		
		//유저정보 가져오기
		User userData=new UserService().selectUser(conn,user);
		request.setAttribute("userData", userData);
		
		if(userData.getState()==1){ //유저가 정지상태이면 내 페이지로 이동
			response.sendRedirect("profile");
		}
		//게시글(다중) 정보 가져오기
		ArrayList<NewspeedMedia> content1DataArray=new NewspeedService().selectContent1(conn,user);
		request.setAttribute("content1DataArray", content1DataArray);
		
		//저장된 게시물 정보 가져오기
		ArrayList<NewspeedMedia> storageContentDataArray=new NewspeedService().selectStorageContent(conn,user);
		request.setAttribute("storageContentDataArray", storageContentDataArray);
		
		//태그된 게시물 정보 가져오기
		ArrayList<NewspeedMedia> tagContentDataArray=new NewspeedService().selectTagContent(conn,user);
		request.setAttribute("tagContentDataArray", tagContentDataArray);
		
		//팔로워정보 가져오기
		ArrayList<User> followerDataArray=new UserService().selectFollower(conn,user);
		request.setAttribute("followerDataArray", followerDataArray);
		
		//팔로우정보 가져오기
		ArrayList<User> followDataArray=new UserService().selectFollow(conn,user);
		request.setAttribute("followDataArray", followDataArray);
		
		//차단정보 가져오기
		ArrayList<User> blockDataArray=new UserService().selectBlock(conn,user);
		request.setAttribute("blockDataArray", blockDataArray);
		
		//상대방페이지일때 팔로우 되어있는지 확인하기
		boolean isFollowed=new UserService().isFollowed(conn,user,myNo);
		request.setAttribute("isFollowed", isFollowed);
		
		close(conn);
		
		//내정보창으로 정보보내기
		RequestDispatcher rd = request.getRequestDispatcher("/view/profile.jsp");
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
