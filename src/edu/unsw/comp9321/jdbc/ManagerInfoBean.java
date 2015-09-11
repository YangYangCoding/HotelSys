package edu.unsw.comp9321.jdbc;

public class ManagerInfoBean {
	
	private int managerId;
	private String managerAccount;
	private String managerPassword;
	private int hotelId;
	
	public ManagerInfoBean(int managerId, String managerAccount,
			String managerPassword, int hotelId) {
		super();
		this.managerId = managerId;
		this.managerAccount = managerAccount;
		this.managerPassword = managerPassword;
		this.hotelId = hotelId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManagerAccount() {
		return managerAccount;
	}

	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	
	
	
	
	
	

}
