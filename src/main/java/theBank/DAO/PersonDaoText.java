package theBank.DAO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import theBank.People.UType;
import theBank.People.Person;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;



public class PersonDaoText implements PersonDao{
	
	//private static final Logger logger = LogManager.getLogger(PersonDaoText.class);
//	import java.io.*;
//	import java.util.Hash;
//
//	public class CustomerDAO {
//	    public final String filePath = "./files/customers.txt";
//
//	    public Hash<String, Customer> readCustomers() {
//	        Hash<String, Customer> returnThis = new Hash<String, Customer>();
//
//	        try {
//	            FileInputStream fis = new FileInputStream(filePath);
//	            ObjectInputStream in = new ObjectInputStream(fis);
//
//	            returnThis = (Hash<String, Customer>) in.readObject();
//	        } catch (IOException | ClassNotFoundException e) {
//	            e.printStackTrace();
//	        }
//
//	        return returnThis;
//	    }
//
//	    public void writeCustomers(Hash<String, Customer> input) {
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
	
	public String filePath = "./data/People.txt";
	
	public PersonDaoText(){
		//logger.info("PersonDaoText default constructor.");
	}
	
	public PersonDaoText(String filePath){
		//logger.info("PersonDaoText paramaterized constructor.");
		//logger.info("filePath changed to :" + filePath);
		this.filePath = filePath;
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	private Set<Person> readPeople() throws Exception {
		//logger.trace("readPeople start");
		//logger.info("readPeople start");
        Set<Person> returnThis = new HashSet<Person>();

        FileInputStream fis = new FileInputStream(filePath);
		try{
			ObjectInputStream in = new ObjectInputStream(fis);
			returnThis = (Set<Person>) in.readObject();
		}catch(Exception e) {
			
		}

        
		//logger.trace("readPeople returning");

        return returnThis;
    }

    private Boolean writePeople(Set<Person> input) throws Exception {
		//logger.trace("writePeople start");

        try {
	        FileOutputStream fos = new FileOutputStream(filePath);
	        ObjectOutputStream out = new ObjectOutputStream(fos);
	        out.writeObject(input);
	        out.flush();
	        out.close();
			//logger.trace("writePeople returning");
	        return true;
        } 
        catch (Exception e) {
        	throw e;
        }
    }
    
	@Override
	public Person getPerson(int id) throws Exception {
		//logger.trace("getPerson start");
		Set<Person> allPeople = readPeople();
		for(Person aPerson:allPeople) {
			if(aPerson.getId()==id) {
				return aPerson;
			}
		}
		return null;
	}

	@Override
	public Set<Person> getAllPeople() throws Exception {
		//logger.trace("getAllPeople start");
		return readPeople();
	}

	@Override
	public boolean PersonExists(String username) throws Exception{
		//logger.trace("PersonExists start");
		for(Person aPerson:readPeople()) {
			if(aPerson.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Set<Person> getAllPeopleByType(UType type) throws Exception {
		//logger.trace("getAllPeopleByType start");
		Set<Person> allPeople = readPeople();
		Set<Person> toReturn = null;
		for(Person aPerson:allPeople) {
			if(aPerson.getType() == type) {
				if(toReturn==null) {
					toReturn=new HashSet<>();
				}
				toReturn.add(aPerson);
			}
		}
		return toReturn;
	}
	
	@Override
	public Person getPersonByPersonNameAndPassword(String Username, String pass) throws Exception {
		//logger.trace("getPersonByPersonNameAndPassword start");
		Set<Person> allPeople = readPeople();
		for(Person aPerson:allPeople) {
			if(aPerson.getUsername().equals(Username)) {
				if(aPerson.getPass().equals(pass)) {
					return aPerson;
				}
			}
		}
		return null;
	}

	@Override
	public boolean insertPerson(Person person) throws Exception {
		//logger.trace("insertPeople start");
		if(person.getId().equals(null) || 
				person.getFname() == null||
				person.getLname().equals(null)) {
			return false;
		}
		Set<Person> allPeople = null;
		try {
			allPeople = readPeople();
			for(Person aPerson :allPeople) {
				if(aPerson.getId().equals(person.getId())) {
					return false;
				}
			}
		}catch(Exception e) {
			allPeople = new HashSet<Person>();
		}
		
		allPeople.add(person);
		if(writePeople(allPeople)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePerson(Person person) throws Exception {
		//logger.trace("updatePerson start");
		boolean updated = false;
		Set<Person> allPeople = readPeople();
		for(Person aPerson: allPeople)
		{
			if(aPerson.getId() == person.getId()) {
				allPeople.remove(aPerson);
				allPeople.add(person);
				updated = true;
				break;
			}
		}
		if(updated) {
			if(writePeople(allPeople)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deletePerson(int id) throws Exception {
		//logger.trace("deletePerson start");
		Set<Person> allPeople = readPeople();
		Boolean deleted = false;
		for(Person aPerson: allPeople)
		{
			if(aPerson.getId() == id) {
				allPeople.remove(aPerson);
				deleted = true;
				break;
			}
		}
		if(deleted) {
			if(writePeople(allPeople)) {
				return true;
			}
		}
		return false;
	}

}
