package theBank.DAO;

import java.util.Map;

import theBank.People.Person;
import theBank.People.UType;
import theBank.People.Username;

public interface UsernameDao {
    public Username getUsername(int usernameID) throws Exception;
    Map<Integer, Person> getAllUsernames() throws Exception;
    Map<Integer, Person> getAllActiveUsernames() throws Exception;
    Map<Integer, Person> getAllActiveUsernamesByType(UType type) throws Exception;
    boolean UsernameExists(String uname) throws Exception;
    int getUserIDByUsernameAndPassword(String uname, String pass) throws Exception;
    boolean insertUsername(Username user) throws Exception;
    boolean updateUsername(Username user) throws Exception;
    boolean activateUsername(int id) throws Exception;
    boolean deactivateUsername(int id) throws Exception;
    boolean deleteUsername(int id) throws Exception;
}