package com.chat.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class UpdateReadStateDao {
	Properties prop=new Properties();
	ResultSet rs=null;
	public UpdateReadStateDao() {
		String fileName=CreateChatroomNoDao.class.getResource("./chatquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public int updateReadState(Connection conn, int chatNo, int myNo) {
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		int chatUserNo=0;
		String sql=prop.getProperty("callPreviewChatUserNo");
		try {
			pstmt2=conn.prepareStatement(sql);
			pstmt2.setInt(1, chatNo);
			rs=pstmt2.executeQuery();
			if(rs.next()) {
				chatUserNo=rs.getInt("user_no");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		int result=0;
		
		if(chatUserNo!=myNo) {
		 sql=prop.getProperty("updateReadState");
		try 
		{
			System.out.println(sql);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, chatNo);
			System.out.println("chat NO:" + chatNo);
			result=pstmt.executeUpdate();
			System.out.println("result : "+result);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}}
		System.out.println("result : "+result);
		return result;
		
	}
}
