package com.revature.interfaces.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.TestDao;
import com.revature.interfaces.dao.DAO;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;
import com.revature.service.RequestService;

public class GeneralRequestServiceTest {
	
	private DAO<Request> reqDao;
	private GeneralRequestService reqServ;
	private Request req;
	private Employee emp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		reqDao = new TestDao<Request>();
		emp = new Employee();
		emp.setEmpId(1);
		req = new Request();
		req.setRequestId(1);
		req.setApplier(emp);
		reqDao.create(req);
		reqServ = new RequestService();
		reqServ.setDAO(reqDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRequest() {
		assertEquals("Testing getting a request", req,
				reqServ.getRequest(req.getRequestId()));
	}

	@Test
	public void testGetAllRequests() {
		ArrayList<Request> reqList = new ArrayList<Request>();
		reqList.add(req);
		assertEquals("Testing getting all requests", reqList,
				reqServ.getAllRequests(emp));
	}

	@Test
	public void testCreateRequest() {
		Request newReq = new Request();
		newReq.setRequestId(2);
		reqServ.createRequest(newReq);
		assertEquals("Testing creating a new request", newReq,
				reqDao.getById("" + newReq.getRequestId()));
	}

	@Test
	public void testFinishRequestDenied() {
		req.setRequestStatus(Request.Status.CREATED);
		reqServ.finishRequest(req);
		assertEquals("Testing improperly finishing a request", Request.Status.DENIED,
				req.getRequestStatus());
	}
	
	@Test
	public void testFinishRequestApproved() {
		req.setRequestStatus(Request.Status.APPROVED);
		reqServ.finishRequest(req);
		assertEquals("Testing correctly finishing a request", Request.Status.APPROVED,
				req.getRequestStatus());
	}
	
	@Test
	public void testUpdateRequest() {
		Request anotherReq = new Request();
		anotherReq.setRequestId(1);
		anotherReq.setAmount(200.0);
		reqServ.updateRequest(anotherReq);
		assertEquals("Testing updating a request", anotherReq.getAmount(), 
				reqDao.getById("" + anotherReq.getRequestId()));
	}

}
