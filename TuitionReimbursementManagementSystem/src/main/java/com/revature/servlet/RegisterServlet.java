package com.revature.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.interfaces.service.GeneralEmployeeService;
import com.revature.pojos.Employee;
import com.revature.pojos.Employee.AccessLevel;
import com.revature.service.EmployeeService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GeneralEmployeeService empServ = new EmployeeService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Register.html");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee skeleton = new Employee(Integer.parseInt(request.getParameter("id")), 
				request.getParameter("username"), request.getParameter("password"),
				request.getParameter("fname"), request.getParameter("lname"),
				request.getParameter("position"), request.getParameter("address"),
				Integer.parseInt(request.getParameter("supid")), 
				request.getParameter("dept"), LocalDate.parse(request.getParameter("bday")),
				Employee.AccessLevel.EMPLOYEE);
		empServ.makeEmployee(skeleton);	
		response.sendRedirect("login");
	}

}
