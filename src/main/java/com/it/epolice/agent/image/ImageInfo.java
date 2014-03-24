package com.it.epolice.agent.image;

import java.util.Date;

public class ImageInfo {
	private String picName;
	private String camId;
	private String lane;
	private String carNumber;
	private String carCcolor;
	private Date time;
	private String regulationDesc;
	private String regulationType;
	
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getCamId() {
		return camId;
	}
	public void setCamId(String camId) {
		this.camId = camId;
	}
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarCcolor() {
		return carCcolor;
	}
	public void setCarCcolor(String carCcolor) {
		this.carCcolor = carCcolor;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getRegulationDesc() {
		return regulationDesc;
	}
	public void setRegulationDesc(String regulationDesc) {
		this.regulationDesc = regulationDesc;
	}
	public String getRegulationType() {
		return regulationType;
	}
	public void setRegulationType(String regulationType) {
		this.regulationType = regulationType;
	}
	
}
