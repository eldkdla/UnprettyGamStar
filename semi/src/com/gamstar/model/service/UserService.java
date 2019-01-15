package com.gamstar.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.gamstar.model.dao.UserDao;
import com.gamstar.model.vo.User;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class UserService {
	
	//유저정보 선택 
	public User selectUser(Connection conn,User u){
			

		User userData=new UserDao().selectUser(conn,u);
			
		return userData;
	}
	public User selectUser(User u){
		
		Connection conn=getConnection();
			
		User userData=new UserDao().selectUser(conn,u);
			
		close(conn);
		return userData;
	}
	
	/*//게시글(다중) 선택 
	public ArrayList<User> selectContent1(Connection conn,User u){
		
		ArrayList<User> content1DataArray=new UserDao().selectContent1(conn,u);

		return content1DataArray;
	}
	
	//게시글(단일) 선택 
	public ArrayList<User> selectContent2(Connection conn,User u){
		
		ArrayList<User> content2DataArray=new UserDao().selectContent2(conn,u);

		return content2DataArray;
	}
	
	//저장된 게시물 선택
	public ArrayList<User> selectStorageContent(Connection conn,User u){
	
	ArrayList<User> storageContentDataArray=new UserDao().selectStorageContent(conn,u);

	return storageContentDataArray;
	}
	
	//태그된 게시물 선택
	public ArrayList<User> selectTagContent(Connection conn,User u){
	
	ArrayList<User> tagContentDataArray=new UserDao().selectTagContent(conn,u);

	return tagContentDataArray;
	}*/

	//팔로워 선택
	public ArrayList<User> selectFollower(Connection conn,User u){
		
		ArrayList<User> followerDataArray=new UserDao().selectFollower(conn,u);

		return followerDataArray;
	}
	
	//팔로우 선택
	public ArrayList<User> selectFollow(Connection conn,User u){
		
		ArrayList<User> followDataArray=new UserDao().selectFollow(conn,u);

		return followDataArray;
	}
	
	//차단목록 선택
	public ArrayList<User> selectBlock(Connection conn,User u){
		
		ArrayList<User> blockDataArray=new UserDao().selectBlock(conn,u);

		return blockDataArray;
	}
	
	//팔로우상태 확인
	public boolean isFollowed(Connection conn,User u,int myname){
		
		boolean isFollowed=new UserDao().isFollowed(conn,u,myname);
		
		return isFollowed;
	}
	
	
	//유저 정보 수정
	public int updateUserData(User u){
		Connection conn=getConnection();
		
		int result=new UserDao().updateUserData(conn,u);
		
		close(conn);
		return result;
	}
	
	//유저 이전비밀번호 확인
	public User chkBeforePw(User u){
		Connection conn=getConnection();
		
		User beforePw=new UserDao().chkBeforePw(conn,u);
		
		close(conn);
		return beforePw;
	}

	//유저 비밀번호 수정
	public int updatePassword(User u){
		Connection conn=getConnection();
		
		int result=new UserDao().updatePassword(conn,u);
		
		close(conn);
		return result;
	}
	
	//프로필사진 수정
	public int updateProfilePhoto(User u){
		Connection conn=getConnection();
		
		int result=new UserDao().updateProfilePhoto(conn,u);
		
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
	public int updateBackgroundPhoto(User u){
		Connection conn=getConnection();
		
		int result=new UserDao().updateBackgroundPhoto(conn,u);
		
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
	public boolean chkEmail(User u){
		Connection conn=getConnection();
		
		boolean chkEmail=new UserDao().chkEmail(conn,u);
		
		close(conn);
		return chkEmail;
	}
	//전화번호 중복 확인
	public boolean chkPhone(User u){
		Connection conn=getConnection();
		
		boolean chkPhone=new UserDao().chkPhone(conn,u);
		
		close(conn);
		return chkPhone;
	}
	
	//팔로우 추가
	public int insertFollow(User u,int myname){
		Connection conn=getConnection();
		
		int result=new UserDao().insertFollow(conn,u,myname);
		
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
	public int deleteFollow(User u,int myname){
		Connection conn=getConnection();
		
		int result=new UserDao().deleteFollow(conn,u,myname);
		
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
	public int insertBlockUser(User u,int myname){
		Connection conn=getConnection();
		
		int result=new UserDao().insertBlockUser(conn,u,myname);
		
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
	public int deleteBlockUser(User u,int myname){
		Connection conn=getConnection();
		
		int result=new UserDao().deleteBlockUser(conn,u,myname);
		
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
