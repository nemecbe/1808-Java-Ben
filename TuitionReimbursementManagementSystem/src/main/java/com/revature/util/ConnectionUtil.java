package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection conn;
	
	public static Connection getConnection() throws SQLException {
		if(conn == null || conn.isClosed()) {
		//Gets the name of the aws database
			String url = "jdbc:postgresql://rev1808java-ben-db-primary.cylm7uaepgl0.us-east-2.rds.amazonaws.com:5432/rev1808Ben?";
			String user = "nemecbe";
			String pass ="revature";
			try {
	            Class.forName("org.postgresql.Driver");
	        } 
			catch (ClassNotFoundException e1) {
	            LoggerUtil.logFatal(e1.toString());
	        }
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
		}
		return conn;
	}
	
	public static boolean closeConnection(){
		if(conn != null) {
			try {
				conn.close();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				LoggerUtil.logFatal(e.toString());
				return false;
			}
		}
		return true;
	}	
}
