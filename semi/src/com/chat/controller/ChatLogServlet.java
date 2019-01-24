package com.chat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.chat.model.vo.ChatLog;
import com.chat.service.ChatLogService;

/**
 * Servlet implementation class ChatLogServlet
 */
@WebServlet("/callChatLog")
public class ChatLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String chatNo=request.getParameter("chatNo");
		String myId="user10";
		int myNo=(int)request.getSession().getAttribute("userNo");;
		System.out.println("에러??"+chatNo);
		int chatno=Integer.parseInt(chatNo);
		System.out.println(chatNo);
		ArrayList<ChatLog> logarr=new ArrayList<ChatLog>();
		logarr=new ChatLogService().callChatLog(chatno,myNo);
		JSONArray chatlogarr= new JSONArray();
		SimpleDateFormat datefm=new SimpleDateFormat("HH:mm");
		if(logarr.get(0).getUserNo()!=0) {
		for(int i=0;i<logarr.size();i++) {
			JSONObject chatlog=new JSONObject();
			System.out.println("시이가안"+datefm.format(logarr.get(i).getSend_date()));
			/*System.out.println("=========채팅로그서블릿==========");
			System.out.println("채팅방번호 : "+logarr.get(i).getChatNo());
			System.out.println("내 유저번호 : "+logarr.get(i).getMyNo());
			System.out.println("유저번호 : "+logarr.get(i).getUserNo());
			System.out.println("메세지 : "+logarr.get(i).getChatroomMessage());
			System.out.println("날짜 : "+logarr.get(i).getSend_date());*/
			chatlog.put("chatRoomNo", logarr.get(i).getChatNo());
			chatlog.put("myNo", logarr.get(i).getMyNo());
			chatlog.put("chatUserNo", logarr.get(i).getUserNo());
			chatlog.put("chatMessage", logarr.get(i).getChatroomMessage());
			chatlog.put("chatDate", datefm.format(logarr.get(i).getSend_date()));
			chatlogarr.add(chatlog);
		}
		/*for(int j=0;j<chatlogarr.size();j++) {
			System.out.println("====채팅로그서블릿제이슨======");
			System.out.println(chatlogarr.get(j));
		}*/
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(chatlogarr);}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
