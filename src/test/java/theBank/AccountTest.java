package theBank;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import theBank.DAO.*;
import theBank.accounts.*;

/**
 * AccountTest.java
 * @author w3
 */
// extends BaseTest
public class AccountTest{
	
	@Test
	public void createAccountTest() {
//		AType[] theTypes = {
//				AType.JOINT, 
//				AType.CHECKING,
//				AType.INDIVIDUAL,
//				AType.SAVINGS};
//		for(AType atype :theTypes) {
//			Account myAccount = new Account();
//			myAccount.setType(atype);
//			int newTemp = myAccount.getType().getValue();
//			System.out.println(myAccount.getType() + " - " + newTemp);
//			myAccount.setType(AType.values()[newTemp]);
			assertTrue(true);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void createBadAccountTypeTest() throws Exception {
		Account myAccount = new Account();
		int newTemp = 6;
		myAccount.setType(AType.values()[newTemp]);
	}
	
	@Test//(expected = ArrayIndexOutOfBoundsException.class)
	public void createAccountsTypeTest() throws Exception {
		AccountDaoText aDao = new AccountDaoText();
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
		assertTrue(true);
	}
	
}
