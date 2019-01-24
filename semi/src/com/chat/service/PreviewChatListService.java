package com.chat.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.chat.dao.PreviewChatListDao;
import com.chat.model.vo.PreiviewChatList;

import static common.JDBCTemplate.*;

public class PreviewChatListService {
	public ArrayList<PreiviewChatList> callPreviewChatList(int myNo) {
		Connection conn=getConnection();
		ArrayList<PreiviewChatList> previewlist=new ArrayList<PreiviewChatList>();
		previewlist=new PreviewChatListDao().previewChatList(conn,myNo);
		for(int i=0;i<previewlist.size();i++) {
			System.out.println("service");
			System.out.println("채팅방 번호 :" + previewlist.get(i).getChatroomNo());
			System.out.println("상대방 번호 : "+previewlist.get(i).getOtherNo());
			System.out.println("상대방 사진 : "+previewlist.get(i).getUserProfilePhoto());
			System.out.println("상대방 이름 : "+previewlist.get(i).getUserName());
			System.out.println("메세지 : "+previewlist.get(i).getChatPreview());
			System.out.println("메세지 시간 : "+previewlist.get(i).getMessageTime());
			System.out.println("메세지 보낸 유저 번호 : "+previewlist.get(i).getMessageUserNo());
		}
		close(conn);
		return previewlist;
		
	}
}
