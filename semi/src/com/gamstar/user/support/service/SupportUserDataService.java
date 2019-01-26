package com.gamstar.user.support.service;

import java.sql.Connection;
import java.util.List;
import static common.JDBCTemplate.*;

import com.gamstar.user.support.dao.SupportUserDataDao;
import com.gamstar.user.support.model.vo.SupportUserData;

public class SupportUserDataService {
	
	public void insertSupportUserData(SupportUserData userData, List fileNamelist,List fileOriNamelist, List fileTypelist) {
		Connection conn=getConnection();
		new SupportUserDataDao().insertSupportUserData(conn, userData, fileNamelist, fileOriNamelist, fileTypelist);
		
		close(conn);
	}

}
