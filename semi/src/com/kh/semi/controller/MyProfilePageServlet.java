package com.kh.semi.controller;

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

import com.kh.semi.model.service.UserService;
import com.kh.semi.model.vo.User;

/**
 * Servlet implementation class MypageUser
 */
@WebServlet("/view/myprofile")
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
		
		HttpSession se = request.getSession();
		se.setAttribute("userNo", 6);
		
		int userNo=0;
		int myNo=(int)se.getAttribute("userNo");
		
		if(request.getParameter("uu")==null){ //내 페이지 접속
			userNo=myNo;
		}else{ // 다른사람페이지 접속
			userNo=Integer.parseInt(request.getParameter("uu"));
		}
			
		User u= new User();
		u.setNo(userNo);
		Connection conn = getConnection();
		
		//유저정보 가져오기
		User userData=new UserService().selectUser(conn,u);
		request.setAttribute("userData", userData);
		
		if(userData.getState()==1){ //유저가 정지상태이면 내 페이지로 이동
			response.sendRedirect("myprofile");
		}
		/*//게시글(다중) 정보 가져오기
		ArrayList<User> content1DataArray=new UserService().selectContent1(conn,u);
		request.setAttribute("content1DataArray", content1DataArray);

		//저장된 게시물 정보 가져오기
		ArrayList<User> storageContentDataArray=new UserService().selectStorageContent(conn,u);
		request.setAttribute("storageContentDataArray", storageContentDataArray);
		
		//태그된 게시물 정보 가져오기
		ArrayList<User> tagContentDataArray=new UserService().selectTagContent(conn,u);
		request.setAttribute("tagContentDataArray", tagContentDataArray);*/
		
		//팔로워정보 가져오기
		ArrayList<User> followerDataArray=new UserService().selectFollower(conn,u);
		request.setAttribute("followerDataArray", followerDataArray);
		
		//팔로우정보 가져오기
		ArrayList<User> followDataArray=new UserService().selectFollow(conn,u);
		request.setAttribute("followDataArray", followDataArray);
		
		//차단정보 가져오기
		ArrayList<User> blockDataArray=new UserService().selectBlock(conn,u);
		request.setAttribute("blockDataArray", blockDataArray);
		
		//상대방페이지일때 팔로우 되어있는지 확인하기
		boolean isFollowed=new UserService().isFollowed(conn,u,myNo);
		request.setAttribute("isFollowed", isFollowed);
		
		close(conn);

		
		//내정보창으로 정보보내기
		RequestDispatcher rd = request.getRequestDispatcher("/view/myProfilePage.jsp");
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
