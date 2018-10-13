package com.revature.interfaces.service;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.TestDao;
import com.revature.interfaces.dao.DAO;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;
import com.revature.service.SupervisorService;
import com.revature.util.MyEvent;

public class GeneralSupervisorServiceTest {
	
	private Employee emp;
	private Employee sup;
	private Employee benco;
	private Request req;
	private GeneralSupervisorService supServ;
	private DAO<Request> reqDao;
	private DAO<Employee> empDao;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		emp = new Employee("new", "emp");
		emp.setEmpId(1);
		sup = new Employee("new", "sup");
		sup.setEmpId(2);
		emp.setSupervisorId(2);
		benco = new Employee("ben", "co");
		benco.setEmpId(3);
		req = new Request(MyEvent.OTHER, Request.Status.CREATED,
				100.0, emp, LocalDateTime.now().plusWeeks(2), "Tampa, FL",
				"Coding bootcamp", "Pass/Fail", "Will gain valuable coding experience",
				null, Duration.ofDays(60), "Pass");
		reqDao = new TestDao<Request>();
		empDao = new TestDao<Employee>();
		reqDao.create(req);
		empDao.create(benco);
		empDao.create(sup);
		empDao.create(emp);
		supServ = new SupervisorService();
		supServ.setDaos(empDao, reqDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	@Test
	public void testValidApprove() {
		supServ.approve(sup, req.getRequestId());
		assertEquals("Testing valid approve method", Request.Status.DEPARTMENT_HEAD,
				req.getRequestStatus());
	}
	
	@Test
	public void testInvalidApprove() {
		supServ.approve(benco, req.getRequestId());
		assertEquals("Testing invalid approve method", Request.Status.DIRECT_SUPERVISOR,
				req.getRequestStatus());
	}

	@Test
	public void testDeny() {
		supServ.deny(sup, req.getRequestId(), "Because I can");
		assertEquals("Testing denying requests", Request.Status.DENIED, 
				req.getRequestStatus());
	}

	@Test
	public void testApprovePresentation() {
		req.setRequestStatus(Request.Status.PENDING_GRADE_APPROVAL);
		supServ.approvePresentation(sup, req.getRequestId());
		assertEquals("Testing approving presentations", Request.Status.APPROVED,
				req.getRequestStatus());
	}

	@Test
	public void testDenyPresentation() {
		req.setRequestStatus(Request.Status.PENDING_GRADE_APPROVAL);
		
	}

	@Test
	public void testApproveGrade() {
		req.setRequestStatus(Request.Status.PENDING_GRADE_APPROVAL);
	}

	@Test
	public void testDenyGrade() {
		req.setRequestStatus(Request.Status.PENDING_GRADE_APPROVAL);
	}

	@Test
	public void testAdjustAmount() {
		fail("Not yet implemented");
	}
*/
}
