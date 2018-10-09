package com.revature.interfaces.service;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
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
import com.revature.pojos.files.NormalFile;
import com.revature.service.EmployeeService;
import com.revature.util.MyEvent;

public class GeneralEmployeeServiceTest {
	
	private DAO<Employee> empDao;
	private GeneralEmployeeService empServ;
	private Employee emp;
	private Request req;
	private NormalFile file;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		empServ = new EmployeeService();
		empDao = new TestDao<Employee>();
		emp = new Employee("bnemec", "logic");
		emp.setEmpId(1);
		empDao.create(emp);
		empServ.setDAO(empDao);
		req = new Request(MyEvent.OTHER, Request.Status.CREATED,
				100.0, emp, LocalDateTime.now().plusWeeks(2), "Tampa, FL",
				"Coding bootcamp", "Pass/Fail", "Will gain valuable coding experience",
				null, Duration.ofDays(60), "Pass");
		req.setRequestId(1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRequestReimbursement() {
		req = new Request(MyEvent.OTHER, Request.Status.CREATED,
				100.0, null, LocalDateTime.now().plusWeeks(2), "Tampa, FL",
				"Coding bootcamp", "Pass/Fail", "Will gain valuable coding experience",
				null, Duration.ofDays(60), "Pass");
		empServ.requestReimbursement(req, emp);
		assertEquals("Testing request reimbursement", emp, req.getApplier());
	}

	@Test
	public void testViewRequestStatus() {
		assertEquals("Testing viewing the reqest", req, 
				empServ.viewRequestStatus(req.getRequestId(), emp));
	}

	@Test
	public void testAddGrade() {
		empServ.addGrade(req.getRequestId(), "A", emp);
		assertEquals("Testing add grade", "A", req.getGradeRecieved());
	}

	@Test
	public void testCancel() {
		empServ.cancel(req.getRequestId(), emp);
		assertEquals("Testing cancel method", req.getRequestStatus(), 
				Request.Status.DENIED);
	}

	@Test
	public void testResetReimbursements() {
		emp.setAvailableReimbursement(500.0);
		empServ.resetReimbursements();
		assertEquals("Testing resetting reimbursements", Employee.TOTAL_REIMBURSEMENT,
				emp.getAvailableReimbursement(), 0.001);
	}

	@Test
	public void testGetEmployee() {
		assertEquals("Testing getting employees", emp,
				empServ.getEmployee(new Employee("bnemec", "logic")));
	}

	@Test
	public void testMakeEmployee() {
		Employee e = new Employee("new", "employee");
		e.setEmpId(50);
		assertEquals("Testing making employees", e, empServ.makeEmployee(e));
		assertEquals("Testing employees being stored properly", e,
				empDao.getById("new"));
	}

	@Test
	public void testAddFile() {
		file = new NormalFile();
		empServ.addFile(file, req.getRequestId());
		assertTrue("Testing adding files", req.getFiles().contains(file));
	}

	@Test
	public void testRemoveFile() {
		testAddFile();
		empServ.removeFile(file, req.getRequestId());
		assertFalse("Testing removing files", req.getFiles().contains(file));
	}

	@Test
	public void testGetAllRequests() {
		ArrayList<Request> reqList = new ArrayList<Request>();
		reqList.add(req);
		assertEquals("Testing getting all requests", reqList, 
				empServ.getAllRequests(emp));
	}

	@Test
	public void testAwardAmount() {
		empServ.awardAmount(emp, req.getRequestId());
		assertEquals("Testing awarding the amount", Request.Status.APPROVED,
				req.getRequestStatus());
	}

	@Test
	public void testAvailableReimbursement() {
		assertEquals("Testing available reimbursement", Employee.TOTAL_REIMBURSEMENT,
				empServ.availableReimbursement(emp), 0.001);
	}

	@Test
	public void testValidateEmployee() {
		Employee toValidate = new Employee("bnemec", "logic");
		assertTrue("Testing good validate", empServ.validateEmployee(toValidate));
	}
	
	@Test
	public void testInvalidValidateEmployee() {
		Employee toValidate = new Employee("", "");
		assertFalse("Testing non-existant employee", 
				empServ.validateEmployee(toValidate));
	}
	
	@Test
	public void testBadPasswordValidateEmployee() {
		Employee toValidate = new Employee("bnemec", "LOGIC");
		assertFalse("Testing bad password", empServ.validateEmployee(toValidate));
	}

	@Test
	public void testGetSupervisor() {
		Employee sup = new Employee("sup", "super");
		sup.setEmpId(45);
		empDao.create(sup);
		emp.setSupervisorId(45);
		assertEquals("Testing getting the supervisor of an employee",
				sup, empServ.getSupervisor(emp));
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee newEmp = new Employee("new", "employee");
		newEmp.setEmpId(5);
		empDao.create(newEmp);
		Employee emp2 = new Employee("new", "employee");
		emp2.setEmpId(5);
		emp2.setfName("Newguy");
		empServ.updateEmployee(emp2);
		assertEquals("Testing updating employees", emp2.getfName(),
				empDao.getById("5").getfName());
	}

}
