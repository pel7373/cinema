package com.cinema.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinema.dao.PersonDAO;
import com.cinema.entity.Person;

public class ListUsersCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	String userPath = request.getServletPath();
    
    	List<Person> listOfPersons = PersonDAO.getAllPersons();
    	session.setAttribute("listOfUsers", listOfPersons);
    	session.setAttribute("pageListOfUsers", "1");
    	userPath = "list-users";
    	
    	return userPath;
    }
}