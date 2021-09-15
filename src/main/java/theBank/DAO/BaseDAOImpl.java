package theBank.DAO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import theBank.general.*;
import theBank.accounts.*;
import theBank.transactions.*;
import theBank.businesses.*;
import theBank.People.*;

public abstract class BaseDAOImpl {
	
	protected Map<Integer, Object> getAllIt(String str, Object o,  Map<String, Extractor> aLookup) throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM " + str;
	        ResultSet rs = stmt.executeQuery(query);
	        Map<Integer, Object> aMap = new HashMap<>();
	        //System.out.println("Get all it");
	        Object alocal = null;
	        while(rs.next()) {
	        	alocal = new Object();
	        	alocal = extractItFromResultSet(rs, o, aLookup);
	        	//System.out.println("ALOCAL" +alocal.toString());
	            aMap.put((Integer) aLookup.get("id").gMethod.invoke(alocal), alocal);
	            //System.out.println("yep");
	            alocal = null;
	            
	        }
	        return aMap;
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}
	
	protected Map<Integer, Object> getAllItWhereOld(String str, Object o,  Map<String, Extractor> aLookup, String where) throws Exception {
		Connection connection = ConnectionSingle.getConn();
		//System.out.println("Get all it WHERE");
	    try {
	        Statement stmt = connection.createStatement();
	        String query = "SELECT * FROM " + str + " WHERE " + where;
	        //System.out.println("before RS");
	        ResultSet rs = stmt.executeQuery(query);
	        //System.out.println("after RS");
	        Map<Integer, Object> aMap = new HashMap<>();
	        Object alocal = null;
	        while(rs.next()) {
	        	alocal = new Object();
		        //System.out.println("before extract");
	        	alocal = extractItFromResultSet(rs, o, aLookup);
		        //System.out.println("after extract");
	            aMap.put((Integer) aLookup.get("id").gMethod.invoke(alocal), alocal);
	            
	        }
	        return aMap;
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}
	
	
	protected List<Object> getAllItWherePS(String str, Object o,  Map<String, Extractor> aLookup, String where, List<Object> values, List<String> types) throws Exception {
		Connection connection = ConnectionSingle.getConn();
		//System.out.println("Get all it WHERE");
	    try {
	        String query = "SELECT * FROM " + str + " WHERE " + where;
	        //System.out.println(query);
	        PreparedStatement ps = connection.prepareStatement(query);
	        //System.out.println("AfterPS");
	        for(int i = 0; i < values.size();i++) {
	        	//System.out.println(i+"THE TYPE"+types.get(i));
	        	if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("Integer") || aLookup.get(types.get(i)).gType.equalsIgnoreCase("int")) {
	        		ps.setInt(i+1, (Integer)(values.get(i)));
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("Double") || aLookup.get(types.get(i)).gType.equalsIgnoreCase("double")) {
	        		ps.setDouble(i+1, (Double)(values.get(i)));
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("Boolean") || aLookup.get(types.get(i)).gType.equalsIgnoreCase("bool")) {
	        		ps.setBoolean(i+1, (Boolean)(values.get(i)));
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("String")) {
	        		ps.setString(i+1, (String)(values.get(i)));
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("State")) {
	        		ps.setString(i+1, ((State)(values.get(i))).toString());
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("TState")) {
	        		ps.setString(i+1, ((TState)(values.get(i))).toString());
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("AState")) {
	        		ps.setString(i+1, ((AState)(values.get(i))).toString());
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("AType")) {
	        		ps.setString(i+1, ((AType)(values.get(i))).toString());
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("BType")) {
	        		ps.setString(i+1, ((BType)(values.get(i))).toString());
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("OType")) {
	        		ps.setString(i+1, ((OType)(values.get(i))).toString());
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("TType")) {
	        		ps.setString(i+1, ((TType)(values.get(i))).toString());
	        	} else if(aLookup.get(types.get(i)).gType.equalsIgnoreCase("UType")) {
	        		ps.setString(i+1, ((UType)(values.get(i))).toString());
	        	}
	        }
	        //System.out.println("BeforeRS");
	        ResultSet rs = ps.executeQuery();
	        //System.out.println("after RS");
	        List<Object> aMap = new ArrayList<>();
	        Object alocal = null;
	        while(rs.next()) {
	        	alocal = new Object();
		        //System.out.println("before extract");
	        	alocal = extractItFromResultSet(rs, o, aLookup);
		        //System.out.println("after extract");
	            aMap.add(alocal);
	            
	        }
	        return aMap;
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return null;
	}
	
	@SuppressWarnings("rawtypes")
	protected Object extractItFromResultSet(ResultSet rs, Object o, Map<String, Extractor> aLookup) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException, SecurityException {

		//Class<?> cl = Class.forName(o.getClass().getName());
		Constructor construct = o.getClass().getConstructor();
		Object newO = o.getClass().cast(construct.newInstance());
		//System.out.println("ExtractIt START");
		for(Entry<String, Extractor> curr:aLookup.entrySet()) {
			Object toGet = null;
			if(curr.getValue().sType.equalsIgnoreCase("Integer") || curr.getValue().sType.equalsIgnoreCase("int")) {
				toGet = rs.getInt(curr.getKey());
			} else if(curr.getValue().sType.equalsIgnoreCase("Double") || curr.getValue().sType.equalsIgnoreCase("double")) {
				toGet = rs.getDouble(curr.getKey());
			} else if(curr.getValue().sType.equalsIgnoreCase("Boolean") || curr.getValue().sType.equalsIgnoreCase("bool")) {
				toGet = rs.getBoolean(curr.getKey());
			} else if(curr.getValue().sType.equalsIgnoreCase("String")) {
				toGet = rs.getString(curr.getKey());
			}else if(curr.getValue().sType.equalsIgnoreCase("State")) {
				toGet = State.get(rs.getString(curr.getKey()));
			}else if(curr.getValue().sType.equalsIgnoreCase("BType")) {
				toGet = BType.get(rs.getString(curr.getKey()));
			}else if(curr.getValue().sType.equalsIgnoreCase("UType")) {
				toGet = UType.get(rs.getString(curr.getKey()));
			}else if(curr.getValue().sType.equalsIgnoreCase("AState")) {
				toGet = AState.get(rs.getString(curr.getKey()));
			}else if(curr.getValue().sType.equalsIgnoreCase("AType")) {
				toGet = AType.get(rs.getString(curr.getKey()));
			}else if(curr.getValue().sType.equalsIgnoreCase("OType")) {
				toGet = OType.get(rs.getString(curr.getKey()));
			}else if(curr.getValue().sType.equalsIgnoreCase("TState")) {
				toGet = TState.get(rs.getString(curr.getKey()));
			}else if(curr.getValue().sType.equalsIgnoreCase("TType")) {
				toGet = TType.get(rs.getString(curr.getKey()));
			}
			//System.out.println("Curr" + curr.getValue().name);
			//System.out.println("Curr" + curr.getValue().getter);
			curr.getValue().sMethod.invoke(newO, toGet);
		}
	    return newO;
	}
	
	protected boolean insertIt(String str, Object o, Map<String, Extractor> aLookup) throws Exception {
	    Connection connection = ConnectionSingle.getConn();
	    //System.out.println("Insert Start");
	    StringBuilder begin = new StringBuilder("INSERT INTO " + str + " (");
	    StringBuilder middle = new StringBuilder(") VALUES (");
	    StringBuilder end = new StringBuilder(")");
	    StringBuilder sParams = new StringBuilder();
	    StringBuilder sValues = new StringBuilder();
	    List<Method>  sMeths = new ArrayList<>();
	    List<String>  sType = new ArrayList<>();
	    for(Entry<String, Extractor> curr:aLookup.entrySet()) {
	    	if(curr.getValue().name.equalsIgnoreCase("id")) {
	    		//continue;
	    	}
	    	if(curr.getValue().name.equalsIgnoreCase("created")) {
	    		continue;
	    	}
	    	if(curr.getValue().name.equalsIgnoreCase("modified")) {
	    		continue;
	    	}
			//System.out.println(curr.getValue().name);
			sParams.append(curr.getValue().name + ", ");
			sValues.append("?, ");
			sMeths.add(curr.getValue().gMethod);
			sType.add(curr.getValue().gType);
	    }
	    //System.out.println("Params Made");
	    sParams = sParams.delete(sParams.lastIndexOf(","), sParams.length());
	    sValues = sValues.delete(sValues.lastIndexOf(","), sValues.length());
	    String query = begin.toString() + sParams.toString() + middle.toString() +
	    		sValues.toString() + end.toString();
	    //System.out.println(query);
	    try {
	    	PreparedStatement ps = connection.prepareStatement(query);
	        for(int i = 0; i < sMeths.size(); i++ ) {
	        	//System.out.println(sMeths.get(i) + " " + sType.get(i) + " " + i + " "+ sMeths.size());
	        	if(sType.get(i).equalsIgnoreCase("Integer") || sType.get(i).equalsIgnoreCase("int")) {
	        		//System.out.println("ABC" + sMeths.get(i).getName()+ " " + sMeths.get(i).invoke(o));
	        		ps.setInt(i+1, (Integer) sMeths.get(i).invoke(o));
	        	}else if(sType.get(i).equalsIgnoreCase("Double") || sType.get(i).equalsIgnoreCase("double")) {
	        		ps.setDouble(i+1, (Double) sMeths.get(i).invoke(o));
	        	}else if(sType.get(i).equalsIgnoreCase("Boolean") || sType.get(i).equalsIgnoreCase("bool")) {
	        		ps.setBoolean(i+1, (Boolean) sMeths.get(i).invoke(o));
	        	}else if(sType.get(i).equalsIgnoreCase("String")) {
	        		ps.setString(i+1, (String) sMeths.get(i).invoke(o));
	        	}else if(sType.get(i).equalsIgnoreCase("State")) {
	        		ps.setString(i+1, ((State) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("AState")) {
	        		ps.setString(i+1, ((AState) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("TState")) {
	        		ps.setString(i+1, ((TState) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("AType")) {
	        		ps.setString(i+1, ((AType) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("BType")) {
	        		ps.setString(i+1, ((BType) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("UType")) {
	        		ps.setString(i+1, ((UType) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("TType")) {
	        		ps.setString(i+1, ((TType) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("OType")) {
	        		ps.setString(i+1, ((OType) sMeths.get(i).invoke(o)).toString());
	        	}
	        }
	        //System.out.println("HERE?");
	        int i = ps.executeUpdate();
	        if(i == 1) { return true; }
	    } catch (SQLException ex) { System.out.println(ex); }
	    return false;
	}
	
	
	protected boolean updateIt(String str, Object o, Map<String, Extractor> aLookup) throws Exception {
		Connection connection = ConnectionSingle.getConn();
	    //System.out.println("UPDATE Start");
	    StringBuilder begin = new StringBuilder("UPDATE " + str + " SET ");
	    StringBuilder end = new StringBuilder(" WHERE id=?");
	    StringBuilder sParams = new StringBuilder();
	    List<Method>  sMeths = new ArrayList<>();
	    List<String>  sType = new ArrayList<>();
	    for(Entry<String, Extractor> curr:aLookup.entrySet()) {
	    	if(curr.getValue().name.equalsIgnoreCase("id")) {
	    		continue;
	    	}
	    	if(curr.getValue().name.equalsIgnoreCase("created")) {
	    		continue;
	    	}
	    	if(curr.getValue().name.equalsIgnoreCase("modified")) {
	    		continue;
	    	}
			//System.out.println(curr.getValue().name);
			sParams.append(curr.getValue().name + "=?, ");
			sMeths.add(curr.getValue().gMethod);
			sType.add(curr.getValue().gType);
	    }
	    //System.out.println("Params Made");
	    sParams = sParams.delete(sParams.lastIndexOf(","), sParams.length());
	    String query = begin.toString() + sParams.toString() + end.toString();
	    //System.out.println(query);
	    try {
	    	PreparedStatement ps = connection.prepareStatement(query);
	        for(int i = 0; i < sMeths.size(); i++ ) {
	        	//System.out.println(sMeths.get(i) + " " + sType.get(i) + " " + i + " "+ sMeths.size());
	        	if(sType.get(i).equalsIgnoreCase("Integer") || sType.get(i).equalsIgnoreCase("int")) {
	        		//System.out.println("ABC" + sMeths.get(i).getName()+ " " + sMeths.get(i).invoke(o));
	        		ps.setInt(i+1, (Integer) sMeths.get(i).invoke(o));
	        	}else if(sType.get(i).equalsIgnoreCase("Double") || sType.get(i).equalsIgnoreCase("double")) {
	        		ps.setDouble(i+1, (Double) sMeths.get(i).invoke(o));
	        	}else if(sType.get(i).equalsIgnoreCase("Boolean") || sType.get(i).equalsIgnoreCase("bool")) {
	        		ps.setBoolean(i+1, (Boolean) sMeths.get(i).invoke(o));
	        	}else if(sType.get(i).equalsIgnoreCase("String")) {
	        		ps.setString(i+1, (String) sMeths.get(i).invoke(o));
	        	}else if(sType.get(i).equalsIgnoreCase("State")) {
	        		ps.setString(i+1, ((State) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("AState")) {
	        		ps.setString(i+1, ((AState) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("TState")) {
	        		ps.setString(i+1, ((TState) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("AType")) {
	        		ps.setString(i+1, ((AType) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("BType")) {
	        		ps.setString(i+1, ((BType) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("UType")) {
	        		ps.setString(i+1, ((UType) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("TType")) {
	        		ps.setString(i+1, ((TType) sMeths.get(i).invoke(o)).toString());
	        	}else if(sType.get(i).equalsIgnoreCase("OType")) {
	        		ps.setString(i+1, ((OType) sMeths.get(i).invoke(o)).toString());
	        	}
	        }
	        ps.setInt(sMeths.size()+1, (Integer) aLookup.get("id").gMethod.invoke(o));
	        //System.out.println("Updating!");
	        int i = ps.executeUpdate();
	        if(i == 1) { return true; }
	    } catch (SQLException ex) { System.out.println(ex); }
	    return false;
	}
	
	protected boolean activateIt(String str, Object o, Map<String, Extractor> aLookup, int id) throws Exception {
		Object toActiCasted = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("id");
		myListV.add(id);
		for(var toActi: getAllItWherePS(str,o,aLookup,"id=?", myListV, myListT)) 
		{ toActiCasted = o.getClass().cast(toActi); }
		aLookup.get("active").sMethod.invoke(toActiCasted,true);
		return updateIt(str, toActiCasted, aLookup);
	}
	
	protected boolean deactivateIt(String str, Object o, Map<String, Extractor> aLookup, int id) throws Exception {
		Object toDeActiCasted = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("id");
		myListV.add(id);
		for(var toActi: getAllItWherePS(str,o,aLookup,"id=?", myListV, myListT)) 
		{ toDeActiCasted = o.getClass().cast(toActi); }
		aLookup.get("active").sMethod.invoke(toDeActiCasted,false);
		return updateIt(str, toDeActiCasted, aLookup);
	}
	
	protected boolean deleteIt(String str, int id) throws Exception {
	    Connection connection = ConnectionSingle.getConn();
	    try {
	        Statement stmt = connection.createStatement();
	        int i = stmt.executeUpdate("DELETE FROM " +str+ " WHERE id=" + id);
	        if(i == 1) { return true; }
	    } catch (SQLException ex) { ex.printStackTrace(); }
	    return false;
	}
}
