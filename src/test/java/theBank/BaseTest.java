package theBank;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import theBank.accounts.*;
import theBank.DAO.*;
import theBank.People.*;

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
	public static Main myMain = null;
	String runStart = "";
	String loginScreen = "";
	

	String endOfTest = "DONE!\r\n";
	
	@Before
	public void setUp() throws Exception 
	{
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
	
	@BeforeClass
	public static void makeUsersMySQL() throws Exception {
		myMain = new Main();
		Person.nextId = 5;
		Account.nextId = 5;
		//myMain.uDao = new UserDaoText("./data/usersTest.txt");
		//myMain.aDao = new AccountDaoText("./data/accountsTest.txt");
		
//		for(Person use:myMain.pDao.getAllPeople()) {
//			myMain.pDao.deletePerson(use.getId());
//		}
		
//		for(Account acc:myMain.AcDao.getAllAccounts()) {
//			myMain.AcDao.deleteAccount(acc.getId());
//		}
		
//		for(Account acca:myMain.AcDao.getAllAccounts()) {
//			System.out.print(acca.getId()+" AP:");
//			System.out.print(acca.getAstate()+" EN:");
//			System.out.print(acca.getBalance()+" TY:");
//			System.out.print(acca.getAtype()+" OWN:");
//			if(acca.getOwners()!=null) {
//				for(int i:acca.getOwners()) {
//					System.out.print(i+" ");
//				}
//			}
//			else {
//				myMain.aDao.deleteAccount(acca.getId());
//			}
//			System.out.print("\n");
//		}
//		
//		for(Person use:myMain.uDao.getAllUsers()) {
//			System.out.print(" ID:"+ use.getId());
//			System.out.print(" FN:"+ use.getFname());
//			System.out.print(" LN:"+ use.getLname());
//			System.out.print(" UN:"+ use.getUsername());
//			System.out.print(" PA:"+ use.getPass());
//			System.out.print(" EM:"+ use.getEmail());
//			System.out.print(" WI:"+ use.getWithdrawn());
//			System.out.print(" TY:"+ use.getType());
//			System.out.print("\n");
//		}
//		System.out.print("Before\n");
//		Person bob = new Person(111, "Bob", "person", "cUser", "cPassword", "realemail", UType.CUSTOMER);
//		myMain.uDao.insertUser(bob);
//		myMain.uDao.updateUser(bob);
//		Person u1 = new Person(112, "Another", "Person", "eUser", "ePassword", "realemailagain", UType.EMPLOYEE);
//		Person u2 = new Person(113, "Totally", "NotFake", "aUser", "aPassword", "another@email.com", UType.ADMIN);
//		myMain.uDao.insertUser(u1);
//		myMain.uDao.updateUser(u1);
//		myMain.uDao.insertUser(u2);
//		myMain.uDao.updateUser(u2);
//		String[] f_names = {"Jen", "Bob", "Chris", "Dan", "Mary", "Sam", "Kate", "Barb"};
//		String[] l_names = {"Smith", "Place", "Person", "Again", "ABC", "DEF", "GHI", "JKL", "MNO", "PQR"};
//		int count = 0;
//		for(String fn:f_names) {
//			for(String ln:l_names) {
//				Person aUser = new Person();
//				aUser.setFname(fn);
//				aUser.setLname(ln);
//				aUser.setUsername(fn+ln);
//				aUser.setEmail(fn +ln+ "@gmail.com");
//				aUser.setPass("pass");
//				if(count%3==0) {
//					aUser.setType(UType.CUSTOMER);
//				}
//				if(count%3==1) {
//					aUser.setType(UType.ADMIN);
//				}
//				if(count%3==2) {
//					aUser.setType(UType.EMPLOYEE);
//				}
//				myMain.uDao.insertUser(aUser);
//				count++;
//			}
//		}
//		count = 0;
//		Set<Person> allUsers = myMain.uDao.getAllUsersByType(UType.CUSTOMER);
//		for(Person aUser:allUsers) {
//			Integer[] users = {aUser.getId()};
//			Account acc2 = new Account();
//			acc2.setOwners(users);
//			if(count%2==0) {
//				acc2.setApproved(true, UType.ADMIN);
//			}
//			acc2.setBalance((double) 5000);
//			myMain.aDao.insertAccount(acc2);
//			count++;
//		}
//
//		for(Account acca:myMain.aDao.getAllAccounts()) {
//			System.out.print(acca.getId()+" AP:");
//			System.out.print(acca.getApproved()+" EN:");
//			System.out.print(acca.getEnabled()+" BA:");
//			System.out.print(acca.getBalance()+" TY:");
//			System.out.print(acca.getType()+" OWN:");
//			if(acca.getOwners()!=null) {
//				for(int i:acca.getOwners()) {
//					System.out.print(i+" ");
//				}
//			}
//			else {
//				myMain.aDao.deleteAccount(acca.getId());
//			}
//			System.out.print("\n");
//		}
//		
//		for(Person use:myMain.uDao.getAllUsers()) {
//			System.out.print(" ID:"+ use.getId());
//			System.out.print(" FN:"+ use.getFname());
//			System.out.print(" LN:"+ use.getLname());
//			System.out.print(" UN:"+ use.getUsername());
//			System.out.print(" PA:"+ use.getPass());
//			System.out.print(" EM:"+ use.getEmail());
//			System.out.print(" WI:"+ use.getWithdrawn());
//			System.out.print(" TY:"+ use.getType());
//			System.out.print("\n");
//		}
	}
	
	@BeforeClass
	public static void makeUsersText() throws Exception {
		myMain = new Main();
		Person.nextId = 5;
		Account.nextId = 5;
		//myMain.uDao = new UserDaoText("./data/usersTest.txt");
		//myMain.aDao = new AccountDaoText("./data/accountsTest.txt");
		
//		for(Person use:myMain.uDao.getAllUsers()) {
//			myMain.uDao.deleteUser(use.getId());
//		}
//		for(Account acc:myMain.aDao.getAllAccounts()) {
//			myMain.aDao.deleteAccount(acc.getId());
//		}
//		for(Account acca:myMain.aDao.getAllAccounts()) {
//			System.out.print(acca.getId()+" AP:");
//			System.out.print(acca.getApproved()+" EN:");
//			System.out.print(acca.getEnabled()+" BA:");
//			System.out.print(acca.getBalance()+" TY:");
//			System.out.print(acca.getType()+" OWN:");
//			if(acca.getOwners()!=null) {
//				for(int i:acca.getOwners()) {
//					System.out.print(i+" ");
//				}
//			}
//			else {
//				myMain.aDao.deleteAccount(acca.getId());
//			}
//			System.out.print("\n");
//		}
//		
//		for(Person use:myMain.uDao.getAllUsers()) {
//			System.out.print(" ID:"+ use.getId());
//			System.out.print(" FN:"+ use.getFname());
//			System.out.print(" LN:"+ use.getLname());
//			System.out.print(" UN:"+ use.getUsername());
//			System.out.print(" PA:"+ use.getPass());
//			System.out.print(" EM:"+ use.getEmail());
//			System.out.print(" WI:"+ use.getWithdrawn());
//			System.out.print(" TY:"+ use.getType());
//			System.out.print("\n");
//		}
//		System.out.print("Before\n");
//		Person bob = new Person(111, "Bob", "person", "cUser", "cPassword", "realemail", UType.CUSTOMER);
//		myMain.uDao.insertUser(bob);
//		myMain.uDao.updateUser(bob);
//		Person u1 = new Person(112, "Another", "Person", "eUser", "ePassword", "realemailagain", UType.EMPLOYEE);
//		Person u2 = new Person(113, "Totally", "NotFake", "aUser", "aPassword", "another@email.com", UType.ADMIN);
//		myMain.uDao.insertUser(u1);
//		myMain.uDao.updateUser(u1);
//		myMain.uDao.insertUser(u2);
//		myMain.uDao.updateUser(u2);
//		String[] f_names = {"Jen", "Bob", "Chris", "Dan", "Mary", "Sam", "Kate", "Barb"};
//		String[] l_names = {"Smith", "Place", "Person", "Again", "ABC", "DEF", "GHI", "JKL", "MNO", "PQR"};
//		int count = 0;
//		for(String fn:f_names) {
//			for(String ln:l_names) {
//				Person aUser = new Person();
//				aUser.setFname(fn);
//				aUser.setLname(ln);
//				aUser.setUsername(fn+ln);
//				aUser.setEmail(fn +ln+ "@gmail.com");
//				aUser.setPass("pass");
//				if(count%3==0) {
//					aUser.setType(UType.CUSTOMER);
//				}
//				if(count%3==1) {
//					aUser.setType(UType.ADMIN);
//				}
//				if(count%3==2) {
//					aUser.setType(UType.EMPLOYEE);
//				}
//				myMain.uDao.insertUser(aUser);
//				count++;
//			}
//		}
//		count = 0;
//		Set<Person> allUsers = myMain.uDao.getAllUsersByType(UType.CUSTOMER);
//		for(Person aUser:allUsers) {
//			Integer[] users = {aUser.getId()};
//			Account acc2 = new Account();
//			acc2.setOwners(users);
//			if(count%2==0) {
//				acc2.setApproved(true, UType.ADMIN);
//			}
//			acc2.setBalance((double) 5000);
//			myMain.aDao.insertAccount(acc2);
//			count++;
//		}
//
//		for(Account acca:myMain.aDao.getAllAccounts()) {
//			System.out.print(acca.getId()+" AP:");
//			System.out.print(acca.getApproved()+" EN:");
//			System.out.print(acca.getEnabled()+" BA:");
//			System.out.print(acca.getBalance()+" TY:");
//			System.out.print(acca.getType()+" OWN:");
//			if(acca.getOwners()!=null) {
//				for(int i:acca.getOwners()) {
//					System.out.print(i+" ");
//				}
//			}
//			else {
//				myMain.aDao.deleteAccount(acca.getId());
//			}
//			System.out.print("\n");
//		}
//		
//		for(Person use:myMain.uDao.getAllUsers()) {
//			System.out.print(" ID:"+ use.getId());
//			System.out.print(" FN:"+ use.getFname());
//			System.out.print(" LN:"+ use.getLname());
//			System.out.print(" UN:"+ use.getUsername());
//			System.out.print(" PA:"+ use.getPass());
//			System.out.print(" EM:"+ use.getEmail());
//			System.out.print(" WI:"+ use.getWithdrawn());
//			System.out.print(" TY:"+ use.getType());
//			System.out.print("\n");
//		}
	}

}
