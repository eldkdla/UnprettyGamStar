package com.gamstar.admin.user.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.gamstar.admin.user.model.dao.AdminUserDao;
import com.gamstar.user.model.vo.User;

public class AdminUserService {
	
	//admin member 페이징
		public List<User> selectMemberList(int cPage, int numPerPage)
		{
			Connection conn=getConnection();
			List<User> list=new AdminUserDao().selectUserList(conn,cPage,numPerPage);
			close(conn);
			return list;
		}
		
		public int selectMemberCount()
		{
			Connection conn=getConnection();
			int result=new AdminUserDao().selectMemberCount(conn);
			close(conn);
			return result;
		}
		
		//정렬에 따라 페이징
		public List<User> orderUserList(int cPage, int numPerPage, String orderType)
		{
			Connection conn=getConnection();
			List<User> list=null;
			
			switch(orderType)
			{
				case "nd" : list=new AdminUserDao().orderUserListND(conn,cPage,numPerPage);break;
				case "na" : list=new AdminUserDao().orderUserListNA(conn,cPage,numPerPage);break;
				case "ide" : list=new AdminUserDao().orderUserListID(conn,cPage,numPerPage);break;
				case "ia" : list=new AdminUserDao().orderUserListIA(conn,cPage,numPerPage);break;
				case "ed" : list=new AdminUserDao().orderUserListED(conn,cPage,numPerPage);break;
				case "ea" : list=new AdminUserDao().orderUserListEA(conn,cPage,numPerPage);break;
				case "sde" : list=new AdminUserDao().orderUserListSD(conn,cPage,numPerPage);break;
			}
			
			close(conn);
			return list;
		}
		
		
		//검색
		public List<User> searchIdList(int cPage, int numPerPage, String searchKeyword)
		{
			Connection conn=getConnection();
			List<User> list=new AdminUserDao().searchIdList(conn, cPage, numPerPage, searchKeyword);
			close(conn);
			return list;
		}
		public int selectMemberCountId(String searchKeyword) 
		{
			Connection conn=getConnection();
			int result=new AdminUserDao().selectMemberCountId(conn, searchKeyword);
			close(conn);
			return result;
		}
		public List<User> searchNameList(int cPage, int numPerPage, String searchKeyword)
		{
			Connection conn=getConnection();
			List<User> list=new AdminUserDao().searchNameList(conn, cPage, numPerPage, searchKeyword);
			close(conn);
			return list;
		}
		public int selectMemberCountName(String searchKeyword) 
		{
			Connection conn=getConnection();
			int result=new AdminUserDao().selectMemberCountName(conn, searchKeyword);
			close(conn);
			return result;
		}
		public List<User> searchEmailList(int cPage, int numPerPage, String searchKeyword)
		{
			Connection conn=getConnection();
			List<User> list=new AdminUserDao().searchEmailList(conn, cPage, numPerPage, searchKeyword);
			close(conn);
			return list;
		}
		public int selectMemberCountEmail(String searchKeyword) 
		{
			Connection conn=getConnection();
			int result=new AdminUserDao().selectMemberCountEmail(conn, searchKeyword);
			close(conn);
			return result;
		}
		public List<User> searchPhoneList(int cPage, int numPerPage, String searchKeyword)
		{
			Connection conn=getConnection();
			List<User> list=new AdminUserDao().searchPhoneList(conn, cPage, numPerPage, searchKeyword);
			close(conn);
			return list;
		}
		public int selectMemberCountPhone(String searchKeyword) 
		{
			Connection conn=getConnection();
			int result=new AdminUserDao().selectMemberCountPhone(conn, searchKeyword);
			close(conn);
			return result;
		}
		
		//멤버 상태 변경
			//탈퇴
		public int deleteUser(int userNo)
		{
			Connection conn=getConnection();
			int result=0;
			
			result=new AdminUserDao().deleteUser(conn, userNo);
			
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
			//정지
		public int changeUserState(int userNo, int stopTime)
		{
			Connection conn=getConnection();
			int result=new AdminUserDao().changeUserState(conn, userNo,1);
			int rDateResult=new AdminUserDao().setRemainingDate(conn,userNo,stopTime);
			int totalResult=0;
			
			if(result>0)
			{
				if(rDateResult>0)
				{
					commit(conn);
					totalResult=1;
				}
			}
			else
			{
				rollback(conn);
			}
			
			close(conn);
			
			return totalResult;
		}
		
			//정지해제	
		public int resetUserState(int userNo)
		{
			Connection conn=getConnection();
			int result=new AdminUserDao().changeUserState(conn, userNo, 0);
			int rDateResult=new AdminUserDao().setRemainingDate(conn,userNo,0);
			int totalResult=0;
			
			if(result>0)
			{
				if(rDateResult>0)
				{
					commit(conn);
					totalResult=1;
				}
			}
			else
			{
				rollback(conn);
			}
			
			close(conn);
			
			return totalResult; 
		}

}
