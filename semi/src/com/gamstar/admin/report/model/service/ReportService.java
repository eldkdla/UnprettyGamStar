package com.gamstar.admin.report.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.gamstar.admin.report.model.dao.ReportDao;
import com.gamstar.admin.report.model.vo.ReportBoard;
import com.gamstar.admin.report.model.vo.ReportBoardMedia;
import com.gamstar.user.model.vo.User;

public class ReportService {
	
	//페이징
		public List<ReportBoard> selectReportList(int cPage, int numPerPage)
		{
			Connection conn=getConnection();
			List<ReportBoard> list= new ReportDao().selectReportList(conn,cPage,numPerPage);
			close(conn);
			return list;
		}
		
		public int selectReportCount()
		{
			Connection conn=getConnection();
			int result=new ReportDao().selectReportCount(conn);
			close(conn);
			return result;
		}
		
		//페이징 하면서 미디어
		public List<ReportBoardMedia> selectReportMediaList(int reportNo)
		{
			Connection conn=getConnection();
			List<ReportBoardMedia> list = new ReportDao().selectMedia(conn,reportNo);
			close(conn);
			return list;
		}
		
		//정지
		public int setUserStop(int userNo, int stopDays, int reportBoardNo)
		{
			Connection conn=getConnection();
			int result=new ReportDao().setUserStop(conn,userNo,stopDays);
			int resultInputLog=0;
			
			if(result>0)
			{
				resultInputLog=new ReportDao().inputReportLog(conn,reportBoardNo,stopDays);
				if(resultInputLog>0)
				{
					commit(conn);
				}
				else
				{
					rollback(conn);
					close(conn);
					return resultInputLog;
				}
			}
			else
			{
				rollback(conn);
			}
			
			close(conn);
			return result;
		}
		
		//여러개정지
		public int setSelectUserStop(int[] userNo, int stopDays, int[] reportBoardNo)
		{
			Connection conn=getConnection();
			
			int[] result=new int[userNo.length];
			for(int i=0; i<userNo.length;i++)
			{
				result[i]=new ReportDao().setUserStop(conn,userNo[i],stopDays);
			}
			
			int allResult=0;
			
			for(int i=0; i<userNo.length;i++)
			{
				if(result[i]==0)
				{
					rollback(conn);
					close(conn);
					return allResult;
				}
				if(i==(userNo.length-1))
				{
					int resultInputLog=0;
					
					for(int j=0; j<userNo.length;j++)
					{
						resultInputLog=new ReportDao().inputReportLog(conn,reportBoardNo[j],stopDays);
						
						if(resultInputLog==0)
						{
							rollback(conn);
							close(conn);
							return allResult;
						}
						if(j==(userNo.length-1))
						{
							allResult=1;
							commit(conn);
							close(conn);
							return allResult;
						}
					}
					
				}
			}
			close(conn);
			return allResult;
		}
		
		//취소
		public int cancelReport(int[] reportBoardNo)
		{
			Connection conn=getConnection();
			
			int[] result=new int[reportBoardNo.length];
			for(int i=0; i<reportBoardNo.length;i++)
			{
				result[i]=new ReportDao().cancelReport(conn,reportBoardNo[i]);
			}
			
			int allResult=0;
			int count=0;
			
			for(int i=0; i<result.length;i++)
			{
				if(result[i]>0)
				{
					count++;
				}
			}
			if(count==result.length)
			{
				allResult=1;
			}
			
			close(conn);
			return allResult;
		}
		
		//검색
		public List<ReportBoard> searchReportList(int cPage, int numPerPage, String searchType, String searchKeyword)
		{
			Connection conn=getConnection();
			List<ReportBoard> list= null;
			switch(searchType)
			{
			case "id" : list=new ReportDao().selectIdReportList(conn,cPage,numPerPage,searchKeyword);break;
//			case "type" :list=new ReportDao().selectIdReportList(conn,cPage,numPerPage,searchKeyword);break;
			}
			
			close(conn);
			return list;
		}
		public int selectReportListCount(String searchType, String searchKeyword)
		{
			Connection conn=getConnection();
			int result=new ReportDao().selectIdReportCount(conn,searchKeyword);
			close(conn);
			return result;
		}
}
