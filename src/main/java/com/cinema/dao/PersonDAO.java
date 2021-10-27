package com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cinema.entity.Person;

/**
 * @author pel7373
 *
 */
public class PersonDAO {

	private static final Logger logger = LogManager.getLogger();
	
	private static final String INSERT_PERSON = "INSERT INTO PERSON VALUES (DEFAULT, ?, ?, ?, ?);";
	private static final String SELECT_USER_BY_ID =    "SELECT ID, EMAIL, PASSWORD, NAME, ROLE_ID FROM PERSON WHERE ID = ?;";
	private static final String SELECT_USER_BY_EMAIL = "SELECT ID, EMAIL, PASSWORD, NAME, ROLE_ID FROM PERSON WHERE EMAIL = ?;";
	private static final String SELECT_ALL_PERSONS = "SELECT * FROM PERSON;";
	private static final String DELETE_PERSON = "DELETE FROM PERSON WHERE ID = ?;";
	private static final String UPDATE_PERSON = "UPDATE PERSON SET EMAIL = ?, PASSWORD = ?, NAME = ?, ROLE_ID = ? WHERE ID = ?;";
	
	private static PersonDAO personDAO;
	static Connection connection = null;
	Statement stmt;
	static ReentrantLock locker = new ReentrantLock();

	public PersonDAO() {
	}

    private static Connection getConnection() 
            throws SQLException, 
                ClassNotFoundException 
    {
        Connection con = ConnectionFactory.
                getInstance().getConnection();
        return con;
    }
    
	public static PersonDAO getInstance() {
		
		if (personDAO == null) {
			personDAO = new PersonDAO(); 
		}
		return personDAO;
	}
	

	public static Boolean insertPerson(Person person) {
		PreparedStatement ps = null;
		ResultSet id = null;
		Boolean isResult = false;
		try {
	        connection = getConnection();
			locker.lock();

	        ps = connection.prepareStatement(INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
	        //INSERT INTO PERSON VALUES (DEFAULT, ?, ?, ?, ?);
	        ps.setString(1, person.getEmail());
	        ps.setString(2, person.getPassword());
	        ps.setString(3, person.getName());
	        ps.setInt(4, person.getRole());
	        ps.executeUpdate();
	        id = ps.getGeneratedKeys();
	        if (id.next()) { 
	          	  person.setId(id.getInt(1));
	          	isResult = true;
	        }
	      } catch (Exception e) {
	    	  isResult = false;
	    	  logger.error("Insert person: " + e.getMessage() + "; Error with person: " + person.getEmail());
	      } finally {
		    	ConnectionFactory.closeAllConnections(connection, id, ps);
		    	locker.unlock();
	      }
		return isResult;
	}
	
	public static Boolean updatePerson(Person person) {
		PreparedStatement ps = null;
		ResultSet id = null;
		Boolean isResult = false;
		try {
	        locker.lock();
	        connection = getConnection();
	        ps = connection.prepareStatement(UPDATE_PERSON, Statement.RETURN_GENERATED_KEYS);
	        //"UPDATE PERSON SET EMAIL = ?, PASSWORD = ?, NAME = ?, ROLE_ID = ? WHERE ID = ?;";
	        ps.setString(1, person.getEmail());
	        ps.setString(2, person.getPassword());
	        ps.setString(3, person.getName());
	        ps.setInt(4, person.getRole());
	        ps.setInt(5, person.getId());
	        ps.executeUpdate();
	        id = ps.getGeneratedKeys();
	        if (id.next()) { 
	          	isResult = true;
	        }
	      } catch (Exception e) {
	    	  isResult = false;
	    	  logger.error("Update person: " + e.getMessage() + "; Error with person: " + person.getEmail());
	      } finally {
	    	  	ConnectionFactory.closeAllConnections(connection, id, ps);
		    	locker.unlock();
	      }
		return isResult;
	}

	public static Person getPersonById(int id_person) {

		Person person = null;
		PreparedStatement ps = null;
		ResultSet id = null;
		try {
	        locker.lock();
	        connection = getConnection();
	        ps = connection.prepareStatement(SELECT_USER_BY_ID, Statement.RETURN_GENERATED_KEYS);  
	        
	    	//private static final String SELECT_USER_BY_ID = "SELECT ID, EMAIL, PASSWORD, NAME, ROLE_ID FROM PERSON WHERE ID = ?;";
	    	
	        ps.setInt(1, id_person);
	        id = ps.executeQuery();
	        
	        while (id.next()) { 
	          	person = new Person(id.getInt(1), id.getString(2), id.getString(3), id.getString(4), id.getInt(5));
	        }
	      } catch (Exception e) {
			  logger.error("Get person by id:" + e.getMessage() + "||| id: " + id_person);
	      } finally {
	    	  ConnectionFactory.closeAllConnections(connection, id, ps);
	    	  locker.unlock();
	      }
		return person;
	}

	
	public static Person getPersonByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet id = null;
		try {
	        locker.lock();
	        connection = getConnection();
	        ps = connection.prepareStatement(SELECT_USER_BY_EMAIL, Statement.RETURN_GENERATED_KEYS);
	        //private static final String SELECT_USER_BY_EMAIL = "SELECT ID, EMAIL, PASSWORD, NAME, ROLE_ID FROM PERSON WHERE EMAIL = ?;";
	    	
	        ps.setString(1, email);
	        id = ps.executeQuery();
	        
	        if (id.next()) { 
	          	Person person1 = new Person(id.getInt(1), id.getString(2), id.getString(3), id.getString(4), id.getInt(5));
	          	//person1.setId(id.getInt(1));
	        	return person1; 
	        }
	      } catch (Exception e) {
			  logger.error("Get person:" + e.getMessage() + "||| " + email);
	      } finally {
	    	  ConnectionFactory.closeAllConnections(connection, id, ps);
	    	  locker.unlock();
	      }
		return null;
	}

	public static List<Person> getAllPersons() {
		PreparedStatement ps = null;
		ResultSet id = null;
		List<Person> list = new ArrayList<>();
		try {
	        locker.lock();
	        connection = getConnection();
	        ps = connection.prepareStatement(SELECT_ALL_PERSONS);
	    	//private static final String SELECT_ALL_PERSONS = "SELECT * FROM PERSON;";

	        id = ps.executeQuery();
	        while (id.next()) { 
	          	list.add(new Person(id.getInt(1), id.getString(2), "", id.getString(4), id.getInt(5)));  
	        }
	      } catch (Exception e) {
			  logger.error("getAllPersons:" + e.getMessage());
	      } finally {
	    	  ConnectionFactory.closeAllConnections(connection, id, ps);
	    	  locker.unlock();
	      }
		return list;
	}

	public static Boolean deletePersonById(int id_person) {
		Person person = null;
		PreparedStatement ps = null;
		ResultSet id = null;
		Boolean isResult = false;
		try {
	        locker.lock();
	        connection = getConnection();
	        ps = connection.prepareStatement(DELETE_PERSON, Statement.RETURN_GENERATED_KEYS);  
	        
	    	//private static final String DELETE_PERSON = "DELETE FROM PERSON WHERE ID = ?;";

	        ps.setInt(1, id_person);
	        isResult = ps.executeUpdate() > 0;
	      } catch (Exception e) {
			  logger.error("Get person by id:" + e.getMessage() + "||| id: " + id_person);
	      } finally {
	    	  ConnectionFactory.closeAllConnections(connection, id, ps);
	    	  locker.unlock();
	      }
		return isResult;
	}
}