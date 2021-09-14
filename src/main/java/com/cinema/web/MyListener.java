package com.cinema.web;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("MyListener#contextInitialized");
    	
    	ServletContext context = sce.getServletContext();
    	String localesAsString = context.getInitParameter("locales");
    	
    	List<String> locales =
			Arrays.asList(localesAsString.split(" "));
    	
    	context.setAttribute("locales", locales);
    	
    	System.out.println("locales ==> " + locales);
    	
    }

}
