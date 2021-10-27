package com.cinema.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Basic abstract class for implementing command classes
 * as part of Front Controller J2EE pattern
 */
public interface Command {
	   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
