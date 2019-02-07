package com.chat.dao;

import static common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import com.chat.model.vo.ChatLog;

public class ChatLogDao {

   
   ResultSet rs=null;
   ResultSet rs2=null;
   ResultSet rs3=null;

   Properties prop=new Properties();
   ChatLog log=new ChatLog();
   ArrayList<ChatLog> logarr=new ArrayList<>();
   
   public ChatLogDao() {
      String fileName=CreateChatroomNoDao.class.getResource("./chatquery.properties").getPath();
      try {
         prop.load(new FileReader(fileName));
      }catch(IOException e){
         e.printStackTrace();
         
      }
   }
   public ArrayList<ChatLog> callChatLog(Connection conn, int chatNo, int myNo) {
      PreparedStatement pstmt=null;
      PreparedStatement pstmt2=null;
      PreparedStatement pstmt3=null;

      int chatUserNo=0;
         String sql3=prop.getProperty("callPreviewChatUserNo");
         try {
            pstmt3=conn.prepareStatement(sql3);
            pstmt3.setInt(1, chatNo);
            rs3=pstmt3.executeQuery();
            if(rs3.next()) {
               chatUserNo=rs3.getInt("user_no");
               System.out.println("user_no::::::"+chatUserNo);
            }
         }catch (Exception e) {
            e.printStackTrace();
         }
         if(chatUserNo!=myNo) {
      String sql2=prop.getProperty("updateReadState");
      int result=0;
      try 
      {
         System.out.println(sql2);
         pstmt2=conn.prepareStatement(sql2);
         pstmt2.setInt(1, chatNo);
         System.out.println("chat NO:" + chatNo);
         result=pstmt2.executeUpdate();
         System.out.println("result : "+result);
         
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      finally
      {
        // close(pstmt2);
      }
      if(result==0) {
         rollback(conn);
         System.out.println("rollback");
      }
      else {
         commit(conn);
         System.out.println("commit");

      }}
      System.out.println("채팅로그dao");
      String sql=prop.getProperty("callChatLog");
      int a=0;
      try {
         
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, chatNo);
         rs=pstmt.executeQuery();
         while(rs.next()) {
            log=new ChatLog();/*
            String sql2=prop.getProperty("callMyUserNo");
            pstmt2=conn.prepareStatement(sql2);
            pstmt2.setString(1, myId);
            rs2=pstmt2.executeQuery();
            if(rs2.next()) {
               //System.out.println(rs2.getInt("user_no"));
*/               
            log.setMyNo(myNo);
            System.out.println("myNo"+myNo);
            log.setChatNo(chatNo);
            log.setUserNo(rs.getInt("user_no"));
            log.setChatroomMessage(rs.getString("chatroom_message"));
            log.setSend_date(rs.getTimestamp("send_date"));
            //System.out.println(log);
            logarr.add(log);
            a=1;
         }
         if(a==0) {
            log=new ChatLog();
            log.setMyNo(myNo);
            log.setChatNo(chatNo);
            log.setUserNo(myNo);
            log.setChatroomMessage("");
            log.setSend_date(new Timestamp(0));
            //System.out.println(log);
            logarr.add(log);
         }
      }
      
      catch (Exception e) {
         e.printStackTrace();
      }
      finally
      {
         close(pstmt);
      }
      System.out.println("logarr"+logarr);
      return logarr;
   }
}