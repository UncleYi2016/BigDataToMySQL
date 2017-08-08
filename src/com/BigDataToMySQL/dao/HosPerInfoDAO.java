package com.BigDataToMySQL.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import com.BigDataToMySQL.entity.HosPerInfo;

public class HosPerInfoDAO extends BaseDAO{
	
	private String tableName = "hos_per_info";
	private String id_column = "id";
	private String visit_no_column = "visit_no";
	private String ill_no_column = "ill_no";
	private String ill_name_column = "ill_name";
	private String visit_time_column = "visit_time";
	private String patient_no_column = "patient_no";
	private String patient_area_no_column = "patient_area_no";
	private String patient_area_addr_column = "patient_area_addr";
	private String patient_gender_column = "patient_gender";
	private String patient_age_column = "patient_age";
		
	public HosPerInfoDAO(){
		super();
	}
	public void insertData(List<HosPerInfo> list,int checkCount){
		System.out.println("Start inserting data ......");
		System.out.println("Insert size: " + list.size());
		for(HosPerInfo hpi : list){
			String sql = "INSERT INTO `" + tableName + "`" +
					"(`" + id_column + "`,"  + 
					"`"  + visit_no_column + "`," + 
					"`"  + ill_no_column + "`," + 
					"`"  + ill_name_column + "`," + 
					"`"  + patient_no_column + "`," +
					"`"  + patient_area_no_column + "`," +
					"`"  + patient_area_addr_column + "`," +
					"`"  + patient_gender_column + "`," +
					"`"  + patient_age_column + "`)" + 
					"VALUES (" + 
						"null" + ",'" + 
						hpi.getVisitNo() + "','" +
						hpi.getIllNo() + "','" +
						hpi.getIllName() + "','" +
						hpi.getVisitTime() + "','" +
						hpi.getPatientNo() + "','" +
						hpi.getPatientAreaNo() + "','" +
						hpi.getPatientAddr() + "','" +
						hpi.getPatientGender() + "','" +
						hpi.getPatientAge() +
					"')";
			System.out.println(sql);
			try {
				Statement stmt = conn.createStatement();
				boolean success = stmt.execute(sql);
				System.out.println(success);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
}
