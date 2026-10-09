package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static String driver_class = "com.mysql.cj.jdbc.Driver";

	private static String database_url = "jdbc:mysql://localhost:3306/";

	private static String database_name = "batch_k_1335";

	private static String database_username = "root";

	private static String database_password = "root";

	public static Connection getConnection() {

		Connection connection = null;

		try {

			Class.forName(driver_class);

			connection = DriverManager.getConnection(database_url + database_name, database_username,
					database_password);

			System.out.println("Database connected...");

		} catch (ClassNotFoundException e) {

			System.out.println("Driver not found: " + e);

		} catch (SQLException e) {

			System.out.println("SQL Error: " + e);
		}

		return connection;
	}

	public static void closeConnection(Connection connection) {

		try {

			if (connection != null && !connection.isClosed()) {

				connection.close();

				System.out.println("Connection closed.");
			}

		} catch (SQLException e) {

			System.out.println("Error In closing connection: " + e);
		}
	}
}