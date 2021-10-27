package com.cinema.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cinema.dao.PersonDAO;
import com.cinema.entity.Person;

public class UpdateUserCommand implements Command {
	
	private static final Logger logger = LogManager.getLogger();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	String userPath;
    	int id = Integer.parseInt(request.getParameter("id"));
		String email = request.getParameter("email");
		String previousEmail = request.getParameter("previousEmail");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int role = Integer.parseInt(request.getParameter("role"));
		if (password.equals("")) {
			password = request.getParameter("previousPassword");
		}
		System.out.println("id: " + id + "; email ==> " + email + "; password ==> " + password + "; name ==> " + name + "; role ==> " + role);

		Person person = new Person(id, email, password, name, role);
		if (!password.equals("")) {
			person.setPassword(password);
		} 
		
		session.setAttribute("user", person);
		
		if (person.isEmailCorrect(email)) {
			if (PersonDAO.getPersonByEmail(email) == null || previousEmail.equals(email)) {
					//if person with this email doesn't exist - update! 
					// (2) process an input info
					// obtain from DB
					
		        	PersonDAO.updatePerson(person);
		        	session.removeAttribute("user");
		            List<Person> listOfPersons = PersonDAO.getAllPersons();
		        	session.setAttribute("listOfUsers", listOfPersons);
		        	userPath = "list-users";
			} else {
					//if person with this email already exist - don't update and ask to re-enter
		            System.out.println("Опять отправляем юзера на редактирование: id =>" + id);
					session.setAttribute("alert-message", "userWithEnteredEmailAlreadyExists");
		            userPath = "user-form";
			}
		} else {
            System.out.println("Опять отправляем юзера на редактирование из-за неправильного емейла: id =>" + id);
			session.setAttribute("alert-message", "wrongEmail");
            userPath = "user-form";
		}
    
    	return userPath;
    }
}