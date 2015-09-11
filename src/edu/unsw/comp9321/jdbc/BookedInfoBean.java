package edu.unsw.comp9321.jdbc;

public class BookedInfoBean {
	private int bookingID;
	private int roomID;
	public BookedInfoBean(int bookingID, int roomID) {
		super();
		this.bookingID = bookingID;
		this.roomID = roomID;
	}
	
	
	public int getBookingID() {
		return bookingID;
	}
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	
	

}
