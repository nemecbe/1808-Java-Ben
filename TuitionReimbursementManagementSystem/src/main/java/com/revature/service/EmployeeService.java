package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.RequestDAO;
import com.revature.interfaces.dao.DAO;
import com.revature.interfaces.service.GeneralEmployeeService;
import com.revature.interfaces.service.GeneralRequestService;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;
import com.revature.pojos.files.NormalFile;
import com.revature.util.LoggerUtil;

public class EmployeeService implements GeneralEmployeeService {
	
	private static GeneralRequestService reqServ = new RequestService();

	private static DAO<Employee> empDao = new EmployeeDAO();

	public EmployeeService() {	
	}

	@Override
	public void resetReimbursements() {
		try {
			for (Employee emp : empDao.getAll()) {
				emp.setAvailableReimbursement(Employee.TOTAL_REIMBURSEMENT);
				for (Request req : reqServ.getAllRequests(emp)) {
					if (req.getRequestStatus() != Request.Status.APPROVED
							|| req.getRequestStatus() != Request.Status.DENIED)
						emp.setAvailableReimbursement(
								emp.getAvailableReimbursement()
								- req.getAmount());
				}
				empDao.update(emp);
			}
		} catch (SQLException e) {
			LoggerUtil.logError(e.toString());
		}
	}

	@Override
	public Employee getEmployee(Employee emp) {
		if(emp != null) {
			Employee getting = empDao.getById(emp.getUsername());
			return getting;
		}
		return null;
	}

	@Override
	public Employee makeEmployee(Employee emp) {
		if(emp != null) {
			empDao.create(emp);
			return emp;
		}
		return null;
	}

	@Override
	public List<Request> getAllRequests(Employee viewing) {
		if(viewing != null) {
			viewing = empDao.getById(viewing.getUsername());
			return reqServ.getAllRequests(viewing);
		}
		return null;
	}

	@Override
	public void awardAmount(Employee awarded, int reqId) {
		if (awarded != null) {
			Request req = reqServ.getRequest(reqId);
			if (req != null && req.getApplier().equals(awarded)) {
				if (req.getRequestStatus() ==
						Request.Status.PENDING_GRADE_APPROVAL) {
					req.setRequestStatus(Request.Status.APPROVED);
					reqServ.updateRequest(req);
				}
			}
		}
	}

	@Override
	public double availableReimbursement(Employee toCalculate) {
		if(toCalculate != null)
			return empDao.getById
					(toCalculate.getUsername()).getAvailableReimbursement();
		return 0.0;
	}

	@Override
	public boolean validateEmployee(Employee loggingIn) {
		if(loggingIn != null) {
			Employee actual = empDao.getById(loggingIn.getUsername());
			if(actual != null && 
					actual.getPassword().equals(loggingIn.getPassword()))
				return true;
		}
		return false;
	}

	@Override
	public Employee getSupervisor(Employee subordinate) {
		if(subordinate != null)
			return empDao.getById("" + subordinate.getSupervisorId());
		return null;
	}

	@Override
	public void setDAO(DAO<Employee> empDAO) {
		EmployeeService.empDao = empDAO;
	}

	@Override
	public void requestReimbursement(Request stub, Employee requesting) {
		if(stub != null && requesting != null &&
				requesting.getAvailableReimbursement() - (stub.getAmount() *
				(stub.getEventModifier() / 100.0)) >= 0.0) {
			stub.setApplier(requesting);
			stub.setRequestStatus(Request.Status.DIRECT_SUPERVISOR);
			reqServ.createRequest(stub);
			requesting.setAvailableReimbursement(
					requesting.getAvailableReimbursement() - (stub.getAmount() *
							(stub.getEventModifier() / 100.0)));
		}
	}

	@Override
	public Request viewRequestStatus(int reqId, Employee viewing) {
		if(viewing != null && reqId != 0) {
			Request req = reqServ.getRequest(reqId);
			if(req != null && viewing.equals(req.getApplier())) {
				return req;
			}
		}
		return null;
	}

	@Override
	public void addGrade(int reqId, String grade, Employee adding) {
		if(reqId != 0 && grade != null && adding != null) {
			Request req = reqServ.getRequest(reqId);
			if(req != null && adding.equals(req.getApplier())) {
				req.setGradeRecieved(grade);
				reqServ.updateRequest(req);
			}
		}
	}

	@Override
	public void cancel(int toCancel, Employee cancelling) {
		Request req = reqServ.getRequest(toCancel);
		if(req.getApplier().equals(cancelling) && 
				req.getRequestStatus() != Request.Status.APPROVED) {
			req.setRequestStatus(Request.Status.DENIED);
			req.setReason("Application cancelled by applier");
			cancelling.setAvailableReimbursement(
					cancelling.getAvailableReimbursement() + 
					(req.getAmount() * (req.getEventModifier() / 100.0)));
		}
	}

	@Override
	public void addFile(NormalFile file, int reqId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFile(NormalFile file, int reqId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEmployee(Employee emp) {
		if(emp != null) {
			Employee old = empDao.getById(emp.getUsername());
			old = setUpdate(old, emp);
			empDao.update(old);
		}
	}
	
	private Employee setUpdate(Employee old, Employee newEmp) {
		if(newEmp.getPassword() != null)
			old.setPassword(newEmp.getPassword());
		if(newEmp.getfName() != null)
			old.setfName(newEmp.getfName());
		if(newEmp.getlName() != null)
			old.setlName(newEmp.getlName());
		if(newEmp.getDept() != null)
			old.setDept(newEmp.getDept());
		if(newEmp.getPosition() != null)
			old.setPosition(newEmp.getPosition());
		if(newEmp.getAddress() != null)
			old.setAddress(newEmp.getAddress());
		if(newEmp.getAvailableReimbursement() != 0.0)
			old.setAvailableReimbursement(newEmp.getAvailableReimbursement());
		if(newEmp.getSupervisorId() != 0)
			old.setSupervisorId(newEmp.getSupervisorId());
		if(newEmp.getBirthday() != null)
			old.setBirthday(newEmp.getBirthday());
		if(newEmp.getLevel() != null)
			old.setLevel(newEmp.getLevel());
		return old;
	}

	@Override
	public Employee getEmployee(String pk) {
		if(pk != null) {
			return empDao.getById(pk);
		}
		return null;
	}

}
