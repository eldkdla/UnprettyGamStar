package com.chat.dao;

import java.io.FileReader;
import static common.JDBCTemplate.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;



public class CreateChatroomNoDao {

	Properties prop=new Properties();
	ResultSet rs=null;
	ResultSet rs2=null;
	ResultSet rs3=null;
	ResultSet rs4=null;


	public CreateChatroomNoDao() {
		String fileName=CreateChatroomNoDao.class.getResource("./chatquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int[] callPreviewMessage(Connection conn, int myNo, String[] otherUserNo) {
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		PreparedStatement pstmt4=null;
		int result_chatroomNoArr[]=new int[2];
		int result=0;
		int chatroomNo=0;

		int myNo2=0;
		String previewMsg=null;
		String sql = prop.getProperty("callChatRoomNo");
		//선택한 사람이 한 명일 때
		if (otherUserNo.length == 1) {
			try {
				int oUserNo = Integer.parseInt(otherUserNo[0]);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, myNo);
				pstmt.setInt(2, oUserNo);
				System.out.println(myNo +" : "+ oUserNo);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println(rs.getInt("chatroom_no"));
					//previewMsg=callPreviewMessage(conn, rs.getInt("chatroom_no"));
					result=-1000;
					chatroomNo=rs.getInt("chatroom_no");
					
				} else {
					/*sql=prop.getProperty("callMyUserNo");
					pstmt2=conn.prepareStatement(sql);
					pstmt2.setString(1, myId);
					rs2=pstmt2.executeQuery();
					if(rs2.next()) {
						myNo2=rs2.getInt("user_no");*/
						System.out.println(myNo);
						sql=prop.getProperty("callChatroomNextval");
						pstmt3=conn.prepareStatement(sql);
						rs3=pstmt3.executeQuery();
						if(rs3.next()) {
							sql=prop.getProperty("insertChatRoom");
							pstmt4=conn.prepareStatement(sql);
							System.out.println(rs3.getInt("NEXTVAL"));
							pstmt4.setInt(1, rs3.getInt("NEXTVAL"));
							pstmt4.setInt(2, rs3.getInt("NEXTVAL"));
							pstmt4.setInt(3, myNo);
							pstmt4.setInt(4, rs3.getInt("NEXTVAL"));
							pstmt4.setInt(5, oUserNo);
							result=pstmt4.executeUpdate();
							chatroomNo=rs3.getInt("NEXTVAL");

						}
						
					

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				if(result==-1000) {
				close(pstmt);}
				else {
				close(pstmt3);
				close(pstmt4);}


			}
		}
		//선택한 사람이 한 명 이상일 때
		else {
			
		}
		result_chatroomNoArr[0]=result;
		result_chatroomNoArr[1]=chatroomNo;
		System.out.println("결과: "+result_chatroomNoArr[0]);
		System.out.println("채팅바No: "+result_chatroomNoArr[1]);
		return result_chatroomNoArr;

	}
	
	
}
