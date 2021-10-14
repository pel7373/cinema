package com.cinema.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinema.dao.PersonDAO;
import com.cinema.entity.Person;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name="ControllerServlet", 
		loadOnStartup = 1,
		urlPatterns={ "/index",
					"/signin",
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
		session.removeAttribute("alert-message");

        System.out.println("Start of doGet# userPath: " + userPath + "; user: " + request.getParameter("currentUser"));

        if (userPath.equals("/signout")) {
        	session.removeAttribute("currentUser");
        	session.removeAttribute("admin");
        	session.removeAttribute("id");
        	userPath = "index";

			// if listOfUsers page is requested
        }  else if (userPath.equals("/list-users")) {
        	List<Person> listOfPersons = PersonDAO.getAllPersons();
        	session.setAttribute("listOfUsers", listOfPersons);
        	session.setAttribute("pageListOfUsers", "1");
        	userPath = "list-users";
        	
        } else if (userPath.equals("/category")) {
            // TODO: Implement category request
        	userPath = "category";

        // if cart page is requested
        } else if (userPath.equals("/viewCart")) {
            // TODO: Implement cart page request
        	
            userPath = "cart";
            
         // if cart page is requested
         } else if (userPath.equals("/index")) {
            userPath = "index";

         // if checkout page is requested
        } else if (userPath.equals("/checkout")) {
            // TODO: Implement checkout page request
        	
        	userPath = "checkout";

        // if user switches language
        } else if (userPath.equals("/chooseLanguage")) {
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
		session.removeAttribute("alert-message");
                
        System.out.println("Start of doPost# userPath: " + userPath + "; current user: " + request.getParameter("currentUser"));

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
			person.setName(name);
			person.setRole(role);
			
			System.out.println("encrypted password = " + person.encryptPassword(password));
			
			// if new user was added successfully - save info in session and go to index.jps 
	        if (PersonDAO.insertPerson(person)) {
	    		session.setAttribute("currentUser", name);
	    		userPath = "index";
	        } else {
	        	userPath = "signup";
	        }

	        // place in request scope
	        //request.setAttribute("language", language);

        // if signin page is requested
        } else if (userPath.equals("/signin")) {
            
			// (1) obtain an input info
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Person person = null;
			
			try {
				 person = PersonDAO.getPersonByEmail(email);
				 System.out.println(person);
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
	    			session.setAttribute("id", person.getId());
	    			System.out.println("Залогинился админ ==> " + email + "; name ==> " + person.getName());
		        } 
			} else {
				userPath = "signin";
				System.out.println("Не удалось залогиниться! Email: " + email);
			}

			// if addNewUser page is requested
        }  else if (userPath.equals("/addNewUser")) {
			
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
        	
			// if deleteUser page is requested
        }  else if (userPath.equals("/deleteUser")) {
            // TODO: Implement cart page request
            int id = Integer.parseInt(request.getParameter("id"));
            PersonDAO.deletePersonById(id);
            List<Person> listOfPersons = PersonDAO.getAllPersons();
        	request.setAttribute("listOfUsers", listOfPersons);
        	userPath = "list-users";

            // if editUser page is requested
        }  else if (userPath.equals("/goToEditUser")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Person existingPerson = PersonDAO.getPersonById(id);
            session.setAttribute("user", existingPerson);
            userPath = "user-form";
            
            System.out.println("Отправляем юзера на редактирование: id =>" + id);
            System.out.println(existingPerson);
        	
            // if updateUser page is requested
        }  else if (userPath.equals("/updateUser")) {
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
			
			
			
			if (PersonDAO.getPersonByEmail(email) == null || previousEmail.equals(email)) {
					//if person with this email doesn't exist - update! 
					// (2) process an input info
					// obtain from DB
					Person person = new Person(id, email, password, name, role);
					if (!password.equals("")) {
						person.setPassword(password);
					} 
					
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

        System.out.println("#End of doPost# userPath: " + userPath + "; currentUser: " + session.getAttribute("currentUser") + "; user: " + session.getAttribute("user"));

        // use RequestDispatcher to forward request internally
		//String url = request.getContextPath() + "/" + userPath;
        //response.sendRedirect(url);
		String url = userPath + ".jsp";
		request.getRequestDispatcher(url).forward(request, response);
    }
}
