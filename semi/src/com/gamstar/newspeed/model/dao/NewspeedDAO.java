package com.gamstar.newspeed.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.sql.*;
import com.gamstar.newspeed.model.vo.*;
import static common.JDBCTemplate.*;

public class NewspeedDAO {
	private Properties prop;
	
	public NewspeedDAO() {
		String fileName = NewspeedDAO.class.getResource("./newspeedquery.properties").getPath();
		prop = new Properties();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("뉴스피드 쿼리문 불러오는거 오류");
			e.printStackTrace();
		}
	}
	
	public int insertNewspeed(Connection conn, Newspeed newspeed) {
		int result = 0;
		String sql = prop.getProperty("insertNewspeed");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeed.getNo());
			pstmt.setString(2, newspeed.getContent());
			pstmt.setInt(3, newspeed.getUserNo());
			
			result = pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int insertNewspeedMedia(Connection conn, NewspeedMedia newspeedMedia) {
		int result = 0;
		String sql = prop.getProperty("insertNewspeedMedia");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedMedia.getIndex());
			pstmt.setInt(2, newspeedMedia.getNewspeedNo());
			
			System.out.println(newspeedMedia.getType() + "너왜?");
			
			pstmt.setInt(3, newspeedMedia.getType());
			pstmt.setString(4, newspeedMedia.getPath());
			
			

			result = pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertNewspeedMediaTag(Connection conn, NewspeedMediaTag newspeedMediaTag) {
		int result = 0;
		String sql = prop.getProperty("insertNewspeedMediaTag");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newspeedMediaTag.getMediaIndex());
			pstmt.setInt(2, newspeedMediaTag.getNewspeedNo());
			pstmt.setInt(3, newspeedMediaTag.getUserNo());
			pstmt.setDouble(4, newspeedMediaTag.getX());
			pstmt.setDouble(5, newspeedMediaTag.getY());
			
			result = pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int selectNextNewspeedNo(Connection conn) {
		int result = 0;
		String sql = prop.getProperty("selectNextNewspeedNo");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			System.out.println(result + "머나오냐??");
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
}
