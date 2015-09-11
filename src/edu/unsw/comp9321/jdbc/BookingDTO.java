package edu.unsw.comp9321.jdbc;

import java.text.DateFormat;
import java.util.Date;

public class BookingDTO {
	private int bookingID;
	private int customerID;
	private Date check_IN;
	private Date check_out;
	private String type;
	private float price;
	private int hotelID;
	private String cityname;
	private int assign;
	private int emailsend;
	
	public BookingDTO(int bookingID, int customerID, Date check_IN, Date check_out,
			String type, float price, int hotelID, String cityname, int assign, int emailsend) {
		super();
		this.bookingID = bookingID;
		this.customerID = customerID;
		this.check_IN = check_IN;
		this.check_out = check_out;
		this.type = type;
		this.price = price;
		this.hotelID = hotelID;
		this.cityname = cityname;
		this.assign = assign;
		this.emailsend = emailsend;
	}

	public int getEmailsend() {
		return emailsend;
	}

	public void setEmailsend(int emailsend) {
		this.emailsend = emailsend;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int bookingID) {
		this.customerID = customerID;
	}

	public Date getCheck_IN() {
		return check_IN;
	}

	public void setCheck_IN(Date check_IN) {
		this.check_IN = check_IN;
	}

	public Date getCheck_out() {
		return check_out;
	}

	public void setCheck_out(Date check_out) {
		this.check_out = check_out;
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

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public int getAssign() {
		return assign;
	}

	public void setAssign(int assign) {
		this.assign = assign;
	}
	
	
	
}
