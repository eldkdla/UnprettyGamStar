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

import com.chat.model.vo.PreiviewChatList;
import com.chat.service.PreviewChatListService;

/**
 * Servlet implementation class PreviewChatLisitServlet
 */
@WebServlet("/previewlist")
public class PreviewChatLisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewChatLisitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String userId="user10";
		int myNo=(int)request.getSession().getAttribute("userNo");;
		ArrayList<PreiviewChatList> previewlist=new ArrayList<PreiviewChatList>();
		previewlist=new PreviewChatListService().callPreviewChatList(myNo);
		/*for(int i=0;i<previewlist.size();i++) {
			System.out.println("서블릿");
			System.out.println("채팅방 번호 :" + previewlist.get(i).getChatroomNo());
			System.out.println("상대방 번호 : "+previewlist.get(i).getOtherNo());
			System.out.println("상대방 사진 : "+previewlist.get(i).getUserProfilePhoto());
			System.out.println("상대방 이름 : "+previewlist.get(i).getUserName());
			System.out.println("메세지 : "+previewlist.get(i).getChatPreview());
			System.out.println("메세지 시간 : "+previewlist.get(i).getMessageTime());
			System.out.println("메세지 보낸 유저 번호 : "+previewlist.get(i).getMessageUserNo());
		}*/
		JSONArray previewChatarr=new JSONArray();
		SimpleDateFormat datefm=new SimpleDateFormat("HH:mm");
		for (int j = 0; j < previewlist.size(); j++) {
			datefm.format(previewlist.get(j).getMessageTime());
			JSONObject previewChat=new JSONObject();
			previewChat.put("chatNo", previewlist.get(j).getChatroomNo());
			previewChat.put("otherNo", previewlist.get(j).getOtherNo());
			previewChat.put("otherProfile", previewlist.get(j).getUserProfilePhoto());
			previewChat.put("otherName", previewlist.get(j).getUserName());
			previewChat.put("previewMessage", previewlist.get(j).getChatPreview());
			previewChat.put("previewMessageTime", datefm.format(previewlist.get(j).getMessageTime()));
			previewChat.put("previewMessageUserNo", previewlist.get(j).getMessageUserNo());
			previewChat.put("readState", previewlist.get(j).getReadState());
			previewChatarr.add(previewChat);
		}
		
		/*for(int i=0;i<previewChatarr.size();i++) {
			System.out.println("==================서블릿 채팅목록 불러오기=================");
			System.out.println(previewChatarr.get(i));
		}*/
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(previewChatarr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
