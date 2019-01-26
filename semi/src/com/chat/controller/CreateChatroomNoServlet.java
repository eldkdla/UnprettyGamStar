package com.chat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.chat.dao.FriendListDao;
import com.chat.service.CreateChatroomNoService;

/**
 * Servlet implementation class PreviewChatServlet
 */
@WebServlet("/preview")
public class CreateChatroomNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateChatroomNoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] otherUserNo=request.getParameterValues("name[]");
		for(int i=0;i<otherUserNo.length;i++) {
			System.out.println(otherUserNo[i]);
		}
		String previewMsg="";
		//int myNo=(int)request.getSession().getAttribute("userNo");;
		int myNo=1;
		int chatroomNo=new CreateChatroomNoService().callPreviewMessage(otherUserNo,myNo);
		System.out.println("서블릿프리뷰:"+previewMsg);
		JSONArray previewMessage=new JSONArray();
		previewMessage.add(previewMsg);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(chatroomNo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
