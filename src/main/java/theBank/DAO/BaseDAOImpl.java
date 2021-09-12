package theBank.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;

import theBank.People.*;
import theBank.general.*;

public abstract class BaseDAOImpl {
	
	
	
	public Class getIt(String toGet, int id) throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        Class yourClass = Bank.class;
	        for (Field field : yourClass.getFields()) {
	        	System.out.println(field.getName() +" " + field.getType());
	        }
	        for (Method method : yourClass.getMethods()){
	        	if(method.getName().startsWith("get")) {
	        		System.out.println(method.getName()+" "+method.getReturnType());
	        	}
	            //method.invoke(obj, args);
	        }
//	        String query = "SELECT * FROM bank WHERE id=";
//	        ResultSet rs = stmt.executeQuery(query + id);
//	        if(rs.next()) {
//	            return extractBankFromResultSet(rs);
//	        }
	    } catch (SQLException ex) { System.out.println(ex.getErrorCode()); ex.printStackTrace(); }
	    return null;
	}
	
	public Object extractItFromResultSet(ResultSet rs, Object o, Map<String, Extractor> aLookup) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(Entry<String, Extractor> curr:aLookup.entrySet()) {
			Object toGet = null;
			if(curr.getValue().sType.equalsIgnoreCase("Integer") || curr.getValue().sType.equalsIgnoreCase("int")) {
				toGet = rs.getInt(curr.getKey());
			} else if(curr.getValue().sType.equalsIgnoreCase("Boolean") || curr.getValue().sType.equalsIgnoreCase("bool")) {
				toGet = rs.getBoolean(curr.getKey());
			} else if(curr.getValue().sType.equalsIgnoreCase("String")) {
				toGet = rs.getString(curr.getKey());
			}else if(curr.getValue().sType.equalsIgnoreCase("State")) {
				toGet = State.get(rs.getString(curr.getKey()));
			}
			System.out.println("toGET:" + toGet);
			curr.getValue().sMethod.invoke(o, toGet);
		}
//		((Person) o).setId(rs.getInt("id") );
//		addr.setActive(rs.getBoolean("active") );
//		addr.setStreet(rs.getString("street") );
//		addr.setState(State.get(rs.getString("state")));
//		addr.setZipcode(rs.getString("zipcode"));
//		addr.setCity(rs.getString("city") );
	    return o;
	}
	
	public boolean activateIt(int id, Object o, Map<String, Extractor> aLookup) throws Exception {

		System.out.println("HERE?");
		Bank oBank = (Bank)o;
		System.out.println(aLookup.get("id").gMethod.invoke(o));

		return false;
	}
}
