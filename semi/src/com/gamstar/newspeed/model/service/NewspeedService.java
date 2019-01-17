package com.gamstar.newspeed.model.service;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import com.gamstar.newspeed.model.dao.NewspeedDAO;
import com.gamstar.newspeed.model.vo.*;
import static common.JDBCTemplate.*;

public class NewspeedService {
	public static final int NEWSPEED_CONTENT_INSERT_ERROR = -100;
	public static final int NEWSPEED_MEDIA_INSERT_ERROR = -200;
	public static final int NEWSPEED_MEDIA_TAG_INSERT_ERROR = -300;
	
	private NewspeedDAO newspeedDAO;

	public NewspeedService() {
		newspeedDAO = new NewspeedDAO();
	}

	public int insertNewspeedData(Newspeed newspeed, List<NewspeedMedia> newspeedMediaList,
			List<NewspeedMediaTag> newspeedMediaTagList) {
		Connection conn = getConnection();
		int result = 0;
		int newspeedNo = newspeedDAO.selectNextNewspeedNo(conn);
		
		newspeed.setNo(newspeedNo);
		result = newspeedDAO.insertNewspeed(conn, newspeed);

		if (result < 1) {
			rollback(conn);
			close(conn);
			
			return result;
		} 
		
		for (int i = 0; i < newspeedMediaList.size(); i++) {
			NewspeedMedia newspeedMedia = newspeedMediaList.get(i);
			System.out.println(newspeedMedia + "d?");
			
			newspeedMedia.setNewspeedNo(newspeedNo);
			result = newspeedDAO.insertNewspeedMedia(conn, newspeedMedia);
			
			if (result < 1) {
				rollback(conn);
				close(conn);
				
				return result;
			}
		}
		
		for (int i = 0; i < newspeedMediaTagList.size(); i++) {
			NewspeedMediaTag newspeedMediaTag = newspeedMediaTagList.get(i);
			
			newspeedMediaTag.setNewspeedNo(newspeedNo);
			
			System.out.println(newspeedMediaTag);
			result = newspeedDAO.insertNewspeedMediaTag(conn, newspeedMediaTag);
			
			if (result < 1) {
				rollback(conn);
				close(conn);
				
				return result;
			}
		}
		

		commit(conn);
		close(conn);
		
		return result;

	}

}
