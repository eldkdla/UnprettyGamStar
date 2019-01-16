package com.gamstar.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gamstar.model.dao.NewspeedDao;
import com.gamstar.model.vo.NewspeedMedia;
import com.gamstar.model.vo.Newspeed;
import com.gamstar.model.vo.User;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;

public class NewspeedService {

		//게시글(다중) 선택 
		public ArrayList<NewspeedMedia> selectContent1(Connection conn,User user){
			
		ArrayList<NewspeedMedia> content1DataArray=new NewspeedDao().selectContent1(conn,user);

		return content1DataArray;
		}
		
		//저장된 게시물 선택
		public ArrayList<NewspeedMedia> selectStorageContent(Connection conn,User user){
		
		ArrayList<NewspeedMedia> storageContentDataArray=new NewspeedDao().selectStorageContent(conn,user);

		return storageContentDataArray;
		}
		
		//태그된 게시물 선택
		public ArrayList<NewspeedMedia> selectTagContent(Connection conn,User user){
		
		ArrayList<NewspeedMedia> tagContentDataArray=new NewspeedDao().selectTagContent(conn,user);

		return tagContentDataArray;
		}
		
		//저장게시물 삭제
		public int deleteStoredNewspeed(Newspeed newspeed){
			Connection conn=getConnection();
			
			int result=new NewspeedDao().deleteStoredNewspeed(conn,newspeed);
			
			try {
				if(result!=0){
					conn.commit();				
				}else{
					conn.rollback();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
				
			close(conn);
			return result;
		}
	
}
