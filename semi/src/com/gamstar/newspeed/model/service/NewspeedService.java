package com.gamstar.newspeed.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gamstar.newspeed.model.dao.NewspeedDAO;
import com.gamstar.newspeed.model.vo.Newspeed;
import com.gamstar.newspeed.model.vo.NewspeedComment;
import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.newspeed.model.vo.NewspeedMediaTag;
import com.gamstar.user.model.dao.UserDao;
import com.gamstar.user.model.vo.User;

public class NewspeedService {
	public static final int NEWSPEED_CONTENT_INSERT_ERROR = -100;
	public static final int NEWSPEED_MEDIA_INSERT_ERROR = -200;
	public static final int NEWSPEED_MEDIA_TAG_INSERT_ERROR = -300;
	
	private NewspeedDAO newspeedDAO;
	private UserDao userDAO;

	public NewspeedService() {
		newspeedDAO = new NewspeedDAO();
		userDAO = new UserDao();
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
	
	public JSONObject selectNewspeedJSON(int newspeedNo) {		
		Connection conn = getConnection();
		
		Newspeed newspeed = newspeedDAO.selectNewspeed(conn, newspeedNo);
		List<NewspeedMedia> newspeedMediaList = newspeedDAO.selectNewspeedMediaList(conn, newspeedNo);
		List<NewspeedMediaTag> newspeedMediaTagList = newspeedDAO.selectNewspeedMediaTagList(conn, newspeedNo);
		List<NewspeedComment> newspeedCommentList = newspeedDAO.selectNewspeedCommentList(conn, newspeedNo);
		User writer = userDAO.selectNewspeedWriter(conn, newspeedNo);
		JSONObject newspeedJSON = getFileInNewspeed(newspeed, newspeedMediaList, newspeedMediaTagList);
		inputNewspeedWriterInJSON(writer, newspeedJSON);
		
		return newspeedJSON;	
	}
	
	//'{"commentList":[{"userNo":1,"userName":"임태완","profilephoto":"basic_profile_photo.png" ,"commenftContent":"날씨가 너무 춥네요~", "commentIndex":1},{"userNo":1,"userName":"임태완","profilephoto":"basic_profile_photo.png" ,"commentContent":"날씨가 너무 춥네요~", "commentIndex":1}],"fileList":[{"mediaIndex":1,"fileName":"btn_add_media.png","tagList":[{"tagIndex":0,"mediaIndex":1,"x":"0.03350970017636689","y":"0.4192790451074912","userNo":"6", "userName":"반가워"}]}, {"mediaIndex":2,"fileName":"m-series-2015-upscaling-4k-large.jpg","tagList":[{"tagIndex":0,"mediaIndex":2,"x":"0.28924162257495595","y":"0.44327739509527836","userNo":"6","userName":"반가워"}]}],
	//"content":"asdasdsdadsadsadsadsadsadasdsadsadsadsadsadsadsadsadsadsasd", "newspeedNo":"1","userNo":"1", "userName":"임태완", "profilephoto":"basic_profile_photo.png"}');
	
	private void inputNewspeedWriterInJSON(User writer, JSONObject newspeedJSON) {
		newspeedJSON.put("userNo", writer.getNo());
		newspeedJSON.put("userName", writer.getName());
		newspeedJSON.put("profilephoto", writer.getProfilePhoto());
		
		System.out.println(writer.getProfilePhoto() + "프사인데 있음?");
	}
	
	
	private JSONObject getFileInNewspeed(Newspeed newspeed,List<NewspeedMedia> mediaList, List<NewspeedMediaTag> mediaTagList) {
		JSONObject newspeedJSONObject = new JSONObject();
		JSONArray mediaListJSONArray = new JSONArray();
		
		newspeedJSONObject.put("content", newspeed.getContent());
		newspeedJSONObject.put("userNo",newspeed.getUserNo());
		newspeedJSONObject.put("newspeedNo", newspeed.getNo());
		
		for (int i = 0; i < mediaList.size(); i++) {
			mediaListJSONArray.add(getMediaInTagList(mediaList.get(i), mediaTagList));
		}
		
		newspeedJSONObject.put("fileList",mediaListJSONArray);
		
		return newspeedJSONObject;
	}
	

	private JSONObject getMediaInTagList(NewspeedMedia newspeedMedia, List<NewspeedMediaTag> tagList) {
		JSONObject json = new JSONObject();
		JSONArray tagListJSONArray = new JSONArray();
		int index = newspeedMedia.getIndex();
		
		for (int i = 0; i < tagList.size(); i++) {
			NewspeedMediaTag tag = tagList.get(i);
			if (index == tag.getNewspeedNo()) {
				tagListJSONArray.add(tag);
			}
		}
		//"fileList":[{"mediaIndex":1,"fileName":"btn_add_media.png","tagList":[{"tagIndex":0,"mediaIndex":1,"x":"0.03350970017636689","y":"0.4192790451074912","userNo":"6", "userName":"반가워"}]},
		json.put("mediaIndex", index);
		json.put("fileName", newspeedMedia.getPath());
		json.put("tagList", tagListJSONArray);
		
		
		return json;
	}
}
