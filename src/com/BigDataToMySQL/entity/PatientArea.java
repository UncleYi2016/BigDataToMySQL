package com.BigDataToMySQL.entity;

public class PatientArea {
	private String areaNo;
	private String areaAddr;
	private String count;
	
	public PatientArea(String areaNo, String areaAddr, String count){
		this.areaNo=areaNo;
		this.areaAddr=areaAddr;
		this.count=count;
	}
	
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getAreaAddr() {
		return areaAddr;
	}
	public void setAreaAddr(String areaAddr) {
		this.areaAddr = areaAddr;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}
