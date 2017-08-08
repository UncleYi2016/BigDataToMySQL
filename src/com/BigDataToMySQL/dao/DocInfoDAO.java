package com.BigDataToMySQL.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.BigDataToMySQL.entity.DocInfo;

public class DocInfoDAO extends BaseDAO{
	private String tableName = "doc_info";
	private String id_column = "id";
	private String visit_no_column = "visit_no";
	private String ill_no_column = "ill_no";
	private String medicine_type_column = "medicine_type";
	private String medicine_no_column = "medicine_no";
	private String medicine_name_column = "medicine_name";
	
	public DocInfoDAO(){
		super();
	}
	
	public void insertData(List<DocInfo> list){
		System.out.println("Start inserting data ......");
		System.out.println("Insert size: " + list.size());
		for(DocInfo di : list){
			String sql = "INSERT INTO `" + tableName + "`" +
					"(`" + id_column + "`,"  + 
					"`"  + visit_no_column + "`," + 
					"`"  + ill_no_column + "`," + 
					"`"  + medicine_type_column + "`," + 
					"`"  + medicine_no_column + "`," +
					"`"  + medicine_name_column + "`)" + 
					"VALUES (" + 
						"null" + ",'" + 
						di.getVisitNo() + "','" +
						di.getIllNo() + "','" +
						di.getMedicineType() + "','" +
						di.getMedicineNo() + "','" +
						di.getMedicineName() +
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
