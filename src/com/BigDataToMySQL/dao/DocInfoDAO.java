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
	
	public void insertData(List<DocInfo> list, int checkCount){
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
		for(DocInfo di : list){
			checkCount++;
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
