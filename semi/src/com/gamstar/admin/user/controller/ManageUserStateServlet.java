package com.gamstar.admin.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamstar.admin.user.model.service.AdminUserService;

/**
 * Servlet implementation class ManageUserStateServlet
 */
@WebServlet("/admin/manageUserState")
public class ManageUserStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUserStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//no<0 관리자 아니면 못들어옴
		if(request.getSession().getAttribute("userNo")==null||(Integer)request.getSession().getAttribute("userNo")>0)
		{
			request.setAttribute("msg", "잘못된 접근입니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
			return;
		}
		else
		{
		//state와 공통(userNo)필요한 parameter받기
			String stateType=request.getParameter("stateType");
			String userNo=request.getParameter("userNo");
			
			System.out.println(stateType+" "+userNo);
			
			//userNo 를 int 배열로 받기(한번에 여러개 처리하도록 했으니까...)
			String[] userNoStr=userNo.split(" ");
			System.out.println("userNoLength : "+userNoStr.length);
			int[] userNos=new int[userNoStr.length];
			
			for(int i=0;i<userNos.length;i++)
			{
				userNos[i]=Integer.parseInt(userNoStr[i]);
			}
			
			//여러개일 경우, 성공/실패 건수 알리기 위해
			int result=0;
			int count=0;
			
			//각각 작업 하기 위한 로직
			switch(stateType)
			{
			case "delete":
				for(int i=0;i<userNos.length;i++)
				{
					 result+=new AdminUserService().deleteUser(userNos[i]);count++;
				}
				break;
			case "stop":
				//필요 자료 만들기 위해
				String remainingDay=request.getParameter("remainingDay");
				int stopTime=Integer.parseInt(request.getParameter("stopTime"));
					
				String[] rDayStr=remainingDay.split(" ");
				int[] rDays=new int[rDayStr.length];
				
				for(int i=0;i<rDays.length;i++)
				{
					rDays[i]=Integer.parseInt(rDayStr[i])+stopTime;
					System.out.println("DAY "+i+":"+rDays[i]);
				}
				
				for(int i=0;i<userNos.length;i++)
				{
					result+=new AdminUserService().changeUserState(userNos[i],rDays[i]);count++;
				}
				break;
			case "resetStop":
				for(int i=0;i<userNos.length;i++)
				{
					result+=new AdminUserService().resetUserState(userNos[i]);count++;
				}
				break;
			}
			
			String view="/view/common/msg.jsp";
			String msg="";
			String loc="";
			
			if(result>0)
			{
				msg=result+"건이 처리되었습니다.";
				loc="/admin/userList";
			}
			else
			{
				msg=(userNos.length-count)+"건 처리에 실패하였습니다.";
				loc="/admin/userList";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
