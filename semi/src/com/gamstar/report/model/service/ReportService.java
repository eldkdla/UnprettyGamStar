package com.gamstar.report.model.service;

import com.gamstar.admin.report.model.vo.ReportBoard;
import com.gamstar.report.model.dao.ReportDao;

import common.JDBCTemplate;

import static common.JDBCTemplate.*;

import java.sql.Connection;

public class ReportService {
	
	private ReportDao reportDAO;
	
	public ReportService() {
		reportDAO = new ReportDao();
		
		
	}
	
	public int insertReportBoardTypeNewspeed(ReportBoard reportBoard) {
		Connection conn = JDBCTemplate.getConnection();
		int result = reportDAO.insertReportBoardTypeNewspeed(conn,reportBoard);
		
		if (result < 1) {
			rollback(conn);
		} else {
			commit(conn);
		}
		
		close(conn);
		
		return 1;
	}

}
