package com.gamstar.report.model.dao;

import java.io.FileReader;
import java.util.Properties;
import java.sql.*;
import static common.JDBCTemplate.*;

import com.gamstar.admin.report.model.vo.ReportBoard;
import com.gamstar.user.model.dao.UserDao;

public class ReportDao {
	private Properties prop;
	
	public ReportDao() {
		prop = new Properties();
		String fileName = UserDao.class.getResource("./reportquery.properties").getPath();
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
			pstmt.setInt(2, reportBoard.getReportBoardLink());
			pstmt.setInt(4, reportBoard.getReportBoardWriterNo());
			pstmt.setInt(1, reportBoard.getReportBoardTargetNo());
			pstmt.setString(3, reportBoard.getReportBoardContent());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
