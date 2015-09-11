package edu.unsw.comp9321.jdbc;

public class RoomTypePriceDTO {
	private String roomtype;
	private float price;
	
	public RoomTypePriceDTO(String roomtype, float price) {
		super();
		this.roomtype = roomtype;
		this.price = price;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
}
