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
import com.BigDataToMySQL.dao.HosPerInfoDAO;
import com.BigDataToMySQL.entity.DocInfo;
import com.BigDataToMySQL.entity.HosPerInfo;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		DocInfoDAO did = new DocInfoDAO();
		HosPerInfoDAO hpid = new HosPerInfoDAO();
//		List<DocInfo> list = loadDocInfo();
		List<HosPerInfo> list = null ;//loadHosPerInfo();		
//		loadDocInfo();
		loadHosPerInfo();
//		hpid.insertData(list);
//		did.insertData(list);
//		did.selectData();
	}
	
//	public static void loadDocInfo(){
//		File doc_info = new File("C://Users/Administrator/Desktop/code/hos_per_info_reduced.txt");
//		if(doc_info.exists() && doc_info.isFile()){
//			try {
//				InputStreamReader read = new InputStreamReader(new FileInputStream(doc_info), "utf8");
//				BufferedReader bufferedReader = new BufferedReader(read);
//				String dataStr = null;   
//				dataStr = bufferedReader.readLine().trim();
//		        String[] datas = dataStr.split(",");
//		        for(String d : datas){
//		        	System.out.print(d + "-");
//		        }
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//		}
//	}
	
	public static void loadHosPerInfo(){
		File hos_Per_info = new File("C://Users/Administrator/Desktop/code/hos_per_info_reduced.txt");
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		if(hos_Per_info.exists() && hos_Per_info.isFile()){
			try {
				read = new InputStreamReader(new FileInputStream(hos_Per_info), "utf8");
				bufferedReader = new BufferedReader(read);
				String dataStr = null;  
				while(bufferedReader.readLine() != null){
					dataStr = bufferedReader.readLine().trim();
					String[] datas = dataStr.split(",");
					for(String d : datas){
						System.out.print(d + "-");
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
