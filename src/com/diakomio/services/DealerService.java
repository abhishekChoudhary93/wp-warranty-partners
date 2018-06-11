package com.diakomio.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diakomio.model.Claim;
import com.diakomio.model.Dealer;
import com.diakomio.model.DealerWarrantyDetails;
import com.diakomio.model.Warranty;
import com.diakomio.util.DBUtil;

public class DealerService {

	public List<Dealer> getAllDealers() throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "select * from dealer_table";
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery(sqlQuery);
		List<Dealer> dealers = new ArrayList<>();
		while (resultSet.next()) {
			Dealer dealer = new Dealer();
			dealer.setDealerName(resultSet.getString("dealerName"));
			dealer.setContactNumber(resultSet.getString("contactNumber"));
			dealer.setAddress(resultSet.getString("address"));
			dealer.setEmail(resultSet.getString("email"));
			dealers.add(dealer);
		}
		return dealers;
	}

	public List<DealerWarrantyDetails> getAllWarrantyDetailsForDealer(String dealerName) throws Exception {
		Connection conn = DBUtil.getConnection();
		String getAllWarranties = "select * from dealer_warranty_details where dealerName = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(getAllWarranties);
		preparedStatement.setString(1, dealerName);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<DealerWarrantyDetails> dealerWarrantyDetailsList = new ArrayList<>();
		while (resultSet.next()) {
			DealerWarrantyDetails dealerWarrantyDetails = new DealerWarrantyDetails();
			dealerWarrantyDetails.setAddress(resultSet.getString("address"));
			dealerWarrantyDetails.setConsumerName(resultSet.getString("consumerName"));
			dealerWarrantyDetails.setDateOfPurchase(resultSet.getString("dateOfPurchase"));
			dealerWarrantyDetails.setOrderNumber(resultSet.getString("orderNumber"));
			dealerWarrantyDetails.setProductName(resultSet.getString("productName"));
			dealerWarrantyDetails.setPurchasePrice(resultSet.getFloat(5));
			dealerWarrantyDetails.setWarrantyNumber(resultSet.getString("warrantyNumber"));
			dealerWarrantyDetails.setProductSerial(resultSet.getString("productSerial"));
			dealerWarrantyDetailsList.add(dealerWarrantyDetails);
		}
		return dealerWarrantyDetailsList;
	}

	public List<Warranty> getAllWarrantiesForDealer(String username) throws Exception {
		Connection conn = DBUtil.getConnection();
		String getDealerName = "select dealerName from dealer_table where username=?";
		PreparedStatement preparedStatement = conn.prepareStatement(getDealerName);
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		String dealerName = "";
		while (resultSet.next()) {
			dealerName = resultSet.getString("dealerName");
		}
		if (dealerName.isEmpty()) {
			return null;
		}
		String getAllWarranties = "select * from warranty_table where dealer=?";
		preparedStatement = conn.prepareStatement(getAllWarranties);
		preparedStatement.setString(1, dealerName);
		resultSet = preparedStatement.executeQuery();
		List<Warranty> warranties = new ArrayList<>();
		while (resultSet.next()) {
			Warranty warranty = new Warranty();
			warranty.setDealer(dealerName);
			warranty.setWarrantyId(resultSet.getString("warrantyId"));
			warranty.setDate(resultSet.getString("date"));
			warranty.setProductSerialNumber(resultSet.getString("productSerialNumber"));
			warranty.setWarrantyNumber(resultSet.getString("warrantyNumber"));
			warranty.setUsername(resultSet.getString("username"));
			warranties.add(warranty);
		}
		return warranties;
	}

	public Dealer getDealer(String username) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "select * from dealer_table where username = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		Dealer dealer = new Dealer();
		while (resultSet.next()) {
			dealer.setDealerName(resultSet.getString("dealerName"));
			dealer.setEmail(resultSet.getString("email"));
			dealer.setContactNumber(resultSet.getString("contactNumber"));
			dealer.setAddress(resultSet.getString("address"));
		}

		return dealer;
	}

	public List<Claim> getClaimsForDealers(String username) throws Exception {
		List<Warranty> warranties = getAllWarrantiesForDealer(username);
		List<Claim> claims = new ArrayList<>();
		for (Warranty warranty : warranties) {
			List<Claim> tempClaims = getClaimsforWarranty(warranty.getWarrantyId());
			for (Claim claim : tempClaims) {
				claims.add(claim);
			}
		}
		return claims;
	}

	public List<Claim> getClaimsforWarranty(String warrantyId) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "select * from claim_table where warrantyId = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, warrantyId);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Claim> claims = new ArrayList<>();
		while (resultSet.next()) {
			Claim claim = new Claim();
			claim.setClaimId(resultSet.getString("claimId"));
			claim.setAddress(resultSet.getString("address"));
			claim.setContactNo(resultSet.getString("contactNumber"));
			claim.setDate(resultSet.getString("date"));
			claim.setStatus(resultSet.getString("status"));
			claim.setName(resultSet.getString("nameForClaim"));
			claim.setUsername(resultSet.getString("username"));
			claim.setWarrantyId(warrantyId);
			claims.add(claim);
		}
		return claims;

	}

	public boolean activateDealer(String username) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "UPDATE user_table SET status='active' where username=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, username);
		int i = preparedStatement.executeUpdate();
		return i == 1 ? true : false;
	}

	public String getDealerEmailFromDealerName(String dealerName) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "select * from dealer_table where dealerName = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, dealerName);
		ResultSet resultSet = preparedStatement.executeQuery();
		Dealer dealer = new Dealer();
		while (resultSet.next()) {
			dealer.setDealerName(resultSet.getString("dealerName"));
			dealer.setEmail(resultSet.getString("email"));
			dealer.setContactNumber(resultSet.getString("contactNumber"));
			dealer.setAddress(resultSet.getString("address"));
		}

		return dealer.getEmail();
	}

	public String getDealerEmailFromWarrantyId(String warrantyId) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "select dealer from warranty_table where warrantyId = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, warrantyId);
		ResultSet resultSet = preparedStatement.executeQuery();
		String dealerName = "";
		while (resultSet.next()) {
			dealerName = resultSet.getString("dealer");
		}
		return getDealerEmailFromDealerName(dealerName);
	}

	public String generateWarrantyNumber(DealerWarrantyDetails dealerWarrantyDetails, String dealerName)
			throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dealerName.substring(0, 2));
		stringBuilder.append(dealerWarrantyDetails.getProductName().substring(0, 1));
		stringBuilder.append(dealerWarrantyDetails.getConsumerName().substring(0, 1));
		String str = dealerWarrantyDetails.getDateOfPurchase();
		str = str.replaceAll("[^a-zA-Z0-9]", "");
		stringBuilder.append(str.substring(5, str.length() - 3));
		String randomUUID = UUID.randomUUID().toString();
		randomUUID = randomUUID.replaceAll("[^\\d.]", "");
		stringBuilder.append(randomUUID.substring(randomUUID.length() - 15, randomUUID.length()));

		final String warrantyNumber = stringBuilder.toString().toUpperCase().substring(0, 16);
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "INSERT INTO dealer_warranty_details (dealerName, warrantyNumber, consumerName, address, purchasePrice, orderNumber, productName, dateOfPurchase,productSerial,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?);";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, dealerName);
		preparedStatement.setString(2, warrantyNumber);
		preparedStatement.setString(3, dealerWarrantyDetails.getConsumerName());
		preparedStatement.setString(4, dealerWarrantyDetails.getAddress());
		preparedStatement.setFloat(5, dealerWarrantyDetails.getPurchasePrice());
		preparedStatement.setString(6, dealerWarrantyDetails.getOrderNumber());
		preparedStatement.setString(7, dealerWarrantyDetails.getProductName());
		preparedStatement.setString(8, dealerWarrantyDetails.getDateOfPurchase());
		preparedStatement.setString(9, dealerWarrantyDetails.getProductSerial());
		preparedStatement.setString(10, "");
		int result = preparedStatement.executeUpdate();
		dealerWarrantyDetails.setWarrantyNumber(warrantyNumber);
		return result == 1 ? warrantyNumber : null;
	}

	public static void main(String[] args) throws Exception {
		DealerWarrantyDetails dealerWarrantyDetails = new DealerWarrantyDetails();
		dealerWarrantyDetails.setAddress("12321312321312");
		dealerWarrantyDetails.setConsumerName("21321312312");
		dealerWarrantyDetails.setDateOfPurchase("2310298310912");
		dealerWarrantyDetails.setOrderNumber("A13221A");
		dealerWarrantyDetails.setProductName("LG1");
		dealerWarrantyDetails.setPurchasePrice((float) 2132133.222);
		dealerWarrantyDetails.setProductSerial("1232131231312123");

		// System.out.println(generateWarrantyNumber(dealerWarrantyDetails,
		// "PROAV"));
	}

}
