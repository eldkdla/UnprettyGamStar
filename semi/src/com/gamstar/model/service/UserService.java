package com.gamstar.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gamstar.model.dao.UserDao;
import com.gamstar.model.vo.NewspeedMedia;
import com.gamstar.model.vo.User;

public class UserService {
	
	//유저정보 선택 
	public User selectUser(Connection conn,User user){
			

		User userData=new UserDao().selectUser(conn,user);
			
		return userData;
	}
	public User selectUser(User user){
		
		Connection conn=getConnection();
			
		User userData=new UserDao().selectUser(conn,user);
			
		close(conn);
		return userData;
	}
	
	//팔로워 선택
	public ArrayList<User> selectFollower(Connection conn,User user){
		
		ArrayList<User> followerDataArray=new UserDao().selectFollower(conn,user);

		return followerDataArray;
	}
	
	//팔로우 선택
	public ArrayList<User> selectFollow(Connection conn,User user){
		
		ArrayList<User> followDataArray=new UserDao().selectFollow(conn,user);

		return followDataArray;
	}
	
	//차단목록 선택
	public ArrayList<User> selectBlock(Connection conn,User user){
		
		ArrayList<User> blockDataArray=new UserDao().selectBlock(conn,user);

		return blockDataArray;
	}
	
	//팔로우상태 확인
	public boolean isFollowed(Connection conn,User user,int myname){
		
		boolean isFollowed=new UserDao().isFollowed(conn,user,myname);
		
		return isFollowed;
	}
	
	
	//유저 정보 수정
	public int updateUserData(User user){
		Connection conn=getConnection();
		
		int result=new UserDao().updateUserData(conn,user);
		
		try {
			if(result!=0){
				conn.commit();				
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		close(conn);
		return result;
	}
	
	//유저 이전비밀번호 확인
	public User chkBeforePw(User user){
		Connection conn=getConnection();
		
		User beforePw=new UserDao().chkBeforePw(conn,user);
		
		close(conn);
		return beforePw;
	}

	//유저 비밀번호 수정
	public int updatePassword(User user){
		Connection conn=getConnection();
		
		int result=new UserDao().updatePassword(conn,user);
		
		try {
			if(result!=0){
				conn.commit();				
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		close(conn);
		return result;
	}
	
	//프로필사진 수정
	public int updateProfilePhoto(User user){
		Connection conn=getConnection();
		
		int result=new UserDao().updateProfilePhoto(conn,user);
		
		try {
			if(result!=0){
				conn.commit();				
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		close(conn);
		return result;
	}
	
	//배경사진 수정
	public int updateBackgroundPhoto(User user){
		Connection conn=getConnection();
		
		int result=new UserDao().updateBackgroundPhoto(conn,user);
		
		try {
			if(result!=0){
				conn.commit();				
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		close(conn);
		return result;
	}
	//이메일 중복 확인
	public boolean chkEmail(User user){
		Connection conn=getConnection();
		
		boolean chkEmail=new UserDao().chkEmail(conn,user);
		
		close(conn);
		return chkEmail;
	}
	//전화번호 중복 확인
	public boolean chkPhone(User user){
		Connection conn=getConnection();
		
		boolean chkPhone=new UserDao().chkPhone(conn,user);
		
		close(conn);
		return chkPhone;
	}
	
	//팔로우 추가
	public int insertFollow(User user,int myname){
		Connection conn=getConnection();
		
		int result=new UserDao().insertFollow(conn,user,myname);
		
		try {
			if(result!=0){
				conn.commit();				
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		close(conn);
		return result;
	}
	//팔로우 삭제
	public int deleteFollow(User user,int myname){
		Connection conn=getConnection();
		
		int result=new UserDao().deleteFollow(conn,user,myname);
		
		try {
			if(result!=0){
				conn.commit();				
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		close(conn);
		return result;
	}
	
	//차단목록 추가
	public int insertBlockUser(User user,int myname){
		Connection conn=getConnection();
		
		int result=new UserDao().insertBlockUser(conn,user,myname);
		
		try {
			if(result!=0){
				conn.commit();				
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		close(conn);
		return result;
	}
	//차단목록 삭제
	public int deleteBlockUser(User user,int myname){
		Connection conn=getConnection();
		
		int result=new UserDao().deleteBlockUser(conn,user,myname);
		
		try {
			if(result!=0){
				conn.commit();				
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		close(conn);
		return result;
	}
	
	
	
	
}
