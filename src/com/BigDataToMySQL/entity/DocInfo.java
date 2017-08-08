package com.BigDataToMySQL.entity;

public class DocInfo {
	private String visitNo;
	private String illNo;
	private String medicineType;
	private String medicineNo;
	private String medicineName;
	
	public DocInfo(){}
	
	public DocInfo(String visitNo, String illNo, String medicineType, String medicineNo, String medicineName){
		this.visitNo=visitNo;
		this.illNo=illNo;
		this.medicineType=medicineType;
		this.medicineNo=medicineNo;
		this.medicineName=medicineName;
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
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public String getMedicineNo() {
		return medicineNo;
	}
	public void setMedicineNo(String medicineNo) {
		this.medicineNo = medicineNo;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
}
