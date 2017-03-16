package servlets;

import java.sql.*;

public class DBManager {

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blabla", "root", "root321");
			return conn;
		} catch (ClassNotFoundException e) {
			return null;

		} catch (SQLException e) {
			return null;
		}

	}

}
