package com.BigDataToMySQL.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.BigDataToMySQL.dao.DocInfoDAO;
import com.BigDataToMySQL.entity.DocInfo;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocInfoDAO did = new DocInfoDAO();
//		List<DocInfo> list = loadDocInfo();
		loadDocInfo();
//		did.insertData(list);
//		did.selectData();
	}
	
	public static void loadDocInfo(){
		File doc_info = new File("C://Users/vm/Desktop/documents/111.txt");
		if(doc_info.exists() && doc_info.isFile()){
			try {
				InputStreamReader read = new InputStreamReader(new FileInputStream(doc_info), "utf8");
				BufferedReader bufferedReader = new BufferedReader(read);
				String dataStr = null;   
				dataStr = bufferedReader.readLine().trim();
		        String[] datas = dataStr.split(",");
		        for(String d : datas){
		        	System.out.print(d + "-");
		        }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
