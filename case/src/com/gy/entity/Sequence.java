package com.gy.entity;

import java.util.ArrayList;

public class Sequence {
	private String seqId;
	private String seqName;
	private String thumbnail;
	private ArrayList<String> fileName;
	private ArrayList<String> keyImg;
	private ArrayList<String> keyObj;

	public ArrayList<String> getFileName() {
		return fileName;
	}
	public void setFileName(ArrayList<String> fileName) {
		this.fileName = fileName;
	}
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getSeqName() {
		return seqName;
	}
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public ArrayList<String> getKeyImg() {
		return keyImg;
	}
	public void setKeyImg(ArrayList<String> keyImg) {
		this.keyImg = keyImg;
	}
	public ArrayList<String> getKeyObj() {
		return keyObj;
	}
	public void setKeyObj(ArrayList<String> keyObj) {
		this.keyObj = keyObj;
	}

	public Sequence(String seqId, String seqName, String thumbnail, ArrayList<String> keyImg,
			ArrayList<String> keyObj,ArrayList<String> fileName) {
		super();
		this.seqId = seqId;
		this.seqName = seqName;
		this.thumbnail = thumbnail;
		this.keyImg = keyImg;
		this.keyObj = keyObj;
		this.fileName = fileName;
	}
	
	public Sequence(){
		
	}
	
}
