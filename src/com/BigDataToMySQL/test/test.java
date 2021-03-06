package com.BigDataToMySQL.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.BigDataToMySQL.dao.DocInfoDAO;
import com.BigDataToMySQL.dao.HosPerInfoDAO;
import com.BigDataToMySQL.entity.DocInfo;
import com.BigDataToMySQL.entity.Gender;
import com.BigDataToMySQL.entity.HosPerInfo;
import com.BigDataToMySQL.dao.IllDAO;
import com.BigDataToMySQL.entity.Ill;
import com.BigDataToMySQL.entity.JsonIll;
import com.BigDataToMySQL.entity.Medicine;
import com.BigDataToMySQL.entity.PatientArea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class test {
	public static List<String> illNames = new ArrayList<String>();
	public static List<String> illCounts = new ArrayList<String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "./";
		String dbpath = "117.141.244.6";  //数据库地址
		int dbport = 8636;  //数据库端口号
		int count = 5;
		int countInfo = 50;
		int listSize = 5;	//每次取出的数目
//		DocInfoDAO did = new DocInfoDAO();
//		HosPerInfoDAO hpid = new HosPerInfoDAO();
//		List<DocInfo> list = loadDocInfo();
//		List<HosPerInfo> list = null ;//loadHosPerInfo();		
//		loadDocInfo();
//		loadHosPerInfo(hpid,path);
//		hpid.insertData(list);
//		loadDocInfo(did);


	
		try{
			path = args[0];
			count = Integer.parseInt(args[1]);	//设置要统计多少个疾病
			countInfo = Integer.parseInt(args[2]); 	//设置统计一个疾病的多少条记录
			listSize = Integer.parseInt(args[3]);  //设置每次查询数目
			dbpath = args[4];  //设置数据库地址
			dbport = Integer.parseInt(args[5]);  //设置数据库端口号
		}catch(Exception e){
			
		}
		
		IllDAO illDAO = new IllDAO(dbpath,dbport);
		List<Ill> list = null; 
		if(count - listSize >=0){
			countJsonIllList(list,illDAO,path,listSize,count,countInfo);
		}else{
			listSize = count;
			countJsonIllList(list,illDAO,path,listSize,count,countInfo);
		}
		saveJsonIllBriefIntro(illNames, path);
		saveJsonIllBriefIntro(illCounts, path);
		
//		DocInfoDAO did = new DocInfoDAO();
//		List<DocInfo> list = loadDocInfo();
//		test.loadDocInfo(path, did);

//		did.insertData(list);
//		did.selectData();
	}
	
	public static String illToJson(Object ji){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String illStr = gson.toJson(ji);
		return illStr;
	}
	
	public static void countIllList(List<Ill> list,IllDAO illDAO,String path,int listSize,int count ,int countInfo){
		int listCount = 0;
		for (int startNumber=0;startNumber<count;startNumber += listSize){
			if(count - startNumber < listSize){
				listSize = count - startNumber;
			}
			list = illDAO.getIlls(startNumber,listSize, countInfo);
//			saveIllToText(list, path,startNumber);
			
			saveIllToText(list, path, startNumber);
		}
	}
	
	public static void countJsonIllList(List<Ill> list,IllDAO illDAO,String path,int listSize,int count ,int countInfo){
		int listCount = 0;
		for (int startNumber=0;startNumber<count;startNumber += listSize){
			if(count - startNumber < listSize){
				listSize = count - startNumber;
			}
			list = illDAO.getIlls(startNumber,listSize, countInfo);
//			saveIllToText(list, path,startNumber);
			
			List<JsonIll> jiList = new ArrayList<JsonIll>();
			for(Ill ill : list){
				if(ill.getIllName().equals(" ")){
					illNames.add(ill.getIllNo());
				}else{
					illNames.add(ill.getIllNo() + "-" + ill.getIllName());
				}
				
				illCounts.add(ill.getCount());
				JsonIll ji = new JsonIll(ill.getIllNo(), ill.getIllName(), ill.getCount());
				ji.genders.add(new Gender("男性", ill.getManCount()));
				ji.genders.add(new Gender("女性", ill.getWomanCount()));
				for(PatientArea pa : ill.patientArea){
					ji.areaNos.add(pa.getAreaNo());
					ji.areaAddrs.add(pa.getAreaAddr());
					ji.areaCounts.add(pa.getCount());
				}
				for(String age : ill.ages){
					ji.agesCount.add(age);
				}
				for(Medicine m : ill.medicine){
					ji.medicineTypes.add(m.getMedicineType());
					ji.medicineNos.add(m.getMedicineNo());
					ji.medicineNames.add(m.getMedicineName());
					ji.medicineCounts.add(m.getCount());
				}
				jiList.add(ji);
				
			}
			
			saveJsonIllToText(jiList, path, startNumber);
			
		}
	}
	public static void saveIllToText(List<Ill> list, String path,int j_colum){
		int j = j_colum + 1;
		for(Ill i: list){
			
			String dirName = "Ill_" + j + "_" +  i.getIllNo();
			String pathName = path + dirName + "/";
			String introFileName = pathName + dirName + "_introduction.txt";
			String dateTimeFileName = pathName + dirName + "_date_time.txt";
			String medicineFileName = pathName + dirName + "_medicine.txt";
			String patientAreaFileName = pathName + dirName + "_patientAreaFileName.txt";
			createDir(pathName);
			File introFile = new File(introFileName);
			File dateTimeFile = new File(dateTimeFileName);
			File medicineFile = new File(medicineFileName);
			File patientAreaFile = new File(patientAreaFileName);
			saveIntro(introFile, i);
			saveDateTime(dateTimeFile, i);
			saveMedicine(medicineFile, i);
			savePatientArea(patientAreaFile, i);
			j++;
		}
		System.out.println("FINISHED!");
	}
	
	public static void saveJsonIllToText(List<JsonIll> list, String path, int j_colum){
		int j = j_colum + 1;
		for(JsonIll ji : list){
			String dirName = "Ill_" + j + "_" + ji.illNo;
			String pathName = path + dirName + "/";
			String jsonFileName = pathName + dirName + ".json";
			createDir(pathName);
			File jsonFile = new File(jsonFileName);
			saveJsonFile(jsonFile, ji);
			j++;
		}
		System.out.println("FINISHED!");
	}
	
	public static void saveJsonIllBriefIntro(List<String> list, String path){
		String dirName = "Ill_Count";
		String pathName = path + dirName + "/";
		String jsonFileName = pathName + dirName + ".json";
		createDir(pathName);
		File jsonFile = new File(jsonFileName);
		saveJsonFile(jsonFile, list);
	}
	
	public static void saveJsonFile(File file, JsonIll ji){
		if(file.exists()){
			System.out.println("File SKIPPED!");
		}
		
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(illToJson(ji));
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveJsonFile(File file, Object o){
		if(file.exists()){
			System.out.println("File SKIPPED!");
		}
		
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(illToJson(o));
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadHosPerInfo(HosPerInfoDAO hpid , String path){
		File hos_per_info = new File(path);
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
				int count = 0;
				int checkCount = 0;
				List<HosPerInfo> list = new ArrayList<HosPerInfo>();
				while(checkCount < 92591){
					try{
						
						checkCount++;

						if((dataStr = bufferedReader.readLine().trim()) != null){
							String[] datas = dataStr.split(",");
							HosPerInfo hosData = new HosPerInfo(datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6], datas[7], datas[8]);
							list.add(hosData);
						}else{
							System.out.println("Start　Insert from " + (checkCount - count) + " to " + checkCount + "!!!!!!!");
							hpid.insertData(list, checkCount - count);
							break;
						}
					}catch (Exception e){
						hpid.insertData(list, checkCount - count);
						System.out.println("Loading No. " + (checkCount) + " record ... FAILED!");
					}
					
					count++;
					if(count == 1000){
						System.out.println("Start　Insert from " + (checkCount - count) + " to " + checkCount + " !!!!!!!");
						hpid.insertData(list, checkCount - count);
						list.clear();
						count = 0;
					}
				}
				System.out.println("Data load FINISHED!");
			}
		}

	//写入介绍文件
	public static void saveIntro(File file, Ill i){
		if(file.exists()){
			System.out.println("Introduction SKIPPED!");
			return;
		}
		System.out.println("Saving Introduction....");
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("疾病编号: " + i.getIllNo() + "\r\n");
			bw.flush();
			bw.write("疾病名称: " + i.getIllName() + "\r\n");
			bw.flush();
			bw.write("疾病统计: " + i.getCount() + "\r\n");
			bw.flush();
			int ageArea = 0;
			for(String age : i.ages){
				bw.write("年龄" + ageArea + "~" + (ageArea+9) + "岁出现次数: " + age + "\r\n");
				bw.flush();
				ageArea += 10;
			}
			bw.write("男性发病次数: " + i.getManCount() + "\r\n");
			bw.flush();
			bw.write("女性发病次数: " + i.getWomanCount() + "\r\n");
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//写入诊断时间
	public static void saveDateTime(File file, Ill i){
		if(file.exists()){
			System.out.println("DateTime SKIPPED!");
			return;
		}
		System.out.println("Saving DateTime....");
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("诊断时间统计： \r\n");
			bw.flush();
			for(String time : i.dateTime){
				bw.write("\t" + time + "\r\n");
				bw.flush();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//写入使用药品
	public static void saveMedicine(File file, Ill i){
		if(file.exists()){
			System.out.println("Medicine SKIPPED!");
			return;
		}
		System.out.println("Saving Medicine....");
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("用药时间统计：\t药品类型-药品代码-药品名称-统计次数\r\n");
			bw.flush();
			for(Medicine m : i.medicine){
				bw.write("\t" + m.getMedicineType() + "-" + m.getMedicineNo() + "-" + m.getMedicineName() + "-" + m.getCount() + "\r\n");
				bw.flush();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//写入病人地区
	public static void savePatientArea(File file, Ill i){
		if(file.exists()){
			System.out.println("PatientArea SKIPPED!");
			return;
		}
		System.out.println("Saving PatientArea....");
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("病人地区统计：\t地区代码-地区名称-统计次数\r\n");
			bw.flush();
			for(PatientArea pa : i.patientArea){
				bw.write("\t" + pa.getAreaNo() + "-" + pa.getAreaAddr() + "-" + pa.getCount() + "\r\n");
				bw.flush();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//生成目录
	public static boolean createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
            return false;  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
            System.out.println("创建目录" + destDirName + "成功！");  
            return true;  
        } else {  
            System.out.println("创建目录" + destDirName + "失败！");  
            return false;  
        }  
    } 
	
	//生成文件
	public static boolean createFile(String destFileName) {  
        File file = new File(destFileName);  
        if(file.exists()) {  
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");  
            return false;  
        }  
        if (destFileName.endsWith(File.separator)) {  
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");  
            return false;  
        }  
        //判断目标文件所在的目录是否存在  
        if(!file.getParentFile().exists()) {  
            //如果目标文件所在的目录不存在，则创建父目录  
            System.out.println("目标文件所在目录不存在，准备创建它！");  
            if(!file.getParentFile().mkdirs()) {  
                System.out.println("创建目标文件所在目录失败！");  
                return false;  
            }  
        }  
        //创建目标文件  
        try {  
            if (file.createNewFile()) {  
                System.out.println("创建单个文件" + destFileName + "成功！");  
                return true;  
            } else {  
                System.out.println("创建单个文件" + destFileName + "失败！");  
                return false;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());  
            return false;  
        }  
    }
	
	public static void loadDocInfo(String path, DocInfoDAO did){
		File doc_info = new File(path);

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
								did.insertData(list, checkCount);
								break;
							}
					} catch (Exception e){
						System.out.println("Loading No. " + (checkCount) + " record ... FAILED!");
					}
					count++;
					if(count == 1000){
						System.out.println("Start　Insert from " + (checkCount-count) + " to " + checkCount + " !!!!!!!");
						did.insertData(list, checkCount-count);
						count = 0;
						list.clear();
					}
				}
				System.out.println("Data load FINISHED!");
			}
		}
}
