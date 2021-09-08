package theBank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import theBank.users.UType;
import theBank.users.User;

/**
 * User DAO implementation
 * @author w3
 */

public class UserDaoImpl implements UserDao {

	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	//@Override
	public User getUser(int id) {
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id=" + id);

	        if(rs.next())
	        {
	            return extractUserFromResultSet(rs);
	        }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return null;
	}

	private User extractUserFromResultSet(ResultSet rs) throws SQLException {
	    User user = new User();
	    user.setId( rs.getInt("id") );
	    user.setFname( rs.getString("fname") );
	    user.setLname( rs.getString("lname") );
//	    user.setUsername( rs.getString("username") );
//	    user.setPass( rs.getString("pass") );
	    user.setEmail( rs.getString("email") );
//	    user.setType( UserType.values()[rs.getInt("uType")] );
	    user.setWithdrawn( rs.getDouble("withdrawn") );
	    return user;
	}
	
	//@Override
	public Set<User> getAllUsers(){
	    Connection connection = ConnectionFactory.getConnection();
	    try 
	    {
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
	
	        Set<User> users = new HashSet<User>();
	
	        while(rs.next())
	        {
	            User user = extractUserFromResultSet(rs);
	            users.add(user);
	        }
	
	        return users;
	
	    } 
	    catch (SQLException ex) 
	    {
	        ex.printStackTrace();
	    }
	
	    return null;
	}

	//@Override
	public User getUserByUserNameAndPassword(String user, String pass) {
	    Connection connection = ConnectionFactory.getConnection();
	    try 
	    {
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE user=? AND pass=?");
	        ps.setString(1, user);
	        ps.setString(2, pass);
	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	        	return extractUserFromResultSet(rs);
	        }

	    } 
	    catch (SQLException ex) 
	    {
	        ex.printStackTrace();
	    }

	    return null;
	}

	//@Override
	public boolean insertUser(User user) throws Exception{
	    
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("insert into user (fname, lname, email) VALUES (?, ?, ?)");

	        ps.setString(1, user.getFname());
	        ps.setString(2, user.getLname());
//	        ps.setString(3, user.getUsername());
//	        ps.setString(4, user.getPass());
	        ps.setString(3, user.getEmail());
//	        ps.setInt(6, user.getType().getValue());
	        //ps.setDouble(7, user.getWithdrawn());
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
	public boolean updateUser(User user) {
		Connection connection = ConnectionFactory.getConnection();
	    try {
	        
	        PreparedStatement ps = connection.
	        		prepareStatement("UPDATE user SET fname=?, lname=?, email=?, withdrawn=? WHERE id=?");
	        ps.setString(1, user.getFname());
	        ps.setString(2, user.getLname());
//	        ps.setString(3, user.getUsername());
//	        ps.setString(4, user.getPass());
	        ps.setString(3, user.getEmail());
//	        ps.setInt(6, user.getType().getValue());
	        ps.setDouble(4, user.getWithdrawn());
	        ps.setInt(5, user.getId());
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
	public boolean deleteUser(int id) {
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        Statement stmt = connection.createStatement();
	        System.out.println(id);
	        int i = stmt.executeUpdate("DELETE FROM user WHERE id=" + id);

	      if(i == 1) {
	    return true;
	      }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return false;
	}

	@Override
	public boolean userExists(String username) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<User> getAllUsersByType(UType type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
