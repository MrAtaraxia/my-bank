package theBank.DAO;

import java.util.Map;

import theBank.People.UType;
import theBank.People.Person;

/**
 * Person DAO
 * @author w3
 */

public interface PersonDao {
    public Person getPerson(int id) throws Exception;
    Map<Integer, Person> getAllPeople() throws Exception;
    Map<Integer, Person> getAllActivePeople() throws Exception;
    Map<Integer, Person> getAllActivePeopleByType(UType type) throws Exception;
    boolean PersonExists(String Personname) throws Exception;
    Person getPersonByPersonNameAndPassword(String Person, String pass) throws Exception;
    boolean insertPerson(Person Person) throws Exception;
    boolean updatePerson(Person Person) throws Exception;
    
    boolean deletePerson(int id) throws Exception;
}
