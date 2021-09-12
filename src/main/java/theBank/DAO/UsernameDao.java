package theBank.DAO;

import java.util.Map;

import theBank.People.*;

public interface UsernameDao {
    public Username getUsername(int usernameID) throws Exception;
    Map<Integer, Username> getAllUsernames() throws Exception;
    Map<Integer, Username> getAllActiveUsernames() throws Exception;
    Map<Integer, Username> getAllActiveUsernamesByType(UType type) throws Exception;
    boolean UsernameExists(String uname) throws Exception;
    int getUserIDByUsernameAndPassword(String uname, String pass) throws Exception;
    boolean insertUsername(Username user) throws Exception;
    boolean updateUsername(Username user) throws Exception;
    boolean activateUsername(int id) throws Exception;
    boolean deactivateUsername(int id) throws Exception;
    boolean deleteUsername(int id) throws Exception;
}