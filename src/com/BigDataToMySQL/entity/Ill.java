package com.BigDataToMySQL.entity;

import java.util.ArrayList;
import java.util.List;

public class Ill {
	private String illNo;
	private String illName;
	private String manCount="0";
	private String womanCount="0";
	private String count="0";									//确诊该病的次数
	public List<String> dateTime = new ArrayList<String>();		//确诊该病的时间
	public List<Medicine> medicine = new ArrayList<Medicine>();	//该病使用过的药
	public List<String> ages = new ArrayList<String>();			//一个String代表一个年龄段的总人数
	public List<PatientArea> patientArea = new ArrayList<PatientArea>();
	
	public Ill(String illNo, String illName, String count){
		this.illNo=illNo;
		this.illName=illName;
		this.count=count;
	}
	
	public String getIllNo() {
		return illNo;
	}
	public void setIllNo(String illNo) {
		this.illNo = illNo;
	}
	public String getIllName() {
		return illName;
	}
	public void setIllName(String illName) {
		this.illName = illName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}

	public String getManCount() {
		return manCount;
	}

	public void setManCount(String manCount) {
		this.manCount = manCount;
	}

	public String getWomanCount() {
		return womanCount;
	}

	public void setWomanCount(String womanCount) {
		this.womanCount = womanCount;
	}
	
}
