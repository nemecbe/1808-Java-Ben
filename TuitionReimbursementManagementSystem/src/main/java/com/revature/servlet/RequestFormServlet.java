package com.revature.servlet;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojos.Employee;
import com.revature.pojos.Request;
import com.revature.pojos.Request.Status;
import com.revature.pojos.files.NormalFile;
import com.revature.service.EmployeeService;
import com.revature.service.RequestService;
import com.revature.util.MyEvent;

public class RequestFormServlet extends HttpServlet {
	
	private static EmployeeService empServ = new EmployeeService();
	private static RequestService reqServ = new RequestService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		if((Boolean)sess.getAttribute("exit") == null)
			sess.setAttribute("exit", true);
		if(sess != null && !(Boolean)sess.getAttribute("exit")) {
			RequestDispatcher rd = req.getRequestDispatcher("RequestForm.html");
			rd.forward(req, resp);
		}
		else
			resp.sendRedirect("login");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		if(sess != null && sess.getAttribute("exit") == null)
			sess.setAttribute("exit", true);
		if(sess != null && !(Boolean)sess.getAttribute("exit")) {
			Employee requesting = empServ.getEmployee(
					((Integer)sess.getAttribute("emp_id")).toString());
			Request request = new Request(getModifier(
					req.getParameter("eventType")), Request.Status.CREATED,
					Double.parseDouble(req.getParameter("cost")), null, getStartDate(
							req.getParameter("startDate"),
							req.getParameter("startTime")),
					req.getParameter("location"),
					req.getParameter("description"), 
					req.getParameter("gradingFormat"), 
					req.getParameter("justification"), null, 
					getTimeMissed(req.getParameter("time")), req.getParameter("gradeReq"));
			empServ.requestReimbursement(request, requesting);
			resp.sendRedirect("home");
		}
		else
			resp.sendRedirect("login");
	}
	
	private Duration getTimeMissed(String time) {
		return Duration.ofHours(Long.parseLong(time));
	}
	
	private int getModifier(String input) {
		input = input.toLowerCase();
		switch(input) {
		case("university"):
			return MyEvent.UNIVERSITY_COURSE;
		case("seminar"):
			return MyEvent.SEMINAR;
		case("prep"):
			return MyEvent.CERTIFICATION_PREP_CLASS;
		case("cert"):
			return MyEvent.CERTIFICATION;
		case("tech"):
			return MyEvent.TECHNICAL_TRAINING;
		case("other"):
			return MyEvent.OTHER;
		}
		return 0;
	}
	
	private LocalDateTime getStartDate(String date, String time) {
		if(date != null && time != null)
			return LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
		return null;
	}

}
