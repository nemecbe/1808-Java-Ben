package com.revature.job;

import java.sql.SQLException;
import java.time.LocalDateTime;

import com.revature.dao.RequestDAO;
import com.revature.interfaces.dao.DAO;
import com.revature.pojos.Request;
import com.revature.util.LoggerUtil;

public class AutoUpdateRequestsJob implements Runnable {
	
	private static DAO<Request> reqDao = new RequestDAO();

	@Override
	public void run() {
		try {
			//System.out.println("Running auto update");
			for(Request req: reqDao.getAll()) {
				switch(req.getRequestStatus()) {
				case DIRECT_SUPERVISOR:
					autoUpdateDS(req);
					break;
				case DEPARTMENT_HEAD:
					autoUpdateDH(req);
					break;
				case BENEFITS_COORDINATOR:
					escalate(req);
					break;
				}
			}
		} catch (SQLException e) {
			LoggerUtil.logError("Issue Running AutoUpdate. Please restart"
					+ " server to fix error: " + e.toString());
		}
	}
	
	private void escalate(Request req) {
		if(LocalDateTime.now().isAfter(req.getRequestSkipDate())) {
			//notify required Benco
		}
	}
	
	private void autoUpdateDS(Request req) {
		if(LocalDateTime.now().isAfter(req.getRequestSkipDate())) {
			req.setRequestStatus(Request.Status.DEPARTMENT_HEAD);
			req.setRequestDate(LocalDateTime.now().plusDays(3));
			reqDao.update(req);
		}
	}
	
	private void autoUpdateDH(Request req) {
		if(LocalDateTime.now().isAfter(req.getRequestSkipDate())) {
			req.setRequestStatus(Request.Status.BENEFITS_COORDINATOR);
			req.setRequestDate(LocalDateTime.now().plusDays(3));
			reqDao.update(req);
		}
	}

}
