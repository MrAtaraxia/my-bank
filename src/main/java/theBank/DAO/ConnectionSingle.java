package theBank.DAO;

import com.mysql.cj.jdbc.Driver;
import oracle.jdbc.driver.OracleDriver;

//import oracle.jdbc.datasource.impl.OracleDataSource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * Connect to Database
 * @author w3
 */

public class ConnectionSingle {

	private static Connection single_instance = null;
	
	public static String connectionType = "mysql"; //mysql / oracle
    
	
	private static final String M_URL = "jdbc:mysql://localhost:3306/mybank";
    private static final String M_USER = "jeffrey";
    private static final String M_PASS = "password";
    
    
    private static final String O_URL = "jdbc:oracle:thin:@java-react-batch-jdbc-demo.cvtq9j4axrge.us-east-1.rds.amazonaws.com:1521:ORCL"; //"jdbc:oracle:thin:@25.3.185.95:1521:XE";//"jdbc:oracle:thin:@localhost:1521:XE";
    private static final String O_USER = "admin";//"testUser";
    private static final String O_PASS = "12345678";//"MyBoardgamePassword101";//"testPass";

    /**
     * Get a connection to database
     * @return Connection object
     */

    
    public static Connection getConn() {
    	if(single_instance==null) {
    		single_instance = getConnection();
    	}
    	return single_instance;
    }
    
    
    private static Connection getConnection()
    {
    	try 
    	{
    		
    		if(connectionType.equalsIgnoreCase("mysql")) {
    			DriverManager.registerDriver(new Driver());
    			return DriverManager.getConnection(M_URL, M_USER, M_PASS);
    		}
    		
    		else if(connectionType.equalsIgnoreCase("oracle")) {
    			DriverManager.registerDriver(new OracleDriver());
    			Connection tempConn = DriverManager.getConnection(O_URL, O_USER, O_PASS);
    			tempConn.setAutoCommit(false);
    			return tempConn;
    		}
    		else {
    			System.out.print("NO CONNECTION!");
    			return null;
    		}
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
   

//	OracleDataSource ods = new OracleDataSource();
//    ods.setURL("jdbc:oracle:thin:@//localhost:1521/XEPDB1"); // jdbc:oracle:thin@//[hostname]:[port]/[DB service name]
//    ods.setUser("system"); // [username]
//    ods.setPassword("GetStartedWithXE"); // [password]
//    Connection conn = ods.getConnection();
//     
//    PreparedStatement stmt = conn.prepareStatement("SELECT 'Hello World!' FROM dual");
//    ResultSet rslt = stmt.executeQuery();
//    while (rslt.next()) {
//      System.out.println(rslt.getString(1));
//    }
//    

    public static void main(String[] args) throws SQLException {
        
		Connection conn=null;
		try {
			conn = ConnectionSingle.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		if(connectionType.equalsIgnoreCase("mysql")) {
			System.out.println("MYSQL");
			rs = stmt.executeQuery("select * from person");
		}
		else {
			System.out.println("ORACLE!");
			rs = stmt.executeQuery("SELECT DISTINCT OBJECT_NAME \r\n"
					+ "  FROM USER_OBJECTS\r\n"
					+ " WHERE OBJECT_TYPE = 'TABLE'\r\n"
					+ "");
		    System.out.println("Tables in the current database: ");
		      
			//rs = stmt.executeQuery("select * from mybankusers");
		}
		while(rs.next()) {
			String tablename = rs.getString(1);
			try {
			stmt.execute("drop table "+tablename + " CASCADE CONSTRAINTS PURGE");
			}catch(Exception e) {
			}finally {
				rs = stmt.executeQuery("SELECT DISTINCT OBJECT_NAME \r\n"
						+ "  FROM USER_OBJECTS\r\n"
						+ " WHERE OBJECT_TYPE = 'TABLE'\r\n"
						+ "");
			}
			System.out.print(tablename);
			System.out.println();
		}
//			while(rs.next())
//				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		ConnectionSingle.closeConnection(conn);

		System.out.println("Done!");
		
    }

}