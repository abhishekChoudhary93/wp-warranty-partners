package com.diakomio.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diakomio.model.DealerWarrantyDetails;
import com.diakomio.model.Warranty;
import com.diakomio.util.DBUtil;

public class WarrantyService {

	public List<Warranty> getWarranties(String username) throws ClassNotFoundException, SQLException {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "select * from warranty_table where username = ?";
		PreparedStatement stmt = conn.prepareStatement(sqlQuery);
		stmt.setString(1, username);
		ResultSet resultSet = stmt.executeQuery();
		List<Warranty> warranties = new ArrayList<>();
		while (resultSet.next()) {
			Warranty warranty = new Warranty();
			warranty.setWarrantyId(resultSet.getString("warrantyId"));
			warranty.setProductSerialNumber(resultSet.getString("productSerialNumber"));
			warranty.setDealer(resultSet.getString("dealer"));
			warranty.setDate(resultSet.getString("date"));
			warranty.setWarrantyNumber(resultSet.getString("warrantyNumber"));
			warranties.add(warranty);

		}
		return warranties;
	}

	public int addWarranty(String username, String warrantyNumber, String productSerial) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "select * from dealer_warranty_details where warrantyNumber = ? and productSerial = ? and status = ''";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, warrantyNumber);
		preparedStatement.setString(2, productSerial);
		ResultSet resultSet = preparedStatement.executeQuery();
		DealerWarrantyDetails dealerWarrantyDetails = new DealerWarrantyDetails();
		String dealerName = null;
		while (resultSet.next()) {
			dealerWarrantyDetails.setAddress(resultSet.getString("address"));
			dealerWarrantyDetails.setConsumerName(resultSet.getString("consumerName"));
			dealerWarrantyDetails.setDateOfPurchase(resultSet.getString("dateOfPurchase"));
			dealerWarrantyDetails.setOrderNumber(resultSet.getString("orderNumber"));
			dealerWarrantyDetails.setProductName(resultSet.getString("productName"));
			dealerWarrantyDetails.setPurchasePrice(resultSet.getFloat(5));
			dealerWarrantyDetails.setWarrantyNumber(resultSet.getString("warrantyNumber"));
			dealerWarrantyDetails.setProductSerial(resultSet.getString("productSerial"));
			dealerName = resultSet.getString("dealerName");
		}

		String random = UUID.randomUUID().toString();
		String warrantyRegNumber = random.substring(random.length() - 16, random.length());

		String addWarranty = "INSERT INTO warranty_table (username, warrantyId, productSerialNumber, dealer, date, warrantyNumber) VALUES (?, ?, ?, ?, ?, ?);";
		preparedStatement = conn.prepareStatement(addWarranty);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, warrantyRegNumber);
		preparedStatement.setString(3, productSerial);
		preparedStatement.setString(4, dealerName);
		preparedStatement.setString(5, dealerWarrantyDetails.getDateOfPurchase());
		preparedStatement.setString(6, warrantyNumber);

		int i = preparedStatement.executeUpdate();

		if (i == 1) {
			sqlQuery = "update dealer_warranty_details set status='registered' where warrantyNumber = ?";
			preparedStatement = conn.prepareStatement(sqlQuery);
			preparedStatement.setString(1, warrantyNumber);
			preparedStatement.executeUpdate();
		}

		return i;
	}

}
