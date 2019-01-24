package com.chat.service;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.chat.dao.FindFriendDao;
import com.gamstar.user.model.vo.User;

public class FindFriendService {
	
	public ArrayList<User> findFriend(String inputFriendName, int myNo) {
		Connection conn=getConnection();
		ArrayList<User> arr=new ArrayList<User>();
		arr=new FindFriendDao().findFriend(conn,inputFriendName,myNo);
		return arr;
	}

}
