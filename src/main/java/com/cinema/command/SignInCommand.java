package com.cinema.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cinema.dao.PersonDAO;
import com.cinema.entity.Person;

public class SignInCommand implements Command {
	
	private static final Logger logger = LogManager.getLogger();
	
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	HttpSession session = request.getSession();
    	String userPath = request.getServletPath();
    	
    	// (1) obtain an input info
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Person person = null;
		
		try {
			 person = PersonDAO.getPersonByEmail(email);
		} catch (Exception e) {
			logger.error(userPath + " // " + this.getClass(), e);
			
		}
		
		if (person != null && person.getEmail().equals(email) && person.getPassword().equals(person.encryptPassword(password))) {
			session.setAttribute("currentUser", person.getName());
    		userPath = "index";
    		System.out.println("Залогинился юзер ==> " + email + "; name ==> " + person.getName());
    		if (person.getRole() == 1) {
    			session.setAttribute("admin", person.getName());
    			session.setAttribute("id", person.getId());
    			System.out.println("Залогинился админ ==> " + email + "; name ==> " + person.getName());
	        } 
		} else {
			userPath = "signin";
			System.out.println("Не удалось залогиниться! Email: " + email);
		}

		return userPath;
	}

}
