package com.BigDataToMySQL.entity;

public class Medicine {
	private String medicineType;
	private String medicineNo;
	private String medicineName;
	private String count;			//使用该药的统计次数
	
	public Medicine(String medicineType, String medicineNo, String medicineName, String count){
		this.medicineType=medicineType;
		this.medicineNo=medicineNo;
		this.medicineName=medicineName;
		this.count=count;
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}
