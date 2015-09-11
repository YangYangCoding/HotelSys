
package edu.unsw.comp9321.jdbc;

import java.util.Date;

public class CheckpaymentDTO {
	private int hotelid;
	private String hotelname;
	private String type;
	private int nbofroom;
	private float price;
	private Date checkin;
	private Date checkout;
	
	public CheckpaymentDTO(int hotelid, String hotelname, String type, int nbofroom,
			float price, Date checkin, Date checkout) {
		super();
		this.hotelid = hotelid;
		this.hotelname = hotelname;
		this.type = type;
		this.nbofroom = nbofroom;
		this.price = price;
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	
	public int getNbofroom() {
		return nbofroom;
	}


	public void setNbofroom(int nbofroom) {
		this.nbofroom = nbofroom;
	}


	public int getHotelid() {
		return hotelid;
	}
	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
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
	
	
}
