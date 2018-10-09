package com.revature.interfaces.service;

import com.revature.interfaces.dao.DAO;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;

public interface GeneralSupervisorService {
	
	/**
	 * Approves the given request if the request is in the correct stage
	 * for this Employee to see
	 * Otherwise this method does nothing
	 * @param approver The Direct Supervisor, Department Head, or BenCo that the request
	 * is currently requiring approval from
	 * @param reqId The request that needs approval
	 */
	public void approve(Employee approver, int reqId);
	
	/**
	 * Denies the given request if the request is in the correct stage for the
	 * given employee to see
	 * Otherwise this method does nothing
	 * @param denier The Direct Supervisor, Department Head, or BenCo that the request
	 * is currently requiring approval from
	 * @param reqId The request that needs approval but will be denied
	 * @param reason The reason that the denier is denying the request
	 */
	public void deny(Employee denier, int reqId, String reason);
	
	/**
	 * Approves the presentation if everything is correct
	 * If the approver is not the correct direct supervisor or if the
	 * request is in an incorrect state, this will do nothing
	 * @param approver The Direct Supervisor approving the request
	 * @param reqId The request to be approved
	 */
	public void approvePresentation(Employee approver, int reqId);
	
	/**
	 * Denies the presentation if the status of everything is correct
	 * Otherwise, this does nothing
	 * @param denier The Direct Supervisor denying the request
	 * @param reqId The request to be denied
	 * @param reason The reason why the presentation was not satisfactory
	 */
	public void denyPresentation(Employee denier, int reqId, String reason);
	
	/**
	 * Approves the grade if the status of everything is correct
	 * Otherwise, this does nothing
	 * @param approver The BenCo approving the request
	 * @param reqId The request to be approved
	 */
	public void approveGrade(Employee approver, int reqId);
	
	/**
	 * Denies the grade if the status of everything is correct
	 * Otherwise, this does nothing
	 * @param denier The BenCo denying the request
	 * @param reqId The request to be denied
	 * @param reason The reason why the request was denied
	 * 	Generally because the grade submitted was not a passing grade
	 */
	public void denyGrade(Employee denier, int reqId, String reason);
	
	/**
	 * Adjusts the amount to be granted to the requesting employee
	 * @param benco The Benco adjusting the amount
	 * @param reqId The request to be adjusted
	 * @param reason An optional reason for adjusting the balance. Only one reason
	 * will be used, any additional reasons inputted will not be reflected in the
	 * database. This field will be required if the balance granted is greater than
	 * the requesting employee's available balance
	 */
	public void adjustAmount(Employee benco, int reqId, String ... reason);
	
	/**
	 * Created solely for testing purposes
	 * Sets the daos for the used employeeService and the used requestService
	 * @param empDao The dao to use for the employee service
	 * @param reqDao The dao to use for the request service
	 */
	public void setDaos(DAO<Employee> empDao, DAO<Request> reqDao);

}
