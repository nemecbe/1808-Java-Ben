package com.revature.pojos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.revature.pojos.files.NormalFile;
import com.revature.util.MyEvent;

public class Request {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + requestId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Request))
			return false;
		Request other = (Request) obj;
		if (requestId != other.requestId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", eventModifier=" + eventModifier + ", reqStatus=" + reqStatus
				+ ", amount=" + amount + ", applier=" + applier + ", requestDate=" + requestDate + ", startDate="
				+ startDate + ", requestSkipDate=" + requestSkipDate + ", location=" + location + ", description="
				+ description + ", format=" + format + ", justification=" + justification + ", urgent=" + urgent
				+ ", files=" + files + ", timeMissed=" + timeMissed + ", gradeNeeded=" + gradeNeeded
				+ ", gradeRecieved=" + gradeRecieved + ", reason=" + reason + ", exceedingAllowedAmount="
				+ exceedingAllowedAmount + "]";
	}

	public static enum Status{
		CREATED, DIRECT_SUPERVISOR, DEPARTMENT_HEAD, BENEFITS_COORDINATOR,
		PENDING_GRADE, PENDING_GRADE_APPROVAL, APPROVED, DENIED
	}
	
	private int requestId;
	
	private int eventModifier;
	
	private Status reqStatus;
	
	private double amount;
	
	private Employee applier;
	
	private LocalDateTime requestDate;
	
	private LocalDateTime startDate;
	
	private LocalDateTime requestSkipDate;
	
	private String location;
	
	private String description;
	
	private String format;
	
	private String justification;
	
	private boolean urgent;
	
	private List<NormalFile> files;
	
	private Duration timeMissed;
	
	private String gradeNeeded;
	
	private String gradeRecieved;
	
	private String reason;
	
	private boolean exceedingAllowedAmount;

	/**
	 * Use this for creating a generic object, then use the getter and setter methods
	 * in order to complete the object from the database
	 * ***USE WITH DATABASE***
	 */
	public Request() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Use this request to create a skeleton of a request.
	 * @param eventModifier The modifier as specified in the MyEvent file represented
	 * as an integer to identify the total amount of reimbursement allowed for the
	 * event
	 * @param reqStatus The current status of the request
	 * @param amount The total amount that the event will cost
	 * *****NOTE: THIS IS THE ENTIRE AMOUNT, MODIFIERS WILL REIMBURSE THE CORRECT 
	 * AMOUNT WHEN THE USER HAS COMPLETED THE EVENT SUCCESSFULLY*****
	 * @param applier The Employee applying for the request
	 * @param startDate The date that the training starts
	 * ****NOTE: THIS IS NOT THE REQUEST DATE!! THAT IS ASSIGNED BY THE SYSTEM!******
	 * @param location The location where the event will occur
	 * @param description The description of the event
	 * @param format The grading format of the event (presentation or graded)
	 * @param justification The business justification for reimbursing the event
	 * @param files Any attached files that have been applied on submission
	 * @param timeMissed The amount of time from work that the employee will 
	 * miss for the event
	 * @param gradeNeeded The grade that the employee needs in order to receive
	 * reimbursement
	 */
	public Request(int eventModifier, Status reqStatus, double amount, Employee applier,
			LocalDateTime startDate, String location, String description, String format,
			String justification, List<NormalFile> files, Duration timeMissed, 
			String gradeNeeded) {
		setEventModifier(eventModifier);
		this.reqStatus = reqStatus;
		setAmount(amount);
		this.applier = applier;
		this.startDate = startDate;
		this.location = location;
		this.description = description;
		this.format = format;
		this.justification = justification;
		this.files = files;
		this.timeMissed = timeMissed;
		this.gradeNeeded = gradeNeeded;
		this.requestDate = LocalDateTime.now();
		this.requestSkipDate = LocalDateTime.now().plusWeeks(1);
		if(LocalDateTime.now().minusWeeks(2).isAfter(this.startDate))
			this.urgent = true;
		else
			this.urgent = false;
		exceedingAllowedAmount = false;
	}

	public int getEventModifier() {
		return eventModifier;
	}

	public void setEventModifier(int eventModifier) {
		if(eventModifier == MyEvent.CERTIFICATION ||
				eventModifier == MyEvent.CERTIFICATION_PREP_CLASS ||
				eventModifier == MyEvent.OTHER ||
				eventModifier == MyEvent.SEMINAR ||
				eventModifier == MyEvent.TECHNICAL_TRAINING ||
				eventModifier == MyEvent.UNIVERSITY_COURSE)
			this.eventModifier = eventModifier;
	}

	public Status getRequestStatus() {
		return reqStatus;
	}

	public void setRequestStatus(Status reqStatus) {
		this.reqStatus = reqStatus;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		amount = ((int)(amount * 100))/100.0;
		this.amount = amount;
	}

	public Employee getApplier() {
		return applier;
	}

	public void setApplier(Employee applier) {
		this.applier = applier;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getRequestSkipDate() {
		return requestSkipDate;
	}

	public void setRequestSkipDate(LocalDateTime requestSkipDate) {
		this.requestSkipDate = requestSkipDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public List<NormalFile> getFiles() {
		return files;
	}

	public void setFiles(List<NormalFile> files) {
		this.files = files;
	}

	public Duration getTimeMissed() {
		return timeMissed;
	}

	public void setTimeMissed(Duration timeMissed) {
		if(!timeMissed.isNegative())
			this.timeMissed = timeMissed;
	}

	public String getGradeNeeded() {
		return gradeNeeded;
	}

	public void setGradeNeeded(String gradeNeeded) {
		this.gradeNeeded = gradeNeeded;
	}

	public String getGradeRecieved() {
		return gradeRecieved;
	}

	public void setGradeRecieved(String gradeRecieved) {
		this.gradeRecieved = gradeRecieved;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isExceedingAllowedAmount() {
		return exceedingAllowedAmount;
	}

	public void setExceedingAllowedAmount(boolean exceedingAllowedAmount) {
		this.exceedingAllowedAmount = exceedingAllowedAmount;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
}
