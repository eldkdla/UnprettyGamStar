package com.gamstar.admin.manager.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.gamstar.admin.manager.model.dao.AdminManagerDao;
import com.gamstar.admin.user.model.dao.AdminUserDao;
import com.gamstar.user.model.vo.User;

public class AdminManagerService {
	
//admin member 페이징
	public List<User> selectAdminList(int cPage, int numPerPage)
	{
		Connection conn=getConnection();
		List<User> list=new AdminManagerDao().selectAdminList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	
	public int selectAdminCount()
	{
		Connection conn=getConnection();
		int result=new AdminManagerDao().selectAdminCount(conn);
		close(conn);
		return result;
	}
	
	//admin delete
	public int deleteAdmin(int adminNo)
	{
		Connection conn=getConnection();
		int result=new AdminManagerDao().deleteAdmin(conn, adminNo);
		
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
			
		close(conn);
		return result;
	}
	
	//admin create
	public int createAdmin(User admin)
	{
		Connection conn=getConnection();
		int result=new AdminManagerDao().nextAdminNum(conn);
		admin.setNo(result);
		result=new AdminManagerDao().createAdmin(conn, admin);
		
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int createBestAdmin(User admin)
	{
		Connection conn=getConnection();
		int result=new AdminManagerDao().createBestAdmin(conn, admin);
		
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	//admin search
	public List<User> searchAdminList(int cPage, int numPerPage, String searchType, String searchKeyword)
	{
		Connection conn=getConnection();
		List<User> list=null;
		switch(searchType)
		{
			case "id" : list=new AdminManagerDao().searchIdList(conn, cPage, numPerPage, searchKeyword);break;
			case "name" :list=new AdminManagerDao().searchNameList(conn, cPage, numPerPage, searchKeyword);break;
		}
		
		close(conn);
		return list;
	}
	
	public int selectAdminListCount(String searchType, String searchKeyword)
	{
		Connection conn=getConnection();
		int result=new AdminManagerDao().selectAdminCount(conn);
		switch(searchType)
		{
			case "id" : result=new AdminManagerDao().selectMemberCountId(conn,searchKeyword);break;
			case "name" :result=new AdminManagerDao().selectMemberCountName(conn,searchKeyword);break;
		}
		close(conn);
		return result;
	}
	
	//정렬
	
	public List<User> selectOrderAdminList(int cPage, int numPerPage, String orderType)
	{
		Connection conn=getConnection();
		List<User> list=null;
		switch(orderType)
		{
			case "enrollAsc" : list=new AdminManagerDao().orderAdminByEA(conn, cPage, numPerPage);break;
			case "enrollDesc" :list=new AdminManagerDao().selectAdminList(conn, cPage, numPerPage);break;
			case "idAsc" : list=new AdminManagerDao().orderAdminByIA(conn, cPage, numPerPage);break;
			case "idDesc" :list=new AdminManagerDao().orderAdminByID(conn, cPage, numPerPage);break;
		}
		
		close(conn);
		return list;
	}

}
