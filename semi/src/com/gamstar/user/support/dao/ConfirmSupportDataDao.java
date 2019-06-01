package com.gamstar.user.support.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import com.gamstar.user.support.model.vo.ConfirmSupportData;

public class ConfirmSupportDataDao {
	ResultSet rs=null;
	Properties prop=new Properties();
	ArrayList<ConfirmSupportData> csArr=new ArrayList<ConfirmSupportData>();
	public ConfirmSupportDataDao() {
		String fileName=ConfirmSupportDataDao.class.getResource("./supportUserQuery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}
	
	public ArrayList<ConfirmSupportData> callConfirmSupportData(Connection conn, int myNo) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("callConfirmSupportData");
		ConfirmSupportData cs=new ConfirmSupportData();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				cs=new ConfirmSupportData();
				cs.setSupportNo(rs.getInt("supportbbs_no"));
				cs.setSupportTitle(rs.getString("supportbbs_title"));
				cs.setSupportDate(rs.getDate("supportbbs_date"));
				cs.setSupportState(rs.getInt("supportbbs_root_no"));
				csArr.add(cs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			close(pstmt);
		}
		return csArr;
		
	}
}
