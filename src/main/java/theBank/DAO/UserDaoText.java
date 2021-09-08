package theBank.DAO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import theBank.users.UType;
import theBank.users.User;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;



public class UserDaoText implements UserDao{
	
	//private static final Logger logger = LogManager.getLogger(UserDaoText.class);
//	import java.io.*;
//	import java.util.HashMap;
//
//	public class CustomerDAO {
//	    public final String filePath = "./files/customers.txt";
//
//	    public HashMap<String, Customer> readCustomers() {
//	        HashMap<String, Customer> returnThis = new HashMap<String, Customer>();
//
//	        try {
//	            FileInputStream fis = new FileInputStream(filePath);
//	            ObjectInputStream in = new ObjectInputStream(fis);
//
//	            returnThis = (HashMap<String, Customer>) in.readObject();
//	        } catch (IOException | ClassNotFoundException e) {
//	            e.printStackTrace();
//	        }
//
//	        return returnThis;
//	    }
//
//	    public void writeCustomers(HashMap<String, Customer> input) {
//
//	        try {
//	            FileOutputStream fos = new FileOutputStream(filePath);
//	            ObjectOutputStream out = new ObjectOutputStream(fos);
//	            out.writeObject(input);
//	            out.flush();
//	            out.close();
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}
	
	public String filePath = "./data/users.txt";
	
	public UserDaoText(){
		//logger.info("UserDaoText default constructor.");
	}
	
	public UserDaoText(String filePath){
		//logger.info("UserDaoText paramaterized constructor.");
		//logger.info("filePath changed to :" + filePath);
		this.filePath = filePath;
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	private Set<User> readUsers() throws Exception {
		//logger.trace("readUsers start");
		//logger.info("readUsers start");
        Set<User> returnThis = new HashSet<User>();

        FileInputStream fis = new FileInputStream(filePath);
		try{
			ObjectInputStream in = new ObjectInputStream(fis);
			returnThis = (Set<User>) in.readObject();
		}catch(Exception e) {
			
		}

        
		//logger.trace("readUsers returning");

        return returnThis;
    }

    private Boolean writeUsers(Set<User> input) throws Exception {
		//logger.trace("writeUsers start");

        try {
	        FileOutputStream fos = new FileOutputStream(filePath);
	        ObjectOutputStream out = new ObjectOutputStream(fos);
	        out.writeObject(input);
	        out.flush();
	        out.close();
			//logger.trace("writeUsers returning");
	        return true;
        } 
        catch (Exception e) {
        	throw e;
        }
    }
    
	@Override
	public User getUser(int id) throws Exception {
		//logger.trace("getUser start");
		Set<User> allUsers = readUsers();
		for(User aUser:allUsers) {
			if(aUser.getId()==id) {
				return aUser;
			}
		}
		return null;
	}

	@Override
	public Set<User> getAllUsers() throws Exception {
		//logger.trace("getAllUsers start");
		return readUsers();
	}

	@Override
	public boolean userExists(String username) throws Exception{
		//logger.trace("userExists start");
		for(User aUser:readUsers()) {
			if(aUser.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Set<User> getAllUsersByType(UType type) throws Exception {
		//logger.trace("getAllUsersByType start");
		Set<User> allUsers = readUsers();
		Set<User> toReturn = null;
		for(User aUser:allUsers) {
			if(aUser.getType() == type) {
				if(toReturn==null) {
					toReturn=new HashSet<>();
				}
				toReturn.add(aUser);
			}
		}
		return toReturn;
	}
	
	@Override
	public User getUserByUserNameAndPassword(String username, String pass) throws Exception {
		//logger.trace("getUserByUserNameAndPassword start");
		Set<User> allUsers = readUsers();
		for(User aUser:allUsers) {
			if(aUser.getUsername().equals(username)) {
				if(aUser.getPass().equals(pass)) {
					return aUser;
				}
			}
		}
		return null;
	}

	@Override
	public boolean insertUser(User user) throws Exception {
		//logger.trace("insertUsers start");
		if(user.getId().equals(null) || 
				user.getFname() == null||
				user.getLname().equals(null)) {
			return false;
		}
		Set<User> allUsers = null;
		try {
			allUsers = readUsers();
			for(User aUser :allUsers) {
				if(aUser.getId().equals(user.getId())) {
					return false;
				}
			}
		}catch(Exception e) {
			allUsers = new HashSet<User>();
		}
		
		allUsers.add(user);
		if(writeUsers(allUsers)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		//logger.trace("updateUser start");
		boolean updated = false;
		Set<User> allUsers = readUsers();
		for(User aUser: allUsers)
		{
			if(aUser.getId() == user.getId()) {
				allUsers.remove(aUser);
				allUsers.add(user);
				updated = true;
				break;
			}
		}
		if(updated) {
			if(writeUsers(allUsers)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteUser(int id) throws Exception {
		//logger.trace("deleteUser start");
		Set<User> allUsers = readUsers();
		Boolean deleted = false;
		for(User aUser: allUsers)
		{
			if(aUser.getId() == id) {
				allUsers.remove(aUser);
				deleted = true;
				break;
			}
		}
		if(deleted) {
			if(writeUsers(allUsers)) {
				return true;
			}
		}
		return false;
	}

}
