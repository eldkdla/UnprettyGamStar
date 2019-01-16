package com.gamstar.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import com.gamstar.model.vo.NewspeedMedia;
import com.gamstar.model.vo.User;

public class UserDao {

	Properties prop=new Properties();
	public UserDao(){
		String fileName=UserDao.class.getResource("./userquery.properties").getPath();
		try{
			prop.load(new FileReader(fileName));
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//유저정보 선택
	public User selectUser(Connection conn,User user){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectUser");
		User userData=new User();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,user.getNo());
			rs=pstmt.executeQuery();
			if(rs.next()){
				userData.setNo(rs.getInt("USER_NO"));
				userData.setName(rs.getString("USER_NAME"));
				userData.setGender(rs.getString("USER_GENDER"));
				userData.setState(rs.getInt("USER_STATE"));
				userData.setProfilePhoto(rs.getString("USER_PROFILE_PHOTO"));
				userData.setProfileBackgroundPhoto(rs.getString("USER_BACKGROUND_PHOTO"));
				userData.setEmail(rs.getString("USER_EMAIL"));
				userData.setPhone(rs.getString("USER_PHONE"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return userData;
	}
	
	//팔로우상태 확인
	public boolean isFollowed(Connection conn,User user,int myname){
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		String sql=prop.getProperty("isFollowed");
		boolean isFollowed=false;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myname);
			pstmt.setInt(2, user.getNo());
			rs=pstmt.executeQuery();
			isFollowed=rs.next();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return isFollowed;
		
	}

	
	
	//팔로워 선택
	public ArrayList<User> selectFollower(Connection conn,User user){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectFollower");
		ArrayList<User> followerDataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,user.getNo());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				User data = new User();
				data.setNo(rs.getInt("USER_NO"));
				data.setName(rs.getString("USER_NAME"));
				data.setProfilePhoto(rs.getString("USER_PROFILE_PHOTO"));
				
				followerDataArray.add(data);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return followerDataArray;
	}
	
	//팔로우 선택
	public ArrayList<User> selectFollow(Connection conn,User user){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectFollow");
		ArrayList<User> followDataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,user.getNo());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				User data = new User();
				data.setNo(rs.getInt("USER_NO"));
				data.setName(rs.getString("USER_NAME"));
				data.setProfilePhoto(rs.getString("USER_PROFILE_PHOTO"));
				
				followDataArray.add(data);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return followDataArray;
	}
	
	//차단목록 선택
	public ArrayList<User> selectBlock(Connection conn,User user){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectBlock");
		ArrayList<User> blockDataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,user.getNo());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				User data = new User();
				data.setNo(rs.getInt("USER_NO"));
				data.setName(rs.getString("USER_NAME"));
				data.setProfilePhoto(rs.getString("USER_PROFILE_PHOTO"));
				
				blockDataArray.add(data);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return blockDataArray;
	}
	

	//유저 정보 수정
	public int updateUserData(Connection conn,User user){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateUserData");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,user.getGender());
			pstmt.setString(2,user.getProfilePhoto());
			pstmt.setString(3,user.getEmail());
			pstmt.setString(4,user.getPhone());
			pstmt.setInt(5,user.getNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;	
	}
	
	//유저 이전비밀번호 확인
	public User chkBeforePw(Connection conn,User user){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("chkBeforePw");
		User beforePw=new User();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, user.getNo());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				beforePw.setPw(rs.getString("USER_PASSWORD"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return beforePw;
	}
	
	//비밀번호 수정
	public int updatePassword(Connection conn,User user){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updatePassword");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getPw());
			pstmt.setInt(2,user.getNo());
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			close(pstmt);
		}
		return result;
	}
	
	//프로필사진 수정
	public int updateProfilePhoto(Connection conn,User user){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateProfilePhoto");
		int result=0;
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,user.getProfilePhoto());
			pstmt.setInt(2,user.getNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	//배경사진 수정
	public int updateBackgroundPhoto(Connection conn,User user){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateBackgroundPhoto");
		int result =0;
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,user.getProfileBackgroundPhoto());
			pstmt.setInt(2,user.getNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	//이메일 중복 확인
	public boolean chkEmail(Connection conn,User user){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("chkEmail");
		boolean chkEmail=false;
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setInt(2, user.getNo());
			
			rs=pstmt.executeQuery();
			chkEmail=rs.next();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return chkEmail;
	}
	//전화번호 중복 확인
	public boolean chkPhone(Connection conn,User user){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("chkPhone");
		boolean chkPhone=false;
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getPhone());
			pstmt.setInt(2, user.getNo());
			
			rs=pstmt.executeQuery();
			chkPhone=rs.next();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return chkPhone;
	}
	
	//팔로우 추가
	public int insertFollow(Connection conn,User user,int myname){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertFollow");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myname);
			pstmt.setInt(2, user.getNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	//팔로우삭제
	public int deleteFollow(Connection conn,User user,int myname){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("deleteFollow");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myname);
			pstmt.setInt(2, user.getNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	//차단목록 추가
	public int insertBlockUser(Connection conn,User user,int myname){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertBlockUser");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, user.getNo());
			pstmt.setInt(2, myname);
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	//차단목록 삭제
	public int deleteBlockUser(Connection conn,User user,int myname){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("deleteBlockUser");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, user.getNo());
			pstmt.setInt(2, myname);
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
}
