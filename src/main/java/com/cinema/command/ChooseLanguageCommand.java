package com.cinema.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinema.dao.PersonDAO;
import com.cinema.entity.Person;

public class ChooseLanguageCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	String userPath = (String) session.getAttribute("view");
        
        if (userPath == null) {     // if the session doesn't exist -> view == null, 
                                    // so we forward user to the index.jsp
        							// Main work with change language is doing in filter, here doing nothing! 
            userPath = "index";
        } 
        
    	return userPath;
    }
}