package com.diakomio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String username = "root";
	private static String password = "abhishek090993";// abhishek090993
	private static String url = "jdbc:mysql://162.217.249.119:3306/warranty_partners";// 3306
	private static Connection conn;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (conn == null) {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		}
		if (conn.isClosed()) {
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
}
