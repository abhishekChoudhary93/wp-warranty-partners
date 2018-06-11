/**
 * 
 */
package com.diakomio.model;

/**
 * @author abhchoud
 *
 */
public class DealerWarrantyDetails {
	private String consumerName;
	private String address;
	private float purchasePrice;
	private String orderNumber;
	private String productName;
	private String dateOfPurchase;
	private String warrantyNumber;
	private String productSerial;

	/**
	 * @return the consumerName
	 */
	public String getConsumerName() {
		return consumerName;
	}

	/**
	 * @param consumerName
	 *            the consumerName to set
	 */
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the purchasePrice
	 */
	public float getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice
	 *            the purchasePrice to set
	 */
	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber
	 *            the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public String getWarrantyNumber() {
		return warrantyNumber;
	}

	public void setWarrantyNumber(String warrantyNumber) {
		this.warrantyNumber = warrantyNumber;
	}

	public String getProductSerial() {
		return productSerial;
	}

	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}

}
