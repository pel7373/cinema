package com.cinema.entity;

import java.io.Serializable;

/**
 * @author pel7373
 *
 */
public class Movie implements Serializable {
	
	private static final long serialVersionUID = 3187651765035758646L;
	private int id;
	private String title = "";
	private String genre = "";
	private String description = "";

	public Movie(String title) {
		this.title = title;
	}
	
	public Movie(String title, String genre, String description) {
		this(title);
		this.genre= genre;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", genre=" + genre + ", description=" + description + "]";
	}

}
