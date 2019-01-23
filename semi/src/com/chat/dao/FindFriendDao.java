package com.chat.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import com.gamstar.user.model.vo.User;

public class FindFriendDao {
	Properties prop=new Properties();
	ResultSet rs=null;
	ArrayList<User> arr=new ArrayList<User>();
	User m=new User();
	
	public FindFriendDao() {
		String fileName=FindFriendDao.class.getResource("./chatquery.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public ArrayList<User> findFriend(Connection conn,String inputFriendName, int myNo)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("findFriend");
		try 
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myNo);
			pstmt.setString(2, "%"+inputFriendName+"%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				m=new User();
				m.setName(rs.getString("user_name"));	 
				m.setProfilePhoto(rs.getString("user_profile_photo"));
				m.setNo(rs.getInt("user_no"));
				arr.add(m);
				}
			/*for(int i=0;i<arr.size();i++) {
				System.out.println(arr.get(i).getUserName());
				System.out.println(arr.get(i).getUserProfilePhoto());
				System.out.println(arr.get(i).getUserNo());
			}*/
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return arr;
	}
}