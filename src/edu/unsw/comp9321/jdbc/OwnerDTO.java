package edu.unsw.comp9321.jdbc;

public class OwnerDTO {
	private int ownerid;
	private String owneraccount;
	private String ownerpassword;
	
	public OwnerDTO(int ownerid, String owneraccount, String ownerpassword) {
		super();
		this.ownerid = ownerid;
		this.owneraccount = owneraccount;
		this.ownerpassword = ownerpassword;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public String getOwneraccount() {
		return owneraccount;
	}

	public void setOwneraccount(String owneraccount) {
		this.owneraccount = owneraccount;
	}

	public String getOwnerpassword() {
		return ownerpassword;
	}

	public void setOwnerpassword(String ownerpassword) {
		this.ownerpassword = ownerpassword;
	}
	
	
}
