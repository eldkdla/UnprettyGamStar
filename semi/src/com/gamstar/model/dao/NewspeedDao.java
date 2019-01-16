package com.gamstar.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import com.gamstar.model.vo.NewspeedMedia;
import com.gamstar.model.vo.Newspeed;
import com.gamstar.model.vo.User;

public class NewspeedDao {
	
	Properties prop=new Properties();
	
	public NewspeedDao(){
		String fileName=UserDao.class.getResource("./newspeedquery.properties").getPath();
		try{
			prop.load(new FileReader(fileName));
		}catch (Exception e) {
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
		
}
