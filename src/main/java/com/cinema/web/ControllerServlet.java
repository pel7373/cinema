package com.cinema.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinema.db.DBManager;
import com.cinema.entity.Person;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name="ControllerServlet", 
		loadOnStartup = 1,
		urlPatterns={ "/index",
					"/signin",
					"/signup",
					"/category",
					"/addToCart",
					"/viewCart", 
					"/updateCart",
					"/checkout",
					"/purchase",
                	"/chooseLanguage"})
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();
        
        if (userPath.equals("/signin")) {
        	userPath = "signin";

        // if cart page is requested
        } else 
        // if category page is requested
        if (userPath.equals("/category")) {
            // TODO: Implement category request
        	userPath = "category";

        // if cart page is requested
        } else if (userPath.equals("/viewCart")) {
            // TODO: Implement cart page request
        	
            userPath = "cart";
            
         // if cart page is requested
         } else if (userPath.equals("/index")) {
            // TODO: Implement cart page request
            	
            userPath = "index";


        // if checkout page is requested
        } else if (userPath.equals("/checkout")) {
            // TODO: Implement checkout page request
        	
        	userPath = "checkout";

        // if user switches language
        } else if (userPath.equals("/chooseLanguage")) {
        	// get language choice
            String language = request.getParameter("language");
            
            // place in request scope
            request.setAttribute("language", language);
            
            HttpSession session = request.getSession();
            String userView = (String) session.getAttribute("view");

            if (userView == null) {     // if the session doesn'nt exist -> view == null, 
                                        // so we forward user to the index.jsp
                userPath = "index";
            } else {

                // if previous view is index or cannot be determined, send user to welcome page
            	// forward request to welcome page
            	String url = userView + ".jsp";
            	
	            try {
	                request.getRequestDispatcher(url).forward(request, response);
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	            return;
            }
        }

        // use RequestDispatcher to forward request internally
        String url = userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
        String userPath = request.getServletPath();

		System.out.println("LoginServlet#doPost" + "; userPath " + userPath);
		
		if (userPath.equals("/signup")) {
        	
			// (1) obtain an input info
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
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
	        
	        // if new user was added successfully - save info in session and go to index.jps 
	        if (DBManager.insertPerson(person)) {
	    		
	    		session.setAttribute("currentUser", name);
	    		userPath = "index";
	        } else {
	        	userPath = "signup";
	        }


        // if cart page is requested
        } else if (userPath.equals("/signin")) {
            // TODO: Implement category request
        	userPath = "category";

        // if cart page is requested
        } else if (userPath.equals("/viewCart")) {
            // TODO: Implement cart page request
        	
            userPath = "cart";
            
            
        } else
        
        // if addToCart action is called
        if (userPath.equals("/addToCart")) {
            // TODO: Implement add product to cart action

        // if updateCart action is called
        } else if (userPath.equals("/updateCart")) {
            // TODO: Implement update cart action

        // if purchase action is called
        } else if (userPath.equals("/purchase")) {
            // TODO: Implement purchase action

            userPath = "confirmation";
        }

        // use RequestDispatcher to forward request internally
        String url = userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
