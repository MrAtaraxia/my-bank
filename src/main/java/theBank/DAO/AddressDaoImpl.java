package theBank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import theBank.general.Address;
import theBank.general.State;

public class AddressDaoImpl implements AddressDAO {

//	private void createTableIfNotExist() {
//		StringBuilder sb = new StringBuilder();
//		Connection connection = ConnectionSingle.getConn();
//	    try {
//	        Statement stmt = connection.createStatement();
//	        
//	        sb.append("CREATE TABLE if not exists address (\r\n"
//	        		+ "id INTEGER auto_increment,\r\n"
//	        		+ "street varchar(50)  not null ,\r\n"
//	        		+ "state varchar(20) not null ,\r\n"
//	        		+ "zipcode varchar(5) not null check (zipcode REGEXP '^[0-9]{5}'),\r\n"
//	        		+ "city varchar(50) not null,\r\n"
//	        		+ "PRIMARY KEY (id));");
//	        String query = sb.toString();
//	        if(stmt.execute(query)) {
//	        	
//	        }
//	    } catch (SQLException ex) {
//	        ex.printStackTrace();
//	    }
//	}
	
	@Override
	public Address getAddress(int id) throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM address WHERE id=";
	        ResultSet rs = stmt.executeQuery(query + id);
	        if(rs.next()) {
	            return extractAddressFromResultSet(rs);
	        }
	    } catch (SQLException ex) { System.out.println(ex.getErrorCode()); ex.printStackTrace(); }
	    return null;
	}

	private Address extractAddressFromResultSet(ResultSet rs) throws SQLException {
		Address addr = new Address();
		addr.setId(rs.getInt("id") );
		addr.setActive(rs.getBoolean("active") );
		addr.setStreet(rs.getString("street") );
		addr.setState(State.get(rs.getString("state")));
		addr.setZipcode(rs.getString("zipcode"));
		addr.setCity(rs.getString("city") );
	    return addr;
	}
	
	@Override
	public Map<Integer, Address> getAllAddresses() throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM address";
	        ResultSet rs = stmt.executeQuery(query);
	        Map<Integer, Address> addrs = new HashMap<Integer, Address>();
	        while(rs.next()) {
	            Address aAddr = extractAddressFromResultSet(rs);
	            addrs.put(aAddr.getId(), aAddr);
	        }
	        return addrs;
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}
	
	@Override
	public Map<Integer, Address> getAllActiveAddresses() throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM address WHERE active = true";
	        
	        ResultSet rs = stmt.executeQuery(query);
	        Map<Integer, Address> addrs = new HashMap<Integer, Address>();
	        while(rs.next()) {
	            Address aAddr = extractAddressFromResultSet(rs);
	            addrs.put(aAddr.getId(), aAddr);
	        }
	        return addrs;
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}

	@Override
	public Map<Integer, Address> getAllAddressesByState(State state) throws Exception {
	    Connection connection = ConnectionSingle.getConn();
	    try {
	    	String query = "SELECT * FROM address WHERE active = true AND state=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, state.toString());
	        ResultSet rs = ps.executeQuery();
	        Map<Integer, Address> addrs = new HashMap<Integer, Address>();
	        if(rs.next()) {
	        	Address aAddr = extractAddressFromResultSet(rs);
	        	addrs.put(aAddr.getId(), aAddr);
	        }
	        return addrs;
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}

	@Override
	public Address getAddressByPerson(int personID) throws Exception {
	    Connection connection = ConnectionSingle.getConn();
	    try {
	    	String query = "SELECT * FROM address a left join person p ON a.id=p.addressID WHERE a.active=true AND p.id=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, personID);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next()) { return extractAddressFromResultSet(rs); }
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}

	@Override
	public boolean insertAddress(Address addr) throws Exception {
	    Map<Integer, Address> allAddr = getAllAddresses();
	    for(Address add:allAddr.values()) {
	    	if(add.getCity().equals(addr.getCity())) 
	    		if(add.getState().equals(addr.getState()))
	    			if(add.getZipcode().equals(addr.getZipcode()))
	    				if(add.getStreet().equals(addr.getStreet()))
	    					{ System.out.println("Duplicate"); return false; }
	    }
	    Connection connection = ConnectionSingle.getConn();
	    try {
	    	String query = "INSERT INTO address (street, state, zipcode, city) VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, addr.getStreet());
	        ps.setString(2, addr.getState().toString());
	        ps.setString(3, addr.getZipcode());
	        ps.setString(4, addr.getCity());
	        int i = ps.executeUpdate();
	        if(i == 1) { return true; }
	    } catch (SQLException ex) { System.out.println(ex); }
	    return false;
	}

	@Override
	public boolean updateAddress(Address addr) throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	    	String query = "UPDATE address SET active=?, street=?, state=?, zipcode=?, city=? WHERE id=?";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setBoolean(1, addr.getActive());
			ps.setString(2, addr.getStreet());
			ps.setString(3, addr.getState().toString());
			ps.setString(4, addr.getZipcode());
			ps.setString(5, addr.getCity());
			ps.setInt(6, addr.getId());
	        int i = ps.executeUpdate();
	      if(i == 1) { return true; }
	    } catch (SQLException ex) { System.out.println(ex); }
	    return false;
	}

	@Override
	public boolean activateAddress(int id) throws Exception {
		Address anAdd = getAddress(id);
		if(anAdd!=null) {
			anAdd.setActive(true);
			if(updateAddress(anAdd)) { return true; }
		}
		return false;
	}

	@Override
	public boolean deactivateAddress(int id) throws Exception {
		Address anAdd = getAddress(id);
		if(anAdd!=null) {
			anAdd.setActive(false);
			if(updateAddress(anAdd)) { return true; }
		}
		return false;
	}

	@Override
	public boolean deleteAddress(int id) throws Exception {
	    Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        int i = stmt.executeUpdate("DELETE FROM address WHERE id=" + id);
	        if(i == 1) { return true; }
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return false;
	}
	
	public static void main(String[] args) throws Exception {
		AddressDaoImpl addDao = new AddressDaoImpl();
		//addDao.createTableIfNotExist();
		Address newAdd = new Address();
		newAdd.setCity("A City");
		newAdd.setState(State.NJ);
		newAdd.setZipcode("12345");
		newAdd.setStreet("12 First Street");
		addDao.insertAddress(newAdd);
		Map<Integer, Address> myMap = addDao.getAllAddresses();
		for(Address a:myMap.values()) {
			System.out.println(a.getId()+"-"+a.getCity()+" "+a.getStreet() + " " + a.getState());
		}
		
	}


}
