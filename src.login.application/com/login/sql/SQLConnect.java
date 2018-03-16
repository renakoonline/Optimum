package com.login.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
import com.login.application.LoginPage;
import com.login.controller.User;
import com.login.sql.SQLConnect;

public class SQLConnect {

	//Connection variable for SQL
	private static Connection ping;

	public static Connection getConnection() {
		if (ping != null)	//Check for connection
			return ping;

		try {
			String driver = "com.mysql.jdbc.Driver";	//Determine driver
			String url = "jdbc:mysql://localhost:3306/loginaut";	//Determine database
			String user = "root";	//Determine SQL username	
			String password = "admin";	//Determine SQL password

			//Set Driver environment
			Class.forName(driver);
			ping = DriverManager.getConnection(url, user, password);	//Input Server, username and password into variable

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ping;	//Return connection

	}

	//Close SQL connection
	public static void closeConnection(Connection getClosed) {

		if (getClosed == null)
			return;
		try {
			getClosed.close();	//Execute SQL connection closure
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
