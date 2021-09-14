package theBank.DAO;

import java.util.ArrayList;
import java.util.List;

import theBank.People.UType;
import theBank.People.Username;

public class UsernameDaoImpl extends BaseDAOImpl implements UsernameDao {

	@Override
	public Username getUsername(int id) throws Exception {
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("id");
		myListV.add(id);
		for(var part:getAllItWherePS(
				"Username", new Username(), Username.lookup,"id = ?", myListV, myListT)) {
			return (Username) part;
		}
		return null;
	}

	@Override
	public List<Username> getAllUsernames() throws Exception {
		List<Username> toReturn = null;
		for(var part:getAllIt(
				"Username", new Username(), Username.lookup).entrySet()) {
			if(toReturn == null) { toReturn = new ArrayList<>(); }
			toReturn.add((Username) part.getValue());
		}
		return toReturn;
	}

	@Override
	public List<Username> getAllActiveUsernames() throws Exception {
		List<Username> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		for(var part:getAllItWherePS(
				"Username", new Username(), Username.lookup,"active=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((Username) part);
		}
		return toReturn;
	}

	@Override
	public List<Username> getAllUsernamesByType(UType utype) throws Exception {
		List<Username> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("utype");
		myListV.add(utype);
		for(var part:getAllItWherePS(
				"Username", new Username(), Username.lookup,"utype=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Username) part);
		}
		return toReturn;
	}

	@Override
	public List<Username> getAllActiveUsernamesByType(UType utype) throws Exception {
		List<Username> toReturn = null;
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		myListT.add("utype");
		myListV.add(utype);
		for(var part:getAllItWherePS(
				"Username", new Username(), Username.lookup,"active=? AND utype=?", myListV, myListT)) {
			if(toReturn==null) { toReturn=new ArrayList<>(); }
			toReturn.add((Username) part);
		}
		return toReturn;
	}
	
	@Override
	public boolean UsernameExists(String uname) throws Exception {
		List<Username> allUsers = getAllActiveUsernames();
		if(allUsers!=null) {
			for(var aUser: allUsers) {
				if(aUser.getUsername().equalsIgnoreCase(uname)) { return true; }
			}
		}
		return false;
	}

	@Override
	public Username getUsernameByPersonID(Integer personID) throws Exception {
		System.out.println("Start PersonID");
		List<Username> allUsers = getAllActiveUsernames();
		System.out.println("Mid PersonID");
		if(allUsers!=null) {
			for(var aUser: allUsers) {
				if(aUser.getPersonID() == (personID)) { return aUser; }
			}
		}
		return null;
	}
	
	@Override
	public int getUserIDByUNameAndPass(String uname, String pass) throws Exception {
		List<Username> allUsers = getAllActiveUsernames();
		if(allUsers!=null) {
			for(var aUser: allUsers) {
				if(aUser.getUsername().equalsIgnoreCase(uname) &&
						aUser.getPass().equalsIgnoreCase(pass)) { return aUser.getPersonID(); }
			}
		}
		return 0;
	}

	@Override
	public boolean insertUsername(Username input) throws Exception {
		List<Username> aMap = getAllUsernames();
	    for(Username entity:aMap) {
	    	if(entity.getUsername().equals(input.getUsername()))
	    		if(entity.getPass().equals(input.getPass()))
	    			{ System.out.println("Duplicate"); return false; }
	    }
	    return insertIt("Username",input,Username.lookup);
	}

	@Override
	public boolean updateUsername(Username input) throws Exception {
		return updateIt("Username", input, Username.lookup);
	}

	@Override
	public boolean activateUsername(int id) throws Exception {
		return activateIt("Username", new Username(), Username.lookup, id);
	}

	@Override
	public boolean deactivateUsername(int id) throws Exception {
		return deactivateIt("Username", new Username(), Username.lookup, id);
	}

	@Override
	public boolean deleteUsername(int id) throws Exception {
		return deleteIt("Username", id);
	}

	public static void main(String[] args) {
		UsernameDaoImpl uDao = new UsernameDaoImpl();
		try {
			Username myCurr = new Username();
			uDao.getAllUsernames();
			myCurr.setId(1);
			List<Username> pers = uDao.getAllUsernames();
			myCurr.setId(1);
			for(Username ap:pers) {
				System.out.println(ap.getId());
			}
		}catch(Exception e) { System.out.println(e); }
	}

}
