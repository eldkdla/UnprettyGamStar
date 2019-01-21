package com.gamstar.newspeed.model.dao;

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

import com.gamstar.newspeed.model.vo.Newspeed;
import com.gamstar.newspeed.model.vo.NewspeedComment;
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
		NewspeedComment newspeedComment = new NewspeedComment();
		newspeedComment.setNewspeedNo(rs.getInt("NEWSPEED_NO"));
		newspeedComment.setContent(rs.getString("COMMENT_CONTENT"));
		newspeedComment.setDate(rs.getDate("COMMENT_DATE"));
		newspeedComment.setNo(rs.getInt("COMMENT_NO"));
		newspeedComment.setRootNo(rs.getInt("COMMENT_ROOT_NO"));
		newspeedComment.setUserNo(rs.getInt("USER_NO"));
		
		return newspeedComment;	
	}

}
