package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.RequestDAO;
import com.revature.interfaces.dao.DAO;
import com.revature.interfaces.service.GeneralRequestService;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;
import com.revature.util.LoggerUtil;

public class RequestService implements GeneralRequestService {
	
	DAO<Request> requestDao;
	
	public RequestService() {
		requestDao = new RequestDAO();
	}

	@Override
	public Request getRequest(int reqId) {
		return requestDao.getById(String.valueOf(reqId));
	}

	@Override
	public List<Request> getAllRequests(Employee emp) {
		try {
			List<Request> empRequests = new ArrayList<Request>();
			for(Request req : requestDao.getAll()) {
				if(req.getApplier().equals(emp))
					empRequests.add(req);
			}
			return empRequests;
		}
		catch(SQLException e) {
			LoggerUtil.logError(e.toString());
		}
		return null;
	}

	@Override
	public Request createRequest(Request req) {
		if(req != null)
			requestDao.create(req);
		return req;
	}

	@Override
	public void finishRequest(Request req) {
		if(req != null)
			requestDao.delete(req);
	}

	@Override
	public void setDAO(DAO<Request> reqDao) {
		if(reqDao != null)
			this.requestDao = reqDao;
	}

	@Override
	public void updateRequest(Request req) {
		if(req != null) {
			Request toUpdate = requestDao.getById("" + req.getRequestId());
			
			requestDao.update(req);
		}
	}

}
