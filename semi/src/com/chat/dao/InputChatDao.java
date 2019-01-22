package com.chat.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class InputChatDao {
	Properties prop=new Properties();
	ResultSet rs=null;
	public InputChatDao() {
		String fileName=CreateChatroomNoDao.class.getResource("./chatquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public int inputchat(Connection conn, String chatText, int chatNo, int myNo) {
		System.out.println(chatText);
		System.out.println(chatNo);
		int result=0;
		if(chatText!=null) {
			PreparedStatement pstmt=null;
			PreparedStatement pstmt2=null;

			String sql=prop.getProperty("callMyUserNo");
			
			try 
			{
				/*pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				rs=pstmt.executeQuery();
				if(rs.next()) {*/
					System.out.println("에러 내숫자"+myNo);
					sql=prop.getProperty("insertChat");
					pstmt2=conn.prepareStatement(sql);
					pstmt2.setInt(1, chatNo);
					pstmt2.setInt(2, myNo);
					pstmt2.setString(3, chatText);
					System.out.println(chatText);
					result=pstmt2.executeUpdate();
				
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				close(pstmt2);
			}
		}
		return result;
	}
}
