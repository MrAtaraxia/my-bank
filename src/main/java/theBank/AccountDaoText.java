package theBank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class AccountDaoText implements AccountDao {


	public String filePath = "./data/accounts.txt";
	
	AccountDaoText(String filePath){
		this.filePath = filePath;
	}
	
	AccountDaoText(){
		
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	private Set<Account> readAccounts() throws Exception {
        Set<Account> returnThis = new HashSet<Account>();

        FileInputStream fis = new FileInputStream(filePath);
        try {
		ObjectInputStream in = new ObjectInputStream(fis);
        returnThis = (Set<Account>) in.readObject();
        }
        catch(Exception e) {
        	
        }

        return returnThis;
    }

    protected Boolean writeAccounts(Set<Account> input) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(input);
            out.flush();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
	@Override
	public Account getAccount(int id) throws Exception {
		Set<Account> allAccount = readAccounts();
		for(Account aAccount:allAccount) {
			if(aAccount.getId()==id) {
				return aAccount;
			}
		}
		return null;
	}

	@Override
	public Set<Account> getAllAccounts() throws Exception {
		return readAccounts();
	}
	
	@Override
	public Set<Account> getAllActiveAccounts() throws Exception {
		Set<Account> toReturn = null;
		Set<Account> allAccounts = readAccounts();
		for(Account aAccount:allAccounts) {
			if(aAccount.getEnabled().equals(true) && 
					aAccount.getApproved().equals(true)) {
				if(toReturn==null) {
					toReturn = new HashSet<Account>();
				}
				toReturn.add(aAccount);
			}
		}
		return toReturn;
	}
	
	@Override
	public Set<Account> getAllNeedApprovalAccounts() throws Exception {
		
		Set<Account> toReturn = null;
		Set<Account> allAccounts = readAccounts();
		for(Account aAccount:allAccounts) {
			if(aAccount.getEnabled().equals(true) && 
					aAccount.getApproved().equals(false)) {
				if(toReturn==null) {
					toReturn = new HashSet<Account>();
				}
				toReturn.add(aAccount);
			}
		}
		return toReturn;
	}

	@Override
	public Set<Account> getActiveAccountsByUser(Integer UserID) throws Exception {
		
		Set<Account> toReturn = null;
		Set<Account> allAccounts = readAccounts();
		for(Account aAccount:allAccounts) {
			for(int aUser: aAccount.getOwners()) {
				if(aUser == UserID) {
					if(aAccount.getEnabled().equals(true) && 
							aAccount.getApproved().equals(true)) {
						if(toReturn==null) {
							toReturn = new HashSet<Account>();
						}
						toReturn.add(aAccount);
					}
				}
			}
		}
		return toReturn;
	}
	
	@Override
	public Set<Account> getAccountsByUser(Integer UserID) throws Exception {
		
		Set<Account> toReturn = null;
		Set<Account> allAccounts = readAccounts();
		for(Account aAccount:allAccounts) {
			for(int aUser: aAccount.getOwners()) {
				if(aUser == UserID) {
					if(toReturn==null) {
						toReturn = new HashSet<Account>();
					}
					toReturn.add(aAccount);
				}
			}
		}
		return toReturn;
	}

	@Override
	public boolean insertAccount(Account account) throws Exception {
		Set<Account> allAccounts = null;
		try{
			allAccounts = readAccounts();
		}
		catch(Exception e) {
			allAccounts = new HashSet<>();
		}
		
		if(account.getId().equals(null) || account.getOwners().equals(null)) {
			return false;
		}
		
		for(Account a :allAccounts) {
			if(a.getId().equals(account.getId())) {
				return false;
			}
		}
		allAccounts.add(account);
		if(writeAccounts(allAccounts)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateAccount(Account account) throws Exception {
		boolean updated = false;
		Set<Account> allAccounts = readAccounts();
		for(Account aAccount: allAccounts)
		{
			if(aAccount.getId() == account.getId()) {
				allAccounts.remove(aAccount);
				allAccounts.add(account);
				updated = true;
				break;
			}
		}
		if(updated) {
			if(writeAccounts(allAccounts)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteAccount(int id) throws Exception {
		Set<Account> allAccounts = readAccounts();
		Boolean deleted = false;
		for(Account aAccount: allAccounts)
		{
			if(aAccount.getId() == id) {
				allAccounts.remove(aAccount);
				deleted = true;
				break;
			}
		}
		if(deleted) {
			if(writeAccounts(allAccounts)) {
				return true;
			}
		}
		return false;
	}

}
