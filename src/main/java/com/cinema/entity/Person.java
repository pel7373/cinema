package com.cinema.entity;

import java.io.Serializable;

public class Person implements Serializable {

	private int id;
	private String login = "";
	private String password = "";
	private String name = "";
	private int role = 2;
	
	public Person() {
		super();
	}

	public Person(String login, String password, String name, int role) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		//this.password = Integer.toString(password.hashCode());
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
		return "User [login=" + login + ", name=" + name + ", role=" + role + "]";
	}

}
