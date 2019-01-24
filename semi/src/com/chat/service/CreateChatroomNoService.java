package com.chat.service;

import java.sql.Connection;

import com.chat.dao.CreateChatroomNoDao;

import static common.JDBCTemplate.*;

public class CreateChatroomNoService {

	public int callPreviewMessage(String[] otherUserNo, int myNo) {
		Connection conn=getConnection(); 
		//String myId="user10";
		int result_chatroomNoArr[]=new CreateChatroomNoDao().callPreviewMessage(conn, myNo, otherUserNo);
		int chatroomNo=0;
		close(conn);
		//return previewMsg;
		if(result_chatroomNoArr[0]==-1000) {
			System.out.println("채팅방 있음");
			chatroomNo=result_chatroomNoArr[1];
			rollback(conn);
		}
		else {
			System.out.println("채팅방 없고 만듬");
			chatroomNo=result_chatroomNoArr[1];
			commit(conn);
		}
		return chatroomNo;
		
	}
}
