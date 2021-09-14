package com.cinema.web;

import java.io.*;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.cinema.db.DBManager;
import com.cinema.entity.Person;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2414521234293762625L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("LoginServlet#doPost");
		
		req.setCharacterEncoding("UTF-8");
		// (1) obtain an input info
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		System.out.println("login ==> " + login + "; name ==> " + name);
		
		// (2) process an input info
		// obtain from DB
		Person person = new Person();
		person.setLogin(login);
		person.setPassword(password);
		person.setName(name);
		person.setRole(2);
		
		//DBManager.insertPerson(person);
		
		//DBManager.getInstance();
        
        // Part 1
        DBManager.insertPerson(person);
        
        	
		
		String p = "Pel";
		System.out.println(p.hashCode());
		
		ServletContext context = getServletContext();
		Enumeration servNames = context.getAttributeNames();
		while(servNames.hasMoreElements()){
		    System.out.println("Servlet context: " + (String) servNames.nextElement());
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("currentUser", "pel");
		String id = session.getId();
		
		System.out.println("Session id: " + id);
		System.out.println("Session name: " + session.getAttribute("currentUser"));
		
		Enumeration keys = session.getAttributeNames();
		String s = null;
		while(keys.hasMoreElements()){
		    s = (String) keys.nextElement();
		    		//System.out.println("Session: " + (String) keys.nextElement());
		    		System.out.println("Session: " + s);
		    		System.out.println("Get: " + session.getAttribute(s));
		}
		
		Locale localEn = new Locale("en");
    	ResourceBundle rbEn = ResourceBundle.getBundle("resources", localEn);

    	Locale localRu = new Locale("ru");
    	ResourceBundle rbRu = ResourceBundle.getBundle("resources", localRu);
    	
    	String language1 = "ru";
    	String language2 = "en";
    	
    	String value1 = "table";
    	
    	if (language1.equals("ru")) {
			System.out.println("Ru руыыы: " + rbRu.getString(value1));
		}
		
		if (language2.equals("en")) {
			System.out.println("En: " +rbEn.getString(value1));
		}

		session.setAttribute("text", rbRu.getString(value1));
		
		//==================
        
		ServletConfig sc = this.getServletConfig();
		// определение набора имен параметров инициализации
		Enumeration e = sc.getInitParameterNames();
		while(e.hasMoreElements()) {
		// определение имени параметра инициализации
		String name2 = (String)e.nextElement();
		
		System.out.println("Session's name: " + name2);
		
		// определение значения параметра инициализации
		String value = sc.getInitParameter(name2);
		}
/*		
		String forward = "index.html";
				
		HttpSession session2 = req.getSession();
		if (person != null) {
			// (3) save a result as an attibute
			session2.setAttribute("currentUser", person);
			forward = "view.jsp";
		}
		
		// (4) go to a view layer
		req.getRequestDispatcher(forward).forward(req, resp);
		*/
		req.getRequestDispatcher("requestLocale.jsp").forward(req, resp);
	}	
	
}