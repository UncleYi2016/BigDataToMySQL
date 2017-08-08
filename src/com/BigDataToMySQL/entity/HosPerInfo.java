package com.BigDataToMySQL.entity;

public class HosPerInfo {
	private String visitNo;	
	private String illNo;
	private String illName;
	private String visitTime;
	private String patientNo;
	private String patientAreaNo;
	private String patientAddr;
	private String patientGender;
	private String patientAge;
	
	
	
	public HosPerInfo(){}
	
	public HosPerInfo(String visitNo, String illNo, String illName, String visitTime, 
			String patientNo, String patientAreaNo, String patientAddr, 
			String patientGender, String patientAge){
		this.visitNo=visitNo;
		this.illNo=illNo;
		this.illName=illName;
		this.visitTime=visitTime;
		this.patientNo=patientNo;
		this.patientAreaNo=patientAreaNo;
		this.patientAddr=patientAddr;
		this.patientGender=patientGender;
		this.patientAge=patientAge;
	}
	
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
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
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getPatientAreaNo() {
		return patientAreaNo;
	}
	public void setPatientAreaNo(String patientAreaNo) {
		this.patientAreaNo = patientAreaNo;
	}
	public String getPatientAddr() {
		return patientAddr;
	}
	public void setPatientAddr(String patientAddr) {
		this.patientAddr = patientAddr;
	}



	public String getPatientGender() {
		return patientGender;
	}



	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public String getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
}