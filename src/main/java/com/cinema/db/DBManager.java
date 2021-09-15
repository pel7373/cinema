package com.cinema.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import com.cinema.entity.Movie;
import com.cinema.entity.Person;

public class DBManager {

	//private static final String URL_CONNECTION = getProperties(); 
	private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/mydb?user=root&password=root";
	
	private static DBManager dbManager;
	private static final Logger logger =  Logger.getLogger(DBManager.class.getName());

	static ReentrantLock locker = new ReentrantLock();

	private DBManager() {
		super();
	}

	public static DBManager getInstance() {
		
		if (dbManager == null) {
			dbManager = new DBManager(); 
		}
		return dbManager;
	}
	
	public static String getProperties() {
		Properties properties = new Properties();
		System.out.println("Пробуем получить connection.url");
		
		//System.out.println(getClass().getResourceAsStream("/resources/app.properties"));
		try (
				InputStream input = new FileInputStream("app.properties")
				//InputStream input = getClass().getResourceAsStream("app.properties");
				) {
			properties.load(input);
			System.out.println(properties.getProperty("connection.url"));
			return properties.getProperty("connection.url");
		} catch (IOException e) {
			logger.severe("Can't read app.properties: " + e.getMessage());
		}
		return null;
	}

	public static Connection getConnection(String connectionUrl) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return DriverManager.getConnection(connectionUrl);
	}

	public static Boolean insertPerson(Person person) {
		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		try {
	        locker.lock();
	        System.out.println(URL_CONNECTION);
	        connection = getConnection(URL_CONNECTION);
	        ps = connection.prepareStatement("INSERT INTO person VALUES (DEFAULT , ?, ?, ?, ?);",          
	        		Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, person.getEmail());
	        ps.setString(2, person.getPassword());
	        ps.setString(3, person.getName());
	        ps.setInt(4, person.getRole());
	        ps.executeUpdate();
	        id = ps.getGeneratedKeys();
	        if (id.next()) { 
	          	  person.setId(id.getInt(1)); 
	        }
	      } catch (Exception e) {
			  logger.severe("Insert person: " + e.getMessage() + "; Error with person: " + person.getEmail());
	      } finally {
		    	closeAllConnections(connection, id, ps);
		    	locker.unlock();
	      }
		return true;
	}

	public static Person getPerson(String email) {

		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		try {
	        locker.lock();
	        connection = getConnection(URL_CONNECTION);
	        ps = connection.prepareStatement("SELECT * FROM person where email = ?;",
	        		Statement.RETURN_GENERATED_KEYS);          
	        ps.setString(1, email);
	        id = ps.executeQuery();
	        
	        if (id.next()) { 
	          	Person person1 = new Person(id.getString(2), id.getString(3), id.getString(4), id.getInt(1));
	          	//person1.setId(id.getInt(1));
	        	return person1; 
	        }
	      } catch (Exception e) {
			  logger.severe("Get person:" + e.getMessage() + "||| " + email);
	      } finally {
	    	  closeAllConnections(connection, id, ps);
	    	  locker.unlock();
	      }
		return null;
	}

	public List<Person> findAllPersons() {
		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		List<Person> list = new ArrayList<>();
		try {
	        locker.lock();
	        connection = getConnection(URL_CONNECTION);
	        ps = connection.prepareStatement("SELECT LOGIN FROM persons;");
	        id = ps.executeQuery();
	        while (id.next()) { 
	          	list.add(getPerson(id.getString(1)));  
	        }
	        return list;
	      } catch (Exception e) {
			  logger.severe("findAllPersons:" + e.getMessage());
	      } finally {
		    	closeAllConnections(connection, id, ps);
		    	locker.unlock();
	      }
		return list;
	}

	public Boolean insertMovie(Movie movie) {
		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		try {
	        locker.lock();
	        connection = getConnection(URL_CONNECTION);
	        ps = connection.prepareStatement("INSERT INTO teams VALUES (DEFAULT , ?, ?);",          
	        Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, movie.getTitle());
	        ps.setString(2, movie.getDescription());
	        ps.executeUpdate();
	        id = ps.getGeneratedKeys();
	        if (id.next()) { 
	          	  movie.setId(id.getInt(1)); 
	        }
	      } catch (Exception e) {
			  logger.severe("Insert movie:" + e.getMessage() + "; Error with movie : " + movie);
	      } finally {
	    	  closeAllConnections(connection, id, ps);
	    	  locker.unlock();
	      }
		return true;
	}

	public List<Movie> findAllMovies() {
		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		List<Movie> list = new ArrayList<>();
		try {
			
	        locker.lock();
	        connection = getConnection(URL_CONNECTION);
	        ps = connection.prepareStatement("SELECT NAME FROM teams;",
	        		Statement.RETURN_GENERATED_KEYS);
	        id = ps.executeQuery();
	        while (id.next()) {
	          	list.add(getMovie(id.getString(1)));  
	        }
	        return list;
	      } catch (Exception e) {
			  logger.severe("findAllTeams:" + e.getMessage());
	      } finally {
	    	  closeAllConnections(connection, id, ps);
	    	  locker.unlock();
	      }
		return list;
	}

	public Movie getMovie(String string) {
		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		try {
	        locker.lock();
	        connection = getConnection(URL_CONNECTION);
	        ps = connection.prepareStatement("SELECT * FROM teams where name = ?;", 
	        		Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, string);
	        id = ps.executeQuery();
	        
	        if (id.next()) { 
	          	Movie movie1 = new Movie(string);
	          	movie1.setId(id.getInt(1));
	        	return movie1; 
	        }
	      } catch (Exception e) {
			  logger.severe("Get person:" + e.getMessage() + "; person: " + string);
	      } finally {
		    	closeAllConnections(connection, id, ps);
		    	locker.unlock();
	      }
		return null;
	}


	public Boolean setTeamsForPerson(Person person, Movie... movies) {
		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		try {
	        locker.lock();
	        connection = getConnection(URL_CONNECTION);
			connection.setAutoCommit(false);
			
			ps = connection.prepareStatement("INSERT INTO USERS_TEAMS VALUES (?, ?);",          
					Statement.RETURN_GENERATED_KEYS);
/*			for (Team teamx : teams) {
		    	ps.setInt(1, person.getId());
		    	ps.setInt(2, teamx.getId());
		    	ps.executeUpdate();
		    	id = ps.getGeneratedKeys();
				}
*/				connection.commit();
	      } catch (Exception e) {
	          try {
	        	  connection.rollback();
	          } catch (SQLException e1) {
	        	  logger.severe(e.getMessage());
	          }
	          logger.severe("SetTeamsForPerson:" + e.getMessage() + "&&& " + person);
	      } finally {
	    	  closeAllConnections(connection, id, ps);
	    	  locker.unlock();
	      }
		return true;
	}

	public List<Movie> getPersonTeams(Person person) {
		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		List<Movie> list = new ArrayList<>();
	
		try {
			String personName = person.getEmail();
			//int personId = getPerson(person).getId();
			locker.lock();
	        connection = getConnection(URL_CONNECTION);

	        ps = connection.prepareStatement("select teams.name from teams join persons_teams " +
	        		"on teams.id = persons_teams.team_id and persons_teams.person_id = ?;",
	        		Statement.RETURN_GENERATED_KEYS);
	        //ps.setInt(1, personId);
	        id = ps.executeQuery();
	        
	        while (id.next()) { 
	        	list.add(getMovie(id.getString(1)));  
	        }
	        return list;
	      } catch (Exception e) {
			  logger.severe("getPersonTeams:" + e.getMessage());
	      } finally {
	    	  closeAllConnections(connection, id, ps);
	    	  locker.unlock();
	      }

		return list;
	}

	public Boolean deleteMovie(Movie movie) {
		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		try {
	        locker.lock();
	        connection = getConnection(URL_CONNECTION);
	        ps = connection.prepareStatement("delete from teams where teams.id = ?;",
	        		Statement.RETURN_GENERATED_KEYS);          
	        ps.setInt(1, movie.getId());
	        ps.executeUpdate();
		    id = ps.getGeneratedKeys();
	      } catch (Exception e) {
			  logger.severe("DeleteMovie:" + e.getMessage() + "; movie: " + movie);
	      } finally {
		    	closeAllConnections(connection, id, ps);
		    	locker.unlock();
	      }
		return true;
	}

	public Boolean updateMovie(Movie movie) {
		PreparedStatement ps = null;
		ResultSet id = null;
		Connection connection = null;
		try {
	        locker.lock();
	        connection = getConnection(URL_CONNECTION);
	        ps = connection.prepareStatement("update teams set name = ? where id = ?;",
	        		Statement.RETURN_GENERATED_KEYS);          
	        ps.setString(1, movie.getTitle());
	        ps.setInt(2, movie.getId());
	        ps.executeUpdate();
	        id = ps.getGeneratedKeys();
	      } catch (Exception e) {
			  logger.severe("UpdateMovie:" + e.getMessage() + "; movie: " + movie);
	      } finally {
		    	closeAllConnections(connection, id, ps);
		    	locker.unlock();
	      }
		return true;
	}

	public static void closeAllConnections(Connection connection, ResultSet id, PreparedStatement ps) {
		if (connection != null && id != null && ps != null) {
			try {
				id.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				logger.severe("Can't close connection: " + e.getMessage());
			}
		}
	}
}