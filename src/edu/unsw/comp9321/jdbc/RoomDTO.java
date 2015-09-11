package edu.unsw.comp9321.jdbc;

public class RoomDTO {
	private int roomid;
	private int hotelid;
	private String type;
	private int nbbeds;
	private float price;
	private int available;
	
	public RoomDTO(int roomid, int hotelid, String type, int nbbeds,
			float price, int available) {
		super();
		this.roomid = roomid;
		this.hotelid = hotelid;
		this.type = type;
		this.nbbeds = nbbeds;
		this.price = price;
		this.available = available;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public int getHotelid() {
		return hotelid;
	}

	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNbbeds() {
		return nbbeds;
	}

	public void setNbbeds(int nbbeds) {
		this.nbbeds = nbbeds;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	
}
