package com.gamstar.user.support.dao;

import static common.JDBCTemplate.getConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import com.chat.dao.CreateChatroomNoDao;
import com.gamstar.user.support.model.vo.SupportUserData;

public class SupportUserDataDao {
	ResultSet rs=null;

	Properties prop=new Properties();
	
	public SupportUserDataDao() {
		String fileName=CreateChatroomNoDao.class.getResource("./supportUserQuery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e){
			e.printStackTrace();
			
		}
		
	}
	public void insertSupportUserData(Connection conn,SupportUserData userData, List fileNamelist,List fileOriNamelist, List fileTypelist) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("");
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
}
