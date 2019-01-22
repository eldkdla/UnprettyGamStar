package com.gamstar.admin.user.model.dao;

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

public class AdminUserDao {
	
	private Properties prop=new Properties();
	
	public AdminUserDao() {
		try {
			String fileName=AdminUserDao.class.getResource("./adminUser-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("adminDao prop load오류");
		}
	}
	//멤버 페이징
	public List<User> selectUserList(Connection conn, int cPage, int numPerPage)
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
					userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
					
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
	
	public int selectMemberCount(Connection conn) 
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
	
	//멤버 정렬에 따른 페이징
	public List<User> orderUserListND(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("selectMemberListND");
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
					userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
					
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
	public List<User> orderUserListNA(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("selectMemberListNA");
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
					userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
					
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
	public List<User> orderUserListID(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("selectMemberListID");
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
					userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
					
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
	public List<User> orderUserListIA(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("selectMemberListIA");
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
					userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
					
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
	public List<User> orderUserListED(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("selectMemberListED");
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
					userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
					
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
	public List<User> orderUserListEA(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("selectMemberListEA");
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
					userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
					
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
	public List<User> orderUserListSD(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("selectMemberListSD");
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
					userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
					
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
	
	//멤버 검색
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
				userData.setGender(rs.getString("USER_GENDER"));
				userData.setState(rs.getInt("USER_STATE"));
				userData.setEnrollDate(rs.getDate("USER_ENROLL_DATE"));
				userData.setEmail(rs.getString("USER_EMAIL"));
				userData.setPhone(rs.getString("USER_PHONE"));
				userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
				
				list.add(userData);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(id검색)MemberList dao문제");
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
			System.out.println("(id검색)MemberCount문제");
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
				userData.setGender(rs.getString("USER_GENDER"));
				userData.setState(rs.getInt("USER_STATE"));
				userData.setEnrollDate(rs.getDate("USER_ENROLL_DATE"));
				userData.setEmail(rs.getString("USER_EMAIL"));
				userData.setPhone(rs.getString("USER_PHONE"));
				userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
				
				list.add(userData);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(name검색)MemberList dao문제");
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
			System.out.println("(name검색)MemberCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public List<User> searchEmailList(Connection conn, int cPage, int numPerPage, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("searchEmailList");
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
				userData.setGender(rs.getString("USER_GENDER"));
				userData.setState(rs.getInt("USER_STATE"));
				userData.setEnrollDate(rs.getDate("USER_ENROLL_DATE"));
				userData.setEmail(rs.getString("USER_EMAIL"));
				userData.setPhone(rs.getString("USER_PHONE"));
				userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
				
				list.add(userData);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(email검색)MemberList dao문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectMemberCountEmail(Connection conn ,String searchKeyword) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectMemberCountEmail");
		
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
			System.out.println("(email검색)MemberCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public List<User> searchPhoneList(Connection conn, int cPage, int numPerPage, String searchKeyword)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList();
		String sql=prop.getProperty("searchPhoneList");
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
				userData.setGender(rs.getString("USER_GENDER"));
				userData.setState(rs.getInt("USER_STATE"));
				userData.setEnrollDate(rs.getDate("USER_ENROLL_DATE"));
				userData.setEmail(rs.getString("USER_EMAIL"));
				userData.setPhone(rs.getString("USER_PHONE"));
				userData.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
				
				list.add(userData);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("(phone검색)MemberList dao문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectMemberCountPhone(Connection conn ,String searchKeyword) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectMemberCountPhone");
		
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
			System.out.println("(phone검색)MemberCount문제");
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	//멤버 탈퇴/ 정지
		//탈퇴
	public int deleteUser(Connection conn, int userNo)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteUser");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			result=pstmt.executeUpdate();			
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("deleteUserDao문제");
		}
		finally
		{
			close(pstmt);
		}
		
		return result;
	}
	
		//정지
	public int changeUserState(Connection conn, int userNo, int state)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("changeUserState");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,state);
			pstmt.setInt(2, userNo);
			result=pstmt.executeUpdate();			
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("deleteUserDao문제");
		}
		finally
		{
			close(pstmt);
		}
		
		return result;
	}
	
	public int setRemainingDate(Connection conn, int userNo, int stopTime)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("setRemainingDate");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, stopTime);
			pstmt.setInt(2, userNo);
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("setRemainDayDao ERR");
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}

}
