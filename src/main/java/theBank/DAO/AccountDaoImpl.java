package theBank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import theBank.People.UType;
import theBank.accounts.AType;
import theBank.accounts.Account;

public class AccountDaoImpl implements AccountDao {

	private void createTableIfNotExist() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        
	        sb.append("CREATE TABLE if not exists address (\r\n"
	        		+ "id INTEGER auto_increment,\r\n"
	        		+ "street varchar(50)  not null check (street REGEXP '^[0-9]+\\s+\\w+'),\r\n"
	        		+ "state varchar(20) not null check (state REGEXP '^([New]|[South]|[North]|[West]|[Rhode])\\s*([A-Za-z]{2,}|[A-Za-z]{2,})*'),\r\n"
	        		+ "zipcode varchar(5) not null check (zipcode REGEXP '^[0-9]{5}'),\r\n"
	        		+ "city varchar(50) not null,\r\n"
	        		+ "PRIMARY KEY (id)\r\n);");
	        ResultSet rs = stmt.executeQuery(query);


	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	@Override
	public Account getAccount(int id) {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM account WHERE id=";
	        ResultSet rs = stmt.executeQuery(query + id);

	        if(rs.next())
	        {
	            return extractAccountFromResultSet(rs);
	        }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return null;
	}

	private Account extractAccountFromResultSet(ResultSet rs) throws SQLException {
		Account account = new Account();
	    
		account.setId( rs.getInt("id") );
		System.out.println(account.getId());
		account.setBalance( rs.getDouble("balance") );
		account.setOwners(null);
		account.setEnabled( rs.getBoolean("enabled") );
		account.setApproved( rs.getBoolean("approved") , UType.ADMIN);
		int tempInt = rs.getInt("aType");
		System.out.println(tempInt);
		account.setType(AType.values()[tempInt] );
		
	    return account;
	}
	
	@Override
	public Set<Account> getAllAccounts() {
		Connection connection = ConnectionSingle.getConn();
	    try 
	    {
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
	
	        Set<Account> accounts = new HashSet<Account>();
	
	        while(rs.next())
	        {
	            Account account = extractAccountFromResultSet(rs);
	            accounts.add(account);
	        }
	
	        return accounts;
	
	    } 
	    catch (SQLException ex) 
	    {
	        ex.printStackTrace();
	    }
	
	    return null;
	}


	@Override
	public Set<Account> getAccountsByUser(Integer UserID) throws Exception {
	    Connection connection = ConnectionSingle.getConn();
	    try 
	    {
	    	//TODO CHANGE IT TO DEAL WITH JOINT ACCOUNTS.
	    	String query = "SELECT * FROM account ac INNER JOIN userwithaccount ua ON ac.id = ua.AccountID WHERE ua.UserID=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, user);
	        ResultSet rs = ps.executeQuery();

	        if(rs.next())
	        {
	        	return extractAccountFromResultSet(rs);
	        }

	    } 
	    catch (SQLException ex) 
	    {
	        ex.printStackTrace();
	    }

	    return null;
	}

	@Override
	public boolean insertAccount(Account account) {
	    
	    Connection connection = ConnectionSingle.getConn();
	    try {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO account VALUES (NULL, ?, ?, ?, ?, ?)");

	        ps.setDouble(1, account.getBalance());
	        //TODO FIX THIS FOR JOINT ACCOUNTS!
	        ps.setInt(2, account.getOwners()[0]);
	        ps.setBoolean(3, account.getEnabled());
	        ps.setBoolean(4, account.getApproved());
	        ps.setString(5, account.getType().toString());
	        int i = ps.executeUpdate();
	        
	        if(i == 1) 
	        {
	        	return true;
	        }

	    } 
	    catch (SQLException ex) 
	    {
	        ex.printStackTrace();
	    }

	    return false;
	}

	@Override
	public boolean updateAccount(Account account) {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        
	        PreparedStatement ps = connection.
	        		prepareStatement("UPDATE account SET balance=?, owner=?, enabled=?, approved=?, aType=? WHERE id=?");
	        ps.setDouble(1, account.getBalance());
	        //TODO FIX THIS FOR JOINT ACCOUNTS!
	        ps.setInt(2, account.getOwners()[0]);
	        ps.setBoolean(3, account.getEnabled());
	        ps.setBoolean(4, account.getApproved());
	        ps.setString(5, account.getType().toString());
	        int i = ps.executeUpdate();

	      if(i == 1) {
	    return true;
	      }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return false;
	}

	@Override
	public boolean deleteAccount(int id) {
	    Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        int i = stmt.executeUpdate("DELETE FROM account WHERE id=" + id);

	      if(i == 1) {
	    return true;
	      }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return false;
	}

	@Override
	public Set<Account> getAllActiveAccounts() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Account> getAllNeedApprovalAccounts() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Account> getActiveAccountsByUser(Integer UserID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		AccountDaoImpl aDao = new AccountDaoImpl();
		Set<Account> accounts = aDao.getAllAccounts();
		accounts.forEach(thing -> System.out.println(thing));
		
	}

}
