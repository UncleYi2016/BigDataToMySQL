package com.BigDataToMySQL.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.BigDataToMySQL.entity.Ill;
import com.BigDataToMySQL.entity.Medicine;
import com.BigDataToMySQL.entity.PatientArea;

public class IllDAO extends BaseDAO{
	private String table_name1 = "doc_info";
	private String table_name2 = "hos_per_info";
	private String ill_no_column = "ill_no";
	private String ill_name_column = "ill_name";
	
	//number: 获取前n个确诊次数最多的疾病
	public List<Ill> getIlls(int number, int countInfo){
		List<Ill> illList = new ArrayList<Ill>();
		Statement stmt = null;
		try {
//			conn.setAutoCommit(false);  
//			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,  
//                    ResultSet.CONCUR_READ_ONLY);
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet rs = null;
		String sql = "select `ill_no`, `ill_name`, count(*) as `Count` from `hos_per_info` group by `ill_no` order by `Count` desc LIMIT 0, " + number;
		try {
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String illNo = rs.getString(1);
				String illName = rs.getString(2);
				String count = rs.getString(3);
				Ill ill = new Ill(illNo, illName, count);
				if(illNo.equals(" ")){
					continue;
				}
				illList.add(ill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Ill i : illList){
			String i_no = i.getIllNo();
			//跳过没有疾病编号的疾病
			try {
				//获取每个疾病的诊断时间
				String sql1 = "select `visit_time` from `hos_per_info` where `ill_no`='" + i_no + "' ORDER BY `visit_time` desc";
				System.out.println(sql1);
				rs = stmt.executeQuery(sql1);
				while(rs.next()){
					String dateTime = rs.getString(1);
					i.dateTime.add(dateTime);
				}
				
				//获取每个疾病的病人性别
				String sql2 = "select `patient_gender`, COUNT(*) as count from `hos_per_info` where `ill_no`='" + i_no + "' group by `patient_gender` order by `patient_gender` asc";
				System.out.println(sql2);
				rs = stmt.executeQuery(sql2);
				if(rs.next()){
					i.setManCount(rs.getString(2));
				}
				if(rs.next()){
					i.setWomanCount(rs.getString(2));
				}
				
				//获取病人年龄统计
				for(int i1 = 0; i1 < 100; i1+=10){
					String sql3 = "select COUNT(*) as count from `hos_per_info` where `ill_no`='" + i_no + "' and `patient_age` >= " + i1 + " and `patient_age` < " + (i1+10) + "";
					System.out.println(sql3);
					rs = stmt.executeQuery(sql3);
					while(rs.next()){
						i.ages.add(rs.getString(1));
					}
				}
				
				//获取该病的常用药或者治疗方法
				String sql4 = "select `medicine_type`, `medicine_no`, `medicine_name`, count(*) as `Count` from `doc_info` where `ill_no`='" + i_no + "' group by `medicine_no` order by `Count` desc LIMIT 0," + countInfo;
				System.out.println(sql4);
				rs = stmt.executeQuery(sql4);
				while(rs.next()){
					Medicine m = new Medicine(rs.getString("medicine_type"), rs.getString("medicine_no"), rs.getString("medicine_name"), rs.getString("Count"));
					i.medicine.add(m);
				}
				
				//获取该病的高发地区
				String sql5 = "select `patient_area_no`, `patient_area_addr`, count(*) as `Count` from `hos_per_info` where `ill_no`='" + i_no + "' group by `patient_area_no` order by `Count` desc LIMIT 0," + countInfo;
				System.out.println(sql5);
				rs = stmt.executeQuery(sql5);
				while(rs.next()){
					PatientArea pa = new PatientArea(rs.getString("patient_area_no"), rs.getString("patient_area_addr"), rs.getString("Count"));
					i.patientArea.add(pa);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		return illList;
	}

	public List<Ill> getIllNames(int number){
		List<Ill> illList = new ArrayList<Ill>();
		Statement stmt = null;
		try {
//			conn.setAutoCommit(false);  
//			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,  
//                    ResultSet.CONCUR_READ_ONLY);
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet rs = null;
		String sql = "select `ill_no`, `ill_name`, count(*) as `Count` from `hos_per_info` group by `ill_no` order by `Count` desc LIMIT 0, " + number;
		try {
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String illNo = rs.getString(1);
				String illName = rs.getString(2);
				String count = rs.getString(3);
				Ill ill = new Ill(illNo, illName, count);
				if(illNo.equals(" ")){
					continue;
				}
				illList.add(ill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return illList;
	}
}
