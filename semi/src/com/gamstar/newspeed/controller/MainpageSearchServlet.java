package com.gamstar.newspeed.controller;

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
import com.google.gson.Gson;

/**
 * Servlet implementation class MainpageSearchServlet
 */
@WebServlet("/mainsearchservlet")
public class MainpageSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainpageSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
	      response.setCharacterEncoding("UTF-8");
	      
	      String searchStr = request.getParameter("searchStr");
	      System.out.println("전달완료 : "+searchStr);
	      
	      List<User> userList = new UserService().selectSearchUser(searchStr); //유저 불러오기
	      JSONObject searchUser = new JSONObject();
	      JSONArray jsonArr = new JSONArray();
	      System.out.println("유저서치 값 : "+userList.size());
	      
	      for(int i =0; i<userList.size(); i++) {
	         
	         JSONObject data2 = new JSONObject();
	         data2.put("userNo",userList.get(i).getNo());
	         data2.put("userName",userList.get(i).getName());
	         data2.put("profilePhoto",userList.get(i).getProfilePhoto());

	         searchUser.put("follow"+i, data2);
	      }
	      
	      jsonArr.add(searchUser);
	      System.out.println(searchUser);
	      
	      response.setContentType("application/json;Charset=UTF-8");
	      new Gson().toJson(jsonArr,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
