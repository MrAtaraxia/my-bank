package theBank;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
//import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import theBank.User;
import theBank.UserDao;
import theBank.UserDaoText;
import theBank.UserType;

/**
 * UserTest.java
 * @author w3
 */
//extends BaseTest
public class UserTest {
	
//	@Test
//	public void userTextRetrieveTest() throws Exception {
//		UserDaoText myUser = new UserDaoText();
//		Set<User> myUsers = myUser.readUsers();
//		String theName = "";
//		for(User user:myUsers) {
//			System.out.print(user.getFname());
//			if (user.getFname().equals("Bob")) {
//				theName = user.getFname();
//			}
//		}
//		assertEquals("Bob",theName);
//	}

	
//  BAADDD TEST!
//	@Test
//	public void userTextStoreTest() throws Exception {
//		UserDaoText uDao = new UserDaoText();
//		Set<User> myUsers = new HashSet<>();
//		User newUser = new User();
//		newUser.setId(10);
//		newUser.setFname("Bob");
//		newUser.setLname("Smith");
//		newUser.setUsername("bsmith");
//		newUser.setPass("pass");
//		newUser.setType(UserType.CUSTOMER);
//		myUsers.add(newUser);
//		uDao.writeUsers(myUsers);
//		//System.out.println(user.getFname());
//		//assertEquals("bob",user.getFname());
//	}
	
	@Test
	public void getUserTest() throws Exception {
		UserDao myUser = new UserDaoText();
		User user = myUser.getUser(10);
		System.out.println(user.getFname());
		assertEquals("Bob",user.getFname());
	}
	
	@Test
	public void getUserNoneTest() throws Exception {
		UserDaoText myUser = new UserDaoText();
		User user = myUser.getUser(4);
		assertEquals(null,user);
	}
	
	@Test
	public void getUserByUserNameAndPassTest() throws Exception {
		UserDaoText myUser = new UserDaoText();
		User user = myUser.getUserByUserNameAndPassword("ChrisPerson", "pass");
		assertEquals("Chris",user.getFname());
	}
	
	@Test
	public void getAllUsersTest() throws Exception {
		UserDaoText myUser = new UserDaoText();
		Set<User> users = myUser.getAllUsers();
		for(User u:users) {
			System.out.print(u.getId() + " " + u.getFname() + " " + u.getLname() + " " + u.getUsername() + " " + u.getPass() +  " " + u.getEmail() + " " + u.getType() + "\n" );
		}
	}
	
	@Test
	public void deleteUsersTest() throws Exception {
		UserDaoText myUser = new UserDaoText();
		boolean myBool = myUser.deleteUser(1000);
		assertEquals(false, myBool);
	}
	
	
	@Test
	public void insertUserFailTest() throws Exception {
		UserDaoText myUser = new UserDaoText();
		User newUser = new User();
		newUser.setId(12);
		Boolean myBool = myUser.insertUser(newUser);
		assertEquals(false, myBool);
	}
	
	@Test
	public void insertUserTest() throws Exception {
		User user = new User();
		user.setId(12);
		user.setFname("chris");
		user.setLname("fou");
		user.setUsername("cfou");
		user.setPass("pass");
		user.setType(UserType.EMPLOYEE);
		user.setEmail("chris@gmail.com");
		UserDao myUser = new UserDaoText();
		Set<User> theUsers = myUser.getAllUsers();
		for(User aUser: theUsers) {
			if(aUser.getUsername().equals(user.getUsername())) {
				myUser.deleteUser(aUser.getId());
				System.out.println("Deleted previous version");
			}
		}
		Boolean isTrue=false;
		try {
			isTrue = myUser.insertUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(true,isTrue);
	}
	
	@Test//(expected = SQLIntegrityConstraintViolationException.class)
	public void insertUserTwiceText() throws Exception{
		User user = new User();
		user.setFname("chris");
		user.setLname("fou");
		user.setUsername("cfou");
		user.setPass("pass");
		user.setType(UserType.EMPLOYEE);
		user.setEmail("chris@gmail.com");
		UserDaoText myUser = new UserDaoText();
		myUser.insertUser(user);
		Boolean works = myUser.insertUser(user);
		//assertEquals("ABC",getError());
		assertEquals(false, works);
	}
	
	@Test//(expected = SQLIntegrityConstraintViolationException.class)
	public void updateUserText() throws Exception{
		User user = new User();
		user.setFname("abc");
		user.setLname("def");
		user.setUsername("ghi");
		user.setPass("pass");
		user.setType(UserType.EMPLOYEE);
		user.setEmail("abc");
		User newUser = new User();
		newUser.setFname("aaa");
		newUser.setLname("bbb");
		newUser.setUsername("ccc");
		newUser.setPass("ddd");
		newUser.setType(UserType.EMPLOYEE);
		newUser.setEmail("eee@gmail.com");
		UserDaoText uDao = new UserDaoText();
		String[] fnames = {"Bob", "Chris", "Jen", "Mary"};
		String[] lname = {"Smith", "Place", "Person", "Again"};
		for(String fn:fnames) {
			for(String ln:lname) {
				User aUser = new User();
				aUser.setFname(fn);
				aUser.setLname(ln);
				aUser.setUsername(fn+ln);
				aUser.setEmail(fn +ln+ "@gmail.com");
				aUser.setPass("pass");
				aUser.setType(UserType.CUSTOMER);
				uDao.insertUser(aUser);
			}
		}
		uDao.insertUser(user);
		uDao.insertUser(newUser);
		Boolean works = uDao.insertUser(user);
		//assertEquals("ABC",getError());
		assertEquals(false, works);
	}

	@Test//(expected = SQLIntegrityConstraintViolationException.class)
	public void updateUserFailText() throws Exception{
		UserDaoText uDao = new UserDaoText();
		User user = new User();
		user.setFname("abc");
		user.setLname("def");
		user.setUsername("ghi");
		user.setPass("pass");
		user.setId(100000);
		Boolean works = uDao.updateUser(user);
		assertEquals(false, works);
		
	}
	
	@Test//(expected = SQLIntegrityConstraintViolationException.class)
	public void updateUserPassText() throws Exception{
		UserDaoText uDao = new UserDaoText();
		Set<User> theUsers = uDao.getAllUsers();
		boolean works = false;
		for(User aUser:theUsers) {
			aUser.setEmail("HiThere");
			works = uDao.updateUser(aUser);
			break;
		}
		assertEquals(false, works);
		
	}
	
	@Test(expected = FileNotFoundException.class)
	public void openFileFailText() throws Exception{
		UserDaoText myDao = new UserDaoText("Q:aUserFile.txt");
		User user = new User();
		user.setFname("abc");
		user.setLname("def");
		user.setUsername("ghi");
		user.setPass("pass");
		user.setId(100000);
		Set<User> users = myDao.getAllUsers();
		//assertEquals(false, returned);
	}
}
