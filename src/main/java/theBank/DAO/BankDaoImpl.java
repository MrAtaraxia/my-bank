package theBank.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import theBank.general.Address;
import theBank.general.Bank;
import theBank.general.State;

public class BankDaoImpl extends BaseDAOImpl implements BankDAO {

//	private void createTableIfNotExist() {
//	StringBuilder sb = new StringBuilder();
//	Connection connection = ConnectionSingle.getConn();
//    try {
//        Statement stmt = connection.createStatement();
//        
//        sb.append("CREATE TABLE if not exists bank (\r\n"
//        		+ "id INTEGER auto_increment,\r\n"
//        		+ "PRIMARY KEY (id));");
//        String query = sb.toString();
//        if(stmt.execute(query)) {
//        	
//        }
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//    }
//}
	
	@Override
	public Bank getBank(int id) throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM bank WHERE id=";
	        ResultSet rs = stmt.executeQuery(query + id);
	        if(rs.next()) {
	            return extractBankFromResultSet(rs);
	        }
	    } catch (SQLException ex) { System.out.println(ex.getErrorCode()); ex.printStackTrace(); }
	    return null;
	}

	@Override
	public Bank getBankByRouting(String routingNumber) throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM bank WHERE routing=";
	        ResultSet rs = stmt.executeQuery(query + routingNumber);
	        if(rs.next()) {
	            return extractBankFromResultSet(rs);
	        }
	    } catch (SQLException ex) { System.out.println(ex.getErrorCode()); ex.printStackTrace(); }
	    return null;
	}
	
	private Bank extractBankFromResultSet(ResultSet rs) throws Exception {
		Bank alocal = new Bank();
		alocal.setId(rs.getInt("id"));
		alocal.setActive(rs.getBoolean("active"));
		alocal.setName(rs.getString("name"));
		alocal.setRouting(rs.getString("routing"));
		alocal.setTotalFunds(rs.getDouble("total_funds"));
		alocal.setAddressID(rs.getInt("addressID"));
	    return alocal;
	}

	@Override
	public Map<Integer, Bank> getAllBanks() throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM bank";
	        ResultSet rs = stmt.executeQuery(query);
	        Map<Integer, Bank> aMap = new HashMap<Integer, Bank>();
	        while(rs.next()) {
	        	Bank alocal = new Bank();
	        	alocal = (Bank) extractItFromResultSet(rs,alocal, Bank.lookup);
	            //Bank alocal = extractBankFromResultSet(rs);
	            aMap.put(alocal.getId(), alocal);
	        }
	        return aMap;
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}

	@Override
	public Map<Integer, Bank> getAllActiveBanks() throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM bank WHERE active = true";
	        ResultSet rs = stmt.executeQuery(query);
	        Map<Integer, Bank> aMap = new HashMap<Integer, Bank>();
	        while(rs.next()) {
	            Bank alocal = extractBankFromResultSet(rs);
	            aMap.put(alocal.getId(), alocal);
	        }
	        return aMap;
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}

	@Override
	public Map<Integer, Bank> getAllBanksByState(State state) throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        String query = "SELECT * FROM bank WHERE active=true AND state=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, state.toString());
	        ResultSet rs = ps.executeQuery();
	        Map<Integer, Bank> aMap = new HashMap<Integer, Bank>();
	        while(rs.next()) {
	            Bank alocal = extractBankFromResultSet(rs);
	            aMap.put(alocal.getId(), alocal);
	        }
	        return aMap;
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}

	@Override
	public boolean insertBank(Bank input) throws Exception {
	    Connection connection = ConnectionSingle.getConn();
	    Map<Integer, Bank> aMap = getAllBanks();
	    for(Bank entity:aMap.values()) {
	    	if(entity.getAddressID().equals(input.getAddressID()))
	    		if(entity.getName().equals(input.getName()))
	    			if(entity.getRouting().equals(input.getRouting()))
	    				{ System.out.println("Duplicate"); return false; }
	    }
	    try {
	    	String query = "INSERT INTO bank "
	    			+ "(name, routing, total_funds, addressID) "
	    			+ "VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, input.getName());
	        ps.setString(2, input.getRouting());
	        ps.setDouble(3, input.getTotalFunds());
	        ps.setInt(4, input.getAddressID());
	        int i = ps.executeUpdate();
	        if(i == 1) { return true; }
	    } catch (SQLException ex) { System.out.println(ex); }
	    return false;
	}

	@Override
	public boolean updateBank(Bank input) throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	    	String query = "UPDATE bank SET "
	    			+ "active=?, name=?, routing=?, "
	    			+ "total_funds=?, addressID=? WHERE id=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setBoolean(1, input.getActive());
	        ps.setString(2, input.getName());
	        ps.setString(3, input.getRouting());
	        ps.setDouble(4, input.getTotalFunds());
	        ps.setInt(5, input.getAddressID());
			ps.setInt(6, input.getId());
	        int i = ps.executeUpdate();
	      if(i == 1) { return true; }
	    } catch (SQLException ex) { System.out.println(ex); }
	    return false;
	}

	@Override
	public boolean activateBank(int id) throws Exception {
		Bank anItem = getBank(id);
		if(anItem!=null) {
			anItem.setActive(true);
			if(updateBank(anItem)) { return true; }
		}
		return false;
	}

	@Override
	public boolean deactivateBank(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBank(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BankDaoImpl bDao = new BankDaoImpl();
		try {
		Bank myBank = new Bank();
//		Class aclass = Bank.class;
//		Method[] methos = aclass.getMethods();
//		Field[] theFields = aclass.getFields();
//		for(Field aField:theFields) {
//			System.out.println(aField.getName() +" " + aField.getType());
//		}
		//bDao.getIt("bank",5);
		
		myBank.setName("BestBank");
		myBank.setAddressID(1);
		myBank.setRouting("234232999");
		myBank.setTotalFunds(0.0d);
		System.out.println("HERE?");
		myBank.setId(5);
		System.out.println("HERE?");
		Map<Integer, Bank> theBanks = bDao.getAllBanks();
		for(var eBank:theBanks.entrySet()) {
			System.out.println(eBank.getValue().getName() + " " + eBank.getValue().getId());
		}
		//bDao.insertBank(myBank);
		//bDao.activateIt(1,myBank, Bank.lookup);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
