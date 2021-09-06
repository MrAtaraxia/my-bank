package theBank;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import theBank.Account;
import theBank.AccountDao;
import theBank.AccountDaoText;
import theBank.AccountType;

/**
 * AccountTest.java
 * @author w3
 */
// extends BaseTest
public class AccountTest{
	
	@Test
	public void createAccountTest() {
		AccountType[] theTypes = {
				AccountType.JOINT, 
				AccountType.CHECKING,
				AccountType.INDIVIDUAL,
				AccountType.SAVINGS};
		for(AccountType atype :theTypes) {
			Account myAccount = new Account();
			myAccount.setType(atype);
			int newTemp = myAccount.getType().getValue();
			System.out.println(myAccount.getType() + " - " + newTemp);
			myAccount.setType(AccountType.values()[newTemp]);
			assertEquals(atype, myAccount.getType());
		}
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void createBadAccountTypeTest() throws Exception {
		Account myAccount = new Account();
		int newTemp = 6;
		myAccount.setType(AccountType.values()[newTemp]);
	}
	
	@Test//(expected = ArrayIndexOutOfBoundsException.class)
	public void createAccountsTypeTest() throws Exception {
		AccountDaoText aDao = new AccountDaoText();
		//UserDao uDao = new UserDaoText();
		//Set<User> theUsers = uDao.getAllUsers();
		//for(User aUser:theUsers) {
		Account aAccount = new Account();
		Set<Account> sAcc = null;
		sAcc = aDao.getAllAccounts();
		if(sAcc == null) {
			sAcc = new HashSet<>();
		}
		sAcc.add(aAccount);
		for(Account acc: sAcc) {
			System.out.print(acc.getId() + " " + acc.getType() + " " + acc.getBalance() + "\n");
		}
		boolean testing = aDao.insertAccount(aAccount);
		assertEquals(true, testing);
	}
	
	@Test
	public void getAccountDaoTest() throws Exception {
		AccountDao aDao = new AccountDaoText();
		
		Account myThing = aDao.getAccount(2);
		System.out.println(myThing.getBalance());
		assertTrue(myThing.getBalance()==0.0d);
	}
	
}
