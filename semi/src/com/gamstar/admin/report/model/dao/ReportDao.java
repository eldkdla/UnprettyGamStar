package com.gamstar.admin.report.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.gamstar.admin.report.model.vo.ReportBoard;
import com.gamstar.admin.report.model.vo.ReportBoardMedia;

public class ReportDao {
private Properties prop=new Properties();
	
	public ReportDao() {
		
		try {
			String fileName=ReportDao.class.getResource("./report-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("report-query");
		}
	}
	
	public List<ReportBoard> selectReportList(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		ArrayList<ReportBoard> list=new ArrayList();
		String sql=prop.getProperty("selectReportList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				ReportBoard r=new ReportBoard();
				r.setReportBoardNo(rs.getInt("report_no"));
				r.setReportBoardType(rs.getInt("report_type"));
				r.setReportBoardLink(rs.getInt("report_link"));
				r.setReportBoardWriterNo(rs.getInt("user_no"));
				r.setReportBoardTargetNo(rs.getInt("target_user_no"));
				r.setReportBoardTargetId(rs.getString("user_id"));
				r.setReportBoardContent(rs.getString("report_content"));
				r.setReportBoardDate(rs.getDate("report_date"));
				r.setReportEndResult(rs.getInt("report_result"));
				
				list.add(r);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectReportCount(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectReportCount");
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("cnt");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	//미디어 같이 가져오기
	public List<ReportBoardMedia> selectMedia(Connection conn, int boardNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		List<ReportBoardMedia> list=new ArrayList();
		String sql=prop.getProperty("selectReportMedia");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				ReportBoardMedia r=new ReportBoardMedia();
				r.setReportBoardNo(rs.getInt("report_no"));
				r.setReportBoardMediaIndex(rs.getInt("REPORTBBS_MEDIA_INDEX"));
				r.setReportBoardMediaPathOri(rs.getString("REPORTBBS_MEDIA_PATH_ORI"));
				r.setReportBoardMediaPathRe(rs.getString("REPORTBBS_MEDIA_PATH_RE"));
				
				list.add(r);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("reportDao List가져오기 오류");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	//정지
	public int setUserStop(Connection conn,int userNo,int stopDays)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("setUserStop");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, stopDays);
			pstmt.setInt(2, userNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("userStopDao Err");
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}
	
	//정지-로그입력
	public int inputReportLog(Connection conn,int reportBoardNo,int stopDays)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("inputReportLog");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, reportBoardNo);
			pstmt.setInt(2, stopDays);
			result=pstmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("insert log err(dao)");
		}
		finally
		{
			close(pstmt);
		}
		
		return result;
	}
}
