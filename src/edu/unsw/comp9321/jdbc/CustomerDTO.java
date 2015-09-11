package edu.unsw.comp9321.jdbc;

public class CustomerDTO {

	
	
	private int customerID;
	private String name;
	private String pin;
	private String email;
	private String cardNum;
	
	
	public CustomerDTO(int customerID, String name, String pin, String email,
			String cardNum) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.pin = pin;
		this.email = email;
		this.cardNum = cardNum;
	}


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCardNum() {
		return cardNum;
	}


	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	
	
	
	
	

}
