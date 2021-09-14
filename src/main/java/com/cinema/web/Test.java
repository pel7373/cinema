package com.cinema.web;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.cinema.entity.Person;

@WebServlet("/test")
public class Test extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Test#doGet");
		
		HttpSession session = req.getSession();
		
		System.out.println(session);

		session.setAttribute("message", "blablabla");
		session.setAttribute("user", new Person());
		
//		System.out.println(session.getAttribute("message"));
//		System.out.println(session.getAttribute("user"));

		
		System.out.println(session.isNew());
		System.out.println(session.getId());
		
	}	
	
}