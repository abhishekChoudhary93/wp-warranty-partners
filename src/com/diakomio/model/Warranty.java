package com.diakomio.model;

public class Warranty {
	private String username;
	private String warrantyId;
	private String productSerialNumber;
	private String dealer;
	private String date;
	private String warrantyNumber;

	public String getWarrantyId() {
		return warrantyId;
	}

	public void setWarrantyId(String warrantyId) {
		this.warrantyId = warrantyId;
	}

	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWarrantyNumber() {
		return warrantyNumber;
	}

	public void setWarrantyNumber(String warrantyNumber) {
		this.warrantyNumber = warrantyNumber;
	}

}
