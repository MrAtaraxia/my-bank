package theBank;

import java.util.Set;

/**
 * User DAO
 * @author w3
 */

public interface UserDao {
    public User getUser(int id) throws Exception;
    Set<User> getAllUsers() throws Exception;
    boolean userExists(String username) throws Exception;
	Set<User> getAllUsersByType(UserType type) throws Exception;
    User getUserByUserNameAndPassword(String user, String pass) throws Exception;
    boolean insertUser(User user) throws Exception;
    boolean updateUser(User user) throws Exception;
    
    boolean deleteUser(int id) throws Exception;
}
