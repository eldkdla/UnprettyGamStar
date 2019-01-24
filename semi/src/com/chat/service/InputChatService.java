package com.chat.service;
import java.sql.Connection;

import com.chat.dao.InputChatDao;

import static common.JDBCTemplate.*;
public class InputChatService {

	public void inputchat(String chatText, int chatNo, int myNo) {
		Connection conn=getConnection();
		int result=new InputChatDao().inputchat(conn, chatText, chatNo, myNo);
		if(result==0) {
			System.out.println("채팅실패");
			rollback(conn);
		}
		else {
			System.out.println("롤백");

			commit(conn);
		}
		close(conn);
	}
}
