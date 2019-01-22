package com.chat.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.chat.dao.FriendListDao;
import com.gamstar.user.model.vo.User;

public class FriendListService {
	
	public ArrayList<User> callFriendList(int userNo) {
		Connection conn=getConnection();
		ArrayList<User> arr=new ArrayList<User>();
		arr = new FriendListDao().callFriendList(conn, userNo);
		close(conn);
		return arr;
		
	}

}
