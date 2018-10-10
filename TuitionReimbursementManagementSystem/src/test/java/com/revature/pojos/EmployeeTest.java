package com.revature.pojos;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	
	Employee e;

	@Before
	public void setUp() throws Exception {
		e = new Employee();
	}
	/*

	@Test
	public void testGetEmpId() {
		e.setEmpId(1);
		assertEquals("Testing empId methods", 1, e.getEmpId());
	}

	@Test
	public void testGetUsername() {
		e.setUsername("Ben");
		assertEquals("Tesing username methods", "Ben", e.getUsername());
	}

	@Test
	public void testGetPassword() {
		e.setPassword("Nemec");
		assertEquals("Testing password methods", "Nemec", e.getPassword());
	}

	@Test
	public void testGetfName() {
		e.setfName("Billy");
		assertEquals("Tesing fName methods", "Billy", e.getfName());
	}

	@Test
	public void testGetlName() {
		e.setlName("The Kid");
		assertEquals("Tesing lName methods", "The Kid", e.getlName());
	}

	@Test
	public void testGetPosition() {
		e.setPosition("Department Head");
		assertEquals("Testing position methods", "Department Head", e.getPosition());
	}

	@Test
	public void testGetAddress() {
		e.setAddress("75 Example Lane");
		assertEquals("Testing address methods", "75 Example Lane", e.getAddress());
	}

	@Test
	public void testGetSupervisorId() {
		Employee sup = new Employee();
		sup.setEmpId(2);
		e.setSupervisorId(2);
		assertEquals("Testing supervisorId methods", 2, e.getSupervisorId());
	}

	@Test
	public void testGetDept() {
		e.setDept("BenCo");
		assertEquals("Testing dept methods", "BenCo", e.getDept());
	}

	@Test
	public void testGetBirthday() {
		e.setBirthday(LocalDate.parse("1996-03-12"));
		assertEquals("Testing birthday methods", LocalDate.parse("1996-03-12"),
				e.getBirthday());
	}

	@Test
	public void testGetAvailableReimbursement() {
		assertEquals("Testing getAvailableReimbursement default",
				Employee.TOTAL_REIMBURSEMENT, e.getAvailableReimbursement(), 0.001);
	}
	
	@Test 
	public void testSetAvailableReimbursement() {
		e.setAvailableReimbursement(100.0);
		assertEquals("Testing setAvailableReimbursement method", 100.0,
				e.getAvailableReimbursement(), 0.001);
	}

	@Test
	public void testGetLevel() {
		e.setLevel(Employee.AccessLevel.EMPLOYEE);
		assertEquals("Testing level methods", 
				Employee.AccessLevel.EMPLOYEE, e.getLevel());
	}
*/
}
