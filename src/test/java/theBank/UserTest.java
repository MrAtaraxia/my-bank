package theBank;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;

import org.junit.Test;

import theBank.DAO.*;
import theBank.People.*;

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
		PersonDao myUser = new PersonDaoText();
		Person person = myUser.getUser(10);
		System.out.println(person.getFname());
		assertEquals("Bob",person.getFname());
	}
	
	@Test
	public void getUserNoneTest() throws Exception {
		PersonDaoText myUser = new PersonDaoText();
		Person person = myUser.getUser(4);
		assertEquals(null,person);
	}
	
	@Test
	public void getUserByUserNameAndPassTest() throws Exception {
		PersonDaoText myUser = new PersonDaoText();
		Person person = myUser.getUserByUserNameAndPassword("ChrisPerson", "pass");
		assertEquals("Chris",person.getFname());
	}
	
	@Test
	public void getAllUsersTest() throws Exception {
		PersonDaoText myUser = new PersonDaoText();
		Set<Person> persons = myUser.getAllUsers();
		for(Person u:persons) {
			System.out.print(u.getId() + " " + u.getFname() + " " + u.getLname() + " " + u.getUsername() + " " + u.getPass() +  " " + u.getEmail() + " " + u.getType() + "\n" );
		}
	}
	
	@Test
	public void deleteUsersTest() throws Exception {
		PersonDaoText myUser = new PersonDaoText();
		boolean myBool = myUser.deleteUser(1000);
		assertEquals(false, myBool);
	}
	
	
	@Test
	public void insertUserFailTest() throws Exception {
		PersonDaoText myUser = new PersonDaoText();
		Person newUser = new Person();
		newUser.setId(12);
		Boolean myBool = myUser.insertUser(newUser);
		assertEquals(false, myBool);
	}
	
	@Test
	public void insertUserTest() throws Exception {
		Person person = new Person();
		person.setId(12);
		person.setFname("chris");
		person.setLname("fou");
		person.setUsername("cfou");
		person.setPass("pass");
		person.setType(UType.EMPLOYEE);
		person.setEmail("chris@gmail.com");
		PersonDao myUser = new PersonDaoText();
		Set<Person> theUsers = myUser.getAllUsers();
		for(Person aUser: theUsers) {
			if(aUser.getUsername().equals(person.getUsername())) {
				myUser.deleteUser(aUser.getId());
				System.out.println("Deleted previous version");
			}
		}
		Boolean isTrue=false;
		try {
			isTrue = myUser.insertUser(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(true,isTrue);
	}
	
	@Test(expected = SQLIntegrityConstraintViolationException.class)
	public void insertUserTwiceText() throws Exception{
		Person person = new Person();
		person.setFname("chris");
		person.setLname("fou");
		person.setUsername("cfou");
		person.setPass("pass");
		person.setType(UType.EMPLOYEE);
		person.setEmail("chris@gmail.com");
		PersonDaoText myUser = new PersonDaoText();
		myUser.insertUser(person);
		Boolean works = myUser.insertUser(person);
		//assertEquals("ABC",getError());
		assertEquals(false, works);
	}
	
	@Test//(expected = SQLIntegrityConstraintViolationException.class)
	public void updateUserText() throws Exception{
		Person person = new Person();
		person.setFname("abc");
		person.setLname("def");
		person.setUsername("ghi");
		person.setPass("pass");
		person.setType(UType.EMPLOYEE);
		person.setEmail("abc");
		Person newUser = new Person();
		newUser.setFname("aaa");
		newUser.setLname("bbb");
		newUser.setUsername("ccc");
		newUser.setPass("ddd");
		newUser.setType(UType.EMPLOYEE);
		newUser.setEmail("eee@gmail.com");
		PersonDaoText uDao = new PersonDaoText();

		uDao.insertUser(person);
		uDao.insertUser(newUser);
		Boolean works = uDao.insertUser(person);
		//assertEquals("ABC",getError());
		assertEquals(false, works);
	}

	@Test//(expected = SQLIntegrityConstraintViolationException.class)
	public void updateUserFailText() throws Exception{
		PersonDaoText uDao = new PersonDaoText();
		Person person = new Person();
		person.setFname("abc");
		person.setLname("def");
		person.setUsername("ghi");
		person.setPass("pass");
		person.setId(100000);
		Boolean works = uDao.updateUser(person);
		assertEquals(false, works);
		
	}
	
	@Test//(expected = SQLIntegrityConstraintViolationException.class)
	public void updateUserPassText() throws Exception{
		PersonDaoText uDao = new PersonDaoText();
		Set<Person> theUsers = uDao.getAllUsers();
		boolean works = false;
		for(Person aUser:theUsers) {
			aUser.setEmail("HiThere");
			works = uDao.updateUser(aUser);
			break;
		}
		assertEquals(false, works);
		
	}
	
	@Test(expected = FileNotFoundException.class)
	public void openFileFailText() throws Exception{
//		UserDaoText myDao = new UserDaoText("Q:aUserFile.txt");
//		User user = new User();
//		user.setFname("abc");
//		user.setLname("def");
//		user.setUsername("ghi");
//		user.setPass("pass");
//		user.setId(100000);
//		//Set<User> users = myDao.getAllUsers();
		assertTrue(true);
	}
}
