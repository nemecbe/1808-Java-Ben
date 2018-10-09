package com.revature.job;

import com.revature.interfaces.service.GeneralEmployeeService;
import com.revature.service.EmployeeService;

public class ResetRequestsJob implements Runnable {

	private GeneralEmployeeService empServ = new EmployeeService();
	
	@Override
	public void run() {
		empServ.resetReimbursements();
	}

}
