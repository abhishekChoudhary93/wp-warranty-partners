package com.diakomio.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.diakomio.model.Customer;
import com.diakomio.model.Dealer;
import com.diakomio.model.User;
import com.diakomio.util.DBUtil;

public class UserService {
	public boolean addCustomer(Customer customer, User user, String password) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "insert into customer_table(username, customerName, contactNumber, address, email) values(?,?,?,?,?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, user.username);
		preparedStatement.setString(2, customer.getCustomerName());
		preparedStatement.setString(3, customer.getContactNumber());
		preparedStatement.setString(4, customer.getAddress());
		preparedStatement.setString(5, customer.getEmail());
		int flag1 = preparedStatement.executeUpdate();
		sqlQuery = "insert into user_table(username, password, user_type, status) values(?,?,?,?)";
		preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, user.username);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, user.userType);
		preparedStatement.setString(4, "active");
		int flag2 = preparedStatement.executeUpdate();

		return flag1 == flag2 ? flag1 == 1 ? true : false : false;

	}

	public boolean addDealer(Dealer dealer, User user, String password) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "insert into dealer_table(username, dealerName, contactNumber, address, email) values(?,?,?,?,?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, user.username);
		preparedStatement.setString(2, dealer.getDealerName());
		preparedStatement.setString(3, dealer.getContactNumber());
		preparedStatement.setString(4, dealer.getAddress());
		preparedStatement.setString(5, dealer.getEmail());
		int flag1 = preparedStatement.executeUpdate();
		sqlQuery = "insert into user_table(username, password, user_type, status) values(?,?,?,?)";
		preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, user.username);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, user.userType);
		preparedStatement.setString(4, "pending");
		int flag2 = preparedStatement.executeUpdate();

		return flag1 == flag2 ? flag1 == 1 ? true : false : false;

	}

	public String getCustomerEmail(String username) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sqlQuery = "select * from customer_table where username = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		Customer customer = new Customer();
		while (resultSet.next()) {
			customer.setEmail(resultSet.getString("email"));
		}
		return customer.getEmail();
	}
}
