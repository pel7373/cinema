package com.cinema.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// use this filter to only purpose - change and set lanuage
@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	request.setCharacterEncoding("UTF-8");
        
    	HttpServletRequest req = (HttpServletRequest) request;
        
        //check variable language - in request and session! 
        //if user change language => req.getParameter("language") != null => set language in session!
        //if language in request is null => user didn't change language => if language in session is null => 
        //language in request and session set as in browser, otherwise language in request set as in session!
        // As a result - language in session and in request is equal!
        
        if (req.getParameter("language") != null) { 
        	req.getSession().setAttribute("language", req.getParameter("language"));
        } else {
        	if (req.getSession().getAttribute("language") == null) {
        		req.getSession().setAttribute("language", req.getLocale().getLanguage());
        		req.setAttribute("language", req.getLocale().getLanguage());
        	} else {
        		req.setAttribute("language", req.getSession().getAttribute("language")); 
        	}
        }

        // set variable sessionLocale as language in session (and in request)
        req.setAttribute("sessionLocale", req.getSession().getAttribute("language"));

        chain.doFilter(request, response);
    }

    public void destroy() {}

    public void init(FilterConfig arg0) throws ServletException {}

}