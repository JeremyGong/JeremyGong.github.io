package com.gy.entity;

import java.util.ArrayList;

public class CaseInfo {
	private int id;
	private String caseName;
	private String caseOrigin;
	private String checkPoint;
	private String sex;
	private int age;
	private String importTime;
	private String caseDescription;
	private String category;
	private String type;
	private ArrayList<String> tag; 
	private ArrayList<Sequence> sequenceList;
	private ArrayList<String> multimedia;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCaseOrigin() {
		return caseOrigin;
	}
	public void setCaseOrigin(String caseOrigin) {
		this.caseOrigin = caseOrigin;
	}
	public String getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getImportTime() {
		return importTime;
	}
	public void setImportTime(String importTime) {
		this.importTime = importTime;
	}
	public String getCaseDescription() {
		return caseDescription;
	}
	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<String> getTag() {
		return tag;
	}
	public void setTag(ArrayList<String> tag) {
		this.tag = tag;
	}
	public ArrayList<Sequence> getSequenceList() {
		return sequenceList;
	}
	public void setSequenceList(ArrayList<Sequence> sequenceList) {
		this.sequenceList = sequenceList;
	}
	public ArrayList<String> getMultimedia() {
		return multimedia;
	}
	public void setMultimedia(ArrayList<String> multimedia) {
		this.multimedia = multimedia;
	}
	public CaseInfo(int id, String caseName, String caseOrigin, String checkPoint, String sex, int age,
			String importTime, String caseDescription, String category, String type, ArrayList<String> tag,
			ArrayList<Sequence> sequenceList, ArrayList<String> multimedia) {
		super();
		this.id = id;
		this.caseName = caseName;
		this.caseOrigin = caseOrigin;
		this.checkPoint = checkPoint;
		this.sex = sex;
		this.age = age;
		this.importTime = importTime;
		this.caseDescription = caseDescription;
		this.category = category;
		this.type = type;
		this.tag = tag;
		this.sequenceList = sequenceList;
		this.multimedia = multimedia;
	}
	public CaseInfo(){
		
	}
	
}
