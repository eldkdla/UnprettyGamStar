package com.gamstar.user.support.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.gamstar.user.support.dao.ConfirmSupportDataDao;
import com.gamstar.user.support.model.vo.ConfirmSupportData;

public class ConfirmSupportDataService {
	
	public ArrayList<ConfirmSupportData> callConfirmSupportData(int myNo){
		Connection conn=getConnection();
		ArrayList<ConfirmSupportData> csArr=new ArrayList<ConfirmSupportData>();
		csArr=new ConfirmSupportDataDao().callConfirmSupportData(conn, myNo);
		close(conn);
		return csArr;
	}

}
