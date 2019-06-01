package com.gamstar.user.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.user.model.vo.User;

public class UserDao {

	Properties prop = new Properties();

	public UserDao() {
		String fileName = UserDao.class.getResource("./userquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public List<User> selectFollowUser(Connection conn, int userNo){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectMyFollow");
		
		List<User> listDB = new ArrayList();
		User data = null;
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs=pstmt.executeQuery();
				
			while(rs.next()) {
				data = new User();
					
				data.setNo(rs.getInt("user_no"));
				data.setName(rs.getString("user_name"));
				data.setProfilePhoto(rs.getString("user_profile_photo"));

					
				listDB.add(data);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return listDB;
		
	}
	
		
	public List<User> selectFeedUser(Connection conn, List<String> peedNo){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFeedUser");
		
		List<User> listDB = new ArrayList();
		User data = null;
		try {
			
			for(int i=0; i<peedNo.size(); i++) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, peedNo.get(i));
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					data = new User();
					
					data.setName(rs.getString("user_name"));
					data.setProfilePhoto(rs.getString("user_profile_photo"));
					
					listDB.add(data);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return listDB;
	}

	//유저정보 선택 - 객체 생성부분 삭제 기존 파라메터에 추가로 값 넣어줌
		public User selectUser(Connection conn,User user){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=prop.getProperty("selectUser");
			//User userData=new User();
			System.out.println("여기냐??");
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,user.getNo());
				rs=pstmt.executeQuery();
				if(rs.next()){
					user.setNo(rs.getInt("USER_NO"));
					user.setName(rs.getString("USER_NAME"));
					user.setGender(rs.getString("USER_GENDER"));
					user.setState(rs.getInt("USER_STATE"));
					user.setProfilePhoto(rs.getString("USER_PROFILE_PHOTO"));
					user.setProfileBackgroundPhoto(rs.getString("USER_BACKGROUND_PHOTO"));
					user.setEmail(rs.getString("USER_EMAIL"));
					user.setPhone(rs.getString("USER_PHONE"));
					user.setDisclosure(rs.getInt("USER_DISCLOSURE"));
					user.setRemainingDay(rs.getInt("USER_REMAINING_DAY"));
					user.setLinkType(rs.getInt("USER_LINK_TYPE"));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(rs);
				close(pstmt);
			}
			System.out.println("넘기냐??");
			return user;
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
		//스토리 선택
		public NewspeedMedia selectStory(Connection conn, User user){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=prop.getProperty("selectStory");
			NewspeedMedia oldUserStory=new NewspeedMedia();
			
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user.getNo());
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					oldUserStory.setType(rs.getInt("MEDIA_TYPE"));
					oldUserStory.setPath(rs.getString("MEDIA_PATH"));
				}
				else{
					oldUserStory.setPath("");
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(rs);
				close(pstmt);
			}
			return oldUserStory;
		}
		
		//팔로우요청목록 선택
		public ArrayList<User> selectRequestFollow(Connection conn,User user){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=prop.getProperty("selectRequestFollow");
			ArrayList<User> requestFollowDataArray=new ArrayList<User>();
			
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user.getNo());
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					User requestFollowUser=new User();
					requestFollowUser.setNo(rs.getInt("USER_NO"));
					requestFollowUser.setName(rs.getString("USER_NAME"));
					requestFollowUser.setProfilePhoto(rs.getString("USER_PROFILE_PHOTO"));
					requestFollowUser.setIswatch(rs.getInt("FOLLOW_REQUEST_ISWATCH"));
					
					requestFollowDataArray.add(requestFollowUser);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(rs);
				close(pstmt);
			}
			return requestFollowDataArray;
			
		}
		//팔로우요청목록 삭제
		public int deleteRequestFollowuser(Connection conn,User user,int myUserNo){
			PreparedStatement pstmt=null;
			String sql=prop.getProperty("deleteRequestFollowuser");
			int result=0;
			
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user.getNo());
				pstmt.setInt(2, myUserNo);
				
				result=pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(pstmt);
			}
			return result;
		}
		//팔로우요청 하기
		public int insertRequestFollow(Connection conn, User user,int myUserNo){
			PreparedStatement pstmt=null;
			String sql=prop.getProperty("insertRequestFollow");
			int result=0;
			
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,myUserNo);
				pstmt.setInt(2,user.getNo());
				
				result=pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(pstmt);
			}
			return result;
			
		}
		//팔로우요청 보류
		public int reserveRequestFollow(Connection conn, User user,int myUserNo){
			PreparedStatement pstmt=null;
			String sql=prop.getProperty("reserveRequestFollow");
			int result=0;
			
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,user.getNo());
				pstmt.setInt(2,myUserNo);
				
				result=pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(pstmt);
			}
			return result;
		}
		//팔로우요청 상태 있는지 확인 [내정보창]
		public ArrayList<User> isRequestFollow(Connection conn, User user){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=prop.getProperty("isRequestFollow");
			ArrayList<User> isRequestFollowDataArray=new ArrayList<User>();
			
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,user.getNo());
				System.out.println(user.getNo());
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					User user1 = new User();
					user1.setNo(rs.getInt("USER_NO"));
					user1.setName(rs.getString("USER_NAME"));
					
					isRequestFollowDataArray.add(user1);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(rs);
				close(pstmt);
			}
			return isRequestFollowDataArray;
			
			
		}
		//팔로우요청 페이지인지 [다른유저페이지]
		public boolean isRequestFollowPage(Connection conn,User user,int myNo){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=prop.getProperty("isRequestFollowPage");
			boolean isRequestFollowPage=false;
			
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, myNo);
				pstmt.setInt(2, user.getNo());
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					isRequestFollowPage=true;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(rs);
				close(pstmt);
			}
			return isRequestFollowPage;
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
				pstmt.setInt(5,user.getDisclosure());
				pstmt.setString(6, user.getName());
				pstmt.setInt(7,user.getNo());
				
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
		//공개로 수정시 팔로우요청 없애주는 트리거 (팔로우테이블에 넣어주기)
		public int updateDisclosureTriggerInsert(Connection conn,int myNo){
			PreparedStatement pstmt=null;
			String sql=prop.getProperty("updateDisclosureTriggerInsert");
			int result=0;
			
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, myNo);
				
				result=pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(pstmt);
			}
			return result;
		}
		//공개로 수정시 팔로우요청 없애주는 트리거 (팔로우요청목록 삭제)
		public int updateDisclosureTriggerDelete(Connection conn,int myNo){
			PreparedStatement pstmt=null;
			String sql=prop.getProperty("updateDisclosureTriggerDelete");
			int result=0;
					
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, myNo);
						
				result=pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
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
		//스토리추가
		public int insertStory(Connection conn,NewspeedMedia newUserStory,User user){
			PreparedStatement pstmt=null;
			String sql=prop.getProperty("insertStory");
			int result=0;
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user.getNo());
				pstmt.setInt(2, 1);
				pstmt.setString(3, newUserStory.getPath());
				
				result=pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(pstmt);
			}
			return result;
			
		}
		//스토리변경
		public int updateStory(Connection conn,NewspeedMedia newUserStory,User user){
			PreparedStatement pstmt=null;
			String sql=prop.getProperty("updateStory");
			int result=0;
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, newUserStory.getPath());
				pstmt.setInt(2, user.getNo());
				
				result=pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(pstmt);
			}
			return result;
			
		}
		
		//회원탈퇴  (state 100으로 변경)
		public int unregister(Connection conn,User user){
			PreparedStatement pstmt=null;
			String sql=prop.getProperty("unregister");
			int result=0;
			
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, user.getNo());
				
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
	
	public List<User> selectFollowLike(Connection conn, int userNo, String name) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectFollowLike");
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setString(2, "%" + name + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setName(rs.getString("USER_NAME"));
				user.setNo(rs.getInt("USER_NO"));
				user.setProfilePhoto(rs.getString("USER_PROFILE_PHOTO"));
				
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return userList;
	}
	
	
	
	public User selectNewspeedWriter(Connection conn,int newspeedNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectNewspeedWriter");
		User userData=new User();
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,newspeedNo);
			rs=pstmt.executeQuery();
			if(rs.next()){
				userData.setNo(rs.getInt("USER_NO"));
				userData.setName(rs.getString("USER_NAME"));
				userData.setProfilePhoto(rs.getString("USER_PROFILE_PHOTO"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return userData;
	}
	
	//유저로그인
			public User loginCheck(Connection conn, User u) {
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = prop.getProperty("loginCheck");
				User data = null;
				System.out.println("DAO 는 오나요??");
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, u.getId());
					rs = pstmt.executeQuery();
					if(rs.next())
					{
						data = new User();
						data.setNo(rs.getInt("USER_NO"));
						data.setId(rs.getString("USER_ID"));
						data.setPw(rs.getString("USER_PASSWORD"));
						System.out.println(data.getNo());
						//조인해서 나머지 유저 데이터 추가
						//후에 로그인에서 TB_USER에서 state참조하여 차단로그인 체크.
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					close(rs);
					close(pstmt);
					
				}
				System.out.println("??");
				return data;
			}
			
			//네이버유저로그인
			public User loginCheckNaver(Connection conn, User u) {
				System.out.println("다오는 왔닌?");
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = prop.getProperty("loginCheckNaver");
				User data = null;
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, u.getId());
					rs = pstmt.executeQuery();
					System.out.println("펑펑"+rs);
					
					if(rs.next())
					{
						data = new User();
						data.setNo(rs.getInt("USER_NO"));
						data.setId(rs.getString("NAVER_USER_ID"));
						//System.out.println(data.getNo());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("다오가 터졌니?");
				}
				finally {
					close(rs);
					close(pstmt);
					
				}
				return data;
			}
		
		

		
		//TB_user insert
		public int insertUser(Connection conn, User u)
		{
			System.out.println("DAO:597 go");
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("insertUser");
			try {
				pstmt=conn.prepareStatement(sql);
				
				
				//INSERT INTO TB_USER (USER_NO, USER_NAME,USER_EMAIL,USER_PHONE, USER_LINK_TYPE) 
				//VALUES (SEQ_USER_NO.CURRVAL, '네이버', 'shinetia@naver.com','01077784442', 1);
				
				pstmt.setInt(1, u.getNo());
				pstmt.setString(2, u.getName());
				pstmt.setString(3, u.getEmail());
				pstmt.setString(4, u.getPhone());
				pstmt.setInt(5, u.getLinkType());
					
				result=pstmt.executeUpdate();						
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("설마 너뉘...?");
			}
			finally {
				close(pstmt);
			}
			
			return result;		
			
		}
		
		//TB_BASIC_USER insert
		public int insertUserBasic(Connection conn, User u) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertUserBasic");
			System.out.println("짜증"+u.getNo());
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, u.getNo());
				pstmt.setString(2, u.getId());
				pstmt.setString(3, u.getPw());

				result=pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}

			return result;
		}

		//TB_NAVER_USER insert
		public int insertUserNaver(Connection conn, User u) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertUserNaver");
			System.out.println("짜증"+u.getNo());
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, u.getNo());
				pstmt.setString(2, u.getId());

				result=pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}

			return result;
		}
		
		//getNewUserNo
			public int selectNextNewUserNo(Connection conn)
			{
				int result = 0;
				String sql = prop.getProperty("selectNextNewUserNo");
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						result = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return -1;
				} finally {
					close(rs);
					close(pstmt);
				}
				
				return result;
			}
			
			//getNowUserNo
			public int selectNowUserNo(Connection conn)
			{
				int result = 0;
				String sql = prop.getProperty("selectNowUserNo");					
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						result = rs.getInt(1);
						System.out.println("DAO:689 - "+result);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return -1;
				} finally {
					close(rs);
					close(pstmt);
				}
				
				return result;
			}
			
			//FindUserId
			public User findUserId(Connection conn, User u) {
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = prop.getProperty("findUserId");
				
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, u.getName());
					pstmt.setString(2, u.getEmail());
					rs = pstmt.executeQuery();
					
					if(rs.next())
					{
						u.setId(rs.getString("USER_ID"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(rs);
					close(pstmt);
					
				}
				
				return u;
			}
			
			//이메일로 회원 넘버 불러오기
			public User emailGetNo(Connection conn,User u) {
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = prop.getProperty("emailGetNo");
				
				try{
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1,u.getEmail());
					rs=pstmt.executeQuery();
					if(rs.next()){
						u.setNo(rs.getInt("USER_NO"));
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					close(rs);
					close(pstmt);
				}	
				return u;
			}

			public List<User> selectSearchUser(Connection conn, String searchStr){
			      PreparedStatement pstmt = null;
			      ResultSet rs = null;
			      String sql = prop.getProperty("selectSearchUser");
			      
			      List<User> listDB = new ArrayList();
			      User data = null;
			      
			   try {
			         
			         pstmt=conn.prepareStatement(sql);
			         pstmt.setString(1, "%" + searchStr + "%");
			         rs=pstmt.executeQuery();
			            
			         while(rs.next()) {
			            data = new User();
			               
			            data.setNo(rs.getInt("user_no"));
			            data.setName(rs.getString("user_name"));
			            data.setProfilePhoto(rs.getString("user_profile_photo"));

			               
			            listDB.add(data);
			         }

			      }catch(Exception e) {
			         e.printStackTrace();
			      }
			      finally {
			         close(rs);
			         close(pstmt);
			      }
			      return listDB;
			   }

}