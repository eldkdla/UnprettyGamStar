package com.gamstar.user.controller;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class SelectRequestFollowServlet
 */
@WebServlet("/view/selectrequestfollow")
public class SelectRequestFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectRequestFollowServlet() {
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
			
			//관리자페이지면 돌려보내기
			if(request.getParameter("userNo")!=null){
				if((Integer.parseInt(request.getParameter("userNo")))<=0){
					request.setAttribute("msg", "잘못된 접근");
					request.setAttribute("loc", "");
					request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
				}
			}
			Connection conn=getConnection();
			User user=new User();
			user.setNo(Integer.parseInt(request.getParameter("userNo")));
			
			ArrayList<User> requestFollowDataArray=new UserService().selectRequestFollow(conn,user);
			/*ArrayList<User> requestFollowDataArray=new ArrayList<User>();
			for(int i=0;i<requestFollowDataArray1.size();i++){
				if(requestFollowDataArray1.get(i).getIswatch()==1){
					user=new User();
					user.setNo(requestFollowDataArray1.get(i).getNo());
					user.setName(requestFollowDataArray1.get(i).getName());
					user.setProfilePhoto(requestFollowDataArray1.get(i).getProfilePhoto());
					
					requestFollowDataArray.add(user);
				}
			}*/

			close(conn);
			
			response.setContentType("application/json;charset=UTF-8");
			new Gson().toJson(requestFollowDataArray,response.getWriter());
			
			
			
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
