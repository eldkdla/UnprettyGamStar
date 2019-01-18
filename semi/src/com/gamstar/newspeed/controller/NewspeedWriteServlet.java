package com.gamstar.newspeed.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.*;
import org.apache.tomcat.util.http.fileupload.disk.*;
import org.apache.tomcat.util.http.fileupload.servlet.*;
import org.apache.tomcat.util.http.fileupload.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.gamstar.filecontroller.NewspeedMediaReNamePolicy;
import com.gamstar.newspeed.model.service.NewspeedService;
import com.gamstar.newspeed.model.vo.Newspeed;
import com.gamstar.newspeed.model.vo.NewspeedMedia;
import com.gamstar.newspeed.model.vo.NewspeedMediaTag;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class PostWritingServlet
 */
@WebServlet("/view/postwrite")
public class NewspeedWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_FILE_SIZE = 2100000000;
	private NewspeedService newspeedService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewspeedWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
		newspeedService = new NewspeedService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String root = getServletContext().getRealPath("/") + "upload/newspeed/";
	
		String path = root + "upload/newspeed/";
		int userNo = Integer.parseInt(request.getSession().getAttribute("userNo").toString());
		int result = -1;
		
		System.out.println(root + "경로야!");
		
		MultipartRequest mr = new MultipartRequest(request, root, MAX_FILE_SIZE, "UTF-8", new NewspeedMediaReNamePolicy());
		String newspeedJSONStr = mr.getParameter("newspeed");
		Newspeed newspeed = new Newspeed();
		List<NewspeedMedia> newspeedMediaList = new ArrayList<NewspeedMedia>();
		List<NewspeedMediaTag> newspeedMediaTagList = new ArrayList<NewspeedMediaTag>();

		newspeed.setUserNo(userNo);
		jsonToNewspeedData(newspeedJSONStr, newspeed, newspeedMediaList, newspeedMediaTagList);
		setNewspeedMediaList(newspeedMediaList, mr);
		result = newspeedService.insertNewspeedData(newspeed, newspeedMediaList, newspeedMediaTagList);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean isError() {
		return false;
	}

	private void jsonToNewspeedData(String json, Newspeed newspeed, List<NewspeedMedia> newspeedMediaList,
			List<NewspeedMediaTag> newspeedMediaTagList) {
		
		JSONParser parser = new JSONParser();
		JSONObject newspeedJSON = null;
		JSONArray fileListJSONArray = null;	
		String content = null;
		String fileList = null;
		
	
		try {
			System.out.println("나오세요?");
			newspeedJSON = (JSONObject) parser.parse(json);
			System.out.println("뉴스피드" + newspeedJSON);
			content = newspeedJSON.get("content").toString();
			System.out.println("내용입니다" + content);
			
			newspeed.setContent(content);

			fileList = newspeedJSON.get("fileList").toString();
			fileListJSONArray = (JSONArray) parser.parse(fileList);

			System.out.println("파일리스트" + fileList);

			for (int i = 0; i < fileListJSONArray.size(); i++) {
				JSONObject fileJSON = (JSONObject) fileListJSONArray.get(i);
				System.out.println(fileJSON);
				System.out.println("파일이름 : " + fileJSON.get("fileName"));
				System.out.println("미디어 인덱스 : " + fileJSON.get("mediaIndex"));
				
				NewspeedMedia newspeedMedia = new NewspeedMedia();
				newspeedMedia.setPath(fileJSON.get("fileName").toString());
				newspeedMedia.setIndex(Integer.parseInt(fileJSON.get("mediaIndex").toString()));
				newspeedMediaList.add(newspeedMedia);

				JSONArray tagListJSONArray = (JSONArray) parser.parse(fileJSON.get("tagList").toString());
				System.out.println(tagListJSONArray);

				for (int j = 0; j < tagListJSONArray.size(); j++) {
					JSONObject tagListJSON = (JSONObject) tagListJSONArray.get(j);
					System.out.println(tagListJSON);
					System.out.println("유저번호 : " + tagListJSON.get("userNo"));
					System.out.println("x좌표 : " + tagListJSON.get("x"));
					System.out.println("y좌표 : " + tagListJSON.get("y"));
					System.out.println("mediaIndex : " + tagListJSON.get("mediaIndex"));
					System.out.println("tagIndex : " + tagListJSON.get("tagIndex"));
					System.out.println("");
					
					NewspeedMediaTag newspeedMediaTag = new NewspeedMediaTag();
					newspeedMediaTag.setMediaIndex(Integer.parseInt(tagListJSON.get("mediaIndex").toString()));
					newspeedMediaTag.setUserNo(Integer.parseInt(tagListJSON.get("userNo").toString()));
					newspeedMediaTag.setX(Double.parseDouble(tagListJSON.get("x").toString()));
					newspeedMediaTag.setY(Double.parseDouble(tagListJSON.get("y").toString()));
					
					newspeedMediaTagList.add(newspeedMediaTag);
				}
			}

			System.out.println("머하세용..");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void setNewspeedMediaList(List<NewspeedMedia> newspeedMediaList, MultipartRequest mr) {
		for (int i = 0; i < newspeedMediaList.size(); i++) {
			NewspeedMedia newspeedMedia = newspeedMediaList.get(i);
			String filePath = "upload/newspeed/" + mr.getFilesystemName(newspeedMedia.getPath());
			newspeedMedia.setPath(filePath);
			
			if (!filePath.endsWith(".jpg") && !filePath.endsWith(".png") && !filePath.endsWith(".bmp")) {
				newspeedMedia.setType(NewspeedMedia.MEDIA_TYPE_VIDEO);
			} else {
				newspeedMedia.setType(NewspeedMedia.MEDIA_TYPE_IMAGE);
			}
			System.out.println(filePath);
		}
	}

}
