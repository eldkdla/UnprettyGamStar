package com.chat.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.chat.dao.InputChatDao;
import com.chat.dao.UpdateReadStateDao;

public class UpdateReadStateService {
	public void updateReadState(int chatNo) {
		Connection conn=getConnection();
		int result=new UpdateReadStateDao().updateReadState(conn, chatNo);
		if(result==0) {
			System.out.println("스테이트 변경 실패");
			rollback(conn);
		}
		else {
			System.out.println("커밋");

			commit(conn);
		}
		close(conn);
	}
}
