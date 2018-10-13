package com.revature.pojos;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.revature.pojos.files.NormalFile;
import com.revature.util.MyEvent;

public class RequestTest {

	Request req;

	@Before
	public void setUp() throws Exception {
		req = new Request();
	}

	/*
	@Test
	public void testGetEventModifier() {
		req.setEventModifier(MyEvent.TECHNICAL_TRAINING);
		assertEquals("Testing eventModifier methods", MyEvent.TECHNICAL_TRAINING,
				req.getEventModifier());
	}
	
	@Test
	public void testBadEventModifier() {
		req.setEventModifier(MyEvent.OTHER);
		req.setEventModifier(20);
		assertEquals("Testing edge case eventModifier", MyEvent.OTHER,
				req.getEventModifier());
	}

	@Test
	public void testGetRequestStatus() {
		req.setRequestStatus(Request.Status.BENEFITS_COORDINATOR);
		assertEquals("Testing requestStatus methods", 
				Request.Status.BENEFITS_COORDINATOR, req.getRequestStatus());
	}

	@Test
	public void testGetAmount() {
		req.setAmount(500.0);
		assertEquals("Testing amount methods", 500.0, req.getAmount(), 0.001);
	}

	@Test
	public void testGetApplier() {
		Employee e = new Employee();
		req.setApplier(e);
		assertEquals("Testing applier methods", e, req.getApplier());
	}

	@Test
	public void testGetRequestDate() {
		LocalDateTime now = LocalDateTime.now();
		req.setRequestDate(now);
		assertEquals("Testing requestDate methods", now, req.getRequestDate());
	}

	@Test
	public void testGetStartDate() {
		LocalDateTime now = LocalDateTime.now();
		req.setStartDate(now);
		assertEquals("Testing startDate methods", now, req.getStartDate());
	}

	@Test
	public void testGetRequestSkipDate() {
		LocalDateTime now = LocalDateTime.now();
		req.setRequestSkipDate(now);
		assertEquals("Testing skipDate methods", now, req.getRequestSkipDate());
	}

	@Test
	public void testGetLocation() {
		req.setLocation("Tampa, FL");
		assertEquals("Testing location methods", "Tampa, FL", req.getLocation());
	}

	@Test
	public void testGetDescription() {
		req.setDescription("Learning how to become a full stack Java Developer.");
		assertEquals("Testing description methods", 
				"Learning how to become a full stack Java Developer.",
				req.getDescription());
	}

	@Test
	public void testGetFormat() {
		req.setFormat("Graded");
		assertEquals("Testing format methods", "Graded", req.getFormat());
	}

	@Test
	public void testGetJustification() {
		req.setJustification("Gain valuable full stack experience");
		assertEquals("Testing justification methods", 
				"Gain valuable full stack experience",
				req.getJustification());
	}

	@Test
	public void testIsUrgent() {
		req.setUrgent(true);
		assertTrue("Testing urgent methods", req.isUrgent());
	}

	@Test
	public void testGetFiles() {
		ArrayList<NormalFile> files = new ArrayList<NormalFile>();
		req.setFiles(files);
		assertEquals("Testing files methods", files, req.getFiles());
	}

	@Test
	public void testGetTimeMissed() {
		req.setTimeMissed(Duration.ofDays(2));
		assertEquals("Test timeMissed methods", Duration.ofDays(2), 
				req.getTimeMissed());
	}

	@Test
	public void testGetGradeNeeded() {
		req.setGradeNeeded("C");
		assertEquals("Test gradeNeeded methods", "C", req.getGradeNeeded());
	}

	@Test
	public void testGetGradeRecieved() {
		req.setGradeRecieved("A");
		assertEquals("Test gradeRecieved methods", "A", req.getGradeRecieved());
	}

	@Test
	public void testGetReason() {
		req.setReason("Presentation was not satisfactory");
		assertEquals("Test reason methods", 
				"Presentation was not satisfactory", req.getReason());
	}

	@Test
	public void testIsExceedingAllowedAmount() {
		req.setExceedingAllowedAmount(false);
		assertFalse("Test exceedingAllowedAmount methods",
				req.isExceedingAllowedAmount());
	}

	@Test
	public void testGetRequestId() {
		req.setRequestId(10);
		assertEquals("Test requestId methods", 10, req.getRequestId());
	}
*/
}
