package com.BigDataToMySQL.entity;

import java.util.ArrayList;
import java.util.List;

public class JsonIll {
	public String illNo;
	public String illName;
	public String count;
	public List<Gender> genders = new ArrayList<Gender>(2);
	
	//地区统计
	public List<String> areaNos = new ArrayList<String>();
	public List<String> areaAddrs = new ArrayList<String>();
	public List<String> areaCounts = new ArrayList<String>();
	
	//年龄统计
	public List<String> ages = new ArrayList<String>();
	public List<String> agesCount = new ArrayList<String>();
	
	//用药统计
	public List<String> medicineTypes = new ArrayList<String>();
	public List<String> medicineNos = new ArrayList<String>();
	public List<String> medicineNames = new ArrayList<String>();
	public List<String> medicineCounts = new ArrayList<String>();
	
	
	public JsonIll(String illNo, String illName, String count){
		this.illNo = illNo;
		this.illName = illName;
		this.count = count;
		ages.add("0~9");
		ages.add("10~19");
		ages.add("20~29");
		ages.add("30~39");
		ages.add("40~49");
		ages.add("50~59");
		ages.add("60~69");
		ages.add("70~79");
		ages.add("80~89");
		ages.add("90~99");
	}
}
