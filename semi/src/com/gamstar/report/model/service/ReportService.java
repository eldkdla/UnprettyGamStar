package com.gamstar.report.model.service;

import com.gamstar.admin.report.model.vo.*;
import com.gamstar.report.model.dao.ReportDao;

import common.JDBCTemplate;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

public class ReportService {
	
	private ReportDao reportDAO;
	
	public ReportService() {
		reportDAO = new ReportDao();
		
		
	}
	
	public int insertReportBoardTypeNewspeed(ReportBoard reportBoard, List<ReportBoardMedia> reportBoardMediaList) {
		Connection conn = JDBCTemplate.getConnection();
		int result = reportDAO.insertReportBoardTypeNewspeed(conn,reportBoard);
		
		if (result < 1) {
			rollback(conn);
			return result;
		} 
		
		for (int i = 0; i < reportBoardMediaList.size(); i++) {
		
			result = reportDAO.insertReportBoardMediaTypeNewspeed(conn, reportBoardMediaList.get(i));
			
			if (result < 1) {
				rollback(conn);
				return result;
			}
		}
		
		commit(conn);
		close(conn);
		
		return 1;
	}
	
	public int insertReportBoardTypeUser(ReportBoard reportBoard, List<ReportBoardMedia> reportBoardMediaList){
		Connection conn =JDBCTemplate.getConnection();
		
		int result=reportDAO.insertReportBoardTypeUser(conn,reportBoard);
		
		if(result<1){
			rollback(conn);
			return result;
		}
		
		for (int i = 0; i < reportBoardMediaList.size(); i++) {
			
			result = reportDAO.insertReportBoardMediaTypeNewspeed(conn, reportBoardMediaList.get(i));
			
			if (result < 1) {
				rollback(conn);
				return result;
			}
		}
		commit(conn);
		close(conn);
		
		return 1;
	}
	
}
