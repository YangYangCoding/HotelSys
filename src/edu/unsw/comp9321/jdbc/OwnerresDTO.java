package edu.unsw.comp9321.jdbc;

public class OwnerresDTO {
	private String hotelname;
	private String location;
	private int occproom;
	private int avairoom;
	
	public OwnerresDTO(String hotelname, String location, int occproom,
			int avairoom) {
		super();
		this.hotelname = hotelname;
		this.location = location;
		this.occproom = occproom;
		this.avairoom = avairoom;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getOccproom() {
		return occproom;
	}

	public void setOccproom(int occproom) {
		this.occproom = occproom;
	}

	public int getAvairoom() {
		return avairoom;
	}

	public void setAvairoom(int avairoom) {
		this.avairoom = avairoom;
	}
	
	
}
