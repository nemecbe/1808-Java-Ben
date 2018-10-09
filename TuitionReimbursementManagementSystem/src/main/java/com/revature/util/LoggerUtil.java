package com.revature.util;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.dao.LogDAO;

public class LoggerUtil {

	{
		try {
			Class.forName("org.apache.log4j.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	private static Logger log = Logger.getRootLogger();

	// last ditch
	public static void logFatal(String message) {
		log.fatal(message);
		try {
			LogDAO.log(message, "Fatal");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.fatal(e.toString());
		}
	}

	// for when an error happens
	public static void logError(String message) {
		log.error(message);
		try {
			LogDAO.log(message, "Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.fatal(e.toString());
		}
	}

	// catch exception
	public static void logWarn(String message) {
		log.warn(message);
		try {
			LogDAO.log(message, "Warn");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.fatal(e.toString());
		}
	}

	// for general info
	public static void logInfo(String message) {
		log.info(message);
		try {
			LogDAO.log(message, "Info");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.fatal(e.toString());
		}
	}

	// for debugging certain spots
	public static void logDebug(String message) {
		log.debug(message);
		try {
			LogDAO.log(message, "Debug");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.fatal(e.toString());
		}
	}

	// to visualize application line by line
	public static void logTrace(String message) {
		log.trace(message);
		try {
			LogDAO.log(message, "Trace");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.fatal(e.toString());
		}
	}
}
