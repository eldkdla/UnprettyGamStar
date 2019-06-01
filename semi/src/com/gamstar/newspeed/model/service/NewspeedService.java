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
import com.gamstar.newspeed.model.vo.NewspeedLike;
import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.newspeed.model.vo.NewspeedMediaTag;
import com.gamstar.user.model.dao.UserDao;
import com.gamstar.user.model.vo.User;

public class NewspeedService {
	public static final int NEWSPEED_CONTENT_INSERT_ERROR = -100;
	public static final int NEWSPEED_MEDIA_INSERT_ERROR = -200;
	public static final int NEWSPEED_MEDIA_TAG_INSERT_ERROR = -300;
	
	public static final int NEWSPEED_LIKE_INSERT_OK = 200;
	public static final int NEWSPEED_LIKE_DELETE_OK = 300;
	
	public static final int NEWSPEED_STORE_INSERT_OK = 400;
	public static final int NEWSPEED_STORE_DELETE_OK = 500;
	
	private NewspeedDAO newspeedDAO;
	private UserDao userDAO;

	public NewspeedService() {
		newspeedDAO = new NewspeedDAO();
		userDAO = new UserDao();
	}
	
			public List<String> selectNewspeedNo(int userNo,int limite) {
				
				Connection conn = getConnection();
				
		
				List<User> user = new ArrayList();
				
				List<String> tagNo = new NewspeedDAO().selectTagNo(conn, userNo);
				List<String> followLikePeed = new NewspeedDAO().selectFollowLikeFeed(conn, userNo);
				List<String> followNo = new NewspeedDAO().selectFollowNo(conn, userNo);
		
				
				System.out.println("내가 이미지태그된 게시물 피드 넘버 : "+tagNo);
				System.out.println("내가 팔로우한 사람이 좋아요 눌른 피드넘버 : "+followLikePeed);
				System.out.println("내가 팔로우 한사람들의 피드넘버 : "+followNo);
				
		
				List<String> peedNo = peedSet(tagNo, followLikePeed, followNo, limite);
				System.out.println("중복 제거 피드 : "+peedNo);
				
				close(conn);
				
				return peedNo;
			}
			
			public List<Newspeed> selectContent(int userNo,int limite){
				
				List<String> peedNo = selectNewspeedNo(userNo, limite);
				Connection conn = getConnection();
				List<Newspeed> contentList = new NewspeedDAO().selectContent(conn, peedNo); //콘텐트 불러오기
				close(conn);
				return contentList;
			}
			
			public List<NewspeedComment> selectComment(int userNo,int limite){
				
				List<String> peedNo = selectNewspeedNo(userNo,limite);
				Connection conn = getConnection();
				List<NewspeedComment> commentList = new NewspeedDAO().selectComment(conn, peedNo); //코멘트 불러오기
				close(conn);
				return commentList;
			}
			
			public List<NewspeedMedia> selectMedia(int userNo,int limite){
				
				List<String> peedNo = selectNewspeedNo(userNo,limite);
				Connection conn = getConnection();
				List<NewspeedMedia> mediaList = new NewspeedDAO().selectMedia(conn, peedNo); //미디어 불러오기
				close(conn);
				return mediaList;
			}
			
			public List<NewspeedLike> selectLike(int userNo,int limite){
				
				List<String> peedNo = selectNewspeedNo(userNo,limite);
				Connection conn = getConnection();
				List<NewspeedLike> likeList = new NewspeedDAO().selectLike(conn, peedNo); //좋아요 불러오기
				close(conn);
				return likeList;
			}
			
			public List<NewspeedMediaTag> selectMediaTag(int userNo,int limite){
				
				List<String> peedNo = selectNewspeedNo(userNo,limite);
				Connection conn = getConnection();
				List<NewspeedMediaTag> tagList = new NewspeedDAO().selectMediaTag(conn, peedNo); //이미지 태그 불러오기
				close(conn);
				return tagList;
			}
			
			
			
			
			
			public List<String> peedSet(List<String> tagNo, List<String> followLikePeed, List<String> followNo,int limite){ //피드번호 셋팅 메소드
				
		
				List<String> resultList = new ArrayList<String>();
				System.out.println("들어옴?"+limite);
				
				for(int i=0; i<1; i++) {

					tagSet:
					for(int j=0; j<tagNo.size();j++) {
						
						if(resultList.size() >= limite) {
							break tagSet;
						}
						
						if(!resultList.contains(tagNo.get(j))) {
							resultList.add(tagNo.get(j));
						}	
					}
				
					followLikePeedSet:
					for(int j=0; j<followLikePeed.size();j++) {
						
						if(resultList.size() >= limite) {
							break followLikePeedSet;
						}
						
						if(!resultList.contains(followLikePeed.get(j))) {
							resultList.add(followLikePeed.get(j));
						}
					}
					
					followNoSet:
					for(int j=0; j<followNo.size();j++) {
						
						if(resultList.size() >= limite) {
							break followNoSet;
						}
						
						if(!resultList.contains(followNo.get(j))) {
							resultList.add(followNo.get(j));
						}
					}
				}	
		   
				return resultList;
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
			
			//게시물 삭제
			public int deleteNewspeed(Newspeed newspeed){
				Connection conn=getConnection();
				
				int result=new NewspeedDAO().deleteNewspeed(conn,newspeed);
				
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
			
			return NewspeedService.NEWSPEED_CONTENT_INSERT_ERROR;
		} 
		
		for (int i = 0; i < newspeedMediaList.size(); i++) {
			NewspeedMedia newspeedMedia = newspeedMediaList.get(i);
			System.out.println(newspeedMedia + "d?");
			
			newspeedMedia.setNewspeedNo(newspeedNo);
			result = newspeedDAO.insertNewspeedMedia(conn, newspeedMedia);
			
			if (result < 1) {
				rollback(conn);
				close(conn);
				
				return NewspeedService.NEWSPEED_MEDIA_INSERT_ERROR;
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
				
				return NewspeedService.NEWSPEED_MEDIA_TAG_INSERT_ERROR;
			}
		}
		

		commit(conn);
		close(conn);
		
		return result;

	}
	
	public Newspeed selectNewspeed(int newspeedNo) {
		Connection conn = getConnection();
		Newspeed newspeed = newspeedDAO.selectNewspeed(conn, newspeedNo);
		
		close(conn);
		
		return newspeed;
	}
	
	public List<NewspeedMedia> selectNewspeedMediaList(int newspeedNo) {
		Connection conn = getConnection();
		List<NewspeedMedia> newspeedMediaList = newspeedDAO.selectNewspeedMediaList(conn, newspeedNo);
		
		close(conn);
		
		return newspeedMediaList;
	}
	
	public List<NewspeedMediaTag> selectNespeedMediaTagList(int newspeedNo) {
		Connection conn = getConnection();
		List<NewspeedMediaTag> newspeedMediaTagList = newspeedDAO.selectNewspeedMediaTagList(conn, newspeedNo);
		
		close(conn);
		
		return newspeedMediaTagList;
	}
	
	public List<NewspeedComment> selectNewspeedCommentList(int newspeedNo) {
		Connection conn = getConnection();
		List<NewspeedComment> newspeedCommentList = newspeedDAO.selectNewspeedCommentList(conn, newspeedNo);
		
		System.out.println("어케나오나 보자 ㅎㅎ"+newspeedCommentList);
		
		close(conn);
		
		return newspeedCommentList;
	}
	
	public List<NewspeedComment> selectNewspeedCommentList(int newspeedNo, int userNo) {
		Connection conn = getConnection();
		List<NewspeedComment> newspeedCommentList = newspeedDAO.selectNewspeedCommentList(conn, newspeedNo, userNo);
		
		System.out.println("야 정렬순서좀 보자" + newspeedCommentList);
		
		close(conn);
		
		return newspeedCommentList;
	}
	
	public boolean isLiked(int userNo,int newspeedNo) {
		Connection conn = getConnection();
		boolean result = newspeedDAO.isLikeNewspeed(conn, userNo, newspeedNo);
		
		close(conn);
				
		return result;
	}
	
	public boolean isStored(int userNo,int newspeedNo) {
		Connection conn = getConnection();
		boolean result = newspeedDAO.isStoreNewspeed(conn, userNo, newspeedNo);
		
		close(conn);
				
		return result;
	}
	
	public int insertLike(int userNo, int newspeedNo) {
		Connection conn = getConnection();
		boolean result = newspeedDAO.isLikeNewspeed(conn, userNo, newspeedNo);
		int res = 0;
		
		if (!result) {
			res = newspeedDAO.insertNewspeedLike(conn, userNo, newspeedNo);
			res = NewspeedService.NEWSPEED_LIKE_INSERT_OK;
			System.out.println("정신차리자...");
		} else {
			res = newspeedDAO.deleteNewspeedLike(conn, userNo, newspeedNo);
			res = NewspeedService.NEWSPEED_LIKE_DELETE_OK;
			System.out.println("너도....");
		}
		
		if (res < 1) {
			rollback(conn);
		} else {
			commit(conn);
		}
		
		close(conn);
		
		return res;
	}
	
	public int insertStore(int userNo, int newspeedNo) {
		Connection conn = getConnection();
		boolean result = newspeedDAO.isStoreNewspeed(conn, userNo, newspeedNo);
		int res = 0;
		
		if (!result) {
			res = newspeedDAO.insertNewspeedStore(conn, userNo, newspeedNo);
			res = NewspeedService.NEWSPEED_STORE_INSERT_OK;
		} else {
			res = newspeedDAO.deleteNewspeedStore(conn, userNo, newspeedNo);
			res = NewspeedService.NEWSPEED_STORE_DELETE_OK;
		}
		
		if (res < 1) {
			rollback(conn);
		} else {
			commit(conn);
		}
		
		close(conn);
		
		return res;
		
	}
	
	public int insertNewspeedComment(NewspeedComment newspeedComment) {
		Connection conn = getConnection();
		int result = newspeedDAO.insertNewspeedComment(conn,newspeedComment);
	
		if (result < 1) {
			rollback(conn);
		} else {
			commit(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int insertNewspeedRecomment(NewspeedComment newspeedComment) {
		Connection conn = getConnection();
		int result = newspeedDAO.insertNewspeedRecomment(conn,newspeedComment);
	
		if (result < 1) {
			rollback(conn);
		} else {
			commit(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int deleteNewspeedComment(int newspeedCommentNo) {
		Connection conn = getConnection();
		int result = newspeedDAO.deleteNewspeedComment(conn, newspeedCommentNo);
				
		if (result < 1) {
			rollback(conn);
		} else {
		    commit(conn);
		}
				
		close(conn);
				
		return result;
	}
	

	
	
}
