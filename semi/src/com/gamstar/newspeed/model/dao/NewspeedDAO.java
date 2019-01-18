package com.gamstar.newspeed.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import com.gamstar.newspeed.model.vo.Newspeed;
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
			pstmt.setInt(1, newspeedMedia.getIndex());
			pstmt.setInt(2, newspeedMedia.getNewspeedNo());
			
			System.out.println(newspeedMedia.getType() + "너왜?");
			
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
			pstmt.setInt(1, newspeedMediaTag.getMediaIndex());
			pstmt.setInt(2, newspeedMediaTag.getNewspeedNo());
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
}
