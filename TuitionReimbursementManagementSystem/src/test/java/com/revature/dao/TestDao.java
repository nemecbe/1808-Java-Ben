package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.interfaces.dao.DAO;

public class TestDao<DataType> implements DAO<DataType> {
	
	//Simulates database
	private List<DataType> database;
	
	public TestDao() {
		database = new ArrayList<DataType>();
	}

	@Override
	public DataType getById(String pk) {
		// TODO Auto-generated method stub
		DataType properObj = null;
		for(DataType data: database) {
			if(data.toString().contains(pk)) {
				properObj = data;
				break;
			}
		}
		return properObj;
	}

	@Override
	public int create(DataType toMake) {
		database.add(toMake);
		return 1;
	}

	//Should not use this method for testing! This class is only meant to simulate
	//a database
	@Override
	public ResultSet execute(String query) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Method Not Implemented! Use this object only"
				+ "for SIMULATING a database.");
	}

	@Override
	public List<DataType> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return database;
	}

	@Override
	public int update(DataType toUpdate) {
		// TODO Auto-generated method stub
		int count = 0;
		for(DataType data: database) {
			if(data.equals(toUpdate)) {
				data = toUpdate;
				count ++;
			}
		}
		return count;
	}

	@Override
	public int delete(DataType toRemove) {
		// TODO Auto-generated method stub
		if(database.remove(toRemove))
			return 1;
		return 0;
	}

}
