package com.gamstar.newspeed.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

import com.gamstar.newspeed.model.vo.Newspeed;
import com.gamstar.newspeed.model.vo.NewspeedComment;
import com.gamstar.newspeed.model.vo.NewspeedLike;
import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.newspeed.model.vo.NewspeedMediaTag;
import com.gamstar.user.model.vo.User;

public class NewspeedDAO {
	private Properties prop;
	
	public NewspeedDAO() {
		String fileName = NewspeedDAO.class.getResource("./newspeedquery.properties").getPath();
		prop = new Properties();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("뉴스피드 쿼리문 불러오는거 오류");
			e.printStackTrace();
		}
	}
	
	public List<String> selectFollowNo(Connection conn,int userNo){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("DAO 통신");
		System.out.println("유저 넘버 : "+userNo);
		

		List<String> peedNo = new ArrayList();
		String sql = prop.getProperty("selectFollowPeedNo");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				peedNo.add(rs.getString("newspeed_no"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}

		return peedNo;
	}
	
	public List<String> selectTagNo(Connection conn, int userNo){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectTagNo");
		
		List<String> tagNo = new ArrayList();
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				tagNo.add(rs.getString("newspeed_no"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return tagNo;
	}
	
	public List<String> selectFollowLikeFeed(Connection conn, int userNo){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectFollowLikeFeed");
		List<String> feedNo = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				feedNo.add(rs.getString("newspeed_no"));
			}
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return feedNo;
	}
	
	public List<Newspeed> selectContent(Connection conn, List<String> peedNo){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectNewspeedContent");
		
		Newspeed data = null;
		List<Newspeed> contentList = new ArrayList();
		
		try {
			
			for(int i=0; i<peedNo.size();i++) {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, peedNo.get(i));
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				data = new Newspeed();
				data.setNo(rs.getInt("newspeed_no"));
				data.setUserNo(rs.getInt("user_no"));
				data.setContent(rs.getString("newspeed_content"));
				data.setEnable(rs.getBoolean("newspeed_enable"));
				data.setDate(rs.getDate("newspeed_date"));;
				
				
				contentList.add(data);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return contentList;
	}
	
public List<NewspeedComment> selectComment(Connection conn, List<String> peedNo){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectNewspeedComment");
		
		NewspeedComment data = null;
		List<NewspeedComment> commentList = new ArrayList();
		
		try {
			
			for(int i=0; i<peedNo.size();i++) {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, peedNo.get(i));
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					data = new NewspeedComment();
					
					data.setNewspeedNo(rs.getInt("newspeed_no"));
					data.setNo(rs.getInt("comment_no"));
					data.setRootNo(rs.getInt("comment_root_no"));
					data.setContent( rs.getString("comment_content"));
					data.setUserNo(rs.getInt("user_no"));
					data.setDate( rs.getDate("comment_date"));
					data.setEnable( rs.getInt("comment_enable"));
					data.setUserName(rs.getString("user_name"));
					
					commentList.add(data);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return commentList;
	}
	
	public List<NewspeedMedia> selectMedia(Connection conn, List<String> peedNo){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectNewspeedMedia");
		
		NewspeedMedia data = null;
		List<NewspeedMedia> mediaList = new ArrayList();
		
		try {
			
			for(int i=0; i<peedNo.size();i++) {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, peedNo.get(i));
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					data = new NewspeedMedia();
					
					data.setIndex(rs.getInt("media_index"));
					data.setNewspeedNo(rs.getInt("newspeed_no"));
					data.setType(rs.getInt("media_type"));
					data.setPath(rs.getString("media_path"));
					
					mediaList.add(data);
				}
				

			}
			

			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return mediaList;
	}
	
	public List<NewspeedLike> selectLike(Connection conn, List<String> peedNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectNewspeedLike");
		List<NewspeedLike> likeArray = new ArrayList();
		
		try {
			
			for(int i =0; i<peedNo.size();i++) {

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, peedNo.get(i));
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					NewspeedLike data = new NewspeedLike();
					data.setNo(rs.getInt("newspeed_no"));
					data.setUserNo(rs.getInt("user_no"));

					
					likeArray.add(data);
				}


			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return likeArray;
	}
	
	public ArrayList<NewspeedMediaTag> selectMediaTag(Connection conn,List<String> peedNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectNewspeedMediaTag");
		ArrayList<NewspeedMediaTag> tagContentDataArray=new ArrayList<NewspeedMediaTag>();
		
		try{
			
			for(int i=0; i<peedNo.size();i++) {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, peedNo.get(i));
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					NewspeedMediaTag data = new NewspeedMediaTag();
					
					data.setNewspeedNo(rs.getInt("newspeed_no"));
					data.setMediaIndex(rs.getInt("media_index"));
					data.setUserNo(rs.getInt("user_no"));
					data.setUserName(rs.getString("user_name"));
					data.setX(rs.getInt("X"));
					data.setY(rs.getInt("Y"));
					
					tagContentDataArray.add(data);
				}

			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return tagContentDataArray;
	} 
	
	//게시글(다중) 선택
			public ArrayList<NewspeedMedia> selectContent1(Connection conn,User user){
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String sql=prop.getProperty("selectContent1");
				ArrayList<NewspeedMedia> content1DataArray=new ArrayList<NewspeedMedia>();
				
				try{
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1,user.getNo());
					rs=pstmt.executeQuery();
					
					while(rs.next()){
						NewspeedMedia data = new NewspeedMedia();
						data.setNewspeedNo(rs.getInt("NEWSPEED_NO"));
						data.setType(rs.getInt("MEDIA_TYPE"));
						data.setPath(rs.getString("MEDIA_PATH"));
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
			
			
			
		//저장된게시물 선택
			public ArrayList<NewspeedMedia> selectStorageContent(Connection conn,User user){
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String sql=prop.getProperty("selectStorageContent");
				ArrayList<NewspeedMedia> storageContentDataArray=new ArrayList<NewspeedMedia>();
				
				try{
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1,user.getNo());
					rs=pstmt.executeQuery();
					
					while(rs.next()){
						NewspeedMedia data = new NewspeedMedia();
						data.setNewspeedNo(rs.getInt("NEWSPEED_NO"));
						data.setType(rs.getInt("MEDIA_TYPE"));
						data.setPath(rs.getString("MEDIA_PATH"));
						
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
			public ArrayList<NewspeedMedia> selectTagContent(Connection conn,User user){
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String sql=prop.getProperty("selectTagContent");
				ArrayList<NewspeedMedia> tagContentDataArray=new ArrayList<NewspeedMedia>();
				
				try{
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1,user.getNo());
					rs=pstmt.executeQuery();
					
					while(rs.next()){
						NewspeedMedia data = new NewspeedMedia();
						data.setNewspeedNo(rs.getInt("NEWSPEED_NO"));
						data.setType(rs.getInt("MEDIA_TYPE"));
						data.setPath(rs.getString("MEDIA_PATH"));
						
						tagContentDataArray.add(data);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					close(rs);
					close(pstmt);
				}
				return tagContentDataArray;
			} 
			
			//게시물 삭제
			public int deleteNewspeed(Connection conn,Newspeed newspeed){
				PreparedStatement pstmt=null;
				String sql=prop.getProperty("deleteNewspeed");
				int result=0;
				
				try{
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, newspeed.getNo());
					pstmt.setInt(2, newspeed.getUserNo());
					
					result=pstmt.executeUpdate();
					
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					close(pstmt);
				}
				
				return result;
			}
			
			//저장 게시물 삭제
			public int deleteStoredNewspeed(Connection conn,Newspeed newspeed){
				PreparedStatement pstmt=null;
				String sql=prop.getProperty("deleteStoredNewspeed");
				int result=0;
				
				try{
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, newspeed.getNo());
					pstmt.setInt(2, newspeed.getUserNo());
					
					result=pstmt.executeUpdate();
					
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					close(pstmt);
				}
				
				return result;
				
			}
			
	
	public int insertNewspeed(Connection conn, Newspeed newspeed) {
		int result = 0;
		String sql = prop.getProperty("insertNewspeed");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeed.getNo());
			pstmt.setString(2, newspeed.getContent());
			pstmt.setInt(3, newspeed.getUserNo());
			
			System.out.println(newspeed.getNo() + "왜그러세요?");
			
			result = pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int insertNewspeedMedia(Connection conn, NewspeedMedia newspeedMedia) {
		int result = 0;
		String sql = prop.getProperty("insertNewspeedMedia");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, newspeedMedia.getIndex());
			pstmt.setInt(1, newspeedMedia.getNewspeedNo());
			
			System.out.println(newspeedMedia.getType() + "너왜?");
			System.out.println(newspeedMedia.getNewspeedNo() + "너왜 자꾸없대냐?");
			
			pstmt.setInt(3, newspeedMedia.getType());
			pstmt.setString(4, newspeedMedia.getPath());
			
			

			result = pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertNewspeedMediaTag(Connection conn, NewspeedMediaTag newspeedMediaTag) {
		int result = 0;
		String sql = prop.getProperty("insertNewspeedMediaTag");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, newspeedMediaTag.getNewspeedNo());
			pstmt.setInt(2, newspeedMediaTag.getMediaIndex());
			pstmt.setInt(3, newspeedMediaTag.getUserNo());
			pstmt.setDouble(4, newspeedMediaTag.getX());
			pstmt.setDouble(5, newspeedMediaTag.getY());
			
			result = pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int selectNextNewspeedNo(Connection conn) {
		int result = 0;
		String sql = prop.getProperty("selectNextNewspeedNo");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			System.out.println(result + "머나오냐??");
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	
	public Newspeed selectNewspeed(Connection conn, int newspeedNo) {
		Newspeed newspeed = null;
		String sql = prop.getProperty("selectNewspeedNewspeedNo");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				newspeed = getNewspeed(rs);
			}


		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return newspeed;
	}
	
	
	private Newspeed getNewspeed(ResultSet rs) throws Exception{
		Newspeed n = new Newspeed();
		n.setNo(rs.getInt("NEWSPEED_NO"));
		n.setContent(rs.getString("NEWSPEED_CONTENT"));
		n.setDate(rs.getDate("NEWSPEED_DATE"));
		n.setUserNo(rs.getInt("USER_NO"));
		n.setBeforeDay(rs.getString("BEFORE"));
		
		System.out.println(n.getBeforeDay() + "게시글 며칠전으로부터 작성되었니?");
		
		return n;
	}
	

	
	public List<NewspeedMedia> selectNewspeedMediaList(Connection conn,int newspeedNo) {
		List<NewspeedMedia> newspeedMediaList = new ArrayList<NewspeedMedia>();
		String sql = prop.getProperty("selectNewspeedMediaNewspeedNo");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				NewspeedMedia newspeedMedia = getNewspeedMedia(rs);
				newspeedMediaList.add(newspeedMedia);
			}


		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return newspeedMediaList;
	}
	
	private NewspeedMedia getNewspeedMedia(ResultSet rs) throws SQLException{
		NewspeedMedia newspeedMedia = new NewspeedMedia();
		newspeedMedia.setNewspeedNo(rs.getInt("NEWSPEED_NO"));
		newspeedMedia.setIndex(rs.getInt("MEDIA_INDEX"));
		newspeedMedia.setPath(rs.getString("MEDIA_PATH"));
		newspeedMedia.setType(rs.getInt("MEDIA_TYPE"));
			
		return newspeedMedia;
	}
	
	/*
	 * 
	 * selectNewspeedNewspeedNo=SELECT * FROM TB_NEWSPEED WHERE NEWSPEED_NO = ?
selectNewspeedMediaNewspeedNo=SELECT * FROM TB_NEWSPEED_MEDIA WHERE NEWSPEED_NO = ?
selectNewspeedMediaTagNewspeedNo=SELECT * FROM TB_NEWSPEED_TAG WHERE NEWSPEED_NO = ?
selectUserNewspeedNo=SELECT U.* FROM TB_USER U JOIN TB_NEWSPEED N ON (U.USER_NO = N.USER_NO) WHERE U.USER_NO = ?
	 */
	
	public List<NewspeedMediaTag> selectNewspeedMediaTagList(Connection conn,int newspeedNo) {
		List<NewspeedMediaTag> newspeedMediaTagList = new ArrayList<NewspeedMediaTag>();
		String sql = prop.getProperty("selectNewspeedMediaTagNewspeedNo");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedNo);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				NewspeedMediaTag newspeedMediaTag= getNewspeedMediaTag(rs);
				newspeedMediaTagList.add(newspeedMediaTag);
			}


		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return newspeedMediaTagList;
	}
	
	private NewspeedMediaTag getNewspeedMediaTag(ResultSet rs) throws SQLException{
		NewspeedMediaTag newspeedMediaTag = new NewspeedMediaTag();
		newspeedMediaTag.setNewspeedNo(rs.getInt("NEWSPEED_NO"));
		newspeedMediaTag.setMediaIndex(rs.getInt("MEDIA_INDEX"));
		newspeedMediaTag.setUserNo(rs.getInt("USER_NO"));
		newspeedMediaTag.setX(rs.getDouble("X"));
		newspeedMediaTag.setY(rs.getDouble("Y"));
		newspeedMediaTag.setUserName(rs.getString("USER_NAME"));
			
		return newspeedMediaTag;
	}
	
	//selectNewspeedCommentNewspeedNo=SELECT * FROM TB_NEWSPEED_COMMENT WHERE NEWSPEED_NO = ? AND COMMENT_ENABLE = 1

	public List<NewspeedComment> selectNewspeedCommentList(Connection conn,int newspeedNo) {
		List<NewspeedComment> newspeedCommentList = new ArrayList<NewspeedComment>();
		String sql = prop.getProperty("selectNewspeedCommentNewspeedNo");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedNo);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				NewspeedComment newspeedComment= getNewspeedComment(rs);
				newspeedCommentList.add(newspeedComment);
				
				System.out.println("도데체 셀렉문 뭐쓰길래 안나오냐?"+newspeedComment);
			}


		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return newspeedCommentList;
	}
	
	

//SELECT * FROM TB_NEWSPEED_COMMENT N JOIN TB_USER U ON (N.USER_NO = U.USER_NO) WHERE NEWSPEED_NO = ? AND COMMENT_ENABLE = 1 AND U.USER_NO NOT IN(SELECT IGNORE_USER_NO FROM TB_USER_IGNORE WHERE USER_NO = ?) AND U.USER_NO NOT IN(SELECT USER_NO FROM TB_USER_IGNORE WHERE IGNORE_USER_NO = ?);

	
	public List<NewspeedComment> selectNewspeedCommentList(Connection conn,int newspeedNo, int userNo) {
		List<NewspeedComment> newspeedCommentList = new ArrayList<NewspeedComment>();
		String sql = prop.getProperty("selectNewspeedCommentIgnoreFilter");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedNo);
			pstmt.setInt(2, userNo);
			pstmt.setInt(3, userNo);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				NewspeedComment newspeedComment= getNewspeedComment(rs);
				newspeedCommentList.add(newspeedComment);
			}


		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return newspeedCommentList;
	}
	
	private NewspeedComment getNewspeedComment(ResultSet rs) throws SQLException{
		SimpleDateFormat sim1 = new SimpleDateFormat("yyMMddHHmmss");
		
		NewspeedComment newspeedComment = new NewspeedComment();
		newspeedComment.setNewspeedNo(rs.getInt("NEWSPEED_NO"));
		newspeedComment.setContent(rs.getString("COMMENT_CONTENT"));
		newspeedComment.setDate(rs.getDate("COMMENT_DATE"));
		newspeedComment.setNo(rs.getInt("COMMENT_NO"));
		newspeedComment.setRootNo(rs.getInt("COMMENT_ROOT_NO"));
		newspeedComment.setUserNo(rs.getInt("USER_NO"));
		newspeedComment.setUserName(rs.getString("USER_NAME"));
		newspeedComment.setEnable(rs.getInt("COMMENT_ENABLE"));
		newspeedComment.setBeforeDay(rs.getString("before"));

		System.out.println(newspeedComment.getBeforeDay() + "날짜는 이렇게 됩니당!");
		System.out.println(sim1.format(newspeedComment.getDate()));
		
		return newspeedComment;	
	}
	
	
	public boolean isLikeNewspeed(Connection conn, int userNo, int newspeedNo) {
		String sql = prop.getProperty("selectNewspeedIsLike");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, newspeedNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return false;
	}
	
	public boolean isStoreNewspeed(Connection conn, int userNo ,int newspeedNo){
		String sql = prop.getProperty("selectNewspeedIsStore");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, newspeedNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return false;
	}
	
	
	public int insertNewspeedLike(Connection conn, int userNo, int newspeedNo) {
		String sql = prop.getProperty("insertNewspeedLike");
		PreparedStatement pstmt = null;
		int result = 0;
		
		System.out.println(sql + "왜오류남?");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, userNo);
			pstmt.setInt(1, newspeedNo);
			result = pstmt.executeUpdate();
			

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertNewspeedStore(Connection conn, int userNo, int newspeedNo) {
		String sql = prop.getProperty("insertNewspeedStore");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, userNo);
			pstmt.setInt(1, newspeedNo);
			result = pstmt.executeUpdate();
			

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int deleteNewspeedLike(Connection conn, int userNo, int newspeedNo) {
		String sql = prop.getProperty("deleteNewspeedLike");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, userNo);
			pstmt.setInt(1, newspeedNo);
			result = pstmt.executeUpdate();
			

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteNewspeedStore(Connection conn, int userNo, int newspeedNo) {
		String sql = prop.getProperty("deleteNewspeedStore");
		PreparedStatement pstmt = null;
	
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, userNo);
			pstmt.setInt(1, newspeedNo);
			result = pstmt.executeUpdate();
			

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertNewspeedComment(Connection conn, NewspeedComment newspeedComment) {
		String sql = prop.getProperty("insertNewspeedComment");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedComment.getNewspeedNo());
			pstmt.setString(2, newspeedComment.getContent());
			pstmt.setInt(3, newspeedComment.getUserNo());
			
			result = pstmt.executeUpdate();
			

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertNewspeedRecomment(Connection conn, NewspeedComment newspeedComment) {
		String sql = prop.getProperty("insertNewspeedrecomment");
		PreparedStatement pstmt = null;
		int result = 0;
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedComment.getNewspeedNo());
			pstmt.setInt(2, newspeedComment.getRootNo());
			pstmt.setString(3, newspeedComment.getContent());
			pstmt.setInt(4, newspeedComment.getUserNo());
			
			result = pstmt.executeUpdate();
			

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteNewspeedComment(Connection conn, int newspeedCommentNo) {
		//deleteNewspeedComment=UPDATE TB_NEWSPEED_COMMENT SET COMMENT_ENABLE = 0 WHERE ((COMMENT_NO = COMMENT_ROOT_NO AND COMMENT_ROOT_NO = ?) OR(COMMENT_NO != COMMENT_ROOT_NO AND COMMENT_NO = ?))
		String sql = prop.getProperty("deleteNewspeedComment");
		PreparedStatement pstmt = null;
		int result = 0;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedCommentNo);
			pstmt.setInt(2, newspeedCommentNo);
			pstmt.setInt(3, newspeedCommentNo);
			result = pstmt.executeUpdate();
			

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
		
	}



}
