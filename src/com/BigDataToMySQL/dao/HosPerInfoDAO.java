package com.BigDataToMySQL.dao;

import java.sql.ResultSet;
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
		
	public HosPerInfoDAO(String dbhost , int dbport){
		super(dbhost, dbport);
	}
	public void insertData(List<HosPerInfo> list,int checkCount){
		System.out.println("Start inserting data ......");
		System.out.println("Insert size: " + list.size());
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);  
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,  
                    ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(HosPerInfo hpi : list){
			String sql = "INSERT INTO `" + tableName + "`" +
					"(`" + id_column + "`,"  + 
					"`"  + visit_no_column + "`," + 
					"`"  + ill_no_column + "`," + 
					"`"  + ill_name_column + "`," + 
					"`"  + visit_time_column + "`," + 
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
//			System.out.println(sql);
			
			try {
				boolean success = stmt.execute(sql);
//				System.out.println("Insert No. " + checkCount + " ... OK!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Insert No. " + checkCount + " ... FAILED!");
//				e.printStackTrace();
			}
		}
			
		try {
			conn.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
