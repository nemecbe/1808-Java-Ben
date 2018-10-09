package com.revature.interfaces.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<DataType> {
	
	/**
	 * Returns a ResultSet containing and only containing
	 * the row represented by the String representation of
	 * the primary key
	 * @param pk The primary key of the row to be returned
	 * @return The row represented by pk.
	 */
	public DataType getById(String pk);
	
	/**
	 * Attempts to add a new object to the database
	 * @param toAdd The object to add to the database
	 * @return How many rows were created:
	 * 	returns 1 if the row was created correctly
	 * 	returns 0 if the row could not be inserted
	 * 	returns -1 if the database could not be connected to
	 */
	public int create(DataType toMake);
	
	/**
	 * Executes a query and returns the resulting ResultSet
	 * @param query The query to run on the database
	 * @return the resulting ResultSet or null if there was no
	 * ResultSet returned
	 */
	public ResultSet execute(String query);
	
	/**
	 * Gets all of the records from the current table.
	 * Same as calling select * from TABLE_NAME in execute
	 * @return All of the rows from the current table in a list format
	 */
	public List<DataType> getAll() throws SQLException;
	
	/**
	 * Updates the record passed if it exists
	 * @param toUpdate The object representing the row to be updated
	 * @return The number of rows affected
	 * 	Returns 1 if the row is correctly updated
	 * 	Returns 0 if no rows were updated in the database
	 * 	Returns -1 if there was an issue connecting to the database
	 */
	public int update(DataType toUpdate);
	
	/**
	 * Removes the specified row represented by the
	 * object from the database
	 * @param toRemove The object representing the row to be removed from
	 * the database
	 * @return The number of rows affected
	 * 	Returns 1 if the row is properly removed
	 * 	Returns 0 if no rows were removed
	 * 	Returns -1 if there was an issue connecting to the database
	 */
	public int delete(DataType toRemove);
}
