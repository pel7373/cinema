package com.cinema.web;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.cinema.entity.Person;

@WebServlet("/test3")
public class Test3 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Test3#doGet");
		
		req.setAttribute("x", 7);
		req.getSession().setAttribute("x", 8);
	//	req.getServletContext().setAttribute("x", 9);
		getServletContext().setAttribute("x", 9);
	}	
	
}