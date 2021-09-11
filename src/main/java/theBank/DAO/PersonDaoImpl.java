package theBank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import theBank.People.UType;
import theBank.People.Person;

/**
 * Person DAO implementation
 * @author w3
 */

public class PersonDaoImpl implements PersonDao {

	public PersonDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	//@Override
	public Person getPerson(int id) {
	    Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Person WHERE id=" + id);

	        if(rs.next())
	        {
	            return extractPersonFromResultSet(rs);
	        }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return null;
	}

	private Person extractPersonFromResultSet(ResultSet rs) throws SQLException {
	    Person person = new Person();
	    person.setId( rs.getInt("id") );
	    person.setFname( rs.getString("fname") );
	    person.setLname( rs.getString("lname") );
//	    Person.setPersonname( rs.getString("Personname") );
//	    Person.setPass( rs.getString("pass") );
	    person.setEmail( rs.getString("email") );
//	    Person.setType( UType.values()[rs.getInt("uType")] );
	    person.setWithdrawn( rs.getDouble("withdrawn") );
	    return person;
	}
	
	//@Override
	public Map<Integer, Person> getAllPeople(){
	    Connection connection = ConnectionSingle.getConn();
	    try 
	    {
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Person");
	
	        Map<Integer, Person> People = new HashMap<Integer, Person>();
	
	        while(rs.next())
	        {
	            Person person = extractPersonFromResultSet(rs);
	            People.put(person.getId(),person);
	        }
	
	        return People;
	
	    } 
	    catch (SQLException ex) 
	    {
	        ex.printStackTrace();
	    }
	
	    return null;
	}
	
	@Override
	public Map<Integer, Person> getAllActivePeople() throws Exception {
		Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<Integer, Person> getAllActivePeopleByType(UType type) throws Exception {
		Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean PersonExists(String username) throws Exception {
		Connection connection = ConnectionSingle.getConn();
		// TODO Auto-generated method stub
		return false;
	}

	//@Override
	public Person getPersonByPersonNameAndPassword(String Person, String pass) {
	    Connection connection = ConnectionSingle.getConn();
	    try 
	    {
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Person WHERE Person=? AND pass=?");
	        ps.setString(1, Person);
	        ps.setString(2, pass);
	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	        	return extractPersonFromResultSet(rs);
	        }

	    } 
	    catch (SQLException ex) 
	    {
	        ex.printStackTrace();
	    }

	    return null;
	}

	//@Override
	public boolean insertPerson(Person person) throws Exception{
	    
	    Connection connection = ConnectionSingle.getConn();
	    try {
	        PreparedStatement ps = connection.prepareStatement("insert into Person (fname, lname, email) VALUES (?, ?, ?)");

	        ps.setString(1, person.getFname());
	        ps.setString(2, person.getLname());
//	        ps.setString(3, person.getUsername());
//	        ps.setString(4, person.getPass());
	        ps.setString(3, person.getEmail());
//	        ps.setString(6, ""+person.getType());
	        ps.setDouble(7, person.getWithdrawn());
	        int i = ps.executeUpdate();
	        
	        if(i == 1) 
	        {
	        	return true;
	        }

	    } 
	    catch (SQLException ex) 
	    {
	        throw ex;
	    	//ex.printStackTrace();
	        
	    }

	    return false;
	}

	//@Override
	public boolean updatePerson(Person person) {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        
	        PreparedStatement ps = connection.
	        		prepareStatement("UPDATE Person SET fname=?, lname=?, email=?, withdrawn=? WHERE id=?");
	        ps.setString(1, person.getFname());
	        ps.setString(2, person.getLname());
//	        ps.setString(3, Person.getPersonname());
//	        ps.setString(4, Person.getPass());
	        ps.setString(3, person.getEmail());
//	        ps.setInt(6, Person.getType().getValue());
	        ps.setDouble(4, person.getWithdrawn());
	        ps.setInt(5, person.getId());
	        int i = ps.executeUpdate();

	      if(i == 1) {
	    return true;
	      }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return false;
	}

	//@Override
	public boolean deletePerson(int id) {
	    Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        System.out.println(id);
	        int i = stmt.executeUpdate("DELETE FROM Person WHERE id=" + id);

	      if(i == 1) {
	    return true;
	      }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return false;
	}




}
