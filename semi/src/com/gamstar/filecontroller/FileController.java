package com.gamstar.filecontroller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.oreilly.servlet.MultipartRequest;

public class FileController {
	
	public List<String> getFileNameList(MultipartRequest mr, String tagName){
		List<String> fileNameList = new ArrayList<String>();
		Enumeration e = mr.getFileNames();
		
		while(e.hasMoreElements()) {
			String fileName = e.nextElement().toString();
			System.out.println(mr.getFilesystemName(fileName));
			
			fileNameList.add(mr.getFilesystemName(fileName));
			
			System.out.println(fileName);
		}
		
		
		return fileNameList;
	}
}
