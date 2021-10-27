package com.cinema.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignOutCommand implements Command {
	
	private static final Logger logger = LogManager.getLogger();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	String userPath;

		session.removeAttribute("currentUser");
		session.removeAttribute("admin");
		session.removeAttribute("id");
		userPath = "index";
		
		return userPath;
    }
	
}
