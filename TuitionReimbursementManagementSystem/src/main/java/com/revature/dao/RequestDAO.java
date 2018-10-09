package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.revature.interfaces.dao.DAO;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;
import com.revature.util.ConnectionUtil;
import com.revature.util.LoggerUtil;

public class RequestDAO implements DAO<Request> {
	
	private static DAO<Employee> empDao = new EmployeeDAO();
	
	public RequestDAO() {
		
	}

	@Override
	public Request getById(String pk) {
		String query = "SELECT * FROM REQUEST "
				+ "WHERE REQ_ID = ?";
		Request toMake = new Request();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, Integer.parseInt(pk));
			ResultSet rs = stmt.executeQuery();
			rs.beforeFirst();
			while(rs.next()) {
				toMake.setRequestId(rs.getInt("req_id"));
				toMake.setAmount(rs.getDouble("amount"));
				toMake.setEventModifier(rs.getInt("modifier"));
				toMake.setRequestStatus(intToStatus(rs.getInt("status")));
				Employee applying = empDao.getById("" + rs.getInt("emp_id"));
				toMake.setApplier(applying);
				toMake.setRequestDate(
						rs.getTimestamp("req_date").toLocalDateTime());
				toMake.setStartDate(
						rs.getTimestamp("start_date").toLocalDateTime());
				toMake.setRequestSkipDate(
						rs.getTimestamp("skip_date").toLocalDateTime());
				toMake.setLocation(rs.getString("location"));
				toMake.setDescription(rs.getString("description"));
				toMake.setFormat(rs.getString("grade_format"));
				toMake.setJustification(rs.getString("justification"));
				toMake.setUrgent(rs.getBoolean("urgent"));
				toMake.setTimeMissed(Duration.ofSeconds(rs.getLong("time_missed")));
				toMake.setGradeNeeded(rs.getString("grade_needed"));
				toMake.setGradeRecieved(rs.getString("grade_received"));
				toMake.setReason(rs.getString("reason"));
				toMake.setExceedingAllowedAmount(rs.getBoolean("exceeding"));
				return toMake;
			}
		}
		catch(SQLException sqlEx) {
			LoggerUtil.logError(sqlEx.toString());
		}
		catch(NumberFormatException badPk) {
			LoggerUtil.logError("Bad primary key input: " + badPk.toString());
		}
		return null;
	}

	@Override
	public int create(Request toMake) {
		String query = "INSERT INTO REQUEST "
				+ "(MODIFIER, STATUS, AMOUNT, EMP_ID, REQ_DATE, START_DATE, "
				+ "SKIP_DATE, LOCATION, DESCRIPTION, GRADE_FORMAT, JUSTIFICATION, "
				+ "URGENT, TIME_MISSED, GRADE_NEEDED, GRADE_RECEIVED, REASON,"
				+ " EXCEEDING) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int rows = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, toMake.getEventModifier());
			stmt.setInt(2, statusToInt(toMake.getRequestStatus()));
			stmt.setDouble(3, toMake.getAmount());
			stmt.setInt(4, toMake.getApplier().getEmpId());
			stmt.setTimestamp(5, Timestamp.valueOf(toMake.getRequestDate()));
			stmt.setTimestamp(6, Timestamp.valueOf(toMake.getStartDate()));
			stmt.setTimestamp(7, Timestamp.valueOf(toMake.getRequestSkipDate()));
			stmt.setString(8, toMake.getLocation());
			stmt.setString(9, toMake.getDescription());
			stmt.setString(10, toMake.getFormat());
			stmt.setString(11, toMake.getJustification());
			stmt.setBoolean(12, toMake.isUrgent());
			stmt.setLong(13, toMake.getTimeMissed().get(ChronoUnit.SECONDS));
			stmt.setString(14, toMake.getGradeNeeded());
			stmt.setString(15, toMake.getGradeRecieved());
			stmt.setString(16, toMake.getReason());
			stmt.setBoolean(17, toMake.isExceedingAllowedAmount());
			rows = stmt.executeUpdate();
			conn.commit();
		}
		catch(Exception e) {
			LoggerUtil.logError(e.toString());
			rows = -1;
		}
		return rows;
	}

	@Override
	public ResultSet execute(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> getAll() throws SQLException {
		String query = "SELECT * FROM REQUEST";
		List<Request> reqList = new ArrayList<Request>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			rs.beforeFirst();
			while(rs.next()) {
				Request toMake = new Request();
				toMake.setRequestId(rs.getInt("req_id"));
				toMake.setAmount(rs.getDouble("amount"));
				toMake.setEventModifier(rs.getInt("modifier"));
				toMake.setRequestStatus(intToStatus(rs.getInt("status")));
				Employee applying = empDao.getById("" + rs.getInt("emp_id"));
				toMake.setApplier(applying);
				toMake.setRequestDate(
						rs.getTimestamp("req_date").toLocalDateTime());
				toMake.setStartDate(
						rs.getTimestamp("start_date").toLocalDateTime());
				toMake.setRequestSkipDate(
						rs.getTimestamp("skip_date").toLocalDateTime());
				toMake.setLocation(rs.getString("location"));
				toMake.setDescription(rs.getString("description"));
				toMake.setFormat(rs.getString("grade_format"));
				toMake.setJustification(rs.getString("justification"));
				toMake.setUrgent(rs.getBoolean("urgent"));
				toMake.setTimeMissed(Duration.ofSeconds(rs.getLong("time_missed")));
				toMake.setGradeNeeded(rs.getString("grade_needed"));
				toMake.setGradeRecieved(rs.getString("grade_received"));
				toMake.setReason(rs.getString("reason"));
				toMake.setExceedingAllowedAmount(rs.getBoolean("exceeding"));
				reqList.add(toMake);
			}
			return reqList;
		}
		catch(SQLException e) {
			LoggerUtil.logError(e.toString());
		}
		return null;
	}

	@Override
	public int update(Request toUpdate) {
		String query = "UPDATE REQUEST "
				+ "SET MODIFIER = ?, STATUS = ?, AMOUNT = ?, EMP_ID = ?, "
				+ "REQ_DATE = ?, START_DATE = ?, SKIP_DATE = ?, LOCATION = ?, "
				+ "DESCRIPTION = ?, GRADE_FORMAT = ?, JUSTIFICATION = ?, "
				+ "URGENT = ?, TIME_MISSED = ?, GRADE_RECEIVED = ?, REASON = ?, "
				+ "EXCEEDING = ? " 
				+ "WHERE REQ_ID = ?";
		int rows = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(query, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, toUpdate.getEventModifier());
			stmt.setInt(2, statusToInt(toUpdate.getRequestStatus()));
			stmt.setDouble(3, toUpdate.getAmount());
			stmt.setInt(4, toUpdate.getApplier().getEmpId());
			stmt.setTimestamp(5, Timestamp.valueOf(toUpdate.getRequestDate()));
			stmt.setTimestamp(6, Timestamp.valueOf(toUpdate.getStartDate()));
			stmt.setTimestamp(7, Timestamp.valueOf(toUpdate.getRequestSkipDate()));
			stmt.setString(8, toUpdate.getLocation());
			stmt.setString(9, toUpdate.getDescription());
			stmt.setString(10, toUpdate.getFormat());
			stmt.setString(11, toUpdate.getJustification());
			stmt.setBoolean(12, toUpdate.isUrgent());
			stmt.setLong(13, toUpdate.getTimeMissed().get(ChronoUnit.SECONDS));
			stmt.setString(14, toUpdate.getGradeRecieved());
			stmt.setString(15, toUpdate.getReason());
			stmt.setBoolean(16, toUpdate.isExceedingAllowedAmount());
			stmt.setInt(17, toUpdate.getRequestId());
			rows = stmt.executeUpdate();
			conn.commit();
		}
		catch(SQLException ex) {
			LoggerUtil.logError(ex.toString());
			rows = -1;
		}
		return rows;
	}

	//Basically an overrated version of the update method
	@Override
	public int delete(Request toRemove) {
		return update(toRemove);
	}
	
	private Request.Status intToStatus(int intStatus){
		switch(intStatus) {
		case 1:
			return Request.Status.DIRECT_SUPERVISOR;
		case 2:
			return Request.Status.DEPARTMENT_HEAD;
		case 3:
			return Request.Status.BENEFITS_COORDINATOR;
		case 4:
			return Request.Status.PENDING_GRADE;
		case 5:
			return Request.Status.PENDING_GRADE_APPROVAL;
		case 6:
			return Request.Status.APPROVED;
		case 7:
			return Request.Status.DENIED;
		}
		return Request.Status.CREATED;
	}
	
	private int statusToInt(Request.Status status) {
		switch(status) {
		case DIRECT_SUPERVISOR:
			return 1;
		case DEPARTMENT_HEAD:
			return 2;
		case BENEFITS_COORDINATOR:
			return 3;
		case PENDING_GRADE:
			return 4;
		case PENDING_GRADE_APPROVAL:
			return 5;
		case APPROVED:
			return 6;
		case DENIED:
			return 7;
		}
		return 0;
	}

}
