package theBank.DAO;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connect to Database
 * @author w3
 */

public class ConnectionFactory {

    public static final String URL = "jdbc:mysql://localhost:3306/mybank";
    public static final String USER = "jeffrey";
    public static final String PASS = "password";

    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection()
    {
    	try 
    	{
    		DriverManager.registerDriver(new Driver());
    		return DriverManager.getConnection(URL, USER, PASS);
    	} 
    	catch (SQLException ex) 
    	{
    		throw new RuntimeException("Error connecting to the database", ex);
		}
    }

    public static void closeConnection(Connection conn)
    {
    	try 
    	{
			conn.close();
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    
//    public static void main(String[] args) {
//        
//		try {
//			Connection conn = ConnectionFactory.getConnection();
//			Statement stmt = conn.createStatement();
//			ResultSet rs=stmt.executeQuery("select * from user");
//			while(rs.next())  
//			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//			ConnectionFactory.closeConnection(conn);
//		}
//		catch (SQLException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("Done!");
//		
//    }

}