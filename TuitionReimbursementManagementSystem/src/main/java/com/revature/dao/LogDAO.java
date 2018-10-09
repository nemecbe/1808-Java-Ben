package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class LogDAO {
	
	public static void log(String logMessage, String level) throws SQLException {
		String query =
				"INSERT INTO LOGGER "
				+ "VALUES( 0, ?, ?, CURRENT_TIMESTAMP)";
		PreparedStatement stmt = ConnectionUtil.getConnection().prepareStatement(query);
		stmt.setString(1, level);
		stmt.setString(2, logMessage);
		stmt.executeUpdate();
	}
}