package com.cinema.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cinema.dao.PersonDAO;
import com.cinema.entity.Person;

public class SignUpCommand implements Command {
	
	private static final Logger logger = LogManager.getLogger();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	String userPath;

		// (1) obtain an input info
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int role = 2;
		System.out.println("email ==> " + email + "; name ==> " + name);
		
		// (2) process an input info
		// obtain from DB
		Person person = new Person();
		person.setEmail(email);
		person.setPassword(password);
		person.setName(name);
		person.setRole(role);
		
		System.out.println("encrypted password = " + person.encryptPassword(password));
		
		// if new user was added successfully - save info in session and go to index.jps 
        if (PersonDAO.insertPerson(person)) {
    		session.setAttribute("currentUser", name);
    		userPath = "index";
        } else {
        	userPath = "signup";
        }

    	return userPath;
    }
}
