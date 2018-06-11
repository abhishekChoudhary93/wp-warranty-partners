package com.diakomio.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.diakomio.model.Claim;
import com.diakomio.util.DBUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ClaimsService {

	public boolean addNewClaim(String username, Claim claim) throws Exception {
		Connection conn = DBUtil.getConnection();
		String canFile = "select * from warranty_table where warrantyId = ? and warrantyNumber = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(canFile);
		preparedStatement.setString(1, claim.getWarrantyId());
		preparedStatement.setString(2, claim.getWarrantyNumber());
		ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			throw new MySQLIntegrityConstraintViolationException();
		}
		String alreadyPresent = "select * from claim_table where warrantyId = ? and warrantyNumber = ? and status = ?";
		preparedStatement = conn.prepareStatement(alreadyPresent);
		preparedStatement.setString(1, claim.getWarrantyId());
		preparedStatement.setString(2, claim.getWarrantyNumber());
		preparedStatement.setString(3, "open");
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			throw new MySQLIntegrityConstraintViolationException();
		}
		String sqlQuery = "insert into claim_table(username, nameForClaim, warrantyId, contactNumber, address,status,date,warrantyNumber) values(?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sqlQuery);
		stmt.setString(1, username);
		stmt.setString(2, claim.getName());
		stmt.setString(3, claim.getWarrantyId());
		stmt.setString(4, claim.getContactNo());
		stmt.setString(5, claim.getAddress());
		stmt.setString(6, "Open");
		stmt.setString(7, new Date().toString());
		stmt.setString(8, claim.getWarrantyNumber());
		int i = stmt.executeUpdate();

		return i == 1 ? true : false;

	}

	public List<Claim> getClaims(String username) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "select * from claim_table where username = ?";
		PreparedStatement stmt = conn.prepareStatement(sqlQuery);
		stmt.setString(1, username);
		ResultSet resultSet = stmt.executeQuery();
		List<Claim> claims = new ArrayList<>();
		while (resultSet.next()) {
			Claim claim = new Claim();
			claim.setName(resultSet.getString("nameForClaim"));
			claim.setWarrantyId(resultSet.getString("warrantyId"));
			claim.setClaimId(resultSet.getString("claimId"));
			claim.setContactNo(resultSet.getString("contactNumber"));
			claim.setStatus(resultSet.getString("status"));
			claim.setDate(resultSet.getString("date"));
			claim.setWarrantyNumber(resultSet.getString("warrantyNumber"));
			claims.add(claim);
		}
		return claims;
	}

}
