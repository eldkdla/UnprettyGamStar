package com.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.chat.service.FindFriendService;
import com.gamstar.user.model.vo.User;


/**
 * Servlet implementation class FindFriendServlet
 */
@WebServlet("/findFriend")
public class FindFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String inputfriendName=request.getParameter("inputFriendName");
		//String userId="user10";
		int myNo=(int)request.getSession().getAttribute("userNo");;
		ArrayList<User> arr= new ArrayList<User>();
		arr=new FindFriendService().findFriend(inputfriendName, myNo);
		JSONArray friedlistarr=new JSONArray();
		
		for (int j = 0; j < arr.size(); j++) {
			
			JSONObject friedlist=new JSONObject();
			friedlist.put("name", arr.get(j).getName());
			friedlist.put("profile", arr.get(j).getProfilePhoto());
			friedlist.put("userNo", arr.get(j).getNo());
			friedlistarr.add(friedlist);
		}
		
		/*for(int i=0;i<friedlistarr.size();i++) {
			System.out.println(friedlistarr.get(i));
		}*/
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
