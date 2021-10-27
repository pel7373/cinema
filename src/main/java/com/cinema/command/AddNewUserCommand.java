package com.cinema.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cinema.dao.PersonDAO;
import com.cinema.entity.Person;

public class AddNewUserCommand implements Command {
	
	private static final Logger logger = LogManager.getLogger();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	String userPath;
    
		// (1) obtain an input info
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int role = Integer.parseInt(request.getParameter("role"));
		System.out.println("email ==> " + email + "; name ==> " + name);

		if (PersonDAO.getPersonByEmail(email) == null) {
			// (2) process an input info
			// obtain from DB
			Person person = new Person();
			person.setEmail(email);
			person.setPassword(password);
			person.setName(name);
			person.setRole(role);

			PersonDAO.insertPerson(person);

			List<Person> listOfPersons = PersonDAO.getAllPersons();
        	session.setAttribute("listOfUsers", listOfPersons);
        	userPath = "list-users";
		} else {
			session.setAttribute("alert-message", "userWithEnteredEmailAlreadyExists");
            userPath = "user-form";
		}

    	return userPath;
    }

}
