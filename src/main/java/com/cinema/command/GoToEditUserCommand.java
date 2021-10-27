package com.cinema.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cinema.dao.PersonDAO;
import com.cinema.entity.Person;

public class GoToEditUserCommand implements Command {
	
	private static final Logger logger = LogManager.getLogger();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	String userPath = "user-form";

    	int id = Integer.parseInt(request.getParameter("id"));
        Person existingPerson = PersonDAO.getPersonById(id);
        session.setAttribute("user", existingPerson);
    
    	return userPath;
    }
}
