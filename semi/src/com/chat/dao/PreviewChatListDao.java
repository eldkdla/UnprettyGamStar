package com.chat.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import com.chat.model.vo.PreiviewChatList;

public class PreviewChatListDao {
	Properties prop=new Properties();
	ResultSet rs=null;
	ResultSet rs2=null;
	ResultSet rs3=null;
	ResultSet rs4=null;
	public PreviewChatListDao() {
		String fileName=PreviewChatListDao.class.getResource("./chatquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<PreiviewChatList> previewChatList(Connection conn , int myNo) {
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		PreparedStatement pstmt4=null;
		ArrayList<PreiviewChatList> previewlist=new ArrayList<PreiviewChatList>();
		boolean flag=true;
		String sql = prop.getProperty("callPreviewChatroomNo");
		PreiviewChatList preview=new PreiviewChatList();
		//채팅방 목록
		try {
			//System.out.println(userId);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myNo);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				flag=false;
				preview=new PreiviewChatList();
				System.out.println("채팅방 번호 : "+ rs.getInt("chatroom_no"));
				preview.setChatroomNo(rs.getInt("chatroom_no"));
				//상대방 no알아내기
				sql = prop.getProperty("callPreviewOtherNo");
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, rs.getInt("chatroom_no"));
				pstmt2.setInt(2, myNo);
				rs2=pstmt2.executeQuery();
				if(rs2.next()) {
					System.out.println("상대방 번호 : "+ rs2.getInt("user_no"));
					preview.setOtherNo(rs2.getInt("user_no"));
					//상대방 정보 알아내기
					sql = prop.getProperty("callPreviewOtherimg_name");
					pstmt3 = conn.prepareStatement(sql);
					pstmt3.setInt(1, rs2.getInt("user_no"));
					rs3=pstmt3.executeQuery();
					if(rs3.next()) {
						preview.setUserProfilePhoto(rs3.getString("user_profile_photo"));
						System.out.println("상대방 사진 : "+rs3.getString("user_profile_photo"));
						preview.setUserName(rs3.getString("user_name"));
						System.out.println("상대방 이름 : "+rs3.getString("user_name"));

					}

				}
				sql = prop.getProperty("callPreviewMessageInfo");
				pstmt4=conn.prepareStatement(sql);
				//System.out.println(rs.getInt("chatroom_no"));
				pstmt4.setInt(1, rs.getInt("chatroom_no"));
				rs4=pstmt4.executeQuery();
				if(rs4.next()) {
				//	System.out.println("프리뷰 메세지 내용"+rs4.getString("chatroom_message"));
					preview.setChatPreview(rs4.getString("chatroom_message"));
				//	System.out.println("프리뷰 메세지 시간"+rs4.getDate("send_date"));
					preview.setMessageTime(rs4.getTimestamp("send_date"));
				//	System.out.println("유저번호"+rs4.getInt("user_no"));
					preview.setMessageUserNo(rs4.getInt("user_no"));
					System.out.println(rs4.getInt("read_state"));
					preview.setReadState(rs4.getInt("read_state"));

				}
				if(preview.getChatPreview()!=null) {
				previewlist.add(preview);
				Collections.sort(previewlist);
				}
				else {
					preview.setChatPreview("");
					preview.setMessageTime(new Timestamp(0));
					preview.setMessageUserNo(0);
					preview.setReadState(0);
					previewlist.add(preview);
					Collections.sort(previewlist);
				}
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			if(flag==false) {
			close(pstmt2);
			close(pstmt3);
			close(pstmt4);}

		}
		return previewlist;
	}
}
