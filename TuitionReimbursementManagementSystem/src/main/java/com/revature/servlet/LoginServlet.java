package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.interfaces.service.GeneralEmployeeService;
import com.revature.pojos.Employee;
import com.revature.service.EmployeeService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static GeneralEmployeeService empServ = new EmployeeService();

    public LoginServlet() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if(sess != null && sess.getAttribute("exit") == null)
			sess.setAttribute("exit", true);
		if(sess == null || (Boolean)sess.getAttribute("exit")){
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect("home");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee loggingIn = new  Employee(username, password);
		if(empServ.validateEmployee(loggingIn)) {
			HttpSession sess = request.getSession(true);
			Employee loggedIn = empServ.getEmployee(loggingIn);
			sess.setAttribute("emp_id", loggedIn.getEmpId());
			sess.setAttribute("exit", false);
			response.sendRedirect("home");
		}
		else
			response.sendRedirect("login");
	}

}
