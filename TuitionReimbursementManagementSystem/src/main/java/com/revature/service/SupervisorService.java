package com.revature.service;

import java.time.LocalDateTime;

import com.revature.interfaces.dao.DAO;
import com.revature.interfaces.service.GeneralEmployeeService;
import com.revature.interfaces.service.GeneralRequestService;
import com.revature.interfaces.service.GeneralSupervisorService;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;

public class SupervisorService implements GeneralSupervisorService {

	private static GeneralEmployeeService empServ = new EmployeeService();
	private static GeneralRequestService reqServ = new RequestService();

	public SupervisorService() {

	}

	@Override
	public void approve(Employee approver, int reqId) {
		Request toApprove = reqServ.getRequest(reqId);
		approver = empServ.getEmployee(approver);
		if (toApprove != null) {
			Employee applying = toApprove.getApplier();
			if (approver != null && applying != null) {
				if (approver.getEmpId() == applying.getSupervisorId()) {
					if (toApprove.getRequestStatus() == Request.Status.DIRECT_SUPERVISOR) {
						if (approver.getPosition().toLowerCase().contains("head")) {
							toApprove.setRequestStatus(Request.Status.BENEFITS_COORDINATOR);
						} else {
							toApprove.setRequestStatus(Request.Status.DEPARTMENT_HEAD);
						}
						toApprove.setRequestSkipDate(LocalDateTime.now().plusDays(3));
					}
				} else if (approver.getPosition().toLowerCase().contains("head")
						&& approver.getDept().equalsIgnoreCase(applying.getDept())) {
					if (toApprove.getRequestStatus() == Request.Status.DEPARTMENT_HEAD) {
						toApprove.setRequestStatus(Request.Status.BENEFITS_COORDINATOR);
						toApprove.setRequestSkipDate(LocalDateTime.now().plusDays(3));
					}
				} else if (approver.getDept().equalsIgnoreCase("BenCo")) {
					if (toApprove.getRequestStatus() == Request.Status.BENEFITS_COORDINATOR) {
						toApprove.setRequestStatus(Request.Status.PENDING_GRADE);
					}
				}
			}
		}
		reqServ.updateRequest(toApprove);
	}

	@Override
	public void deny(Employee denier, int reqId, String reason) {
		Request toApprove = reqServ.getRequest(reqId);
		denier = empServ.getEmployee(denier);
		if (toApprove != null) {
			Employee applying = toApprove.getApplier();
			if (denier != null && applying != null) {
				if (denier.getEmpId() == applying.getSupervisorId()) {
					if (toApprove.getRequestStatus() == Request.Status.DIRECT_SUPERVISOR) {
						toApprove.setReason(reason);
						denyRequest(toApprove, applying);
					}
				} else if (denier.getPosition().toLowerCase().contains("head")
						&& denier.getDept().equalsIgnoreCase(applying.getDept())) {
					toApprove.setReason(reason);
					denyRequest(toApprove, applying);
				} else if (denier.getDept().equalsIgnoreCase("BenCo")) {
					if (toApprove.getRequestStatus() == Request.Status.BENEFITS_COORDINATOR) {
						toApprove.setReason(reason);
						denyRequest(toApprove, applying);
					}
				}
			}
			empServ.updateEmployee(applying);
		}
		reqServ.updateRequest(toApprove);
	}

	@Override
	public void approvePresentation(Employee approver, int reqId) {
		Request toApprove = reqServ.getRequest(reqId);
		approver = empServ.getEmployee(approver);
		if (toApprove != null) {
			Employee applying = toApprove.getApplier();
			if (approver != null && applying != null) {
				if (approver.getEmpId() == applying.getSupervisorId()) {
					if (toApprove.getRequestStatus() == Request.Status.PENDING_GRADE_APPROVAL) {
						toApprove.setRequestStatus(Request.Status.APPROVED);
					}
				}
			}
			empServ.updateEmployee(applying);
		}
		reqServ.updateRequest(toApprove);
	}

	@Override
	public void denyPresentation(Employee denier, int reqId, String reason) {
		Request toApprove = reqServ.getRequest(reqId);
		denier = empServ.getEmployee(denier);
		if (toApprove != null) {
			Employee applying = toApprove.getApplier();
			if (denier != null && applying != null) {
				if (denier.getEmpId() == applying.getSupervisorId()) {
					if (toApprove.getRequestStatus() == Request.Status.PENDING_GRADE_APPROVAL) {
						toApprove.setReason(reason);
						denyRequest(toApprove, applying);
					}
				}
			}
			empServ.updateEmployee(applying);
		}
		reqServ.updateRequest(toApprove);
	}

	@Override
	public void approveGrade(Employee approver, int reqId) {
		Request toApprove = reqServ.getRequest(reqId);
		approver = empServ.getEmployee(approver);
		if (toApprove != null) {
			if (approver != null &&
					approver.getDept().equalsIgnoreCase("BenCo")) {
				if(toApprove.getRequestStatus() == 
						Request.Status.PENDING_GRADE_APPROVAL) {
					toApprove.setRequestStatus(Request.Status.APPROVED);
				}
			}
			reqServ.updateRequest(toApprove);
		}
	}

	@Override
	public void denyGrade(Employee denier, int reqId, String reason) {
		Request toApprove = reqServ.getRequest(reqId);
		denier = empServ.getEmployee(denier);
		if (toApprove != null) {
			if (denier != null &&
					denier.getDept().equalsIgnoreCase("BenCo")) {
				if(toApprove.getRequestStatus() == 
						Request.Status.PENDING_GRADE_APPROVAL) {
					toApprove.setReason(reason);
					denyRequest(toApprove, toApprove.getApplier());
				}
			}
			empServ.updateEmployee(toApprove.getApplier());
			reqServ.updateRequest(toApprove);
		}
	}

	@Override
	public void adjustAmount(Employee benco, int reqId, String... reason) {
		benco = empServ.getEmployee(benco);
		Request toAdjust = reqServ.getRequest(reqId);
		if(benco != null && toAdjust != null) {
			if(toAdjust.getRequestStatus() == 
					Request.Status.BENEFITS_COORDINATOR &&
					benco.getDept().equalsIgnoreCase("BenCo")) {
				toAdjust.setAmount(toAdjust.getAmount());
				
			}
		}
	}

	@Override
	public void setDaos(DAO<Employee> empDao, DAO<Request> reqDao) {
		reqServ.setDAO(reqDao);
		empServ.setDAO(empDao);
	}
	
	private void denyRequest(Request toDeny, Employee refund) {
		toDeny.setRequestStatus(Request.Status.DENIED);
		refund.setAvailableReimbursement(refund.getAvailableReimbursement()
				+ (toDeny.getAmount() * (toDeny.getEventModifier() / 100.0)));
	}

}
