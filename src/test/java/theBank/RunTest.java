package theBank;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import theBank.People.UType;
import theBank.People.Person;
import theBank.accounts.Account;

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
		Main aMain = myMain;
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
		Main aMain = myMain;
		aMain.currentUser = new Person();
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
		Main aMain = myMain;
		Account a1 = null;
		Account a2 = null;
		for(Account acc:aMain.aDao.getAllActiveAccounts()) {
			if(acc.getBalance() > 100) {
				if(a1==null) {
					a1 = acc;
				}
				else {
					a2 = acc;
					break;
				}
			}
		}
		final String[] testInput = {
				a1.getId().toString(), 
				a2.getId().toString(),
				"-5", "100000", "39", "1"};
		InputStream localStream = makeStream(testInput);
		aMain.currentUser = aMain.uDao.getUser(113);
		aMain.aDao.getAccount(1);
		
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
		Main aMain = myMain;
		aMain.currentUser = new Person();
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
	@Test
	public void  applyOpenJointAccount1Test() throws Exception {
		

		Main aMain = myMain;
		Set<Person> theUsers = aMain.uDao.getAllUsersByType(UType.CUSTOMER);
		List<Person> uList = new ArrayList<>();
		for(Person use:theUsers) {
			uList.add(use);
		}
		Person use1 = uList.get(0);
		Person use2 = uList.get(1);
		aMain.currentUser = use1;
		String[] testInput = {
				use2.getId().toString(), 
				" ", 
				" "};
		InputStream localStream = makeStream(testInput);
		aMain.aScanner = new Scanner(localStream);
		aMain.applyOpenJointAccount();
		aMain.aScanner.close();
		aMain.currentUser = use2;
		testInput = new String[]{
				use1.getId().toString(), 
				" ", 
				" "};
		localStream = makeStream(testInput);
		aMain.aScanner = new Scanner(localStream);
		aMain.applyOpenJointAccount();
		aMain.aScanner.close();
		
		String theOutput = getOutput();
		String expectedEnd = "You have successfully applied for a joint account with that user!\n";
		System.setOut(systemOut);
		System.out.print("applyOpenJointAccount1Test");
		System.out.print(theOutput);
		System.out.print("applyOpenJointAccount1Test");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	// viewCustAccInfo
	@Test
	public void viewCustAccInfoTestOutput() throws Exception {
		Main aMain = myMain;
		String[] testInput = {""};
		Person theUser = null;
		for (Person aUser:aMain.uDao.getAllUsersByType(UType.CUSTOMER)) {
			if(aMain.aDao.getActiveAccountsByUser(aUser.getId())==null) {
				continue;
			}
			testInput = new String[]{aUser.getId().toString(),
					aUser.getId().toString()};
			theUser = aUser;
			InputStream localStream = makeStream(testInput);
			aMain.aScanner = new Scanner(localStream);
			break;
		}
		
		Set<Account> theAccs = aMain.aDao.getAccountsByUser(theUser.getId());
		aMain.viewCustAccInfo();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "";
		System.setOut(systemOut);
		System.out.print("viewCustAccInfo");
		System.out.print(theOutput);
		System.out.print("viewCustAccInfo");
		if(theAccs != null) {
			expectedEnd = "5000.0\n";
		}
		else {
			expectedEnd = "have any active accounts at this time.\n";
		}
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	// viewCustPersonalInfo
	@Test
	public void viewCustPersonalInfoTestOutput() throws Exception {
		Main aMain = myMain;
		Person tempUser = null;
		for(Person use:aMain.uDao.getAllUsersByType(UType.CUSTOMER)) {
			tempUser = use;
			break;
		}

		final String[] testInput = {tempUser.getId().toString()};
		InputStream localStream = makeStream(testInput);
		aMain.aScanner = new Scanner(localStream);
		aMain.viewCustPersonalInfo();
		aMain.aScanner.close();
		String endsWith = "Username:   "+tempUser.getUsername()+"\n";
		String theOutput = getOutput();
		System.setOut(systemOut);
		System.out.print("viewCustPersonalInfo");
		System.out.print(theOutput);
		System.out.print("viewCustPersonalInfo");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(endsWith));
		//assertEquals(testReturn, returned);
	}
	// transferBetweenAccounts
	@Test
	public void transferBetweenAccountsOneAccountTest() throws Exception {
		final String[] testInput = {"T1", "T2", "T3"};
		InputStream localStream = makeStream(testInput);
		Main aMain = myMain;
		aMain.currentUser = aMain.uDao.getUser(10);
//		for(Account acc:aMain.aDao.getAccountsByUser(10)) {
//			aMain.aDao.deleteAccount(acc.getId());
//		}
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
		Main aMain = myMain;
		aMain.currentUser = new Person();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UType.EMPLOYEE);
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
		Main aMain = myMain;
		aMain.currentUser = new Person();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UType.ADMIN);
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
		
		Main aMain = myMain;
		aMain.currentUser = new Person();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UType.ADMIN);
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
		Main aMain = myMain;

		aMain.currentUser = new Person();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UType.EMPLOYEE);
		aMain.aScanner = new Scanner(localStream);
		aMain.cancelAccount();
		aMain.aScanner.close();
		String theOutput = getOutput();
		System.setOut(systemOut);
		System.out.print("cancelAccount1Test");
		System.out.print(theOutput);
		System.out.print("cancelAccount1Test");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(testBreak));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void cancelAccount3Test() throws Exception {
		
		Main aMain = myMain;
		aMain.currentUser = new Person();
		aMain.currentUser.setId(100);
		aMain.currentUser.setFname("First");
		aMain.currentUser.setLname("Last");
		aMain.currentUser.setType(UType.ADMIN);
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
		Main aMain = myMain;
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
		Main aMain = myMain;
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
		Main aMain = myMain;
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
		Main aMain = myMain;
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
		Main aMain = myMain;
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
		Main aMain = myMain;
		Account tempAcc=null;
		for(Account acc:myMain.aDao.getAllActiveAccounts()) {
			if(acc.getBalance()>100) {
				tempAcc = acc;
				break;
			}
		}
		final String[] testInput = {
				"-1", 			//Error
				"0",  			//View All Accounts.
				"1", tempAcc.getId().toString(), "q",	//Withdraw from Account.
				"2", tempAcc.getId().toString(), "q",	//"Deposit to Account"
				//"3", "10", "q", //"Transfer Between Accounts"
				//"4", "8", "r",	//"Approve/Deny Open Applications"
				"6"};
		InputStream localStream = makeStream(testInput);
		aMain.aScanner = new Scanner(localStream);
		//String testParam = "Hi";
		//int testReturn = 5;
		//try {
		aMain.currentUser = aMain.uDao.getUser(113);
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
		Main aMain = myMain;
		Person tempUser = null;
		Account tempAcc = null;
		for(Person use:aMain.uDao.getAllUsersByType(UType.CUSTOMER)) {
			tempUser = use;
			break;
		}
		for(Account acc:aMain.aDao.getAllNeedApprovalAccounts()) {
			tempAcc = acc;
		}
		final String[] testInput = {
				"-1", 
				"0", tempUser.getId().toString(), //"View Customer Account Information"
				"1", tempUser.getId().toString(), //"View Customer Personal Information"
				"2", tempAcc.getId().toString(), "r", //"Approve/Deny Open Applications"
				"3",};
		InputStream localStream = makeStream(testInput);
		aMain.currentUser = new Person();
		aMain.currentUser.setType(UType.EMPLOYEE);
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
		Main aMain = myMain;
		Person tempUser = null;
		Account a1 = null;
		for(Person use:aMain.uDao.getAllUsersByType(UType.CUSTOMER)) {
			tempUser = use;
			if(aMain.aDao.getActiveAccountsByUser(tempUser.getId())==null) {
				continue;
			}
			else {
				for(Account a:aMain.aDao.getActiveAccountsByUser(tempUser.getId())) {
					a1 = a;
					break;
				}
			}
			break;
		}


		final String[] testInput = {
				"-1", 
				"0", //"Apply to open an account."
				"1", "3000", "3000",//"Apply to open a join account."
				"2", a1.getId().toString(), "r", //"Withdraw from Account"
				"3", a1.getId().toString(), "r", //"Deposit to Account"
				//"4", //"Transfer between Accounts"
				"5"};
		InputStream localStream = makeStream(testInput);
		aMain.currentUser = tempUser;
		aMain.aScanner = new Scanner(localStream);
		aMain.custMenu();
		aMain.aScanner.close();
		String theOutput = getOutput();
		String expectedEnd = "Input > ";
		System.setOut(systemOut);
		System.out.print("custMenuTest");
		System.out.print(theOutput);
		System.out.print("custMenuTest");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	@Test
	public void custMenuTest2() throws Exception {

		Main aMain = myMain;
		String[] testInput = {""};
		Set<Person> theUsers = aMain.uDao.getAllUsersByType(UType.CUSTOMER);
		for(Person use:theUsers) {
			for(Account acc:aMain.aDao.getAccountsByUser(use.getId())) {
				if(acc.getApproved()&&acc.getEnabled()) {
					System.out.print("\n\nTHE ACCOUNT NUMBER" + acc.getId()+"\n\n");
					testInput = new String[]{
							"3", "3", acc.getId().toString(), acc.getId().toString(), //"Deposit to Account"
							
							"2", "2", acc.getId().toString(), acc.getId().toString(), //"Withdraw from Account"
							"5", "5"};
					aMain.currentUser = use;
					InputStream localStream = makeStream(testInput);
					aMain.aScanner = new Scanner(localStream);
					try {
					aMain.custMenu();
					}
					catch(Exception e) {
						
					}
					aMain.aScanner.close();
					break;
				}
			}
		}	
		String theOutput = getOutput();
		String expectedEnd = "Quit\nInput > ";
		System.setOut(systemOut);
		System.out.print("custMenuTest2");
		System.out.print(theOutput);
		System.out.print("custMenuTest2");
		assertEquals(true, theOutput.startsWith(testBreak));
		assertEquals(true, theOutput.endsWith(expectedEnd));
		//assertEquals(testReturn, returned);
	}
	
}


















