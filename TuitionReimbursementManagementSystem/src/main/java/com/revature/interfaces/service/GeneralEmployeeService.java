package com.revature.interfaces.service;

import java.util.List;

import com.revature.interfaces.dao.DAO;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;
import com.revature.pojos.files.NormalFile;

public interface GeneralEmployeeService {
	
	/**
	 * Allows Employees to request a tuition reimbursement
	 * @param stub The information that was entered on the form in the webpage should
	 * be passed as a skeleton for the request. The Employee and id should be added by
	 * this method to the request, and then this should be submitted for review to any
	 * supervisors or direct supervisors
	 * @param requesting The Employee requesting the reimbursement. This Employee should
	 * be fully initialized from the database. Recommended to use the getEmployee method
	 * in order to get the full Employee if they are not currently available.
	 */
	public void requestReimbursement(Request stub, Employee requesting);
	
	/**
	 * Allows an Employee to view a single Request's current status
	 * Will do nothing if the requesting Employee has no access to the 
	 * requested Reimbursement Request
	 * @param reqId The id of the request to view
	 * @param viewing The fully initialized Employee that wishes to view all information
	 * about the given request
	 * @return Returns the request if the employee is allowed to view the request and
	 * the request exists
	 * Returns null otherwise
	 */
	public Request viewRequestStatus(int reqId, Employee viewing);
	
	/**
	 * Allows an employee to provide a grade for their course
	 * If the employee gave a presentation, the grade should be
	 * presentation
	 * @param reqId the request id that the employee is adding a grade for
	 * @param grade the grade that the employee got during their course
	 * @param adding The requesting employee that wishes to attach the grade that
	 * they received during their session eligible for reimbursement
	 */
	public void addGrade(int reqId, String grade, Employee adding);
	
	/**
	 * Cancels the specified request if the given employee is the requesting
	 * employee
	 * @param toCancel The request id that represents the Request that 
	 * 	the Employee wants to cancel
	 * @param cancelling The Employee wishing to cancel their request. This must be the
	 * employee who made the reimbursement request
	 */
	public void cancel(int toCancel, Employee cancelling);
	
	/**
	 * Resets all employees reimbursement amount
	 * Should only be called once at the beginning of every year
	 * Method should fail if not called before the 3rd of January.
	 * 	Developers will then have to go in and manually adjust everything 
	 * 	in the database
	 * Intended to be called by a job scheduler
	 */
	public void resetReimbursements();
	
	/**
	 * Gets the specified employee
	 * @param emp The employee to get (only holding the username and password)
	 * @return The Employee object represented by the passed object
	 */
	public Employee getEmployee(Employee emp);
	
	/**
	 * Gets the Employee represented by the given pk
	 * @param pk The Employee's id or username that you wish to get from the
	 * database
	 * @return An Employee object with id or username pk
	 */
	public Employee getEmployee(String pk);
	
	/**
	 * Makes a brand new Employee
	 * @param emp The Full skeleton from the registration form to apply for an account
	 * @return The newly made Employee object
	 */
	public Employee makeEmployee(Employee emp);
	
	/**
	 * Updates the Employee that has the given employee's id with the information
	 *  provided
	 * Does nothing if this employee is not in the database already
	 * @param emp The employee to update in the database
	 */
	public void updateEmployee(Employee emp);
	
	/**
	 * Adds a file to the list of files for the request
	 * @param file the file to be added
	 * @param The request to add the file to
	 */
	public void addFile(NormalFile file, int reqId);
	
	/**
	 * Removes a file from the list of files for the request
	 * @param file the file to be removed
	 * @param The request to remove the file from
	 */
	public void removeFile(NormalFile file, int reqId);

	/**
	 * Views all of the requests for the specified employee
	 * @param viewing the employee wishing to view all of their requests
	 * @return A list of all of the requests made by the given Employee
	 */
	public List<Request> getAllRequests(Employee viewing);
	
	/**
	 * Awards the amount specified in the request to the specified
	 * employee
	 * @param awarded The employee to be awarded their reimbursement
	 * @param reqId The id representing the request to be completed
	 * and awarded to the employee
	 */
	public void awardAmount(Employee awarded, int reqId);
	
	/**
	 * Gets the available reimbursement for the given employee
	 * @param toCalculate The employee to calculate the available reimbursement for
	 * @return The available reimbursement for the given employee
	 */
	public double availableReimbursement(Employee toCalculate); 
	
	/**
	 * Checks to make sure that the employee is valid
	 * @param loggingIn The employee that is logging in
	 * @return true if the employee exists
	 * 		   false otherwise
	 */
	public boolean validateEmployee(Employee loggingIn);
	
	/**
	 * Gets the specified direct supervisor object from the database
	 * @param toGet The employee that is under the needed direct supervisor
	 * @return The employee representing the direct supervisor
	 */
	public Employee getSupervisor(Employee subordinate);
	
	/**
	 * Adds the specified DAO to this class. Mainly added to aid with testing
	 * purposes
	 * @param empDAO The DAO to set for this service to use
	 */
	public void setDAO(DAO<Employee> empDAO);
	
}
