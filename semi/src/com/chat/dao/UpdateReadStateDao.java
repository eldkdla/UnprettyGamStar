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
	//ResultSet rs=null;
	public UpdateReadStateDao() {
		String fileName=CreateChatroomNoDao.class.getResource("./chatquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public int updateReadState(Connection conn, int chatNo) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateReadState");
		int result=0;
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
		}
		System.out.println("result : "+result);
		return result;
		
	}
}
