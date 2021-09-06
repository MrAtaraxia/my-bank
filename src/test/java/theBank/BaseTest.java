package theBank;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.util.Scanner;
import java.util.Set;

import org.junit.After;
import org.junit.Before;

import theBank.Account;
import theBank.Main;
import theBank.User;
import theBank.UserType;

/**
 * BaseTest.java
 * @author w3
 */

public abstract class BaseTest {

	public String testBreak = "#################################";
	protected final InputStream systemIn = System.in;
	protected final PrintStream systemOut = System.out;
	protected final PrintStream systemErr = System.err;
	public static InputStream inSt = null;
	public OutputStream outSt = null;
	public OutputStream errSt = null;
	protected static ByteArrayInputStream testIn;
	protected final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
	protected final ByteArrayOutputStream testErr = new ByteArrayOutputStream();
	protected Main myMain = null;
	String runStart = "";
	String loginScreen = "";
	

	String endOfTest = "DONE!\r\n";
	
	@Before
	public void setUp() throws Exception 
	{
		myMain = new Main();
		runStart = myMain.clearScreen + 
				myMain.WELCOME_MESSAGE + 
				myMain.spacingOnScreen + 
				myMain.PRESS_ANY + 
				myMain.clearScreen;
		loginScreen = "Please select from the following choices:\n"
				+ myMain.loginMenu[0].number + " - " + myMain.loginMenu[0].name + "\n"
				+ myMain.loginMenu[1].number + " - " + myMain.loginMenu[1].name + "\n"
				+ myMain.loginMenu[2].number + " - " + myMain.loginMenu[2].name + "\n"
				+ "\n\n" + "Input > ";
        System.setOut(new PrintStream(testOut));
		System.out.print(testBreak);
		myMain.aScanner = new Scanner(System.in);
	}
	
	@After
	public void tearDown() throws Exception 
	{
		//myMain.aScanner.close();
		testOut.close();
	}
	
	protected String getOutput() {
		return testOut.toString();
	}
	
	protected String getError() {
		return testErr.toString();
	}

	protected InputStream makeStream(String[] aString) {
		InputStream lInStream = new ByteArrayInputStream((aString[0]+"\n").getBytes());
		for(int i=1;i<aString.length;i++) {
			lInStream = new SequenceInputStream(lInStream, new ByteArrayInputStream((aString[i]+"\n").getBytes()));
		}
		return lInStream;
	}
	
	protected void makeUsers() throws Exception {
		//myMain.aDao.
		User bob = new User(111, "Bob", "person", "cUser", "cPassword", "realemail", UserType.CUSTOMER);
		myMain.uDao.insertUser(bob);
		User u1 = new User(112, "Another", "Person", "eUser", "ePassword", "realemailagain", UserType.EMPLOYEE);
		User u2 = new User(113, "Totally", "NotFake", "aUser", "aPassword", "another@email.com", UserType.ADMIN);
		myMain.uDao.insertUser(u1);
		myMain.uDao.insertUser(u2);
		int count = 0;
		Set<User> allUsers = myMain.uDao.getAllUsersByType(UserType.CUSTOMER);
		for(User aUser:allUsers) {
			Integer[] users = {aUser.getId()};
			Account acc2 = new Account();
			acc2.setOwners(users);
			if(count%2==0) {
				acc2.setApproved(true, UserType.ADMIN);
			}
			acc2.setBalance((double) 5000);
			myMain.aDao.insertAccount(acc2);
			count++;
		}
	}

}
