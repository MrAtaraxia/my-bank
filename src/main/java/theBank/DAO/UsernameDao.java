package theBank.DAO;

import java.util.List;
import theBank.People.*;

/**
 * Username DAO
 * @author w3
 */

public interface UsernameDao {
    public Username getUsername(int id) throws Exception;
    List<Username> getAllUsernames() throws Exception;
    List<Username> getAllActiveUsernames() throws Exception;
    List<Username> getAllUsernamesByType(UType utype) throws Exception;
    List<Username> getAllActiveUsernamesByType(UType utype) throws Exception;
    boolean UsernameExists(String uname) throws Exception;
    int getUserIDByUNameAndPass(String uname, String pass) throws Exception;
    Username getUsernameByPersonID(Integer personID) throws Exception;
    boolean insertUsername(Username input) throws Exception;
    boolean updateUsername(Username input) throws Exception;
    boolean activateUsername(int id) throws Exception;
    boolean deactivateUsername(int id) throws Exception;
    boolean deleteUsername(int id) throws Exception;
}