package com.gamstar.newspeed.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.gamstar.newspeed.model.dao.NewspeedDAO;
import com.gamstar.newspeed.model.vo.Newspeed;
import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.newspeed.model.vo.NewspeedMediaTag;
import com.gamstar.user.model.vo.User;

public class NewspeedService {
	public static final int NEWSPEED_CONTENT_INSERT_ERROR = -100;
	public static final int NEWSPEED_MEDIA_INSERT_ERROR = -200;
	public static final int NEWSPEED_MEDIA_TAG_INSERT_ERROR = -300;
	
	private NewspeedDAO newspeedDAO;

	public NewspeedService() {
		newspeedDAO = new NewspeedDAO();
	}
	
			//게시글(다중) 선택 
			public ArrayList<NewspeedMedia> selectContent1(Connection conn,User user){
				
			ArrayList<NewspeedMedia> content1DataArray=new NewspeedDAO().selectContent1(conn,user);

			return content1DataArray;
			}
			
			//저장된 게시물 선택
			public ArrayList<NewspeedMedia> selectStorageContent(Connection conn,User user){
			
			ArrayList<NewspeedMedia> storageContentDataArray=new NewspeedDAO().selectStorageContent(conn,user);

			return storageContentDataArray;
			}
			
			//태그된 게시물 선택
			public ArrayList<NewspeedMedia> selectTagContent(Connection conn,User user){
			
			ArrayList<NewspeedMedia> tagContentDataArray=new NewspeedDAO().selectTagContent(conn,user);

			return tagContentDataArray;
			}
			
			//저장게시물 삭제
			public int deleteStoredNewspeed(Newspeed newspeed){
				Connection conn=getConnection();
				
				int result=new NewspeedDAO().deleteStoredNewspeed(conn,newspeed);
				
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
			

	public int insertNewspeedData(Newspeed newspeed, List<NewspeedMedia> newspeedMediaList,
			List<NewspeedMediaTag> newspeedMediaTagList) {
		Connection conn = getConnection();
		int result = 0;
		int newspeedNo = newspeedDAO.selectNextNewspeedNo(conn);
		
		newspeed.setNo(newspeedNo);
		result = newspeedDAO.insertNewspeed(conn, newspeed);

		if (result < 1) {
			rollback(conn);
			close(conn);
			
			return result;
		} 
		
		for (int i = 0; i < newspeedMediaList.size(); i++) {
			NewspeedMedia newspeedMedia = newspeedMediaList.get(i);
			System.out.println(newspeedMedia + "d?");
			
			newspeedMedia.setNewspeedNo(newspeedNo);
			result = newspeedDAO.insertNewspeedMedia(conn, newspeedMedia);
			
			if (result < 1) {
				rollback(conn);
				close(conn);
				
				return result;
			}
		}
		
		for (int i = 0; i < newspeedMediaTagList.size(); i++) {
			NewspeedMediaTag newspeedMediaTag = newspeedMediaTagList.get(i);
			
			newspeedMediaTag.setNewspeedNo(newspeedNo);
			
			System.out.println(newspeedMediaTag);
			result = newspeedDAO.insertNewspeedMediaTag(conn, newspeedMediaTag);
			
			if (result < 1) {
				rollback(conn);
				close(conn);
				
				return result;
			}
		}
		

		commit(conn);
		close(conn);
		
		return result;

	}

}
