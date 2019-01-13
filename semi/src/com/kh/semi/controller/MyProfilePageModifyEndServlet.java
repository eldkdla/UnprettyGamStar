package com.kh.semi.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.model.service.UserService;
import com.kh.semi.model.vo.User;
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
		
		String root=getServletContext().getRealPath("/");
		String path=root+"upload";
//		String path = request.getRealPath("/upload");
		System.out.println("path:"+ path);

		if(ServletFileUpload.isMultipartContent(request)){
		int maxSize=1024*1024*10;//10MB
		MultipartRequest multi = new MultipartRequest(request, path,maxSize,"utf-8", new DefaultFileRenamePolicy());
		
		HttpSession se = request.getSession();
		se.setAttribute("myname", 6);
		
		User u= new User();
		u.setUserNo((int)se.getAttribute("myname"));
		
		User oldUser=new UserService().selectUser(u);
		
		if(multi.getFilesystemName("uploadPhoto")!=null){
			u.setProfilePhoto(request.getContextPath()+"/upload/"+multi.getFilesystemName("uploadPhoto"));
		}
		else{
			u.setProfilePhoto(oldUser.getProfilePhoto());
		}
		u.setUserEmail(multi.getParameter("email"));
		u.setUserPhone(multi.getParameter("phone"));
		u.setUserGender(multi.getParameter("gender"));
		
		int result=new UserService().updateUserData(u);
		
		if(result!=0){
			if(multi.getFilesystemName("uploadPhoto")!=null){//프로필변경후 정보변경 성공시 예전사진 삭제
				File file=new File(oldUser.getProfilePhoto());
				if(file.delete()){
					System.out.println("삭제성공");
				}else{
					System.out.println("삭제실패");
				}
			}
			System.out.println("정보 변경성공");
		}
		else{
			System.out.println("정보 변경실패");
		}
	
		}
		response.sendRedirect("myprofile");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
