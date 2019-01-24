package com.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.chat.service.FriendListService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gamstar.user.model.vo.User;

/**
 * Servlet implementation class AddFriendList
 */
@WebServlet("/addfriendlist")
public class AddFriendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<User> arr=new ArrayList<User>();
		int userNo=1;
		arr=new FriendListService().callFriendList(userNo);
		
		
		JSONArray friedlistarr=new JSONArray();
		
		for (int j = 0; j < arr.size(); j++) {
			
			JSONObject friedlist=new JSONObject();
			friedlist.put("name", arr.get(j).getName());
			friedlist.put("profile", arr.get(j).getProfilePhoto());
			friedlist.put("userNo", arr.get(j).getNo());
			friedlistarr.add(friedlist);
		}
		
		for(int i=0;i<friedlistarr.size();i++) {
			System.out.println(friedlistarr.get(i));
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(friedlistarr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
