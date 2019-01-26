package com.gamstar.admin.support.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.gamstar.admin.report.model.dao.ReportDao;
import com.gamstar.admin.support.model.dao.SupportDao;
import com.gamstar.admin.support.model.vo.SupportBoard;
import com.gamstar.admin.support.model.vo.SupportBoardMedia;

public class SupportService {
	
	public SupportService() {}
	
	//서포트 게시판 페이징 처리
	public List<SupportBoard> selectSupportList(int cPage, int numPerPage)
	{
		Connection conn=getConnection();
		List<SupportBoard> list=new SupportDao().selectSupportList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	
	public int selectSupportCount() 
	{
		Connection conn=getConnection();
		int result=new SupportDao().selectSupportCount(conn);
		close(conn);
		return result;
		
	}
	
	//서포트 게시판 게시글 불러오기
	public SupportBoard selectSupportOne(int no)
	{
		Connection conn=getConnection();
		SupportBoard s=new SupportDao().selectSupportOne(conn,no);
		close(conn);
		return s;
	}
	
	//서포트 게시글의 첨부파일 불러오기
	public List<SupportBoardMedia> selectSupportOneMedia(int no)
	{
		Connection conn=getConnection();
		List<SupportBoardMedia> sMedias= new SupportDao().selectSupportOneMedia(conn,no);
		close(conn);
		return sMedias;
	}

	//서포트 게시판 답변 입력하기
	public int insertAnswer(SupportBoard supportAnswer)
	{
		Connection conn=getConnection();
		int result=new SupportDao().insertAnswer(conn, supportAnswer);
		
		int changeRootResult=0;
		if(result>0)
		{
			changeRootResult=new SupportDao().changeRootResult(conn,supportAnswer.getSupportBoardRootNo(),supportAnswer.getSupportBoardNo());
			if(changeRootResult>0)
			{
				commit(conn);
			}
			else
			{
				rollback(conn);
			}
		}
		else
		{
			rollback(conn);
		}
		
		close(conn);
		
		return changeRootResult;
	}
	//첨부파일 넣기
	
		
	//수정
	//서포트 게시판 답변 입력하기
		public int editAnswer(SupportBoard supportAnswer)
		{
			Connection conn=getConnection();
			int result=new SupportDao().editAnswer(conn, supportAnswer);
			
			if(result>0)
			{
				commit(conn);
			}
			else
			{
				rollback(conn);
			}
			
			close(conn);
			
			return result;
		}
	
	
	//서포트 게시판 검색
	
	public List<SupportBoard> searchIdList(int cPage, int numPerPage, String searchKeyword)
	{
		Connection conn=getConnection();
		List<SupportBoard> list=new SupportDao().searchIdList(conn, cPage, numPerPage, searchKeyword);
		close(conn);
		return list;
	}
	public int selectSupportCountId(String searchKeyword) 
	{
		Connection conn=getConnection();
		int result=new SupportDao().selectSupportCountId(conn, searchKeyword);
		close(conn);
		return result;
		
	}
	
	public List<SupportBoard> searchTitleList(int cPage, int numPerPage, String searchKeyword)
	{
		Connection conn=getConnection();
		List<SupportBoard> list=new SupportDao().searchTitleList(conn, cPage, numPerPage,searchKeyword);
		close(conn);
		return list;
	}
	public int selectSupportCountTitle(String searchKeyword) 
	{
		Connection conn=getConnection();
		int result=new SupportDao().selectSupportCountTitle(conn, searchKeyword);
		close(conn);
		return result;
		
	}
	
	public List<SupportBoard> searchContentList(int cPage, int numPerPage, String searchKeyword)
	{
		Connection conn=getConnection();
		List<SupportBoard> list=new SupportDao().searchContentList(conn, cPage, numPerPage, searchKeyword);
		close(conn);
		return list;
	}
	public int selectSupportCountContent(String searchKeyword) 
	{
		Connection conn=getConnection();
		int result=new SupportDao().selectSupportCountContent(conn, searchKeyword);
		close(conn);
		return result;
		
	}
	
	public List<SupportBoard> searchAllList(int cPage, int numPerPage, String searchKeyword)
	{
		Connection conn=getConnection();
		List<SupportBoard> list=new SupportDao().searchAllList(conn, cPage, numPerPage, searchKeyword);
		close(conn);
		return list;
	}
	public int selectSupportCountAll(String searchKeyword) 
	{
		Connection conn=getConnection();
		int result=new SupportDao().selectSupportCountAll(conn, searchKeyword);
		close(conn);
		return result;
		
	}
	
	//서포트 다음글 번호 가져오기
	public int selectSupportNextNo(int boardNo)
	{
		Connection conn=getConnection();
		int nextNo=new SupportDao().selectSupportNextNo(conn,boardNo);
		close(conn);
		return nextNo;
	}
	
	//서포트 이전글 번호 가져오기
	public int selectSupportPrevNo(int boardNo)
	{
		Connection conn=getConnection();
		int prevNo=new SupportDao().selectSupportPrevNo(conn,boardNo);
		close(conn);
		return prevNo;
	}
	
	//서포트 글 삭제하기
	public int deleteSupportBoard(int boardNo)
	{
		Connection conn=getConnection();
		int result=new SupportDao().deleteSupportBoard(conn,boardNo);
		if(result>0)
		{
			if(boardNo<0)
			{
				int changeRootNo=new SupportDao().deleteRootNo(conn,boardNo);
				if(changeRootNo>0)
				{
					commit(conn);
				}
				else
					rollback(conn);
				return changeRootNo;
			}
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	//답글 여부에 따라 목록 바꾸기
	public List<SupportBoard> selectTypeSupportList(int cPage, int numPerPage, String selectType)
	{
		Connection conn=getConnection();
		List<SupportBoard> list=null;
		
		if(selectType.equals("all"))
		{
			list=new SupportDao().selectSupportList(conn, cPage, numPerPage);
		}
		else if(selectType.equals("ing"))
		{
			list=new SupportDao().selectTypeSupportListIng(conn, cPage, numPerPage);
		}
		else
		{
			list=new SupportDao().selectTypeSupportListEnd(conn, cPage, numPerPage);
		}
		close(conn);
		
		return list;
	}
	
	public int selectSupportTypeCount(String selectType) 
	{
		Connection conn=getConnection();
		int result=0;
		
		if(selectType.equals("all"))
		{
			result=new SupportDao().selectSupportCount(conn);
		}
		else if(selectType.equals("ing"))
		{
			result=new SupportDao().selectSupportCountIng(conn);
		}
		else
		{
			result=new SupportDao().selectSupportCountEnd(conn);
		}
		close(conn);
		
		return result;
		
	}
	
	//메인용 - 답변되지 않은 문의
		public int selectUnckSupportCount()
		{
			Connection conn=getConnection();
			int result=new SupportDao().selectUnckSupportCount(conn);
			close(conn);
			return result;
		}
		public List<SupportBoard> selectUcnkSupportList(int cPage, int numPerPage)
		{
			Connection conn=getConnection();
			List<SupportBoard> list=new SupportDao().selectUnckSupportList(conn,cPage,numPerPage);
			close(conn);
			return list;
		}
}
