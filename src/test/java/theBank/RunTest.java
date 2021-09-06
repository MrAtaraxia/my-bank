package theBank;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import theBank.Account;
import theBank.Main;
import theBank.User;
import theBank.UserType;

/**
 * RunTest.java
 * @author w3
 */

public class RunTest extends BaseTest{

	// isTestWorking #######################
	@Test
	public void isTestWorkingTestOutput() throws Exception {
		
		final String[] testInput = {"iTW1", "iTW2", "iTW3"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		makeUsers();
		String testParam = "Hi";
		int testReturn = 5;
		int returned = aMain.isTestWorking(testParam);
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "iTW3\r\n";
		System.setOut(systemOut);
		System.out.print("isTestWorkingTestOutput");
		System.out.print(theOutput);
		System.out.print("isTestWorkingTestOutput");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		assertEquals(testReturn, returned);
	}
	// applyOpenAccount
	@Test
	public void applyOpenAccountTest() throws Exception {
		
		final String[] testInput = {"aOA1", "aOA2", "aOA3"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.currentUser = new User();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.aScanner = new Scanner(localStream);
		aMain.applyOpenAccount();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "Thank you for applying for wanting to create a new account.\n";
		System.setOut(systemOut);
		System.out.print("applyOpenAccountTest");
		System.out.print(theOutput);
		System.out.print("applyOpenAccountTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	// transferBetweenAccounts
		@Test
	public void transferBetweenAccounts1Test() throws Exception {
		final String[] testInput = {"10", "15","39", "-5", "1", "100000", "-5", "1"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.currentUser = new User();
		Account acco = aMain.aDao.getAccount(1);
		acco.setBalance((double)5000);
		aMain.aDao.updateAccount(acco);
		aMain.currentUser.setId(100);
		aMain.currentUser.setType(UserType.ADMIN);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setUsername("FirstLast100");
		aMain.currentUser.setPass("password");
		aMain.aScanner = new Scanner(localStream);
		aMain.transferBetweenAccounts();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = ".0\n";
		System.setOut(systemOut);
		System.out.print("transferBetweenAccounts");
		System.out.print(theOutput);
		System.out.print("transferBetweenAccounts");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	// applyOpenJointAccount
	@Test
	public void applyOpenJointAccountFailedTest() throws Exception {
		final String[] testInput = {"aOA1", "aOA2", "aOA3", "aOA3"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.currentUser = new User();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		
		aMain.aScanner = new Scanner(localStream);
		boolean returned = aMain.applyOpenJointAccount();
		aMain.aScanner.close();
		String theEnd = "We will send it to our employees to approve and add it to your account.\n";
		assertEquals(true, getOutput().startsWith(testBreak));
		assertEquals(true, getOutput().endsWith(theEnd));
		assertEquals(false, returned);
	}
	// viewCustAccInfo
	@Test
	public void viewCustAccInfoTestOutput() throws Exception {
		final String[] testInput = {"10", "10", "vCAI3"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		aMain.viewCustAccInfo();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "ID:17 BALANCE:5000.0\n";
		System.setOut(systemOut);
		//System.out.print(theOutput);
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	// viewCustPersonalInfo
	@Test
	public void viewCustPersonalInfoTestOutput() throws Exception {
		//System.setOut(systemOut);
		final String[] testInput = {"10", "30", "42"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		aMain.viewCustPersonalInfo();
		aMain.aScanner.close();
		String endsWith = "Username:   bsmith\n";
		String theOutput = getOutput();
		System.out.print(theOutput);
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(endsWith));
		//assertEquals(testReturn, returned);
	}
	// transferBetweenAccounts
	@Test
	public void transferBetweenAccountsOneAccountTest() throws Exception {
		final String[] testInput = {"T1", "T2", "T3"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.currentUser = aMain.uDao.getUser(10);
		aMain.aScanner = new Scanner(localStream);
		aMain.transferBetweenAccounts();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "You need at least 2 accounts to transfer between them.\n";
		System.setOut(systemOut);
		System.out.print(theOutput);
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}	
	// depositToAccount
	@Test
	public void depositToAccountFailTest() throws Exception {
		
		final String[] testInput = {"aOA1", "aOA2", "aOA3"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.currentUser = new User();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UserType.EMPLOYEE);
		aMain.aScanner = new Scanner(localStream);
		aMain.depositToAccount();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "deposit to:\n";
		System.setOut(systemOut);
		System.out.print("depositToAccountFail");
		System.out.print(theOutput);
		System.out.print("depositToAccountFail");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void depositToAccount1Test() throws Exception {
		
		final String[] testInput = {"12", "1", "aOA3", "-12", "1"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.currentUser = new User();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UserType.ADMIN);
		aMain.aScanner = new Scanner(localStream);
		aMain.depositToAccount();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "5001.0\n";
		System.setOut(systemOut);
		System.out.print("depositToAccount1");
		System.out.print(theOutput);
		System.out.print("depositToAccount1");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	
	// cancelAccount
	@Test
	public void cancelAccount1Test() throws Exception {
		
		Main aMain = new Main();
		aMain.currentUser = new User();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UserType.ADMIN);
		Set<Account> accounts = aMain.aDao.getAccountsByUser(100);
		Account anAccount = null;
		for(Account acc:accounts) {
			anAccount = acc;
		}
		final String[] testInput = {anAccount.getId().toString(),
				anAccount.getId().toString(), "r"};
		InputStream localStream = makeStream(testInput);
		aMain.aScanner = new Scanner(localStream);
		aMain.cancelAccount();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "Returning to the menu.\n";
		System.setOut(systemOut);
		System.out.print("cancelAccount1Test");
		System.out.print(theOutput);
		System.out.print("cancelAccount1Test");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void cancelAccount2Test() throws Exception {
		
		final String[] testInput = {"44","21", "14", "13", "10","61","53", "r"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.currentUser = new User();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UserType.EMPLOYEE);
		aMain.aScanner = new Scanner(localStream);
		aMain.cancelAccount();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "\n";
		System.setOut(systemOut);
		System.out.print("cancelAccount1Test");
		System.out.print(theOutput);
		System.out.print("cancelAccount1Test");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void cancelAccount3Test() throws Exception {
		
		Main aMain = new Main();
		aMain.currentUser = new User();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UserType.ADMIN);
		Set<Account> accounts = aMain.aDao.getAccountsByUser(100);
		Account anAccount = null;
		for(Account acc:accounts) {
			anAccount = acc;
		}
		final String[] testInput = {anAccount.getId().toString(),
				anAccount.getId().toString(), "r"};
		InputStream localStream = makeStream(testInput);
		aMain.aScanner = new Scanner(localStream);
		aMain.cancelAccount();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "menu.\n";
		System.setOut(systemOut);
		System.out.print("cancelAccount3Test");
		System.out.print(theOutput);
		System.out.print("cancelAccount3Test");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	
	/////////////////////////////////////////////////////////////////////////
	//
	// RUN
	//
	/////////////////////////////////////////////////////////////////////////
	
	// run
	@Test
	public void runFirstExitTest() throws Exception {
		final String[] testInput = {"2", "2", "2"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		aMain.run();
		aMain.aScanner.close();
		assertEquals(true, getOutput().startsWith(testBreak));
		assertEquals(true, getOutput().endsWith(endOfTest));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void runCreateAccountAndExitTest() throws Exception {
		final String[] testInput = {"1", "1", "abdefg", "n", "abdefg", "y", "12345678", "12345678", "2"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		aMain.run();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "DONE!\r\n";
		System.setOut(systemOut);
		System.out.print("runCreateAccountAndExitTest");
		System.out.print(theOutput);
		System.out.print("runCreateAccountAndExitTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void runAdminAccessAndExitTest() throws Exception {
		final String[] testInput = {"0", "0", "aUser", "aPassword", "6", "6"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		aMain.run();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "DONE!\r\n";
		System.setOut(systemOut);
		System.out.print("runAdminAccessAndExitTest");
		System.out.print(theOutput);
		System.out.print("runAdminAccessAndExitTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void runEmployeeAccessAndExitTest() throws Exception {
		final String[] testInput = {"0", "0", "eUser", "ePassword", "3", "3"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		aMain.run();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "DONE!\r\n";
		System.setOut(systemOut);
		System.out.print("runEmployeeAccessAndExitTest");
		System.out.print(theOutput);
		System.out.print("runEmployeeAccessAndExitTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void runCustomerAccessAndExitTest() throws Exception {
		final String[] testInput = {"0", "0", "cUser", "cPassword", "5", "5"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		aMain.run();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "DONE!\r\n";
		System.setOut(systemOut);
		System.out.print("runCustomerAccessAndExitTest");
		System.out.print(theOutput);
		System.out.print("runCustomerAccessAndExitTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void runCustomerCUserIPassAndExitTest() throws Exception {
		//System.setOut(systemOut);
		final String[] testInput = {"0", "0", "cUser", "wrongPass", "2"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		aMain.run();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "DONE!\r\n";
		System.setOut(systemOut);
		System.out.print("runCustomerCUserIPassAndExitTest");
		System.out.print(theOutput);
		System.out.print("runCustomerCUserIPassAndExitTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	
	/////////////////////////////////////////////////////////////////////////
	//
	// MENUS
	//
	/////////////////////////////////////////////////////////////////////////

	// adminMenu
	@Test
	public void adminMenuTest() throws Exception {
		//System.setOut(systemOut);
		final String[] testInput = {
				"-1", 			//Error
				"0",  			//View All Accounts.
				"1", "1", "q",	//Withdraw from Account.
				//"2", "10", "q",	//"Deposit to Account"
				//"3", "10", "q", //"Transfer Between Accounts"
				//"4", "8", "r",	//"Approve/Deny Open Applications"
				"6"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.aScanner = new Scanner(localStream);
		//String testParam = "Hi";
		//int testReturn = 5;
		//try {
		aMain.currentUser = new User();
		aMain.currentUser.setType(UserType.ADMIN);
		aMain.adminMenu();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "Input > ";
		System.setOut(systemOut);
		System.out.print("adminMenuTest");
		System.out.print(theOutput);
		System.out.print("adminMenuTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	// employMenu
	@Test
	public void employMenuTest() throws Exception {
		final String[] testInput = {
				"-1", 
				"0", "10", //"View Customer Account Information"
				"1", "10", //"View Customer Personal Information"
				"2", "33", "r", //"Approve/Deny Open Applications"
				"3",};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.currentUser = new User();
		aMain.currentUser.setType(UserType.EMPLOYEE);
		aMain.aScanner = new Scanner(localStream);
		aMain.employMenu();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "Input > ";
		System.setOut(systemOut);
		System.out.print("employMenuTest");
		System.out.print(theOutput);
		System.out.print("employMenuTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	// custMenu
	@Test
	public void custMenuTest() throws Exception {
		//System.setOut(systemOut);
		final String[] testInput = {
				"-1", 
				"0", //"Apply to open an account."
				"1", "12",//"Apply to open a join account."
				"2", "8", "r", //"Withdraw from Account"
				"3", "8", "r", //"Deposit to Account"
				"4", //"Transfer between Accounts"
				"5"};
		InputStream localStream = makeStream(testInput);
		Main aMain = new Main();
		aMain.currentUser = aMain.uDao.getUser(32);
		aMain.aScanner = new Scanner(localStream);
		aMain.custMenu();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "Input > ";
		System.setOut(systemOut);
		System.out.print("employMenuTest");
		System.out.print(theOutput);
		System.out.print("employMenuTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	
}


















