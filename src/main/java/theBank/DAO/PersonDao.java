package theBank.DAO;

import java.util.List;
import theBank.People.*;

/**
 * Person DAO
 * @author w3
 */

public interface PersonDao {
    public Person getPerson(int id) throws Exception;
    List<Person> getAllPeople() throws Exception;
    List<Person> getAllActivePeople() throws Exception;
    List<Person> getAllPeopleByType(UType type) throws Exception;
    List<Person> getAllActivePeopleByType(UType type) throws Exception;
    boolean UserExists(String Personname) throws Exception;
    Person getPersonByUserAndPass(String user, String pass) throws Exception;
    List<Person> getPeopleByPersonIDs(List<Integer> personIDs) throws Exception;
    boolean insertPerson(Person Person) throws Exception;
    boolean updatePerson(Person Person) throws Exception;
    boolean activateUsername(int id) throws Exception;
    boolean deactivateUsername(int id) throws Exception;
    boolean deletePerson(int id) throws Exception;
}
