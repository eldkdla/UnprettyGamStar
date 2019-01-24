package com.chat.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import com.chat.model.vo.ChatLog;

public class ChatLogDao {

	
	ResultSet rs=null;
	ResultSet rs2=null;

	Properties prop=new Properties();
	ChatLog log=new ChatLog();
	ArrayList<ChatLog> logarr=new ArrayList<>();
	
	public ChatLogDao() {
		String fileName=CreateChatroomNoDao.class.getResource("./chatquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}
	public ArrayList<ChatLog> callChatLog(Connection conn, int chatNo, int myNo) {
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;

		System.out.println("채팅로그dao");
		String sql=prop.getProperty("callChatLog");
		int a=0;
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, chatNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				log=new ChatLog();/*
				String sql2=prop.getProperty("callMyUserNo");
				pstmt2=conn.prepareStatement(sql2);
				pstmt2.setString(1, myId);
				rs2=pstmt2.executeQuery();
				if(rs2.next()) {
					//System.out.println(rs2.getInt("user_no"));
*/					
				log.setMyNo(myNo);
				
				log.setChatNo(chatNo);
				log.setUserNo(rs.getInt("user_no"));
				log.setChatroomMessage(rs.getString("chatroom_message"));
				log.setSend_date(rs.getTimestamp("send_date"));
				//System.out.println(log);
				logarr.add(log);
				a=1;
			}
			if(a==0) {
				log=new ChatLog();
				log.setChatNo(0);
				log.setUserNo(0);
				log.setChatroomMessage("");
				log.setSend_date(new Timestamp(0));
				//System.out.println(log);
				logarr.add(log);
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return logarr;
	}
}
