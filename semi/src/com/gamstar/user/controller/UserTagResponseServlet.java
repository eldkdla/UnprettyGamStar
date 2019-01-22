package com.gamstar.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class UserTagResponseServlet
 */
@WebServlet("/user/usertag")
public class UserTagResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTagResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserService userService = new UserService();
		
		
		//int userNo = Integer.parseInt(request.getParameter("userNo"));
		int userNo = 1;
		String userName = request.getParameter("userName");
		List<User> userList = userService.selectFollowLike(userNo, userName);
		JSONArray userListJSONArray = new JSONArray();
		userListToJSONArray(userListJSONArray,userList);		
		response.getWriter().println(userListJSONArray);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void userListToJSONArray(JSONArray userListJSONArray, List<User> userList) {
		for (int i = 0; i < userList.size(); i++) {
			JSONObject json = new JSONObject();
			json.put("userNo", userList.get(i).getNo());
			json.put("profilePhoto", userList.get(i).getProfilePhoto());
			json.put("name", userList.get(i).getName());
			
			userListJSONArray.add(json);
		}
	}

}
