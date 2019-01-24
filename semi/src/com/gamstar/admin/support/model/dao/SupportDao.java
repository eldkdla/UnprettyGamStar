package com.gamstar.admin.support.model.dao;

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

import com.gamstar.admin.support.model.vo.SupportBoard;
import com.gamstar.admin.support.model.vo.SupportBoardMedia;

public class SupportDao {
	
	private Properties prop = new Properties();
	
	public SupportDao() {
		try {
			String fileName=SupportDao.class.getResource("./support-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("supportDao prop load오류");
		}
	}
	
	//서포트 페이징용
	public List<SupportBoard> selectSupportList(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SupportBoard> list=new ArrayList();
		String sql=prop.getProperty("selectSupportList");
		try {
			System.out.println("supportDao cPage, numPerPage"+cPage+" "+numPerPage);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,(cPage-1)*numPerPage+1);
			pstmt.setInt(2,cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs);
				SupportBoard s=new SupportBoard();
				s.setSupportBoardNo(rs.getInt("supportbbs_no"));
				s.setSupportBoardRootNo(rs.getInt("supportbbs_root_no"));
				s.setSupportBoardTitle(rs.getString("supportbbs_title"));
				s.setSupportBoardContent(rs.getString("supportbbs_content"));
				s.setSupportBoardDate(rs.getDate("supportbbs_date"));
				s.setSupportBoardWriterNo(rs.getInt("user_no"));
				s.setSupportBoardWriterId(rs.getString("user_id"));
				s.setSupportBoardWriterName(rs.getString("user_name"));
				
				list.add(s);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("supportList문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectSupportCount(Connection conn) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectSupportCount");
		
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
			System.out.println("supportCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	//서포트 불러오기
	public SupportBoard selectSupportOne(Connection conn, int no)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		SupportBoard s=null;
		String sql=prop.getProperty("selectSupportOne");
		
		try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs=pstmt.executeQuery();
				
				while(rs.next())
				{
					s=new SupportBoard();
					s.setSupportBoardNo(rs.getInt("supportbbs_no"));
					s.setSupportBoardRootNo(rs.getInt("supportbbs_root_no"));
					s.setSupportBoardTitle(rs.getString("supportbbs_title"));
					s.setSupportBoardContent(rs.getString("supportbbs_content"));
					s.setSupportBoardDate(rs.getDate("supportbbs_date"));
					s.setSupportBoardWriterNo(rs.getInt("user_no"));
					s.setSupportBoardWriterId(rs.getString("user_id"));
					s.setSupportBoardWriterName(rs.getString("user_name"));
				}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("supportDao selectOne 문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return s;
	}
	
	//서포트 첨부파일
	public List<SupportBoardMedia> selectSupportOneMedia(Connection conn, int no)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SupportBoardMedia> sMedias=new ArrayList();
		String sql=prop.getProperty("selectSupportOneMedia");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				SupportBoardMedia s=new SupportBoardMedia();
				s.setSupportBoardMediaNo(rs.getInt("supportbbs_no"));
				s.setSupportBoardMediaIndex(rs.getInt("supportbbs_media_index"));
				s.setSupportBoardMediaType(rs.getInt("supportbbs_media_type"));
				s.setSupportBoardMediaPathOri(rs.getString("supportbbs_media_path_ori"));
				s.setSupportBoardMediaPathRe(rs.getString("supportbbs_media_path_re"));
				sMedias.add(s);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Support media 가져오기 실패");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		System.out.println(sMedias+"서포트 dao 첨부파일 보내기는 성공");
		return sMedias;
	}

	//답변 넣기
	public int insertAnswer(Connection conn, SupportBoard supportAnswer)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertSupport");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, supportAnswer.getSupportBoardNo());
			pstmt.setInt(2, supportAnswer.getSupportBoardRootNo());
			pstmt.setString(3, supportAnswer.getSupportBoardContent());
			pstmt.setInt(4, supportAnswer.getSupportBoardWriterNo());
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}
	
	//answer의 No를 찾아준다 (rootBoard의 root_no를 answer의 no로 바꿔주기 위해서)
	public int findAnswerNo(Connection conn, int rootNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int answerNo=0;
		String sql=prop.getProperty("findSupportAnswerNo");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rootNo);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				answerNo=rs.getInt("supportbbs_no");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("support_dao_findAnswerNo문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return answerNo;
	}
	
	//답변 달린 글글과 답변을 연결
	public int changeRootResult(Connection conn, int rootNo ,int answerNo)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("changeSupportRoot");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, answerNo);
			pstmt.setInt(2, rootNo);
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("support_dao_changeRootREsult문제");
		}
		finally
		{
			close(pstmt);
		}
		
		return result;
	}
	
	
	//서포트 검색
	public List<SupportBoard> searchIdList(Connection conn, int cPage, int numPerPage, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SupportBoard> list=new ArrayList();
		String sql=prop.getProperty("searchIdList");
		try {
			System.out.println("supportDao cPage, numPerPage"+cPage+" "+numPerPage);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			pstmt.setInt(3,(cPage-1)*numPerPage+1);
			pstmt.setInt(4,cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs);
				SupportBoard s=new SupportBoard();
				s.setSupportBoardNo(rs.getInt("supportbbs_no"));
				s.setSupportBoardRootNo(rs.getInt("supportbbs_root_no"));
				s.setSupportBoardTitle(rs.getString("supportbbs_title"));
				s.setSupportBoardContent(rs.getString("supportbbs_content"));
				s.setSupportBoardDate(rs.getDate("supportbbs_date"));
				s.setSupportBoardWriterNo(rs.getInt("user_no"));
				s.setSupportBoardWriterId(rs.getString("user_id"));
				s.setSupportBoardWriterName(rs.getString("user_name"));
				
				list.add(s);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(id검색)supportList문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectSupportCountId(Connection conn ,String searchKeyword) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectSupportCountId");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("cnt");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(id검색)supportCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<SupportBoard> searchTitleList(Connection conn, int cPage, int numPerPage, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SupportBoard> list=new ArrayList();
		String sql=prop.getProperty("searchTitleList");
		try {
			System.out.println("supportDao cPage, numPerPage"+cPage+" "+numPerPage);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2,(cPage-1)*numPerPage+1);
			pstmt.setInt(3,cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs);
				SupportBoard s=new SupportBoard();
				s.setSupportBoardNo(rs.getInt("supportbbs_no"));
				s.setSupportBoardRootNo(rs.getInt("supportbbs_root_no"));
				s.setSupportBoardTitle(rs.getString("supportbbs_title"));
				s.setSupportBoardContent(rs.getString("supportbbs_content"));
				s.setSupportBoardDate(rs.getDate("supportbbs_date"));
				s.setSupportBoardWriterNo(rs.getInt("user_no"));
				s.setSupportBoardWriterId(rs.getString("user_id"));
				s.setSupportBoardWriterName(rs.getString("user_name"));
				
				list.add(s);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(title)supportList문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectSupportCountTitle(Connection conn ,String searchKeyword) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectSupportCountTitle");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("cnt");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(title)supportCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<SupportBoard> searchContentList(Connection conn, int cPage, int numPerPage, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SupportBoard> list=new ArrayList();
		String sql=prop.getProperty("searchContentList");
		try {
			System.out.println("supportDao cPage, numPerPage"+cPage+" "+numPerPage);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2,(cPage-1)*numPerPage+1);
			pstmt.setInt(3,cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs);
				SupportBoard s=new SupportBoard();
				s.setSupportBoardNo(rs.getInt("supportbbs_no"));
				s.setSupportBoardRootNo(rs.getInt("supportbbs_root_no"));
				s.setSupportBoardTitle(rs.getString("supportbbs_title"));
				s.setSupportBoardContent(rs.getString("supportbbs_content"));
				s.setSupportBoardDate(rs.getDate("supportbbs_date"));
				s.setSupportBoardWriterNo(rs.getInt("user_no"));
				s.setSupportBoardWriterId(rs.getString("user_id"));
				s.setSupportBoardWriterName(rs.getString("user_name"));
				
				list.add(s);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(Content)supportList문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectSupportCountContent(Connection conn ,String searchKeyword) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectSupportCountContent");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("cnt");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(content)supportCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<SupportBoard> searchAllList(Connection conn, int cPage, int numPerPage, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SupportBoard> list=new ArrayList();
		String sql=prop.getProperty("searchAllList");
		try {
			System.out.println("supportDao cPage, numPerPage"+cPage+" "+numPerPage);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			pstmt.setInt(3,(cPage-1)*numPerPage+1);
			pstmt.setInt(4,cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs);
				SupportBoard s=new SupportBoard();
				s.setSupportBoardNo(rs.getInt("supportbbs_no"));
				s.setSupportBoardRootNo(rs.getInt("supportbbs_root_no"));
				s.setSupportBoardTitle(rs.getString("supportbbs_title"));
				s.setSupportBoardContent(rs.getString("supportbbs_content"));
				s.setSupportBoardDate(rs.getDate("supportbbs_date"));
				s.setSupportBoardWriterNo(rs.getInt("user_no"));
				s.setSupportBoardWriterId(rs.getString("user_id"));
				s.setSupportBoardWriterName(rs.getString("user_name"));
				
				list.add(s);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(all)supportList문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectSupportCountAll(Connection conn ,String searchKeyword) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectSupportCountAll");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("cnt");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(all)supportCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	//서포트 이전/다음글 가져오기
	public int selectSupportNextNo(Connection conn,int boardNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int nextNo=0;
		
		String sql=prop.getProperty("selectSupportNextNo");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				nextNo=rs.getInt("min(supportbbs_no)");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("다음글 no 가져오기 오류 dao");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return nextNo;
	}
	
	public int selectSupportPrevNo(Connection conn,int boardNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int prevNo=0;
		
		String sql=prop.getProperty("selectSupportPrevNo");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				prevNo=rs.getInt("max(supportbbs_no)");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("이전글 no 가져오기 dao 오류");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return prevNo;
	}
	
	public int deleteSupportBoard(Connection conn, int boardNo)
	{
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql=prop.getProperty("deleteSupportBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteRootNo(Connection conn, int boardNo)
	{
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql=prop.getProperty("deleteRootNo");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		
		return result;
	}
	
	
	//답변여부에 따라 글 확인
	public List<SupportBoard> selectTypeSupportListIng(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SupportBoard> list=new ArrayList();
		String sql=prop.getProperty("selectListTypeIng");
		try {
			System.out.println("supportDao cPage, numPerPage"+cPage+" "+numPerPage);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,(cPage-1)*numPerPage+1);
			pstmt.setInt(2,cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs);
				SupportBoard s=new SupportBoard();
				s.setSupportBoardNo(rs.getInt("supportbbs_no"));
				s.setSupportBoardRootNo(rs.getInt("supportbbs_root_no"));
				s.setSupportBoardTitle(rs.getString("supportbbs_title"));
				s.setSupportBoardContent(rs.getString("supportbbs_content"));
				s.setSupportBoardDate(rs.getDate("supportbbs_date"));
				s.setSupportBoardWriterNo(rs.getInt("user_no"));
				s.setSupportBoardWriterId(rs.getString("user_id"));
				s.setSupportBoardWriterName(rs.getString("user_name"));
				
				list.add(s);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(ing)supportList문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectSupportCountIng(Connection conn) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectCountTypeIng");
		
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
			System.out.println("(ing)supportCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	public List<SupportBoard> selectTypeSupportListEnd(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SupportBoard> list=new ArrayList();
		String sql=prop.getProperty("selectListTypeEnd");
		try {
			System.out.println("supportDao cPage, numPerPage"+cPage+" "+numPerPage);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,(cPage-1)*numPerPage+1);
			pstmt.setInt(2,cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs);
				SupportBoard s=new SupportBoard();
				s.setSupportBoardNo(rs.getInt("supportbbs_no"));
				s.setSupportBoardRootNo(rs.getInt("supportbbs_root_no"));
				s.setSupportBoardTitle(rs.getString("supportbbs_title"));
				s.setSupportBoardContent(rs.getString("supportbbs_content"));
				s.setSupportBoardDate(rs.getDate("supportbbs_date"));
				s.setSupportBoardWriterNo(rs.getInt("user_no"));
				s.setSupportBoardWriterId(rs.getString("user_id"));
				s.setSupportBoardWriterName(rs.getString("user_name"));
				
				list.add(s);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(end)supportList문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectSupportCountEnd(Connection conn) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectCountTypeEnd");
		
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
			System.out.println("(end)supportCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
}
