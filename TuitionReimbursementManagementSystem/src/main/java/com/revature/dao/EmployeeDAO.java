package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.interfaces.dao.DAO;
import com.revature.pojos.Employee;
import com.revature.util.ConnectionUtil;
import com.revature.util.LoggerUtil;

public class EmployeeDAO implements DAO<Employee> {

	public EmployeeDAO() {

	}

	@Override
	public Employee getById(String pk) {
		boolean NAN = false;
		String query;
		try {
			Integer.parseInt(pk);
		}
		catch(Exception e) {
			NAN = true;
		}
		if(!NAN)
			query = "SELECT * FROM EMPLOYEE " 
					+ "WHERE EMP_ID = ?";
		else
			query = "SELECT * FROM EMPLOYEE "
					+ "WHERE USERNAME = ?";
		Employee toMake = new Employee();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(query, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if(NAN)
				stmt.setString(1, pk);
			else
				stmt.setInt(1, Integer.parseInt(pk));
			ResultSet rs = stmt.executeQuery();
			rs.beforeFirst();
			if (rs.next()) {
				//if employee is still active
				if (rs.getBoolean("active")) {
					// username and password should already be set
					toMake.setEmpId(rs.getInt("emp_id"));
					toMake.setUsername(rs.getString("username"));
					toMake.setPassword(rs.getString("emp_password"));
					toMake.setDept(rs.getString("emp_dept"));
					toMake.setPosition(rs.getString("emp_pos"));
					toMake.setAddress(rs.getString("emp_addr"));
					Date bday = rs.getDate("emp_bday");
					toMake.setBirthday(bday.toLocalDate());
					toMake.setSupervisorId((rs.getInt("emp_sup")));
					toMake.setfName(rs.getString("fname"));
					toMake.setlName(rs.getString("lname"));
					if (toMake.getPosition().toLowerCase().contains("head"))
						toMake.setLevel(Employee.AccessLevel.DEPARTMENT_HEAD);
					else if (isSupervisor(toMake.getEmpId()))
						toMake.setLevel(Employee.AccessLevel.DIRECT_SUPERVISOR);
					else if (toMake.getDept().toLowerCase().contains("benco"))
						toMake.setLevel(Employee.AccessLevel.BEN_CO);
					else
						toMake.setLevel(Employee.AccessLevel.EMPLOYEE);
					toMake.setAvailableReimbursement(rs.getDouble("available"));
					return toMake;
				}
			}
		} catch (Exception e) {
			LoggerUtil.logError(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	private boolean isSupervisor(int supId) {
		// Need to implement at some point
		return false;
	}

	@Override
	public int create(Employee toMake) {
		String query = "INSERT INTO EMPLOYEE " + "(USERNAME, EMP_PASSWORD, FNAME, LNAME, AVAILABLE, EMP_DEPT, EMP_POS,"
				+ " EMP_ADDR, EMP_BDAY, EMP_SUP, ACTIVE, EMP_ID) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TRUE, ?)";
//		String[] pks = new String[1];
//		pks[0] = "emp_id";
		int rows = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, toMake.getUsername());
			stmt.setString(2, toMake.getPassword());
			stmt.setString(3, toMake.getfName());
			stmt.setString(4, toMake.getlName());
			stmt.setDouble(5, toMake.getAvailableReimbursement());
			stmt.setString(6, toMake.getDept());
			stmt.setString(7, toMake.getPosition());
			stmt.setString(8, toMake.getAddress());
			stmt.setDate(9, Date.valueOf(toMake.getBirthday()));
			if (toMake.getSupervisorId() > 0)
				stmt.setInt(10, toMake.getSupervisorId());
			else
				stmt.setNull(10, java.sql.Types.INTEGER);
			stmt.setInt(11, toMake.getEmpId());
			rows = stmt.executeUpdate();
//			ResultSet genKeys = stmt.getGeneratedKeys();
//			while (genKeys.next())
//				toMake.setEmpId(genKeys.getInt("emp_id"));
			conn.commit();
		} catch (SQLException e) {
			LoggerUtil.logError(e.toString());
			rows = -1;
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public ResultSet execute(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAll() throws SQLException {
		String query = "SELECT * FROM EMPLOYEE";
		List<Employee> empList = new ArrayList<Employee>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			rs.beforeFirst();
			while(rs.next()) {
				Employee next = new Employee();
				next.setEmpId(rs.getInt("emp_id"));
				next.setUsername(rs.getString("username"));
				next.setPassword(rs.getString("emp_password"));
				next.setfName(rs.getString("fname"));
				next.setlName(rs.getString("lname"));
				next.setAvailableReimbursement(rs.getDouble("available"));
				next.setDept(rs.getString("emp_dept"));
				next.setPosition(rs.getString("emp_pos"));
				next.setAddress(rs.getString("emp_pos"));
				Date bday = rs.getDate("emp_bday");
				next.setBirthday(bday.toLocalDate());
				next.setSupervisorId(rs.getInt("emp_sup"));
				if (next.getPosition().toLowerCase().contains("head"))
					next.setLevel(Employee.AccessLevel.DEPARTMENT_HEAD);
				else if (isSupervisor(next.getEmpId()))
					next.setLevel(Employee.AccessLevel.DIRECT_SUPERVISOR);
				else if (next.getDept().toLowerCase().contains("benco"))
					next.setLevel(Employee.AccessLevel.BEN_CO);
				else
					next.setLevel(Employee.AccessLevel.EMPLOYEE);
				empList.add(next);
			}
		}
		catch(SQLException e) {
			LoggerUtil.logError(e.toString());
		}
		return empList;
	}

	/**
	 * Updates employee in the database. Can also effectively activate an employee
	 * @param toUpdate The employee to update
	 * @return The number of rows affected
	 * 	Returns 1 if the row is correctly updated
	 * 	Returns 0 if no rows were updated in the database
	 * 	Returns -1 if there was an issue connecting to the database
	 */
	@Override
	public int update(Employee toUpdate) {
		String query = "UPDATE EMPLOYEE " + "SET USERNAME = ?, EMP_PASSWORD = ?, FNAME = ?, LNAME = ?, "
				+ "AVAILABLE = ?, DEPT = ?, EMP_POS = ?, EMP_ADDR = ?, EMP_BDAY = ?, " + "SUP_ID = ?, ACTIVE = TRUE "
				+ "WHERE EMP_ID = ? ";
		int rows = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, toUpdate.getUsername());
			stmt.setString(2, toUpdate.getPassword());
			stmt.setString(3, toUpdate.getfName());
			stmt.setString(4, toUpdate.getlName());
			stmt.setDouble(5, toUpdate.getAvailableReimbursement());
			stmt.setString(6, toUpdate.getDept());
			stmt.setString(7, toUpdate.getPosition());
			stmt.setString(8, toUpdate.getAddress());
			stmt.setDate(9, Date.valueOf(toUpdate.getBirthday()));
			if (toUpdate.getSupervisorId() > 0)
				stmt.setInt(10, toUpdate.getSupervisorId());
			else
				stmt.setObject(10, null);
			stmt.setInt(11, toUpdate.getEmpId());
			rows = stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			LoggerUtil.logError(e.toString());
			rows = -1;
		}
		return rows;
	}

	@Override
	public int delete(Employee toRemove) {
		String query = "UPDATE EMPLOYEE "
				+ "SET ACTIVE = FALSE "
				+ "WHERE EMP_ID = ?";
		int rows = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, toRemove.getEmpId());
			rows = stmt.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			LoggerUtil.logError(e.toString());
			rows = -1;
		}
		return rows;
	}

}
