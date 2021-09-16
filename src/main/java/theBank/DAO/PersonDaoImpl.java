package theBank.DAO;

import java.util.ArrayList;
import java.util.List;
import theBank.People.*;

/**
 * Person DAO implementation
 * @author w3
 */

public class PersonDaoImpl extends BaseDAOImpl implements PersonDao {

	@Override
	public Person getPerson(int id) throws Exception {
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("id");
		myListV.add(id);
		for(Object part:getAllItWherePS(
				"Person", new Person(), Person.lookup,"id = ?", myListV, myListT)) {
			return (Person) part;
		}
		return null;
	}

	@Override
	public List<Person> getAllPeople() throws Exception {
		List<Person> toReturn = new ArrayList<>();
		for(Object part:getAllIt(
				"Person", new Person(), Person.lookup)) {
			if(toReturn == null) { toReturn = new ArrayList<>(); }
			toReturn.add((Person) part);
		}
		return toReturn;
	}

	@Override
	public List<Person> getAllActivePeople() throws Exception {
		List<Person> toReturn = new ArrayList<>();
		List<String> myListT = new ArrayList<>();
		List<Object> myListV = new ArrayList<>();
		myListT.add("active");
		myListV.add(true);
		for(Object part:getAllItWherePS(
				"Person", new Person(), Person.lookup,"active=?", myListV, myListT)) {
			if(toReturn==null) {
				toReturn=new ArrayList<>();
			}
			toReturn.add((Person) part);
		}
		return toReturn;
	}

	@Override
	public List<Person> getAllPeopleByType(UType utype) throws Exception {
		List<Person> toReturn = new ArrayList<>();
		UsernameDaoImpl uDao = new UsernameDaoImpl();
		List<Username> uName = uDao.getAllUsernamesByType(utype);
		for(Username aname:uName) {
			Person aPerson = getPerson(aname.getPersonID());
			if(aPerson != null) {
				if(toReturn==null) {
					toReturn= new ArrayList<>();
				}
				toReturn.add(aPerson);
			}
		}
		return toReturn;
	}
	
	@Override
	public List<Person> getAllActivePeopleByType(UType utype) throws Exception {
		List<Person> toReturn = new ArrayList<>();
		UsernameDaoImpl uDao = new UsernameDaoImpl();
		List<Username> uName = uDao.getAllActiveUsernamesByType(utype);
		for(Username aname:uName) {
			Person aPerson = getPerson(aname.getPersonID());
			if(aPerson != null) {
				if(toReturn==null) {
					toReturn= new ArrayList<>();
				}
				toReturn.add(aPerson);
			}
		}
		return toReturn;
	}

	@Override
	public boolean UserExists(String uname) throws Exception {
		try {
			UsernameDaoImpl uDao = new UsernameDaoImpl();
			return uDao.UsernameExists(uname);
		} catch (Exception e) { System.out.println(e); }
		return false;
	}

	@Override
	public Person getPersonByUserAndPass(String user, String pass) throws Exception {
		try {
			//System.out.println("PERSON BY USER AND PASS");
			UsernameDaoImpl uDao = new UsernameDaoImpl();
			return getPerson(uDao.getUserIDByUNameAndPass(user, pass));
		} catch (Exception e) { System.out.println(e); }
		return null;
	}

	@Override
	public List<Person> getPeopleByPersonIDs(List<Integer> personIDs) throws Exception {
		List<Person> toReturn = new ArrayList<>();
		for(Integer perNum:personIDs) {
			Person curPer = getPerson(perNum);
			if(curPer!=null) {
				if(toReturn==null) { toReturn = new ArrayList<>(); }
				toReturn.add(curPer);
			}
		}
		return toReturn;
	}
	
	@Override
	public boolean insertPerson(Person input) throws Exception {
//		List<Person> aMap = getAllPeople();
//	    for(Person entity:aMap) {
//	    	if(entity.getAddressID().equals(input.getAddressID()))
//	    		if(entity.getFname().equals(input.getFname()))
//	    			if(entity.getLname().equals(input.getLname()))
//	    				{ System.out.println("Duplicate"); return false; }
//	    }
	    return insertIt("Person",input,Person.lookup);
	}

	@Override
	public boolean updatePerson(Person input) throws Exception {
		return updateIt("Person", input, Person.lookup);
	}

	@Override
	public boolean activateUsername(int id) throws Exception {
		return activateIt("Person", new Person(), Person.lookup, id);
	}

	@Override
	public boolean deactivateUsername(int id) throws Exception {
		return deactivateIt("Person", new Person(), Person.lookup, id);
	}

	@Override
	public boolean deletePerson(int id) throws Exception {
		return deleteIt("Person", id);
	}
	
	public static void main(String[] args) {
		PersonDaoImpl pDao = new PersonDaoImpl();
		try {
			Person myCurr = new Person();
			List<Person> pers = pDao.getAllPeople();
			myCurr.setId(1);
			for(Person ap:pers) {
				System.out.println(ap.getId());
			}
			UsernameDaoImpl uDao = new UsernameDaoImpl();
			myCurr = pDao.getPerson(1);
			System.out.println(uDao.getUserIDByUNameAndPass("123456", "123456"));
			//myCurr = pDao.getPersonByUserAndPass("123456","123456");
			System.out.println(myCurr.getId());
		}catch(Exception e) { System.out.println(e); }
	}

}
