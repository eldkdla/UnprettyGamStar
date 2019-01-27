package com.gamstar.user.support.service;

import java.sql.Connection;

import com.gamstar.user.support.dao.AnswerSupportDataDao;
import com.gamstar.user.support.model.vo.AnswerSupportData;

import static common.JDBCTemplate.*;
public class AnswerSupportDataService {
	
	public AnswerSupportData callAnswerSupportData(int supportNo) {
		Connection conn=getConnection();
		AnswerSupportData as=new AnswerSupportData();
		as=new AnswerSupportDataDao().AnswerSupportDataDao(conn, supportNo);
		close(conn);
		return as;
	}
}
