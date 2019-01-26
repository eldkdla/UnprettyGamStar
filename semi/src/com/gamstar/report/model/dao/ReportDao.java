package com.gamstar.report.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import com.gamstar.admin.report.model.vo.ReportBoard;
import com.gamstar.admin.report.model.vo.ReportBoardMedia;

public class ReportDao {
	private Properties prop;
	
	public ReportDao() {
		prop = new Properties();
		String fileName = ReportDao.class.getResource("./reportquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertReportBoardTypeNewspeed(Connection conn, ReportBoard reportBoard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReportBoardTypeNewspeed");
		int result = 0;
		
		/*
		 * INSERT INTO TB_REPORT_NEWSPEED VALUES(SEQ_REPPORT_NO.NEXTVAL,?,DEFAULT,?,?,DEFAULT,?)
1:신고당한사람NO 2:신고 당한 게시글 NO 3:신고 글 내용 4:신고한 사람 NO
		 */
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportBoard.getReportBoardTargetNo());
			pstmt.setInt(2, reportBoard.getReportBoardLink());
			pstmt.setString(3, reportBoard.getReportBoardContent());
			pstmt.setInt(4, reportBoard.getReportBoardWriterNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertReportBoardTypeUser(Connection conn, ReportBoard reportBoard){
		PreparedStatement pstmt= null;
		String sql = prop.getProperty("insertReportBoardTypeUser");
		int result =0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, reportBoard.getReportBoardTargetNo());
			pstmt.setInt(2, reportBoard.getReportBoardTargetNo());
			pstmt.setString(3, reportBoard.getReportBoardContent());
			pstmt.setInt(4, reportBoard.getReportBoardWriterNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}

	public int insertReportBoardMediaTypeNewspeed(Connection conn, ReportBoardMedia reportBoardMedia) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReportBoardTypeNewspeedMedia");
		int result = 0;
		
		//insertReportBoardTypeNewspeedMedia = INSERT INTO TB_REPORT_NEWSPEED_MEDIA VALUES((select max(report_no),?,?,?)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportBoardMedia.getReportBoardMediaIndex());
			pstmt.setString(2, reportBoardMedia.getReportBoardMediaPathRe());
			pstmt.setString(3, reportBoardMedia.getReportBoardMediaPathRe());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	

}
