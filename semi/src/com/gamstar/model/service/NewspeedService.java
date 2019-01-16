package com.gamstar.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gamstar.model.dao.NewspeedDao;
import com.gamstar.model.vo.Media;
import com.gamstar.model.vo.User;

public class NewspeedService {

	//게시글(다중) 선택 
		public ArrayList<Media> selectContent1(Connection conn,User user){
			
			ArrayList<Media> content1DataArray=new NewspeedDao().selectContent1(conn,user);

			return content1DataArray;
		}
		
		//저장된 게시물 선택
		public ArrayList<Media> selectStorageContent(Connection conn,User user){
		
		ArrayList<Media> storageContentDataArray=new NewspeedDao().selectStorageContent(conn,user);

		return storageContentDataArray;
		}
		
		//태그된 게시물 선택
		public ArrayList<Media> selectTagContent(Connection conn,User user){
		
		ArrayList<Media> tagContentDataArray=new NewspeedDao().selectTagContent(conn,user);

		return tagContentDataArray;
		}
	
}
