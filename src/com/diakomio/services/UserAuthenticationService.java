package com.diakomio.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diakomio.util.DBUtil;

public class UserAuthenticationService {

	public boolean authorize(String username, String password, String userType) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Connection conn = DBUtil.getConnection();
			String userQuery = "select * from user_table where username=? and password=? and user_type=? and status=?";
			PreparedStatement stmt = conn.prepareStatement(userQuery);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, userType);
			stmt.setString(4, "active");

			ResultSet resultSet = stmt.executeQuery();
			flag = resultSet.next();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

}
