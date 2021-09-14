package com.cinema.web;

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.cinema.entity.Person;

@WebServlet("/test2")
public class Test2 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Test2#doGet");
		
		// obtain info
		String role = req.getParameter("role");
		System.out.println("role ==> " + role);
		
		// generate a result
		String message = "Hi, " + role;
		
		// save a result as an attribute
		req.setAttribute("result", message);
		req.setAttribute("role", role);
		
		// go to a view layer
		req.getRequestDispatcher("test2.jsp")
			.forward(req, resp);
		
	}	
	
}