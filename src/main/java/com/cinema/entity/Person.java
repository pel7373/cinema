package com.cinema.entity;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String email = "";
	private String password = "";
	private String name = "";
	private int role = 2;
	
	public Person() {
		super();
	}

	public Person(String email, String password, String name, int role) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	public Person(int id, String email, String password, String name, int role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	public boolean isEmailCorrect(String email) {
		String regexString = "^((([0-9A-Za-z]{1}[-0-9A-z\\.]{0,30}[0-9A-Za-z]?)|([0-9А-Яа-я]{1}[-0-9А-я\\.]{0,30}[0-9А-Яа-я]?))@([-A-Za-z]{1,}\\.){1,}[-A-Za-z]{2,})$";
		
    	Pattern pattern = Pattern.compile(regexString);
    	Matcher matcher = pattern.matcher(email);
    	
    	if (matcher.find()) {
    		return true;
    	}
    	
    	return false;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = encryptPassword(password);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id = " + id + ", email = " + email + ",  encryptedPassword = " + password + ", name = " + name +  ", role = " + role + "]";
	}

	public String encryptPassword(String password) {
		return Integer.toString(password.hashCode());
	}
}
