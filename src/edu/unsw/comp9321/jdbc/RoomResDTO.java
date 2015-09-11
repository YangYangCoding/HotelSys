package edu.unsw.comp9321.jdbc;

import java.util.Date;

public class RoomResDTO {
	private String hotelname;
	private int hotelid;
	private int nbofroom;
	private String type;
	private float price;
	
	private Date checkin;
	private Date checkout;
	


	public RoomResDTO(String hotelname, int hotelid, int nbofroom, String type,
			float price, Date checkin, Date checkout) {
		super();
		this.hotelname = hotelname;
		this.hotelid = hotelid;
		this.nbofroom = nbofroom;
		this.type = type;
		this.price = price;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public int getHotelid() {
		return hotelid;
	}

	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}

	public int getNbofroom() {
		return nbofroom;
	}

	public void setNbofroom(int nbofroom) {
		this.nbofroom = nbofroom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	
	
}
