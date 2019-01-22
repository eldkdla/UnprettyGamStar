package com.gamstar.admin.manager.model.dao;

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

import com.gamstar.user.model.vo.User;

public class AdminManagerDao {
	
	private Properties prop=new Properties();
	
	public AdminManagerDao() {
		try {
			String fileName=AdminManagerDao.class.getResource("./adminManager-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("adminDao prop load오류");
		}
	}
	//관리자  페이징
	public List<User> selectAdminList(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("selectMemberList");
		try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, (cPage-1)*numPerPage+1);
				pstmt.setInt(2, cPage*numPerPage);
				rs=pstmt.executeQuery();
				
				while(rs.next())
				{
					User userData=new User();
					userData.setNo(rs.getInt("USER_NO"));
					userData.setId(rs.getString("USER_Id"));
					userData.setName(rs.getString("USER_NAME"));
					userData.setGender(rs.getString("USER_GENDER"));
					userData.setState(rs.getInt("USER_STATE"));
					userData.setEnrollDate(rs.getDate("USER_ENROLL_DATE"));
					userData.setEmail(rs.getString("USER_EMAIL"));
					userData.setPhone(rs.getString("USER_PHONE"));
					
					list.add(userData);
				}
		}
		catch(SQLException e)
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
	
	public int selectAdminCount(Connection conn) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectMemberCount");
		
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
	
	//어드민 삭제
	public int deleteAdmin(Connection conn, int adminNo) 
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteAdmin");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, adminNo);
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
	
	//어드민 no 위해서, 현재 가장 낮은(최근 입력된) adminNo받아오기
	public int findMinAdminNo(Connection conn)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("findMinAdminNo");
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				result=rs.getInt("MIN(USER_NO)");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("adminNo 가장 작은거 못찾았대요");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	//어드민 추가
	public int createAdmin(Connection conn, User admin)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("createAdmin");
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, admin.getNo());
			pstmt.setString(2, admin.getName());
			pstmt.setString(3, admin.getEmail());
			pstmt.setString(4, admin.getPhone());
			pstmt.setInt(5,admin.getNo());
			pstmt.setString(6, admin.getId());
			pstmt.setString(7, admin.getPw());
			
			result=pstmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("createDao문제");
		}
		finally
		{
			close(pstmt);
		}
		
		return result;
	}
	
	//검색
	public List<User> searchIdList(Connection conn, int cPage, int numPerPage, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("searchIdList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,(cPage-1)*numPerPage+1);
			pstmt.setInt(2,cPage*numPerPage);
			pstmt.setString(3, "%"+searchKeyword+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				User userData=new User();
				
				userData.setNo(rs.getInt("USER_NO"));
				userData.setId(rs.getString("USER_Id"));
				userData.setName(rs.getString("USER_NAME"));
				userData.setState(rs.getInt("USER_STATE"));
				userData.setEnrollDate(rs.getDate("USER_ENROLL_DATE"));
				userData.setEmail(rs.getString("USER_EMAIL"));
				userData.setPhone(rs.getString("USER_PHONE"));
				
				list.add(userData);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(id검색)admin List dao문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectMemberCountId(Connection conn ,String searchKeyword) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectMemberCountId");
		
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
			System.out.println("(id검색)admin Count문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public List<User> searchNameList(Connection conn, int cPage, int numPerPage, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("searchNameList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,(cPage-1)*numPerPage+1);
			pstmt.setInt(2,cPage*numPerPage);
			pstmt.setString(3, "%"+searchKeyword+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				User userData=new User();
				
				userData.setNo(rs.getInt("USER_NO"));
				userData.setId(rs.getString("USER_Id"));
				userData.setName(rs.getString("USER_NAME"));
				userData.setState(rs.getInt("USER_STATE"));
				userData.setEnrollDate(rs.getDate("USER_ENROLL_DATE"));
				userData.setEmail(rs.getString("USER_EMAIL"));
				userData.setPhone(rs.getString("USER_PHONE"));
				
				list.add(userData);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(name검색)admin dao문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectMemberCountName(Connection conn ,String searchKeyword) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectMemberCountName");
		
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
			System.out.println("(name검색)admin Count문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	

}
