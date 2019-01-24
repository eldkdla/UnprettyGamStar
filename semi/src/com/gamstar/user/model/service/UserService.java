package com.gamstar.user.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.user.model.dao.UserDao;
import com.gamstar.user.model.vo.User;

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
		
		//스토리 선택
		public NewspeedMedia selectStory(Connection conn,User user){
			
			NewspeedMedia oldUserStory = new UserDao().selectStory(conn,user);
			
			return oldUserStory;
		}
		
		// 팔로우요청목록 선택
		public ArrayList<User> selectRequestFollow(Connection conn,User user){
			
			ArrayList<User> requestFollowDataArray = new UserDao().selectRequestFollow(conn,user);
			
			return requestFollowDataArray;
		}
		//팔로우요청목록 삭제
		public int deleteRequestFollowuser(Connection conn,User user){
			
			int result=new UserDao().deleteRequestFollowuser(conn,user);
			
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
			return result;
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
		//스토리 추가
		public int insertStory(Connection conn,NewspeedMedia newUserStory,User user){
			
			int result=new UserDao().insertStory(conn,newUserStory,user);
			
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
			
			return result;
			
		}
		//스토리 수정
		public int updateStory(Connection conn,NewspeedMedia newUserStory,User user){
			
			int result=new UserDao().updateStory(conn,newUserStory,user);
			
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
			
			return result;
		}
		
		//회원탈퇴  (state 100으로 변경)
		public int unregister(User user){
			Connection conn=getConnection();
			
			int result=0;
			
			result=new UserDao().unregister(conn,user);
			
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
		public int insertFollow(Connection conn,User user,int myname){
			
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
	
	public List<User> selectFollowLike(int userNo, String userName) {
		Connection conn = getConnection();
		
		List<User> userList = new UserDao().selectFollowLike(conn, userNo, userName);
		
		return userList;
	}
	
	//유저로그인
			public User loginCheck(User u) {
				Connection conn = getConnection();
				User data = new UserDao().loginCheck(conn, u);
				close(conn);
				return data;
			}
			
			//네이버유저로그인
			public User loginCheckNaver(User u) {
				System.out.println("서비스는 왔니? 네이년 서블릿");
				Connection conn = getConnection();
				User data = new UserDao().loginCheckNaver(conn, u);
				close(conn);
				return data;
			}
			
			//가입아이디 중복체크
			public User userIdChk(User user){
				Connection conn=getConnection();
				
				User data = new UserDao().loginCheck(conn,user);
				
				close(conn);
				return data;
			}
			
			//회원가입 TB_USER
			public int insertUser(User u) {
				Connection conn = getConnection();
				int newUserNo = new UserDao().selectNextNewUserNo(conn);
				u.setNo(newUserNo);//get SEQ_USER_NO.NEXTVAL
				System.out.println("다음 시퀀스 : "+newUserNo);
				int result = new UserDao().insertUser(conn, u);
				
				if(result>0)//입력성공
				{
					//commit(conn);
				}
				else{
					//rollback(conn);
				}
				close(conn);
				
				return result;
			}
			
			//회원가입 TB_BASIC_USER
			public int insertUserBasic(User u) {
				Connection conn = getConnection();			
				
				int tbUserResult = insertUser(u);
				System.out.println("상위 : "+tbUserResult);
				//int nowUserNo = new UserDao().selectNowUserNo(conn);
//				u.setNo(nowUserNo);//get SEQ_USER_NO.CURRVAL  //안써도 될듯.
				System.out.println("basic에 지금 시퀀스 : "+u.getNo());
				int result = new UserDao().insertUserBasic(conn, u);
				System.out.println("하위 : "+result);
				if(result > 0 && tbUserResult > 0)
				{
					commit(conn);
					return result;
				}
				else
				{
					rollback(conn);
					return -1;
				}			
				 
			}
			
			//회원가입 TB_NAVER_USER
			public int insertUserNaver(User u) {
				Connection conn = getConnection();			
				
				int tbUserResult = insertUser(u);
				System.out.println("in naver 상위 : "+tbUserResult);

				System.out.println("basic에 지금 시퀀스 : "+u.getNo());
				
				int result = new UserDao().insertUserNaver(conn, u);
				System.out.println("in naver 하위 : "+result);
				if(result > 0 && tbUserResult > 0)
				{
					commit(conn);
					return result;
				}
				else
				{
					rollback(conn);
					return -1;
				}			
				 
			}

}
