package com.gamstar.user.support.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import com.gamstar.user.support.model.vo.AnswerSupportData;
import com.gamstar.user.support.model.vo.ConfirmSupportData;

public class AnswerSupportDataDao {

	ResultSet rs=null;
	ResultSet rs2=null;

	Properties prop=new Properties();
	AnswerSupportData as=new AnswerSupportData();
	public AnswerSupportDataDao() {
		String fileName=AnswerSupportDataDao.class.getResource("./supportUserQuery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}
	public AnswerSupportData AnswerSupportDataDao(Connection conn, int supportNo) {
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;

		String sql=prop.getProperty("callConfirmSupportContent");
		
		try {
			as=new AnswerSupportData();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, supportNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				as.setqContent(rs.getString("supportbbs_content"));
				as.setqDate(rs.getDate("supportbbs_date"));
				as.setTitle(rs.getString("supportbbs_title"));
				System.out.println(rs.getString("supportbbs_content"));
			}
			sql=prop.getProperty("callConfirmSupportAnswer");
			pstmt2=conn.prepareStatement(sql);
			System.out.println("-1: "+supportNo*(-1));
			int answerNo=supportNo*(-1);
			pstmt2.setInt(1, answerNo);
			rs2=pstmt2.executeQuery();
			if(rs2.next()) {
				as.setaContent(rs2.getString("supportbbs_content"));
				as.setaDate(rs2.getDate("supportbbs_date"));
			}
			else {
				as.setaContent("");
				as.setaDate(new Date(0));
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
		return as;
		
	}
}
