package edu.unsw.comp9321.jdbc;

import java.util.List;
import java.io.Serializable;

public class HotelDTO {
	
	private int hotelID;
	private String hotelname;
	private int cityID;
	private int singleroomNum;
	private int twinbedNum;
	private int queenNum;
	private int executiveNum;
	private int suiteNum;
	
	public HotelDTO(int hotelID, String hotelname, int cityID,
			int singleroomNum, int twinbedNum, int queenNum, int executiveNum,
			int suiteNum) {
		super();
		this.hotelID = hotelID;
		this.hotelname = hotelname;
		this.cityID = cityID;
		this.singleroomNum = singleroomNum;
		this.twinbedNum = twinbedNum;
		this.queenNum = queenNum;
		this.executiveNum = executiveNum;
		this.suiteNum = suiteNum;
	}

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public int getCityID() {
		return cityID;
	}

	public void setCityID(int cityID) {
		this.cityID = cityID;
	}

	public int getSingleroomNum() {
		return singleroomNum;
	}

	public void setSingleroomNum(int singleroomNum) {
		this.singleroomNum = singleroomNum;
	}

	public int getTwinbedNum() {
		return twinbedNum;
	}

	public void setTwinbedNum(int twinbedNum) {
		this.twinbedNum = twinbedNum;
	}

	public int getQueenNum() {
		return queenNum;
	}

	public void setQueenNum(int queenNum) {
		this.queenNum = queenNum;
	}

	public int getExecutiveNum() {
		return executiveNum;
	}

	public void setExecutiveNum(int executiveNum) {
		this.executiveNum = executiveNum;
	}

	public int getSuiteNum() {
		return suiteNum;
	}

	public void setSuiteNum(int suiteNum) {
		this.suiteNum = suiteNum;
	}

	
	
	
	
	
	
	
}
