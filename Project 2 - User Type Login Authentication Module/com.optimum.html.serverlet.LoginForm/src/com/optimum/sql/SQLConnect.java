package com.optimum.sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnect {

	private static Connection ping;
	
	public static Connection getConnection() {
        if (ping != null)
            return ping;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = SQLConnect.class.getClassLoader().getResourceAsStream("/db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                ping = DriverManager.getConnection(url, user, password);
                System.out.println("Connection Established Successfull");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ping;
        }

    }
}

	

