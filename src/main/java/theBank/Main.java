package theBank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;

import theBank.accounts.*;
import theBank.general.*;
import theBank.DAO.*;
import theBank.People.*;

import org.apache.logging.log4j.LogManager;

//public class MyClass{
//private static final Logger logger = LogManager.getLogger(MyClass.class);
//	public static void main(String[] args)
//		logger.info("Insert your log message here.");
//	}
//}

/**
 * Main.java
 * @author w3
 * Ctrl Alt Down Arrow duplicates currently line
 */
 
public class Main {
    // Define a static logger variable so that it references the
    // Logger instance named "Main".
	private static final Logger logger = LogManager.getLogger(Main.class);
	
	public double allowed_per_day = 10000.0d;
	public  String clearScreen = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
	public  String spacingOnScreen = "\n\n\n\n\n\n\n\n";

	
	Menu[] loginMenu = null;
	Menu[] customerMenu = null;
	Menu[] employeeMenu = null;
	Menu[] adminMenu = null;
	Menu[] adminAccViewMenu = null;
	Menu[] adminAccEditMenu = null;
	String[] DEFAULT_YES_YES = {"", "yes", "y", "[yes]", "[y]", "ye", "[ye]", "[]"};
	String[] DEFAULT_YES_NO = {"no", "n", "[n]", "[no]"};
	String[] DEFAULT_NO_YES = {"yes", "y", "[yes]", "[y]", "ye", "[ye]"};
	String[] DEFAULT_NO_NO = {"no", "n", "[n]", "[no]", "", "[]"};
	String[] DEFAULT_QUIT = {"q",  "[q]", "qu", "[qu]", "qui", "[qui]", "quit", "[quit]", "[q]uit", "[q]ui", "[q]u"};
	
	String[] APPROVE = {"a", "ap", "app", "appr", "appro", "approv", "approve", "[a]", "[a]p", "[a]pp", "[a]ppr", "[a]ppro", "[a]pprov", "[a]pprove"};
	String[] RETURN = {"r", "re", "ret", "retu", "retur", "return", "[r]", "[r]e", "[r]et", "[r]etu", "[r]etur", "[r]eturn"};
	String[] DENY = {"d", "de", "den", "deny", "[d]", "[d]e", "[d]en", "[d]eny"};
	String[] CONFIRM = {"c", "co", "con", "conf", "confi", "confir", "confirm", "[c]", "[c]o", "[c]on", "[c]onf", "[c]onfi", "[c]onfir", "[c]onfirm"};
	String WELCOME_MESSAGE = 	"Welcome to Bank MyBank!";
	String QUIT_TEXT = 			"0 - Quit to Main Menu";
	String Y_ND_Q = 			"[Y]es or [N]o* or [Q]uit";
	String YD_N_Q = 			"[Y]es* or [N]o or [Q]uit";
	String Y_ND = 				"[Y]es or [N]o*";
	String YD_N = 				"[Y]es* or [N]o";
	String PRESS_ANY = 			"Press Any Key to Continue:";
	int minLength = 5;
	int maxLength = 50;
	protected BankDaoImpl bDao = new BankDaoImpl();
	protected PersonDaoImpl pDao = new PersonDaoImpl();
	protected UsernameDaoImpl uDao = new UsernameDaoImpl();
	protected AddressDaoImpl AdDao = new AddressDaoImpl();
	protected AccountDaoImpl AcDao = new AccountDaoImpl();
	protected AccountOwnerDaoImpl AoDao = new AccountOwnerDaoImpl();
	protected TransactionDaoImpl tDao = new TransactionDaoImpl();

	Username currentUser = null;
	Person currentPerson = null;
	Bank currentBank = bDao.getBank(1);
	Address CurAddr = AdDao.getAddress(3);
	UType typeOfUser = UType.NONE;
	public Scanner aScanner = null;
	
	public Main() throws Exception {
		logger.trace("Constructor() start.");
		
		List<Account> allAccounts= null;
		allAccounts = AcDao.getAllAccounts();
		
		int nextAcc = 0;
		for(Account acc: allAccounts) {
			if(nextAcc < acc.getId()) {
				nextAcc = acc.getId();
			}
		}
		
		Account.nextId = nextAcc+1;
		List<Person> allUsers = new ArrayList<>();

		allUsers = pDao.getAllPeople();
		int nextUser = 0;
		for(Person use: allUsers) {
			if(nextUser < use.getId()) {
				nextUser = use.getId();
			}
		}
//		User.nextId = nextUser+1;
//		
		
		System.out.print("NEXT U:" + Person.nextId + "\n");
		System.out.print("NEXT A:" + Account.nextId + "\n");
		//User bob = new User(13, "aUser", "aPassword", 20, UserType.ADMIN);
		//bob.getName();
		//allUsers.get(i).
		aScanner = new Scanner(System.in);
		loginMenu = new Menu[3];
		loginMenu[0] = new Menu("0", "Login");
		loginMenu[1] = new Menu("1", "Register");
		loginMenu[2] = new Menu("2", "Quit");
		
		customerMenu = new Menu[6];
		customerMenu[0] = new Menu("0", "Apply to open an account.");
		customerMenu[1] = new Menu("1", "Apply to open a join account.");
		customerMenu[2] = new Menu("2", "Withdraw from Account", "IfAccount");
		customerMenu[3] = new Menu("3", "Deposit to Account", "IfAccount");
		customerMenu[4] = new Menu("4", "Transfer between Accounts", "If2Accounts");
		customerMenu[5] = new Menu("5", "Quit");
		
		employeeMenu = new Menu[4];
		employeeMenu[0] = new Menu("0", "View Customer Account Information");
		employeeMenu[1] = new Menu("1", "View Customer Personal Information");
		employeeMenu[2] = new Menu("2", "Approve/Deny Open Applications", "IfApplications");
		employeeMenu[3] = new Menu("3", "Quit");
		
		adminMenu = new Menu[7];
		adminMenu[0] = new Menu("0", "View All Accounts");
		adminMenu[1] = new Menu("1", "Withdraw from Account");
		adminMenu[2] = new Menu("2", "Deposit to Account");
		adminMenu[3] = new Menu("3", "Transfer Between Accounts");
		adminMenu[4] = new Menu("4", "Approve/Deny Open Applications", "IfApplications");
		adminMenu[5] = new Menu("5", "Cancel Account");
		adminMenu[6] = new Menu("6", "Quit");
		//logger.trace("Constructor() end.");
	}

	public void run() throws Exception {
		logger.trace("Entering run.");
		splashScreen();
		String choice;
		boolean running = true;
		while(running) {
			choice = getInput(loginMenu);
			switch(choice) {
			case "Login":
				int returned = 0;
				if(loginScreen() > 0) {
					switch(typeOfUser) {
					case CUSTOMER:
						returned = custMenu();
						running = false;
						break;
					case EMPLOYEE:
						returned = employMenu();
						running = false;
						break;
					case ADMIN:
						returned = adminMenu();
						running = false;
						break;
					default:
						break;
					}
					if(returned == -1) {
						running = false;
					}
				}
				break;
			case "Register":
				registerCustomer();
				break;
			case "Quit":
				running = false;
				break;
			default:
				break;
			}
		}
		System.out.println("DONE!");
		logger.trace("Exiting Run.");
	}

	public int custMenu() throws Exception {
		//logger.info("Enter Customer Menu.");
		if(!currentUser.getUType().equals(UType.CUSTOMER)) {
			//logger.warn("THIS SHOULD NOT HAPPEN!");
			return -1;
		}
		boolean running = true;
		String choice;
		while(running) {
			choice = getInput(customerMenu);
			switch(choice) {
			case "Apply to open an account.":
				applyOpenAccount();
				break;
			case "Apply to open a join account.":
				applyOpenJointAccount();
				break;
			case "Withdraw from Account":
				withdrawFromAccount();
				break;
			case "Deposit to Account":
				depositToAccount();
				break;
			case "Transfer between Accounts":
				transferBetweenAccounts();
				break;
			case "Quit":
				//logger.info("Exiting Customer Menu.");
				return -1;
			default:
				break;
			}
		}
		//logger.warn("This SHOULD NOT HAPPEN!");
		return 0;
	}
	
	public void applyOpenAccount() {
		//logger.info("Enter Apply Open Account.");
		//System.out.print(custMenuChoicesText[0]);
		System.out.println("OPEN ACCOUNT1");
		Account tempAcc = new Account();
		List<AccountOwner> AO = new ArrayList<>();
		tempAcc.setAtype(AType.INDIVIDUAL);
		tempAcc.setAstate(AState.PENDING);
		tempAcc.setActive(true);
		tempAcc.setBalance(0.0d);
		tempAcc.setAccountNumber(tempAcc.getId());
		tempAcc.setBankID(currentBank.getId());
		AccountOwner newAO = new AccountOwner();
		System.out.println("OPEN ACCOUNT2");
		newAO.setAccountID(tempAcc.getId());
		newAO.setPersonID(currentUser.getPersonID());
		System.out.println("OPEN ACCOUNT3");
		AO.add(newAO);
		try {
			if(AcDao.insertAccount(tempAcc)!=null)
			{
				logger.info("User:" + currentUser.getId().toString() + " - Applied to open an account.");
				System.out.print("You have been added to the list of users who want to add new accounts.\n");
				System.out.print("When an employee approves of your new account\nit will be added to your list of accounts.\n");
				System.out.print("Thank you for applying for wanting to create a new account.\n");
			}
			else {
				System.out.print("Sadly the system is unable to process your request.\nTry again later!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Sadly the system is unable to process your request. Try again later!");
		}

		//logger.info("Exit Apply Open Account.");
	}

	public boolean applyOpenJointAccount() throws Exception {
		//logger.info("Enter Apply Open Joint Account.");
		String theInput;
		System.out.print("Please type in the UserID of the person you want to open the account with.\n");
		System.out.print("Input > ");
		theInput = aScanner.nextLine();
		logger.info("User:" + currentUser.getId().toString() + " - Applied to open a joint account with:" + theInput);
		for(Username aUser:uDao.getAllUsernamesByType(UType.CUSTOMER)) {
			if(theInput.equals(aUser.getId().toString())) {
				System.out.print("USERID!"+aUser.getId());
				if(!aUser.getId().equals(currentUser.getId())){
					List<Account> tempAccounts = AcDao.getAccountsByAccountIDs(AoDao.getAllAccountIDsByAOs(AoDao.getAllActiveAOsByPersonID(aUser.getPersonID())));
					for(Account acc:tempAccounts) {
						int auserAO = -1;
						int currAO = -1;
						List<AccountOwner> curAOs= AoDao.getAllActiveAOsByAccountID(acc.getId());
						if(curAOs.size() > 1) {
							for(int i = 0; i < curAOs.size();i++) {
								if(curAOs.get(i).getPersonID().equals(aUser.getPersonID())) {
									auserAO=i;
								}
								if(curAOs.get(i).getPersonID().equals(currentPerson.getId())) {
									currAO=i;
								}
							}
							if(auserAO >= 0 && currAO >= 0) {
								if(acc.getAstate().equals(AState.ACTIVE)) {
									System.out.print("You already have a joint account with that user!\n");
									return false;
								}
								else if(acc.getAstate().equals(AState.AWAITING_CONFIRMATION)){
									if(curAOs.get(currAO).getOtype().equals(OType.NEEDSAPPROVAL)){
										curAOs.get(currAO).setOtype(OType.APPROVED);
										acc.setAstate(AState.PENDING);
										System.out.print("You have successfully applied for a joint account with that user!\n");
										return true;
									}else {
										System.out.print("You are currently waiting for the other user to confirm the account!\n");
										return false;
									}
								}
							}
						}
					}
					AccountOwner newOwn1 = new AccountOwner();
					AccountOwner newOwn2 = new AccountOwner();
					newOwn1.setPersonID(currentUser.getId());
					newOwn2.setPersonID(aUser.getId());
					newOwn1.setOtype(OType.APPROVED);
					newOwn2.setOtype(OType.NEEDSAPPROVAL);
					Account tempAcc = new Account();
					tempAcc.setAtype(AType.JOINT);
					tempAcc.setActive(true);
					tempAcc.setAstate(AState.AWAITING_CONFIRMATION);
					tempAcc = AcDao.insertAccount(tempAcc);
					newOwn1.setAccountID(tempAcc.getId());
					newOwn2.setAccountID(tempAcc.getId());
					AoDao.insertAO(newOwn1);
					AoDao.insertAO(newOwn2);
					System.out.print("SUCCESS\n");
					System.out.print("Once the person whose UserID you typed, tries to open an account with your id.\n");
					System.out.print("We will send it to our employees to approve and add it to your account.\n");
					//logger.info("Applied to Open Joint Account.");
					return true;
				}
			}
		}
		System.out.print("Once the person whose UserID you typed, tries to open an account with your id.\n");
		System.out.print("We will send it to our employees to approve and add it to your account.\n");
		//logger.warn("Failed to Apply to Open Joint Account.");
		return false;
	}
	
	public List<Account> getAccounts() throws Exception{
		//logger.info("getAccounts Start");
		if(currentUser.getUType().equals(UType.ADMIN)) {
			return AcDao.getAllAccounts();
		}
		else if (currentUser.getUType().equals(UType.CUSTOMER)) {
			return AcDao.getAccountsByAccountIDs(AoDao.getAllAccountIDsByAOs(AoDao.getAllActiveAOsByPersonID(currentUser.getPersonID())));
		}
		return null;
	}
	
	public void withdrawFromAccount() throws Exception {
		//logger.info("Withdraw From Account Start.");
		System.out.print("Which Account would you like to withdraw from:\n");
		List<Account> tempAccounts = getAccounts();
		Account tempAccount = null;
		if(tempAccounts != null) {
			tempAccount = getAccount(tempAccounts);
		}
		double wAmount = -1.0d;
		while(wAmount<0 && tempAccount!=null) {
			System.out.print("Account:" + tempAccount.getId() + ": has a balance of " + tempAccount.getBalance() + "\n");
			System.out.print("Q - To return to the menu.\n");
			System.out.print("How much would you like to withdraw?\n");
			System.out.print("Input > ");
			String theInput = aScanner.nextLine();
			if(theInput.equalsIgnoreCase("q")) {
				break;
			}
			try {
				wAmount = Double.parseDouble(theInput);
				if(wAmount < 0) {
					System.out.print("You are not allowed to withdraw a negative amount.\nPlease try again.\n");
					continue;
				}
				else if(wAmount > tempAccount.getBalance()) {
					System.out.print("You are not allowed to withdraw more than you have in the account.\nPlease try again.\n");
					continue;
				}
				else if(wAmount + pDao.getPerson(currentUser.getPersonID()).getWithdrawn() > allowed_per_day) {
					System.out.print("You are not allowed to withdraw that much today.\nPlease decrease the amount or try again tomorrow.\n");
					continue;
				}
				else {
					System.out.print("You have withdrawn " + wAmount + " from account " + tempAccount.getId() + "\n");
					logger.info("Acc:" + tempAccount.getId() + " old balance " + tempAccount.getBalance());
					tempAccount.setBalance(tempAccount.getBalance()-wAmount);
					AcDao.updateAccount(tempAccount);
					logger.info("User:" + currentUser.getId().toString() + " - withdrew " + wAmount + " from " + tempAccount.getId());
					logger.info("Acc:" + tempAccount.getId() + " new balance " + tempAccount.getBalance());
					System.out.print("Account " + tempAccount.getId() + " has a new balance of " + tempAccount.getBalance() + "\n");
					break;
				}
			}
			catch(Exception e) {
				System.out.print("Please try again.\n");
				continue;
			}
			
		}
	}
	
	public void depositToAccount() throws Exception {
		//logger.info("Deposit To Account Start.");
		System.out.print("Which Account would you like to deposit to:\n");
		List<Account> tempAccounts = getAccounts();
		Account tempAccount = null;
		if(tempAccounts != null) {
			tempAccount = getAccount(tempAccounts);
		}
		double wAmount = -1.0d;
		while(wAmount<0 && tempAccount!=null) {
			System.out.print("Account:" + tempAccount.getId() + ": has a balance of " + tempAccount.getBalance() + "\n");
			System.out.print("Q - To return to the menu.\n");
			System.out.print("How much would you like to deposit?\n");
			System.out.print("Input > ");
			String theInput = aScanner.nextLine();
			if(theInput.equalsIgnoreCase("q")) {
				break;
			}
			try {
				wAmount = Double.parseDouble(theInput);
				
				if(wAmount < 0) {
					System.out.print("You are not allowed to deposit a negative amount.\nPlease try again.\n");
					continue;
				}
				else {
					System.out.print("You have deposited " + wAmount + " to account " + tempAccount.getId() + "\n");
					logger.info("Acc:" + tempAccount.getId() + " old balance " + tempAccount.getBalance());
					tempAccount.setBalance(tempAccount.getBalance()+wAmount);
					AcDao.updateAccount(tempAccount);
					logger.info("User:" + currentUser.getId().toString() + "-deposited-" + wAmount + "-into-" + tempAccount.getId());
					logger.info("Acc:" + tempAccount.getId() + " new balance " + tempAccount.getBalance());
					System.out.print("Account " + tempAccount.getId() + " has a new balance of " + tempAccount.getBalance() + "\n");
					break;
				}
			}
			catch(Exception e) {
				System.out.print("Please try again.\n");
				continue;
			}
			
		}
	}

	public void transferBetweenAccounts() throws Exception{
		//logger.info("Transfer Between Accounts Start.");
		while(true) {
			List<Account> tempAccounts = getAccounts();
			if(tempAccounts == null || tempAccounts.size()<2) {
				System.out.print("You need at least 2 accounts to transfer between them.\n");
				break;
			}
			Account fromAcc = null;
			Account toAcc = null;
			double wAmount = -0.0d;
			System.out.print("Which Account would you like to transfer from:\n");
			fromAcc = getAccount(tempAccounts);
			System.out.print("Which Account would you like to transfer to:\n");
			toAcc = getAccount(tempAccounts);
			while (true) {
				System.out.print("How much would you like to transfer\nFROM:" + fromAcc.getId() + " BALANCE:" + fromAcc.getBalance()+ "\nTO:  " + toAcc.getId()+ " BALANCE:" + toAcc.getBalance() + "\n");
				System.out.print("Q - To return to the menu.\n");
				System.out.print("Input > ");
				String theInput = aScanner.nextLine();
				if(theInput.equalsIgnoreCase("q")) {
					break;
				}
				try {
					wAmount = Double.parseDouble(theInput);
					if(wAmount < 0) {
						System.out.print("You are not allowed to withdraw a negative amount.\nPlease try again.\n");
						continue;
					}
					else if(wAmount > fromAcc.getBalance()) {
						System.out.print("You are not allowed to withdraw more than the total that is in the account.\nPlease try again.\n");
						continue;
					}
					else {
						System.out.print("You have transfered " + wAmount + " from account " + fromAcc.getId() + " to account " + toAcc.getId() +"\n");
						logger.info("Acc:" + fromAcc.getId() + " old balance " + fromAcc.getBalance());
						logger.info("Acc:" + toAcc.getId() + " old balance " + toAcc.getBalance());
						fromAcc.setBalance(fromAcc.getBalance()-wAmount);
						toAcc.setBalance(toAcc.getBalance()+wAmount);
						AcDao.updateAccount(fromAcc);
						AcDao.updateAccount(toAcc);
						logger.info("User:" + currentUser.getId().toString() + "-transfered-" + wAmount + "-from-" + fromAcc.getId()+ "-to-" + toAcc.getId());
						logger.info("Acc:" + fromAcc.getId() + " new balance " + fromAcc.getBalance());
						logger.info("Acc:" + toAcc.getId() + " new balance " + toAcc.getBalance());
						System.out.print("Account " + fromAcc.getId() + " has a new balance of " + fromAcc.getBalance() + "\n");
						System.out.print("Account " + toAcc.getId() + " has a new balance of " + toAcc.getBalance() + "\n");
						break;
					}
				}
				catch(Exception e) {
					System.out.print("Please try again.\n");
					continue;
				}
			}
			break;
		}
	}
	
	public int employMenu() throws Exception{
		//logger.info("Employee Menu Start.");
		if(!currentUser.getUType().equals(UType.EMPLOYEE)) {
			logger.warn("THIS SHOULD NOT HAPPEN!");
			return -1;
		}
		boolean running = true;
		String choice;
		while(running) {
			choice = getInput(employeeMenu);
			switch(choice) {
			case "View Customer Account Information":
				viewCustAccInfo();
				break;
			case "View Customer Personal Information":
				viewCustPersonalInfo();
				break;
			case "Approve/Deny Open Applications":
				approveDeny();
				break;
			case "Quit":
				return -1;
			default:
				//logger.warn("THIS SHOULD NOT HAPPEN!");
				break;
			}
		}
		return 0;
	}

	public int isTestWorking(String s) {
		//logger.info("TESTING if tests work!");
		System.out.println(s);
		while(aScanner.hasNextLine()) {
			System.out.println(aScanner.nextLine());
		}
		return 5;
	}
	
	public void viewCustAccInfo() throws Exception {
		//logger.info("View Customer Account Info");
		Username tempUser = null;
		tempUser = getCustomer("Account Information");
		List<Account> tempAccounts = null;
		tempAccounts = AcDao.getAccountsByAccountIDs(AoDao.getAllAccountIDsByAOs(AoDao.getAllActiveAOsByPersonID(tempUser.getPersonID())));
		if (tempAccounts != null) {
			System.out.print("User " + tempUser.getId() + " has active accounts:\n");
			for(Account acc:tempAccounts) {
				System.out.print("ID:" + acc.getId() + " BALANCE:" + acc.getBalance()+ "\n");
			}
		}
		else
		{
			System.out.print("User " + tempUser.getId() + " does not have any active accounts at this time.\n");
		}
	}
	
	public void viewCustPersonalInfo() throws Exception {
		//logger.info("View Customer Personal Information");
		Username tempUser = getCustomer("Personal Information");
		Person tempPerson = pDao.getPerson(tempUser.getPersonID());
		if(tempUser!=null) {
			System.out.print("User " + tempUser.getId() + "\n");
			System.out.print("First Name: " + tempPerson.getFname() + "\n");
			System.out.print("Last Name:  " + tempPerson.getLname() + "\n");
			System.out.print("Email:      " + tempPerson.getEmail() + "\n");
			System.out.print("Username:   " + tempUser.getUsername() + "\n");
		}
	}
	
	public int adminMenu() throws Exception {
		//logger.info("Admin Menu Start");
		if(!currentUser.getUType().equals(UType.ADMIN)) {
			return -1;
		}
		boolean running = true;
		String choice;
		while(running) {
			choice = getInput(adminMenu);
			switch(choice) {
			case "View All Accounts":
				viewAllAccounts();
				break;
			case "Withdraw from Account":
				withdrawFromAccount();
				break;
			case "Deposit to Account":
				depositToAccount();
				break;
			case "Transfer Between Accounts":
				transferBetweenAccounts();
				break;
			case "Approve/Deny Open Applications":
				approveDeny();
				break;
			case "Cancel Account":
				cancelAccount();
				break;
			case "Quit":
				return -1;
			default:
				break;
			}
		}
		return 0;
	}
	
	public void viewAllAccounts() throws Exception{
		//logger.info("View All Accounts");
		//System.out.println("VIEW ACCOUNTS####################");
		List<Account> tempAccs = getAccounts();
		//System.out.println("VIEW ACCOUNTS2####################");
		for(Account acc : tempAccs) {
			String owners = "";
			for(Integer use : AoDao.getAllPersonIDsByAOs(AoDao.getAllActiveAOsByAccountID(acc.getId()))) {
				owners += use.toString();
			}
			System.out.print("ID:" + acc.getId() + " Balance" + acc.getBalance());
			System.out.print(" Type:" + acc.getAtype() + " Owners:" + owners + "\n");
		}
	}
	
	public void cancelAccount() throws Exception {
		//logger.info("Cancel Account");
		if(currentUser.getUType().equals(UType.ADMIN))
		{
			List<Account> accounts = getAccounts();
			boolean exit = false;
			if(accounts!=null) {
				System.out.print("Select an account to cancel:\n");
				Account acc = getAccount(accounts);
				String owners = "";
				for(Integer i:AoDao.getAllPersonIDsByAOs(AoDao.getAllActiveAOsByAccountID(acc.getId()))) {
					owners += i.toString() + " ";
				}
				String theInput = "";
				while(true) {
					System.out.print("Account: " + acc.getId() + "\n");
					System.out.print("Owners:  " + owners + "\n");
					System.out.print("Balance:  " + acc.getBalance() + "\n");
					System.out.print("[C]onfirm or [R]eturn to the menu >");
					theInput = aScanner.nextLine();
					for(String check: RETURN) {
						if(theInput.equalsIgnoreCase(check)){
							System.out.print("Returning to the menu.\n");
							exit = true;
							break;
						}
					}
					if(exit) {
						break;
					}
					for(String check: CONFIRM) {
						if(theInput.equalsIgnoreCase(check)){
							System.out.print("Canceling account:" + acc.getId()+ "\n");
							AcDao.CancelAccount(acc.getId());
							exit = true;
							break;
						}
					}
					if(exit) {
						break;
					}
				}
			}
		}
	}
	
	public void approveDeny() throws Exception {
		//logger.info("Approve/Deny");
		boolean exit = false;
		if((currentUser.getUType().equals(UType.ADMIN)||
				currentUser.getUType().equals(UType.EMPLOYEE)))
		{
			List<Account> accounts = AcDao.getAccountsByState(AState.PENDING);
			if(accounts!=null) {
				System.out.print("Select an account to approve or deny:\n");
				Account acc = getAccount(accounts);
				String owners = "";
				for(Integer i:AoDao.getAllPersonIDsByAOs(AoDao.getAllActiveAOsByAccountID(acc.getId()))) {
					owners += i.toString() + " ";
				}
				String theInput = "";
				while(true) {
					System.out.print("Account: " + acc.getId() + "\n");
					System.out.print("Owners:  " + owners + "\n");
					System.out.print("[A]pprove the account, [D]eny the account, or [R]eturn to the menu >");
					theInput = aScanner.nextLine();
					for(String check: RETURN) {
						if(theInput.equalsIgnoreCase(check)){
							System.out.print("Returning to the menu.\n");
							exit=true;
							break;
						}
					}
					if(exit) {
						break;
					}
					for(String check: APPROVE) {
						if(theInput.equalsIgnoreCase(check)){
							System.out.print("Approving the account.\n");
							acc.setAstate(AState.ACTIVE);
							AcDao.updateAccount(acc);
							exit = true;
							break;
						}
					}
					if(exit) {
						break;
					}
					for(String check: DENY) {
						if(theInput.equalsIgnoreCase(check)){
							System.out.print("Denying the account.\n");
							AcDao.deactivateAccount(acc.getId());
							exit=true;
							break;
						}
					}
					if(exit) {
						break;
					}
				}
			}
			else {
				System.out.print("There are currently no accounts that need approval!\n");
			}
		}
	}
	
	String[] splashScreenText = {
			new StringBuilder(clearScreen+WELCOME_MESSAGE+spacingOnScreen+PRESS_ANY).toString(),
			clearScreen};
	
	public void splashScreen() {
		//logger.info("Splash Screen");
		System.out.print(splashScreenText[0]);
		aScanner.nextLine();
		System.out.print(splashScreenText[1]);
	}
	
	@SuppressWarnings("unused")
	public int loginScreen() throws Exception {
		//logger.info("Login Screen");
		String username = inputChoice("Username");
		String password = inputChoice("Password");
		//System.out.println("LOGIN PERSON");
		Person tempPerson = pDao.getPersonByUserAndPass(username, password);
		//System.out.println("PERSONID"+tempPerson.getId());
		//System.out.println("LOGIN USERNAME");
		Username tempUser = uDao.getUsernameByPersonID(tempPerson.getId());
		//System.out.println("USERID" + tempUser.getId());
		if(tempPerson==null) {
			System.out.print("That Username and Password combination does not work.\n");
			System.out.print("Please try again later.\nThank you.");
			return -1;
		} else {
			currentUser = tempUser;
			currentPerson = tempPerson;
			
			typeOfUser = tempUser.getUType();
			return 1;
		}
	}
	
	String[] inputChoiceText = {clearScreen,
			"Input ",
			" : ",
			clearScreen};
	
	public String inputChoice(String to_input) {
		//logger.info("Input Choice");
		String to_return;
		System.out.print(inputChoiceText[0]);
		System.out.print(inputChoiceText[1] + to_input +inputChoiceText[2]);
		to_return = aScanner.nextLine();
		System.out.print(inputChoiceText[3]);
		return to_return;
	}
	
	String[] registerCustomerText = {
			clearScreen + "Please input a username for the account.\nThe minimum length for a username is " + minLength + " characters.\n",
			"Input > ",
			"Unfortunatly, the minimum length is " + minLength + " characters.\nPlease try again.\n",
			"Unfortunatly, the maximum length is " + maxLength + " characters.\nPlease try again.\n",
			"Unfortunatly, spaces are not allowed.\n" + "Please try again.\n",
			"Confirm you want username : ",
			"\n",
			clearScreen, 
			"Please input a password for the account.\nThe minimum length for a password is " + minLength + " characters.\n",
			"Input > ",
			clearScreen + "Unfortunatly, the minimum length is " + minLength + " characters.\nPlease try again.\n",
			clearScreen + "Unfortunatly, the maximum length is " + maxLength + " characters.\nPlease try again.\n",
			clearScreen + "Unfortunatly, spaces are not allowed.\nPlease try again.\n",
			"Confirm your password by tying it again.\n",
			clearScreen + "Unfortunatly, the passwords did not match.\nPlease try again.\n",
			"CREATING USER: ",
			":",
			"\n"};
	
	public int registerCustomer() throws Exception {
		//logger.info("Register Customer");
		boolean running = true;
		String uName = "";
		String theInput = "";
		String confirm = "";
		while (running) {
			while(uName=="") {
				System.out.print(registerCustomerText[0]);
				System.out.print(registerCustomerText[1]);
				theInput = aScanner.nextLine();
				if(theInput.length() < minLength) {
					System.out.print(registerCustomerText[2]);
				}
				else if(theInput.length() > maxLength) {
					System.out.print(registerCustomerText[3]);
				}
				else if(theInput.indexOf(" ")!=-1) {
					System.out.print(registerCustomerText[4]);
				}
				else {
					System.out.print(registerCustomerText[5] + theInput + registerCustomerText[6]);
					System.out.print(YD_N_Q);
					confirm = aScanner.nextLine();
					for(String choice : DEFAULT_QUIT) {
						if(confirm.equals(choice)) {
							return 0;
						}
					}
					for(String choice : DEFAULT_YES_YES) {
						if(confirm.equals(choice)) {
							if(uDao.UsernameExists(theInput)) {
								System.out.print("Unfortunatly, that username is used by another.\nPlease try again.\n");
								continue;
							}
							else {
								uName = theInput;
							}
						}
					}
				}
				System.out.print(registerCustomerText[7]);
			}
			String uPass = "";
			while(uPass=="") {
				System.out.print(registerCustomerText[8]);
				System.out.print(registerCustomerText[9]);
				theInput = aScanner.nextLine();

				if(theInput.length() < minLength) {
					System.out.print(registerCustomerText[10]);
				}
				else if(theInput.length() > maxLength) {
					System.out.print(registerCustomerText[11]);
				}
				else if(theInput.indexOf(" ")!=-1) {
					System.out.print(registerCustomerText[12]);
				}
				else {
					System.out.print(registerCustomerText[13]);
					confirm = aScanner.nextLine();
					if(confirm.equals(theInput)) {
						uPass = theInput;
						Username use = new Username();
						Person per = new Person();
						per.setAddressID(CurAddr.getId());
						per.setEmail("default@account");
						per.setFname("default");
						per.setLname("account");
						per.setWithdrawn(0.0);
						pDao.insertPerson(per);
						per = pDao.getPerson(per.getId());
						System.out.println("NEW PERSON ID" + per.getId());
						use.setUsername(uName);
						use.setPass(uPass);
						use.setPersonID(per.getId());
						use.setUType(UType.CUSTOMER);
						use.setActive(true);
						uDao.insertUsername(use);
						use = uDao.getUsername(use.getId());
						System.out.println("NEW USER ID" + per.getId());
						
						currentUser=use;
						currentPerson=per;
						logger.info("User:" + currentUser.getUsername() + " - Registered a user.");
						return 1;
					}

					System.out.print(registerCustomerText[14]);
				}
			}
		}
		return 0;
	}

	/**
	 * The method that gets input from the user, with predefined choices.
	 * @param myMenu a menu with the choices.
	 * @return a string version of the index. (Easier to work with than the indexes).
	 */

	String[] getInputText = {
			"Please select from the following choices:\n",
			" - ",
			"\n",
			"Input > ",
			"Please try again.\n"};
	
	public String getInput(Menu[] myMenu) {
		//logger.info("Get Input");
		String myInput;
		int to_return = -1;
		while(true) {
			System.out.print(getInputText[0]);
			// Outputs the predefined choices.
			for(Menu item: myMenu) {
				System.out.print(item.number + getInputText[1] + item.name + getInputText[2]);
			}
			System.out.print(getInputText[3]);
			myInput = aScanner.nextLine();
			for(Menu item: myMenu) {
				if(myInput.equalsIgnoreCase(item.number)) {
					try {
						to_return = Integer.parseInt(myInput);
					}
					catch(NumberFormatException e) {
					}
					return myMenu[to_return].name;
				}
			}
			System.out.print(getInputText[4]);
		}
	}
	
	public Account getAccount(List<Account> accts) {
		//logger.info("Get Account");
		String myInput;
		while(true) {
			System.out.print(getInputText[0]);
			for(Account item: accts) {
				System.out.print(item.getId() + ":" + item.getAtype() + ":" + item.getBalance() + "\n");
			}
			System.out.print(getInputText[3]);
			//Get the next line of input
			//BLOCK aScanner = new Scanner();
			myInput = aScanner.nextLine();
			for(Account item: accts) {
				if(myInput.equalsIgnoreCase(String.valueOf(item.getId()))) {
					return item;
				}
			}
			System.out.print(getInputText[4]);
		}
	}
	
	public Username getCustomer(String thing) throws Exception {
		//logger.info("Get Customer");
		List<Username> persons = uDao.getAllUsernamesByType(UType.CUSTOMER);
		String myInput;
		while(true) {
			System.out.print("Please input the UserID of the user whose " + thing + " you would like to view.\n");
			myInput = aScanner.nextLine();
			for(Username use: persons) {
				if(myInput.equalsIgnoreCase(use.getId().toString())) {
					return use;
				}
			}
			System.out.print(getInputText[4]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		//logger.trace("start.");
		//logger.info("STARTING main");
		Main myMain = null;
		try {
			myMain = new Main();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			myMain.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//logger.info("ENDING main");
		//logger.trace("end");
	}

}
