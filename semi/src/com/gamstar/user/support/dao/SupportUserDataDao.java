package com.gamstar.user.support.dao;

import static common.JDBCTemplate.*;

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
		String fileName=SupportUserDataDao.class.getResource("./supportUserQuery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e){
			e.printStackTrace();
			
		}
		
	}
	public void insertSupportUserData(Connection conn,SupportUserData userData, List fileNamelist,List fileOriNamelist, int[] type) {
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		

		int result=0;
		int result2=0;
		//시퀀스 알아오기
		String sql=prop.getProperty("callSupportSequence");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				rs.getInt("NEXTVAL");
				sql=prop.getProperty("insertUserSupportData");
				pstmt2=conn.prepareStatement(sql);
				pstmt2.setInt(1, rs.getInt("NEXTVAL"));
				pstmt2.setString(2, userData.getSupportTitle());
				pstmt2.setString(3, userData.getSupportContent());
				pstmt2.setInt(4, userData.getUserNo());
				result=pstmt2.executeUpdate();
				if(result==0) {
					rollback(conn);
				}
				else {
					commit(conn);
				}
				for(int i=0;i<fileNamelist.size();i++) {
				sql=prop.getProperty("insertUserSupportMedia");
				pstmt3=conn.prepareStatement(sql);
				pstmt3.setInt(1, rs.getInt("NEXTVAL"));
				pstmt3.setInt(2, i+1);
				pstmt3.setInt(3, type[i]);
				pstmt3.setString(4, "upload/support/"+fileOriNamelist.get(i));
				pstmt3.setString(5, "upload/support/"+fileNamelist.get(i));
				result2=pstmt3.executeUpdate();
				if (result2==0){
					rollback(conn);
				}
				else {
					commit(conn);
				}
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(pstmt2);
			close(pstmt3);
		}
	}
}
