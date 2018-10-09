package com.revature.interfaces.service;

import java.util.List;

import com.revature.interfaces.dao.DAO;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;

public interface GeneralRequestService {
	
	/**
	 * Gets a request from the given id
	 * @param reqId The id representing the Request to get
	 * @return The request represented by the given id
	 */
	public Request getRequest(int reqId);
	
	/**
	 * Gets all the requests from the specified Employee
	 * @param emp The employee to get all the requests for
	 * @return A list of all the requests that this employee has made
	 * This will include cancelled Requests
	 */
	public List<Request> getAllRequests(Employee emp);
	
	/**
	 * Creates a new request
	 * @param req the request to be created
	 */
	public Request createRequest(Request req);
	
	/**
	 * Inactivates the request
	 * @param req The request to finish
	 */
	public void finishRequest(Request req);
	
	/**
	 * Adds the specified DAO to this class. Mainly added to aid with testing
	 * purposes
	 * @param reqDao The DAO to set for this service to use
	 */
	public void setDAO(DAO<Request> reqDao);
	
	/**
	 * Updates the given request
	 * @param req The request to update that contains the same reqId as the request to
	 * update
	 */
	public void updateRequest(Request req);

}
