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

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        String language = request.getParameter("language");

        System.out.println("Start of doGet# userPath: " + userPath + "; user: " + request.getParameter("currentUser"));

        if (userPath.equals("/signout")) {
        	session.removeAttribute("currentUser");
        	session.removeAttribute("admin");
        	// place in request scope
        	userPath = "index";

        } else
        
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
        	
        	//request.setAttribute("language", language);
            
            // place in request scope
            String userView = (String) session.getAttribute("view");
            System.out.println(userView);
            
            if (userView == null) {     // if the session doesn'nt exist -> view == null, 
                                        // so we forward user to the index.jsp
                userPath = "index";
            } else {
                // if previous view is index or cannot be determined, send user to welcome page
            	// forward request to welcome page
            	userPath = userView;
            }
        }
        
        System.out.println("#End of doGet# userPath: " + userPath + "; user: " + request.getParameter("currentUser"));
        
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
                
        System.out.println("Start of doPost# userPath: " + userPath + "; user: " + request.getParameter("currentUser"));

		if (userPath.equals("/signup")) {
        	
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
			
			System.out.println("encrypted password = " + person.encryptPassword(password));
			
			person.setName(name);
			person.setRole(role);
			
			// if new user was added successfully - save info in session and go to index.jps 
	        if (DBManager.insertPerson(person)) {
	    		
	    		session.setAttribute("currentUser", name);
	    		userPath = "index";
	        } else {
	        	userPath = "signup";
	        }

	        // place in request scope
	        //request.setAttribute("language", language);

        // if signin page is requested
        } else if (userPath.equals("/signin")) {
            
        	// TODO: Implement category request
			// (1) obtain an input info
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Person person = null;
			
			try {
				 person = DBManager.getPerson(email);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (person != null && person.getEmail().equals(email) && person.getPassword().equals(person.encryptPassword(password))) {
				session.setAttribute("currentUser", person.getName());
	    		userPath = "index";
	    		System.out.println("Залогинился юзер ==> " + email + "; name ==> " + person.getName());
	    		if (person.getRole() == 1) {
	    			session.setAttribute("admin", person.getName());
	    			System.out.println("Залогинился админ ==> " + email + "; name ==> " + person.getName());
		        } 
			} else {
				userPath = "signin";
			}

			// if sign Out page is requested
        }  else if (userPath.equals("/viewCart")) {
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

        System.out.println("#End of doPost# userPath: " + userPath + "; user: " + request.getParameter("currentUser"));

        // use RequestDispatcher to forward request internally
		//String url = request.getContextPath() + "/" + userPath;
        //response.sendRedirect(url);
		String url = userPath + ".jsp";
		request.getRequestDispatcher(url).forward(request, response);
    }
}
