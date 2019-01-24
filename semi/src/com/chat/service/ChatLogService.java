package com.chat.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.chat.dao.ChatLogDao;
import com.chat.model.vo.ChatLog;
public class ChatLogService {
	public ArrayList<ChatLog> callChatLog(int chatNo, int myNo) {
		Connection conn=getConnection();
		ArrayList<ChatLog> logarr=new ArrayList<ChatLog>();
		logarr=new ChatLogDao().callChatLog(conn, chatNo, myNo);
		close(conn);
		return logarr;
	}
}
