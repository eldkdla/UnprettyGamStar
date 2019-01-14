package com.kh.semi.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semi.model.vo.User;

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
	public User selectUser(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectUser");
		User userData=new User();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,u.getNo());
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
	public boolean isFollowed(Connection conn,User u,int myname){
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		String sql=prop.getProperty("isFollowed");
		boolean isFollowed=false;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myname);
			pstmt.setInt(2, u.getNo());
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

	/*//게시글(다중) 선택
	public ArrayList<User> selectContent1(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectContent1");
		ArrayList<User> content1DataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,u.getId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				User data = new User();
				data.setId(rs.getString("name"));
				content1DataArray.add(data);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return content1DataArray;
	}
	
	//게시글(단일) 선택
	public ArrayList<User> selectContent2(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectContent2");
		ArrayList<User> content2DataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,u.getId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				User data = new User();
				data.setId(rs.getString("name"));
				content2DataArray.add(data);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return content2DataArray;
	}
	
	//저장된게시물 선택
	public ArrayList<User> selectStorageContent(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectStorageContent");
		ArrayList<User> storageContentDataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,u.getId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				User data = new User();
				data.setId(rs.getString("name"));
				storageContentDataArray.add(data);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return storageContentDataArray;
	}
	
	//태그된 게시물 선택
	public ArrayList<User> selectTagContent(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectTagContent");
		ArrayList<User> tagContentDataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,u.getId());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				User data = new User();
				data.setId(rs.getString("name"));
				tagContentDataArray.add(data);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return tagContentDataArray;
	} */
	
	//팔로워 선택
	public ArrayList<User> selectFollower(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectFollower");
		ArrayList<User> followerDataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,u.getNo());
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
	public ArrayList<User> selectFollow(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectFollow");
		ArrayList<User> followDataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,u.getNo());
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
	public ArrayList<User> selectBlock(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectBlock");
		ArrayList<User> blockDataArray=new ArrayList<User>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,u.getNo());
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
	public int updateUserData(Connection conn,User u){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateUserData");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,u.getGender());
			pstmt.setString(2,u.getProfilePhoto());
			pstmt.setString(3,u.getEmail());
			pstmt.setString(4,u.getPhone());
			pstmt.setInt(5,u.getNo());
			
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
	/*
	//비밀번호 수정
	public int updatePassword(Connection conn,User u){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updatePassword");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,u.getId());
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			close(pstmt);
		}
		return result;
	}
	
	*/
	//프로필사진 수정
	public int updateProfilePhoto(Connection conn,User u){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateProfilePhoto");
		int result=0;
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,u.getProfilePhoto());
			pstmt.setInt(2,u.getNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	//배경사진 수정
	public int updateBackgroundPhoto(Connection conn,User u){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateBackgroundPhoto");
		int result =0;
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,u.getProfileBackgroundPhoto());
			pstmt.setInt(2,u.getNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	//이메일 중복 확인
	public boolean ckEmail(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("ckEmail");
		boolean ckEmail=false;
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getEmail());
			pstmt.setInt(2, u.getNo());
			
			rs=pstmt.executeQuery();
			ckEmail=rs.next();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return ckEmail;
	}
	//전화번호 중복 확인
	public boolean ckPhone(Connection conn,User u){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("ckPhone");
		boolean ckPhone=false;
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getPhone());
			pstmt.setInt(2, u.getNo());
			
			rs=pstmt.executeQuery();
			ckPhone=rs.next();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return ckPhone;
	}
	
	//팔로우 추가
	public int insertFollow(Connection conn,User u,int myname){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertFollow");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myname);
			pstmt.setInt(2, u.getNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	//팔로우삭제
	public int deleteFollow(Connection conn,User u,int myname){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("deleteFollow");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myname);
			pstmt.setInt(2, u.getNo());
			
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	//차단목록 추가
	public int insertBlockUser(Connection conn,User u,int myname){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertBlockUser");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, u.getNo());
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
	public int deleteBlockUser(Connection conn,User u,int myname){
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("deleteBlockUser");
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, u.getNo());
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
