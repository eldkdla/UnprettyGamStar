package com.kh.semi.controller;

import java.io.File;
import java.io.IOException;

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
 * Servlet implementation class MyProfilePhotoModifyServlet
 */
@WebServlet("/view/photoModify")
public class MyProfilePhotoModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfilePhotoModifyServlet() {
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
		se.setAttribute("userNo", 6);
		
		User u= new User();
		u.setNo((int)se.getAttribute("userNo"));
		
		int result=0;
		
		User oldUser=new UserService().selectUser(u);
		
		if(multi.getFilesystemName("uploadProfilePhoto1")!=null){
			//프로필 사진 수정한것
			u.setProfilePhoto(request.getContextPath()+"/upload/"+multi.getFilesystemName("uploadProfilePhoto1"));
			result=new UserService().updateProfilePhoto(u);
			
			if(result!=0){
				File file=new File(oldUser.getProfilePhoto());
				if(file.delete()){
					System.out.println("삭제성공");
				}else{
					System.out.println("삭제실패");
				}
				System.out.println("프로필사진 변경 성공");
			}
		}
		else if(multi.getFilesystemName("uploadProfilePhoto2")!=null){
			//배경프로필 수정한것
			u.setProfileBackgroundPhoto(request.getContextPath()+"/upload/"+multi.getFilesystemName("uploadProfilePhoto2"));
			result=new UserService().updateBackgroundPhoto(u);
			
			if(result!=0){
				System.out.println(oldUser.getProfileBackgroundPhoto());
				File file=new File(oldUser.getProfileBackgroundPhoto());
				if(file.delete()){
					System.out.println("삭제성공");
				}else{
					System.out.println("삭제실패");
				}
				System.out.println("배경사진 변경 성공");
			}
		}
		System.out.println("다 끝냈나 "+result);
		response.sendRedirect("myprofile");
		
		}
		else{
			System.out.println("사진 multipart로 안보냈음");
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
