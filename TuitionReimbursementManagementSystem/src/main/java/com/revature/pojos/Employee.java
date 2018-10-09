package com.revature.pojos;

import java.time.LocalDate;

public class Employee {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (empId != other.empId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", username=" + username + ", password=" + password + ", fName=" + fName
				+ ", lName=" + lName + ", position=" + position + ", address=" + address + ", supervisorId="
				+ supervisorId + ", dept=" + dept + ", birthday=" + birthday + ", availableReimbursement="
				+ availableReimbursement + ", level=" + level + "]";
	}

	public static enum AccessLevel{
		EMPLOYEE, DIRECT_SUPERVISOR, DEPARTMENT_HEAD, BEN_CO
	}
	
	public static final int TOTAL_REIMBURSEMENT = 1000;

	private int empId;
	
	private String username;
	
	private String password;
	
	private String fName;
	
	private String lName;
	
	private String position;
	
	private String address;
	
	private int supervisorId;
	
	private String dept;
	
	private LocalDate birthday;
	
	private double availableReimbursement;
	
	private AccessLevel level;
	
	public Employee() {
		availableReimbursement = Employee.TOTAL_REIMBURSEMENT;
	}

	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Employee(int empId, String username, String password, String fName, String lName, String position,
			String address, int supervisorId, String dept, LocalDate birthday, AccessLevel level) {
		this.empId = empId;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.position = position;
		this.address = address;
		this.supervisorId = supervisorId;
		this.dept = dept;
		this.birthday = birthday;
		this.level = level;
		this.availableReimbursement = Employee.TOTAL_REIMBURSEMENT;
	}

	public synchronized int getEmpId() {
		return empId;
	}

	public synchronized void setEmpId(int empId) {
		this.empId = empId;
	}

	public synchronized String getUsername() {
		return username;
	}

	public synchronized void setUsername(String username) {
		this.username = username;
	}

	public synchronized String getPassword() {
		return password;
	}

	public synchronized void setPassword(String password) {
		this.password = password;
	}

	public synchronized String getfName() {
		return fName;
	}

	public synchronized void setfName(String fName) {
		this.fName = fName;
	}

	public synchronized String getlName() {
		return lName;
	}

	public synchronized void setlName(String lName) {
		this.lName = lName;
	}

	public synchronized String getPosition() {
		return position;
	}

	public synchronized void setPosition(String position) {
		this.position = position;
	}

	public synchronized String getAddress() {
		return address;
	}

	public synchronized void setAddress(String address) {
		this.address = address;
	}

	public synchronized int getSupervisorId() {
		return supervisorId;
	}

	public synchronized void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public synchronized String getDept() {
		return dept;
	}

	public synchronized void setDept(String dept) {
		this.dept = dept;
	}

	public synchronized LocalDate getBirthday() {
		return birthday;
	}

	public synchronized void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public synchronized double getAvailableReimbursement() {
		return availableReimbursement;
	}

	public synchronized void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

	public synchronized AccessLevel getLevel() {
		return level;
	}

	public synchronized void setLevel(AccessLevel level) {
		this.level = level;
	}
	
}
