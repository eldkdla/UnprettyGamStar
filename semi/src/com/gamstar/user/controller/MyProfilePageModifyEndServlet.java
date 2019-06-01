package com.gamstar.user.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.gamstar.user.model.service.UserService;
import com.gamstar.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class MyProfilePageModifyEnd
 */
@WebServlet("/view/modifyEnd")
public class MyProfilePageModifyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfilePageModifyEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		if(request.getSession().getAttribute("userNo")!=null&&request.getParameter("uu")==null){
		
			String root=getServletContext().getRealPath("/");
			String path=root+"upload";
	//		String path = request.getRealPath("/upload");
			System.out.println("path:"+ path);
			String msg="";
			String loc="";
	
			if(ServletFileUpload.isMultipartContent(request)){
			int maxSize=1024*1024*10;//10MB
			MultipartRequest multi = new MultipartRequest(request, path,maxSize,"utf-8", new DefaultFileRenamePolicy());
			
			User user= new User();
			user.setNo((int)request.getSession().getAttribute("userNo"));
			
			User oldUser=new UserService().selectUser(user);
			String oldProfilePhoto=oldUser.getProfilePhoto();
			int oldDisclosure=oldUser.getDisclosure();
			
			if(multi.getFilesystemName("uploadPhoto")!=null){
				user.setProfilePhoto("upload/"+multi.getFilesystemName("uploadPhoto"));
			}
			else{
				user.setProfilePhoto(oldUser.getProfilePhoto());
			}
			user.setName(multi.getParameter("name"));
			user.setEmail(multi.getParameter("email"));
			user.setPhone(multi.getParameter("phone"));
			user.setGender(multi.getParameter("gender"));
			user.setDisclosure(Integer.parseInt(multi.getParameter("disclosure")));
			
			int result=new UserService().updateUserData(user);
			
			if(result!=0){
				if(multi.getFilesystemName("uploadPhoto")!=null){//프로필변경후 정보변경 성공시 예전사진 삭제
					File file=new File(request.getSession().getServletContext().getRealPath("/")+oldProfilePhoto);
					if(file.delete()){
						System.out.println("삭제성공");
					}else{
						System.out.println("삭제실패");
					}
				}
				msg="정보 변경성공";
				loc="/view/profile";
			}
			else{
				msg="정보 변경실패";
				loc="/view/profile";
			}
			
			if((oldDisclosure!=user.getDisclosure())&&user.getDisclosure()==1){
				int updateDisclosureTriggerResult = new UserService().updateDisclosureTrigger((int)request.getSession().getAttribute("userNo"));
			}
		
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
			
		}else{
			request.setAttribute("msg", "잘못된 접근");
			request.setAttribute("loc", "");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
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
