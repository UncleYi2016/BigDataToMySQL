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
		DocInfoDAO did = new DocInfoDAO();
		HosPerInfoDAO hpid = new HosPerInfoDAO();
//		List<DocInfo> list = loadDocInfo();
		List<HosPerInfo> list = null ;//loadHosPerInfo();		
//		loadDocInfo();
		loadHosPerInfo();
//		hpid.insertData(list);
		loadDocInfo(did);
//		did.insertData(list);
//		did.selectData();
	}
	
	public static void loadHosPerInfo(HosPerInfoDAO hpid){
		File hos_per_info = new File("C://Users/Administrator/Desktop/code/hos_per_info_reduced.txt");
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		if(hos_per_info.exists() && hos_per_info.isFile()){
			try {
				read = new InputStreamReader(new FileInputStream(hos_per_info), "utf8");
				bufferedReader = new BufferedReader(read);
			} catch (Exception e) {
						// TODO Auto-generated catch block
						
				}
				String dataStr = null;  
				List<HosPerInfo> list = new ArrayList<HosPerInfo>();
				int count = 0;
				int checkCount = 0;
				while(true){
					try{
						checkCount++;
						if((dataStr = bufferedReader.readLine().trim()) != null && checkCount < 18407973){
							String[] datas = dataStr.split(",");
							HosPerInfo hosData = new HosPerInfo(datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6], datas[7], datas[8]);
							list.add(hosData);
						}else{
							System.out.println("Start　Insert from " + (checkCount-count) + " to " + checkCount + "!!!!!!!");
							hpid.insertData(list, checkCount-count);
							break;
						}
					} catch (Exception e){
						System.out.println("Loading No. " + (checkCount) + " record ... FAILED!");
					}
					count++;
					if(count == 100){
						System.out.println("Start　Insert from " + (checkCount-100) + " to " + checkCount + " !!!!!!!");
						hpid.insertData(list, checkCount-100);
						count = 0;
					}
				}
				System.out.println("Data load FINISHED!");
			}
		}


	public static void loadDocInfo(DocInfoDAO did){
		File doc_info = new File("C://Users/vm/Desktop/documents/doc_info_utf8.txt");
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		if(doc_info.exists() && doc_info.isFile()){
			try {
				read = new InputStreamReader(new FileInputStream(doc_info), "utf8");
				bufferedReader = new BufferedReader(read);
			} catch (Exception e) {
						// TODO Auto-generated catch block
						
				}
				String dataStr = null;  
				List<DocInfo> list = new ArrayList<DocInfo>();
				int count = 0;
				int checkCount = 0;
				while(true){
					try{
						checkCount++;
						if((dataStr = bufferedReader.readLine().trim()) != null && checkCount < 18407973){
							String[] datas = dataStr.split(",");
							DocInfo docData = new DocInfo(datas[0], datas[1], datas[2], datas[3], datas[4]);
							list.add(docData);
						}else{
							System.out.println("Start　Insert from " + (checkCount-count) + " to " + checkCount + "!!!!!!!");
							did.insertData(list, checkCount-count);
							break;
						}
					} catch (Exception e){
						System.out.println("Loading No. " + (checkCount) + " record ... FAILED!");
					}
					count++;
					if(count == 100){
						System.out.println("Start　Insert from " + (checkCount-100) + " to " + checkCount + " !!!!!!!");
						did.insertData(list, checkCount-100);
						count = 0;
					}
				}
				System.out.println("Data load FINISHED!");
			}
		}
}
