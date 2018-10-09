package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomePageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if(sess != null && sess.getAttribute("exit") == null)
			sess.setAttribute("exit", true);
		if(sess != null && !(Boolean)sess.getAttribute("exit")) {
			RequestDispatcher rd = request.getRequestDispatcher(
					"EmployeeHub.html");
			rd.forward(request, response);
		}
		else
			response.sendRedirect("login");
	}

}
