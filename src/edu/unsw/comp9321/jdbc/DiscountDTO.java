package edu.unsw.comp9321.jdbc;

import java.text.DateFormat;
import java.util.Date;

public class DiscountDTO {
	private int discountid;
	private int hotelid;
	private String hotelname;
	private float discount;
	private String type;
	private Date startdate;
	private Date enddate;
	
	public DiscountDTO(int discountid, int hotelid, String hotelname, float discount, String type,
			Date startdate, Date enddate) {
		super();
		this.discountid = discountid;
		this.hotelid = hotelid;
		this.hotelname = hotelname;
		this.discount = discount;
		this.type = type;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public int getDiscountid() {
		return discountid;
	}

	public void setDiscountid(int discountid) {
		this.discountid = discountid;
	}

	public int getHotelid() {
		return hotelid;
	}

	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	
	
}
