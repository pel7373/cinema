package com.cinema.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cinema.command.AddNewUserCommand;
import com.cinema.command.ChooseLanguageCommand;
import com.cinema.command.Command;
import com.cinema.command.DeleteUserCommand;
import com.cinema.command.GoToEditUserCommand;
import com.cinema.command.ListUsersCommand;
import com.cinema.command.SignInCommand;
import com.cinema.command.SignOutCommand;
import com.cinema.command.SignUpCommand;
import com.cinema.command.UpdateUserCommand;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name="ControllerServlet", 
		loadOnStartup = 1,
		urlPatterns={ "/signin",
					"/signup",
					"/addNewUser",
					"/deleteUser",
					"/goToEditUser",
					"/updateUser",
					"/list-users",
					"/signout",
					"/category",
					"/addToCart",
					"/viewCart", 
					"/updateCart",
					"/checkout",
					"/purchase",
                	"/chooseLanguage"})
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private Map<String, Command> commandMap = new HashMap<String, Command>();
	
	public void init(ServletConfig config)
	          throws ServletException {
		commandMap.put("/signin", 			new SignInCommand());
		
		commandMap.put("/signup", 			      new SignUpCommand());
		commandMap.put("/signout", 			     new SignOutCommand());
		commandMap.put("/list-users", 		   new ListUsersCommand());
		commandMap.put("/addNewUser", 		  new AddNewUserCommand());
		commandMap.put("/deleteUser", 		  new DeleteUserCommand());
		commandMap.put("/goToEditUser", 	new GoToEditUserCommand());
		commandMap.put("/updateUser", 		  new UpdateUserCommand());
		commandMap.put("/chooseLanguage", new ChooseLanguageCommand());
//		commandMap.put("delete", 			new SignInCommand());
//		commandMap.put("delete", 			new SignInCommand());
//		commandMap.put("delete", 			new SignInCommand());
//		commandMap.put("delete", 			new SignInCommand());

	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
        String userPath = request.getServletPath();
		session.removeAttribute("alert-message");
		String requestType = request.getMethod();

		System.out.println("Start of Service# userPath: " + userPath + "; current user: " + request.getParameter("currentUser"));
		
        String commandKey = request.getServletPath();
        Command command = commandMap.get(commandKey);
        
        if (command != null) {
	        try {
				userPath = command.execute(request, response);
			} catch (Exception e) {
				logger.error(userPath + " // " + this.getClass(), e);
			}
        }
        
        System.out.println("#End of Service# userPath: " + userPath + "; currentUser: " + session.getAttribute("currentUser") + "; user: " + session.getAttribute("user"));
        
		String url = userPath + ".jsp";
		if (requestType.equals("POST")) {
			response.sendRedirect(url);
		} else {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
